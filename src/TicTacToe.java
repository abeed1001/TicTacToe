import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String playOptionInput = "Y";
        byte squareChoiceInput;
        String gameLoopCheck;
        Random ran = new Random();



        while (playOptionInput.equals("Y")) {

            //Rows
            String[] row1 = new String[]{" ", " ", " "};
            String[] row2 = new String[]{" ", " ", " "};
            String[] row3 = new String[]{" ", " ", " "};

            //2D array
            String[][] arrRows = new String[][]{row1, row2, row3};

            System.out.println("Do you want to play Tic-Tac-Toe (Y/N)?");
            //Check if valid "(Y/N)" input
            while (true) {
                playOptionInput = scan.nextLine().toUpperCase();
                if (playOptionInput.equals("Y") || playOptionInput.equals("N")) {
                    break;
                }
                System.out.println("Enter 'Y' or 'N'");
            }


            System.out.println("[0,1,2]\n[3,4,5]\n[6,7,8]");
            gameLoopCheck = playOptionInput;
            //Main game loop
            while (gameLoopCheck.equals("Y")) {


                //Check if valid input
                while (true) {
                    try {
                        System.out.println("Pick a spot from 0-8");
                        squareChoiceInput = scan.nextByte();


                        if (squareChoiceInput < 0 || squareChoiceInput > 8) {
                            throw new Exception();
                        } else if (squareChoiceInput == 0 || squareChoiceInput == 1 || squareChoiceInput == 2) {
                            if (row1[squareChoiceInput].equals(" ")) {
                                row1[squareChoiceInput] = "x";
                                break;
                            }
                        } else if (squareChoiceInput == 3 || squareChoiceInput == 4 || squareChoiceInput == 5) {
                            if (row2[squareChoiceInput - 3].equals(" ")) {
                                row2[squareChoiceInput - 3] = "x";
                                break;
                            }
                        } else if (squareChoiceInput == 6 || squareChoiceInput == 7 || squareChoiceInput == 8) {
                            if (row3[squareChoiceInput - 6].equals(" ")) {
                                row3[squareChoiceInput - 6] = "x";
                                break;
                            }
                        }
                    } catch (InputMismatchException e) {
                        scan.next();
                    } catch (Exception e) {
                    }


                    if (!Arrays.asList(row1).contains(" ") && !Arrays.asList(row2).contains(" ") && !Arrays.asList(row3).contains(" ")) {
                        System.out.println("Game over. Board is full.");
                        gameLoopCheck = "N";
                        break;
                    }




                }

                //Enter bot choice
                while (true) {
                    int botChoiceInput = ran.nextInt(9);

                    if (botChoiceInput == 0 || botChoiceInput == 1 || botChoiceInput == 2) {
                        if (row1[botChoiceInput].equals(" ")) {
                            row1[botChoiceInput] = "o";
                            break;
                        }
                    } else if (botChoiceInput == 3 || botChoiceInput == 4 || botChoiceInput == 5) {
                        if (row2[botChoiceInput - 3].equals(" ")) {
                            row2[botChoiceInput - 3] = "o";
                            break;
                        }
                    } else if (botChoiceInput == 6 || botChoiceInput == 7 || botChoiceInput == 8) {
                        if (row3[botChoiceInput - 6].equals(" ")) {
                            row3[botChoiceInput - 6] = "o";
                            break;
                        }
                    }
                }

                //Checking win combos

                //Check diagonals
                if (row1[0].equals(row2[1]) && row2[1].equals(row3[2]) && (row1[0].equals("x") || row1[0].equals("o"))) {
                    System.out.println(row1[0] + " wins!");
                    gameLoopCheck = "N";

                } else if (row1[2].equals(row2[1]) && row2[1].equals(row3[0]) && (row1[2].equals("x") || row1[2].equals("o"))) {
                    System.out.println(row1[2] + " wins!");
                    gameLoopCheck = "N";
                } else {
                    for (int i = 0; i < 3; i++) {
                        if (Arrays.stream(arrRows[i]).distinct().count() == 1 && (arrRows[i][0].equals("x") || arrRows[i][0].equals("o"))) { //Check row
                            System.out.println(arrRows[i][0] + " wins!");
                            gameLoopCheck = "N";
                        } else if (row1[i].equals(row2[i]) && row2[i].equals(row3[i]) && (row1[i].equals("x") || row1[0].equals("o"))) { //Check column
                            System.out.println(row1[i] + " wins!");
                            gameLoopCheck = "N";
                        }
                    }
                }

                displayBoard(arrRows);

            }
        }
    }

    //Print board
    public static void displayBoard(String[][] arrRows){
        for(String[] r : arrRows){
            System.out.println(Arrays.toString(r));
        }
    }

}