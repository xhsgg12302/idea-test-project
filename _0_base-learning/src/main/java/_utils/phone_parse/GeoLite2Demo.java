/**
 * Copyright 2018 qmhd.con.cn Inc. All Rights Reserved.
 *  @Title Test.java
 *  @Package com.elizabeth.geoip
 *  @Description: TODO:
 *  @author eric
 *  @date 2018年8月15日下午4:34:30
 *  @version V1.0
 */
package _utils.phone_parse;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.*;
import org.junit.Test;

import java.io.File;
import java.net.InetAddress;
import java.net.URL;

/**
 * @Title Test.java
 * @Package com.elizabeth.geoip
 * @Description: TODO:
 * @author eric
 * @date 2018年8月15日下午4:34:30
 * @version V1.0
 */
public class GeoLite2Demo {

    @Test
    public void test() throws Exception {
        // 创建 GeoLite2 数据库
        URL resource = GeoLite2Demo.class.getClassLoader().getResource("_utils/phone_parse/res/GeoLite2-City.mmdb");
        File database = new File(resource.getFile());
        // 读取数据库内容
        DatabaseReader reader = new DatabaseReader.Builder(database).build();
        InetAddress ipAddress = InetAddress.getByName("222.128.15.208");

        // 获取查询结果
        CityResponse response = reader.city(ipAddress);

        // 获取国家信息
        Country country = response.getCountry();
        System.out.println(country.getIsoCode());
        System.out.println(country.getName());
        System.out.println(country.getNames().get("zh-CN"));

        // 获取省份
        Subdivision subdivision = response.getMostSpecificSubdivision();
        System.out.println(subdivision.getName());
        System.out.println(subdivision.getIsoCode()); // '45'
        System.out.println(subdivision.getNames().get("zh-CN"));

        // 获取城市
        City city = response.getCity();
        System.out.println(city.getName());
        Postal postal = response.getPostal();
        System.out.println(postal.getCode());
        System.out.println(city.getNames().get("zh-CN"));
        Location location = response.getLocation();
        System.out.println(location.getLatitude());
        System.out.println(location.getLongitude());

    }
}
