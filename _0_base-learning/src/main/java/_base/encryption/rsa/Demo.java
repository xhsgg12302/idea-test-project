package _base.encryption.rsa;


import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.util.Base64;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
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
        java.security.Security.addProvider( new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }


    public static void showInfo(RSAPublicKey pub, RSAPrivateKey pri) throws Exception {
        // 公匙加密，私匙解密
        String plainText = "13156568985^233332222";
        byte[] cipherData = RSA.encrypt(pub, plainText.getBytes());
        byte[] res = RSA.decrypt(pri,cipherData);

        String restr = new String(res);
        System.out.println("原文：" + plainText);
        System.out.println("密文：" + new String(Base64.encode(cipherData)));
        System.out.println("解密：" + restr);

        System.out.println();

        // 私匙加密，公匙解密
        byte[] revdata = RSA.encrypt(pri,plainText.getBytes());
        System.out.println("原文：" + plainText);
        System.out.println("密文：" + new String(Base64.encode(revdata)));
        System.out.println("解密：" + new String(RSA.decrypt(pub,revdata)));
    }

    public static String getPathFileString(String path) throws IOException {
        return FileUtils.readFileToString(new File(Demo.class.getResource(path).getFile()),"UTF-8");
    }

    @Test
    public void genKeyPair(){
        RSA.genKeyPair();
    }

    @Test
    public void testDemoPath(){
        try {
            String pubPath = "res/demo/token_public.key";
            String priPath = "res/demo/token_private.key";
            RSAPublicKey pubKey = RSA.loadPublicKeyByStr(getPathFileString(pubPath));
            RSAPrivateKey priKey = RSA.loadPrivateKeyByStr(getPathFileString(priPath));
            showInfo(pubKey,priKey);
        } catch (Exception ex) { ex.printStackTrace();}
    }

    @Test
    public void testGenKeyPairPath(){
        try {
            String pubPath = "res/genKeyPair/pub.key";
            String priPath = "res/genKeyPair/pri.key";
            RSAPublicKey pubKey = RSA.loadPublicKeyByStr(getPathFileString(pubPath));
            RSAPrivateKey priKey = RSA.loadPrivateKeyByStr(getPathFileString(priPath));
            showInfo(pubKey,priKey);
        } catch (Exception ex) { ex.printStackTrace();}
    }


    @Test
    public void testSsh_keygen(){
        try {
            String pubPath = "res/t_rsa_b_2048_m_pem/pub.key";
            String priPath = "res/t_rsa_b_2048_m_pem/pri.key";
            RSAPublicKey pubKey = RSA.loadPublicKeyByStr(getPathFileString(pubPath));
            RSAPrivateKey priKey = RSA.loadPrivateKeyByStr(getPathFileString(priPath));
            showInfo(pubKey,priKey);
        } catch (Exception ex) { ex.printStackTrace();}
    }





}
