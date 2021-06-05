package role;

import frame.GameStartFrame;
import com.javaGame.util.ImageChange;
import com.javaGame.util.PressKeyEvent;

import java.awt.*;

/**
 * 宠物类
 */
public class Mink extends BaseRole{

    /**状态图片数组*/
    public static Image[] normalImage;
    /**消失状体图片数组*/
    public static Image[] disappearImage;
    /**准备吐火图片数组*/
    public static Image[] readyFireImage;
    /**吐火图片数组*/
    public static Image[] fireImage;

    /**普通状态常量*/
    public static final int NORMAL = 0;
    /**消失状态常量*/
    public static final int DISAPPEAR = 1;
    /**准备吐火状态常量*/
    public static final int READYFIRE = 2;
    /**吐火状态常量*/
    public static final int FIRE = 3;
    /**宠物状态*/
    private int status = NORMAL;

    /**图片切换对象*/
    private ImageChange changeObj = new ImageChange(4);

    /**聚气计数器*/
    private int gatherCount;

    /**吐火计数器*/
    private int fireCount;

    /**火对象*/
    private Fire fire;


    public Fire getFire() {
        return fire;
    }


    /**
     * 角色构造方法
     *
     */
    public Mink() {
        super(GameStartFrame.girl.x - 35, GameStartFrame.girl.y - 15, 45, 25 );
    }

    @Override
    public void move() {
        this.setStatus();
        this.setMove();
        this.setImage();
    }

    /**
     *宠物图片的设置
     */
    private void setImage(){

        if(this.status == NORMAL){//普通状态
            this.roleImage = changeObj.change(normalImage);
            this.width = 45;
            this.height = 25;
        }
        else if(this.status == DISAPPEAR){//消失状态
            this.roleImage = changeObj.change(disappearImage);
            this.width = 45;
            this.height = 25;
        }
        else if(this.status == READYFIRE){//准备吐火状态
            this.roleImage = changeObj.change(readyFireImage);
            this.width = 100;
            this.height = 100;
        }
        else if(this.status == FIRE){//吐火状态
            this.roleImage = changeObj.change(fireImage);
            this.width = 150;
            this.height = 80;
        }


    }

    /**
     *宠物移动
     */
    private void setMove(){

        if(this.status == NORMAL || this.status == DISAPPEAR){
            this.x = GameStartFrame.girl.x - 35;
            this.y = GameStartFrame.girl.y - 15;
        }
        else if(this.status == READYFIRE){
            this.x = GameStartFrame.girl.x + 80;
            this.y = GameStartFrame.girl.y - 80;
        }
    }


    /**
     *宠物状态
     */
    private void setStatus(){
        if(PressKeyEvent.isFire == true && this.status == NORMAL){//玩家在宠物普通状态时聚气
            this.gatherCount++;
            if(this.gatherCount > 100){
                this.status = DISAPPEAR;
            }
        }
        else if(PressKeyEvent.isFire == true && this.status == DISAPPEAR){//玩家在宠物消失状态时聚气
            this.gatherCount++;
            if(this.gatherCount >= 116){
                this.status = READYFIRE;
            }
        }
        else if(PressKeyEvent.isFire == false && this.status == DISAPPEAR){//玩家在宠物消失状态时，弹起开火键
                this.status = NORMAL;
        }
        else if(this.status == READYFIRE && PressKeyEvent.isFire == false){//玩家在宠物准备吐火状态时，弹起开火键
            this.status = FIRE;
            this.fire = new Fire(this.x + 150, this.y - 50);
            GameStartFrame.roleList.add(this.fire);
        }
        else if(this.status == FIRE){//吐火状态

            this.fireCount ++;
            if(this.fireCount > 200){//吐火结束
                this.status = NORMAL;
                this.fireCount = 0;

                GameStartFrame.roleList.remove(this.fire);
                this.fire = null;
            }
        }
        else{
            this.gatherCount = 0;
        }
    }
}
