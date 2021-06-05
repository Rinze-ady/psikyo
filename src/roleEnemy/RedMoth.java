package roleEnemy;

import frame.GameStartFrame;
import role.FireBall;
import role.GameMap;
import role.Prop;
import com.javaGame.util.ImageChange;

import java.awt.*;

public class RedMoth extends EnemyRole implements IDie{
    /**飞蛾图片*/
    public static Image[] mothImage;
    /**飞蛾初始常量*/
    public static final int INIT = 0;
    /**飞蛾向下常量*/
    public static final int DOWN = 1;
    /**飞蛾向上常量*/
    public static final int UP = 2;
    /**飞蛾状态*/
    private int status = INIT;
    /**计数器*/
    private int count;

    /**图片切换对象*/
    private ImageChange changeObj = new ImageChange(1);

    public RedMoth() {
        super(1100, 100, 52, 58, 100, 5000);
    }

    @Override
    public void move() {
        this.roleImage = changeObj.change(mothImage);

        if(this.status == INIT){//飞蛾初始状态
            this.x -= 5;
            if (x <= 700){
                this.status = DOWN;
            }
        }
        else{
            if (this.status == DOWN){//飞蛾向下状态
                this.y += 2;

                if (this.y >= 400){
                    this.status = UP;
                }
            }
            else if (this.status == UP ){//飞蛾向上状态
                this.y -= 2;

                if (this.y <= 100){
                    this.status = DOWN;
                }
            }
            this.shoot();
        }
    }

    /**
     * 发射子弹
     */
    private void shoot(){
        this.count ++;

        if (count > 100){
            count = 0;
            GameStartFrame.roleList.add(new FireBall(this.x + 20, this.y + 20, FireBall.LEVEL));
            GameStartFrame.roleList.add(new FireBall(this.x + 20, this.y + 20, FireBall.LEFTDOWN));
            GameStartFrame.roleList.add(new FireBall(this.x + 20, this.y + 20, FireBall.LEFTUP));

        }
    }

    @Override
    public void leaveOver() {
        GameStartFrame.roleList.add(new Prop(this.x, this.y, (int)(Math.random()*3)));
        GameMap.isRoll = true;
    }
}
