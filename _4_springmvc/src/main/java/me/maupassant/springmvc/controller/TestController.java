package me.maupassant.springmvc.controller;

import me.maupassant.springmvc.entity.Employee;
import me.maupassant.springmvc.entity.TestObject;
import me.maupassant.springmvc.entity.User;
import me.maupassant.springmvc.services.ITest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-07-14
 * @Desc:
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping("/testRb")
    @ResponseBody
    public Employee testRb(@RequestBody Employee e) {
        return e;
    }

    @RequestMapping("/testCustomObj")
    @ResponseBody
    public Employee testCustomObj(Employee e) {
        return e;
    }

    @RequestMapping("/testCustomObjWithRp")
    @ResponseBody
    public Employee testCustomObjWithRp(@RequestParam Employee e) {
        return e;
    }

    @RequestMapping("/testDate")
    @ResponseBody
    public Date testDate(Date date) {
        return date;
    }
    @RequestMapping("/sync")
    @ResponseBody
    public Object testSynchronized(){

        TestObject.work();
        return null;
    }

    @RequestMapping(value = "/ldt")
    @ResponseBody
    public User testLocalDateTime(@RequestBody User user){
        return user;
    }

    @RequestMapping("exception")
    @ResponseBody
    public String testException(){
        String abc = null;
        abc.equals("1");
        return "";
    }

    @Resource
    private ITest iTest;
    @RequestMapping(value = "/transaction")
    @ResponseBody
    public Object testTransaction(){
        User user = new User("909","world","hello");
        Integer result = iTest.testTransaction(user);
        return result;
    }


    /**
     * #测试 session跨域（一种session共享解决方案）
     *
     * 1,设置的domain （wtfu.site) 在控制台自动变化为（.wtfu.site）
     * 2,通过两个域名{
     *          http://www.wtfu.site:8080/test/cookie.do,
     *          http://sso.wtfu.site:8080/test/cookie.do
     *      }访问，浏览器确实可以将wtfu的cookie信息附入sso请求中。
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/cookie")
    @ResponseBody
    public String testCookie(HttpServletRequest request,HttpServletResponse response){
        boolean flag = false;
        request.getSession();
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("custom_global_session_id")){
                    flag = true;
                }
            }
        }
        if(!flag){
            Cookie cookie = new Cookie("custom_global_session_id", UUID.randomUUID().toString());
            cookie.setPath("/");
            cookie.setDomain("wtfu.site");
            response.addCookie(cookie);
        }
        return "Test Ok";
    }


    /**
     * # 测试 post URL带参数，和 body数据一起接收
     * @param employee
     * @return
     *
     * example1 : curl http://localhost:8080/test/postdataandparameter.do\?get1\=123\&get2\=456 -d '{"name":"eli","age":18}' -H 'Content-Type: application/json'
     *
     *      public String testPostDataAndParameter(String get1, String get2,@RequestBody Employee employee){}
     *      public String testPostDataAndParameter(@RequestParam String get1,@RequestParam String get2,@RequestBody Employee employee){}
     *
     *      As you see ， receiving url parameters via empty annotations has the same effect as @RequestParam
     */
    @RequestMapping(value = "/postdataandparameter",method = RequestMethod.POST)
    @ResponseBody
    public String testPostDataAndParameter(@RequestParam String get1,@RequestParam String get2,@RequestBody Employee employee){
        //System.out.println(get1);
        //System.out.println(get2);
        System.out.println(employee);
        return "Test Ok";
    }

    /**
     *  # 测试 post 默认编码 urlencode
     * @param employee
     * @return
     *
     * example1: curl http://localhost:8080/test/postDefault.do -d 'name=eli&age=18'
     *
     *      public String testPostDefault(Employee employee){}
     *
     *
     * example2: curl http://localhost:8080/test/postDefault.do\?get1\=123\&get2\=456 -d 'name=eli&age=18'
     *
     *      public String testPostDefault(String get1,String get2, Employee employee){}
     *      public String testPostDefault(@RequestParam String get1,@RequestParam String get2, Employee employee){}
     *
     *
     * additional note: url arguments also append to entity(employee)
     * example3: curl http://localhost:8080/test/postDefault.do\?get1\=123\&get2\=456\&age\=17 -d 'name=eli'
     *
     *      public String testPostDefault(@RequestParam String get1,@RequestParam String get2, Employee employee ){}
     */
    @RequestMapping(value = "/postDefault" ,method = RequestMethod.POST)
    @ResponseBody
    public String testPostDefault(@RequestParam String get1,@RequestParam String get2, Employee employee ){
        System.out.println(employee);
        return "Test OK";
    }
}
