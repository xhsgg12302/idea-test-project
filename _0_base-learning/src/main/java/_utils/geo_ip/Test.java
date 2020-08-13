/**
* Copyright 2018 qmhd.con.cn Inc. All Rights Reserved. 
* @Title Test.java
* @Package com.elizabeth.phoneparse
* @Description: TODO:
* @author eric
* @date 2018年8月15日下午5:52:53
* @version V1.0
*/
package _utils.geo_ip;

import java.util.Locale;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;

/**
 * @Title Test.java
 * @Package com.elizabeth.phoneparse
 * @Description: TODO:
 * @author eric
 * @date 2018年8月15日下午5:52:53
 * @version V1.0
 */
public class Test {
  public static void main(String[] args) {
    PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
    PhoneNumberOfflineGeocoder phoneNumberOfflineGeocoder = PhoneNumberOfflineGeocoder.getInstance();

    String language ="CN";
    PhoneNumber referencePhonenumber = null;

    String phoneNum = "+8618294255755";
    
    try {
        referencePhonenumber = phoneUtil.parse(phoneNum, language);
      
    } catch (NumberParseException e) {
        e.printStackTrace();
    }
    //手机号码归属城市 referenceRegion 
    String referenceRegion = phoneNumberOfflineGeocoder.getDescriptionForNumber(referencePhonenumber,Locale.CHINA);
    System.out.println(referenceRegion);
  }
}
