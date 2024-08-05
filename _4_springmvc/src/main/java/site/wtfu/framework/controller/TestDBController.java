package site.wtfu.framework.controller;

import site.wtfu.framework.entity.Employee;
import site.wtfu.framework.entity.XMLReturnObject;
import site.wtfu.framework.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/db")
public class TestDBController {

    //@Resource
    private IUserService userServiceImpl;

    @Autowired
    public void setUserServiceImpl(IUserService userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }


    @ResponseBody
    @RequestMapping(value = "query")
    public Object queryList(){

        System.out.println(userServiceImpl);
        List list = userServiceImpl.getList(new HashMap());
        return new HashMap(){{put(200,"success"); put("data", list);}};
    }

}
