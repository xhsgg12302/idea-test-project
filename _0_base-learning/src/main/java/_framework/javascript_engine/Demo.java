package _framework.javascript_engine;

import org.junit.Before;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileReader;
import java.net.URL;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2022/8/16
 *                          @since  1.0
 *                          @author 12302
 *
 *
 *                          https://github.com/nuysoft/Mock/wiki
 * 
 */
public class Demo {

    private ScriptEngine engine;

    @Before
    public void before(){
        engine = new ScriptEngineManager().getEngineByName("nashorn");
        try{
            URL mock = Demo.class.getClassLoader().getResource("_framework/javascript_engine/res/mock.js");
            URL extend = Demo.class.getClassLoader().getResource("_framework/javascript_engine/res/extends.js");
            engine.eval(new FileReader(mock.getFile()));
            engine.eval(new FileReader(extend.getFile()));
            engine.eval("var Random = Mock.Random");
        }catch (Exception e){ e.printStackTrace(); }
    }

    @Test
    public void getTemplate() throws ScriptException {
        String tmp = "var data = Mock.mock({\n" +
                "    // 属性 list 的值是一个数组，其中含有 1 到 10 个元素\n" +
                "    'list|1-10': [{\n" +
                "        // 属性 id 是一个自增数，起始值为 1，每次增 1\n" +
                "        'id|+1': 1\n" +
                "    }]\n" +
                "})";
        engine.eval(tmp);
        System.out.println(engine.eval("JSON.stringify(data, null, 4)"));

    }

    @Test
    public void getName() throws ScriptException {
        for (int i = 0; i < 10; i++) {
            //System.out.println(engine.eval("Random.name()"));
            System.out.println(engine.eval("Random.cname()"));
        }
    }

    @Test
    public void getAddress() throws ScriptException {
        System.out.println(engine.eval("Random.region()"));
        System.out.println(engine.eval("Random.province()"));
        System.out.println(engine.eval("Random.city()"));
        System.out.println(engine.eval("Random.city(true)"));
        System.out.println(engine.eval("Random.county()"));
        System.out.println(engine.eval("Random.county(true)"));
        System.out.println(engine.eval("Random.zip()"));
    }

    @Test
    public void getText() throws ScriptException {
        for (int i = 0; i < 1; i++) {
            //System.out.println(engine.eval("Random.name()"));
            System.out.println(engine.eval("Random.paragraph()"));
            System.out.println(engine.eval("Random.cparagraph()"));


            System.out.println(engine.eval("Random.sentence()"));
            System.out.println(engine.eval("Random.csentence()"));


            System.out.println(engine.eval("Random.word()"));
            System.out.println(engine.eval("Random.cword()"));

            System.out.println(engine.eval("Random.title()"));
            System.out.println(engine.eval("Random.ctitle()"));

            System.out.println(engine.eval("Random.word()"));
            System.out.println(engine.eval("Random.cword()"));
        }
    }

    @Test
    public void getEmail() throws ScriptException {
        for (int i = 0; i < 10; i++) {
            //Random.email()
            //Random.email( domain )
            System.out.println(engine.eval("Random.email('126.com')"));
        }
    }

    @Test
    public void getphoneNum() throws ScriptException {
        for (int i = 0; i < 10; i++) {
            System.out.println(engine.eval("Random.phone()"));
        }
    }

    @Test
    public void getIp() throws ScriptException {
        for (int i = 0; i < 10; i++) {
            //Random.ip()
            System.out.println(engine.eval("Random.ip()"));
        }
    }

    public void getPerson()throws ScriptException {
        // 姓名
        System.out.println(engine.eval(""));
        // 年龄
        System.out.println(engine.eval(""));
        // 身份证号
        System.out.println(engine.eval(""));
        // 生日
        System.out.println(engine.eval(""));
        // 邮箱
        System.out.println(engine.eval(""));
        // 电话号码
        System.out.println(engine.eval(""));
        // 地址
        System.out.println(engine.eval(""));
        // 详细描述
        System.out.println(engine.eval(""));
        //
        System.out.println(engine.eval(""));
    }

}
