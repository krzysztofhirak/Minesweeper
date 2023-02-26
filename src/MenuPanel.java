import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    JButton resetButton = new JButton("RESET");
    JButton toggleButton = new JButton("SET SAFE");

    public MenuPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        add(resetButton, gbc);
        add(toggleButton, gbc);

        resetButton.addActionListener(e -> { //TODO NOT WORKING YET
            Window.board = new Board(Window.board.xSize, Window.board.ySize, Window.board.bombs);
        });

        toggleButton.addActionListener(e -> {
            Board.bombState = !Board.bombState;
            if(Board.bombState){
                toggleButton.setText("SET BOMB");
                toggleButton.setBackground(Color.RED);
            }
            else {
                toggleButton.setText("SET SAFE");
                toggleButton.setBackground(Color.WHITE);
            }
        });
    }
}
