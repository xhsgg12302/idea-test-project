package site.wtfu.framework.utils;

import lombok.AllArgsConstructor;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.AEADBlockCipher;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.bouncycastle.tls.*;
import org.bouncycastle.tls.crypto.CryptoHashAlgorithm;
import org.bouncycastle.tls.crypto.TlsECConfig;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl;
import org.bouncycastle.tls.crypto.impl.bc.BcTlsCrypto;
import org.bouncycastle.tls.crypto.impl.bc.BcTlsECDomain;
import org.junit.jupiter.api.Test;
import site.wtfu.framework.utils.tls.ExBcTlsECDH;
import site.wtfu.framework.utils.tls.OuterBcTlsAEADCCipherImpl;

import java.io.IOException;
import java.math.BigInteger;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/12/26
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class DecryptDemo {
    public static void main(String[] args) throws IOException {
        TlsKeyExchangeFactory factory = new DefaultTlsKeyExchangeFactory();
        TlsKeyExchange keyExchange = factory.createECDHEKeyExchangeClient(KeyExchangeAlgorithm.ECDHE_ECDSA);


        //keyExchange.init(clientContext);
    }

    public static byte[] hexStringToByteArray(String s) {
        s = s.replaceAll("\\s", "");
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    static byte[] concat(byte[] a, byte[] b)
    {
        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    static byte[] additionalData(long seqNo, short recordType, ProtocolVersion recordVersion,
                                 int ciphertextLength, int plaintextLength, byte[] connectionID){
        byte[] additional_data = new byte[13];
        TlsUtils.writeUint64(seqNo, additional_data, 0);
        TlsUtils.writeUint8(recordType, additional_data, 8);
        TlsUtils.writeVersion(recordVersion, additional_data, 9);
        TlsUtils.writeUint16(plaintextLength, additional_data, 11);
        return additional_data;
    }

    @Test
    public void testStringToBytes(){
        String data = "39 fa 55 3b 42 5e 31 90 f8 25 8c 26 e2 a6 bc 43 00 4b 12 9d b7 d6 5d 46 9c 16 8e 5b ";
        byte[] bytes = hexStringToByteArray(data);
        System.out.println(TLSTest.bytesToHex(bytes));
    }



    @Test
    public void testTlsHashForMD5(){

        BcTlsCrypto bcTlsCrypto = new BcTlsCrypto();
        Digest digest = bcTlsCrypto.createDigest(CryptoHashAlgorithm.md5);


        byte[] append = " from MD5".getBytes();
        digest.update(append,0, append.length);

        byte[] bytes = "hello world".getBytes();
        digest.update(bytes, 0, bytes.length);

        byte[] rv = new byte[digest.getDigestSize()];
        digest.doFinal(rv, 0);

        // 21 18 fd 60 2c a3 ab a1 0e 87 39 29 01 aa 3a 57
        System.out.println(TLSTest.bytesToHex(rv));

    }


    public static TlsSecret TestPreMaster(byte[] peerPublicKey, BigInteger big) throws IOException {
        TlsECConfig ecConfig = new TlsECConfig(0x0017);
        ECDomainParameters domainParameters = BcTlsECDomain.getDomainParameters(ecConfig);
        BcTlsECDomain domain = new BcTlsECDomain(new BcTlsCrypto(), new TlsECConfig(0x0017));

        // BcTlsECDH
        ExBcTlsECDH exBcTlsECDH = new ExBcTlsECDH(domain);
        ECPoint var5 = new FixedPointCombMultiplier().multiply(domainParameters.getG(), big);
        AsymmetricCipherKeyPair localKeyPair = new AsymmetricCipherKeyPair(new ECPublicKeyParameters(var5, domainParameters), new ECPrivateKeyParameters(big, domainParameters));

        // byte[] point = bcTlsECDH.generateEphemeral();
        exBcTlsECDH.setLocalKeyPair(localKeyPair);
        exBcTlsECDH.receivePeerValue(peerPublicKey);
        TlsSecret tlsSecret = exBcTlsECDH.calculateSecret();
        return tlsSecret;
    }

    /**
     * 通过预主密匙计算 masterSecret
     * @throws IOException
     */
    public static TlsSecret testMasterSecret(byte[] peerPublicKey, BigInteger big, String asciiLabel, byte[] clientRandom, byte[] serverRandom) throws IOException {

        //TlsKeyExchangeFactory factory = new DefaultTlsKeyExchangeFactory();
        //TlsKeyExchange keyExchange = factory.createECDHEKeyExchangeClient(KeyExchangeAlgorithm.ECDHE_ECDSA);
        //keyExchange.init(clientContext);

        //TlsSecret preMasterSecret = keyExchange.generatePreMasterSecret(); // 主要通过本地的私匙，和 远端的私匙 来生成一个预主密匙。
        //TlsSecret tlsSecret = agreement.calculateSecret();

        // extendedMasterSecret = false
        byte[] seed = concat(clientRandom, serverRandom);

        TlsSecret preMasterSecret = TestPreMaster(peerPublicKey, big);
        // TlsSecret masterSecret = TlsUtils.calculateMasterSecret(context, preMasterSecret);
        TlsSecret masterSecret = preMasterSecret.deriveUsingPRF(PRFAlgorithm.tls_prf_sha256, asciiLabel, seed, 48);
        return masterSecret;
    }

    @AllArgsConstructor
    class TlsAEADCipher {
        private TlsSecret masterSecret;
        private TlsAEADCipherImpl encryptCipher;
        private TlsAEADCipherImpl decryptCipher;

        private byte[] encryptNonce;
        private byte[] decryptNonce;

        private int fixed_iv_length;
        private int record_iv_length;
        private int macSize;
    }

    public TlsAEADCipher testCreateConstructor() throws IOException {
        /*
         * construct
         */
        BlockCipher blockCipher = AESEngine.newInstance();
        AEADBlockCipher aeadBlockCipher = GCMBlockCipher.newInstance(blockCipher);
        OuterBcTlsAEADCCipherImpl encryptCipher = new OuterBcTlsAEADCCipherImpl(aeadBlockCipher, true);
        OuterBcTlsAEADCCipherImpl decryptCipher = new OuterBcTlsAEADCCipherImpl(aeadBlockCipher, false);

        int nonceMode =  1; //NONCE_RFC5288;
        int fixed_iv_length = 4, record_iv_length = 8;
        int keySize = 16, macSize = 16;
        // ProtocolVersion negotiatedVersion = ProtocolVersion.TLSv12;
        byte[] decryptConnectionID = null;
        boolean decryptUseInnerPlaintext = false;

        byte[] decryptNonce = new byte[fixed_iv_length];
        byte[] encryptNonce = new byte[fixed_iv_length];
        boolean isServer = false;

        int keyBlockSize = (2 * keySize) + (2 * fixed_iv_length);
        byte[] peerPublicKey = hexStringToByteArray("04 cf 1f 1d 3c 13 27 b0 bf d5 0d f9 55 9e b2 c2 e5 f7 ad ed db db 34 03 37 6e d2 e9 b1 ae 93 a0 b1 33 87 5d 9b 90 d9 71 ee df ea 19 d3 b9 da 74 bf 60 f3 39 4c 37 5d dc 75 82 68 fe a3 9d 7e c1 a4 ");
        BigInteger big = new BigInteger("41421376590410550347532242163556460089898812947798573278257512740784871055544");
        String asciiLabel = "master secret";
        byte[] clientRandom = hexStringToByteArray("e3 e5 0a 0c c0 f1 f7 49 4c 14 89 45 9c 28 05 39 fa b4 e9 ce 08 77 f0 94 1f 44 4e 05 34 d6 4b c0 ");
        byte[] serverRandom = hexStringToByteArray("52 f6 1e a4 6d 79 2e 2b 1b 94 70 d7 eb 21 5d 42 ed 4b a3 44 88 45 f2 b8 c1 4b 63 8f 28 5b e9 fe ");

        TlsSecret masterSecret = testMasterSecret(peerPublicKey, big, asciiLabel, clientRandom, serverRandom);
        int cipherSuite = CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256; //securityParameters.getCipherSuite();   TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 (0xc02b)
        int keyExchangeAlgorithm = KeyExchangeAlgorithm.ECDHE_ECDSA;
        int encryptionAlgorithm = EncryptionAlgorithm.AES_128_GCM; //getEncryptionAlgorithm(cipherSuite);
        int macAlgorithm = MACAlgorithm._null; // getMACAlgorithm(cipherSuite);
        byte[] seed = concat(serverRandom, clientRandom);
        byte[] keyBlock = masterSecret.deriveUsingPRF(PRFAlgorithm.tls_prf_sha256, ExporterLabel.key_expansion, seed, keyBlockSize).extract();

        int pos = 0;
        encryptCipher.setKey(keyBlock, pos, keySize);
        pos += keySize;
        decryptCipher.setKey(keyBlock, pos, keySize); pos += keySize;
        System.arraycopy(keyBlock, pos, encryptNonce, 0, fixed_iv_length);
        pos += fixed_iv_length;
        System.arraycopy(keyBlock, pos, decryptNonce, 0, fixed_iv_length); pos += fixed_iv_length;
        if (keyBlockSize != pos) {throw new RuntimeException("that's here");}
        return new TlsAEADCipher(masterSecret, encryptCipher,decryptCipher, encryptNonce,decryptNonce, fixed_iv_length,record_iv_length, macSize);
    }

    @Test
    public  void testDecodeCipherForFinished() throws IOException {

        TlsAEADCipher tlsAEADCipher = testCreateConstructor();
        TlsAEADCipherImpl decryptCipher = tlsAEADCipher.decryptCipher;
        byte[] decryptNonce = tlsAEADCipher.decryptNonce;
        int record_iv_length = tlsAEADCipher.record_iv_length;
        int macSize = tlsAEADCipher.macSize;

        /*
         * decodeCipherText
         *
         * params:
         *      byte[] ciphertext,
         *      int ciphertextOffset,
         *      int ciphertextLength
         */
        byte[] finished = hexStringToByteArray("16 03 03 00 28 84 ef 8c ec 32 b4 9f d9 a5 e6 04 \n" +
                "70 54 90 69 44 14 84 ce ad d3 03 e0 eb 66 36 3e \n" +
                "01 28 88 6e d7 5c ed f6 80 58 e6 6d a4");
        byte[] ciphertext = finished;
        int ciphertextOffset = 5;
        int ciphertextLength = ciphertext.length - ciphertextOffset;
        byte[] nonce = new byte[decryptNonce.length + record_iv_length];
        System.arraycopy(decryptNonce, 0, nonce, 0, decryptNonce.length);
        System.arraycopy(ciphertext, ciphertextOffset, nonce, nonce.length - record_iv_length, record_iv_length);
        decryptCipher.init(nonce, macSize);

        int encryptionOffset = ciphertextOffset + record_iv_length;
        int encryptionLength = ciphertextLength - record_iv_length;
        int innerPlaintextLength = decryptCipher.getOutputSize(encryptionLength);

        // byte[] additionalData = getAdditionalData(seqNo, recordType, recordVersion, ciphertextLength,innerPlaintextLength, decryptConnectionID);
        byte[] additionalData = hexStringToByteArray("00 00 00 00 00 00 00 01 17 03 03 09 ec ");
        additionalData = additionalData(0L,(short)0x16, ProtocolVersion.TLSv12, 0, innerPlaintextLength, null);
        byte[] target = new byte[ciphertext.length];
        decryptCipher.doFinal(additionalData, ciphertext, encryptionOffset, encryptionLength, ciphertext, encryptionOffset);
        System.out.println(TLSTest.bytesToHex(ciphertext));
    }

    @Test
    public  void testDecodeCipherForResponse() throws IOException {

        TlsAEADCipher tlsAEADCipher = testCreateConstructor();
        TlsAEADCipherImpl decryptCipher = tlsAEADCipher.decryptCipher;
        byte[] decryptNonce = tlsAEADCipher.decryptNonce;
        int record_iv_length = tlsAEADCipher.record_iv_length;
        int macSize = tlsAEADCipher.macSize;

        /*
         * decodeCipherText
         *
         * params:
         *      byte[] ciphertext,
         *      int ciphertextOffset,
         *      int ciphertextLength
         */
        byte[] response = hexStringToByteArray("17 03 03 0a 04 84 ef 8c ec 32 b4 9f da 96 81 de \n" +
                "e1 95 e3 1c 13 dc cf 70 da d2 f9 30 c6 70 49 47 \n" +
                "f1 78 eb b6 eb 7e ba c7 7b 22 3a 18 6b 2e 29 fe \n" +
                "f8 d6 46 62 21 89 d8 84 05 a5 29 28 23 bc a8 05 \n" +
                "2b 0f 77 95 bb e2 ba 20 14 c5 5d 89 5d 11 c2 df \n" +
                "5d 46 f0 4d 38 5e 2c ca 1f 46 4f 05 4e 8d 6d f8 \n" +
                "7a 0a 30 22 78 49 08 3f 40 bd e6 52 99 39 19 7d \n" +
                "59 8c 19 3c 9f b4 f5 0f 68 e7 51 00 47 71 4a 12 \n" +
                "a2 97 35 8e e9 12 65 01 88 17 7e fd 85 c1 e6 f1 \n" +
                "0f ca bf 0d 08 88 8c b9 d4 c4 5f 4b 18 bf ba 26 \n" +
                "f6 15 84 92 df be e5 83 15 d7 3b 6c 92 d7 66 e5 \n" +
                "a6 ad 42 77 0d eb 24 f2 21 fb 60 04 c4 a5 ab 8e \n" +
                "7b df 2f c9 74 a3 83 a5 29 bd b6 c3 e4 d7 d1 e9 \n" +
                "b3 78 91 af c3 00 1a 0f 3a 61 19 ce 2a 9b 43 1e \n" +
                "d1 f7 02 6c 93 90 46 0a 8b 6c 96 37 56 6d dd 3d \n" +
                "3b a0 68 ad 30 e6 f4 3b 02 b5 05 73 8b a9 4b 3f \n" +
                "2e 0d 26 e2 f4 be ad 30 9a 52 83 73 65 b4 b2 e8 \n" +
                "6b b2 0d 66 ba dd 58 8d 5c 2e 34 13 e0 52 0b 91 \n" +
                "66 dd ed 30 2c b9 9e 7a 14 4b 52 06 8d 3f bd 01 \n" +
                "72 dc b2 68 0d 83 64 c4 00 01 12 b4 34 4a c4 bd \n" +
                "15 eb e6 1c 62 ec 92 e5 ea 5b 6f d6 94 1f 89 6b \n" +
                "c7 37 82 ad 28 2f 33 ff ac 63 13 47 e8 81 fb 4e \n" +
                "6c ac 27 d5 f1 7c 0a 23 20 4d 15 77 7f 70 dd 4d \n" +
                "e4 f3 18 6c b6 42 00 4f 5c 97 11 9b 4a 5d 46 90 \n" +
                "c4 53 d4 1c 57 94 b9 ba 06 2d 53 ab f9 b8 f7 bf \n" +
                "4f 60 4d 28 f7 6e e1 be 83 e1 36 2f 61 65 d3 31 \n" +
                "67 90 1f e0 ae 84 92 e8 d5 26 74 fc 15 82 37 cf \n" +
                "33 ed 86 df 7c e7 12 a9 c1 48 b3 53 23 e5 55 0d \n" +
                "50 7f 2b 9a 65 dd eb 40 1c 2a 1f b0 fa 44 97 49 \n" +
                "99 cb a9 f0 f6 cd 95 1e a8 f3 b9 4b 2e 6a 09 cd \n" +
                "03 90 ec 04 a9 b7 6d ba 20 fe 38 cc e5 a6 cc e6 \n" +
                "b2 62 f3 fb 2f 00 ae 9d 59 cb 9a 31 2c 74 5e e0 \n" +
                "35 47 58 d8 68 25 9d fd 32 51 9a 82 a4 66 32 df \n" +
                "6b fa 4f e7 40 a3 31 6f cd ee 23 e5 fe 14 41 06 \n" +
                "e2 cb b9 3a c3 85 c7 fa d7 aa dd e1 3a b9 5c 46 \n" +
                "0d 17 9b e2 21 5a 3c b6 8f 97 a2 a6 5d 4f 8e 4a \n" +
                "b0 3d 50 66 04 0f 9a 42 f0 42 bc 79 0f 0b 68 d6 \n" +
                "a3 36 dd 36 f4 06 7d f9 51 69 18 5d 13 2f 6a 54 \n" +
                "b5 8c f0 ee 0d 86 67 f5 85 54 51 30 06 48 5b f6 \n" +
                "f9 e5 e1 d8 4a 45 f2 75 da 17 69 60 c2 c4 09 36 \n" +
                "0d 76 9c 3e 30 0d ce ed 1c 55 aa d1 d0 c8 62 6f \n" +
                "18 41 cf f9 0c 49 df 19 15 a5 c5 25 57 5d f0 2f \n" +
                "14 86 e3 37 49 46 a3 01 5e c0 58 38 d6 20 70 4a \n" +
                "36 67 2c 5b d3 26 cf ed 46 12 d1 3e 83 a8 69 57 \n" +
                "0e b0 8e f9 87 2e 69 dd fe e9 88 22 fb eb 74 0f \n" +
                "a3 4a 3e 39 f5 cb b2 42 32 b8 3d b0 8e 72 3a 87 \n" +
                "7f 79 ec 51 24 7e 34 7c a5 e3 e7 22 b0 05 bd 62 \n" +
                "f8 75 da 28 0a 55 16 78 f2 ed 06 e1 5d d8 6e 56 \n" +
                "2b 88 78 93 2d fd 4d da 8e 59 eb b6 f4 12 86 d9 \n" +
                "c3 f6 7b 85 56 05 01 5d 1d ac 35 1a 4b 41 91 b6 \n" +
                "aa 79 00 90 a7 47 0c 1c 9e 82 b6 43 f2 30 29 19 \n" +
                "1b b4 72 e2 61 b9 24 77 1e 53 47 57 7d cf e3 4e \n" +
                "97 ae f2 88 01 5e 7a 8d 55 1f 7a a3 06 a7 4f 0c \n" +
                "56 72 c9 5b b1 41 73 5a 38 c0 fb 16 a3 d8 f8 da \n" +
                "e3 fc a4 e3 91 4e ab 5a 94 1b 5a 02 69 d3 11 e9 \n" +
                "06 e0 95 1a 21 bd 3c f5 ef ec e2 f3 ed be 5d b0 \n" +
                "0d a7 49 7b 5c 66 4d e6 4d 2f 89 52 b9 d2 3c 2f \n" +
                "c1 ec 26 73 8d 52 c5 d7 20 49 c9 57 3f f8 f2 da \n" +
                "d5 46 13 a1 30 a1 24 60 27 cb 95 ab 7f 15 1c 6c\n" +
                //"void\n" +
                "9b 98 0c a8 80 ce 9b 17 06 3b 3d 6e c2 24 0b e3 \n" +
                "29 36 b0 41 df 6b 94 97 8f e4 be e8 d8 5b fb 99 \n" +
                "f8 01 e8 c8 9b fd 6e fd 67 74 74 02 27 9e dc c4 \n" +
                "52 a8 18 dc 83 25 19 5e 34 5d e1 33 d9 55 cb 39 \n" +
                "10 78 66 5a 88 65 44 de 11 3b 38 d2 94 42 26 4b \n" +
                "4e 64 c7 17 5a 62 f3 e8 83 46 37 15 01 94 f5 21 \n" +
                "3e 81 03 17 05 7d fd ef 33 b6 2c 55 66 6a c2 36 \n" +
                "a2 5b 7f 12 78 0a fb e1 a1 8f a1 7c 2a c9 19 cc \n" +
                "bc 39 a5 e5 27 b6 e2 1c 2f 8e af fc 71 17 09 1b \n" +
                "1d 26 e5 ac ff 27 09 d7 20 16 43 42 8e 9e 4c f5 \n" +
                "bb 2e 4f 57 bc 41 79 a1 93 d5 ca f5 23 36 56 82 \n" +
                "7d 02 b5 0b af ef 35 d1 5a d0 f3 8a 92 8b 83 55 \n" +
                "8d 0e e4 77 78 45 5a 57 01 19 a5 e5 d3 46 1b 06 \n" +
                "e8 4a 18 ce 63 6e e9 4d 90 19 a1 6a 3e 86 d2 60 \n" +
                "4f 45 84 56 a5 fd f4 b4 ef 55 cf 6e d3 08 40 a6 \n" +
                "08 c7 2d e1 fe bd 25 0c 13 0f 08 4a ff d9 3d 92 \n" +
                "e5 db 9d 22 53 5d e2 8a c5 65 74 14 f2 ff 81 54 \n" +
                "04 2b bd 49 ee 02 ed 5c 64 35 f3 bb aa 50 8d a6 \n" +
                "a9 5b ae 4b 93 82 ce a5 87 c2 65 4c c3 ab 05 f0 \n" +
                "3d 4d 75 b7 ad d4 68 de b2 94 88 07 ce 31 ac 20 \n" +
                "0e 97 41 e1 d4 89 af b9 e5 92 3c ce 56 e3 f5 c6 \n" +
                "c8 f5 4f b7 94 db cc 30 f0 30 f1 49 4a 15 03 6d \n" +
                "bc 64 85 6e 95 82 14 c1 5b 6c d7 43 81 f3 da b0 \n" +
                "e1 6b a5 e3 59 5d 4e 41 38 7d d5 9a aa cb ef 12 \n" +
                "8d 97 88 90 03 d3 92 91 90 96 7b 02 00 6a cc c3 \n" +
                "01 f9 c1 fe 2c 8d a7 49 0b c3 7f 16 77 45 4b 9f \n" +
                "3d 5d f5 96 f6 94 3e f1 65 0b 33 90 f9 f0 6a ef \n" +
                "c9 2b 9c 8c 92 53 e2 c9 26 30 6e fc fa b7 df 70 \n" +
                "38 ee b9 02 16 5e d5 69 97 25 a1 ae a4 19 9f f8 \n" +
                "93 31 85 7e 2b 78 29 b7 a0 fb f3 b9 15 71 59 ca \n" +
                "e1 b5 6d 61 b9 df 8d 93 35 6d 25 ff ba 80 e1 b9 \n" +
                "d0 32 b3 4a 08 7f 4f 1f 83 49 f3 61 59 59 18 7d \n" +
                "40 b2 f8 97 44 61 fa d9 a3 9f d5 d6 41 64 ee ef \n" +
                "22 3d f7 ba b6 47 a2 65 8f 50 7b d1 83 59 c0 51 \n" +
                "0e 38 06 d5 03 17 d2 87 0c 38 bd 72 b3 1b 16 33 \n" +
                "ca 41 91 d7 1f 73 bd 4a 88 d4 f7 58 1f 92 24 a5 \n" +
                "80 51 ea 97 3c c9 67 3e 42 1d 70 4f 58 f2 e3 1b \n" +
                "49 e0 67 12 c4 35 37 3e 22 cc 3a 90 2d ea 23 4c \n" +
                "39 57 8f fb e8 6e 9c df bb c3 38 ae b5 e5 e5 ee \n" +
                "18 d0 b6 c5 c7 e0 a6 ad 5c 9e f7 59 ce 53 71 eb \n" +
                "fe 09 c2 3b 39 fc d2 8d f8 bb 5c 37 c9 61 e3 94 \n" +
                "3a de 42 85 b7 7b ca e8 c9 06 29 f8 e1 41 12 d5 \n" +
                "1a 63 24 75 66 ee cf a2 bf 22 ae 55 33 49 4c 43 \n" +
                "fa a1 1c a1 5b cb fb 0a 4d 07 cd d8 c2 16 e1 35 \n" +
                "23 af a8 96 c6 a7 8d fc f6 c3 1b 85 70 2e ae 56 \n" +
                "d4 6a af 1a 96 eb cd ac 25 5d c4 a3 00 fd 1b 8c \n" +
                "29 75 75 68 eb d6 2d 15 45 98 90 75 31 24 ac ec \n" +
                "87 70 34 ee 23 eb 15 3c 54 dd 8f ff 34 74 81 ae \n" +
                "ea c6 19 45 6c 9a 88 0f 43 b0 f9 9c 4a 13 cc 6f \n" +
                "56 f1 f5 b7 c9 16 47 1d 54 f5 99 95 ba ac 5e c4 \n" +
                "02 7e fd a2 66 76 92 fe 5b 06 63 cb b9 29 14 f2 \n" +
                "2c 70 58 36 89 a8 84 af 60 aa 41 a0 21 84 d0 43 \n" +
                "b7 1b f5 eb 3a 43 8f f9 c2 ec 6b 76 94 5b 7f e9 \n" +
                "17 e7 7f 9d c8 19 28 c6 fe a1 7f 84 49 31 45 68 \n" +
                "2f cc fc 0e a1 85 ef 79 e7 0f f5 65 b6 65 45 59 \n" +
                "af 5b 3b 27 0f bf e6 5b 75 63 35 c5 03 e6 51 57 \n" +
                "81 48 00 f9 b5 4f dd 0b 1e 41 45 ef 90 c4 4e 26 \n" +
                "b4 15 c8 fc e2 60 be b9 a7 2f e5 73 06 0c d7 56 \n" +
                "f2 e8 fc 70 64 e0 2e be 62 df a9 14 a1 ab 3d f8 \n" +
                "dc 93 d8 32 77 d7 38 1d 6e ae ce 29 92 d9 90 9b \n" +
                "cb cd 6c 5c 57 ce 91 05 16 72 64 5b 6a 9a 54 3b \n" +
                "eb 42 8c b8 6e 6d ac 74 5c b0 c0 4c b6 f8 61 f1 \n" +
                "a2 c5 aa b3 ed 6b e9 76 e1 9a c1 b8 70 43 d3 b9 \n" +
                "8a 5e 0a c2 a6 f1 39 7d d9 fe 46 a0 17 56 f1 49 \n" +
                "5d ed f9 2e 8e a1 56 4c fc a9 39 7c 95 a6 77 e2 \n" +
                "e0 7c db ea a2 be ff 14 f7 04 ea 23 cf 6a 8a 02 \n" +
                "18 bf 40 ce 2d de e4 b0 34 90 d4 c3 c3 15 bf 7b \n" +
                "e7 03 de f7 5a d8 8f 27 5c 5f b8 37 c1 f9 54 22 \n" +
                "39 52 e7 e9 78 a3 df ce 98 96 7a a4 ea 8b 62 28 \n" +
                "00 25 3b 2c ce 1a 68 ae 46 1d 6c a1 f2 9c 16 29 \n" +
                "91 05 18 3e ab fb a3 d4 fd d0 ed e0 54 5e c1 13 \n" +
                "65 2b 59 ee 4f 6f bc 37 75 ff ab 1b 63 5e 7c fa \n" +
                "f7 3b 91 46 ae 0c f5 5f f8 5f 43 5a 2c f4 82 b5 \n" +
                "1d 10 ae 59 92 f9 fc 61 86 06 ec d1 43 18 c2 8e \n" +
                "be fd 9a 41 2f 81 58 d4 c9 8a 49 43 2a 81 f5 d9 \n" +
                "92 e9 b2 dd 23 5d 94 fb 7b 29 b3 7b cc 49 57 8d \n" +
                "26 63 1d 15 9d f7 12 87 df 7d c4 4c 0d fd 29 81 \n" +
                "79 0b e0 9a c0 0b 27 93 ca d1 02 d0 ba 37 e1 67 \n" +
                "53 dd 6c 1d b5 bd 99 11 c5 1a 84 06 6a 08 82 c8 \n" +
                "fc 2b 92 fb b7 c3 dc 4a 4a eb 29 04 b4 42 1c 8f \n" +
                "4d a2 39 41 a7 d3 d6 ad c0 71 f3 ba c3 ad 00 0b \n" +
                "a5 c7 60 6a 1a 5d 87 a3 80 5c 08 97 7a 04 4c 13 \n" +
                "f5 6b 0c a4 90 78 fa 1d b6 d5 df 4c c3 27 a4 a9 \n" +
                "ae a3 a8 db 37 cd 0e b0 ba a3 6f 7d 7f 40 b4 fc \n" +
                "c3 35 59 ef 4e b7 dc 27 32 79 0a 30 e4 a4 58 f2 \n" +
                "47 d7 f8 38 24 d1 41 41 53 de c8 ff f2 65 ff 1a \n" +
                "f7 88 17 f3 59 84 4d 2d 5b ad 7d 3e d6 c2 c2 e7 \n" +
                "5f 4e f5 d5 da 56 e5 1c a4 30 6a f7 60 5d 51 ff \n" +
                "4e ec 0d f7 60 5a 69 53 0e 63 d7 f8 af b7 e2 81 \n" +
                "37 99 0a 4f 1f a5 9b fc ff 6a 11 dc 2b 1f f2 4e \n" +
                "06 e2 2d 53 b7 0f 65 9a 9c 6a 52 e1 7c 13 af 56 \n" +
                "7b 50 f4 16 37 f2 67 12 a4 54 11 48 3a cd 9f bb \n" +
                "d6 4f eb da d4 1c b9 47 db 2c 7e 3b 85 e3 77 03 \n" +
                "aa 12 e8 af 4f 5f df e5 da 5a c9 53 14 2c 86 ed \n" +
                "02 5e 9c e3 77 f6 bf 18 ad 25 51 47 75 73 72 85 \n" +
                "c6 0a 45 bf 2a 14 2c d8 07 5d 9f e6 e4 e2 6f 2c \n" +
                "5c e0 84 4f 50 16 6c 23 75 87 63 d9 2f 43 22 ef \n" +
                "31 2a c1 f9 be ce c3 54 64 5d 3c d7 71 06 9d 5f \n" +
                "85 b0 c2 26 87 dc 3f 33 f9 e5 5e 6c d3 7b 07 cd \n" +
                "41 35 80 ab 5c 6a 97 ec a1 c6 60 23 6d 3d c4 c3 \n" +
                "f2 ea b2 59 fd 5b 38 b8 9d aa 24 bf 32 b0 fd 3f \n" +
                "e6 c3 94 29 05 98 5c f7 39");
        byte[] ciphertext = response;
        int ciphertextOffset = 5;
        int ciphertextLength = ciphertext.length - ciphertextOffset;
        byte[] nonce = new byte[decryptNonce.length + record_iv_length];
        System.arraycopy(decryptNonce, 0, nonce, 0, decryptNonce.length);
        System.arraycopy(ciphertext, ciphertextOffset, nonce, nonce.length - record_iv_length, record_iv_length);
        decryptCipher.init(nonce, macSize);

        int encryptionOffset = ciphertextOffset + record_iv_length;
        int encryptionLength = ciphertextLength - record_iv_length;
        int innerPlaintextLength = decryptCipher.getOutputSize(encryptionLength);

        // byte[] additionalData = getAdditionalData(seqNo, recordType, recordVersion, ciphertextLength,innerPlaintextLength, decryptConnectionID);
        byte[] additionalData = hexStringToByteArray("00 00 00 00 00 00 00 01 17 03 03 09 ec ");
        additionalData = additionalData(1L,(short)0x17, ProtocolVersion.TLSv12, 0, innerPlaintextLength, null);
        byte[] target = new byte[ciphertext.length];
        decryptCipher.doFinal(additionalData, ciphertext, encryptionOffset, encryptionLength, ciphertext, encryptionOffset);
        System.out.println();
    }

    @Test
    public void testEncodeCipherForFinished() throws IOException {
        TlsAEADCipher tlsAEADCipher = testCreateConstructor();
        TlsSecret masterSecret = tlsAEADCipher.masterSecret;
        TlsAEADCipherImpl encryptCipher = tlsAEADCipher.encryptCipher;
        byte[] encryptNonce = tlsAEADCipher.encryptNonce;
        int record_iv_length = tlsAEADCipher.record_iv_length;
        int macSize = tlsAEADCipher.macSize;

        String asciiLabel = ExporterLabel.client_finished;
        byte[] seed = testHash2ed();
        byte[] verify_data = masterSecret.deriveUsingPRF(PRFAlgorithm.tls_prf_sha256, asciiLabel, seed, 12).extract();

        byte[] assertResult = hexStringToByteArray("16 03 03 00 28 00 00 00 00 00 00 00 00 04 c1 4c \n" +
                "a1 7a c3 ed ad 20 45 0a 2c 32 08 b2 db 6c 79 76 \n" +
                "d8 5b 3d ae 76 ff 1e 28 6e 4e 12 56 8e ");

        byte[] plaintext = concat(hexStringToByteArray("14 00 00 0c"), verify_data);
        int plaintextOffset = 0;
        int plaintextLength= plaintext.length;
        boolean encryptUseInnerPlaintext = false;
        int headerAllocation = 5;

        byte[] nonce = new byte[encryptNonce.length + record_iv_length];
        System.arraycopy(encryptNonce, 0, nonce, 0, encryptNonce.length);
        // RFC 5288/6655: The nonce_explicit MAY be the 64-bit sequence number.
        TlsUtils.writeUint64(0, nonce, encryptNonce.length);

        int innerPlaintextLength = plaintextLength + (encryptUseInnerPlaintext ? 1 : 0);

        encryptCipher.init(nonce, macSize);
        int encryptionLength = encryptCipher.getOutputSize(innerPlaintextLength);
        int ciphertextLength = record_iv_length + encryptionLength;

        byte[] output = new byte[headerAllocation + ciphertextLength];
        int outputPos = headerAllocation;

        System.arraycopy(nonce, nonce.length - record_iv_length, output, outputPos, record_iv_length);
        outputPos += record_iv_length;

        byte[] additionalData = additionalData(0L,(short)0x16, ProtocolVersion.TLSv12, ciphertextLength, innerPlaintextLength, null);
        System.arraycopy(plaintext, plaintextOffset, output, outputPos, plaintextLength);

        encryptCipher.doFinal(additionalData, output, outputPos, innerPlaintextLength, output, outputPos);
        TlsUtils.writeUint8(ContentType.handshake, output, RecordFormat.TYPE_OFFSET);
        TlsUtils.writeVersion(ProtocolVersion.TLSv12, output, RecordFormat.VERSION_OFFSET);
        TlsUtils.writeUint16(ciphertextLength, output, RecordFormat.LENGTH_OFFSET);
        System.out.println();
    }

    @Test
    public void testHash(){

        char[] clientHello = {
                0x01, 0x00, 0x00, 0xf9, 0x03, 0x03, 0x5f, 0x65,
                0x76, 0xa5, 0x75, 0xc2, 0x97, 0x45, 0x8e, 0xe7,
                0x67, 0xdc, 0x49, 0xc8, 0x5c, 0x59, 0x77, 0xf9,
                0x12, 0xd3, 0xfe, 0xfb, 0xbd, 0x71, 0x8a, 0xb3,
                0xa1, 0x79, 0x2f, 0x85, 0xfa, 0xe9, 0x20, 0xdb,
                0x28, 0x60, 0x17, 0x30, 0x86, 0xf1, 0x6a, 0x1f,
                0x6b, 0xf5, 0x78, 0x4a, 0xf1, 0x6c, 0xf3, 0x0c,
                0x35, 0x61, 0xeb, 0x7c, 0x4d, 0x12, 0x46, 0x67,
                0xb8, 0x8d, 0xb8, 0xff, 0x75, 0xac, 0xbe, 0x00,
                0x1e, 0x13, 0x03, 0x13, 0x01, 0xcc, 0xa9, 0xc0,
                0x2b, 0xc0, 0x23, 0xc0, 0x09, 0xcc, 0xa8, 0xc0,
                0x2f, 0xc0, 0x27, 0xc0, 0x13, 0xcc, 0xaa, 0x00,
                0x9e, 0x00, 0x67, 0x00, 0x33, 0x00, 0xff, 0x01,
                0x00, 0x00, 0x92, 0x00, 0x17, 0x00, 0x00, 0x00,
                0x16, 0x00, 0x00, 0x00, 0x2b, 0x00, 0x05, 0x04,
                0x03, 0x04, 0x03, 0x03, 0x00, 0x0a, 0x00, 0x10,
                0x00, 0x0e, 0x00, 0x1d, 0x00, 0x1e, 0x00, 0x17,
                0x00, 0x18, 0x01, 0x00, 0x01, 0x01, 0x01, 0x02,
                0x00, 0x33, 0x00, 0x26, 0x00, 0x24, 0x00, 0x1d,
                0x00, 0x20, 0xde, 0xe8, 0x76, 0x45, 0xeb, 0x3b,
                0xce, 0x4a, 0xe4, 0x94, 0xf9, 0xa2, 0x3f, 0xcc,
                0x53, 0xaf, 0x6a, 0x56, 0x5e, 0x93, 0xf8, 0xc6,
                0xe1, 0x5d, 0xd8, 0xe1, 0x7f, 0x66, 0xa4, 0x93,
                0x58, 0x19, 0x00, 0x05, 0x00, 0x05, 0x01, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x0d, 0x00, 0x30, 0x00,
                0x2e, 0x08, 0x07, 0x08, 0x08, 0x04, 0x03, 0x05,
                0x03, 0x06, 0x03, 0x08, 0x04, 0x08, 0x05, 0x08,
                0x06, 0x08, 0x09, 0x08, 0x0a, 0x08, 0x0b, 0x04,
                0x01, 0x05, 0x01, 0x06, 0x01, 0x04, 0x02, 0x05,
                0x02, 0x06, 0x02, 0x03, 0x03, 0x03, 0x01, 0x03,
                0x02, 0x02, 0x03, 0x02, 0x01, 0x02, 0x02, 0x00,
                0x0b, 0x00, 0x02, 0x01, 0x00
        };

        char[] serverHello = {
                0x02, 0x00, 0x00, 0x55, 0x03, 0x03, 0x11, 0xdf,
                0x63, 0x0c, 0xcf, 0x6b, 0x75, 0x88, 0xef, 0x7d,
                0x21, 0xd2, 0x7b, 0xdf, 0xd3, 0x45, 0x4f, 0xed,
                0xd0, 0xd5, 0x49, 0x27, 0xff, 0x06, 0x27, 0xcf,
                0x00, 0xa8, 0x82, 0x9b, 0xfe, 0x93, 0x20, 0xab,
                0x84, 0x61, 0x7e, 0x65, 0xa3, 0x00, 0x7e, 0xb9,
                0xb4, 0x92, 0xc2, 0x79, 0x40, 0x23, 0x6e, 0xb9,
                0xf0, 0x79, 0x08, 0x28, 0x67, 0x14, 0x47, 0xac,
                0xda, 0xa5, 0x0f, 0x97, 0x94, 0xa8, 0x60, 0xc0,
                0x2b, 0x00, 0x00, 0x0d, 0xff, 0x01, 0x00, 0x01,
                0x00, 0x00, 0x0b, 0x00, 0x04, 0x03, 0x00, 0x01,
                0x02
        };

        char[] certificate = {
                0x0b, 0x00, 0x0b, 0x73, 0x00, 0x0b, 0x70, 0x00,
                0x04, 0x07, 0x30, 0x82, 0x04, 0x03, 0x30, 0x82,
                0x03, 0x88, 0xa0, 0x03, 0x02, 0x01, 0x02, 0x02,
                0x10, 0x4f, 0xc9, 0x72, 0x15, 0xbf, 0x60, 0x1b,
                0x22, 0x25, 0x62, 0x0a, 0x35, 0x37, 0xa8, 0x57,
                0x73, 0x30, 0x0a, 0x06, 0x08, 0x2a, 0x86, 0x48,
                0xce, 0x3d, 0x04, 0x03, 0x03, 0x30, 0x4b, 0x31,
                0x0b, 0x30, 0x09, 0x06, 0x03, 0x55, 0x04, 0x06,
                0x13, 0x02, 0x41, 0x54, 0x31, 0x10, 0x30, 0x0e,
                0x06, 0x03, 0x55, 0x04, 0x0a, 0x13, 0x07, 0x5a,
                0x65, 0x72, 0x6f, 0x53, 0x53, 0x4c, 0x31, 0x2a,
                0x30, 0x28, 0x06, 0x03, 0x55, 0x04, 0x03, 0x13,
                0x21, 0x5a, 0x65, 0x72, 0x6f, 0x53, 0x53, 0x4c,
                0x20, 0x45, 0x43, 0x43, 0x20, 0x44, 0x6f, 0x6d,
                0x61, 0x69, 0x6e, 0x20, 0x53, 0x65, 0x63, 0x75,
                0x72, 0x65, 0x20, 0x53, 0x69, 0x74, 0x65, 0x20,
                0x43, 0x41, 0x30, 0x1e, 0x17, 0x0d, 0x32, 0x34,
                0x31, 0x32, 0x32, 0x32, 0x30, 0x30, 0x30, 0x30,
                0x30, 0x30, 0x5a, 0x17, 0x0d, 0x32, 0x35, 0x30,
                0x33, 0x32, 0x32, 0x32, 0x33, 0x35, 0x39, 0x35,
                0x39, 0x5a, 0x30, 0x14, 0x31, 0x12, 0x30, 0x10,
                0x06, 0x03, 0x55, 0x04, 0x03, 0x13, 0x09, 0x77,
                0x74, 0x66, 0x75, 0x2e, 0x73, 0x69, 0x74, 0x65,
                0x30, 0x59, 0x30, 0x13, 0x06, 0x07, 0x2a, 0x86,
                0x48, 0xce, 0x3d, 0x02, 0x01, 0x06, 0x08, 0x2a,
                0x86, 0x48, 0xce, 0x3d, 0x03, 0x01, 0x07, 0x03,
                0x42, 0x00, 0x04, 0x4a, 0xcd, 0xf6, 0x13, 0x8a,
                0xa1, 0x45, 0x4b, 0xa1, 0xb2, 0x29, 0x2f, 0x8c,
                0x31, 0x06, 0x2a, 0x7f, 0x03, 0x7f, 0xe0, 0xcd,
                0xbe, 0x38, 0x9b, 0xc3, 0x85, 0x02, 0x0e, 0x08,
                0x13, 0xfb, 0x14, 0x31, 0x8c, 0x52, 0x9c, 0x12,
                0x31, 0x76, 0xdd, 0xbe, 0xbf, 0x92, 0x7a, 0x7d,
                0x66, 0x66, 0x79, 0xdf, 0x2e, 0x90, 0x18, 0x2b,
                0x58, 0x62, 0x67, 0x51, 0x57, 0x82, 0x5a, 0x6c,
                0xeb, 0x11, 0x56, 0xa3, 0x82, 0x02, 0x83, 0x30,
                0x82, 0x02, 0x7f, 0x30, 0x1f, 0x06, 0x03, 0x55,
                0x1d, 0x23, 0x04, 0x18, 0x30, 0x16, 0x80, 0x14,
                0x0f, 0x6b, 0xe6, 0x4b, 0xce, 0x39, 0x47, 0xae,
                0xf6, 0x7e, 0x90, 0x1e, 0x79, 0xf0, 0x30, 0x91,
                0x92, 0xc8, 0x5f, 0xa3, 0x30, 0x1d, 0x06, 0x03,
                0x55, 0x1d, 0x0e, 0x04, 0x16, 0x04, 0x14, 0xee,
                0xf5, 0xa9, 0xf7, 0x35, 0xa7, 0x22, 0x36, 0x8c,
                0xb5, 0x58, 0xf4, 0x03, 0x78, 0x2e, 0x98, 0xbd,
                0xe6, 0xfe, 0xc2, 0x30, 0x0e, 0x06, 0x03, 0x55,
                0x1d, 0x0f, 0x01, 0x01, 0xff, 0x04, 0x04, 0x03,
                0x02, 0x07, 0x80, 0x30, 0x0c, 0x06, 0x03, 0x55,
                0x1d, 0x13, 0x01, 0x01, 0xff, 0x04, 0x02, 0x30,
                0x00, 0x30, 0x1d, 0x06, 0x03, 0x55, 0x1d, 0x25,
                0x04, 0x16, 0x30, 0x14, 0x06, 0x08, 0x2b, 0x06,
                0x01, 0x05, 0x05, 0x07, 0x03, 0x01, 0x06, 0x08,
                0x2b, 0x06, 0x01, 0x05, 0x05, 0x07, 0x03, 0x02,
                0x30, 0x49, 0x06, 0x03, 0x55, 0x1d, 0x20, 0x04,
                0x42, 0x30, 0x40, 0x30, 0x34, 0x06, 0x0b, 0x2b,
                0x06, 0x01, 0x04, 0x01, 0xb2, 0x31, 0x01, 0x02,
                0x02, 0x4e, 0x30, 0x25, 0x30, 0x23, 0x06, 0x08,
                0x2b, 0x06, 0x01, 0x05, 0x05, 0x07, 0x02, 0x01,
                0x16, 0x17, 0x68, 0x74, 0x74, 0x70, 0x73, 0x3a,
                0x2f, 0x2f, 0x73, 0x65, 0x63, 0x74, 0x69, 0x67,
                0x6f, 0x2e, 0x63, 0x6f, 0x6d, 0x2f, 0x43, 0x50,
                0x53, 0x30, 0x08, 0x06, 0x06, 0x67, 0x81, 0x0c,
                0x01, 0x02, 0x01, 0x30, 0x81, 0x88, 0x06, 0x08,
                0x2b, 0x06, 0x01, 0x05, 0x05, 0x07, 0x01, 0x01,
                0x04, 0x7c, 0x30, 0x7a, 0x30, 0x4b, 0x06, 0x08,
                0x2b, 0x06, 0x01, 0x05, 0x05, 0x07, 0x30, 0x02,
                0x86, 0x3f, 0x68, 0x74, 0x74, 0x70, 0x3a, 0x2f,
                0x2f, 0x7a, 0x65, 0x72, 0x6f, 0x73, 0x73, 0x6c,
                0x2e, 0x63, 0x72, 0x74, 0x2e, 0x73, 0x65, 0x63,
                0x74, 0x69, 0x67, 0x6f, 0x2e, 0x63, 0x6f, 0x6d,
                0x2f, 0x5a, 0x65, 0x72, 0x6f, 0x53, 0x53, 0x4c,
                0x45, 0x43, 0x43, 0x44, 0x6f, 0x6d, 0x61, 0x69,
                0x6e, 0x53, 0x65, 0x63, 0x75, 0x72, 0x65, 0x53,
                0x69, 0x74, 0x65, 0x43, 0x41, 0x2e, 0x63, 0x72,
                0x74, 0x30, 0x2b, 0x06, 0x08, 0x2b, 0x06, 0x01,
                0x05, 0x05, 0x07, 0x30, 0x01, 0x86, 0x1f, 0x68,
                0x74, 0x74, 0x70, 0x3a, 0x2f, 0x2f, 0x7a, 0x65,
                0x72, 0x6f, 0x73, 0x73, 0x6c, 0x2e, 0x6f, 0x63,
                0x73, 0x70, 0x2e, 0x73, 0x65, 0x63, 0x74, 0x69,
                0x67, 0x6f, 0x2e, 0x63, 0x6f, 0x6d, 0x30, 0x82,
                0x01, 0x05, 0x06, 0x0a, 0x2b, 0x06, 0x01, 0x04,
                0x01, 0xd6, 0x79, 0x02, 0x04, 0x02, 0x04, 0x81,
                0xf6, 0x04, 0x81, 0xf3, 0x00, 0xf1, 0x00, 0x77,
                0x00, 0xcf, 0x11, 0x56, 0xee, 0xd5, 0x2e, 0x7c,
                0xaf, 0xf3, 0x87, 0x5b, 0xd9, 0x69, 0x2e, 0x9b,
                0xe9, 0x1a, 0x71, 0x67, 0x4a, 0xb0, 0x17, 0xec,
                0xac, 0x01, 0xd2, 0x5b, 0x77, 0xce, 0xcc, 0x3b,
                0x08, 0x00, 0x00, 0x01, 0x93, 0xec, 0xbe, 0x8a,
                0xc5, 0x00, 0x00, 0x04, 0x03, 0x00, 0x48, 0x30,
                0x46, 0x02, 0x21, 0x00, 0xba, 0x14, 0x89, 0x4c,
                0xd4, 0xa6, 0xbf, 0xd5, 0x8e, 0x18, 0x3b, 0x10,
                0x63, 0x54, 0x35, 0x3e, 0x16, 0x3a, 0xf1, 0xb0,
                0x1d, 0xe3, 0x49, 0x20, 0xa0, 0x7f, 0x36, 0xe0,
                0x44, 0x30, 0xe7, 0xe1, 0x02, 0x21, 0x00, 0xa8,
                0xdc, 0x88, 0xb6, 0x51, 0x32, 0x6b, 0x67, 0x1e,
                0x60, 0xd9, 0x3a, 0x8b, 0x2e, 0xf1, 0xf9, 0x1c,
                0xf1, 0xff, 0x18, 0x8d, 0xc5, 0x3f, 0xf7, 0x9b,
                0xcc, 0x4b, 0xd4, 0x28, 0xdb, 0x5c, 0x13, 0x00,
                0x76, 0x00, 0xcc, 0xfb, 0x0f, 0x6a, 0x85, 0x71,
                0x09, 0x65, 0xfe, 0x95, 0x9b, 0x53, 0xce, 0xe9,
                0xb2, 0x7c, 0x22, 0xe9, 0x85, 0x5c, 0x0d, 0x97,
                0x8d, 0xb6, 0xa9, 0x7e, 0x54, 0xc0, 0xfe, 0x4c,
                0x0d, 0xb0, 0x00, 0x00, 0x01, 0x93, 0xec, 0xbe,
                0x8a, 0x8f, 0x00, 0x00, 0x04, 0x03, 0x00, 0x47,
                0x30, 0x45, 0x02, 0x20, 0x1b, 0xff, 0x93, 0x39,
                0x6b, 0xc9, 0x87, 0xb7, 0x00, 0xd6, 0x45, 0x63,
                0x66, 0x92, 0xb3, 0x60, 0xa0, 0x4f, 0x85, 0x86,
                0xd5, 0x1a, 0x7a, 0x4d, 0x4d, 0x40, 0x30, 0xd8,
                0x9e, 0x7a, 0x21, 0xdf, 0x02, 0x21, 0x00, 0xfa,
                0xb6, 0xae, 0x47, 0xeb, 0x9f, 0xc3, 0xa7, 0xf7,
                0xe1, 0x87, 0x3c, 0x28, 0xaa, 0xa3, 0x47, 0x88,
                0x81, 0xaf, 0x36, 0xff, 0x1d, 0x16, 0x3e, 0xf1,
                0x3a, 0x6c, 0x5f, 0x90, 0xcb, 0xe3, 0xe1, 0x30,
                0x21, 0x06, 0x03, 0x55, 0x1d, 0x11, 0x04, 0x1a,
                0x30, 0x18, 0x82, 0x09, 0x77, 0x74, 0x66, 0x75,
                0x2e, 0x73, 0x69, 0x74, 0x65, 0x82, 0x0b, 0x2a,
                0x2e, 0x77, 0x74, 0x66, 0x75, 0x2e, 0x73, 0x69,
                0x74, 0x65, 0x30, 0x0a, 0x06, 0x08, 0x2a, 0x86,
                0x48, 0xce, 0x3d, 0x04, 0x03, 0x03, 0x03, 0x69,
                0x00, 0x30, 0x66, 0x02, 0x31, 0x00, 0xf9, 0x31,
                0xc9, 0x8c, 0x2e, 0x6b, 0xf5, 0xca, 0xd4, 0xad,
                0xdd, 0x19, 0x87, 0x27, 0x1f, 0xd8, 0xbd, 0x32,
                0xea, 0x6c, 0x99, 0x6c, 0x2e, 0xd6, 0xc7, 0x87,
                0x1c, 0x02, 0xf1, 0x33, 0x40, 0x68, 0x3a, 0x1a,
                0x03, 0xcd, 0x65, 0x10, 0xae, 0x68, 0x63, 0x85,
                0x2e, 0x30, 0x80, 0x22, 0xda, 0x47, 0x02, 0x31,
                0x00, 0xb6, 0x93, 0x15, 0x5e, 0x96, 0x75, 0xce,
                0x12, 0x4e, 0xc0, 0xc7, 0x14, 0x79, 0x2b, 0x0a,
                0x63, 0xed, 0x00, 0x42, 0x3a, 0xa3, 0x82, 0x0a,
                0x68, 0x29, 0xd2, 0x90, 0x2a, 0xa8, 0x59, 0xc7,
                0x56, 0xdf, 0x33, 0x53, 0x6f, 0x59, 0x0e, 0x30,
                0xec, 0xa0, 0x81, 0x42, 0x65, 0x83, 0xa4, 0x09,
                0x68, 0x00, 0x03, 0x89, 0x30, 0x82, 0x03, 0x85,
                0x30, 0x82, 0x03, 0x0c, 0xa0, 0x03, 0x02, 0x01,
                0x02, 0x02, 0x10, 0x23, 0xb7, 0x6d, 0xe3, 0xc1,
                0xbb, 0x2b, 0x1a, 0x51, 0x96, 0x1e, 0x08, 0xea,
                0xb7, 0x64, 0xe8, 0x30, 0x0a, 0x06, 0x08, 0x2a,
                0x86, 0x48, 0xce, 0x3d, 0x04, 0x03, 0x03, 0x30,
                0x81, 0x88, 0x31, 0x0b, 0x30, 0x09, 0x06, 0x03,
                0x55, 0x04, 0x06, 0x13, 0x02, 0x55, 0x53, 0x31,
                0x13, 0x30, 0x11, 0x06, 0x03, 0x55, 0x04, 0x08,
                0x13, 0x0a, 0x4e, 0x65, 0x77, 0x20, 0x4a, 0x65,
                0x72, 0x73, 0x65, 0x79, 0x31, 0x14, 0x30, 0x12,
                0x06, 0x03, 0x55, 0x04, 0x07, 0x13, 0x0b, 0x4a,
                0x65, 0x72, 0x73, 0x65, 0x79, 0x20, 0x43, 0x69,
                0x74, 0x79, 0x31, 0x1e, 0x30, 0x1c, 0x06, 0x03,
                0x55, 0x04, 0x0a, 0x13, 0x15, 0x54, 0x68, 0x65,
                0x20, 0x55, 0x53, 0x45, 0x52, 0x54, 0x52, 0x55,
                0x53, 0x54, 0x20, 0x4e, 0x65, 0x74, 0x77, 0x6f,
                0x72, 0x6b, 0x31, 0x2e, 0x30, 0x2c, 0x06, 0x03,
                0x55, 0x04, 0x03, 0x13, 0x25, 0x55, 0x53, 0x45,
                0x52, 0x54, 0x72, 0x75, 0x73, 0x74, 0x20, 0x45,
                0x43, 0x43, 0x20, 0x43, 0x65, 0x72, 0x74, 0x69,
                0x66, 0x69, 0x63, 0x61, 0x74, 0x69, 0x6f, 0x6e,
                0x20, 0x41, 0x75, 0x74, 0x68, 0x6f, 0x72, 0x69,
                0x74, 0x79, 0x30, 0x1e, 0x17, 0x0d, 0x32, 0x30,
                0x30, 0x31, 0x33, 0x30, 0x30, 0x30, 0x30, 0x30,
                0x30, 0x30, 0x5a, 0x17, 0x0d, 0x33, 0x30, 0x30,
                0x31, 0x32, 0x39, 0x32, 0x33, 0x35, 0x39, 0x35,
                0x39, 0x5a, 0x30, 0x4b, 0x31, 0x0b, 0x30, 0x09,
                0x06, 0x03, 0x55, 0x04, 0x06, 0x13, 0x02, 0x41,
                0x54, 0x31, 0x10, 0x30, 0x0e, 0x06, 0x03, 0x55,
                0x04, 0x0a, 0x13, 0x07, 0x5a, 0x65, 0x72, 0x6f,
                0x53, 0x53, 0x4c, 0x31, 0x2a, 0x30, 0x28, 0x06,
                0x03, 0x55, 0x04, 0x03, 0x13, 0x21, 0x5a, 0x65,
                0x72, 0x6f, 0x53, 0x53, 0x4c, 0x20, 0x45, 0x43,
                0x43, 0x20, 0x44, 0x6f, 0x6d, 0x61, 0x69, 0x6e,
                0x20, 0x53, 0x65, 0x63, 0x75, 0x72, 0x65, 0x20,
                0x53, 0x69, 0x74, 0x65, 0x20, 0x43, 0x41, 0x30,
                0x76, 0x30, 0x10, 0x06, 0x07, 0x2a, 0x86, 0x48,
                0xce, 0x3d, 0x02, 0x01, 0x06, 0x05, 0x2b, 0x81,
                0x04, 0x00, 0x22, 0x03, 0x62, 0x00, 0x04, 0x36,
                0x41, 0x61, 0x17, 0x2b, 0x53, 0x25, 0xed, 0xaa,
                0xca, 0x94, 0xe4, 0xd6, 0xda, 0x48, 0x57, 0xef,
                0x50, 0xba, 0x84, 0x64, 0x82, 0xd7, 0xbb, 0x05,
                0x1b, 0xd6, 0x1f, 0x06, 0x24, 0xf6, 0xa5, 0x33,
                0x9d, 0x8c, 0xe7, 0xf1, 0x0b, 0x55, 0x68, 0x63,
                0x82, 0x30, 0x10, 0x5f, 0x8d, 0x65, 0xec, 0xaa,
                0xa8, 0xaf, 0x97, 0xca, 0xb5, 0x86, 0xce, 0x30,
                0x01, 0x89, 0x74, 0xde, 0xe3, 0x4e, 0x5e, 0x01,
                0x6e, 0xee, 0x26, 0x7b, 0xcc, 0x53, 0xfa, 0x23,
                0xa4, 0xf7, 0x44, 0x1d, 0x3e, 0x4d, 0x1e, 0x5f,
                0x66, 0xa6, 0xad, 0x85, 0xf6, 0xf2, 0xe3, 0xbc,
                0x8e, 0x09, 0x98, 0x80, 0x24, 0x8e, 0x20, 0xa3,
                0x82, 0x01, 0x75, 0x30, 0x82, 0x01, 0x71, 0x30,
                0x1f, 0x06, 0x03, 0x55, 0x1d, 0x23, 0x04, 0x18,
                0x30, 0x16, 0x80, 0x14, 0x3a, 0xe1, 0x09, 0x86,
                0xd4, 0xcf, 0x19, 0xc2, 0x96, 0x76, 0x74, 0x49,
                0x76, 0xdc, 0xe0, 0x35, 0xc6, 0x63, 0x63, 0x9a,
                0x30, 0x1d, 0x06, 0x03, 0x55, 0x1d, 0x0e, 0x04,
                0x16, 0x04, 0x14, 0x0f, 0x6b, 0xe6, 0x4b, 0xce,
                0x39, 0x47, 0xae, 0xf6, 0x7e, 0x90, 0x1e, 0x79,
                0xf0, 0x30, 0x91, 0x92, 0xc8, 0x5f, 0xa3, 0x30,
                0x0e, 0x06, 0x03, 0x55, 0x1d, 0x0f, 0x01, 0x01,
                0xff, 0x04, 0x04, 0x03, 0x02, 0x01, 0x86, 0x30,
                0x12, 0x06, 0x03, 0x55, 0x1d, 0x13, 0x01, 0x01,
                0xff, 0x04, 0x08, 0x30, 0x06, 0x01, 0x01, 0xff,
                0x02, 0x01, 0x00, 0x30, 0x1d, 0x06, 0x03, 0x55,
                0x1d, 0x25, 0x04, 0x16, 0x30, 0x14, 0x06, 0x08,
                0x2b, 0x06, 0x01, 0x05, 0x05, 0x07, 0x03, 0x01,
                0x06, 0x08, 0x2b, 0x06, 0x01, 0x05, 0x05, 0x07,
                0x03, 0x02, 0x30, 0x22, 0x06, 0x03, 0x55, 0x1d,
                0x20, 0x04, 0x1b, 0x30, 0x19, 0x30, 0x0d, 0x06,
                0x0b, 0x2b, 0x06, 0x01, 0x04, 0x01, 0xb2, 0x31,
                0x01, 0x02, 0x02, 0x4e, 0x30, 0x08, 0x06, 0x06,
                0x67, 0x81, 0x0c, 0x01, 0x02, 0x01, 0x30, 0x50,
                0x06, 0x03, 0x55, 0x1d, 0x1f, 0x04, 0x49, 0x30,
                0x47, 0x30, 0x45, 0xa0, 0x43, 0xa0, 0x41, 0x86,
                0x3f, 0x68, 0x74, 0x74, 0x70, 0x3a, 0x2f, 0x2f,
                0x63, 0x72, 0x6c, 0x2e, 0x75, 0x73, 0x65, 0x72,
                0x74, 0x72, 0x75, 0x73, 0x74, 0x2e, 0x63, 0x6f,
                0x6d, 0x2f, 0x55, 0x53, 0x45, 0x52, 0x54, 0x72,
                0x75, 0x73, 0x74, 0x45, 0x43, 0x43, 0x43, 0x65,
                0x72, 0x74, 0x69, 0x66, 0x69, 0x63, 0x61, 0x74,
                0x69, 0x6f, 0x6e, 0x41, 0x75, 0x74, 0x68, 0x6f,
                0x72, 0x69, 0x74, 0x79, 0x2e, 0x63, 0x72, 0x6c,
                0x30, 0x76, 0x06, 0x08, 0x2b, 0x06, 0x01, 0x05,
                0x05, 0x07, 0x01, 0x01, 0x04, 0x6a, 0x30, 0x68,
                0x30, 0x3f, 0x06, 0x08, 0x2b, 0x06, 0x01, 0x05,
                0x05, 0x07, 0x30, 0x02, 0x86, 0x33, 0x68, 0x74,
                0x74, 0x70, 0x3a, 0x2f, 0x2f, 0x63, 0x72, 0x74,
                0x2e, 0x75, 0x73, 0x65, 0x72, 0x74, 0x72, 0x75,
                0x73, 0x74, 0x2e, 0x63, 0x6f, 0x6d, 0x2f, 0x55,
                0x53, 0x45, 0x52, 0x54, 0x72, 0x75, 0x73, 0x74,
                0x45, 0x43, 0x43, 0x41, 0x64, 0x64, 0x54, 0x72,
                0x75, 0x73, 0x74, 0x43, 0x41, 0x2e, 0x63, 0x72,
                0x74, 0x30, 0x25, 0x06, 0x08, 0x2b, 0x06, 0x01,
                0x05, 0x05, 0x07, 0x30, 0x01, 0x86, 0x19, 0x68,
                0x74, 0x74, 0x70, 0x3a, 0x2f, 0x2f, 0x6f, 0x63,
                0x73, 0x70, 0x2e, 0x75, 0x73, 0x65, 0x72, 0x74,
                0x72, 0x75, 0x73, 0x74, 0x2e, 0x63, 0x6f, 0x6d,
                0x30, 0x0a, 0x06, 0x08, 0x2a, 0x86, 0x48, 0xce,
                0x3d, 0x04, 0x03, 0x03, 0x03, 0x67, 0x00, 0x30,
                0x64, 0x02, 0x30, 0x24, 0x70, 0x54, 0x0f, 0x01,
                0xc9, 0x40, 0xdd, 0xc8, 0x54, 0xd9, 0x6d, 0x54,
                0xca, 0xc8, 0x08, 0xca, 0x98, 0x43, 0x74, 0xd8,
                0x3f, 0xf4, 0xd7, 0xa9, 0x5f, 0x6d, 0xf2, 0x61,
                0xb9, 0x70, 0x0a, 0x26, 0x1b, 0x63, 0x30, 0xa8,
                0x8b, 0x31, 0x9c, 0xbf, 0x77, 0xec, 0x67, 0xb0,
                0x7f, 0xa5, 0x88, 0x02, 0x30, 0x25, 0xad, 0xab,
                0xa4, 0xb0, 0xee, 0x8d, 0x52, 0xe0, 0xdd, 0x0d,
                0x7c, 0x9d, 0xdf, 0x7d, 0x1d, 0xae, 0xe2, 0x5c,
                0x64, 0x9c, 0x74, 0xf8, 0x7e, 0x63, 0xe5, 0xc1,
                0x4e, 0x60, 0x16, 0x86, 0xb0, 0xa7, 0x5e, 0x19,
                0x6e, 0xec, 0x08, 0xc6, 0x91, 0xd8, 0xfb, 0x03,
                0x14, 0xa1, 0xa5, 0x95, 0xab, 0x00, 0x03, 0xd7,
                0x30, 0x82, 0x03, 0xd3, 0x30, 0x82, 0x02, 0xbb,
                0xa0, 0x03, 0x02, 0x01, 0x02, 0x02, 0x10, 0x56,
                0x67, 0x1d, 0x04, 0xea, 0x4f, 0x99, 0x4c, 0x6f,
                0x10, 0x81, 0x47, 0x59, 0xd2, 0x75, 0x94, 0x30,
                0x0d, 0x06, 0x09, 0x2a, 0x86, 0x48, 0x86, 0xf7,
                0x0d, 0x01, 0x01, 0x0c, 0x05, 0x00, 0x30, 0x7b,
                0x31, 0x0b, 0x30, 0x09, 0x06, 0x03, 0x55, 0x04,
                0x06, 0x13, 0x02, 0x47, 0x42, 0x31, 0x1b, 0x30,
                0x19, 0x06, 0x03, 0x55, 0x04, 0x08, 0x0c, 0x12,
                0x47, 0x72, 0x65, 0x61, 0x74, 0x65, 0x72, 0x20,
                0x4d, 0x61, 0x6e, 0x63, 0x68, 0x65, 0x73, 0x74,
                0x65, 0x72, 0x31, 0x10, 0x30, 0x0e, 0x06, 0x03,
                0x55, 0x04, 0x07, 0x0c, 0x07, 0x53, 0x61, 0x6c,
                0x66, 0x6f, 0x72, 0x64, 0x31, 0x1a, 0x30, 0x18,
                0x06, 0x03, 0x55, 0x04, 0x0a, 0x0c, 0x11, 0x43,
                0x6f, 0x6d, 0x6f, 0x64, 0x6f, 0x20, 0x43, 0x41,
                0x20, 0x4c, 0x69, 0x6d, 0x69, 0x74, 0x65, 0x64,
                0x31, 0x21, 0x30, 0x1f, 0x06, 0x03, 0x55, 0x04,
                0x03, 0x0c, 0x18, 0x41, 0x41, 0x41, 0x20, 0x43,
                0x65, 0x72, 0x74, 0x69, 0x66, 0x69, 0x63, 0x61,
                0x74, 0x65, 0x20, 0x53, 0x65, 0x72, 0x76, 0x69,
                0x63, 0x65, 0x73, 0x30, 0x1e, 0x17, 0x0d, 0x31,
                0x39, 0x30, 0x33, 0x31, 0x32, 0x30, 0x30, 0x30,
                0x30, 0x30, 0x30, 0x5a, 0x17, 0x0d, 0x32, 0x38,
                0x31, 0x32, 0x33, 0x31, 0x32, 0x33, 0x35, 0x39,
                0x35, 0x39, 0x5a, 0x30, 0x81, 0x88, 0x31, 0x0b,
                0x30, 0x09, 0x06, 0x03, 0x55, 0x04, 0x06, 0x13,
                0x02, 0x55, 0x53, 0x31, 0x13, 0x30, 0x11, 0x06,
                0x03, 0x55, 0x04, 0x08, 0x13, 0x0a, 0x4e, 0x65,
                0x77, 0x20, 0x4a, 0x65, 0x72, 0x73, 0x65, 0x79,
                0x31, 0x14, 0x30, 0x12, 0x06, 0x03, 0x55, 0x04,
                0x07, 0x13, 0x0b, 0x4a, 0x65, 0x72, 0x73, 0x65,
                0x79, 0x20, 0x43, 0x69, 0x74, 0x79, 0x31, 0x1e,
                0x30, 0x1c, 0x06, 0x03, 0x55, 0x04, 0x0a, 0x13,
                0x15, 0x54, 0x68, 0x65, 0x20, 0x55, 0x53, 0x45,
                0x52, 0x54, 0x52, 0x55, 0x53, 0x54, 0x20, 0x4e,
                0x65, 0x74, 0x77, 0x6f, 0x72, 0x6b, 0x31, 0x2e,
                0x30, 0x2c, 0x06, 0x03, 0x55, 0x04, 0x03, 0x13,
                0x25, 0x55, 0x53, 0x45, 0x52, 0x54, 0x72, 0x75,
                0x73, 0x74, 0x20, 0x45, 0x43, 0x43, 0x20, 0x43,
                0x65, 0x72, 0x74, 0x69, 0x66, 0x69, 0x63, 0x61,
                0x74, 0x69, 0x6f, 0x6e, 0x20, 0x41, 0x75, 0x74,
                0x68, 0x6f, 0x72, 0x69, 0x74, 0x79, 0x30, 0x76,
                0x30, 0x10, 0x06, 0x07, 0x2a, 0x86, 0x48, 0xce,
                0x3d, 0x02, 0x01, 0x06, 0x05, 0x2b, 0x81, 0x04,
                0x00, 0x22, 0x03, 0x62, 0x00, 0x04, 0x1a, 0xac,
                0x54, 0x5a, 0xa9, 0xf9, 0x68, 0x23, 0xe7, 0x7a,
                0xd5, 0x24, 0x6f, 0x53, 0xc6, 0x5a, 0xd8, 0x4b,
                0xab, 0xc6, 0xd5, 0xb6, 0xd1, 0xe6, 0x73, 0x71,
                0xae, 0xdd, 0x9c, 0xd6, 0x0c, 0x61, 0xfd, 0xdb,
                0xa0, 0x89, 0x03, 0xb8, 0x05, 0x14, 0xec, 0x57,
                0xce, 0xee, 0x5d, 0x3f, 0xe2, 0x21, 0xb3, 0xce,
                0xf7, 0xd4, 0x8a, 0x79, 0xe0, 0xa3, 0x83, 0x7e,
                0x2d, 0x97, 0xd0, 0x61, 0xc4, 0xf1, 0x99, 0xdc,
                0x25, 0x91, 0x63, 0xab, 0x7f, 0x30, 0xa3, 0xb4,
                0x70, 0xe2, 0xc7, 0xa1, 0x33, 0x9c, 0xf3, 0xbf,
                0x2e, 0x5c, 0x53, 0xb1, 0x5f, 0xb3, 0x7d, 0x32,
                0x7f, 0x8a, 0x34, 0xe3, 0x79, 0x79, 0xa3, 0x81,
                0xf2, 0x30, 0x81, 0xef, 0x30, 0x1f, 0x06, 0x03,
                0x55, 0x1d, 0x23, 0x04, 0x18, 0x30, 0x16, 0x80,
                0x14, 0xa0, 0x11, 0x0a, 0x23, 0x3e, 0x96, 0xf1,
                0x07, 0xec, 0xe2, 0xaf, 0x29, 0xef, 0x82, 0xa5,
                0x7f, 0xd0, 0x30, 0xa4, 0xb4, 0x30, 0x1d, 0x06,
                0x03, 0x55, 0x1d, 0x0e, 0x04, 0x16, 0x04, 0x14,
                0x3a, 0xe1, 0x09, 0x86, 0xd4, 0xcf, 0x19, 0xc2,
                0x96, 0x76, 0x74, 0x49, 0x76, 0xdc, 0xe0, 0x35,
                0xc6, 0x63, 0x63, 0x9a, 0x30, 0x0e, 0x06, 0x03,
                0x55, 0x1d, 0x0f, 0x01, 0x01, 0xff, 0x04, 0x04,
                0x03, 0x02, 0x01, 0x86, 0x30, 0x0f, 0x06, 0x03,
                0x55, 0x1d, 0x13, 0x01, 0x01, 0xff, 0x04, 0x05,
                0x30, 0x03, 0x01, 0x01, 0xff, 0x30, 0x11, 0x06,
                0x03, 0x55, 0x1d, 0x20, 0x04, 0x0a, 0x30, 0x08,
                0x30, 0x06, 0x06, 0x04, 0x55, 0x1d, 0x20, 0x00,
                0x30, 0x43, 0x06, 0x03, 0x55, 0x1d, 0x1f, 0x04,
                0x3c, 0x30, 0x3a, 0x30, 0x38, 0xa0, 0x36, 0xa0,
                0x34, 0x86, 0x32, 0x68, 0x74, 0x74, 0x70, 0x3a,
                0x2f, 0x2f, 0x63, 0x72, 0x6c, 0x2e, 0x63, 0x6f,
                0x6d, 0x6f, 0x64, 0x6f, 0x63, 0x61, 0x2e, 0x63,
                0x6f, 0x6d, 0x2f, 0x41, 0x41, 0x41, 0x43, 0x65,
                0x72, 0x74, 0x69, 0x66, 0x69, 0x63, 0x61, 0x74,
                0x65, 0x53, 0x65, 0x72, 0x76, 0x69, 0x63, 0x65,
                0x73, 0x2e, 0x63, 0x72, 0x6c, 0x30, 0x34, 0x06,
                0x08, 0x2b, 0x06, 0x01, 0x05, 0x05, 0x07, 0x01,
                0x01, 0x04, 0x28, 0x30, 0x26, 0x30, 0x24, 0x06,
                0x08, 0x2b, 0x06, 0x01, 0x05, 0x05, 0x07, 0x30,
                0x01, 0x86, 0x18, 0x68, 0x74, 0x74, 0x70, 0x3a,
                0x2f, 0x2f, 0x6f, 0x63, 0x73, 0x70, 0x2e, 0x63,
                0x6f, 0x6d, 0x6f, 0x64, 0x6f, 0x63, 0x61, 0x2e,
                0x63, 0x6f, 0x6d, 0x30, 0x0d, 0x06, 0x09, 0x2a,
                0x86, 0x48, 0x86, 0xf7, 0x0d, 0x01, 0x01, 0x0c,
                0x05, 0x00, 0x03, 0x82, 0x01, 0x01, 0x00, 0x19,
                0xec, 0xeb, 0x9d, 0x89, 0x2c, 0x20, 0x0b, 0x04,
                0x80, 0x1d, 0x18, 0xde, 0x42, 0x99, 0x72, 0x99,
                0x16, 0x32, 0xbd, 0x0e, 0x9c, 0x75, 0x5b, 0x2c,
                0x15, 0xe2, 0x29, 0x40, 0x6d, 0xee, 0xff, 0x72,
                0xdb, 0xdb, 0xab, 0x90, 0x1f, 0x8c, 0x95, 0xf2,
                0x8a, 0x3d, 0x08, 0x72, 0x42, 0x89, 0x50, 0x07,
                0xe2, 0x39, 0x15, 0x6c, 0x01, 0x87, 0xd9, 0x16,
                0x1a, 0xf5, 0xc0, 0x75, 0x2b, 0xc5, 0xe6, 0x56,
                0x11, 0x07, 0xdf, 0xd8, 0x98, 0xbc, 0x7c, 0x9f,
                0x19, 0x39, 0xdf, 0x8b, 0xca, 0x00, 0x64, 0x73,
                0xbc, 0x46, 0x10, 0x9b, 0x93, 0x23, 0x8d, 0xbe,
                0x16, 0xc3, 0x2e, 0x08, 0x82, 0x9c, 0x86, 0x33,
                0x74, 0x76, 0x3b, 0x28, 0x4c, 0x8d, 0x03, 0x42,
                0x85, 0xb3, 0xe2, 0xb2, 0x23, 0x42, 0xd5, 0x1f,
                0x7a, 0x75, 0x6a, 0x1a, 0xd1, 0x7c, 0xaa, 0x67,
                0x21, 0xc4, 0x33, 0x3a, 0x39, 0x6d, 0x53, 0xc9,
                0xa2, 0xed, 0x62, 0x22, 0xa8, 0xbb, 0xe2, 0x55,
                0x6c, 0x99, 0x6c, 0x43, 0x6b, 0x91, 0x97, 0xd1,
                0x0c, 0x0b, 0x93, 0x02, 0x1d, 0xd2, 0xbc, 0x69,
                0x77, 0x49, 0xe6, 0x1b, 0x4d, 0xf7, 0xbf, 0x14,
                0x78, 0x03, 0xb0, 0xa6, 0xba, 0x0b, 0xb4, 0xe1,
                0x85, 0x7f, 0x2f, 0xdc, 0x42, 0x3b, 0xad, 0x74,
                0x01, 0x48, 0xde, 0xd6, 0x6c, 0xe1, 0x19, 0x98,
                0x09, 0x5e, 0x0a, 0xb3, 0x67, 0x47, 0xfe, 0x1c,
                0xe0, 0xd5, 0xc1, 0x28, 0xef, 0x4a, 0x8b, 0x44,
                0x31, 0x26, 0x04, 0x37, 0x8d, 0x89, 0x74, 0x36,
                0x2e, 0xef, 0xa5, 0x22, 0x0f, 0x83, 0x74, 0x49,
                0x92, 0xc7, 0xf7, 0x10, 0xc2, 0x0c, 0x29, 0xfb,
                0xb7, 0xbd, 0xba, 0x7f, 0xe3, 0x5f, 0xd5, 0x9f,
                0xf2, 0xa9, 0xf4, 0x74, 0xd5, 0xb8, 0xe1, 0xb3,
                0xb0, 0x81, 0xe4, 0xe1, 0xa5, 0x63, 0xa3, 0xcc,
                0xea, 0x04, 0x78, 0x90, 0x6e, 0xbf, 0xf7
        };

        char[] serverKeyExchange = {
                0x0c, 0x00, 0x00, 0x90, 0x03, 0x00, 0x17, 0x41,
                0x04, 0x78, 0xc1, 0xf2, 0xe9, 0xb8, 0xf6, 0x6e,
                0xa5, 0x23, 0xd0, 0xac, 0x6d, 0xa6, 0xfe, 0x54,
                0x1d, 0xc1, 0xca, 0xfb, 0x97, 0x13, 0x9a, 0xb6,
                0x6a, 0x44, 0xdb, 0x5d, 0x24, 0x91, 0x21, 0x55,
                0xfd, 0xc4, 0xcd, 0xe9, 0x2d, 0xa5, 0x2d, 0x49,
                0x8f, 0x02, 0xc4, 0xea, 0xe4, 0xd7, 0x6f, 0xe0,
                0x46, 0xa1, 0x40, 0xb5, 0x17, 0xc1, 0xdf, 0x84,
                0xa2, 0xe5, 0x6b, 0x12, 0x53, 0x6a, 0x0d, 0xf4,
                0x43, 0x06, 0x03, 0x00, 0x47, 0x30, 0x45, 0x02,
                0x20, 0x28, 0x49, 0xce, 0x52, 0x4a, 0xaf, 0xb7,
                0x5d, 0x8c, 0x96, 0xcf, 0x7b, 0xd1, 0xd7, 0x31,
                0xba, 0x70, 0x83, 0x93, 0xc1, 0x3e, 0xa6, 0xe4,
                0x27, 0xae, 0xb7, 0xa4, 0xc1, 0x1e, 0xa6, 0x86,
                0x9d, 0x02, 0x21, 0x00, 0xf6, 0xea, 0x1d, 0x9c,
                0xa0, 0x3d, 0xed, 0xe5, 0x10, 0x22, 0xde, 0x84,
                0x49, 0x2c, 0x4a, 0x99, 0x5a, 0x63, 0xd8, 0xab,
                0x36, 0x43, 0x85, 0xa4, 0x38, 0x34, 0xbd, 0x89,
                0x9d, 0x5f, 0xb7, 0x4d
        };


        char[] serverDone = {
                0x0e, 0x00, 0x00, 0x00
        };

        char[] clientKeyExChange = {
                0x10, 0x00, 0x00, 0x42, 0x41, 0x04, 0xa6, 0xe2,
                0xdb, 0x82, 0xd7, 0x14, 0xac, 0x0b, 0xc0, 0x5b,
                0x33, 0x92, 0x3b, 0x3f, 0x28, 0xa2, 0xf1, 0x80,
                0xd1, 0x2a, 0x43, 0x36, 0xf4, 0x4f, 0x07, 0x72,
                0xa5, 0xe9, 0x4a, 0x86, 0xee, 0x6e, 0xe0, 0xd9,
                0x88, 0x1a, 0xc0, 0xfe, 0x55, 0x2b, 0xad, 0xae,
                0x3c, 0xfc, 0xa9, 0x7e, 0x41, 0x43, 0x03, 0xf6,
                0xac, 0xf7, 0x9e, 0x78, 0x2a, 0x77, 0x46, 0xb8,
                0x1b, 0x09, 0x6f, 0x26, 0x6f, 0xe4
        };


        byte[] concat = concat(charArrayToByteArray(clientHello), charArrayToByteArray(serverHello));
        concat = concat(concat, charArrayToByteArray(certificate));
        concat = concat(concat, charArrayToByteArray(serverKeyExchange));
        concat = concat(concat, charArrayToByteArray(serverDone));
        concat = concat(concat, charArrayToByteArray(clientKeyExChange));


        BcTlsCrypto bcTlsCrypto = new BcTlsCrypto();
        Digest digest = bcTlsCrypto.createDigest(CryptoHashAlgorithm.sha256);


        digest.update(concat,0, concat.length);

        byte[] rv = new byte[digest.getDigestSize()];
        digest.doFinal(rv, 0);

        System.out.println(TLSTest.bytesToHex(rv));

    }

    @Test
    public byte[] testHash2ed(){
        byte[] clientHello = hexStringToByteArray("01 00 00 f9 03 03 e3 e5 0a 0c c0 \n" +
                "f1 f7 49 4c 14 89 45 9c 28 05 39 fa b4 e9 ce 08 \n" +
                "77 f0 94 1f 44 4e 05 34 d6 4b c0 20 e0 76 16 70 \n" +
                "7f ac e1 53 5f 39 77 f3 98 b3 ff 2a 19 b6 76 08 \n" +
                "3e 23 15 d2 10 78 c5 a8 c1 2a 65 3a 00 1e 13 03 \n" +
                "13 01 cc a9 c0 2b c0 23 c0 09 cc a8 c0 2f c0 27 \n" +
                "c0 13 cc aa 00 9e 00 67 00 33 00 ff 01 00 00 92 \n" +
                "00 17 00 00 00 16 00 00 00 2b 00 05 04 03 04 03 \n" +
                "03 00 0a 00 10 00 0e 00 1d 00 1e 00 17 00 18 01 \n" +
                "00 01 01 01 02 00 33 00 26 00 24 00 1d 00 20 20 \n" +
                "87 73 67 9a 1e 19 0a 9c 9e 04 b8 1d 49 60 6f a1 \n" +
                "2e 6c 12 f0 f6 19 03 24 99 0c 77 11 ec 04 60 00 \n" +
                "05 00 05 01 00 00 00 00 00 0d 00 30 00 2e 08 07 \n" +
                "08 08 04 03 05 03 06 03 08 04 08 05 08 06 08 09 \n" +
                "08 0a 08 0b 04 01 05 01 06 01 04 02 05 02 06 02 \n" +
                "03 03 03 01 03 02 02 03 02 01 02 02 00 0b 00 02 \n" +
                "01 00");

        byte[] serverHello = hexStringToByteArray("02 00 00 55 03 03 52 f6 1e a4 6d \n" +
                "79 2e 2b 1b 94 70 d7 eb 21 5d 42 ed 4b a3 44 88 \n" +
                "45 f2 b8 c1 4b 63 8f 28 5b e9 fe 20 0a 0e a0 a9 \n" +
                "e9 88 4d 2d 07 c1 54 db 76 d3 9c e0 97 48 eb af \n" +
                "7e 54 ee 51 d1 bb 61 39 7a 80 53 d0 c0 2b 00 00 \n" +
                "0d ff 01 00 01 00 00 0b 00 04 03 00 01 02");

        byte[] certificate = hexStringToByteArray("0b 00 0b 73 00 0b 70 00 04 07 30 \n" +
                "82 04 03 30 82 03 88 a0 03 02 01 02 02 10 4f c9 \n" +
                "72 15 bf 60 1b 22 25 62 0a 35 37 a8 57 73 30 0a \n" +
                "06 08 2a 86 48 ce 3d 04 03 03 30 4b 31 0b 30 09 \n" +
                "06 03 55 04 06 13 02 41 54 31 10 30 0e 06 03 55 \n" +
                "04 0a 13 07 5a 65 72 6f 53 53 4c 31 2a 30 28 06 \n" +
                "03 55 04 03 13 21 5a 65 72 6f 53 53 4c 20 45 43 \n" +
                "43 20 44 6f 6d 61 69 6e 20 53 65 63 75 72 65 20 \n" +
                "53 69 74 65 20 43 41 30 1e 17 0d 32 34 31 32 32 \n" +
                "32 30 30 30 30 30 30 5a 17 0d 32 35 30 33 32 32 \n" +
                "32 33 35 39 35 39 5a 30 14 31 12 30 10 06 03 55 \n" +
                "04 03 13 09 77 74 66 75 2e 73 69 74 65 30 59 30 \n" +
                "13 06 07 2a 86 48 ce 3d 02 01 06 08 2a 86 48 ce \n" +
                "3d 03 01 07 03 42 00 04 4a cd f6 13 8a a1 45 4b \n" +
                "a1 b2 29 2f 8c 31 06 2a 7f 03 7f e0 cd be 38 9b \n" +
                "c3 85 02 0e 08 13 fb 14 31 8c 52 9c 12 31 76 dd \n" +
                "be bf 92 7a 7d 66 66 79 df 2e 90 18 2b 58 62 67 \n" +
                "51 57 82 5a 6c eb 11 56 a3 82 02 83 30 82 02 7f \n" +
                "30 1f 06 03 55 1d 23 04 18 30 16 80 14 0f 6b e6 \n" +
                "4b ce 39 47 ae f6 7e 90 1e 79 f0 30 91 92 c8 5f \n" +
                "a3 30 1d 06 03 55 1d 0e 04 16 04 14 ee f5 a9 f7 \n" +
                "35 a7 22 36 8c b5 58 f4 03 78 2e 98 bd e6 fe c2 \n" +
                "30 0e 06 03 55 1d 0f 01 01 ff 04 04 03 02 07 80 \n" +
                "30 0c 06 03 55 1d 13 01 01 ff 04 02 30 00 30 1d \n" +
                "06 03 55 1d 25 04 16 30 14 06 08 2b 06 01 05 05 \n" +
                "07 03 01 06 08 2b 06 01 05 05 07 03 02 30 49 06 \n" +
                "03 55 1d 20 04 42 30 40 30 34 06 0b 2b 06 01 04 \n" +
                "01 b2 31 01 02 02 4e 30 25 30 23 06 08 2b 06 01 \n" +
                "05 05 07 02 01 16 17 68 74 74 70 73 3a 2f 2f 73 \n" +
                "65 63 74 69 67 6f 2e 63 6f 6d 2f 43 50 53 30 08 \n" +
                "06 06 67 81 0c 01 02 01 30 81 88 06 08 2b 06 01 \n" +
                "05 05 07 01 01 04 7c 30 7a 30 4b 06 08 2b 06 01 \n" +
                "05 05 07 30 02 86 3f 68 74 74 70 3a 2f 2f 7a 65 \n" +
                "72 6f 73 73 6c 2e 63 72 74 2e 73 65 63 74 69 67 \n" +
                "6f 2e 63 6f 6d 2f 5a 65 72 6f 53 53 4c 45 43 43 \n" +
                "44 6f 6d 61 69 6e 53 65 63 75 72 65 53 69 74 65 \n" +
                "43 41 2e 63 72 74 30 2b 06 08 2b 06 01 05 05 07 \n" +
                "30 01 86 1f 68 74 74 70 3a 2f 2f 7a 65 72 6f 73 \n" +
                "73 6c 2e 6f 63 73 70 2e 73 65 63 74 69 67 6f 2e \n" +
                "63 6f 6d 30 82 01 05 06 0a 2b 06 01 04 01 d6 79 \n" +
                "02 04 02 04 81 f6 04 81 f3 00 f1 00 77 00 cf 11 \n" +
                "56 ee d5 2e 7c af f3 87 5b d9 69 2e 9b e9 1a 71 \n" +
                "67 4a b0 17 ec ac 01 d2 5b 77 ce cc 3b 08 00 00 \n" +
                "01 93 ec be 8a c5 00 00 04 03 00 48 30 46 02 21 \n" +
                "00 ba 14 89 4c d4 a6 bf d5 8e 18 3b 10 63 54 35 \n" +
                "3e 16 3a f1 b0 1d e3 49 20 a0 7f 36 e0 44 30 e7 \n" +
                "e1 02 21 00 a8 dc 88 b6 51 32 6b 67 1e 60 d9 3a \n" +
                "8b 2e f1 f9 1c f1 ff 18 8d c5 3f f7 9b cc 4b d4 \n" +
                "28 db 5c 13 00 76 00 cc fb 0f 6a 85 71 09 65 fe \n" +
                "95 9b 53 ce e9 b2 7c 22 e9 85 5c 0d 97 8d b6 a9 \n" +
                "7e 54 c0 fe 4c 0d b0 00 00 01 93 ec be 8a 8f 00 \n" +
                "00 04 03 00 47 30 45 02 20 1b ff 93 39 6b c9 87 \n" +
                "b7 00 d6 45 63 66 92 b3 60 a0 4f 85 86 d5 1a 7a \n" +
                "4d 4d 40 30 d8 9e 7a 21 df 02 21 00 fa b6 ae 47 \n" +
                "eb 9f c3 a7 f7 e1 87 3c 28 aa a3 47 88 81 af 36 \n" +
                "ff 1d 16 3e f1 3a 6c 5f 90 cb e3 e1 30 21 06 03 \n" +
                "55 1d 11 04 1a 30 18 82 09 77 74 66 75 2e 73 69 \n" +
                "74 65 82 0b 2a 2e 77 74 66 75 2e 73 69 74 65 30 \n" +
                "0a 06 08 2a 86 48 ce 3d 04 03 03 03 69 00 30 66 \n" +
                "02 31 00 f9 31 c9 8c 2e 6b f5 ca d4 ad dd 19 87 \n" +
                "27 1f d8 bd 32 ea 6c 99 6c 2e d6 c7 87 1c 02 f1 \n" +
                "33 40 68 3a 1a 03 cd 65 10 ae 68 63 85 2e 30 80 \n" +
                "22 da 47 02 31 00 b6 93 15 5e 96 75 ce 12 4e c0 \n" +
                "c7 14 79 2b 0a 63 ed 00 42 3a a3 82 0a 68 29 d2 \n" +
                "90 2a a8 59 c7 56 df 33 53 6f 59 0e 30 ec a0 81 \n" +
                "42 65 83 a4 09 68 00 03 89 30 82 03 85 30 82 03 \n" +
                "0c a0 03 02 01 02 02 10 23 b7 6d e3 c1 bb 2b 1a \n" +
                "51 96 1e 08 ea b7 64 e8 30 0a 06 08 2a 86 48 ce \n" +
                "3d 04 03 03 30 81 88 31 0b 30 09 06 03 55 04 06 \n" +
                "13 02 55 53 31 13 30 11 06 03 55 04 08 13 0a 4e \n" +
                "65 77 20 4a 65 72 73 65 79 31 14 30 12 06 03 55 \n" +
                "04 07 13 0b 4a 65 72 73 65 79 20 43 69 74 79 31 \n" +
                "1e 30 1c 06 03 55 04 0a 13 15 54 68 65 20 55 53 \n" +
                "45 52 54 52 55 53 54 20 4e 65 74 77 6f 72 6b 31 \n" +
                "2e 30 2c 06 03 55 04 03 13 25 55 53 45 52 54 72 \n" +
                "75 73 74 20 45 43 43 20 43 65 72 74 69 66 69 63 \n" +
                "61 74 69 6f 6e 20 41 75 74 68 6f 72 69 74 79 30 \n" +
                "1e 17 0d 32 30 30 31 33 30 30 30 30 30 30 30 5a \n" +
                "17 0d 33 30 30 31 32 39 32 33 35 39 35 39 5a 30 \n" +
                "4b 31 0b 30 09 06 03 55 04 06 13 02 41 54 31 10 \n" +
                "30 0e 06 03 55 04 0a 13 07 5a 65 72 6f 53 53 4c \n" +
                "31 2a 30 28 06 03 55 04 03 13 21 5a 65 72 6f 53 \n" +
                "53 4c 20 45 43 43 20 44 6f 6d 61 69 6e 20 53 65 \n" +
                "63 75 72 65 20 53 69 74 65 20 43 41 30 76 30 10 \n" +
                "06 07 2a 86 48 ce 3d 02 01 06 05 2b 81 04 00 22 \n" +
                "03 62 00 04 36 41 61 17 2b 53 25 ed aa ca 94 e4 \n" +
                "d6 da 48 57 ef 50 ba 84 64 82 d7 bb 05 1b d6 1f \n" +
                "06 24 f6 a5 33 9d 8c e7 f1 0b 55 68 63 82 30 10 \n" +
                "5f 8d 65 ec aa a8 af 97 ca b5 86 ce 30 01 89 74 \n" +
                "de e3 4e 5e 01 6e ee 26 7b cc 53 fa 23 a4 f7 44 \n" +
                "1d 3e 4d 1e 5f 66 a6 ad 85 f6 f2 e3 bc 8e 09 98 \n" +
                "80 24 8e 20 a3 82 01 75 30 82 01 71 30 1f 06 03 \n" +
                "55 1d 23 04 18 30 16 80 14 3a e1 09 86 d4 cf 19 \n" +
                "c2 96 76 74 49 76 dc e0 35 c6 63 63 9a 30 1d 06 \n" +
                "03 55 1d 0e 04 16 04 14 0f 6b e6 4b ce 39 47 ae \n" +
                "f6 7e 90 1e 79 f0 30 91 92 c8 5f a3 30 0e 06 03 \n" +
                "55 1d 0f 01 01 ff 04 04 03 02 01 86 30 12 06 03 \n" +
                "55 1d 13 01 01 ff 04 08 30 06 01 01 ff 02 01 00 \n" +
                "30 1d 06 03 55 1d 25 04 16 30 14 06 08 2b 06 01 \n" +
                "05 05 07 03 01 06 08 2b 06 01 05 05 07 03 02 30 \n" +
                "22 06 03 55 1d 20 04 1b 30 19 30 0d 06 0b 2b 06 \n" +
                "01 04 01 b2 31 01 02 02 4e 30 08 06 06 67 81 0c \n" +
                "01 02 01 30 50 06 03 55 1d 1f 04 49 30 47 30 45 \n" +
                "a0 43 a0 41 86 3f 68 74 74 70 3a 2f 2f 63 72 6c \n" +
                "2e 75 73 65 72 74 72 75 73 74 2e 63 6f 6d 2f 55 \n" +
                "53 45 52 54 72 75 73 74 45 43 43 43 65 72 74 69 \n" +
                "66 69 63 61 74 69 6f 6e 41 75 74 68 6f 72 69 74 \n" +
                "79 2e 63 72 6c 30 76 06 08 2b 06 01 05 05 07 01 \n" +
                "01 04 6a 30 68 30 3f 06 08 2b 06 01 05 05 07 30 \n" +
                "02 86 33 68 74 74 70 3a 2f 2f 63 72 74 2e 75 73 \n" +
                "65 72 74 72 75 73 74 2e 63 6f 6d 2f 55 53 45 52 \n" +
                "54 72 75 73 74 45 43 43 41 64 64 54 72 75 73 74 \n" +
                "43 41 2e 63 72 74 30 25 06 08 2b 06 01 05 05 07 \n" +
                "30 01 86 19 68 74 74 70 3a 2f 2f 6f 63 73 70 2e \n" +
                "75 73 65 72 74 72 75 73 74 2e 63 6f 6d 30 0a 06 \n" +
                "08 2a 86 48 ce 3d 04 03 03 03 67 00 30 64 02 30 \n" +
                "24 70 54 0f 01 c9 40 dd c8 54 d9 6d 54 ca c8 08 \n" +
                "ca 98 43 74 d8 3f f4 d7 a9 5f 6d f2 61 b9 70 0a \n" +
                "26 1b 63 30 a8 8b 31 9c bf 77 ec 67 b0 7f a5 88 \n" +
                "02 30 25 ad ab a4 b0 ee 8d 52 e0 dd 0d 7c 9d df \n" +
                "7d 1d ae e2 5c 64 9c 74 f8 7e 63 e5 c1 4e 60 16 \n" +
                "86 b0 a7 5e 19 6e ec 08 c6 91 d8 fb 03 14 a1 a5 \n" +
                "95 ab 00 03 d7 30 82 03 d3 30 82 02 bb a0 03 02 \n" +
                "01 02 02 10 56 67 1d 04 ea 4f 99 4c 6f 10 81 47 \n" +
                "59 d2 75 94 30 0d 06 09 2a 86 48 86 f7 0d 01 01 \n" +
                "0c 05 00 30 7b 31 0b 30 09 06 03 55 04 06 13 02 \n" +
                "47 42 31 1b 30 19 06 03 55 04 08 0c 12 47 72 65 \n" +
                "61 74 65 72 20 4d 61 6e 63 68 65 73 74 65 72 31 \n" +
                "10 30 0e 06 03 55 04 07 0c 07 53 61 6c 66 6f 72 \n" +
                "64 31 1a 30 18 06 03 55 04 0a 0c 11 43 6f 6d 6f \n" +
                "64 6f 20 43 41 20 4c 69 6d 69 74 65 64 31 21 30 \n" +
                "1f 06 03 55 04 03 0c 18 41 41 41 20 43 65 72 74 \n" +
                "69 66 69 63 61 74 65 20 53 65 72 76 69 63 65 73 \n" +
                "30 1e 17 0d 31 39 30 33 31 32 30 30 30 30 30 30 \n" +
                "5a 17 0d 32 38 31 32 33 31 32 33 35 39 35 39 5a \n" +
                "30 81 88 31 0b 30 09 06 03 55 04 06 13 02 55 53 \n" +
                "31 13 30 11 06 03 55 04 08 13 0a 4e 65 77 20 4a \n" +
                "65 72 73 65 79 31 14 30 12 06 03 55 04 07 13 0b \n" +
                "4a 65 72 73 65 79 20 43 69 74 79 31 1e 30 1c 06 \n" +
                "03 55 04 0a 13 15 54 68 65 20 55 53 45 52 54 52 \n" +
                "55 53 54 20 4e 65 74 77 6f 72 6b 31 2e 30 2c 06 \n" +
                "03 55 04 03 13 25 55 53 45 52 54 72 75 73 74 20 \n" +
                "45 43 43 20 43 65 72 74 69 66 69 63 61 74 69 6f \n" +
                "6e 20 41 75 74 68 6f 72 69 74 79 30 76 30 10 06 \n" +
                "07 2a 86 48 ce 3d 02 01 06 05 2b 81 04 00 22 03 \n" +
                "62 00 04 1a ac 54 5a a9 f9 68 23 e7 7a d5 24 6f \n" +
                "53 c6 5a d8 4b ab c6 d5 b6 d1 e6 73 71 ae dd 9c \n" +
                "d6 0c 61 fd db a0 89 03 b8 05 14 ec 57 ce ee 5d \n" +
                "3f e2 21 b3 ce f7 d4 8a 79 e0 a3 83 7e 2d 97 d0 \n" +
                "61 c4 f1 99 dc 25 91 63 ab 7f 30 a3 b4 70 e2 c7 \n" +
                "a1 33 9c f3 bf 2e 5c 53 b1 5f b3 7d 32 7f 8a 34 \n" +
                "e3 79 79 a3 81 f2 30 81 ef 30 1f 06 03 55 1d 23 \n" +
                "04 18 30 16 80 14 a0 11 0a 23 3e 96 f1 07 ec e2 \n" +
                "af 29 ef 82 a5 7f d0 30 a4 b4 30 1d 06 03 55 1d \n" +
                "0e 04 16 04 14 3a e1 09 86 d4 cf 19 c2 96 76 74 \n" +
                "49 76 dc e0 35 c6 63 63 9a 30 0e 06 03 55 1d 0f \n" +
                "01 01 ff 04 04 03 02 01 86 30 0f 06 03 55 1d 13 \n" +
                "01 01 ff 04 05 30 03 01 01 ff 30 11 06 03 55 1d \n" +
                "20 04 0a 30 08 30 06 06 04 55 1d 20 00 30 43 06 \n" +
                "03 55 1d 1f 04 3c 30 3a 30 38 a0 36 a0 34 86 32 \n" +
                "68 74 74 70 3a 2f 2f 63 72 6c 2e 63 6f 6d 6f 64 \n" +
                "6f 63 61 2e 63 6f 6d 2f 41 41 41 43 65 72 74 69 \n" +
                "66 69 63 61 74 65 53 65 72 76 69 63 65 73 2e 63 \n" +
                "72 6c 30 34 06 08 2b 06 01 05 05 07 01 01 04 28 \n" +
                "30 26 30 24 06 08 2b 06 01 05 05 07 30 01 86 18 \n" +
                "68 74 74 70 3a 2f 2f 6f 63 73 70 2e 63 6f 6d 6f \n" +
                "64 6f 63 61 2e 63 6f 6d 30 0d 06 09 2a 86 48 86 \n" +
                "f7 0d 01 01 0c 05 00 03 82 01 01 00 19 ec eb 9d \n" +
                "89 2c 20 0b 04 80 1d 18 de 42 99 72 99 16 32 bd \n" +
                "0e 9c 75 5b 2c 15 e2 29 40 6d ee ff 72 db db ab \n" +
                "90 1f 8c 95 f2 8a 3d 08 72 42 89 50 07 e2 39 15 \n" +
                "6c 01 87 d9 16 1a f5 c0 75 2b c5 e6 56 11 07 df \n" +
                "d8 98 bc 7c 9f 19 39 df 8b ca 00 64 73 bc 46 10 \n" +
                "9b 93 23 8d be 16 c3 2e 08 82 9c 86 33 74 76 3b \n" +
                "28 4c 8d 03 42 85 b3 e2 b2 23 42 d5 1f 7a 75 6a \n" +
                "1a d1 7c aa 67 21 c4 33 3a 39 6d 53 c9 a2 ed 62 \n" +
                "22 a8 bb e2 55 6c 99 6c 43 6b 91 97 d1 0c 0b 93 \n" +
                "02 1d d2 bc 69 77 49 e6 1b 4d f7 bf 14 78 03 b0 \n" +
                "a6 ba 0b b4 e1 85 7f 2f dc 42 3b ad 74 01 48 de \n" +
                "d6 6c e1 19 98 09 5e 0a b3 67 47 fe 1c e0 d5 c1 \n" +
                "28 ef 4a 8b 44 31 26 04 37 8d 89 74 36 2e ef a5 \n" +
                "22 0f 83 74 49 92 c7 f7 10 c2 0c 29 fb b7 bd ba \n" +
                "7f e3 5f d5 9f f2 a9 f4 74 d5 b8 e1 b3 b0 81 e4 \n" +
                "e1 a5 63 a3 cc ea 04 78 90 6e bf f7");

        byte[] serverKeyExchange = hexStringToByteArray("0c 00 00 90 03 00 17 41 04 cf 1f \n" +
                "1d 3c 13 27 b0 bf d5 0d f9 55 9e b2 c2 e5 f7 ad \n" +
                "ed db db 34 03 37 6e d2 e9 b1 ae 93 a0 b1 33 87 \n" +
                "5d 9b 90 d9 71 ee df ea 19 d3 b9 da 74 bf 60 f3 \n" +
                "39 4c 37 5d dc 75 82 68 fe a3 9d 7e c1 a4 06 03 \n" +
                "00 47 30 45 02 21 00 de ed 7f a8 fd 3c d8 e0 d5 \n" +
                "f2 e0 cf 22 eb a8 48 c6 a8 11 4a 65 30 7a 9e c6 \n" +
                "8d fa 22 a2 a5 b0 e5 02 20 11 2d 77 5a 4b 27 a0 \n" +
                "b6 0a 00 c0 e0 f5 ca 53 ba 57 66 40 76 79 26 c3 \n" +
                "40 c0 fc 46 d4 97 74 87 44");

        byte[] serverDone = hexStringToByteArray("0e 00 00 00");

        byte[] clientKeyExChange = hexStringToByteArray("10 00 00 42 41 04 8e 74 54 c4 02 \n" +
                "9d a0 63 1d 72 d2 ee 25 74 2d 59 82 6f 1b 85 51 \n" +
                "65 38 c8 e7 4a 3e af 4c bc fa 83 cc c5 6c f5 c2 \n" +
                "93 1a 51 34 ac 2e 60 13 0d 17 23 35 d7 2e c2 01 \n" +
                "2e 1e a5 47 f5 60 40 ab 94 b1 ef");

        byte[] concat = concat(clientHello, serverHello);
        concat = concat(concat, certificate);
        concat = concat(concat, serverKeyExchange);
        concat = concat(concat, serverDone);
        concat = concat(concat, clientKeyExChange);


        BcTlsCrypto bcTlsCrypto = new BcTlsCrypto();
        Digest digest = bcTlsCrypto.createDigest(CryptoHashAlgorithm.sha256);
        digest.update(concat,0, concat.length);

        byte[] rv = new byte[digest.getDigestSize()];
        digest.doFinal(rv, 0);

        System.out.println(TLSTest.bytesToHex(rv));
        return rv;
    }

    public static byte[] charArrayToByteArray(char[] charArray) {
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            // 将 char 转换为 byte，使用 & 0xFF 确保只取低 8 位
            byteArray[i] = (byte) (charArray[i] & 0xFF);
        }
        return byteArray;
    }
}
