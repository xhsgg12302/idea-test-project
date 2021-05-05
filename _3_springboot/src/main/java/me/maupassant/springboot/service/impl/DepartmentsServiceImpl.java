package me.maupassant.springboot.service.impl;

import me.maupassant.springboot.beans.Departments;
import me.maupassant.springboot.mapper.DepartmentsMapper;
import me.maupassant.springboot.service.IDepartmentsService;
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

}
