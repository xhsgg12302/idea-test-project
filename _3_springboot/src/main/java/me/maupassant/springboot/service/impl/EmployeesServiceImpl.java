package me.maupassant.springboot.service.impl;

import me.maupassant.springboot.beans.Employees;
import me.maupassant.springboot.mapper.EmployeesMapper;
import me.maupassant.springboot.service.IEmployeesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 12302
 * @since 2021-05-05
 */
@Service
public class EmployeesServiceImpl extends ServiceImpl<EmployeesMapper, Employees> implements IEmployeesService {

}
