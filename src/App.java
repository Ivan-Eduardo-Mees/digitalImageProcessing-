import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class App extends JFrame{

    //configure canvas from JFrame class
    public void configureFrame() {

        Container container = getContentPane();

        JButton btnBackImage = new JButton("BackImage");
        btnBackImage.addActionListener(backImage);
        container.add(btnBackImage, BorderLayout.SOUTH);
        container.add(Constants.myPanelImg, BorderLayout.CENTER);

        setJMenuBar(new Menu().buildMenu());

        setSize(1080 , 720);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

//    function that replaces the image with the penultimate modified image
    ActionListener backImage = e -> {
            int sizeOfArray = Constants.modifyImages.size();
            if(sizeOfArray != 1) {
                Graphics2D g = (Graphics2D) Constants.myPanelImg.getGraphics();

                BufferedImage cleanImage = new BufferedImage(Constants.alteredImage.getWidth(), Constants.alteredImage.getHeight(), BufferedImage.TYPE_INT_RGB);
                cleanImage.getGraphics().setColor(Constants.myPanelImg.getBackground());
                cleanImage.getGraphics().fillRect(0, 0, cleanImage.getWidth(), cleanImage.getHeight());
                g.drawImage(cleanImage, 580, 175, 500, 360, null);


                g.drawImage(Constants.modifyImages.get(sizeOfArray - 2), 580, 175, 500, 360, null);
                Constants.alteredImage = Constants.modifyImages.get(sizeOfArray - 2);

                Constants.modifyImages.remove(sizeOfArray - 1);
            }

    };

    public static void main(String[] args) {
        App app = new App();
        app.configureFrame();
    }
}
