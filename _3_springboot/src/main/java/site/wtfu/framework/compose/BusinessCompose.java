package site.wtfu.framework.compose;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import site.wtfu.framework.beans.Employees;
import site.wtfu.framework.beans.Locations;
import site.wtfu.framework.service.IDepartmentsService;
import site.wtfu.framework.service.IEmployeesService;
import site.wtfu.framework.service.IJobsService;
import site.wtfu.framework.service.ILocationsService;

import javax.annotation.Resource;

/**
 * Copyright 2022 wtfu.site Inc. All Rights Reserved.
 *
 * @author: 12302
 * @date: 2022-04-22
 */
@Component
public class BusinessCompose {

    @Resource
    private IEmployeesService employeesService;

    @Resource
    private IDepartmentsService departmentsService;

    @Resource
    private IJobsService jobsService;

    @Resource
    private ILocationsService locationsService;

    public Object queryEmp(){
        System.out.println(employeesService);
        System.out.println(departmentsService);
        System.out.println(jobsService);
        Employees employee = employeesService.getById(110);
        return employee;
    }

    @Transactional(rollbackFor = Exception.class)
    public Object updateSerial(){
        boolean save = employeesService.save(Employees.builder().firstName("eli").lastName("_w").build());
        boolean location = locationsService.save(Locations.builder().city("GS").postalCode("741020").build());
        return new Object();
    }
}
