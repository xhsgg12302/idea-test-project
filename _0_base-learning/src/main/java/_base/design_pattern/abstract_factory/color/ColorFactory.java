package _base.design_pattern.abstract_factory.color;

import _base.design_pattern.abstract_factory.AbstractFactory;
import _base.design_pattern.abstract_factory.color.impl.Blue;
import _base.design_pattern.abstract_factory.color.impl.Green;
import _base.design_pattern.abstract_factory.color.impl.Red;
import _base.design_pattern.abstract_factory.shape.Shape;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2024/1/25
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class ColorFactory extends AbstractFactory {
    @Override
    public Shape getShape(String shapeType){
        return null;
    }

    @Override
    public Color getColor(String color) {
        if(color == null){ return null; }

        if(color.equalsIgnoreCase("RED")){
            return new Red();
        } else if(color.equalsIgnoreCase("GREEN")){
            return new Green();
        } else if(color.equalsIgnoreCase("BLUE")){
            return new Blue();
        }
        return null;
    }
}
