import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class EdgeDetection extends Menu{

    //make roberts edge detection calling madeRobertsSobel method
    ActionListener roberts = e -> {
        int[][] xKernel = { {0,0,0},
                {0,-1,0},
                {0,0,1}
        };

        int[][] yKernel = { {0,0,0},
                {0,0,-1},
                {0,1,0}
        };

        madeRobertsSobel(xKernel, yKernel);

    };

    //make sobel edge detection calling madeRobertsSobel method
    ActionListener sobel = e -> {
        int[][] xKernel = { {1,0,-1},
                {2,0,-2},
                {1,0,-1}
        };

        int[][] yKernel = { {1,2,1},
                {0,0,0},
                {-1,-2,-1}
        };

        madeRobertsSobel(xKernel, yKernel);

    };

    //made robinson edge detection
    ActionListener robinson = e -> {
        int[][] mask1 = { {1,0,-1},
                {2,0,-2},
                {1,0,-1}
        };

        int[][] mask2 = { {0,-1,-2},
                {1,0,-1},
                {2,1,0}
        };

        int[][] mask3 = { {-1,-2,-1},
                {0,0,0},
                {1,2,1}
        };

        int[][] mask4 = { {-2,-1,0},
                {-1,0,1},
                {0,1,2}
        };

        int[][] mask5 = { {-1,0,1},
                {-2,0,2},
                {-1,0,1}
        };

        int[][] mask6 = { {0,1,2},
                {-1,0,1},
                {-2,-1,0}
        };

        int[][] mask7 = { {1,2,1},
                {0,0,0},
                {-1,-2,-1}
        };

        int[][] mask8 = { {2,1,0},
                {1,0,-1},
                {0,-1,-2}
        };

        Double g;
        Double gm1;
        Double gm2;
        Double gm3;
        Double gm4;
        Double gm5;
        Double gm6;
        Double gm7;
        Double gm8;

        Double threshold = Double.parseDouble(JOptionPane.showInputDialog(null,"threshold"));

        BufferedImage robinsionApply = prepareImage();

        for(int y = 1; y < robinsionApply.getHeight()-1; y++){
            for(int x = 1; x < robinsionApply.getWidth()-1; x++) {

                gm1 = gm2 = gm3 = gm4 = gm5 = gm6 = gm7 = gm8 = 0.0;
                int graySclale =0;
                for(int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        int[] getRGB = captureRGB(x+(i-1),y+(j-1));
                        graySclale = (getRGB[0] + getRGB[1] + getRGB [2])/3;
                        gm1 += graySclale * mask1[i][j];
                        gm2 += graySclale * mask2[i][j];
                        gm3 += graySclale * mask3[i][j];
                        gm4 += graySclale * mask4[i][j];
                        gm5 += graySclale * mask5[i][j];
                        gm6 += graySclale * mask6[i][j];
                        gm7 += graySclale * mask7[i][j];
                        gm8 += graySclale * mask8[i][j];
                    }
                }

                g = gm1;

                if(gm2 > g)
                    g = gm2;
                if(gm3 > g)
                    g = gm3;
                if(gm4 > g)
                    g = gm4;
                if(gm5 > g)
                    g = gm5;
                if(gm6 > g)
                    g = gm6;
                if(gm7 > g)
                    g = gm7;
                if(gm8 > g)
                    g = gm8;

                graySclale = joinRGB(new int[]{0,0,0});

                if(g > threshold){
                    graySclale = joinRGB(new int[]{255,255,255});
                }

                robinsionApply.setRGB(x,y,graySclale);
            }
        }

        saveANDraw(robinsionApply);

    };

    //made edge detection -- robert and sobel only change matrix
    private void madeRobertsSobel(int[][] xKernel, int[][] yKernel){

        Double gx;
        Double gy;
        Double g;
        Double threshold = Double.parseDouble(JOptionPane.showInputDialog(null,"threshold"));

        BufferedImage sobelApply = prepareImage();


        for(int y = 1; y < sobelApply.getHeight()-1; y++){
            for(int x = 1; x < sobelApply.getWidth()-1; x++){
                gx = 0.0;
                gy = 0.0;
                g  = 0.0;
                int graySclale =0;
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        int[] getRGB = captureRGB(x+(i-1),y+(j-1));
                        graySclale = (getRGB[0] + getRGB[1] + getRGB [2])/3;
                        gx += graySclale * xKernel[i][j];
                        gy += graySclale * yKernel[i][j];
                    }
                }

                g = Math.sqrt(Math.pow(gx,2) + Math.pow(gy,2));
                graySclale = joinRGB(new int[]{0,0,0});

                if(g > threshold){
                    graySclale = joinRGB(new int[]{255,255,255});
                }

                sobelApply.setRGB(x,y,graySclale);
            }
        }

        saveANDraw(sobelApply);
    }
}
