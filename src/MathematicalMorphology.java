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

    ActionListener hilditch = e -> {
        BufferedImage hilditchApply =prepareImage();
//        BufferedImage hilditchApply =  new BufferedImage(Constants.alteredImage.getWidth(), Constants.alteredImage.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

//        Graphics biG = hilditchApply.getGraphics();

//        biG.drawImage(Constants.alteredImage,0,0,null);





        for(int y = 1; y < hilditchApply.getHeight()-1; y++){
            for(int x = 1; x < hilditchApply.getWidth()-1; x++){
                hilditchApply.setRGB(x,y,Constants.alteredImage.getRGB(x,y));
            }
        }


        int[][] image = copyImageToBinary(hilditchApply);
//


//        for(int y=0; y< image.length; y++){
//            for(int x=0; x< image[y].length; x++){
//                System.out.print(image[y][x]);
//            }
//            System.out.println();
//        }
//
//

//        int[] a = new int[2];
//        int[] g = hilditchApply.getRaster().getPixel(1,1,captureRGBhilditch(1,1,hilditchApply));
//
//        hilditchApply.getClass().getp
//        System.out.println(g.length);
//        System.out.println(g[0]);
//        System.out.println(g[1]);
//        System.out.println(g[2]);
//
//        for(int y = 1; y < hilditchApply.getHeight()-1; y++){
//            for(int x = 1; x < hilditchApply.getWidth()-1; x++){
////                System.out.println(hilditchApply.);
//            }
//        }

//        hilditchApply = Constants.alteredImage;
//        BufferedImage hilditchApply =  new BufferedImage(3, 3, BufferedImage.TYPE_INT_RGB);
//        hilditchApply.setRGB(0,0, joinRGB(new int[]{255,255,255}));
//        hilditchApply.setRGB(1,0, joinRGB(new int[]{0,0,0}));
//        hilditchApply.setRGB(2,0, joinRGB(new int[]{0,0,0}));
//
//        hilditchApply.setRGB(0,1, joinRGB(new int[]{255,255,255}));
//        hilditchApply.setRGB(1,1, joinRGB(new int[]{255,255,255}));
//        hilditchApply.setRGB(2,1, joinRGB(new int[]{255,255,255}));
//
//        hilditchApply.setRGB(0,2, joinRGB(new int[]{255,255,255}));
//        hilditchApply.setRGB(1,2, joinRGB(new int[]{255,255,255}));
//        hilditchApply.setRGB(2,2, joinRGB(new int[]{255,255,255}));
//
//        Constants.alteredImage = hilditchApply;
//
//        System.out.println(p1(1,1));

//        Integer graySclale = 0;
//
//        Integer graySclaleP2 = 0;
//        Integer graySclaleP4 = 0;
//        Integer graySclaleP6 = 0;
//        Integer graySclaleP8 = 0;
//
        boolean again = true;
//        System.out.println(image.length);
//        System.out.println(image[image.length-1].length);
        int count = 0;
        while (again){

            again = false;
            count++;
            for(int y =0; y < image.length; y++){
                for(int x =0; x < image[y].length; x++){

//                    int[] p11 = new int[1];
//                    int[] p22 = new int[1];
//                    int[] p44 = new int[1];
//                    int[] p66 = new int[1];
//                    int[] p88 = new int[1];
//
//                    p11 = hilditchApply.getRaster().getPixel(x,y,p11);
//                    p22 = hilditchApply.getRaster().getPixel(x,y-1,p22);
//                    p44 = hilditchApply.getRaster().getPixel(x+1,y,p44);
//                    p66 = hilditchApply.getRaster().getPixel(x,y+1,p66);
//                    p88 = hilditchApply.getRaster().getPixel(x-1,y,p88);
//
//                    int p1 = p11[0];
//                    int p2 = p22[0];
//                    int p4 = p44[0];
//                    int p6 = p66[0];
//                    int p8 = p88[0];


//                    int[] getRGBP = captureRGBhilditch(x,y, hilditchApply);
//                    int[] getRGBP2 = captureRGBhilditch(x,y-1,hilditchApply);
//                    int[] getRGBP4 = captureRGBhilditch(x+1,y,hilditchApply);
//                    int[] getRGBP6 = captureRGBhilditch(x,y+1,hilditchApply);
//                    int[] getRGBP8 = captureRGBhilditch(x-1,y,hilditchApply);
//
//                    Integer p1 = (getRGBP[0] + getRGBP[1] + getRGBP [2])/3;
//                    Integer p2 = (getRGBP2[0] + getRGBP2[1] + getRGBP2 [2])/3;
//                    Integer p4 = (getRGBP4[0] + getRGBP4[1] + getRGBP4 [2])/3;
//                    Integer p6 = (getRGBP6[0] + getRGBP6[1] + getRGBP6 [2])/3;
//                    Integer p8 = (getRGBP8[0] + getRGBP8[1] + getRGBP8 [2])/3;

                    int p2 = 0;
                    int p4 = 0;
                    int p6 = 0;
                    int p8=0;



                    p2 = getBinary(y-1,x,image);
                    p4 = getBinary(y,x+1,image);
                    p6 = getBinary(y+1,x,image);
                    p8  = getBinary(y,x-1,image);


//                    if(getBinary(y,x,image) == 1) {
                        if (2 <= b1(y,x, image) && b1(y, x, image) <= 6) {
                            if (p1( y,x, image) == 1) {
                                if (p2 != 1 || p4 != 1 || p8 != 1 || p1(y - 1,x, image) != 1) {
                                    if ((p2 * p4 * p6 == 0) || p1(y,x + 1, image) != 1) {
//                                        count++;
                                        again = true;
                                        image[y][x] = 0;
//                                        System.out.println("y: "+y + "x: "+x);
                                    }
                                }
                            }
                        }
//                    }

                }
            }
            System.out.println(count);

//            System.out.println(count);

        }

//        for(int y=0; y< image.length; y++){
//            for(int x=0; x< image[y].length; x++){
//                System.out.print(image[y][x]);
//            }
//            System.out.println();
//        }


//        for(int y = 1; y < hilditchApply.getHeight()-1; y++){
//            for(int x = 1; x < hilditchApply.getWidth()-1; x++){
//                int[] getRGBP = captureRGB(x,y);
//                Integer p1 = (getRGBP[0] + getRGBP[1] + getRGBP [2])/3;
//                if(p1 ==1)
//                    hilditchApply.setRGB(x,y,joinRGB(new int[]{255,255,255}));
//            }
//        }
//        BufferedImage img =  new BufferedImage(Constants.alteredImage.getWidth(), Constants.alteredImage.getHeight(), BufferedImage.TYPE_INT_RGB);
//
//        img.getGraphics().setColor(Constants.myPanelImg.getBackground());
//        img.getGraphics().fillRect(0,0,img.getWidth(),img.getHeight());
//
//        Graphics2D g = (Graphics2D) Constants.myPanelImg.getGraphics();
//        g.drawImage(img, 580, 175, 500, 360, null);
//        g.drawImage(hilditchApply, 580, 175, 500, 360, null);
//        for(int y=0; y< image.length; y++){
//            for(int x=0; x< image[y].length; x++){
//                System.out.print(image[y][x]);
//            }
//            System.out.println();
//        }
//
        saveANDraw(copyBinaryToImage(hilditchApply,image));
//




    };

    public int b1(int y, int x, int[][] image){
        Integer count = 0;

//        int[] getRGBP2 = captureRGBhilditch(x,y-1,image);
//        int[] getRGBP3 = captureRGBhilditch(x+1,y-1,image);
//        int[] getRGBP4 = captureRGBhilditch(x+1,y,image);
//        int[] getRGBP5 = captureRGBhilditch(x+1,y+1,image);
//        int[] getRGBP6 = captureRGBhilditch(x,y+1,image);
//        int[] getRGBP7 = captureRGBhilditch(x-1,y+1,image);
//        int[] getRGBP8 = captureRGBhilditch(x-1,y,image);
//        int[] getRGBP9 = captureRGBhilditch(x-1,y-1,image);
//
//        Integer p2 = (getRGBP2[0] + getRGBP2[1] + getRGBP2 [2])/3;
//        Integer p3 = (getRGBP3[0] + getRGBP3[1] + getRGBP3 [2])/3;
//        Integer p4 = (getRGBP4[0] + getRGBP4[1] + getRGBP4 [2])/3;
//        Integer p5 = (getRGBP5[0] + getRGBP5[1] + getRGBP5 [2])/3;
//        Integer p6 = (getRGBP6[0] + getRGBP6[1] + getRGBP6 [2])/3;
//        Integer p7 = (getRGBP7[0] + getRGBP7[1] + getRGBP7 [2])/3;
//        Integer p8 = (getRGBP8[0] + getRGBP8[1] + getRGBP8 [2])/3;
//        Integer p9 = (getRGBP9[0] + getRGBP9[1] + getRGBP9 [2])/3;

//        int[] p22 = new int[1];
//        int[] p33 = new int[1];
//        int[] p44 = new int[1];
//        int[] p55 = new int[1];
//        int[] p66 = new int[1];
//        int[] p77 = new int[1];
//        int[] p88 = new int[1];
//        int[] p99 = new int[1];
//
//
//        p22 = image.getRaster().getPixel(x,y-1,p22);
//        p33 = image.getRaster().getPixel(x+1,y-1,p33);
//        p44 = image.getRaster().getPixel(x+1,y,p44);
//        p55 = image.getRaster().getPixel(x+1,y+1,p55);
//        p66 = image.getRaster().getPixel(x,y+1,p66);
//        p77 = image.getRaster().getPixel(x-1,y+1,p77);
//        p88 = image.getRaster().getPixel(x-1,y,p88);
//        p99 = image.getRaster().getPixel(x-1,y-1,p99);
//
//        int p2 = p22[0];
//        int p3 = p33[0];
//        int p4 = p44[0];
//        int p5 = p55[0];
//        int p6 = p66[0];
//        int p7 = p77[0];
//        int p8 = p88[0];
//        int p9 = p99[0];

        int p2 = getBinary(y-1,x,image);
        int p3 = getBinary(y-1,x+1,image);
        int p4 = getBinary(y,x+1,image);
        int p5 = getBinary(y+1,x+1,image);
        int p6 = getBinary(y+1,x,image);
        int p7 = getBinary(y+1,x-1,image);
        int p8 = getBinary(y,x-1,image);
        int p9 = getBinary(y-1,x-1,image);

//        count = p2+p3+p4+p5+p6+p7+p8+p9;
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

    public int p1(int y, int x,int[][] image){
        Integer count = 0;
        try{


//            int[] getRGBP2 = captureRGBhilditch(x,y-1,image);
//            int[] getRGBP3 = captureRGBhilditch(x+1,y-1,image);
//            int[] getRGBP4 = captureRGBhilditch(x+1,y,image);
//            int[] getRGBP5 = captureRGBhilditch(x+1,y+1,image);
//            int[] getRGBP6 = captureRGBhilditch(x,y+1,image);
//            int[] getRGBP7 = captureRGBhilditch(x-1,y+1,image);
//            int[] getRGBP8 = captureRGBhilditch(x-1,y,image);
//            int[] getRGBP9 = captureRGBhilditch(x-1,y-1,image);
//
//            Integer p2 = (getRGBP2[0] + getRGBP2[1] + getRGBP2 [2])/3;
//            Integer p3 = (getRGBP3[0] + getRGBP3[1] + getRGBP3 [2])/3;
//            Integer p4 = (getRGBP4[0] + getRGBP4[1] + getRGBP4 [2])/3;
//            Integer p5 = (getRGBP5[0] + getRGBP5[1] + getRGBP5 [2])/3;
//            Integer p6 = (getRGBP6[0] + getRGBP6[1] + getRGBP6 [2])/3;
//            Integer p7 = (getRGBP7[0] + getRGBP7[1] + getRGBP7 [2])/3;
//            Integer p8 = (getRGBP8[0] + getRGBP8[1] + getRGBP8 [2])/3;
//            Integer p9 = (getRGBP9[0] + getRGBP9[1] + getRGBP9 [2])/3;

//            int[] p22 = new int[1];
//            int[] p33 = new int[1];
//            int[] p44 = new int[1];
//            int[] p55 = new int[1];
//            int[] p66 = new int[1];
//            int[] p77 = new int[1];
//            int[] p88 = new int[1];
//            int[] p99 = new int[1];
//
//
//            p22 = image.getRaster().getPixel(x,y-1,p22);
//            p33 = image.getRaster().getPixel(x+1,y-1,p33);
//            p44 = image.getRaster().getPixel(x+1,y,p44);
//            p55 = image.getRaster().getPixel(x+1,y+1,p55);
//            p66 = image.getRaster().getPixel(x,y+1,p66);
//            p77 = image.getRaster().getPixel(x-1,y+1,p77);
//            p88 = image.getRaster().getPixel(x-1,y,p88);
//            p99 = image.getRaster().getPixel(x-1,y-1,p99);
//
//            int p2 = p22[0];
//            int p3 = p33[0];
//            int p4 = p44[0];
//            int p5 = p55[0];
//            int p6 = p66[0];
//            int p7 = p77[0];
//            int p8 = p88[0];
//            int p9 = p99[0];

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
        }catch (Exception e){
            System.out.println("entrou");
        }


        return count;
    }



    public int[] captureRGBhilditch(int x, int y, BufferedImage image){
        int r = (image.getRGB(x,y) >> 16) & 0xFF;
        int g = (image.getRGB(x,y) >>  8) & 0xFF;
        int b = (image.getRGB(x,y) >>  0) & 0xFF;

        return new int[] {r,g,b};
    }

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
    public int getBinary(int y, int x,int[][] binary) {
        try {
            return binary[y][x];
        } catch (IndexOutOfBoundsException e) {
            return 1;
        }
    }
}
