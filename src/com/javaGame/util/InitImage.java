package com.javaGame.util;

import frame.GameStartFrame;
import frame.SuccessFrame;
import role.*;
import roleEnemy.Boss;
import roleEnemy.LightEnemy;
import roleEnemy.RedMoth;
import roleEnemy.RobotDog;

import javax.swing.*;
import java.awt.*;

/**
 * 初始化地图图像和切割图像
 */
public class InitImage {


    /**角色图片是否加载*/
    public static boolean isInit = false;

    /**
     * 初始化图像
     * @param frame 窗体对象
     */
    public static void init(JFrame frame){
        //创建媒体跟踪器对象
        MediaTracker tracker = new MediaTracker(frame);

//        //将地图图像加入媒体跟踪器，分成1组
//        tracker.addImage(gameMapImage, 1);

        //切割boss图像,第一个参数为切割图片的路径，第二个参数为切割的分数，
        // 第三个参数为媒体跟踪器，第四个参数为分组编号
        Boss.bossImage = CutImage.cutOneImage("image/boss/playSpear.png", 18, tracker, 1);


        //初始化地图图像
        GameMap.gameMapImage = CutImage.getSingleImage("image/map.jpg", tracker, 1);

        //初始化女孩子弹图像
        GirlBullet.girlBulletImage = CutImage.getSingleImage("image/jbullet/dart.png", tracker, 1);

        //初始化爆炸图像
        Detonte.detonateImage = CutImage.cutOneImage("image/bomb/detonate.png", 5, tracker, 1);

        //初始化机器狗图像
        RobotDog.dogImage = CutImage.cutOneImage("image/enemy/dogMachine.png", 3, tracker, 1);
//        RobotDog.dogImage = CutImage.getSingleImage("image/enemy/dogMachine.gif", tracker, 1);

        //初始化灯怪图像
        LightEnemy.lightImage = CutImage.cutOneImage("image/enemy/lightEnemy.gif", 1, tracker, 1);
//        LightEnemy.lightImage = CutImage.getSingleImage("image/enemy/lightEnemy.gif", tracker, 1);

        //初始化飞蛾图像
        RedMoth.mothImage = CutImage.cutOneImage("image/enemy/dragon.gif", 1, tracker, 1);
//        RedMoth.mothImage = CutImage.getSingleImage("image/enemy/dragon.gif", tracker, 1);

        //初始化火球图像
        FireBall.fireBallImage = CutImage.cutOneImage("image/enemyBullet/ice.png", 3, tracker, 1);

        //击中敌人小爆炸图像
        Blast.blastImage = CutImage.cutOneImage("image/bomb/blast.png", 4, tracker, 1);

        //初始化道具图片
        Prop.propImage = CutImage.cutOneImage("image/prop.png", 3, tracker, 1);

        //初始化得分图像
        GameStartFrame.scoreImage = CutImage.getSingleImage("image/score.png", tracker, 1);

        //初始化女孩图片
        initGirlImage(tracker);

        //初始化普通花瓣图片
        Flower.flowerImage = CutImage.cutOneImage("image/flower.png", 8, tracker, 1);

        //初始化玫瑰花瓣图片
        Flower.roseImage = CutImage.cutOneImage("image/rose.png", 4, tracker, 1);

        //通关图片
        SuccessFrame.successImage = CutImage.cutOneImage("image/win.jpg", 2, tracker, 1);


        //初始化宠物貂图片
        initMinkImage(tracker);

        //初始化火图片
        Fire.fireImage = CutImage.cutOneImage("image/fireImg.png", 6, tracker, 1);


        try {
            //1组所有图片，全部加载完毕后，再一起显示
            tracker.waitForID(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 初始化女孩图片
     * @param tracker 媒体跟踪器
     */
    private static void initGirlImage(MediaTracker tracker){
        //切割女孩无敌状态前进图片
        Girl.forwardFlickerImage = CutImage.cutOneImage("image/girl/forward.png", 4, tracker, 1);
        //切割女孩普通状态前进图片
        Girl.forwardImage = new Image[]{Girl.forwardFlickerImage[0], Girl.forwardFlickerImage[2]};
        //切割女孩无敌后退图片
        Girl.backFlickerImage = CutImage.cutOneImage("image/girl/back.png", 4, tracker, 1);
        //切割女孩普通后退图片
        Girl.backImage = new Image[]{Girl.backFlickerImage[0],Girl.backFlickerImage[2]};
        //切割女孩旋转图片
        Girl.spinImage = CutImage.cutOneImage("image/girl/circumgyrate.png", 12, tracker, 1);
        //切割女孩死亡图片
        Girl.dieImage = CutImage.getSingleImage("image/girl/die.png", tracker, 1);

    }

    /**
     * 初始化宠物图片
     * @param tracker 媒体跟踪器
     */
    private static void initMinkImage(MediaTracker tracker){

        /**初始化普通图片*/
        Mink.normalImage = CutImage.cutOneImage("image/mink/normal.png", 2, tracker, 1);
        /**初始化消失图片*/
        Mink.disappearImage = CutImage.cutOneImage("image/mink/disappear.png", 4, tracker, 1);
        /**初始化准备吐火图片*/
        Mink.readyFireImage = CutImage.cutOneImage("image/mink/readyFire.png", 6, tracker, 1);
        /**初始化吐火图片*/
        Mink.fireImage = CutImage.cutOneImage("image/mink/fire.png", 8, tracker, 1);


    }

}
