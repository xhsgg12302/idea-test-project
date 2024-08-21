package _base.design_pattern.visitor.passive.sub;

import _base.design_pattern.visitor.active.Visitor;
import _base.design_pattern.visitor.passive.Staff;

import java.util.Random;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/3/28
 *                          @since 1.0
 *                          @author 12302
 *
 */
public class Manager extends Staff {

    public Manager(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    // 一年做的产品数量
    public int getProducts() {
        return new Random().nextInt(10);
    }
}
