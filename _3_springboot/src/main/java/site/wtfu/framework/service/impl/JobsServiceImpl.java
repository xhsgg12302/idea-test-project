package site.wtfu.framework.service.impl;

import site.wtfu.framework.beans.Jobs;
import site.wtfu.framework.mapper.JobsMapper;
import site.wtfu.framework.service.IJobsService;
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
