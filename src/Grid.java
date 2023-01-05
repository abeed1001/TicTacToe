import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class Grid extends JPanel implements ActionListener{

    private final JButton[] buttons = new JButton[9];
    private final Random ran = new Random();
    private JLabel winText;


    public Grid(){
        this.setPreferredSize(new Dimension(500,500));
        this.setLayout(new GridLayout(4,3,10,10));

        //Game Text
        winText = new JLabel("");
        winText.setPreferredSize(new Dimension(200, 50));
        winText.setFont(new Font("Sans-serif", Font.BOLD, 20));
        winText.setHorizontalAlignment(JLabel.CENTER);

        //Making grid (9 squares)
        for(int i=0;i<9;i++){
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            this.add(buttons[i]);
        }

        this.add(winText);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i=0; i < 9; i++) {
            if (e.getSource().equals(buttons[i])) {
                //Player input
                winText.setText("Play!");
                if(buttons[i].getText().equals("")) {
                    buttons[i].setText("X");

                    //Check if board full
                    if(boardFull(buttons)){
                        winText.setText("Tie!");
                        resetBoard(buttons);
                    }

                    //Check if X won
                    if (isWinner("X", buttons)) {
                        System.out.println("X Won!");
                        winText.setText("X Won!");
                        resetBoard(buttons);
                    }

                    //Bot input
                    while (true) {
                        int random = ran.nextInt(9);
                        if (buttons[random].getText().equals("")) {
                            buttons[random].setText("O");
                            break;
                        }
                    }

                    //Check if board full
                    if(boardFull(buttons)){
                        winText.setText("Tie!");
                        resetBoard(buttons);
                    }

                    //Check if O won
                    if (isWinner("O", buttons)) {
                            System.out.println("O Won!");
                            winText.setText("O Won!");
                            resetBoard(buttons);
                    }
                }
            }
        }
    }

    public boolean isWinner (String character, JButton[] board) {
        for (int i = 0; i < 9; i++) {
            if ((i < 3 && board[i].getText().equals(character)
                    && board[i + 3].getText().equals(character)
                    && board[i + 6].getText().equals(character))
            || (i % 3 == 0 && board[i].getText().equals(character)
                    && board[i + 1].getText().equals(character)
                    && board[i + 2].getText().equals(character))
            || (board[0].getText().equals(character)
                    && board[4].getText().equals(character)
                    && board[8].getText().equals(character))
            || (board[2].getText().equals(character)
                    && board[4].getText().equals(character)
                    && board[6].getText().equals(character))
            ) {
                return true;
            }
        }
        return false;
    }

    //Checks if board is full
    public boolean boardFull(JButton[] buttons){
        for (int i = 0; i < 9; i++){
            if(buttons[i].getText().equals("")){
                return false;
            }
        }
        return true;
    }

    //Empties boards
    public void resetBoard (JButton[] buttons){

        for(int j=0;j<9;j++){
            buttons[j].setText("");
        }

        revalidate();
        repaint();

    }


}
