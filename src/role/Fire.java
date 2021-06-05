package role;

import frame.GameStartFrame;
import roleEnemy.EnemyRole;
import com.javaGame.util.ImageChange;

import java.awt.*;

/**
 * 宠物吐的火类
 */
public class Fire extends BaseRole{
    /**火的图片数组*/
    public static Image[] fireImage;

    /**图片切换对象*/
    private ImageChange changeObj = new ImageChange(3);

    /**
     * 角色构造方法
     */
    public Fire(int x, int y) {
        super(x, y, 200, 120);
    }

    @Override
    public void move() {
        this.roleImage = changeObj.change(fireImage);
        for(int i = 0; i < GameStartFrame.roleList.size(); i++){
            BaseRole role = GameStartFrame.roleList.get(i);

            if(this.rect.intersects(role.rect)
                    && role instanceof EnemyRole){
                EnemyRole enemy = (EnemyRole) role;
                enemy.byHit(3);
            }
        }

    }
}
