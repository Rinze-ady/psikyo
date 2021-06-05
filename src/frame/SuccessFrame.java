package frame;

import com.javaGame.util.ImageChange;
import com.javaGame.util.MusicUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 游戏通关窗体
 */
public class SuccessFrame extends JFrame implements Runnable, KeyListener {
    /**双缓冲画布*/
    private Image memoryImage;
    /**双缓冲画笔*/
    private Graphics memoryGraphics;
    /**线程是否运行*/
    private boolean isRun = true;
    /**通关图片数组*/
    public static Image[] successImage;
    /**图片切换对象*/
    private ImageChange changeObj = new ImageChange(5);
    /**音乐播放对象*/
    private MusicUtil music = new MusicUtil("music/Caviare - Luna.mp3", false);



     public SuccessFrame(){
         this.setSize(950, 700);
         this.setVisible(true);
         this.setDefaultCloseOperation(3);
         this.setLocationRelativeTo(null);
         this.setResizable(false);

         this.addKeyListener(this);

         //创建950*700的双缓冲画布
         this.memoryImage = this.createImage(950, 700);

         //得到双缓冲画笔
         this.memoryGraphics = this.memoryImage.getGraphics();

         Thread thread = new Thread(this);
         thread.start();
     }

    @Override
    public void run() {
         while(isRun){
             this.drawFrame(this.getGraphics());

             try {
                 Thread.sleep(20);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }

    }


    /**
     * 绘制窗体
     * @param g 窗体画笔
     */
    private void drawFrame(Graphics g){
         Image image = changeObj.change(successImage);

         this.memoryGraphics.drawImage(image, 0, 0, null);

         g.drawImage(this.memoryImage, 0, 0, this);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_ESCAPE){
            music.stopMusic();

            isRun = false;
            this.dispose();
            new MenuFrame();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
