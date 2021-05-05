package me.maupassant.springboot.service.impl;

import me.maupassant.springboot.beans.Locations;
import me.maupassant.springboot.mapper.LocationsMapper;
import me.maupassant.springboot.service.ILocationsService;
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
