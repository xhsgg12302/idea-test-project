package site.wtfu.framework.entity.sub;

import lombok.Data;

import java.io.Serializable;

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
public class SubParam implements Serializable {

    private String target;

    private String rename;

    private String url;
}
