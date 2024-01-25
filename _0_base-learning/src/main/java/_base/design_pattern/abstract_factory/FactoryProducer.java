package _base.design_pattern.abstract_factory;

import _base.design_pattern.abstract_factory.color.ColorFactory;
import _base.design_pattern.abstract_factory.shape.ShapeFactory;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2024/1/25
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class FactoryProducer {
    public static AbstractFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("SHAPE")){
            return new ShapeFactory();
        } else if(choice.equalsIgnoreCase("COLOR")){
            return new ColorFactory();
        }
        return null;
    }
}
