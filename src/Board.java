import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Board extends JPanel{
    Field[][] fields;
    int xSize, ySize;
    int bombs;

    public Board(int x, int y, int bombs){
        fields = new Field[x][y];
        this.xSize = x;
        this.ySize = y;
        this.bombs = bombs;

        setLayout(new GridLayout(xSize, ySize));
        setBackground(Color.GREEN);

        generateEmptyFields();

        if(bombs > x*y-1){
            System.out.println("Za dużo bomb");
        } else {
            generateBombs(bombs);
            Colors.initColors();
            calculateFields();
            for(int i = 0; i < fields.length; i++){
                for(int j = 0; j < fields[0].length; j++){
                    add(fields[i][j]);
                    int finalI = i;
                    int finalJ = j;
                    fields[i][j].addActionListener(e -> {
                        fields[finalI][finalJ].setFont(new Font("Franklin Gothic", Font.BOLD, 20));
                        fields[finalI][finalJ].setForeground(fields[finalI][finalJ].color);
                        fields[finalI][finalJ].setEnabled(false);
                        if(fields[finalI][finalJ].value == 10) fields[finalI][finalJ].setText("#");
                        else if(fields[finalI][finalJ].value == 0) fields[finalI][finalJ].setText(" ");
                        else fields[finalI][finalJ].setText(String.valueOf(fields[finalI][finalJ].value));
                    });
                }
            }
        }
    }

    public void generateBombs(int amount){
        int generated = 0;
        while(generated < amount){
            Random random = new Random();
            int x = random.nextInt(xSize);
            int y = random.nextInt(ySize);
            if(fields[x][y].value != 10 && fields[x][y] != null){
                fields[x][y].value = 10;
                generated++;
            }
        }
    }

    public void generateEmptyFields(){
        for(int x = 0; x < xSize; x++){
            for(int y = 0; y < ySize; y++){
                fields[x][y] = new Field(0, x, y, Colors.get(0), new Dimension(10, 10));
                fields[x][y].setBackground(Color.DARK_GRAY);
            }
        }
    }

    public void calculateFields(){
        for(int x = 0; x < xSize; x++){
            for(int y = 0; y < ySize; y++){
                if(fields[x][y].value != 10){
                    int counter = 0;
                    if(fieldExists(x-1, y-1) && fields[x-1][y-1].value == 10) counter++;
                    if(fieldExists(x, y-1) && fields[x][y-1].value == 10) counter++;
                    if(fieldExists(x+1, y-1) && fields[x+1][y-1].value == 10) counter++;
                    if(fieldExists(x-1, y) && fields[x-1][y].value == 10) counter++;
                    if(fieldExists(x+1, y) && fields[x+1][y].value == 10) counter++;
                    if(fieldExists(x-1, y+1) && fields[x-1][y+1].value == 10) counter++;
                    if(fieldExists(x, y+1) && fields[x][y+1].value == 10) counter++;
                    if(fieldExists(x+1, y+1) && fields[x+1][y+1].value == 10) counter++;
                    fields[x][y].value = counter;
                    fields[x][y].color = Colors.colors.get(counter);
                }
            }
        }
    }

    public boolean fieldExists(int x, int y){
        return x >= 0 && y >= 0 && x < fields.length && y < fields[0].length;
    }

    public void printBoard(){
        for(int x = 0; x < xSize; x++){
            for(int y = 0; y < ySize; y++){
                if(fields[x][y].value == 0) System.out.print(Colors.c0CT);
                if(fields[x][y].value == 1) System.out.print(Colors.c1CT);
                if(fields[x][y].value == 2) System.out.print(Colors.c2CT);
                if(fields[x][y].value == 3) System.out.print(Colors.c3CT);
                if(fields[x][y].value == 4) System.out.print(Colors.c4CT);
                if(fields[x][y].value == 5) System.out.print(Colors.c5CT);
                if(fields[x][y].value == 6) System.out.print(Colors.c6CT);
                if(fields[x][y].value == 7) System.out.print(Colors.c7CT);
                if(fields[x][y].value == 8) System.out.print(Colors.c8CT);
                if(fields[x][y].value == 10) System.out.print(Colors.bombColorCT);
                if(fields[x][y].value == 10) System.out.print("# ");
                else if(fields[x][y].value == 0) System.out.print("  ");
                else System.out.print(fields[x][y].value + " ");
            }
            System.out.print("\n");
        }
    }
}
