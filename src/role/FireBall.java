package role;

import com.javaGame.util.ImageChange;

import java.awt.*;

/**
 * 火球类
 */
public class FireBall extends BaseRole implements IEnemyBullet{

    /**火球图片数组*/
    public static Image[] fireBallImage;

    /**图片切换对象*/
    private ImageChange changeObj = new ImageChange(3);

    /**水平方向*/
    public static final int LEVEL = 0;
    /**左上方向*/
    public static final int LEFTUP = 1;
    /**左下方向*/
    public static final int LEFTDOWN = 2;
    /**子弹发射方向*/
    private int direction;

    /**
     * 角色构造方法
     */
    public FireBall(int x, int y, int direction) {
        super(x, y, 60, 60);
        this.direction = direction;
    }

    @Override
    public void move() {
        this.roleImage = changeObj.change(fireBallImage);

        if (this.direction == LEVEL){//水平方向
            this.x -= 5;
        }
        else if (this.direction == LEFTUP){//左上方向
             this.x -= 3;
             this.y -= 2;
        }
        else if (this.direction == LEFTDOWN){//左下方向
            this.x -= 1;
            this.y += 2;
        }

    }
}
