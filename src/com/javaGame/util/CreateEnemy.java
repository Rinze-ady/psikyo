package com.javaGame.util;

import frame.GameStartFrame;
import role.GameMap;
import roleEnemy.Boss;
import roleEnemy.LightEnemy;
import roleEnemy.RedMoth;
import roleEnemy.RobotDog;

/**
 * 产生怪物工具类
 */
public class CreateEnemy {
    /**产生机器狗计数器*/
    private static int dogCount;

    /**产生灯怪计数器*/
    private static int lightCount;

    /**是否产生飞蛾*/
    private static boolean isCreateMoth = true;

    /**是否产生boss*/
    private static boolean isCreateBoss = true;


    /**
     * 初始化产生怪物信息
     */
    public static void init(){
        dogCount = 0;
        lightCount = 0;
        isCreateMoth = true;

    }

    /**
     * 产生怪物
     */
    public static void create(){
        createRobotDog();
        createLight();
        createMoth();
        createBoss();
    }

    /**
     * 产生机器狗
     */
    public static void createRobotDog(){
        dogCount ++;

        if (dogCount > 200){
            dogCount = 0;

            for(int i = 0; i <= Math.random()*3; i++) {
                GameStartFrame.roleList.add(new RobotDog());
            }
        }
    }

    /**
     * 产生灯怪
     */
    public static void createLight(){
//        if (GameMap.mapx > -1800 && GameMap.mapx < -500)
        if (GameMap.mapx < -500){
            lightCount++;

            if (lightCount >= 200) {
                lightCount = 0;

                for (int i = 0; i <= Math.random() * 3; i++) {
                    GameStartFrame.roleList.add(new LightEnemy());
                }
            }
        }
    }

    /**
     * 产生飞蛾
     */
    public static void createMoth(){
        if (GameMap.mapx < -2000 && isCreateMoth == true){
            GameStartFrame.roleList.add(new RedMoth());
            GameMap.isRoll = false;
            isCreateMoth = false;
        }

    }

    /**
     *产生boss
     */
    public static void createBoss(){
        if(GameMap.mapx < -8000 && isCreateBoss == true){
            GameStartFrame.roleList.add(new Boss());

            isCreateBoss = false;
        }


    }

}
