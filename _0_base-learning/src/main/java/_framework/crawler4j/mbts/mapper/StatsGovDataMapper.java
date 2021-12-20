package _framework.crawler4j.mbts.mapper;

import _framework.crawler4j.mbts.StatsGovData;

import java.util.List;
import java.util.Map;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2021/12/16
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public interface StatsGovDataMapper {

    List<Map> selectByName(String name);

    int insertBatch(List<StatsGovData> list);
    
}
