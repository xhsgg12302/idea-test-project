package _draft.test;

import _framework.spring.bean_lifecycle.SpringBeanLifeCycleTest;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2022/8/30
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class TongRenTang2 {
    public static void main(String[] args) throws FileNotFoundException {


        URL config = TongRenTang2.class.getResource("./res/data.json");


        Assert.notNull(config);

        String rst = FileUtil.readString(config,"UTF-8");

        JSONArray objects = JSONArray.parseArray(rst);

        FileOutputStream fos = new FileOutputStream("/Users/stevenobelia/Desktop/tongrentang.xls");
        try {

            List<List<String>> rowAll = new ArrayList<List<String>>();
            List<String> row = CollUtil.newArrayList("区域", "门店性质", "门店名称", "地址", "联系人", "电话");
            rowAll.add(row);

            for (Object object : objects) {

                JSONObject each = (JSONObject) object;
                String cityName = each.getString("city");
                JSONArray array = each.getJSONObject("data").getJSONArray("data");

                for (int i = 0; i < array.size(); i++) {
                    JSONObject temp = (JSONObject) array.get(i);
                    List<String> rowItem = CollUtil.newArrayList(
                            String.valueOf(cityName),
                            String.valueOf(temp.get("name")),
                            String.valueOf(temp.get("jy")),
                            String.valueOf(temp.get("address")),
                            String.valueOf(temp.get("fullname")),
                            String.valueOf(temp.get("mobile"))
                    );
                    rowAll.add(rowItem);
                }
                rowAll.add(new ArrayList<>());
            }
            output(rowAll,fos);
        }catch (Exception e){e.printStackTrace();}

    }


    public static void output(List<List<String>> rowAll, FileOutputStream output){
        ExcelWriter writer = ExcelUtil.getWriter();
        writer.setColumnWidth(-1, 30);
        writer.setRowHeight(-1, 20);
        writer.write(rowAll);
        writer.flush(output);
        writer.close();
    }
}
