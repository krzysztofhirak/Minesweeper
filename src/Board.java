import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Board extends JPanel{
    Field[][] fields;
    int xSize, ySize;
    int bombs;
    public static boolean bombState = false;
    boolean firstClick = true;
    LinkedList<Field> checkedList = new LinkedList<>();
    LinkedList<Field> toCheck = new LinkedList<>();
    LinkedList<Field> checked = new LinkedList<>();

    public Board(int x, int y, int bombs){
        fields = new Field[x][y];
        this.xSize = x;
        this.ySize = y;
        this.bombs = bombs;

        setLayout(new GridLayout(xSize, ySize));
        setBackground(Color.GREEN);

        generateEmptyFields();
        Colors.initColors();

        if(bombs > x*y-1){
            System.out.println("Za dużo bomb");
        } else {
            for(int i = 0; i < fields.length; i++){
                for(int j = 0; j < fields[0].length; j++){
                    add(fields[i][j]);
                    int finalI = i;
                    int finalJ = j;
                    fields[i][j].addActionListener(e -> {
                        if(!bombState){
                            if(!fields[finalI][finalJ].bombMarked){
                                fields[finalI][finalJ].setEnabled(true);
                                if(firstClick){
                                    boolean boardIsValid = false;
                                    while(!boardIsValid){
                                        generateBombsAndCalculateFields();
                                        if(fields[finalI][finalJ].value != 10){
                                            boardIsValid = true;
                                        }
                                    }
                                    firstClick = false;
                                }
                                showField(fields[finalI][finalJ]);
                                if(fields[finalI][finalJ].value == 0) checkSurrounding(fields[finalI][finalJ]);
                            } else fields[finalI][finalJ].setEnabled(false);
                        } else {
                            markBomb(fields[finalI][finalJ]);
                        }
                    });
                }
            }
        }
    }

    public void markBomb(Field field){
        fields[field.x][field.y].setFont(new Font("Franklin Gothic", Font.BOLD, 20));
        if(!fields[field.x][field.y].bombMarked){
            fields[field.x][field.y].setBackground(Colors.get(10));
            fields[field.x][field.y].setForeground(Color.LIGHT_GRAY);
            fields[field.x][field.y].setText("B");
        } else {
            fields[field.x][field.y].setBackground(Color.DARK_GRAY);
            fields[field.x][field.y].setText(" ");
        }
    }

    public void showField(Field field){
        fields[field.x][field.y].setFont(new Font("Franklin Gothic", Font.BOLD, 20));
        fields[field.x][field.y].setForeground(fields[field.x][field.y].color);
        fields[field.x][field.y].setEnabled(false);
        fields[field.x][field.y].revealed = true;
        if(fields[field.x][field.y].value == 10) fields[field.x][field.y].setText("#");
        else if(fields[field.x][field.y].value == 0) fields[field.x][field.y].setText(" ");
        else fields[field.x][field.y].setText(String.valueOf(fields[field.x][field.y].value));
    }

    public void generateBombsAndCalculateFields(){
        generateBombs(bombs);
        calculateFields();
    }

    public void generateBombs(int amount){
        int generated = 0;
        while(generated < amount){
            Random random = new Random();
            int x = random.nextInt(xSize);
            int y = random.nextInt(ySize);
            if(fields[x][y].value != 10 && fields[x][y] != null && fields[x][y].isEnabled()){
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

    public void checkSurrounding(Field field){
        int x = field.x;
        int y = field.y;
        LinkedList<Field> surrounding = new LinkedList<>();
        if(fieldExists(x-1, y-1)) surrounding.add(fields[x-1][y-1]);
        if(fieldExists(x, y-1)) surrounding.add(fields[x][y-1]);
        if(fieldExists(x+1, y-1)) surrounding.add(fields[x+1][y-1]);
        if(fieldExists(x-1, y)) surrounding.add(fields[x-1][y]);
        if(fieldExists(x+1, y)) surrounding.add(fields[x+1][y]);
        if(fieldExists(x-1, y+1)) surrounding.add(fields[x-1][y+1]);
        if(fieldExists(x, y+1)) surrounding.add(fields[x][y+1]);
        if(fieldExists(x+1, y+1)) surrounding.add(fields[x+1][y+1]);

        for (Field sf : surrounding){
            showField(sf);
            sf.revealed = true;
            if(sf.value == 0 && !checked.contains(sf)){
                toCheck.add(sf);
            }
        }
        checked.add(fields[x][y]);
        toCheck.remove(fields[x][y]);
        for (Field f2 : toCheck){
//            System.out.println(toCheck.size());
            checkSurrounding(f2);
        }
    }

//    public void checkSurrounding(Field field){
//        if(fieldExists(field.x -1, field.y-1) && fields[field.x-1][field.y-1].value == 0 && !checkedList.contains(fields[field.x-1][field.y-1])){
//            checkSurrounding(fields[field.x-1][field.y-1]);
//        }
//        else if(fieldExists(field.x, field.y-1) && fields[field.x][field.y-1].value == 0 && !checkedList.contains(fields[field.x][field.y-1])){
//            checkSurrounding(fields[field.x][field.y-1]);
//        }
//        else if(fieldExists(field.x+1, field.y-1) && fields[field.x+1][field.y-1].value == 0 && !checkedList.contains(fields[field.x+1][field.y-1])){
//            checkSurrounding(fields[field.x+1][field.y-1]);
//        }
//        else if(fieldExists(field.x-1, field.y) && fields[field.x-1][field.y].value == 0 && !checkedList.contains(fields[field.x-1][field.y])){
//            checkSurrounding(fields[field.x-1][field.y]);
//        }
//        else if(fieldExists(field.x+1, field.y) && fields[field.x+1][field.y].value == 0 && !checkedList.contains(fields[field.x+1][field.y])){
//            checkSurrounding(fields[field.x+1][field.y]);
//        }
//        else if(fieldExists(field.x-1, field.y+1) && fields[field.x-1][field.y+1].value == 0 && !checkedList.contains(fields[field.x-1][field.y+1])){
//            checkSurrounding(fields[field.x-1][field.y+1]);
//        }
//        else if(fieldExists(field.x, field.y+1) && fields[field.x][field.y+1].value == 0 && !checkedList.contains(fields[field.x][field.y+1])){
//            checkSurrounding(fields[field.x][field.y+1]);
//        }
//        else if(fieldExists(field.x+1, field.y+1) && fields[field.x+1][field.y+1].value == 0 && !checkedList.contains(fields[field.x+1][field.y+1])){
//            checkSurrounding(fields[field.x+1][field.y+1]);
//        }
//        else {
//            showField(fields[field.x][field.y]);
//            checkedList.add(fields[field.x][field.y]);
//        }
//    }
}
