import javax.swing.*;
import java.awt.*;

public class App extends JFrame{

    public void configurarFrame() {

        Container container = getContentPane();
        container.add(Constants.myPanelImg, BorderLayout.CENTER);

        setJMenuBar(new Menu().buildMenu());

        setSize(1080 , 720);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    public static void main(String[] args) {
        App app = new App();

        app.configurarFrame();
    }
}
