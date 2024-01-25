package _base.design_pattern.abstract_factory.color.impl;

import _base.design_pattern.abstract_factory.color.Color;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2024/1/25
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class Blue implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Blue::fill() method.");
    }
}
