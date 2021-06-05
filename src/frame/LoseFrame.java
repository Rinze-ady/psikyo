package frame;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 游戏通关失败窗体
 */
public class LoseFrame extends JFrame implements KeyListener {
    public LoseFrame(){
        JLabel label = new JLabel(new ImageIcon("image/lose.jpg"));
        this.add(label);

        this.addKeyListener(this);

        this.setSize(950, 700);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setResizable(false) ;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_ESCAPE){
            this.dispose();
            new MenuFrame();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
