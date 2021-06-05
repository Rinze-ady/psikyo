package roleEnemy;

import com.javaGame.util.ImageChange;

import java.awt.*;

/**
 * 机器狗类
 */
public class RobotDog extends EnemyRole{
    /**机器狗图片数组*/
    public static Image[] dogImage;

    /**图片切换对象*/
    private ImageChange changeObj = new ImageChange(2);

    public RobotDog() {
        super((int)(Math.random()*500 + 1000), (int)(Math.random()*400 + 100),
                121, 80, 20, 100);
    }

    @Override
    public void move() {
        this.roleImage = changeObj.change(dogImage);

        this.x -= 3;
    }
}
