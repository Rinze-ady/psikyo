package role;


import frame.GameStartFrame;

import java.awt.*;

/**
 * 所有类的父类
 */
public abstract class BaseRole {
    /**角色的x坐标*/
    //protected修饰符，只允许本包中的类及其子类调用
    protected int x;
    /**角色的y坐标*/
    protected int y;
    /**角色的宽度*/
    protected int width;
    /**角色的高度*/
    protected int height;
    /**角色当前应该绘制的图像*/
    protected Image roleImage;
    /**角色的碰撞区域*/
    protected Rectangle rect;

    //重用属性的初始化代码
    //子类构建时，需要调用父类的构造方法

    /**
     * 角色构造方法
     * @param x x坐标
     * @param y y坐标
     * @param width 角色宽度
     * @param height 角色高度
     */
    public BaseRole(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        //初始化Rectangle属性
        //碰撞区域做成原来的1/4.
        //x坐标是原来x坐标，加上1/4宽度。y坐标是原来y坐标，加上1/4高度。宽度和高度都是原来的一半、
        this.rect = new Rectangle(x+width/4, y+height/4, width/2, height/2);

    }

    /**
     * 设置碰撞区域随角色移动而移动
     */
    public void setRect(){
        this.rect.x = this.x + this.width/4;
        this.rect.y = this.y + this.height/4;
        this.rect.width = this.width/2;
        this.rect.height = this.height/2;

    }

    /**
     * 绘制角色图像
     * @param memoryGraphics 双缓冲画笔
     */
    public void drawMySelf(Graphics memoryGraphics){
        //绘制角色当前图像
        memoryGraphics.drawImage(this.roleImage, this.x, this.y, this.width, this.height, null);
        this.move();
        this.setRect();
        this.removeThis();
    }

    /**
     * 描述子类特有的行为
     */
    public abstract void move();

    /**
     * 角色超出屏幕，从角色集合移除
     */
    public void removeThis(){
        if (this.x > 1500 || this.x < -200 || this.y < -200 || this.y > 900){
            GameStartFrame.roleList.remove(this);
        }
    }


}
