package site.wtfu.framework.service.impl;

import site.wtfu.framework.beans.Employees;
import site.wtfu.framework.mapper.EmployeesMapper;
import site.wtfu.framework.service.IEmployeesService;
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
