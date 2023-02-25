import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    JButton resetButton = new JButton("RESET");
    JButton toggleButton = new JButton("TOGGLE");

    public MenuPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        add(resetButton, gbc);
        add(toggleButton, gbc);

        resetButton.addActionListener(e -> {
            Window.board = new Board(Window.board.xSize, Window.board.ySize, Window.board.bombs);
        });
    }
}
