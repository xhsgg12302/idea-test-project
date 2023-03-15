package _base.encryption.des;

import _base.encryption.utils.CommonUtils;
import org.junit.Test;

import java.util.Base64;

public class Demo {

    @Test
    public void test(){
        String key = "123zaqwsx";
        key = "123!@#zaqXSWqwer";
        key = "error";

        String origin = "bjdNU2lGL1VOazB1RTU3dnhxODE4QT09";

        System.out.println(CommonUtils.byteArrayToString(Base64.getDecoder().decode(origin)));

        System.out.println(DES.decrypt(key,origin));
    }
}
