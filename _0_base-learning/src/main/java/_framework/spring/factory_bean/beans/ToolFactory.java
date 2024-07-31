package _framework.spring.factory_bean.beans;

import lombok.Data;
import org.springframework.beans.factory.FactoryBean;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/6/26
 *                          @since  1.0
 *                          @author 12302
 *
 */
@Data
public class ToolFactory implements FactoryBean<Tool> {

    private int factoryId;

    private int toolId;

    @Override
    public Tool getObject() throws Exception {
        return new Tool(toolId);
    }

    @Override
    public Class<?> getObjectType() {
        return Tool.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
