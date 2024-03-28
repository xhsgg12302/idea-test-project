package _base.design_pattern.visitor.active.impl;

import _base.design_pattern.visitor.active.Visitor;
import _base.design_pattern.visitor.passive.sub.Engineer;
import _base.design_pattern.visitor.passive.sub.Manager;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/3/28
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class CTOVisitor implements Visitor {

    @Override
    public void visit(Engineer engineer) {
        System.out.println("CTO -visit-> 工程师: " + engineer.name + ", 代码行数: " + engineer.getCodeLines());
    }

    @Override
    public void visit(Manager manager) {
        System.out.println("CTO -visit-> 经理: " + manager.name + ", 产品数量: " + manager.getProducts());
    }
}
