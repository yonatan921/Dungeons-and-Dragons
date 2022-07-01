import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;

public class main {
    public static void main(String[] args) {
//        Random ran = new Random();
//        int x = ran.nextInt(1);
//        System.out.println(x);

//        Player p = tf.listPlayers().get(4); //Arya stark
//        GameBoard gb = new GameBoard();
//        TileFactory tf = new TileFactory();
//        Player p = tf.listPlayers().get(4); //Arya stark
//        System.out.println(e.describe());
//        Tile w = new Wall();
//        e.TMPprocessStep(w);

        Tile[][] tiles = new Tile[10][10];
//        for (Tile[] tRow: tiles) {
//            for(Tile tile : tRow) {
//                tile = new Empty();
//            }
//        }

        for (int i = 0; i < 10; i++) { //rows
            for (int j = 0; j < 10; j++) { //columns
                tiles[i][j] = new Empty();
                tiles[i][j].initialize(new Position(i,j));
            }
        }

        for (int i = 0; i < tiles.length; i++) { //rows
            for (int j = 0; j < tiles[0].length; j++) { //columns
                if(i == 0 || i == tiles.length - 1) {
                    tiles[i][j] = new Wall();
                    tiles[i][j].initialize(new Position(i,j));
                }
                if(j == 0 || j == tiles[0].length - 1) {
                    tiles[i][j] = new Wall();
                    tiles[i][j].initialize(new Position(i,j));
                }
            }
        }


//        System.out.println(gb.toString());

        GameFlow gf = new GameFlow();
        gf.startGame();

//        LevelManager lm = new LevelManager();
//        List<String> list = lm.readAllLines("D:\\POOPass3\\Dungeons-and-Dragons\\level1.txt");
//        for(String row : list) {
//            System.out.println(row);
//        String str = gb.toString();
//        System.out.println(str);

    }
}
