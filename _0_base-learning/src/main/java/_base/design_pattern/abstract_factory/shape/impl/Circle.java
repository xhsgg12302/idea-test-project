package _base.design_pattern.abstract_factory.shape.impl;

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
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
