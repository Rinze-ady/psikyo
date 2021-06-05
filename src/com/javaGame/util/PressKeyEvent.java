package com.javaGame.util;


import frame.GameStartFrame;
import role.Girl;
import role.GirlBullet;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 键盘事件处理类
 */
public class PressKeyEvent implements KeyListener {
//用户按哪个键而创建的四个boolean属性
    /**用户是否按下向上键*/
    public static boolean isUp;
    /**用户是否按下向下键*/
    public static boolean isDown;
    /**用户是否按下向左键*/
    public static boolean isLeft;
    /**用户是否按下向右键*/
    public static boolean isRight;

    /**用户是否按下开火键*/
    public static boolean isFire;


    //键盘事件处理方法
    @Override
    //键盘按下时触发
    public void keyPressed(KeyEvent e) {
        //得到键盘事件的键盘码
        int code = e.getKeyCode();
//        System.out.println(code);
        if (code == KeyEvent.VK_UP){  //向上键
            isUp = true;
        }
        if (code == KeyEvent.VK_DOWN){  //向下键
            isDown = true;
        }
        if (code == KeyEvent.VK_LEFT){  //向左键
            isLeft = true;
        }
        if (code == KeyEvent.VK_RIGHT){  //向右键
            isRight = true;
        }
        if (code == KeyEvent.VK_A){  //玩家按下开火键
            isFire = true;
        }


    }

    @Override
    //键盘弹起时触发
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP){  //向上键
            isUp = false;
        }
        if (code == KeyEvent.VK_DOWN){  //向下键
            isDown = false;
        }
        if (code == KeyEvent.VK_LEFT){  //向左键
            isLeft = false;
        }
        if (code == KeyEvent.VK_RIGHT){  //向右键
            isRight = false;
        }
        if (code == KeyEvent.VK_A){ //玩家按下开火键
            isFire = false;
           if (GameStartFrame.girl.getStatus() == Girl.FORWARD
                   || GameStartFrame.girl.getStatus() == Girl.BACK) {
               GameStartFrame.roleList.add(new GirlBullet());
           }
        }
        if(code == KeyEvent.VK_S){//玩家按下炸弹键
            GameStartFrame.girl.setSpinInit();

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
