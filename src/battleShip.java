
import java.util.Scanner;

public class battleShip {
    private static final int BOARD_SIZE = 9;
    private static final char EMPTY_CELL = '-';
    private static final char SHIP_CELL = 'S';
    private static final char HIT_CELL = 'X';
    private static final char MISS_CELL = 'O';

    private char[][] board;

    public battleShip() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
    }

    private void printBoard() {
        System.out.println("  1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    private void placeShips() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the number of ships you want to place");
        int numberOfShips = s.nextInt();
        s.nextLine();
        System.out.println("Where do you want to place ships?");
        System.out.println("Example: A1, B5, F9");

        for (int i = 0; i <= numberOfShips; i++) {
            String choose = s.nextLine().toUpperCase();
            if (choose.length() != 2 || choose.charAt(0) < 'A' || choose.charAt(0) >= 'A' + BOARD_SIZE ||
                    choose.charAt(1) < '1' || choose.charAt(1) > '0' + BOARD_SIZE) {
                System.out.println("Invalid input. Try again.");
                continue;
            }

            int shipRow = choose.charAt(0) - 'A';
            int shipCol = choose.charAt(1) - '1';
            board[shipRow][shipCol] = SHIP_CELL;
        }
    }


    private boolean isHit(int row, int col) {
        return board[row][col] == SHIP_CELL;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Battleship!");
        System.out.println("Try to sink all ships!");

        placeShips();
        printBoard();

        while (true) {
            System.out.print("Enter target (e.g., A1): ");
            String target = scanner.nextLine().toUpperCase();

            if (target.length() != 2 || target.charAt(0) < 'A' || target.charAt(0) >= 'A' + BOARD_SIZE ||
                    target.charAt(1) < '1' || target.charAt(1) > '0' + BOARD_SIZE) {
                System.out.println("Invalid input. Try again.");
                continue;
            }

            int row = target.charAt(0) - 'A';
            int col = target.charAt(1) - '1';

            if (isHit(row, col)) {
                System.out.println("Hit!");
                board[row][col] = HIT_CELL;
            } else {
                System.out.println("Miss!");
                board[row][col] = MISS_CELL;
            }

            printBoard();

            boolean allShipsSunk = true;
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (board[i][j] == SHIP_CELL) {
                        allShipsSunk = false;
                        break;
                    }
                }
            }

            if (allShipsSunk) {
                System.out.println("Congratulations! You sank the ship!");
                break;
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        battleShip game = new battleShip();
        game.play();
    }
}
