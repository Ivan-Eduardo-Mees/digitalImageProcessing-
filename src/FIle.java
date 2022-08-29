import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class FIle {


    ActionListener listenerOpenImage = e -> {
        try {
            JFileChooser FILE_CHOOSER = new JFileChooser();
            int returnValue = FILE_CHOOSER.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
//                Constants.defaultImg = ImageIO.read(FILE_CHOOSER.getSelectedFile());
                Constants.defaultImg = ImageIO.read(new File("/home/ivanmees/IdeaProjects/digitalImageProcessing/phoca_thumb_l_image03_grd.png"));
                Constants.alteredImage = Constants.defaultImg;



                Graphics2D g = (Graphics2D) Constants.myPanelImg.getGraphics();

                g.drawImage(Constants.defaultImg, 0, 150, 250, 150, null);
                g.drawImage(Constants.alteredImage, 280, 150, 250, 150, null);


            }
        }catch (Exception exception) {
            System.out.println("Error Import Image" + exception);
        }


    };


    ActionListener saveAlteredImage = e -> {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.removeChoosableFileFilter(fileChooser.getFileFilter());
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JPG File", "jpg"));

            int returnVal = fileChooser.showDialog(Constants.myPanelImg, "Save");

            if (returnVal == JFileChooser.APPROVE_OPTION) {

                String file = fileChooser.getSelectedFile().toString();

                if(file.split("\\.").length != 2) {
                    file += ".jpg";
                }

                ImageIO.write(Constants.alteredImage, "PNG", new File(file));
            }

        }catch (Exception exception) {
            System.out.println("Error Saving Image" + exception);
        }
    };

    ActionListener about = e -> {
        JOptionPane.showMessageDialog(null,"Made by IvanMees");
    };


}
