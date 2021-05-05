package me.maupassant.springboot.beans;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
* <p>
*    
* </p>
*
* @author 12302
* @since 2021-05-05
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="Jobs对象", description="")
public class Jobs implements Serializable {

    private static final long serialVersionUID = 1L;

    private String jobId;

    private String jobTitle;

    private Integer minSalary;

    private Integer maxSalary;


}
