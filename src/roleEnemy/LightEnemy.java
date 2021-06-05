package roleEnemy;

import frame.GameStartFrame;
import role.Prop;
import com.javaGame.util.ImageChange;

import java.awt.*;

/**
 * 灯怪类
 */
public class LightEnemy extends EnemyRole implements IDie{

    /**灯怪图片*/
    public static Image[] lightImage;

    /**图片切换对象*/
    private ImageChange changeObj = new ImageChange(1);

    public LightEnemy() {
        super((int)(Math.random()*450 + 300), (int)(Math.random()*500 + 700),
                67, 65, 30, 200);
    }

    @Override
    public void move() {
        this.roleImage = changeObj.change(lightImage);

        this.y -= 4;
    }

    @Override
    public void leaveOver() {
        GameStartFrame.roleList.add(new Prop(this.x, this.y, Prop.POWER));
    }
}
