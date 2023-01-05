import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {

    private Grid grid;

    AppFrame(){

        this.setSize(500,650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        grid = new Grid();

        this.add(grid, BorderLayout.CENTER);

        this.setVisible(true);

    }
}
