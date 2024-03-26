package site.wtfu.framework.controller;

import site.wtfu.framework.entity.Employee;
import site.wtfu.framework.entity.TestObject;
import site.wtfu.framework.entity.User;
import site.wtfu.framework.entity.XMLReturnObject;
import site.wtfu.framework.services.ITest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
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
public class TestOtherController {

    @RequestMapping("/hello")
    @ResponseBody
    public Object helloWorld(){
        return new HashMap(){{put("code", 200); put("desc", "success");}};
    }

    /**
     * 根据 Accept: "application/xml" | "application/json",
     * 返回对应的数据类型。
     * example:
     *      curl -H 'Accept: application/json' http://localhost:8080/test/helloxml
     *      curl -H 'Accept: application/xml' http://localhost:8080/test/helloxml
     *
     * @return
     */
    @RequestMapping(value = "/helloxml")
    @ResponseBody
    public XMLReturnObject helloWorldXml(){
        return new XMLReturnObject(200,"success",new Employee(12,"wang"));
    }




    @RequestMapping("/sync")
    @ResponseBody
    public Object testSynchronized(){

        TestObject.work();
        return null;
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
     *      * additional note: url arguments also append to entity(employee)
     *      * example3: curl http://localhost:8080/test/postDefault.do\?get1\=123\&get2\=456\&age\=17 -d 'name=eli'
     *      @RequestParam 可以将post 中的数据拿到  json 获取不到
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
     * curl -X GET http://localhost:11181/test/getMethodUrlEncoding.do -G -d 'get1=123' -d 'name=wtfu' -d 'age=2' -d 'id=932'
     * @param get1
     * @param name
     * @param employee
     * @param id
     * @return
     */
    @RequestMapping(value = "/getMethodUrlEncoding", method = RequestMethod.GET)
    @ResponseBody
    public String testGetParamsUrlEncoding(@RequestParam String get1, String name, Employee employee ,Long id){
        System.out.println(employee);
        return "Test OK";
    }

    /**
     *  curl -X POST http://localhost:11181/test/postMethodUrlEncoding.do  -d 'get1=123' -d 'name=wtfu' -d 'age=2' -d 'id=932'
     * @param get1
     * @param name
     * @param employee
     * @param id
     * @return
     */
    @RequestMapping(value = "/postMethodUrlEncoding" ,method = RequestMethod.POST)
    @ResponseBody
    public String testPostUrlEncoding(@RequestParam String get1, String name, Employee employee ,Long id){
        System.out.println(employee);
        return "Test OK";
    }

    /**
     * 会出现415，400
     * @param get1
     * @param name
     * @param employee
     * @param id
     * @return
     */
    @RequestMapping(value = "/getMethodJSON", method = RequestMethod.GET)
    @ResponseBody
    public String testGetParamsJSON(@RequestParam String get1, String name, String text, @RequestBody Employee employee ){ // ,@RequestBody Long id){
        System.out.println(employee);
        return "Test OK";
    }

    /**
     * curl -X POST  http://localhost:11181/test/postMethodJSON.do\?get1\=123 -H 'Content-Type: application/json' -d '{"name":"wtfu"}'   [400]
     * 删除 @RequestBody Long id 后正常，不过name 不会给String name; employee 中的好使
     * curl -X POST  http://localhost:11181/test/postMethodJSON.do\?get1\=123\&name\=hello -H 'Content-Type: application/json' -d '{"name":"wtfu"}'
     * JSON 中的会给employee, url 上的会给String name,
     * @param get1
     * @param name
     * @param employee
     * @param id
     * @return
     */
    @RequestMapping(value = "/postMethodJSON" ,method = RequestMethod.POST)
    @ResponseBody
    public String testPostJSON(@RequestParam String get1, String name,String text, @RequestBody Employee employee ){
        System.out.println(employee);
        return "Test OK";
    }


}
