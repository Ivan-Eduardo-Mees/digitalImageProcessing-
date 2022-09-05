import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class PreProcessing extends Menu{

    ActionListener grayScale = e -> {
        BufferedImage image = prepareImage();

        for (int y = 0; y < Constants.alteredImage.getHeight(); y++) {
            for (int x = 0; x < Constants.alteredImage.getWidth(); x++) {

                int[] rgb = captureRGB(x,y);

                int media = (rgb[0] + rgb[1] + rgb[2])/3;

                rgb[0] = media;
                rgb[1] = media;
                rgb[2] = media;

                image.setRGB(x,y,joinRGB(rgb));
            }
        }

        saveANDraw(image);
    };

    ActionListener glare = e -> {
        aplyGlareContrast(1,Double.parseDouble(JOptionPane.showInputDialog(null,"glare")));
    };

    ActionListener contrast = e -> {
        aplyGlareContrast(Double.parseDouble(JOptionPane.showInputDialog(null,"glare")),0);
    };

    private void aplyGlareContrast(double contrast, double glare){
        BufferedImage image = prepareImage();

        for (int y = 0; y < Constants.alteredImage.getHeight(); y++) {
            for (int x = 0; x < Constants.alteredImage.getWidth(); x++) {

                int[] rgb = captureRGB(x,y);

                for(int i = 0; i < rgb.length; i++){
                    rgb[i] = (int) (contrast * rgb[i] + glare);

                    if(rgb[i] > 250){
                        rgb[i] = 250;
                    }
                    if(rgb[i] < 0){
                        rgb[i] = 0;
                    }
                }
                image.setRGB(x,y, joinRGB(rgb));
            }
        }

        saveANDraw(image);
    }

    private int[] captureRGB(int x, int y){
        int r = (Constants.alteredImage.getRGB(x,y) >> 16) & 0xFF;
        int g = (Constants.alteredImage.getRGB(x,y) >>  8) & 0xFF;
        int b = (Constants.alteredImage.getRGB(x,y) >>  0) & 0xFF;

        return new int[] {r,g,b};
    }

    private int joinRGB(int[] rgb){
        int red   = (rgb[0] & 0xFF) << 16;
        int green = (rgb[1] & 0xFF) <<  8;
        int blue  = (rgb[2] & 0xFF) <<  0;

        return red | green | blue;
    }
}
