import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    int xSize, ySize;
    int xFieldSize, yFieldSize;

    public GamePanel(int x, int y){
        xSize = x;
        ySize = y;
        xFieldSize = calculateFieldSize().width;
        yFieldSize = calculateFieldSize().height;
        System.out.println(xFieldSize + " " + yFieldSize);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public Dimension calculateFieldSize(){
        return new Dimension((int)Math.floor((double)getWidth()/xSize), (int)Math.floor((double)getHeight()/ySize));
    }
}
