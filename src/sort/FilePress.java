package sort;

import java.io.*;
import java.util.*;

/**
 * 操作排行榜文件
 */
public class FilePress {

    /**
     * 读取排行榜文件信息
     * @return 玩家得分集合
     */
    public List<PlayerScore> readFile() {
        List<PlayerScore> scoreList = new ArrayList<PlayerScore>();

        Reader r = null;
        BufferedReader br = null;

        try {
            r = new FileReader("data/sort.txt");
            br = new BufferedReader(r);

            String str = null;
            while((str = br.readLine()) != null){
                PlayerScore p = new PlayerScore();
                String[] array = str.split("\\s+");
                p.setName(array[0]);
                p.setScore(Integer.parseInt(array[1]));

                scoreList.add(p);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                br.close();
                r.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return scoreList;
    }


    public void writeFile(PlayerScore p, List<PlayerScore> sortList){
        sortList.add(p);

        Collections.sort(sortList, new Comparator<PlayerScore>() {
            @Override
            public int compare(PlayerScore o1, PlayerScore o2) {
                return o2.getScore() - o1.getScore();
            }
        });

        sortList.remove(sortList.size() - 1);

        Writer w = null;

        try {
            w = new FileWriter("data/sort.txt");
            for(PlayerScore ps : sortList){
                w.write(ps.getName() + "   " + ps.getScore() + "\n") ;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                w.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        FilePress f = new FilePress();
        System.out.println(f.readFile());

    }



}
