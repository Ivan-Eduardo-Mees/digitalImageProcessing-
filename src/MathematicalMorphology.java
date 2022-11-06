import com.sun.tools.jconsole.JConsoleContext;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.*;

public class MathematicalMorphology extends Menu{


    ActionListener dilation = e -> {
        Map<Integer, Integer> tradeXY = new TreeMap<>();
        madeDilationErosion(true);
    };

    ActionListener erosion = e -> {
        madeDilationErosion(false);
    };

    ActionListener opening = e -> {
        madeDilationErosion(false);
        madeDilationErosion(true);
    };

    ActionListener closure = e -> {
        madeDilationErosion(true);
        madeDilationErosion(false);
    };

    private void madeDilationErosion(boolean xORy){
        BufferedImage erosionDilationApply = prepareImage();

        int[][] Kernel = {{0,10,0},
                          {10,10,10},
                          {0,10,0}
                         };

        ArrayList<Integer> val;

        Integer graySclale = 0;

        for(int y = 1; y < erosionDilationApply.getHeight()-1; y++){
            for(int x = 1; x < erosionDilationApply.getWidth()-1; x++){

                val = new ArrayList<>();

                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        int[] getRGB = captureRGB(x+(i-1),y+(j-1));
                        graySclale = (getRGB[0] + getRGB[1] + getRGB [2])/3;

                        if(xORy)
                            val.add(graySclale + Kernel[i][j]);
                        else
                            val.add(graySclale - Kernel[i][j]);
                    }
                }

                Collections.sort(val);
                Integer smallerBigValues =0;
                if(xORy){
                    smallerBigValues = val.get(8);
                    if(smallerBigValues > 255){
                        smallerBigValues = 255;
                    }
                }
                else{
                    smallerBigValues = val.get(0);
                    if(smallerBigValues < 0){
                        smallerBigValues = 0;
                    }
                }


                erosionDilationApply.setRGB(x, y-1, joinRGB(new int[]{smallerBigValues,smallerBigValues,smallerBigValues}));
                erosionDilationApply.setRGB(x-1, y, joinRGB(new int[]{smallerBigValues,smallerBigValues,smallerBigValues}));
                erosionDilationApply.setRGB(x, y, joinRGB(new int[]  {smallerBigValues,smallerBigValues,smallerBigValues}));
                erosionDilationApply.setRGB(x+1, y, joinRGB(new int[]{smallerBigValues,smallerBigValues,smallerBigValues}));
                erosionDilationApply.setRGB(x, y+1, joinRGB(new int[]{smallerBigValues,smallerBigValues,smallerBigValues}));
            }
        }

        saveANDraw(erosionDilationApply);
    }

    //made hilditch
    ActionListener hilditch = e -> {
        BufferedImage hilditchApply = prepareImage();

        for(int y = 1; y < hilditchApply.getHeight()-1; y++){
            for(int x = 1; x < hilditchApply.getWidth()-1; x++){
                hilditchApply.setRGB(x,y,Constants.alteredImage.getRGB(x,y));
            }
        }

        int[][] image = copyImageToBinary(hilditchApply);

        boolean again = true;
        int count = 0;
        ArrayList<Integer> trocarX = new ArrayList();;
        ArrayList<Integer> trocarY =  new ArrayList();;
        while (again){

            again = false;
            count=0;
            trocarX = new ArrayList();
            trocarY = new ArrayList();
            for(int y =0; y < image.length; y++){
                for(int x =0; x < image[y].length; x++){

                    int p2 = 0;
                    int p4 = 0;
                    int p6 = 0;
                    int p8 = 0;



                    p2 = getBinary(y-1,x,image);
                    p4 = getBinary(y,x+1,image);
                    p6 = getBinary(y+1,x,image);
                    p8  = getBinary(y,x-1,image);



                    if(getBinary(y,x,image) == 1) {//cond1
                        if (2 <= b1(y,x, image) && b1(y, x, image) <= 6) {//cond2
                            if (p1( y,x, image) == 1) {//cond3
                                if ((p2 * p4 * p8 == 0)|| p1(y - 1,x, image) != 1) {//cond4
                                    if ((p2 * p4 * p6 == 0) || p1(y,x + 1, image) != 1) {//cond5
                                        count++;
                                        again = true;
                                        trocarX.add(x);
                                        trocarY.add(y);
                                    }
                                }
                            }
                        }
                    }

                }
            }

            for(int k =0; k < trocarX.size(); k++){
                image[trocarY.get(k)][trocarX.get(k)] = 0;
            }

        }
        saveANDraw(copyBinaryToImage(hilditchApply,image));
    };

    //I see the black neighbors and return the amount
    public int b1(int y, int x, int[][] image){
        Integer count = 0;

        int p2 = getBinary(y-1,x,image);
        int p3 = getBinary(y-1,x+1,image);
        int p4 = getBinary(y,x+1,image);
        int p5 = getBinary(y+1,x+1,image);
        int p6 = getBinary(y+1,x,image);
        int p7 = getBinary(y+1,x-1,image);
        int p8 = getBinary(y,x-1,image);
        int p9 = getBinary(y-1,x-1,image);

        if(p2 == 0){
            count++;
        }
        if(p3 == 0){
            count++;
        }
        if(p4 == 0){
            count++;
        }
        if(p5 == 0){
            count++;
        }
        if(p6 == 0){
            count++;
        }
        if(p7 == 0){
            count++;
        }
        if(p8 == 0){
            count++;
        }
        if(p9 == 0){
            count++;
        }

        return count;
    }


