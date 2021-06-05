package role;


import java.awt.*;

/**
 * 地图类
 */
public class GameMap {
    //    /**地图图像*/
//    private Image gameMapImage = new ImageIcon("image/map.jpg").getImage();
    public static Image gameMapImage;

    /**地图的x坐标*/
    public static int mapx = 0;

    /**地图是否滚动*/
    public static boolean isRoll = true;



    public void drawMySelf(Graphics memoryGraphics){

        if (isRoll == true) {
            mapx -= 2;
            if (mapx <= -17000) {
                mapx = -17000;
            }
        }
        //绘制地图图像
//        g.drawImage(gameMapImage, mapx, 0, this);
        memoryGraphics.drawImage(gameMapImage, mapx, 0, null);
    }

    /**
     * 地图信息初始化
     */
    public static void init(){
        mapx = 0;
        isRoll = true;
    }
}
