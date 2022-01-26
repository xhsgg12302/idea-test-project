package _base.encryption;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @author 12302
 * @date 2022/1/26
 * @since 1.0
 */
public class Demo {

    public static void main(String[] args) {
        try {
            String publicKey = System.getProperty("user.dir") + "/_0_base-learning/src/main/resources/pub.key";
            String privateKey = System.getProperty("user.dir") + "/_0_base-learning/src/main/resources/pri.key";

            java.security.Security.addProvider(
                    new org.bouncycastle.jce.provider.BouncyCastleProvider()
            );

            System.out.println("--------------公钥加密私钥解密过程-------------------");
            String plainText = "13156568985^233332222";
            String publicKeyStr = RSA.loadPublicKeyByFile(publicKey);
            RSAPublicKey rpubkey = RSA.loadPublicKeyByStr(publicKeyStr);

            // 公钥加密过程
            byte[] cipherData = RSA.encrypt(rpubkey, plainText.getBytes());
            
            // 私钥解密过程
            String privateKeyStr = RSA.loadPrivateKeyByFile(privateKey);
            RSAPrivateKey rprikey = RSA.loadPrivateKeyByStr(privateKeyStr,false);
            byte[] res = RSA.decrypt(rprikey,cipherData);
            String restr = new String(res);
            System.out.println("原文：" + plainText);
            System.out.println("密文：" + Base64.encode(cipherData));
            System.out.println("解密：" + restr);
            System.out.println();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
