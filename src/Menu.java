import javax.swing.*;
import java.awt.event.ActionListener;


public class Menu {

    private final JMenuBar menuBar = new JMenuBar();

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
}
