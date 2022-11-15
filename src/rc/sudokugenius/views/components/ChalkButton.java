package rc.sudokugenius.views.components;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import rc.sudokugenius.global.Global;

public class ChalkButton extends JButton {

    private final Font small;
    private final Font large;
    private final double degrees;

    public ChalkButton() {
        small = Global.CHALK_FONT.deriveFont(30f);
        large = Global.CHALK_FONT.deriveFont(34f);
        degrees = Math.random() * .2 - .1;

        initComponents();

        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setFont(small);
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

        FormListener formListener = new FormListener();

        setForeground(new java.awt.Color(255, 255, 255));
        setContentAreaFilled(false);
        setFocusable(false);
        addMouseListener(formListener);
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.MouseListener {
        FormListener() {}
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == ChalkButton.this) {
                ChalkButton.this.formMouseEntered(evt);
            }
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == ChalkButton.this) {
                ChalkButton.this.formMouseExited(evt);
            }
        }

        public void mousePressed(java.awt.event.MouseEvent evt) {
        }

        public void mouseReleased(java.awt.event.MouseEvent evt) {
        }
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        setFont(large);
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        setFont(small);
    }//GEN-LAST:event_formMouseExited
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}