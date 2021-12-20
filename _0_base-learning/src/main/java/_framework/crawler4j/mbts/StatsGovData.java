package _framework.crawler4j.mbts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2021/12/15
 *                          @since  1.0
 *                          @author 12302
 * 
 */
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatsGovData implements Serializable {


    private static final long serialVersionUID = 3386582311261703988L;
    /**
     * 主键
     */
    private Integer sgdId;

    /**
     * 名称 collate utf8mb4_general_ci
     */
    private String sgdName;

    /**
     * 页面主键（唯一）
     */
    private Integer sgdDocId;

    /**
     * 父页面主键
     */
    private Integer sgdFatherDocId;

    /**
     * 深度
     */
    private int sgdDepth;

    /**
     * 编码统计用区划代码 collate utf8mb4_general_ci
     */
    private String sgdCode;

    /**
     * 城乡分类代码 collate utf8mb4_general_ci
     */
    private String sgdClassifierCode;

    /**
     * 行政数据来源url collate utf8mb4_general_ci
     */
    private String sgdSourceUrl;
    
    private String sgdFatherUrl;

    /**
     * 状态
     */
    private int sgdState;

    /**
     * 创建时间 collate utf8mb4_general_ci
     */
    private String sgdCreatedTime;

    /**
     * 备注 collate utf8mb4_general_ci
     */
    private String sgdRemark;


}
