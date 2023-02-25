import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Colors {
    public static final Map<Integer, Color> colors = new HashMap<>();
    private static final Color c0 = new Color(180, 180, 180);
    private static final Color c1 = new Color(0, 140, 220);
    private static final Color c2 = new Color(0, 160, 0);
    private static final Color c3 = new Color(200, 0, 0);
    private static final Color c4 = new Color(0, 40, 180);
    private static final Color c5 = new Color(140, 90, 0);
    private static final Color c6 = new Color(140, 0, 100);
    private static final Color c7 = new Color(140, 140, 0);
    private static final Color c8 = new Color(0, 140, 100);
    private static final Color bombColor = new Color(0, 0, 0);

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String c0CT = "\u001B[37m";
    public static final String c1CT = "\u001B[36m";
    public static final String c2CT = "\u001B[32m";
    public static final String c3CT = "\u001B[31m";
    public static final String c4CT = "\u001B[34m";
    public static final String c5CT = "\u001B[33m";
    public static final String c6CT = "\u001B[35m";
    public static final String c7CT = "\u001B[0m";
    public static final String c8CT = "\u001B[0m";
    public static final String bombColorCT = "\u001B[30m";

    public static Color get(int num){
        return colors.get(num);
    }

    public static void initColors(){
        colors.put(0, c0);
        colors.put(1, c1);
        colors.put(2, c2);
        colors.put(3, c3);
        colors.put(4, c4);
        colors.put(5, c5);
        colors.put(6, c6);
        colors.put(7, c7);
        colors.put(8, c8);
        colors.put(10, bombColor);
    }
}
