import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        WOFFrame wofFrame = new WOFFrame();     //calls wofFrame method
        wofFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        //exits when closed
        wofFrame.pack();
        wofFrame.setVisible(true);
    }
}