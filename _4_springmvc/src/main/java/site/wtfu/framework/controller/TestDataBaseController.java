package site.wtfu.framework.controller;

import site.wtfu.framework.entity.Employee;
import site.wtfu.framework.entity.XMLReturnObject;
import site.wtfu.framework.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/data")
public class TestDataBaseController {

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
        return new XMLReturnObject(200,"success",new Employee(12,"wang"));
    }

}
