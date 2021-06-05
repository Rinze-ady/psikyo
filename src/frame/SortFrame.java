package frame;

import component.TextLabel;
import sort.FilePress;
import sort.PlayerScore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * 排行榜窗体
 */
public class SortFrame extends JFrame implements KeyListener {

    /**玩家得分集合*/
    private static List<PlayerScore> sortList;
    /**排行榜文件操作对象*/
    private static FilePress f = new FilePress();

    static{
        sortList = f.readFile();
    }


    public SortFrame(){
        this.setLayout(null);

        TextLabel titleLabel = new TextLabel("排  行  榜", 350, 100, this);
        titleLabel.setForeground(Color.red);
        titleLabel.setFont(new Font("华文行楷", Font.BOLD, 50));
        titleLabel.setSize(260, 50);

        this.showScore();

        JLabel backImageLabel = new JLabel(new ImageIcon("image/lose.jpg"));
        backImageLabel.setBounds(0, -30, 950, 700);
        this.add(backImageLabel);

        this.addKeyListener(this);

        this.setSize(950, 700);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

    }


    /**
     * 显示排行榜信息
     */
    private void showScore(){
        for(int i = 0; i < sortList.size(); i++ ){

            PlayerScore p = sortList.get(i);
            int y = 180 + i*80;
//            new TextLabel("", 100, y, this);
            new TextLabel(p.getName(), 350, y, this);
            new TextLabel(String.valueOf(p.getScore()), 600, y, this);

        }

    }


    /**
     * 得到排行榜信息的最低分
     * @return 排行榜信息的最低分
     */
    public static int getLowest(){
        return sortList.get(sortList.size() - 1).getScore();

    }

    /**
     * 添加玩家得分信息
     * @param p 玩家得分对象
     */
    public static void addScore(PlayerScore p){
        f.writeFile(p, sortList);
    }


    public static void main(String[] args) {
        new SortFrame();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_ESCAPE){
            this.dispose();
            new MenuFrame();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
