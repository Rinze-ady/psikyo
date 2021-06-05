package frame;

import role.*;
import sort.PlayerScore;
import com.javaGame.util.CreateEnemy;
import com.javaGame.util.InitImage;
import com.javaGame.util.MusicUtil;
import com.javaGame.util.PressKeyEvent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 游戏主窗体
 * @author zzw
 *
 */
public class GameStartFrame extends JFrame implements Runnable {
//还剩下窗体相关职责（JFrame）和线程相关职责（Runnable）
//    是否应该把线程相关职责分离，线程的职责只是为了对窗体中的图像进行绘制，业务较简单，可保留
//    虽然根据单一职责划分原则需要对业务进行划分，但划分的太细也会增加程序的复杂度

    /**游戏是否运行*/
    public static boolean isRun = true;

    /**地图对象*/
    private GameMap gameMap = new GameMap();
//    /**boss对象*/
//    private Boss boss = new Boss();
    /**女孩对象*/
    public static Girl girl = new Girl();


    /**双缓冲图像*/
    private Image memoryImage;
    /**双缓冲画笔*/
    private Graphics memoryGraphics;

    /**角色集合*/
    public static List<BaseRole> roleList = new ArrayList<BaseRole>();
    /**玩家得分*/
    public static int score;
    /**女孩生命数*/
    public static int girlNumber = 3;
    /**游戏是否通过*/
    public static boolean isPass;
    /**得分图像*/
    public static Image scoreImage;


    /**用户是否按下向上键*/
    private boolean isUp;
    /**用户是否按下向下键*/
    private boolean isDown;
    /**用户是否按下向左键*/
    private boolean isLeft;
    /**用户是否按下向右键*/
    private boolean isRight;

    /**音乐播放对象*/
    private MusicUtil music = new MusicUtil("music/Caviare - Boiling.mp3", true);


    public GameStartFrame(){
        this.setSize(1000, 700);
        this.setVisible(true);
        //窗体关闭后，程序结束
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //窗体居中显示
        this.setLocationRelativeTo(null);

        //窗体不能改变大小
        this.setResizable(false);


        //解决游戏角色闪烁的问题
        //创建1000*700的内存图像平日，创建和窗体一样大小的内存图片
        memoryImage = this.createImage(1000, 700);
        //得到双缓冲画笔
        this.memoryGraphics = memoryImage.getGraphics();

        //设置双缓冲画笔字体颜色为粉红色
        this.memoryGraphics.setColor(Color.pink);
        //设置双缓冲画笔字体
        this.memoryGraphics.setFont(new Font("黑体", Font.BOLD, 20));

        this.init();

        if(InitImage.isInit == false    ){
            //初始化图像
            InitImage.init(this);
            InitImage.isInit = true;
        }

        //初始化图像,完成图像的切割和媒体跟踪器的管理
        //初始化图像后再启动线程，否则可能造成空指针异常
        InitImage.init(this);

        //注册键盘监听事件
        this.addKeyListener(new PressKeyEvent());

        //在角色集合中添加女孩和boss
        roleList.add(girl);
//        roleList.add(boss);

        //创建线程
        Thread gameThread = new Thread(this);
        //启动线程
        gameThread.start();
    }

    public static void main(String[] args) {
        new GameStartFrame();
    }

    @Override
    public void run() {
        while(isRun){
            this.drawFrame(this.getGraphics());

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        music.stopMusic();

        this.dispose();

        if(score > SortFrame.getLowest()){
            String playerName = JOptionPane.showInputDialog(null, "请输入玩家姓名");
            PlayerScore p = new PlayerScore();
            p.setName(playerName);
            p.setScore(score);

            SortFrame.addScore(p);
        }

        if(isPass == true){
            new SuccessFrame();
        }
        else{
            new LoseFrame();
        }

    }

    /**
     *绘制窗体
     * @param g 窗体画笔
     */
    private void drawFrame(Graphics g){
        //完成地图对象drawMyself方法的调用，并传入双缓冲画笔
        gameMap.drawMySelf(this.memoryGraphics);


        for (int i = 0; i < roleList.size(); i++){
            BaseRole role = roleList.get(i);
            role.drawMySelf(this.memoryGraphics);
        }

        CreateEnemy.create();

        this.drawInfo();
        //将内存中叠加后的图像，绘制再窗体中
        g.drawImage(this.memoryImage, 0, 0, this);

    }


    /**
     * 绘制游戏信息
     */
    private void drawInfo(){
        //绘制女孩的生命
        this.memoryGraphics.drawImage(Girl.forwardFlickerImage[0], 10, 50, 20, 20, null);
        this.memoryGraphics.drawString("X  " + girlNumber, 40, 68);

        //绘制女孩炸弹数量
        this.memoryGraphics.drawImage(Prop.propImage[1], 10, 75, 20, 20, null);
        this.memoryGraphics.drawString("X  " + girl.getBombNumber(), 40, 95);

        //绘制玩家得分
        this.memoryGraphics.drawImage(scoreImage, 10, 105, 20, 20, null);
        this.memoryGraphics.drawString( String.valueOf(score), 40, 122);
    }

    /**
     * 游戏信息初始化
     */
    private void init(){

        isRun = true;
        girl = new Girl();
        roleList.clear();
        score = 0;

        girlNumber = 3;
        isPass = false;

        GameMap.init();
        CreateEnemy.init();
    }

}
