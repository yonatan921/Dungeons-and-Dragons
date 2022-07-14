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
            Position newPosition = new Position(selected.position.x, selected.position.y);
            switch (c) {
                case 'w': {
                    newPosition.moveUp();

                    Tile newT = reOrganizedBoard(newPosition);
                    selected.interact(newT);
                    break;
                }
                case 'a': {
                    newPosition.moveLeft();

                    Tile newT = reOrganizedBoard(newPosition);
                    selected.interact(newT);
                    break;
                }
                case 's': {
                    newPosition.moveDown();

                    Tile newT = reOrganizedBoard(newPosition);
                    selected.interact(newT);
                    break;
                }
                case 'd': {
                    newPosition.moveRight();

                    Tile newT = reOrganizedBoard(newPosition);
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
                    Tile newT = reOrganizedBoard(newEnemyPosition);
                    enemy.interact(newT);
                }
                selected.gameTick();

        } while(c != 'q');
    }

    private Tile reOrganizedBoard(Position position){
        int calc = position.y * gameBoard.boardWidth + position.x;
        return gameBoard.tiles.get(calc);
    }
}
