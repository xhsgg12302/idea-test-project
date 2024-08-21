package _base.io.nio.Scalable_IO_in_Java.classic_server_socker_loop;

import java.awt.*;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/5/1
 *                          @since  1.0
 *                          @author 12302
 *
 */
import java.awt.event.*;

public class SimpleAWTExample {

    public SimpleAWTExample() {
        // 创建窗口
        Frame frame = new Frame("简单AWT示例");
        frame.setSize(300, 150);
        frame.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) { System.exit(0);}});

        // 创建文本框
        TextField textField = new TextField(20);
        frame.add(textField);

        // 创建按钮
        Button button = new Button("点击我");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("Hello, World!");
            }
        });
        frame.add(button);

        // 设置布局
        frame.setLayout(new FlowLayout());

        // 显示窗口
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // 创建AWT示例对象
        new SimpleAWTExample();
    }
}

