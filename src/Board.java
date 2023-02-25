import java.util.Random;

public class Board {

    Field[][] fields;
    int xSize, ySize;

    public Board(int x, int y, int bombs){
        fields = new Field[x][y];
        this.xSize = x;
        this.ySize = y;

        generateEmptyFields();

        if(bombs > x*y-1){
            System.out.println("Za dużo bomb");
        } else {
            generateBombs(bombs);
            calculateFields();
            Colors.initColors();
        }

        printBoard();
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
                fields[x][y] = new Field(0, x, y, Colors.get(0));
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
                if(fields[x][y].value == 10) System.out.print("X ");
                else if(fields[x][y].value == 0) System.out.print("  ");
                else System.out.print(fields[x][y].value + " ");
            }
            System.out.print("\n");
        }
    }
}
