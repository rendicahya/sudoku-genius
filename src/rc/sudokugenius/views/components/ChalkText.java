package rc.sudokugenius.views.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import rc.sudokugenius.global.Global;
import rc.swing.JLabel;

public class ChalkText extends JLabel {

    private final double degrees;

    public ChalkText() {
        degrees = Math.random() * .2 - .1;

        initComponents();

        setFont(Global.CHALK_FONT.deriveFont(34f));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(degrees, getWidth() / 2, getHeight() / 2);
        super.paintComponent(g2d);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setForeground(new java.awt.Color(255, 255, 255));
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}