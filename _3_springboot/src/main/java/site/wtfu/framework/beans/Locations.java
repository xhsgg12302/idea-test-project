package site.wtfu.framework.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="Locations对象", description="")
public class Locations implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "location_id", type = IdType.AUTO)
    private Integer locationId;

    private String streetAddress;

    private String postalCode;

    private String city;

    private String stateProvince;

    private String countryId;


}
