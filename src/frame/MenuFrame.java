package frame;

import com.javaGame.util.MusicUtil;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 游戏菜单窗体
 */
public class MenuFrame extends JFrame implements KeyListener {



    /**玩家选项*/
    private int item;
    /**光标图片*/
    private JLabel cursorLabel;
    /**音乐播放对象*/
    private MusicUtil music = new MusicUtil("music/inDark.mp3", false);



    public MenuFrame(){
        this.setLayout(null);

        //光标图片
        this.cursorLabel = new JLabel(new ImageIcon("image/menuRose.png"));
        cursorLabel.setBounds(90, 320, 121, 54);
        this.add(cursorLabel);

        //背景图片标签
        JLabel backImageLabel = new JLabel(new ImageIcon("image/menu.jpg"));
        backImageLabel.setBounds(0, -30, 950, 700);
        this.add(backImageLabel);

        this.addKeyListener(this);

        this.setSize(950, 700);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public static void main(String[] args){
        new MenuFrame();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //得到键盘码
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_DOWN){ //玩家按下向下键
            item ++;

            if(item > 2){
                this.item = 0;
            }
        }
        else if(code == KeyEvent.VK_UP){//玩家按下向上键
            item --;

            if(item < 0){
                item = 2;
            }
        }
        else if(code == KeyEvent.VK_ENTER){//玩家按下回车键
            this.option();
        }

        this.cursorLabel.setLocation(90, 320 + item*100);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * 玩家按下回车键后，执行的业务
     */
    private void option(){

        music.stopMusic();

        if(item == 0){//开始游戏
            this.dispose();
            new GameStartFrame();
        }
        else if(item == 1){//排行榜
           this.dispose();
           new SortFrame();
        }
        else if(item == 2   ){//游戏结束
            System.exit(0);
        }

    }


}
