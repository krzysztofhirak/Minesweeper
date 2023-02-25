import javax.swing.*;

public class Window extends JFrame{
    private JPanel mainPanel;
    private JPanel gamePanel;
    private JPanel menuPanel;

    private final int xSize = 16;
    private final int ySize = 16;
    private final int bombs = 48;

    Board board;

    public Window(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Minesweeper");
        setResizable(false);
        setSize(600, 800);
        add(mainPanel);

        board = new Board(xSize, ySize, bombs);
        gamePanel = new GamePanel(xSize, ySize);
        getContentPane().add(gamePanel);
    }
}
