package com.javaGame.util;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 音乐播放类
 */
public class MusicUtil implements Runnable{

    /**音乐播放对象*/
    private Player p;

    /**音频文件*/
    private String filePath;

    /**是否循环播放音乐*/
    private boolean isLoop;

    public MusicUtil(String filePath, boolean isLoop) {
        this.filePath = filePath;
        this.isLoop = isLoop;

        //创建并启动音频线程
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        do {
            try {
                BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(filePath));
                p = new Player(buffer);
                p.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }while(isLoop);
    }

    /**
     * 停止音乐播放
     */
    public void stopMusic(){
        p.close();
        isLoop = false;
    }
//
//    public static void main(String[] args) {
//        MusicUtil m = new MusicUtil("", false);
//    }
}
