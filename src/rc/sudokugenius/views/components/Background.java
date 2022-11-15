package rc.sudokugenius.views.components;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import rc.sudokugenius.global.Global;

public class Background extends JPanel {

    public Background() {
        initComponents();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Image image = null;

        try {
//            image = ImageIO.read(new File("D:\\NetBeansProjects\\SudokuGenius\\1680_Albert-Einstein-wallpaper copy.jpg"));
            image = ImageIO.read(getClass().getResourceAsStream(Global.IMAGES_PACKAGE + "1680_Albert-Einstein-wallpaper.jpg"));
        } catch (IOException ex) {
        }

        g.drawImage(image, 0, 0, null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}