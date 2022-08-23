import javax.swing.*;
import java.awt.event.ActionListener;


public class Menu {

    private JMenuBar menuBar = new JMenuBar();

    public JMenuBar buildMenu() {

        makeMenu("File", "Open Image", new FIle().listenerOpenImage);
        makeMenu("File", "Save Image", new FIle().saveAlteredImage);
        makeMenu("File", "About", new FIle().about);

        makeMenu("Transformation", "Translation", new Transformation().translation);
        makeMenu("Transformation", "Enlargement", new Transformation().enlargement);
        makeMenu("Transformation", "Reduction",new Transformation().reduction);
        makeMenu("Transformation", "Mirroring", new Transformation().mirroring);
        makeMenu("Transformation", "Rotation", new Transformation().rotation);

//        makeMenu("Pre-Processing", "GrayScale", null);
//        makeMenu("Pre-Processing", "Glare", null);
//        makeMenu("Pre-Processing", "Contrast", null);

        return menuBar;
    }


    private void makeMenu(String menuName, String subMenuName, ActionListener actionListener){

        for(int i = 0; i < menuBar.getMenuCount(); i++){
            if(menuName.equals(menuBar.getMenu(i).getText())){
                menuBar.getMenu(i).add(new JMenuItem(subMenuName)).addActionListener(actionListener);
                return;
            }
        }

        JMenu menu = new JMenu(menuName);
        JMenuItem menuItem = new JMenuItem(subMenuName);
        menuItem.addActionListener(actionListener);

        menu.add(menuItem);

        menuBar.add(menu);
    }
}
