import javax.swing.*;
import java.awt.*;

public class App {
    JFrame frame = new JFrame();

    public void configurarFrame() {
        frame.setSize(540, 550);
        frame.add(new Menu().buildMenu(), BorderLayout.NORTH);
        frame.add(Constants.myPanelImg);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

    }

    public static void main(String[] args) {
        App app = new App();

        app.configurarFrame();
    }
}
