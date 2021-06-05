package com.javaGame.util;

import java.awt.*;

/**
 * 图片切换算法工具类
 */
public class ImageChange {

    /**图片切换计数器*/
    private int count;
    /**图片下标变量*/
    private int index;
    /**图片切换频率*/
    private int frequency;

    public ImageChange(int frequency) {
        super();
        this.frequency = frequency;
    }

    public ImageChange(int frequency, int index) {
        this.index = index;
        this.frequency = frequency;
    }

    /**
     * 图片切换
     * @param imageArray 要切换的图片数组
     * @return 当前应该绘制的图片
     */
    public Image change(Image[] imageArray){
        count ++;
        if (count >= frequency){
            count = 0;
            index ++;
        }
        if(index >= imageArray.length){
            index = 0;
        }
        return imageArray[index];
    }


}
