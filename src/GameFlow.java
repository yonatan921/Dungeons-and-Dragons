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
        this.tileFactory = new TileFactory();
        int player_index = selectPlayer();
        tileFactory.selected = tileFactory.listPlayers().get(player_index);
//        this.selected = tileFactory.listPlayers().get(player_index);
        this.levelManager= new LevelManager(tileFactory);
        this.selected = levelManager.selected;
        this.gameBoard = new GameBoard(levelManager);

        tileFactory.setGameBoard(gameBoard);
        this.gameBoard.tiles = this.gameBoard.tiles.stream().sorted().collect(Collectors.toList());
        gameTick();
    }

    private void gameTick() {
        System.out.println("You have selected:");
        System.out.println(selected.getName());
        Scanner scanner = new Scanner(System.in);
        char c;
        do {
            System.out.println(gameBoard);
            System.out.println(selected.describe());
            c = scanner.next().charAt(0); //TODO: if more than one char, keep listennign until valid input!
            Position newPosition = new Position(selected.getPosition().getX(), selected.getPosition().getY());
            switch (c) {
                case 'w': {
                    newPosition.moveUp();

                    Tile newT = reOrganizedBoard(newPosition);
                    selected.interact(newT);
                    System.out.println(gameBoard.tiles.indexOf(selected));
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
                    enemy.gameTick(selected);
                    Position newEnemyPosition = enemy.move(selected);
                    Tile newT = reOrganizedBoard(newEnemyPosition);
                    enemy.interact(newT);
                }
                selected.gameTick();

        } while(c != 'p');
    }

    private Tile reOrganizedBoard(Position position){
        int calc = position.getY() * gameBoard.boardWidth + position.getX();
        return gameBoard.tiles.get(calc);
    }

    private int selectPlayer() {
        int chosen;
        int iter = 1;
        while (true){
            for (Player player : tileFactory.listPlayers()){
                System.out.println(iter+".  " + player.describe());
                iter++;
            }
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()){
                chosen = scanner.nextInt();
                if (chosen >= 1 && chosen <=tileFactory.listPlayers().size() )
                    return chosen -1;
            }
        }
    }
}
