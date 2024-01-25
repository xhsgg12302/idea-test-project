package _base.design_pattern.abstract_factory;

import _base.design_pattern.abstract_factory.color.Color;
import _base.design_pattern.abstract_factory.shape.Shape;
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
public class Main {
    public static void main(String[] args) {
        //获取形状工厂
        AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");

        // 会出现NPE
        // Color color = shapeFactory.getColor("everything");
        Shape shape1 = shapeFactory.getShape("CIRCLE");
        shape1.draw();

        //获取颜色工厂
        AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");

        Color color1 = colorFactory.getColor("RED");
        color1.fill();

    }
}
