package _base.exception;

import _draft.test.entityBean;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Placeholder {

    public static Map<String,String> getMap(){
        Map map = new HashMap<>();
        map.put("KEY","INIT");
        try{
            map.put("KEY","TRY");

            /*返回之前 finally中处理的map 可以当做object 的另一条引用。*/
            return map;
        }catch (Exception e){
            map.put("KEY","CATCH");
        }finally {
            map.put("KEY","FINALLY");
            map = null;
        }
        return map;
    }


    /**
     * 传递到函数里面的对象只是地址的一个复制，和原来的对象引用的是同一个Object，可以通过method改值，或者数组的下标。
     * 但是如果直接复制的话，就是修改了形参的指向，跟原来的对象无关了。也就是此后对原来的对象修改无效了。
     * @param entityBean
     * @return
     */
    public entityBean ObjectToNull(entityBean entityBean){
        entityBean = null;
        return entityBean;
    }

    @Test
    public void test(){
        System.out.println(getMap().get("KEY").toString());
    }

    @Test
    public void test2(){
        entityBean entityBean = new entityBean();
        System.out.println(entityBean);
        ObjectToNull(entityBean);
        System.out.println(entityBean);
    }
}
