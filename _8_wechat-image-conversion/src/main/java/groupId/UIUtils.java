package groupId;

import javax.swing.*;
import java.awt.*;

public class UIUtils {

    public static void centerFrameOnScreen(Window frame) {
        positionFrameOnScreen(frame, 0.5D, 0.5D);
    }

    public static void positionFrameOnScreen(Window frame, double horizontalPercent, double verticalPercent) {
        Rectangle s = frame.getGraphicsConfiguration().getBounds();
        Dimension f = frame.getSize();
        int w = Math.max(s.width - f.width, 0);
        int h = Math.max(s.height - f.height, 0);
        int x = (int)(horizontalPercent * (double)w) + s.x;
        int y = (int)(verticalPercent * (double)h) + s.y;
        frame.setBounds(x, y, f.width, f.height);
    }

    public static void positionFrameRandomly(Window frame) {
        positionFrameOnScreen(frame, Math.random(), Math.random());
    }

    public static void centerDialogInParent(Dialog dialog) {
        positionDialogRelativeToParent(dialog, 0.5D, 0.5D);
    }

    public static void positionDialogRelativeToParent(Dialog dialog, double horizontalPercent, double verticalPercent) {
        Container parent = dialog.getParent();
        if (parent == null) {
            centerFrameOnScreen(dialog);
        } else {
            Dimension d = dialog.getSize();
            Dimension p = parent.getSize();
            int baseX = parent.getX();
            int baseY = parent.getY();
            int x = baseX + (int)(horizontalPercent * (double)p.width);
            int y = baseY + (int)(verticalPercent * (double)p.height);
            Rectangle s = parent.getGraphicsConfiguration().getBounds();
            Rectangle r = new Rectangle(x, y, d.width, d.height);
            dialog.setBounds(r.intersection(s));
        }
    }

    public static JLabel createJLabel(String text, Font font) {
        JLabel result = new JLabel(text);
        result.setFont(font);
        return result;
    }

    public static JLabel createJLabel(String text, Font font, Color color) {
        JLabel result = new JLabel(text);
        result.setFont(font);
        result.setForeground(color);
        return result;
    }

    public static JButton createJButton(String label, Font font) {
        JButton result = new JButton(label);
        result.setFont(font);
        return result;
    }
}
