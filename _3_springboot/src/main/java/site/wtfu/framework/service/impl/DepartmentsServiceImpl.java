package site.wtfu.framework.service.impl;

import org.springframework.transaction.annotation.Transactional;
import site.wtfu.framework.beans.Departments;
import site.wtfu.framework.mapper.DepartmentsMapper;
import site.wtfu.framework.service.IDepartmentsService;
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
public class DepartmentsServiceImpl extends ServiceImpl<DepartmentsMapper, Departments> implements IDepartmentsService {

    @Transactional(rollbackFor = Exception.class)
    public void testTransaction(){}
}
