import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
public class Transformation extends Menu{


    private int contMirroring = 0;

    //I request x y to the user, make my matrix and call the processImage method
    ActionListener translation = e ->{

        int x = Integer.parseInt(JOptionPane.showInputDialog(null,"inform o valor x"));
        int y = Integer.parseInt(JOptionPane.showInputDialog(null,"inform o valor y"));

        double[][] matrizTranslate = {  { 1, 0, x },
                                        { 0, 1, y },
                                        { 0, 0, 1 } };

        processImage(matrizTranslate);
    };


    //I request enlargement-reduction to the user, make my matrix and call the processImage method
    ActionListener enlargementReduction = e ->{

        double enlargementReduction = Double.parseDouble(JOptionPane.showInputDialog(null, "Enlargement"));

        double[][] matrixEnlargement = {{ enlargementReduction, 0, 0 },
                                        { 0, enlargementReduction, 0 },
                                        { 0, 0, 1 } };

        processImage(matrixEnlargement);
    };


    //I'm mirroring my image in several directions, always making the matrix and sending it to the processImage method
    ActionListener mirroring = e ->{
        double[][] matrixMirroring = new double[][]{};

        switch (contMirroring) {
            case 0 -> {
                matrixMirroring = new double[][]{{-1, 0, Constants.alteredImage.getWidth()},
                                                 {0, 1, 0},
                                                 {0, 0, 1}};
                System.out.println("0");
                contMirroring++;
            }
            case 1 -> {
                matrixMirroring = new double[][]{{1, 0, 0},
                        {0, -1, Constants.alteredImage.getHeight()},
                        {0, 0, 1}};

                contMirroring++;
                System.out.println("1");
            }
            case 2 -> {

                matrixMirroring = new double[][]{{-1, 0, Constants.alteredImage.getWidth()},
                                                    {0, 1, 0},
                                                    {0, 0, 1}};
                contMirroring++;
                System.out.println("2");
            }
            case 3 -> {
                matrixMirroring = new double[][]{{-1, 0, Constants.alteredImage.getWidth()},
                                                 {0, -1, Constants.alteredImage.getHeight()},
                                                 {0, 0, 1}};
                contMirroring = 0;
                System.out.println("3");
            }
        }

        processImage(matrixMirroring);
    };

    //request how much to rotate to my user, make my matrix and go through the entire image
    //take half of my x and y I make my x minus half and apply the calculation on the matrix after that I add the x and y again, thus compensating for the image displacement
    //after that I draw
    ActionListener rotation = e ->{
        double rotation = Double.parseDouble(JOptionPane.showInputDialog(null,"rotation"));

        double[][] matrixRotation = {   { Math.cos(Math.toRadians(rotation)), -Math.sin(Math.toRadians(rotation)), 0 },
                                        { Math.sin(Math.toRadians(rotation)), Math.cos(Math.toRadians(rotation)), 0},
                                        { 0, 0, 1 } };

        BufferedImage img = prepareImage();

        for (int y = 0; y < Constants.alteredImage.getHeight(); y++) {
            for (int x = 0; x < Constants.alteredImage.getWidth(); x++) {
                double halfX = Constants.alteredImage.getWidth() / 2.0;
                double halfY = Constants.alteredImage.getHeight() / 2.0;
                double tmpX = x - halfX;
                double tmpY = y - halfY;
                double newX = Math.round(tmpX * matrixRotation[0][0] + tmpY * matrixRotation[0][1] + matrixRotation[0][2]);
                double newY = Math.round(tmpX * matrixRotation[1][0] + tmpY * matrixRotation[1][1] + matrixRotation[1][2]);
                newX += halfX;
                newY += halfY;

                if (newX < Constants.alteredImage.getWidth() && newY < Constants.alteredImage.getHeight() && newX >= 0 && newY >= 0) {
                    img.setRGB((int) newX,(int) newY, Constants.alteredImage.getRGB(x, y));
                }
            }
        }

        saveANDraw(img);

    };
    //run through my entire image applying the calculations on top of the matrix thus discovering the new y and x after that I draw the image
    private void processImage(double[][] matrix){

        BufferedImage img = prepareImage();

        for (int y = 0; y < Constants.alteredImage.getHeight(); y++) {
            for (int x = 0; x < Constants.alteredImage.getWidth(); x++) {
                double newX = Math.round(x * matrix[0][0] + x * matrix[0][1] + matrix[0][2]);
                double newY = Math.round(y * matrix[1][0] + y * matrix[1][1] + matrix[1][2]);

                if (newX < Constants.alteredImage.getWidth() && newY < Constants.alteredImage.getHeight() && newX >= 0 && newY >= 0) {
                    img.setRGB((int) newX, (int) newY, Constants.alteredImage.getRGB(x, y));
                }
            }
        }

        saveANDraw(img);
    }

}
