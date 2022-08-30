package _base.encryption;

import com.github.fommil.ssh.SshRsaCrypto;
import org.apache.commons.io.FileUtils;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

import javax.crypto.Cipher;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2022/8/31
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class SshCrypto {

    public static void main(String[] args) throws Exception {}

    // 好使
    @Test
    public void testSshCrypto() throws Exception {
        SshRsaCrypto rsa = new SshRsaCrypto();

        String pubPath = "res/t_rsa_b_2048_m_pem/pub.key";
        String priPath = "res/t_rsa_b_2048_m_pem/pri.key";

        PublicKey publicKey = rsa.readPublicKey(rsa.slurpPublicKey(getPathFileString(pubPath)));
        PrivateKey privateKey = rsa.readPrivateKey(rsa.slurpPrivateKey(getPathFileString(priPath)));

        showInfo(publicKey,privateKey);
    }

    //暂时不好使
    @Test
    public void testOrigin() throws Exception {
        SshRsaCrypto rsa = new SshRsaCrypto();

        String pubPath = "res/pub.key";
        String priPath = "res/pri.key";

        PublicKey publicKey = rsa.readPublicKey(rsa.slurpPublicKey(getPathFileString(pubPath)));
        PrivateKey privateKey = rsa.readPrivateKey(rsa.slurpPrivateKey(getPathFileString(priPath)));

        showInfo(publicKey,privateKey);
    }


    public static void showInfo(PublicKey pub, PrivateKey pri) throws Exception {
        String message = "Hello World!!1!";
        byte[] cipherText = encrypt(message, pub);

        System.out.println("原文：" + message);
        System.out.println("密文：" + Hex.toHexString(cipherText));
        System.out.println("解密：" + decrypt(cipherText, pri));

        System.out.println();

        // 私匙加密，公匙解密
        byte[] reverData = encrypt(message, pri);

        System.out.println("原文：" + message);
        System.out.println("密文：" + Hex.toHexString(reverData));
        System.out.println("解密：" + decrypt(reverData, pub));
    }

    public static String getPathFileString(String path) throws IOException {
        return FileUtils.readFileToString(new File(Demo.class.getResource(path).getFile()),"UTF-8");
    }
    private static byte[] encrypt(String text, Key key) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(text.getBytes());
    }

    private static String decrypt(byte[] text, Key key) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(text));
    }
}
