import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Threshold extends Menu{

    //I request the threshold I scroll through my image and apply the grayscale and do the crop
    ActionListener threshold = e -> {
        BufferedImage thresholdApply = prepareImage();
        int cut = Integer.parseInt(JOptionPane.showInputDialog(null, "threshold cut"));

        for(int y = 0; y < Constants.alteredImage.getHeight(); y++){
            for(int x = 0; x < Constants.alteredImage.getWidth(); x++){

                int[] rgb = captureRGB(x,y);

                if((rgb[0] + rgb[1] + rgb[2])/3 >= cut){
                    rgb[0] = 255;
                    rgb[1] = 255;
                    rgb[2] = 255;
                }else{
                    rgb[0] = 0;
                    rgb[1] = 0;
                    rgb[2] = 0;
                }

                thresholdApply.setRGB(x,y,joinRGB(rgb));
            }
        }

        saveANDraw(thresholdApply);

    };

}
