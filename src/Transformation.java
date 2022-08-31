import javax.net.ssl.CertPathTrustManagerParameters;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.security.cert.CertPath;

public class Transformation {


    int contMirroring = 0;

    ActionListener translation = e ->{

        int x = Integer.parseInt(JOptionPane.showInputDialog(null,"inform o valor x"));
        int y = Integer.parseInt(JOptionPane.showInputDialog(null,"inform o valor y"));

        double[][] matrizTranslate = {  { 1, 0, x },
                                        { 0, 1, y },
                                        { 0, 0, 1 } };

        processImage(matrizTranslate);
    };



    ActionListener enlargementReduction = e ->{

        double enlargement = Double.parseDouble(JOptionPane.showInputDialog(null, "Enlargement"));


        double[][] matrixEnlargement = {{ enlargement, 0, 0 },
                                        { 0, enlargement, 0 },
                                        { 0, 0, 1 } };

        processImage(matrixEnlargement);
    };


    ActionListener mirroring = e ->{
        double[][] matrixMirroring = new double[][]{};

        switch (contMirroring) {
            case 0 -> {
                matrixMirroring = new double[][]{{-1, 0, Constants.alteredImage.getWidth()},
                                                 {0, 1, 0},
                                                 {0, 0, 1}};
                contMirroring++;
            }
            case 1 -> {
                matrixMirroring = new double[][]{{-1, 0, Constants.alteredImage.getWidth()},
                                                 {0, -1, Constants.alteredImage.getHeight()},
                                                 {0, 0, 1}};
                contMirroring++;
            }
            case 2 -> {
                matrixMirroring = new double[][]{{1, 0, 0},
                                                 {0, -1, Constants.alteredImage.getHeight()},
                                                 {0, 0, 1}};
                contMirroring++;
            }
            case 3 -> {
                matrixMirroring = new double[][]{{1, 0, 0},
                                                 {0, 1, 0},
                                                 {0, 0, 1}};
                contMirroring = 0;
            }
        }

        processImage(matrixMirroring);
    };

    ActionListener rotation = e ->{
        double rotation = 45;


        double[][] matrixRotation = {{ Math.cos(rotation), -Math.sin(rotation), 250 },
                                        { Math.sin(rotation), Math.cos(rotation), 0 },
                                        { 0, 0, 1 } };

        processImage(matrixRotation);

    };

    private void processImage(double[][] matrix){
        BufferedImage img =  new BufferedImage(Constants.alteredImage.getWidth(), Constants.alteredImage.getHeight(), BufferedImage.TYPE_INT_RGB);



        for (int y = 0; y < Constants.alteredImage.getHeight(); y++) {
            for (int x = 0; x < Constants.alteredImage.getWidth(); x++) {
                double newX = Math.round(x * matrix[0][0] + x * matrix[0][1] + matrix[0][2]);
                double newY = Math.round(y * matrix[1][0] + y * matrix[1][1] + matrix[1][2]);

                if (newX < Constants.alteredImage.getWidth() && newY < Constants.alteredImage.getHeight() && newX >= 0 && newY >= 0) {
                    img.setRGB((int) newX, (int) newY, Constants.alteredImage.getRGB(x, y));
                }
            }
        }

        Constants.alteredImage = img;
        Graphics2D g = (Graphics2D) Constants.myPanelImg.getGraphics();
        g.drawImage(img, 0, 0, Constants.alteredImage.getWidth(), Constants.alteredImage.getHeight(), null);
    }


}
