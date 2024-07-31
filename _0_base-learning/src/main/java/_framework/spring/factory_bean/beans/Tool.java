package _framework.spring.factory_bean.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

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
@AllArgsConstructor
public class Tool {

    @Autowired
    private BeanFactory beanFactory;

    private int id;

    public Tool(){}

    public Tool(int id){
        this.id = id;
    }
}
