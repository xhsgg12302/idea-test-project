package _base.encryption.rsa;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-12-27
 * @Desc:
 */
public class Test {

    class RSAClient {

        //使用公钥对数据进行解密
        public byte[] decryptByPublicKey() throws Exception {

            byte[] data = readFile("ServerData.dat");

            byte[] key = readFile("D:/encryptedFile/publicKey.dat");

            //构建公钥规范

            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);

            KeyFactory keyFactory = null;

            Key publicKey = null;

            keyFactory = KeyFactory.getInstance("RSA");

            publicKey = keyFactory.generatePublic(x509KeySpec); // 取得公钥

            //根据算法名称返回指定转换的Cipher对象

            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            // 初始化 Cipher.DECRYPT_MODE表示解密

            cipher.init(Cipher.DECRYPT_MODE, publicKey);

            //完成解密返回包含缓冲区

            return cipher.doFinal(data);

        }

        //使用公钥加密数据
        public void encryptOfClient(byte[] data) throws Exception {

            byte[] key = readFile("D:/encryptedFile/publicKey.dat");

            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);

            KeyFactory keyFactory = null;

            Key publicKey = null;

            keyFactory = KeyFactory.getInstance("RSA");

            publicKey = keyFactory.generatePublic(x509KeySpec);

            // 下面是加密操作
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            writeFile(cipher.doFinal(data), "ClientData.dat");//保存密文

        }

        //验证数字签名
        public boolean VerifyDigitalSignature() throws Exception {

            byte[] data = readFile("ServerData.dat");//读取RSAServert加密的密文文件

            byte[] publicKey = readFile("D:/encryptedFile/publicKey.dat");//公钥

//读取RSAServer生成数字签名文件

            byte[] sign = readFile("D:/encryptedFile/SignData.dat");

            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);

            KeyFactory keyFactory = null;

            PublicKey pubKey = null;

            keyFactory = KeyFactory.getInstance("RSA");

            pubKey = keyFactory.generatePublic(keySpec); //得到公钥对象

            Signature signature = Signature.getInstance("MD5withRSA");

            signature.initVerify(pubKey); /*初始化此用于验证的公钥对象,如果密钥无效，则会抛出invalidkeyException异常*/

            signature.update(data); //使用指定的 byte 数组更新要签名或验证的数据

            return signature.verify(sign); //验证传入的签名有效性,有效返回true,反之false

        }
    }

    class RSAServer {
        //使用公钥对数据进行解密
        public byte[] decryptByPublicKey() throws Exception {

            byte[] data = readFile("ServerData.dat");

            byte[] key = readFile("D:/encryptedFile/publicKey.dat");

            //构建公钥规范

            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);

            KeyFactory keyFactory = null;

            Key publicKey = null;

            keyFactory = KeyFactory.getInstance("RSA");

            publicKey = keyFactory.generatePublic(x509KeySpec); // 取得公钥

            //根据算法名称返回指定转换的Cipher对象

            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            // 初始化 Cipher.DECRYPT_MODE表示解密

            cipher.init(Cipher.DECRYPT_MODE, publicKey);

            //完成解密返回包含缓冲区

            return cipher.doFinal(data);

        }
        //使用公钥对数据进行解密
        public byte[] decryptByPrivateKey() throws Exception {
            return null;
        }

        //根据私钥生成数字签名
        public void GenerateDigitalSignature() throws Exception {

            //获取私钥

            byte[] privateKey = readFile("D:/encryptedFile/PrivateKey.dat");

            byte[] serverData = readFile("ServerData.dat");//密文

            // 构造PKCS8EncodedKeySpec对象

            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);

            KeyFactory keyFactory = null; //声明

            PrivateKey priKey = null;

            keyFactory = KeyFactory.getInstance("RSA");

            priKey = keyFactory.generatePrivate(pkcs8KeySpec); //得到私钥

            //指定证书算法为MD5withRSA

            Signature signature = Signature.getInstance("MD5withRSA");

            signature.initSign(priKey);

            signature.update(serverData);

            //以文件形式保存数字签名

            writeFile(signature.sign(), "D:/encryptedFile/SignData.dat");

        }

        //生成密钥对方法
        public void generateKey() {

            KeyPairGenerator kpg = null;

            try {

//KeyPairGenerator类用于生成密钥对，基于RSA算法生成对象

                kpg = KeyPairGenerator.getInstance("RSA");

            } catch (NoSuchAlgorithmException e) {

                e.printStackTrace();

            }

            KeyPair kp = kpg.generateKeyPair();

            //得到 公钥

            PublicKey publicKey = kp.getPublic();

            writeFile(publicKey.getEncoded(), "D:/encryptedFile/publicKey.dat");//将数据以文件的形式保存下来

            PrivateKey privateKey = kp.getPrivate();// 得到私钥

            writeFile(privateKey.getEncoded(), "D:/encryptedFile/PrivateKey.dat");
        }

        public void privateKeyEncryption(byte[] data) throws Exception {

            // 读取私钥数据(以byte数组形式返回)
            byte[] by = readFile("D:/encryptedFile/PrivateKey.dat");

            //使用得到byte数组构造PKCS8EncodedKeySpec对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(by);

            KeyFactory keyFactory = null;

            Key privateKey = null;

            keyFactory = KeyFactory.getInstance("RSA");

            //使用KeyFactory类的generatePrivate()方法获取私钥对象 pkcs8KeySpec 表示根据提供的密钥材料生成私钥对象

            privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

            // 对数据加密 Cipher.ENCRYPT_MODE表示加密

            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

            cipher.init(Cipher.ENCRYPT_MODE, privateKey);

            writeFile(cipher.doFinal(data), "ServerData.dat");

        }
    }


    public void testClient() throws Exception {

        //实例化

        RSAClient client = new RSAClient();

        RSAServer server = new RSAServer();

        String msg = "客户端加密后发送的信息";

        client.encryptOfClient(msg.getBytes());//调用方法对数据进行加密

        byte[] data = server.decryptByPrivateKey();//调用RSAServert的方法解密

        System.out.println("客户端加密发送的数据：" + msg);

        System.out.println("服务端解密后数据：" + new String(data));

    }

    public void testServer() throws Exception {

        String msg = "这是服务端加密后发送的数据";

        //实例化当前类
        RSAServer server = new RSAServer();

        server.generateKey();// 调用方法生成密钥对

        server.privateKeyEncryption(msg.getBytes());// 加密发送数据

        server.GenerateDigitalSignature();// 生成数字签名

        RSAClient client = new RSAClient();// 实例化客户端类

        byte[] by = null; //声明数组

        //调用RSAClient类的方法校对数字签名是否有效
        if (client.VerifyDigitalSignature()) {

            by = client.decryptByPublicKey();

        }

        System.out.println("服务端加密发送的数据：" + msg);

        System.out.println("客户端解密的数据：" + new String(by));

    }

    public static void createrKeyFile() {

        KeyPairGenerator kpg = null;

        try {

            kpg = KeyPairGenerator.getInstance("RSA");

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();

        }

        KeyPair kp = kpg.generateKeyPair();//用于生成密钥对

        PublicKey publicKey = kp.getPublic();//得到公钥

        createFile(publicKey.getEncoded(), "D:is/publicKey.dat");
        //得到私钥

        PrivateKey privateKey = kp.getPrivate();

        createFile(privateKey.getEncoded(), "D:is/privateKey.dat");

    }

    public void writeFile(byte[] data, String fileName) {

        //省略代码

    }

    //编写readFile()方法读取文件并以byte[]返回数据

    public byte[] readFile(String fileName) {

        //省略代码

        return null;
    }

    private static void createFile(byte[] encoded, String s) {
    }
}
