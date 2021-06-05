package role;

import frame.GameStartFrame;
import roleEnemy.EnemyRole;
import com.javaGame.util.ImageChange;
import com.javaGame.util.PressKeyEvent;

import java.awt.*;

/**
 * Girl
 */
public class Girl extends BaseRole{
    /**无敌前进图片数组*/
    public static Image[] forwardFlickerImage;
    /**普通状态前进数组*/
    public static Image[] forwardImage;
    /**无敌后退图片数组*/
    public static Image[] backFlickerImage;
    /**普通状态后退数组*/
    public static Image[] backImage;
    /**旋转图片数组*/
    public static Image[] spinImage;
    /**死亡图片*/
    public static Image dieImage;

    /**初始状态常量*/
    public static final int INIT = 0;
    /**前进状态常量*/
    public static final int FORWARD = 1;
    /**后退状态常量*/
    public static final int BACK = 2;
    /**旋转状态常量*/
    public static final int SPIN = 3;
    /**死亡状态常量*/
    public static final int DIE = 4;

    /**女孩状态*/
    private int status = 0;

    public int getStatus() {
        return status;
    }

    /**女孩是否无敌*/
    private boolean invincible ;

    //宠物
    private Mink mink;

    public Mink getMink() {
        return mink;
    }

    /**图片切换计数器*/
    private ImageChange changeObj = new ImageChange(2);

    /**女孩炸弹数量*/
    private int bombNumber = 4;

    /**女孩无敌状态计数器*/
    private int invincibleCount;

    /**旋转计数器*/
    private int spinCount;

    /**
     * 角色构造方法
     * 女孩类坐标和大小的初始化
     */
    public Girl() {
        super(-100, 100, 60, 40);
    }


    public int getBombNumber() {
        return bombNumber;
    }

    public void setBombNumber(int bombNumber) {
        this.bombNumber = bombNumber;
    }

    @Override
    public void move() {
        if (this.status == INIT){//初始状态
            this.setInit();
        }
        else if (this.status == SPIN){//旋转状态
            this.setSpin();
        }
        else if (this.status == DIE){//死亡状态
            this.setDie();
        }
        else {//
            this.setMove();
            this.setSize();

            this.setHit();
            this.setInvincible();

        }
        this.setImage();

    }

    /**
     * 增加火力
     */
    public void addPower() {

        if (this.mink == null) {
            this.mink = new Mink();
            GameStartFrame.roleList.add(this.mink);
        }
    }

    /**
     * 描述女孩在各种状态下的图片
     */
    private void setImage(){
        if (this.status == FORWARD && this.invincible == true) {
            this.roleImage = changeObj.change(forwardFlickerImage);
        }
        else if (this.status == FORWARD && this.invincible == false || this.status == INIT){
            this.roleImage = changeObj.change(forwardImage);
        }
        else if (this.status == BACK && this.invincible == true){
            this.roleImage = changeObj.change(backFlickerImage);
        }
        else if (this.status == BACK && this.invincible == false){
            this.roleImage = changeObj.change(backImage);
        }
        else if (this.status == SPIN){
            this.roleImage = changeObj.change(spinImage);
        }
        else if (this.status == DIE){
            this.roleImage = dieImage;
        }

    }

    /**
     * 描述女孩初始状态行为
     */
    private void setInit(){

        this.x += 10;
        if (this.x >= 200){
            this.status = FORWARD;
            this.invincible = true;
        }
    }

    /**
     * 描述女孩在各种状态下的大小
     */
    private void setSize(){
        if (this.status == FORWARD){
            this.width = 60;
            this.height = 40;
        }
        else if(this.status == BACK){
            this.width = 40;
            this.height = 60;
        }
        else if(this.status == DIE){
            this.width = 50;
            this.height = 50;
        }

    }

