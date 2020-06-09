package groupId;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

public class GUIFrame {

    private static void createAndShowGUI(byte[] data) {
        Assert.assertTrue("data invalid", data != null && data.length > 0);
        // 确保一个漂亮的外观风格
        JFrame.setDefaultLookAndFeelDecorated(true);

        // 创建及设置窗口
        JFrame frame = new JFrame("Picture Preview");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 添加 "Hello World" 标签
        //JLabel label = new JLabel("Hello World");
        ImageIcon temp = new ImageIcon(data);
        JLabel image = new JLabel(temp);
        frame.getContentPane().add(image);
        frame.setPreferredSize(new Dimension(temp.getIconWidth(),temp.getIconHeight()));

        // 显示窗口
        frame.pack();
        UIUtils.centerFrameOnScreen(frame);
        frame.setVisible(true);
    }

    public static void showImage(byte[] bytes){
        SwingUtilities.invokeLater(()->createAndShowGUI(bytes));
    }


    public static void main(String[] args) {
        //SwingUtilities.invokeLater(()->createAndShowGUI("_8_wechat-image-conversion/src/main/resources/01300000164186121366756803686.jpg"));
    }

    //一闪而退，why?
    @Test
    public void test(){
        //SwingUtilities.invokeLater(()->createAndShowGUI("_8_wechat-image-conversion/src/main/resources/01300000164186121366756803686.jpg"));
    }
}
