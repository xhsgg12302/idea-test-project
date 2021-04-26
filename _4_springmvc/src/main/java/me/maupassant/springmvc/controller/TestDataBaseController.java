package me.maupassant.springmvc.controller;

import me.maupassant.springmvc.entity.Employee;
import me.maupassant.springmvc.entity.XMLReturnObject;
import me.maupassant.springmvc.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;

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