//    I see the difference from black to white in the kernel and return the amount
    public int p1(int y, int x,int[][] image){
        Integer count = 0;

            int p2 = getBinary(y-1,x,image);
            int p3 = getBinary(y-1,x+1,image);
            int p4 = getBinary(y,x+1,image);
            int p5 = getBinary(y+1,x+1,image);
            int p6 = getBinary(y+1,x,image);
            int p7 = getBinary(y+1,x-1,image);
            int p8 = getBinary(y,x-1,image);
            int p9 = getBinary(y-1,x-1,image);

            if(p2==0 && p3==1)
                count++;
            if(p3==0 && p4==1)
                count++;
            if(p4==0 && p5==1)
                count++;
            if(p5==0 && p6==1)
                count++;
            if(p6==0 && p7==1)
                count++;
            if(p7==0 && p8==1)
                count++;
            if(p8==0 && p9==1)
                count++;
            if(p9==0 && p2==1)
                count++;



        return count;
    }


//    converts the RGB image to binary
    public static int[][] copyImageToBinary(BufferedImage image) {
        int[][] imageData = new int[image.getHeight()][image.getWidth()];
        for (int y = 0; y < imageData.length; y++) {
            for (int x = 0; x < imageData[y].length; x++) {

                if (image.getRGB(x, y) == Color.BLACK.getRGB()) {
                    imageData[y][x] = 0;
                } else {
                    imageData[y][x] = 1;

                }
            }
        }
        return imageData;
    }


//    convert the binary image to rgb
    public BufferedImage copyBinaryToImage(BufferedImage image, int[][] imageData) {
        for (int y = 0; y < imageData.length; y++) {
            for (int x = 0; x < imageData[y].length; x++) {

                if (imageData[y][x] == 0) {
                    image.setRGB(x, y, Color.BLACK.getRGB());

                } else {
                    image.setRGB(x, y, Color.WHITE.getRGB());
                }

            }
        }
        return image;
    }

    //returns the pixel value if it does not exist (edge) returns 1
    public int getBinary(int y, int x,int[][] binary) {
        try {
            return binary[y][x];
        } catch (IndexOutOfBoundsException e) {
            return 1;
        }
    }
}
