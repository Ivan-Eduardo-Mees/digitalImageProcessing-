import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;

import java.io.File;


public class FIle extends Menu{

    //I open my image, put it in my image control variables and draw on canvas
    ActionListener listenerOpenImage = e -> {
        try {
            JFileChooser FILE_CHOOSER = new JFileChooser();
            int returnValue = FILE_CHOOSER.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                Constants.originalImage = ImageIO.read(FILE_CHOOSER.getSelectedFile());
                Constants.alteredImage = Constants.originalImage;
                Graphics g = Constants.myPanelImg.getGraphics();

                g.drawImage(Constants.originalImage, 0, 175, 500,360, null);
                g.drawImage(Constants.alteredImage, 580, 175, 500, 360, null);

            }
        }catch (Exception exception) {
            System.out.println("Error Import Image" + exception);
        }
    };


    //saved my changed image. If you don't put any . informing the extension of the image I put .jpg
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
