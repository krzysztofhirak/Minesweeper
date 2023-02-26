import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{
    int xSize = 16;
    int ySize = 16;
    int bombs = 40;
    final Dimension WINDOW_SIZE = new Dimension(600, 700);

    JPanel gamePanel = new JPanel();
    JPanel menuPanel = new MenuPanel();

    public static Board board;

    public Window(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        pack();
        setSize(WINDOW_SIZE);
        setVisible(true);
        setTitle("Minesweeper");
        setResizable(false);

        setPanels();

        board = new Board(xSize, ySize, bombs);
    }

    public void setPanels(){
        setMenuPanel();
        setGamePanel();
    }

    public void setMenuPanel(){
        menuPanel.setPreferredSize(new Dimension(WINDOW_SIZE.width, 50));
        menuPanel.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(menuPanel, BorderLayout.PAGE_START);
        menuPanel.setLayout(new FlowLayout());
    }

    public void setGamePanel(){
//        gamePanel = new GamePanel(new Dimension(xSize, ySize), gamePanel.getSize());
        gamePanel = new Board(xSize, ySize, bombs);
        gamePanel.setBackground(Color.DARK_GRAY);
        getContentPane().add(gamePanel, BorderLayout.CENTER);
    }
}
