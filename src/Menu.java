import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


public class Menu{

    private final JMenuBar menuBar = new JMenuBar();

    //create my menuBar using the makeMenu method
    public JMenuBar buildMenu() {

        makeMenu("File", "Open Image", new FIle().listenerOpenImage);
        makeMenu("File", "Save Image", new FIle().saveAlteredImage);
        makeMenu("File", "About", new FIle().about);

        makeMenu("Transformation", "Translation", new Transformation().translation);
        makeMenu("Transformation", "Enlargement", new Transformation().enlargementReduction);
        makeMenu("Transformation", "Reduction",new Transformation().enlargementReduction);
        makeMenu("Transformation", "Mirroring", new Transformation().mirroring);
        makeMenu("Transformation", "Rotation", new Transformation().rotation);

        makeMenu("Pre-Processing", "GrayScale", new PreProcessing().grayScale);
        makeMenu("Pre-Processing", "Glare", new PreProcessing().glare);
        makeMenu("Pre-Processing", "Contrast", new PreProcessing().contrast);

        makeMenu("Threshold", "Threshold", new Threshold().threshold);

        makeMenu("LowPassFilter", "Average", new LowPassFilter().Average);
        makeMenu("LowPassFilter", "Fashion", new LowPassFilter().Fashion);
        makeMenu("LowPassFilter", "median", new LowPassFilter().median);
        makeMenu("LowPassFilter", "Gaussian", new LowPassFilter().Gaussian);

        makeMenu("EdgeDetection", "roberts", new EdgeDetection().roberts);
        makeMenu("EdgeDetection", "sobel", new EdgeDetection().sobel);
        makeMenu("EdgeDetection", "robinson", new EdgeDetection().robinson);

        makeMenu("MathematicalMorphology", "dilation", new MathematicalMorphology().dilation);
        makeMenu("MathematicalMorphology", "erosion", new MathematicalMorphology().erosion);
        makeMenu("MathematicalMorphology", "opening", new MathematicalMorphology().opening);
        makeMenu("MathematicalMorphology", "closure", new MathematicalMorphology().closure);
        makeMenu("MathematicalMorphology", "hilditch", new MathematicalMorphology().hilditch);
        return menuBar;
    }

    //see if the menu is already created, if not, I call the function again adding the menu so that I can place the menu item
    private void makeMenu(String menuName, String subMenuName, ActionListener actionListener){
        for(int i = 0; i < menuBar.getMenuCount(); i++){
            if(menuName.equals(menuBar.getMenu(i).getText())){
                menuBar.getMenu(i).add(new JMenuItem(subMenuName)).addActionListener(actionListener);
                return;
            }
        }
        menuBar.add(new JMenu(menuName));
        makeMenu(menuName,subMenuName,actionListener);
    }

    //I prepare an image to be processed, a method common to the menus
    public BufferedImage prepareImage(){
        BufferedImage img =  new BufferedImage(Constants.alteredImage.getWidth(), Constants.alteredImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        img.getGraphics().setColor(Constants.myPanelImg.getBackground());
        img.getGraphics().fillRect(0,0,img.getWidth(),img.getHeight());

        return img;
    }
    //I save my image and draw it
    public void saveANDraw(BufferedImage img){
        Constants.modifyImages.add(img);
        Constants.alteredImage = img;

        BufferedImage cleanImage = prepareImage();
        Graphics2D g = (Graphics2D) Constants.myPanelImg.getGraphics();
        g.drawImage(cleanImage, 580, 175, 500, 360, null);
        g.drawImage(img, 580, 175, 500, 360, null);
    }


    //capture the red green and blue of the rgb and return an array of integers
    public int[] captureRGB(int x, int y){
        int r = (Constants.alteredImage.getRGB(x,y) >> 16) & 0xFF;
        int g = (Constants.alteredImage.getRGB(x,y) >>  8) & 0xFF;
        int b = (Constants.alteredImage.getRGB(x,y) >>  0) & 0xFF;

        return new int[] {r,g,b};
    }

    //join the red green and blue in rgb and return an integer
    public int joinRGB(int[] rgb){
        int red   = (rgb[0] & 0xFF) << 16;
        int green = (rgb[1] & 0xFF) <<  8;
        int blue  = (rgb[2] & 0xFF) <<  0;

        return red | green | blue;
    }
}
