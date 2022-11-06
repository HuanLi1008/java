import java.util.*;
import java.io.*;

public class Battleship {
        public static void main(String[] args) {

            System.out.println("Welcome to Battleship!");
            Scanner input = new Scanner(System.in);
            int[] player = {1,2};

            char[][]shipPlayer1 = playerShip(player[0],input);
            char[][]shipPlayer2 = playerShip(player[1],input);
            char[][]trackBoard1 = new char[5][5];
            char[][]trackBoard2 = new char[5][5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    trackBoard1[i][j]='-';
                    trackBoard2[i][j]='-';
                }
            }
            int countShip;
            for (int i = 0; i < 5 * 5 *2; i++) {
                countShip = 0;
                if (i%2 == 0){
                    hitOrMiss(player[0],player[1],shipPlayer2,trackBoard2,input);
                    for (int j = 0; j < 5; j++) {
                        for (int k = 0; k < 5; k++) {
                            if (shipPlayer2[j][k] == '@'){
                                countShip++;
                            }
                        }
                    }
                    if(countShip == 0){
                        System.out.println("PLAYER "+player[0]+" WINS! YOU SUNK ALL OF YOUR OPPONENT’S SHIPS!");
                        break;
                    }
                }
                else {
                    hitOrMiss(player[1],player[0],shipPlayer1,trackBoard1,input);
                    for (int j = 0; j < 5; j++) {
                        for (int k = 0; k < 5 ; k++) {
                            if (shipPlayer1[j][k] == '@'){
                                countShip++;
                            }
                        }
                    }
                    if(countShip == 0){
                        System.out.println("PLAYER "+player[1]+" WINS! YOU SUNK ALL OF YOUR OPPONENT’S SHIPS!");
                        break;
                    }
                }
            }
            System.out.println("Final boards:");
            printBattleShip(shipPlayer1);
            System.out.println(" ");
            printBattleShip(shipPlayer2);

        }
        // prompt users to get coordinates
    public static char[][] playerShip(int numPlayer,Scanner input){
        char [][] ship = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ship[i][j] = '-';
            }

        }
        String error = "Invalid coordinates. Choose different coordinates.";
        System.out.println("PLAYER "+numPlayer+", ENTER YOUR SHIPS’ COORDINATES.");
        int numShip = 1;
        while (numShip <= 5) {
            int a = -1;
            int b = -1;
            while (a < 0 ||a >4 ||b < 0 || b > 4){
                System.out.println("Enter ship "+numShip+" location:");
                if(input.hasNextInt()){
                    a = input.nextInt();
                    if(input.hasNextInt()) {
                        b = input.nextInt();
                        if (a >4 || a < 0||b > 4 || b < 0) {
                            System.out.println(error);
                            input.nextLine();
                            continue;
                        }
                    }
                }
                else{
                    System.out.println(error);

                }
                input.nextLine();
            }
            if(ship[a][b]!='@'){
                ship[a][b] = '@';
                numShip++;
            }
            else{
                System.out.println("You already have a ship there. Choose different coordinates.");
            }

        }
        printBattleShip(ship);
        for (int i = 0; i < 100; i++) {
            System.out.println(" ");
        }
        return ship;
    }
    //judge hit or miss
    public static void hitOrMiss(int numPlayer,int opponent, char[][] ship,char[][]trackBoard, Scanner input){
        String error = "Invalid coordinates. Choose different coordinates.";

        int count = 0;
        do {
            System.out.println("Player "+numPlayer+", enter hit row/column:");
            int a;
            int b;

            if(input.hasNextInt()){
                a = input.nextInt();
                if(input.hasNextInt()){
                    b = input.nextInt();
                    if(a>=0&&a<=4&&b>=0&&b<=4){
                        switch (ship[a][b]){
                            case '@':
                                trackBoard[a][b] = 'X';
                                ship[a][b] = 'X';
                                System.out.println("PLAYER "+numPlayer+" HIT PLAYER "+opponent+"’s SHIP!");
                                count = 1;
                                break;
                            case '-':
                                trackBoard[a][b] = 'O';
                                ship[a][b] = 'O';
                                System.out.println("PLAYER "+numPlayer+" MISSED!");
                                count = 2;
                                break;
                            case 'X':
                                System.out.println("You already fired on this spot. Choose different coordinates.");

                                break;
                            case 'O':
                                System.out.println("You already fired on this spot. Choose different coordinates.");

                                break;
                        }

                    }
                    else {
                        System.out.println(error);

                    }
                }
                input.nextLine();
            }
            else{
                System.out.println(error);
                input.nextLine();
            }

        }while (count == 0);
        printBattleShip(trackBoard);


    }

        // Use this method to print game boards to the console.
        private static void printBattleShip(char[][] player) {
            System.out.print("  ");
            for (int row = -1; row < 5; row++) {
                if (row > -1) {
                    System.out.print(row + " ");
                }
                for (int column = 0; column < 5; column++) {
                    if (row == -1) {
                        System.out.print(column + " ");
                    } else {
                        System.out.print(player[row][column] + " ");
                    }
                }
                System.out.println("");
            }
        }

}

