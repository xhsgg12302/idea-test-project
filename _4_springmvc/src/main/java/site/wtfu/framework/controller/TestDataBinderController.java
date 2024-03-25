package site.wtfu.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import site.wtfu.framework.entity.Dept;
import site.wtfu.framework.entity.Employee;
import site.wtfu.framework.web.test.CustomDeptEditor;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2024/3/22
 *                          @since  1.0
 *                          @author 12302
 * 
 */
@Controller
@RequestMapping(value = "/bind")
public class TestDataBinderController {

    @RequestMapping("/simple")
    public ModelAndView simple(boolean b, ModelAndView view) {
        view.setViewName("databind");
        if(b) {
            view.addObject("attr", "b is true");
        } else {
            view.addObject("attr", "b is false");
        }
        return view;
    }

    @RequestMapping("/obj")
    public ModelAndView testObj(Employee e, ModelAndView view) {
        view.setViewName("databind");
        view.addObject("attr", e.toString());
        return view;
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Dept.class, new CustomDeptEditor());
    }
}
