import java.util.Scanner;
import java.util.stream.Collectors;

public class GameFlow {
    Player selected;
    GameBoard gameBoard;

    GameFlow(Player selected, GameBoard gameBoard) {
        this.selected = selected;
        this.gameBoard = gameBoard;
//        System.out.println(gameBoard); //behaves like sort
        this.gameBoard.tiles = this.gameBoard.tiles.stream().sorted().collect(Collectors.toList());
        selected.initialize(new Position(1,2));
        gameBoard.replacePosition(selected);
//        System.out.println(gameBoard);
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        char c;
        do {
            System.out.println(gameBoard);
            c = scanner.next().charAt(0);
            switch (c) {
                case 'w': {
                    int newY = selected.position.y - 1;
                    int newX = selected.position.x;
                    int calc = newY * gameBoard.boardWidth + newX;
                    Tile newT = gameBoard.tiles.get(calc);
                    if(!newT.toString().equals("#"))
                        gameBoard.switchPosition(newT, selected, -1);
//                    gameBoard.switchPosition(gameBoard.tiles.get(calc), selected, -1);
//                    selected.position.y = selected.position.y - 1;
                    break;
                }
                case 'a': {
//                    selected.position.x = selected.position.x - 1;

                    int newY = selected.position.y;
                    int newX = selected.position.x - 1;
                    int calc = newY * gameBoard.boardWidth + newX;

                    Tile newT = gameBoard.tiles.get(calc);
                    if(!newT.toString().equals("#"))
                        gameBoard.switchPosition(newT, selected, -1);
//                    gameBoard.switchPosition(gameBoard.tiles.get(calc), selected, -1);
                    break;
                }
                case 's': {
//                    selected.position.y = selected.position.y + 1;

                    int newY = selected.position.y + 1;
                    int newX = selected.position.x;
                    int calc = newY * gameBoard.boardWidth + newX;
                    Tile newT = gameBoard.tiles.get(calc);
                    if(!newT.toString().equals("#"))
                        gameBoard.switchPosition(newT, selected, 0);
//                    gameBoard.switchPosition(gameBoard.tiles.get(calc), selected, 0);
                    break;
                }
                case 'd': {
                    int newY = selected.position.y;
                    int newX = selected.position.x + 1;
                    int calc = newY * gameBoard.boardWidth + newX;
                    Tile newT = gameBoard.tiles.get(calc);
                    if(!newT.toString().equals("#"))
                        gameBoard.switchPosition(newT, selected, 0);
//                    gameBoard.switchPosition(gameBoard.tiles.get(newY * gameBoard.boardWidth + newX), selected, 0);
                    break;
                }
                default: {break;}
            }
        } while(c != 'q');
    }
}
