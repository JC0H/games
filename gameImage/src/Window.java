import javax.swing.*;

public class Window {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.pack();
        frame.setSize(600,400);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new Game());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
