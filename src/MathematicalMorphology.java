import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
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
}
