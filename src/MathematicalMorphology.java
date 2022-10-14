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

        for(int y = 1; y < hilditchApply.getHeight()-1; y++){
            for(int x = 1; x < hilditchApply.getWidth()-1; x++){
                hilditchApply.setRGB(x,y,Constants.alteredImage.getRGB(x,y));
            }
        }
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

        while (again){

            again = false;
            int count = 0;
            for(int y = 1; y < hilditchApply.getHeight()-1; y++){
                for(int x = 1; x < hilditchApply.getWidth()-1; x++){
                    int[] getRGBP = captureRGBhilditch(x,y, hilditchApply);
                    int[] getRGBP2 = captureRGBhilditch(x,y-1,hilditchApply);
                    int[] getRGBP4 = captureRGBhilditch(x+1,y,hilditchApply);
                    int[] getRGBP6 = captureRGBhilditch(x,y+1,hilditchApply);
                    int[] getRGBP8 = captureRGBhilditch(x-1,y,hilditchApply);

                    Integer p1 = (getRGBP[0] + getRGBP[1] + getRGBP [2])/3;
                    Integer p2 = (getRGBP2[0] + getRGBP2[1] + getRGBP2 [2])/3;
                    Integer p4 = (getRGBP4[0] + getRGBP4[1] + getRGBP4 [2])/3;
                    Integer p6 = (getRGBP6[0] + getRGBP6[1] + getRGBP6 [2])/3;
                    Integer p8 = (getRGBP8[0] + getRGBP8[1] + getRGBP8 [2])/3;

                    if(p1 == 0){
                        if(2 <= b1(x,y,hilditchApply) && b1(x,y,hilditchApply) <= 6){
                            if(p1(x,y,hilditchApply) == 1){
                                if((p2 * p4 * p8 == 0) || p1(x,y-1,hilditchApply) != 1){
                                    if((p2 * p4 * p6 == 0) || p1(x+1,y,hilditchApply) != 1){
                                        count++;
                                        again = true;
                                        hilditchApply.setRGB(x,y, joinRGB(new int[]{255,255,255}));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            System.out.println(count);

        }

//        BufferedImage img =  new BufferedImage(Constants.alteredImage.getWidth(), Constants.alteredImage.getHeight(), BufferedImage.TYPE_INT_RGB);
//
//        img.getGraphics().setColor(Constants.myPanelImg.getBackground());
//        img.getGraphics().fillRect(0,0,img.getWidth(),img.getHeight());
//
//        Graphics2D g = (Graphics2D) Constants.myPanelImg.getGraphics();
//        g.drawImage(img, 580, 175, 500, 360, null);
//        g.drawImage(hilditchApply, 580, 175, 500, 360, null);
        saveANDraw(hilditchApply);
//




    };

    public int b1(int x, int y, BufferedImage image){
        Integer count = 0;
        Integer graySclale = 0;

        int[] getRGBP2 = captureRGBhilditch(x,y-1,image);
        int[] getRGBP3 = captureRGBhilditch(x+1,y-1,image);
        int[] getRGBP4 = captureRGBhilditch(x+1,y,image);
        int[] getRGBP5 = captureRGBhilditch(x+1,y+1,image);
        int[] getRGBP6 = captureRGBhilditch(x,y+1,image);
        int[] getRGBP7 = captureRGBhilditch(x-1,y+1,image);
        int[] getRGBP8 = captureRGBhilditch(x-1,y,image);
        int[] getRGBP9 = captureRGBhilditch(x-1,y-1,image);

        Integer p2 = (getRGBP2[0] + getRGBP2[1] + getRGBP2 [2])/3;
        Integer p3 = (getRGBP3[0] + getRGBP3[1] + getRGBP3 [2])/3;
        Integer p4 = (getRGBP4[0] + getRGBP4[1] + getRGBP4 [2])/3;
        Integer p5 = (getRGBP5[0] + getRGBP5[1] + getRGBP5 [2])/3;
        Integer p6 = (getRGBP6[0] + getRGBP6[1] + getRGBP6 [2])/3;
        Integer p7 = (getRGBP7[0] + getRGBP7[1] + getRGBP7 [2])/3;
        Integer p8 = (getRGBP8[0] + getRGBP8[1] + getRGBP8 [2])/3;
        Integer p9 = (getRGBP9[0] + getRGBP9[1] + getRGBP9 [2])/3;

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

    public int p1(int x, int y,BufferedImage image){

        Integer count = 0;



        int[] getRGBP2 = captureRGBhilditch(x,y-1,image);
        int[] getRGBP3 = captureRGBhilditch(x+1,y-1,image);
        int[] getRGBP4 = captureRGBhilditch(x+1,y,image);
        int[] getRGBP5 = captureRGBhilditch(x+1,y+1,image);
        int[] getRGBP6 = captureRGBhilditch(x,y+1,image);
        int[] getRGBP7 = captureRGBhilditch(x-1,y+1,image);
        int[] getRGBP8 = captureRGBhilditch(x-1,y,image);
        int[] getRGBP9 = captureRGBhilditch(x-1,y-1,image);

        Integer p2 = (getRGBP2[0] + getRGBP2[1] + getRGBP2 [2])/3;
        Integer p3 = (getRGBP3[0] + getRGBP3[1] + getRGBP3 [2])/3;
        Integer p4 = (getRGBP4[0] + getRGBP4[1] + getRGBP4 [2])/3;
        Integer p5 = (getRGBP5[0] + getRGBP5[1] + getRGBP5 [2])/3;
        Integer p6 = (getRGBP6[0] + getRGBP6[1] + getRGBP6 [2])/3;
        Integer p7 = (getRGBP7[0] + getRGBP7[1] + getRGBP7 [2])/3;
        Integer p8 = (getRGBP8[0] + getRGBP8[1] + getRGBP8 [2])/3;
        Integer p9 = (getRGBP9[0] + getRGBP9[1] + getRGBP9 [2])/3;

        if(p2==0 && p3>0)
            count++;
        if(p3==0 && p4>0)
            count++;
        if(p4==0 && p5>0)
            count++;
        if(p5==0 && p6>0)
            count++;
        if(p6==0 && p7>0)
            count++;
        if(p7==0 && p8>0)
            count++;
        if(p8==0 && p9>0)
            count++;
        if(p9==0 && p2>0)
            count++;

        return count;
    }



    public int[] captureRGBhilditch(int x, int y, BufferedImage image){
        int r = (image.getRGB(x,y) >> 16) & 0xFF;
        int g = (image.getRGB(x,y) >>  8) & 0xFF;
        int b = (image.getRGB(x,y) >>  0) & 0xFF;

        return new int[] {r,g,b};
    }
}
