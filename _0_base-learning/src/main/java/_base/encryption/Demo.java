package _base.encryption;

import _base.encryption.aes.AES;
import _base.encryption.des.DES;
import org.junit.Test;

public class Demo {

    @Test
    public void testDES(){

        String key = "U2yT4Q)2Ss+Li=Y)";
        String iv  = "2j=HC,#Au}VDE\\P:";
        iv  = "2=H,#Au}"; //des 8 byte
        String data = "hello world from 加密";


        String encrypt = DES.encrypt(data, key.getBytes(), iv.getBytes());
        System.out.println(encrypt);
        String decrypt = DES.decrypt(encrypt, key.getBytes(), iv.getBytes());
        System.out.println(decrypt);

    }

    @Test
    public void testAES(){
        String key = "U2yT4Q)2Ss+Li=Y)";
        String iv  = "2j=HC,#Au}VDE\\P:";
        //iv  = "2=H,#Au}"; //des 8 byte
        String data = "hello world from 加密";


        String encrypt = AES.encrypt(data, key.getBytes(), iv.getBytes());
        System.out.println(encrypt);
        String decrypt = AES.decrypt(encrypt, key.getBytes(), iv.getBytes());
        System.out.println(decrypt);
    }
}
