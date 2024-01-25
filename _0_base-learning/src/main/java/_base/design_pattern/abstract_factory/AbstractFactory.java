package _base.design_pattern.abstract_factory;

import _base.design_pattern.abstract_factory.color.Color;
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
public abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape);
}
