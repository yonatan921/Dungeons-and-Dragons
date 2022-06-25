import java.util.Scanner;
import java.util.stream.Collectors;

public class GameFlow {
    Player selected;
    GameBoard gameBoard;

    GameFlow(Player selected, GameBoard gameBoard) {
        this.selected = selected;
        this.gameBoard = gameBoard;
        this.gameBoard.tiles = this.gameBoard.tiles.stream().sorted().collect(Collectors.toList());
    }

    public void startGame() {
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
                default: {break;}
            }
        } while(c != 'q');
    }
}
