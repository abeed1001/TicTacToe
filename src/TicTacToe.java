import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);
        boolean checkEmptySpaceLoop;
        String gameLoopCheck;
        String playOptionInput = "Y";
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


                checkEmptySpaceLoop = false;
                while(!checkEmptySpaceLoop){
                    System.out.println("Pick from 0-8");
                    try{
                        byte squareChoiceInput = scan.nextByte();
                        checkEmptySpaceLoop = checkIfSpaceEmpty("x",squareChoiceInput,row1,row2,row3);

                    }catch (Exception e){
                        scan.next();
                    }
                }


                //Checking win combos
                gameLoopCheck = checkWinCombos(arrRows,row1,row2,row3);


                if (!Arrays.asList(row1).contains(" ") && !Arrays.asList(row2).contains(" ") && !Arrays.asList(row3).contains(" ")) {
                    System.out.println("Game over. Board is full.");
                    break;
                }

                //Bot input
                checkEmptySpaceLoop = false;
                while(!checkEmptySpaceLoop){
                    checkEmptySpaceLoop = checkIfSpaceEmpty("o", (byte) ran.nextInt(9),row1,row2,row3);

                }

                //Checking win combos
                gameLoopCheck = checkWinCombos(arrRows,row1,row2,row3);
                //Print board
                printBoard(arrRows);

            }
        }
    }

    //Print board
    public static void printBoard(String[][] arrRows){
        for(String[] r : arrRows){
            System.out.println(Arrays.toString(r));
        }
    }

    //Check if selected board space is empty
    public static boolean checkIfSpaceEmpty(String player, byte squareChoiceInput, String[] row1, String[] row2, String[] row3) {

            if (squareChoiceInput < 0 || squareChoiceInput > 8) {
                return false;
            } else if (squareChoiceInput == 0 || squareChoiceInput == 1 || squareChoiceInput == 2) {
                if (row1[squareChoiceInput].equals(" ")) {
                    row1[squareChoiceInput] = player;
                    return true;
                }
            } else if (squareChoiceInput == 3 || squareChoiceInput == 4 || squareChoiceInput == 5) {
                if (row2[squareChoiceInput - 3].equals(" ")) {
                    row2[squareChoiceInput - 3] = player;
                    return true;
                }
            } else if (squareChoiceInput == 6 || squareChoiceInput == 7 || squareChoiceInput == 8) {
                if (row3[squareChoiceInput - 6].equals(" ")) {
                    row3[squareChoiceInput - 6] = player;
                    return true;
                }
            }
            return false;
    }

    //Check win combos
    public static String checkWinCombos(String[][] arrRows, String[] row1, String[] row2, String[] row3){

        //If winner exit play loop
        if (row1[0].equals(row2[1]) && row2[1].equals(row3[2]) && (row1[0].equals("x") || row1[0].equals("o"))) {
            System.out.println(row1[0] + " wins!");
            return "N";

        } else if (row1[2].equals(row2[1]) && row2[1].equals(row3[0]) && (row1[2].equals("x") || row1[2].equals("o"))) {
            System.out.println(row1[2] + " wins!");
            return "N";
        } else {
            for (int i = 0; i < 3; i++) {
                if (Arrays.stream(arrRows[i]).distinct().count() == 1 && (!arrRows[i][0].equals(" "))) { //Check row
                    System.out.println(arrRows[i][0] + " wins!");
                    return "N";
                } else if (row1[i].equals(row2[i]) && row2[i].equals(row3[i]) && (!row1[i].equals(" "))) { //Check column
                    System.out.println(row1[i] + " wins!");
                    return "N";
                }
            }
        }
        //No winner keep playing
        return "Y";
    }
}