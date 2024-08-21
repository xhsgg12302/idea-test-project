package site.wtfu.framework.entity.sub;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/5/4
 *                          @since  1.0
 *                          @author 12302
 *
 */
@Data
@JsonSerialize(using = ObjectSerializer.class)
public class Proxy {

    //protected String origin;

}
