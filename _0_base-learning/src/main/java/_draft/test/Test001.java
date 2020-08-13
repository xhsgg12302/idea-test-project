package _draft.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test001 {
    public static void main(String[] args) {

        List<entityBean> lists = new ArrayList<>();
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

        Collections.sort(lists, new Comparator<entityBean>() {
            @Override
            public int compare(entityBean o1, entityBean o2) {
                return Integer.parseInt(o1.getPay_chanel()) - Integer.parseInt(o2.getPay_chanel());
            }
        });
    }
}
