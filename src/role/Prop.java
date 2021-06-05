package role;

import frame.GameStartFrame;
import com.javaGame.util.MusicUtil;

import java.awt.*;

/**
 * 道具类
 */
public class Prop extends BaseRole{

    /**道具图片*/
    public static Image[] propImage;
    /**火力道具常量*/
    public static final int POWER = 0;
    /**炸弹道具常量*/
    public static final int BOMB = 1;
    /**生命道具常量*/
    public static final int LIFE = 2;
    /**道具类别*/
    private int type;



    /**
     * 角色构造方法
     **/
    public Prop(int x, int y, int type) {
        super(x, y, 65, 40);
        this.type = type;

        if (type == POWER){//火力道具
            this.roleImage = propImage[0];
        }
        else if (type == BOMB){//炸弹道具
            this.roleImage = propImage[1];
        }
        else if (type == LIFE){//生命道具
            this.roleImage = propImage[2];
        }
    }

    @Override
    public void move() {
        this.x -= 3;

        if (this.rect.intersects(GameStartFrame.girl.rect) &&
                (GameStartFrame.girl.getStatus() == Girl.FORWARD
                || GameStartFrame.girl.getStatus() == Girl.BACK)){//道具和女孩相碰
            GameStartFrame.score += 500; //增加得分

            MusicUtil music= new MusicUtil("music/starStack.mp3", false);

            if (type == POWER){//火力道具
                GameStartFrame.girl.addPower();
            }
            else if (type == BOMB){//炸弹道具
                GameStartFrame.girl.setBombNumber(GameStartFrame.girl.getBombNumber() + 1);
            }
            else if (type == LIFE){//生命道具
                GameStartFrame.girlNumber ++;
            }

            //移除道具对象
            GameStartFrame.roleList.remove(this);

        }

    }
}
