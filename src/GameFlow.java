import java.util.Scanner;
import java.util.stream.Collectors;

public class GameFlow {
    Player selected;
    GameBoard gameBoard;
    TileFactory tileFactory;
    LevelManager levelManager;

    GameFlow() {


    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        int player_index = scanner.nextInt();
        this.tileFactory = new TileFactory(player_index);
//        this.selected = tileFactory.listPlayers().get(player_index);
        this.levelManager= new LevelManager(tileFactory);
        this.selected = levelManager.selected;
        this.gameBoard = new GameBoard(levelManager);

        tileFactory.setGameBoard(gameBoard);
        this.gameBoard.tiles = this.gameBoard.tiles.stream().sorted().collect(Collectors.toList());
        gameTick();
    }

    private void gameTick() {
        Scanner scanner = new Scanner(System.in);
        char c;
        do {
            System.out.println(gameBoard);
            c = scanner.next().charAt(0); //TODO: if more than one char, keep listennign until valid input!
            switch (c) {
                case 'w': {
                    int newY = selected.position.y - 1;
                    int newX = selected.position.x;
                    int calc = newY * gameBoard.boardWidth + newX;

                    Tile newT = gameBoard.tiles.get(calc);
                    selected.interact(newT);
                    break;
                }
                case 'a': {
                    int newY = selected.position.y;
                    int newX = selected.position.x - 1;
                    int calc = newY * gameBoard.boardWidth + newX;

                    Tile newT = gameBoard.tiles.get(calc);
                    selected.interact(newT);
                    break;
                }
                case 's': {
                    int newY = selected.position.y + 1;
                    int newX = selected.position.x;
                    int calc = newY * gameBoard.boardWidth + newX;

                    Tile newT = gameBoard.tiles.get(calc);
                    selected.interact(newT);
                    break;
                }
                case 'd': {
                    int newY = selected.position.y;
                    int newX = selected.position.x + 1;
                    int calc = newY * gameBoard.boardWidth + newX;

                    Tile newT = gameBoard.tiles.get(calc);
                    selected.interact(newT);
                    break;
                }
                case 'e':{
                    selected.specialAbility(levelManager.getEnemies());
                }
                default: {break;}
                }
                for (Enemy enemy : levelManager.getEnemies()){
                    Position newEnemyPosition = enemy.move(selected);
                    int calc = newEnemyPosition.y * gameBoard.boardWidth + newEnemyPosition.x;
                    Tile newT = gameBoard.tiles.get(calc);
                    enemy.interact(newT);
            }
        } while(c != 'q');
    }
}
