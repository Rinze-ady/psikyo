package role;

import frame.GameStartFrame;
import com.javaGame.util.ImageChange;

import java.awt.*;

/**
 * 击中敌人的小爆炸类
 */
public class Blast extends BaseRole{

    /**小爆炸图片数组*/
    public static Image[] blastImage;
    /**图片切换对象*/
    private ImageChange changeObj = new ImageChange(2);
    /**小爆炸消失计数器*/
    private int count;


    /**
     * 角色构造方法
     */
    public Blast(int x, int y) {
        super(x, y, 50, 50);
    }

    @Override
    public void move() {
        this.roleImage = changeObj.change(blastImage);

        count ++;
        if (count > 8){
            GameStartFrame.roleList.remove(this);
        }

    }
}
