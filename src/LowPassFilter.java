import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.*;

public class LowPassFilter extends Menu{


//    loop through my image adding the rgb values ​​after that /9
    ActionListener Average = e -> {

        BufferedImage avarageAply = prepareImage();

        for (int y = 1; y < Constants.alteredImage.getHeight()-1; y++){
            for(int x = 1; x < Constants.alteredImage.getWidth()-1; x++){
                int r = 0;
                int g = 0;
                int b = 0;

                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        int[] getRGB = captureRGB(x + (i-1), y + (j-1));
                        r += getRGB[0];
                        g += getRGB[1];
                        b += getRGB[2];
                    }
                }

                r = r/9;
                g = g/9;
                b = b/9;

                int[] setRGB = {r,g,b};
                avarageAply.setRGB(x,y,joinRGB(setRGB));
            }
        }

        saveANDraw(avarageAply);
    };

//    I go through my image and get it by calling the getFashion method to get the value that occurs most in rgb
    ActionListener Fashion = e -> {
        ArrayList<Integer> r = new ArrayList<>();
        ArrayList<Integer> g = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();

        BufferedImage fashionApply = prepareImage();

        for (int y = 1; y < Constants.alteredImage.getHeight()-1; y++){
            for(int x = 1; x < Constants.alteredImage.getWidth()-1; x++){
                r.clear();
                g.clear();
                b.clear();

                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        int[] getRGB = captureRGB(x + (i-1), y + (j-1));
                        r.add(getRGB[0]);
                        g.add(getRGB[1]);
                        b.add(getRGB[2]);
                    }
                }
                int newR = getFashion(r);
                int newG = getFashion(g);
                int newB = getFashion(b);

                int[] setRGB = {newR,newG,newB};
                fashionApply.setRGB(x,y,joinRGB(setRGB));

            }
        }
        saveANDraw(fashionApply);

    };
//I go through the image putting the values ​​in the array after that I do the sort and then I get the middle one
    ActionListener median = e -> {

        ArrayList<Integer> r = new ArrayList<>();
        ArrayList<Integer> g = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();

        BufferedImage medianApply = prepareImage();

        for (int y = 1; y < Constants.alteredImage.getHeight()-1; y++){
            for(int x = 1; x < Constants.alteredImage.getWidth()-1; x++){
                r.clear();
                g.clear();
                b.clear();

                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        int[] getRGB = captureRGB(x + (i-1), y + (j-1));
                        r.add(getRGB[0]);
                        g.add(getRGB[1]);
                        b.add(getRGB[2]);
                    }
                }
                Collections.sort(r);
                Collections.sort(g);
                Collections.sort(b);
                int newR = r.get(5);
                int newG = g.get(5);
                int newB = b.get(5);

                int[] setRGB = {newR,newG,newB};
                medianApply.setRGB(x,y,joinRGB(setRGB));

            }
        }
        saveANDraw(medianApply);
    };
    //I scroll through the image applying the convolution of the gaus mask
    ActionListener Gaussian = e -> {

        BufferedImage gausApply = prepareImage();

        int[][] gausMasc =
                {{1, 2, 1},
                 {2, 4, 2},
                 {1, 2, 1}
                };


        int r,g,b;

        for (int y = 1; y < Constants.alteredImage.getHeight()-1; y++){
            for(int x = 1; x < Constants.alteredImage.getWidth()-1; x++){
                r = 0;
                g = 0;
                b = 0;
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        int[] getRGB = captureRGB(x + (i-1), y + (j-1));
                        r += getRGB[0] * gausMasc[i][j];
                        g += getRGB[1] * gausMasc[i][j];
                        b += getRGB[2] * gausMasc[i][j];
                    }
                }

                int[] setRGB = {r/16,g/16,b/16};
                gausApply.setRGB(x,y,joinRGB(setRGB));

            }
        }

        saveANDraw(gausApply);
    };
//I see what is the most repeated value among the array that was passed
    private int getFashion(ArrayList<Integer> array){
        int thisGuy = 0;
        int testGuy;
        int maxGuy = 0;
        for(int i=0; i < array.size(); ++i){
            testGuy = 0;
            for(int j=0; j < array.size(); ++j){
                if(array.get(i) == array.get(j)){
                    testGuy++;
                    if(testGuy >= maxGuy){
                        maxGuy = testGuy;
                        thisGuy = array.get(i);
                    }
                }
            }
        }
        return thisGuy;
    }
}
