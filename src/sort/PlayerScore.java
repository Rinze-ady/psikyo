package sort;

/**
 * 得分信息类
 */
public class PlayerScore {
    /**玩家姓名*/
    private String name;
    /**玩家得分*/
    private int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "PlayerScore{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
