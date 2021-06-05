package role;

import com.javaGame.util.ImageChange;

import java.awt.*;

/**
 * 花瓣类
 */
public class Flower extends BaseRole{
    /**普通花瓣图片数组*/
    public static Image[] flowerImage;
    /**玫瑰花瓣图片数组*/
    public static Image[] roseImage;

    /**普通花瓣常量*/
    public static final int NORMAL = 0;
    /**玫瑰花瓣常量*/
    public static final int ROSE = 1;
    /**花瓣类别*/
    public int type;
    /**左下方向*/
    public static final int LEFTDOWN = 0;
    /**右上方下常量*/
    public static final int RIGHTUP = 1;
    /**花瓣移动方向*/
    private int direction;
    /**图片切换对象*/
    private ImageChange flowerChange = new ImageChange(3, (int)(Math.random()*8));
    private ImageChange roseChange = new ImageChange(5, (int)(Math.random()*4));

    /**花瓣移动速度*/
    private int speed;

    /**
     * 角色构造方法
     */
    public Flower(int type, int direction) {
        super((int)(Math.random()*800 + 100), (int)(Math.random()*500 + 100),
                (int)(Math.random()*50 + 50),0);
        this.type = type;
        this.direction = direction;
        this.height = this.width;
        this.speed = (int)(Math.random()*8 + 4);
    }

    @Override
    public void move() {
        if(this.type == NORMAL){
            this.roleImage = this.flowerChange.change(flowerImage);
        }
        else if(this.type == ROSE){
            this.roleImage = this.roseChange .change(roseImage);
        }

        if(this.direction == LEFTDOWN){
            this.x -= this.speed;
            this.y += this.speed;
        }
        else if(this.direction == RIGHTUP){
            this.x += this.speed;
            this.y -= this.speed;
        }

    }
}
