import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    Dimension panelSize;
    Dimension boardSize;
    Dimension fieldSize;

    public GamePanel(Dimension boardSize, Dimension panelSize){
        this.boardSize = boardSize;
        this.panelSize = panelSize;
        fieldSize = calculateFieldSize();
        System.out.println(fieldSize);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public Dimension calculateFieldSize(){
        return new Dimension((int)Math.floor((double)panelSize.width/boardSize.width), (int)Math.floor((double)panelSize.height/boardSize.height));
    }
}
