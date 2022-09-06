import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class PreProcessing extends Menu{
    //apply grayScale
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
    //call applyGlareContrast, passing 1 fixed in contrast
    ActionListener glare = e -> {
        applyGlareContrast(1,Double.parseDouble(JOptionPane.showInputDialog(null,"glare")));
    };
    //call applyGlareContrast, passing 0 fixed in glare
    ActionListener contrast = e -> {
        applyGlareContrast(Double.parseDouble(JOptionPane.showInputDialog(null,"contrast")),0);
    };

    //metodo que faz o aplique de contraste e glare
    private void applyGlareContrast(double contrast, double glare){
        BufferedImage image = prepareImage();

        for (int y = 0; y < Constants.alteredImage.getHeight(); y++) {
            for (int x = 0; x < Constants.alteredImage.getWidth(); x++) {

                int[] rgb = captureRGB(x,y);

                for(int i = 0; i < rgb.length; i++){
                    rgb[i] = (int) (contrast * rgb[i] + glare);

                    if(rgb[i] > 255){
                        rgb[i] = 255;
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

    //capture the red green and blue of the rgb and return an array of integers
    private int[] captureRGB(int x, int y){
        int r = (Constants.alteredImage.getRGB(x,y) >> 16) & 0xFF;
        int g = (Constants.alteredImage.getRGB(x,y) >>  8) & 0xFF;
        int b = (Constants.alteredImage.getRGB(x,y) >>  0) & 0xFF;

        return new int[] {r,g,b};
    }

    //join the red green and blue in rgb and return an integer
    private int joinRGB(int[] rgb){
        int red   = (rgb[0] & 0xFF) << 16;
        int green = (rgb[1] & 0xFF) <<  8;
        int blue  = (rgb[2] & 0xFF) <<  0;

        return red | green | blue;
    }
}
