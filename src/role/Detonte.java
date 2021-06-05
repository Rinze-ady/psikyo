package role;

import frame.GameStartFrame;
import com.javaGame.util.ImageChange;

import java.awt.*;

/**
 * 爆炸类
 */
public class Detonte extends BaseRole{
    /**爆炸图片*/
    public static Image[] detonateImage;

    /**图片切换对象*/
    private ImageChange changeObj = new ImageChange(2);
    /**爆炸计数器*/
    private int count;
    public Detonte(int x, int y) {
        super(x, y, 100, 100);
    }
    @Override
    public void move() {
        this.roleImage = changeObj.change(detonateImage);
        this.count ++;
        if (this.count > 10){
            //移除爆炸对象
            GameStartFrame.roleList.remove(this);
        }
    }
}
