package me.maupassant.springboot.service.impl;

import me.maupassant.springboot.beans.Jobs;
import me.maupassant.springboot.mapper.JobsMapper;
import me.maupassant.springboot.service.IJobsService;
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
public class JobsServiceImpl extends ServiceImpl<JobsMapper, Jobs> implements IJobsService {

}
