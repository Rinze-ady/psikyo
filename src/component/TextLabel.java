package component;

import javax.swing.*;
import java.awt.*;

/**
 * 文本标签类
 */
public class TextLabel extends JLabel {
    public TextLabel(String info, int x, int y, JFrame frame){
        this.setText(info);
        this.setBounds(x, y, 200, 30);
        this.setFont(new Font("黑体", Font.BOLD, 30));
        this.setForeground(Color.blue);
        frame.add(this);
    }

}
