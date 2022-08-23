import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Transformation {

    ActionListener translation = e ->{
        int width  = Constants.alteredImage.getWidth();
        int height = Constants.alteredImage.getHeight();
        int x = 150;
        int y = 30;


        BufferedImage translate = new BufferedImage(250, 150, BufferedImage.TYPE_INT_RGB);

        double[][] matrizTranslate = { { 1, 0, x },
                                       { 0, 1, y },
                                       { 0, 0, 1  } };

        Graphics2D g = (Graphics2D) Constants.myPanelImg.getGraphics();
        for (int j = 0; j < x; j++) {
            for (int i = 0; i < y; i++) {
                translate = applyProcess(Constants.alteredImage, matrizTranslate, x,y);
            }
        }


        System.out.println(translate.getWidth() + "---" + translate.getHeight());
        g.drawImage(translate, x, y, translate.getWidth(), translate.getHeight(), null);

    };

    public BufferedImage applyProcess(BufferedImage img, double[][] matrizTranslate, int x, int y) {


                double out_x = Math.round(x * matrizTranslate[0][0] + x * matrizTranslate[0][1]);
                double out_y = Math.round(y * matrizTranslate[1][0] + y * matrizTranslate[1][1]);

                img.setRGB((int) out_x, (int) out_y, Constants.alteredImage.getRGB( x, y));

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
