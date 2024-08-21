package _base.design_pattern.visitor.active;

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
public interface Visitor {

    void visit(Engineer engineer);

    void visit(Manager manager);
}
