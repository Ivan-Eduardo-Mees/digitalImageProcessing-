import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Transformation {

    int x =0;
    int y =0;
    ActionListener translation = e ->{

        x += Integer.parseInt(JOptionPane.showInputDialog(null,"inform o valor x"));
        y += Integer.parseInt(JOptionPane.showInputDialog(null,"inform o valor y"));

        BufferedImage translate = new BufferedImage(Constants.alteredImage.getWidth(), Constants.alteredImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        BufferedImage img =  new BufferedImage(Constants.alteredImage.getWidth(), Constants.alteredImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        double[][] matrizTranslate = {  { 1, 0, x },
                                        { 0, 1, y },
                                        { 0, 0, 1 } };

        for (int j = 0; j < Constants.alteredImage.getHeight(); j++) {
            for (int i = 0; i < Constants.alteredImage.getWidth(); i++) {
                translate = applyProcess(img, matrizTranslate, i, j);
            }
        }

        Graphics2D g = (Graphics2D) Constants.myPanelImg.getGraphics();

        g.drawImage(translate, 280, 150, 250, 150, null);
    };

    public BufferedImage applyProcess(BufferedImage img, double[][] mat, int x, int y) {

        double newX = Math.round(x * mat[0][0] + y * mat[0][1] + mat[0][2]);
        double newY = Math.round(x * mat[1][0] + y * mat[1][1] + mat[1][2]);

        if (newX < Constants.alteredImage.getWidth() && newY < Constants.alteredImage.getHeight() && newX >= 0 && newY >= 0) {
          img.setRGB((int) newX, (int) newY, Constants.alteredImage.getRGB(x, y));
        }

        return img;
    }

    ActionListener enlargement = e ->{

    };

    ActionListener reduction = e ->{

    };

    ActionListener mirroring = e ->{

    };

    ActionListener rotation = e ->{

    };


}
