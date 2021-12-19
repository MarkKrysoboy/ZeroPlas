import java.util.Scanner;
import java.util.SimpleTimeZone;

public class Test {
    static int SIZE = 4;

    static void displayMatrix(String[][] matrix, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%3s", matrix[i][j]);
            }
            System.out.println();
        }
    }

    static boolean checkLine(String[] line) {
        boolean check = true;
        for (int i = 0; i < SIZE; i++) {
            if (line[i].equals("-") || !line[i].equals(line[0])) {
                check = false;
                break;
            }
        }
        return check;
    }

    static String checkGame(String[][] matrix) {
        String winner = "-";
        for (String[] str : matrix) {
            if (checkLine(str)) {
                winner = str[0];
                return winner;

            }
        }

        String[] tempArr = new String[SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                tempArr[j] = matrix[j][i];
            }
            if (checkLine(tempArr)) {
                winner = tempArr[0];
                return winner;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            tempArr[i] = matrix[i][i];
            if (checkLine(tempArr)) {
                winner = tempArr[0];
                return winner;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            tempArr[i] = matrix[i][SIZE - 1  - i];
            if (checkLine(tempArr)) {
                winner = tempArr[0];
                return winner;
            }
        }

        return winner;
    }


    public static void main(String[] args) {

        int maxStep = SIZE * SIZE;
        int currentStep = 0;
        int x = -1;
        int y = -1;
        String currentGamer = "X";

        String[][] gameField = new String[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                gameField[i][j] = "-";
            }
        }

        System.out.println("Игра \"Крестики - нолики\"");
        System.out.println();

        displayMatrix(gameField, SIZE);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (currentStep == maxStep) {
                System.out.println();
                System.out.println("Игра окончена. Ничья!!!");
                break;
            }

            System.out.println();
            System.out.println("Ход игрока 1, введите через пробел координаты " + currentGamer);
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;

            if ((x + 1 <= SIZE) & (y + 1 <= SIZE)) {
                if (gameField[x][y] != "X" & gameField[x][y] != "O") {
                    gameField[x][y] = currentGamer;
                    displayMatrix(gameField, SIZE);
                    currentStep++;
                    currentGamer = currentGamer == "X" ? "O" : "X";
                    String winner = checkGame(gameField);
                    System.out.println(winner);

                    if (!winner.equals("-")) {
                        System.out.println();
                        System.out.println("Победили " + winner + "!!!");
                        break;
                    }

                } else {
                    System.out.println("Данное поле уже заполнено!!!");
                }
            } else {
                System.out.println("Такие координаты не существуют!!! Введите координаты от 1 до " + SIZE);
            }
        }
    }
}


