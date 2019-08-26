package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2018/12/27 10:54
 * @Description:
 */
public class Test11 {
    /**
     * 根据值找key,查询用
     *
     * @param map
     * @param value
     * @return
     */
    public static String getKeyByValue(Map<String, String> map, String value) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * 替换查询条件工具
     *
     * @param pro
     * @param val
     * @return
     */
    public static List<String> replaceValue(String pro, String val) {
        List<String> listStr = new ArrayList<>();
        String[] arrPro = pro.split(",");
        String[] arrVal = val.split(",");
        StringBuffer proSb = new StringBuffer();
        StringBuffer valSb = new StringBuffer();
        boolean flag = false;
        for (int i = 0; i < arrPro.length; i++) {
            if ("functionName".equals(arrPro[i])) {
                arrVal[i] = getKeyByValue(methodMap, arrVal[i]);
                flag = true;
            }
            if ("operateEvent".equals(arrPro[i])) {
                if ("列入".equals(arrVal[i]) || "移除".equals(arrVal[i])) {
                    arrVal[i] = getKeyByValue(funcMap, arrVal[i]);
                } else {  //删除operateEvent 和 对应的列
                    arrPro[i] = "default";
                    if (!flag) {
                        arrPro[i] = "functionName";
                        arrVal[i] = getKeyByValue(otherMap, arrVal[i]);
                    }
                }
            }

        }
        for (int i = 0; i < arrPro.length; i++) {
            proSb.append(arrPro[i] + ",");
        }
        for (int i = 0; i < arrVal.length; i++) {
            valSb.append(arrVal[i] + ",");
        }
        listStr.add(proSb.toString());
        listStr.add(valSb.toString());
        return listStr;
    }


    /**
     * 静态功能名称map
     */
    public static Map<String, String> methodMap = new HashMap() {{
        put("blackList", "黑名单管理");
        put("visitorList", "访客车辆管理");
        put("cardType", "cardType管理");
        put("cardOpen", "cardOpen管理");
        put("car", "car管理");
        put("carOwner", "carOwner管理");
        put("sendRenewal", "sendRenewal管理");
        put("updateCar", "updateCar管理");
        put("sendCardRefund", "sendCardRefund管理");
    }};

    /**
     * 黑名单事件map
     */
    public static Map<String, String> funcMap = new HashMap() {{
        put("1", "列入");
        put("2", "移除");
        put("3", null);
    }};

    /**
     * 其他map
     */
    public static Map<String, String> otherMap = new HashMap() {{
        put("visitorList", "添加");
        put("cardType", "cardType事件");
        put("cardOpen", "cardOpen事件");
        put("car", "car事件");
        put("carOwner", "carOwner事件");
        put("sendRenewal", "sendRenewal事件");
        put("updateCar", "updateCar事件");
        put("sendCardRefund", "sendCardRefund事件");
    }};

    /*public static void main(String[] args) {
        String origin = "name,sex,functionName,operateEvent";    //operateEvent
        String val = "wang,12,黑名单管理,添加";
        replaceValue(origin, val);
    }*/

    /*public static void main(String []args) {
        String countSumStr = funcMap.get("1")==null?"0":"2";
        System.out.println(countSumStr);
    }*/


    //    public static void main(String[] args) {
//        test.MyThread a = new test.MyThread("client");
//        a.start();
//        /*try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }*/  //sleep 放弃cpu资源，但是不会放锁。
//        for(int i = 0; i< 1000;i++){
//           // System.out.println(i);
//        }
//    }
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        for(int j = 0; j<5;j++){
            list.add(j);
        }
        for(int i=0; i<list.size();i++){
            System.out.println(list.get(i));
            list.remove(i);
        }
        System.out.println(list.size());
//        for(int k=0;k<list.size();k++){
//            System.out.println(list.get(k));
//        }
    }
}

class MyThread extends Thread {
    public MyThread(String c) {
        super(c);
    }

    public void run() {
        System.out.println("Hello");
    }
}
