package _draft.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestStatistic {

    public static void main(String [] args){

        TestBean t1 = new TestBean("老师",null,null,"语文",null,null,null,"男",new BigDecimal(24));
        TestBean t2 = new TestBean("老师",null,null,"语文",null,null,null,"女",new BigDecimal(20));
        TestBean t3 = new TestBean("老师",null,null,"数学",null,null,null,"男",new BigDecimal(26));
        TestBean t4 = new TestBean("老师",null,null,"数学",null,null,null,"男",new BigDecimal(21));
        TestBean t5 = new TestBean("校长",null,null,"数学",null,null,null,"女",new BigDecimal(26));
        TestBean t6 = new TestBean("学生",null,null,"英语",null,null,null,"男",new BigDecimal(22));
        TestBean t7 = new TestBean("学生",null,null,"数学",null,null,null,"女",new BigDecimal(27));

        List<TestBean> lists = new ArrayList<>();

        lists.add(t1);lists.add(t2);lists.add(t3);lists.add(t4);lists.add(t5);lists.add(t6);lists.add(t7);

        //处理month and year
        List<TestBean> month = new ArrayList<>();
        List<TestBean> season = new ArrayList<>();
        List<TestBean>  year = new ArrayList<>();
        List<TestBean>  all = new ArrayList<>();

        for(TestBean  tb: lists){
            String style = tb.getdType();
            switch (style){
                case "老师" : month.add(tb);break;
                case "校长" :season.add(tb);break;
                case "学生" :year.add(tb);break;
                default:break;
            }
        }
        if(month.size()>0){
            dealWithLists(month);
            TestBean tt = month.get(0);
            month.add(new TestBean(null,null,null,"合计",tt.getdChanelMod(),tt.getdChanelSize(),getSum(month),tt.getdResource(),tt.getdIncome()));
            all.addAll(month);
        }
        if(season.size()>0){
            dealWithLists(season);
            TestBean tt1 = season.get(0);
            season.add(new TestBean(null,null,null,"合计",tt1.getdChanelMod(),tt1.getdChanelSize(),getSum(season),tt1.getdResource(),tt1.getdIncome()));
            all.addAll(season);
        }
        if(year.size()>0){
            dealWithLists(year);
            TestBean tt2 = year.get(0);
            year.add(new TestBean(null,null,null,"合计",tt2.getdChanelMod(),tt2.getdChanelSize(),getSum(year),tt2.getdResource(),tt2.getdIncome()));
            all.addAll(year);
        }

        for(TestBean tb :all){
            System.out.println(tb);
        }
        //dealWithLists(lists);
    }

    public static void dealWithLists(List<TestBean> lists){
        boolean Lxmod = true; //未修改
        boolean Fsmod = true;

        int typeNumber = 0;
        int chanelNumber = 0;
        BigDecimal chanelSum = null;

        List<String>  compareListType = new ArrayList<>();
        List<String>  compareListChanel = new ArrayList<>();


        for(int i =0 ;i<lists.size();i++){

            if(!compareListType.contains(lists.get(i).getdType())){ //
                compareListType.add(lists.get(i).getdType());
                lists.get(i).setdTypeMod(1);
                typeNumber =1;
                lists.get(i).setdTypeSize(typeNumber);
            }else{
                lists.get(i).setdTypeMod(0);
                lists.get(i).setdTypeSize(++typeNumber);
                if(i ==lists.size()-1 || !compareListType.contains(lists.get(i+1).getdType())){
                    lists.get(i-(typeNumber-1)).setdTypeSize(typeNumber);
                }
            }


            if(!compareListChanel.contains(lists.get(i).getdChanel())){ //
                compareListChanel.add(lists.get(i).getdChanel());
                lists.get(i).setdChanelMod(1);
                chanelNumber =1;
                chanelSum = lists.get(i).getdIncome();
                lists.get(i).setdChanelSize(chanelNumber);
                lists.get(i).setdChanelSum(chanelSum);
            }else{
                lists.get(i).setdChanelMod(0);
                lists.get(i).setdChanelSize(++chanelNumber);
                chanelSum = chanelSum.add(lists.get(i).getdIncome());
                if(i ==lists.size()-1 || !compareListChanel.contains(lists.get(i+1).getdChanel())){
                    lists.get(i-(chanelNumber-1)).setdChanelSize(chanelNumber);
                    lists.get(i-(chanelNumber-1)).setdChanelSum(chanelSum);
                }
            }
        }
    }

    public static BigDecimal getSum(List<TestBean> lists){
        BigDecimal bd = new BigDecimal(0);
        if(lists!=null) {
            for (TestBean temp : lists) {
                if (temp.getdChanelSum() != null) {
                    bd=bd.add(temp.getdChanelSum());
                }
            }
        }
        return bd;
    }
}