    /**
     * 描述女孩在正常状态下的移动行为
     */
    private void setMove(){
        if (PressKeyEvent.isUp == true){
            this.y -=5;
            if (this.y <= 30){
                this.y = 30;
            }
        }
        if (PressKeyEvent.isDown == true){
            this.y +=5;
            if (this.y >= 700 - this.height){
                this.y = 700 - this.height;
            }
        }
        if (PressKeyEvent.isLeft == true){
            this.x -=5;
            if(this.x <= 0){
                this.x = 0;
            }
            this.status = BACK;
        }
        if (PressKeyEvent.isRight == true){
            this.x +=5;
            if(this.x >= 1000 - this.width  ){
                this.x = 1000 - this.width;
            }
            this.status = FORWARD;
        }
        if (PressKeyEvent.isRight == false && PressKeyEvent.isLeft == false){
            this.status = FORWARD;
        }

    }


    /**
     * 描述女孩被敌人或敌人子弹击中
     */
    private void setHit(){
        for(int i = 0; i < GameStartFrame.roleList.size(); i++){

            BaseRole role = GameStartFrame.roleList.get(i);

            if(this.rect.intersects(role.rect) && (role instanceof EnemyRole
                    || role instanceof IEnemyBullet) && invincible == false) {
                this.status = DIE;

                if(this.mink != null){
                    if(this.mink.getFire() != null){//宠物貂吐火
                        GameStartFrame.roleList.remove(this.mink.getFire());//移除火对象
                    }

                    GameStartFrame.roleList.remove(this.mink);//移除宠物貂
                }
                return;
            }
        }
    }

    /**
     * 女孩无敌状态设置
     */
    private void setInvincible(){
        this.invincibleCount ++;
        if(this.invincibleCount > 200){
            this.invincibleCount = 0;
            this.invincible = false;
        }

    }

    /**
     * 女孩旋转
     */
    private void setSpin(){
        this.spinCount ++;

        if(spinCount < 50){
            this.width += 4;
            this.height += 4;
        }
        else if(spinCount < 100){
            this.width -= 4;
            this.height -= 4;
        }
        else {
            this.spinCount = 0;
            this.status = FORWARD;
            this.invincible = true;
            this.invincibleCount = 0;
        }
    }

    /**
     * 女孩死亡
     */
    private void setDie(){
        this.y += 25;
        if(this.y >= 750){
            GameStartFrame.girlNumber --;

            if(GameStartFrame.girlNumber == 0){//通关失败
                GameStartFrame.isRun = false;
            }
            else{//产生新的主角
                GameStartFrame.roleList.remove(GameStartFrame.girl);
                GameStartFrame.girl = new Girl();
                GameStartFrame.roleList.add(GameStartFrame.girl);
            }
        }
    }

    /**
     * 旋转初始化
     */
    public void setSpinInit(){

        if((this.status == FORWARD || this.status == BACK) && this.bombNumber > 0){
            this.status = SPIN;
            this.bombNumber --;

            //设置女孩旋转初始大小
            this.width = 40;
            this.width = 65;

            for(int i = 0; i < GameStartFrame.roleList.size(); i++){
                BaseRole role = GameStartFrame.roleList.get(i);
                if(role instanceof EnemyRole){
                    EnemyRole enemy = (EnemyRole) role;
                    enemy.byHit(100);

                    if(enemy.getHp() <= 0){
                        i --;
                    }
                }
                else if(role instanceof IEnemyBullet){//清除敌人子弹
                    GameStartFrame.roleList.remove(role);
                    i --;
                }
            }

            for(int i = 0; i < 40; i++){
                GameStartFrame.roleList.add(new Flower(Flower.NORMAL, Flower.LEFTDOWN));
                GameStartFrame.roleList.add(new Flower(Flower.NORMAL, Flower.RIGHTUP));
            }

            for(int i = 0; i < 15; i++){
                GameStartFrame.roleList.add(new Flower(Flower.ROSE, Flower.LEFTDOWN));
                GameStartFrame.roleList.add(new Flower(Flower.ROSE, Flower.RIGHTUP));
            }
        }
    }




































}
