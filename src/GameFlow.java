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
        System.out.println(gameBoard);
        char c;
        do {

            System.out.println(selected.describe());
            c = scanner.next().charAt(0); //TODO: if more than one char, keep listennign until valid input!
            Position newPosition = new Position(selected.getPosition().getX(), selected.getPosition().getY());
            switch (c) {
                case 'w': {
                    newPosition.moveUp();
                    playerTick(newPosition);
                    break;
                }
                case 'a': {
                    newPosition.moveLeft();
                    playerTick(newPosition);
                    break;
                }
                case 's': {
                    newPosition.moveDown();
                    playerTick(newPosition);
                    break;
                }
                case 'd': {
                    newPosition.moveRight();
                    playerTick(newPosition);
                    break;
                }
                case 'e':{
                    selected.castAbility(levelManager.getEnemies());
                }
                default: {break;}
                }
                gameBoard.tiles = gameBoard.tiles.stream().sorted().collect(Collectors.toList()); // Todo crete function
                for (Enemy enemy : levelManager.getEnemies()){
                    enemy.gameTick(selected);
                    Position newEnemyPosition = enemy.move(selected);
                    Tile newT = reOrganizedBoard(newEnemyPosition);
                    enemy.interact(newT);
                }
                if (levelManager.getEnemies().size() == 0)
                    gameBoard.advanceLevel();

                System.out.println(gameBoard);
        } while(!(selected.getHealthAmount() == 0 || levelManager.won) );
            if (levelManager.won)
                System.out.println("You WON!!!");
    }

    private Tile reOrganizedBoard(Position position){
        int calc = position.getY() * gameBoard.boardWidth + position.getX();
        return gameBoard.tiles.get(calc);
    }

    private void playerTick(Position newPosition){
        Tile newT = reOrganizedBoard(newPosition);
        selected.interact(newT);
        selected.gameTick();
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
