package role;

import frame.GameStartFrame;
import roleEnemy.EnemyRole;

import java.awt.*;

/**
 * 女孩子弹类
 */
public class GirlBullet extends BaseRole{

    /**子弹图像*/
    public static Image girlBulletImage;

    /**
     * 角色构造方法
     *
     */
    public GirlBullet() {
        super(GameStartFrame.girl.x + 10, GameStartFrame.girl.y + 10, 65, 15);
        this.roleImage = girlBulletImage;
    }

    @Override
    public void move() {
        this.x += 15;

        for (int i = 0; i < GameStartFrame.roleList.size(); i++){
            BaseRole role = GameStartFrame.roleList.get(i);
            if (this.rect.intersects(role.rect)){
                if (role instanceof EnemyRole){//子弹击中怪物
                    EnemyRole enemy = (EnemyRole) role;
                    enemy.byHit(15);

                    GameStartFrame.roleList.remove(this);//移除子弹对象
                    GameStartFrame.roleList.add(new Blast(this.x - 30, this.y));

                }
            }


        }
    }
}
