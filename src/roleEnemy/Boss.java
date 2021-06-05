package roleEnemy;

import frame.GameStartFrame;
import com.javaGame.util.ImageChange;

import java.awt.*;

/**
 * boss类
 */
public class Boss extends EnemyRole implements IDie{

    /**boss图像数组*/
    public static Image[] bossImage;

   /**图片切换计数器*/
    private ImageChange changeObj = new ImageChange(3);

    /**boss初始常量*/
    public static final int INIT = 0;
    /**boss向下常量*/
    public static final int DOWN = 1;
    /**boss向上常量*/
    public static final int UP = 2;
    /**boss状态*/
    private int status = INIT;

    /**
     * 角色构造方法
     */
    public Boss() {
        super(1100, 100, 280, 180, 400, 1000);
    }

    @Override
    public void move() {
        this.roleImage = changeObj.change(bossImage);


        if(this.status == INIT){//初始状态
            this.x -= 5;
            if (x <= 700){
                this.status = DOWN;
            }
        }
        else {
            if (this.status == DOWN) {//飞蛾向下状态
                this.y += 2;

                if (this.y >= 400) {
                    this.status = UP;
                }
            } else if (this.status == UP) {//飞蛾向上状态
                this.y -= 2;

                if (this.y <= 100) {
                    this.status = DOWN;
                }
            }
        }
    }

    @Override
    public void leaveOver() {
        //停止游戏线程
        GameStartFrame.isRun = false;
        //游戏是否通过
        GameStartFrame.isPass = true;
    }
}
