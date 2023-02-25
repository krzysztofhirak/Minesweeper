import javax.swing.*;
import java.awt.*;

public class Field extends JButton {

    int value;
    int x, y;
    Color color;

    public Field(int value, int x, int y, Color color, Dimension size){
        setText("");
        setSize(size);
        this.value = value;
        this.x = x;
        this.y = y;
        this.color = color;
    }
}
