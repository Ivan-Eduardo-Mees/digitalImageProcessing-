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
        Constants.alteredImage = img;
        Graphics2D g = (Graphics2D) Constants.myPanelImg.getGraphics();
        g.drawImage(img, 580, 175, 500, 360, null);
    }
}
