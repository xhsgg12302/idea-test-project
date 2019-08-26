package test;

import java.util.*;

public class TestList {
    public static void main(String [] args){
        List<entityBean>  lists = new ArrayList<>();
        lists.add(new entityBean("1",null,"1","1","20"));
        lists.add(new entityBean("1",null,"4","1","39"));
        lists.add(new entityBean("1",null,"4","7","60"));
        lists.add(new entityBean("1",null,"5","1","1"));
        lists.add(new entityBean("1",null,"5","6","1"));
        lists.add(new entityBean("1",null,"2","1","1"));
        lists.add(new entityBean("1",null,"2","6","1"));


        lists.add(new entityBean("3","自发卡","1","1","20"));
        lists.add(new entityBean("3","自发卡","4","1","39"));
        lists.add(new entityBean("3","拼凑卡","5","7","60"));
        lists.add(new entityBean("3","自发卡","4","1","1"));
        lists.add(new entityBean("3","拼凑卡","5","6","1"));
        lists.add(new entityBean("3","自发卡","2","1","1"));
        lists.add(new entityBean("3","拼凑卡","2","6","1"));


        //System.out.println(null == null);

        //printLists(lists);
        //dealWithLists(lists);
        groupByAttribute(lists);
    }

    public static void printLists(List<entityBean> lists){
        for(entityBean e : lists){
            System.out.println(e);
        }
    }

    public static void groupByAttribute(List<entityBean> lists){
        //先根据style 分组，成小list, 然后排序，合并
        List<entityBean> listSmall;
        Map<String,List<entityBean>> styleMap = new HashMap<>();
        for(entityBean eb: lists){
            if(null == styleMap.get(eb.getStyle())){
                listSmall = new ArrayList<>();
                listSmall.add(eb);
                styleMap.put(eb.getStyle(),listSmall);
            }else{
                styleMap.get(eb.getStyle()).add(eb);
            }
        }
        List<entityBean> listAll = new ArrayList<>();
        //对小的list进行chanel排序
        for(Map.Entry<String,List<entityBean>> entry:styleMap.entrySet()){
            List<entityBean> tempList = entry.getValue();
            Collections.sort(tempList, Comparator.comparing(entityBean::getPay_chanel));
            listAll.addAll(tempList);
        }

        for(entityBean e : listAll){
            System.out.println(e);
        }
    }



    //遍历lists取值，处理
    public static void dealWithLists(List<entityBean> lists){
        int payChanelNum = 0;
        Map<String,Integer> kindMap =new LinkedHashMap<>();
        List<String> listStr = new ArrayList<>();
        for(entityBean e : lists){
            if(!listStr.contains(e.getPay_chanel())) {
                listStr.add(e.getPay_chanel());
                payChanelNum++;//记录支付渠道类型数量
                kindMap.put(e.getPay_chanel(),1);
            }else{
                //找到当前的map,修改里面的值,当前值的数量+1;
                for (Map.Entry<String,Integer> entry : kindMap.entrySet()) {
                    String value = entry.getKey();
                    if (e.getPay_chanel().equals(value)){
                        kindMap.put(value,entry.getValue()+1);
                        break;
                    }
                }
            }
        }
        System.out.println(payChanelNum);
        for (Map.Entry<String, Integer> entry : kindMap.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
    }
}
