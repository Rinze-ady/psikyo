package roleEnemy;

import frame.GameStartFrame;
import role.BaseRole;
import role.Detonte;

import java.awt.*;

/**
 * 所有怪物的父类
 */
public abstract class EnemyRole extends BaseRole {

    /**怪物的生命*/
    protected int hp;
    protected int exp;


    public EnemyRole(int x, int y, int width, int height, int hp, int exp) {
        super(x, y, width, height);
        this.hp = hp;
        this.exp = exp;
    }

    public int getHp() {
        return hp;
    }

    /**
     * 怪物被击中的方法
     * @param reduceLife 怪物应该减少的生命
     */
    public void byHit(int reduceLife){
        this.hp -= reduceLife;
        if (this.hp <= 0){//怪物被打死
            //从角色集合中移除怪物
            GameStartFrame.roleList.remove(this);
            //增加玩家得分
            GameStartFrame.score += this.exp;

            this.createDetonate();

            if (this instanceof IDie){ //判断怪物是否匹配IDie类型
                IDie d = (IDie) this;
                d.leaveOver();
            }
        }
    }

    /**
     * 添加爆炸效果
     */
    public void createDetonate(){
        //添加爆炸效果
        GameStartFrame.roleList.add(new Detonte(this.x, this.y));
    }

    /**
     * 怪物血条
     */
    @Override
    public void drawMySelf(Graphics memoryGraphics){
        super.drawMySelf(memoryGraphics);
        memoryGraphics.setColor(Color.red);
        //画一个实心矩形
        memoryGraphics.fillRect(this.x, this.y -20, (int)(this.hp*1.0/this.width), 5);
    }

}
