package rc.sudokugenius.views.components;

import rc.sudokugenius.global.Global;

public class ChalkTextArea extends javax.swing.JTextArea {

    public ChalkTextArea() {
        initComponents();

        setFont(Global.CHALK_FONT.deriveFont(22f));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(61, 66, 56));
        setEditable(false);
        setForeground(new java.awt.Color(255, 255, 255));
        setFocusable(false);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}