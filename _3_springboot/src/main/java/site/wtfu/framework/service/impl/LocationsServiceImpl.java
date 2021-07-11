package site.wtfu.framework.service.impl;

import site.wtfu.framework.beans.Locations;
import site.wtfu.framework.mapper.LocationsMapper;
import site.wtfu.framework.service.ILocationsService;
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
public class LocationsServiceImpl extends ServiceImpl<LocationsMapper, Locations> implements ILocationsService {

}
