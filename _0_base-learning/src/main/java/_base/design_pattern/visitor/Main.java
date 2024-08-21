package _base.design_pattern.visitor;

import _base.design_pattern.visitor.active.impl.CEOVisitor;
import _base.design_pattern.visitor.active.impl.CTOVisitor;
import _base.design_pattern.visitor.passive.Staff;
import _base.design_pattern.visitor.passive.sub.Engineer;
import _base.design_pattern.visitor.passive.sub.Manager;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/3/28
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class Main {

    public static void main(String[] args) {

        List<Staff> staffs = new ArrayList<>();

        staffs.add(new Manager("经理-A"));
        staffs.add(new Manager("经理-B"));
        staffs.add(new Engineer("工程师-A"));
        staffs.add(new Engineer("工程师-B"));
        staffs.add(new Engineer("工程师-C"));
        staffs.add(new Engineer("工程师-D"));


        CEOVisitor ceoVisitor = new CEOVisitor();
        for (Staff staff : staffs) {
            staff.accept(ceoVisitor);
        }

        System.out.println();

        CTOVisitor ctoVisitor = new CTOVisitor();
        for (Staff staff : staffs) {
            staff.accept(ctoVisitor);
        }
    }
}
