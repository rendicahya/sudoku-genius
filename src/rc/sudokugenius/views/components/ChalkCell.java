package rc.sudokugenius.views.components;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import rc.swing.JLabel;
import javax.swing.JLayeredPane;
import rc.collections.ArrayList;
import rc.sudokugenius.global.Global;
import rc.util.UiUtil;

public class ChalkCell extends JLayeredPane {

    private int value;
    private boolean enableRollover;
    private boolean alwaysShowCandidates;
    private boolean editable;
    private ArrayList<Integer> candidates;
    private JLabel[] subcells;
    private Board board;
    private final Color normalColor;
    private final Color focusColor;
    private final Color lockedColor;

    public ChalkCell() {
        normalColor = getForeground();
        focusColor = new Color(110, 110, 110);
        lockedColor = new Color(66, 65, 40);

        candidates = new ArrayList<Integer>();
        candidates.addAll(1, 2, 3, 4, 5, 6, 7, 8, 9);

        enableRollover = true;
        alwaysShowCandidates = false;
        editable = true;

        initComponents();

        subcells = new JLabel[]{lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9};
        UiUtil.hide(subcells);

        popMenu.setBackground(new Color(1, 1, 1, .7f));
        lblMain.setFont(Global.CHALK_FONT.deriveFont(34f));
    }

    public int getValue() {
        return value;
    }

    @Override
    public void setForeground(Color color) {
        lblMain.setForeground(color);
    }

    public void setValue(int value) {
        this.value = value;

        lblMain.setText(value);
        UiUtil.hide(subcells);
        board.requestFocus();
        setForeground(Color.WHITE);
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
        lblMain.setBackground(editable ? normalColor : lockedColor);
    }

    public boolean isEditable() {
        return editable;
    }

    public void clear() {
        value = 0;
        lblMain.setText(null);
        UiUtil.hide(subcells);
        lblMain.setBackground(normalColor);
        editable = true;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void enableRollover(boolean enable) {
        enableRollover = enable;
    }

    public void alwaysShowCandidates(boolean show) {
        alwaysShowCandidates = show;
        enableRollover = !show;

        if (show && value == 0) {
            showCandidates();
        } else {
            hideCandidates();
        }
    }

    public void setCandidates(ArrayList<Integer> candidates) {
        this.candidates = candidates;

        popMenu.removeAll();

        for (final int i : candidates) {
            JMenuItem item = new JMenuItem();
            item.setText("Fill " + i);
            item.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseReleased(MouseEvent e) {
                    setValue(i);
                    board.recalculateCandidates();
                }
            });

            popMenu.add(item);
        }

        JMenuItem clear = new JMenuItem();
        clear.setText("Clear");
        clear.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                clear();
                board.recalculateCandidates();
            }
        });

        popMenu.add(clear);
    }

    public void showCandidates() {
        if (editable) {
            UiUtil.hide(subcells);

            for (int i : candidates) {
                UiUtil.show(subcells[i - 1]);
            }
        }
    }

    public void hideCandidates() {
        UiUtil.hide(subcells);
    }

    @Override
    public void requestFocus() {
        super.requestFocus();
        lblMain.setBackground(focusColor);
        enableRollover = false;
        lblMain.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));

        if (value == 0) {
            showCandidates();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popMenu = new rc.sudokugenius.views.components.JPopupMenu();
        lbl1 = new rc.swing.JLabel();
        lbl2 = new rc.swing.JLabel();
        lbl3 = new rc.swing.JLabel();
        lbl4 = new rc.swing.JLabel();
        lbl5 = new rc.swing.JLabel();
        lbl6 = new rc.swing.JLabel();
        lbl7 = new rc.swing.JLabel();
        lbl8 = new rc.swing.JLabel();
        lbl9 = new rc.swing.JLabel();
        lblMain = new rc.swing.JLabel();

        FormListener formListener = new FormListener();

        popMenu.setBorderPainted(false);

        setPreferredSize(new java.awt.Dimension(50, 50));
        addMouseListener(formListener);
        addFocusListener(formListener);
        addKeyListener(formListener);

        lbl1.setForeground(new java.awt.Color(240, 240, 240));
        lbl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl1.setText("1");
        lbl1.setDoubleBuffered(true);
        lbl1.setFont(new java.awt.Font("Tahoma", 0, 10));
        lbl1.setBounds(1, 1, 16, 16);
        add(lbl1, javax.swing.JLayeredPane.MODAL_LAYER);

        lbl2.setForeground(new java.awt.Color(240, 240, 240));
        lbl2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl2.setText("2");
        lbl2.setDoubleBuffered(true);
        lbl2.setFont(new java.awt.Font("Tahoma", 0, 10));
        lbl2.setBounds(17, 1, 16, 16);
        add(lbl2, javax.swing.JLayeredPane.MODAL_LAYER);

        lbl3.setForeground(new java.awt.Color(240, 240, 240));
        lbl3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3.setText("3");
        lbl3.setDoubleBuffered(true);
        lbl3.setFont(new java.awt.Font("Tahoma", 0, 10));
        lbl3.setBounds(33, 1, 16, 16);
        add(lbl3, javax.swing.JLayeredPane.MODAL_LAYER);

        lbl4.setForeground(new java.awt.Color(240, 240, 240));
        lbl4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl4.setText("4");
        lbl4.setDoubleBuffered(true);
        lbl4.setFont(new java.awt.Font("Tahoma", 0, 10));
        lbl4.setBounds(1, 17, 16, 16);
        add(lbl4, javax.swing.JLayeredPane.MODAL_LAYER);

        lbl5.setForeground(new java.awt.Color(240, 240, 240));
        lbl5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl5.setText("5");
        lbl5.setDoubleBuffered(true);
        lbl5.setFont(new java.awt.Font("Tahoma", 0, 10));
        lbl5.setBounds(17, 17, 16, 16);
        add(lbl5, javax.swing.JLayeredPane.MODAL_LAYER);

        lbl6.setForeground(new java.awt.Color(240, 240, 240));
        lbl6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl6.setText("6");
        lbl6.setDoubleBuffered(true);
        lbl6.setFont(new java.awt.Font("Tahoma", 0, 10));
        lbl6.setBounds(33, 17, 16, 16);
        add(lbl6, javax.swing.JLayeredPane.MODAL_LAYER);

        lbl7.setForeground(new java.awt.Color(240, 240, 240));
        lbl7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl7.setText("7");
        lbl7.setDoubleBuffered(true);
        lbl7.setFont(new java.awt.Font("Tahoma", 0, 10));
        lbl7.setBounds(1, 33, 16, 16);
        add(lbl7, javax.swing.JLayeredPane.MODAL_LAYER);

        lbl8.setForeground(new java.awt.Color(240, 240, 240));
        lbl8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl8.setText("8");
        lbl8.setDoubleBuffered(true);
        lbl8.setFont(new java.awt.Font("Tahoma", 0, 10));
        lbl8.setBounds(17, 33, 16, 16);
        add(lbl8, javax.swing.JLayeredPane.MODAL_LAYER);

        lbl9.setForeground(new java.awt.Color(240, 240, 240));
        lbl9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl9.setText("9");
        lbl9.setDoubleBuffered(true);
        lbl9.setFont(new java.awt.Font("Tahoma", 0, 10));
        lbl9.setBounds(33, 33, 16, 16);
        add(lbl9, javax.swing.JLayeredPane.MODAL_LAYER);

        lblMain.setBackground(normalColor);
        lblMain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        lblMain.setForeground(new java.awt.Color(240, 240, 240));
        lblMain.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMain.setOpaque(true);
        lblMain.setBounds(0, 0, 50, 50);
        add(lblMain, javax.swing.JLayeredPane.PALETTE_LAYER);
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.FocusListener, java.awt.event.KeyListener, java.awt.event.MouseListener {
        FormListener() {}
        public void focusGained(java.awt.event.FocusEvent evt) {
        }

        public void focusLost(java.awt.event.FocusEvent evt) {
            if (evt.getSource() == ChalkCell.this) {
                ChalkCell.this.formFocusLost(evt);
            }
        }

        public void keyPressed(java.awt.event.KeyEvent evt) {
            if (evt.getSource() == ChalkCell.this) {
                ChalkCell.this.formKeyPressed(evt);
            }
        }

        public void keyReleased(java.awt.event.KeyEvent evt) {
        }

        public void keyTyped(java.awt.event.KeyEvent evt) {
        }

        public void mouseClicked(java.awt.event.MouseEvent evt) {
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == ChalkCell.this) {
                ChalkCell.this.formMouseEntered(evt);
            }
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == ChalkCell.this) {
                ChalkCell.this.formMouseExited(evt);
            }
        }

        public void mousePressed(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == ChalkCell.this) {
                ChalkCell.this.formMousePressed(evt);
            }
        }

        public void mouseReleased(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == ChalkCell.this) {
                ChalkCell.this.formMouseReleased(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        if (enableRollover && value == 0) {
            showCandidates();
        }
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        if (enableRollover) {
            hideCandidates();
        }
    }//GEN-LAST:event_formMouseExited

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        requestFocus();
    }//GEN-LAST:event_formMousePressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (editable) {
            final int code = evt.getKeyCode();
            int val;

            if (code >= KeyEvent.VK_1 && code <= KeyEvent.VK_9 && candidates.contains((val = Integer.parseInt(KeyEvent.getKeyText(code))))) {
                setValue(val);
                board.recalculateCandidates();
            } else if (code == KeyEvent.VK_DELETE || code == KeyEvent.VK_BACK_SPACE) {
                clear();
                board.recalculateCandidates();
                showCandidates();
            }
        }
    }//GEN-LAST:event_formKeyPressed

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        lblMain.setBackground(editable ? normalColor : lockedColor);
        lblMain.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204), 1));

        if (!alwaysShowCandidates) {
            hideCandidates();
            enableRollover = true;
        }
    }//GEN-LAST:event_formFocusLost

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        if (evt.isPopupTrigger() && editable && popMenu.getComponentCount() > 0) {
            popMenu.show(this, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_formMouseReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rc.swing.JLabel lbl1;
    private rc.swing.JLabel lbl2;
    private rc.swing.JLabel lbl3;
    private rc.swing.JLabel lbl4;
    private rc.swing.JLabel lbl5;
    private rc.swing.JLabel lbl6;
    private rc.swing.JLabel lbl7;
    private rc.swing.JLabel lbl8;
    private rc.swing.JLabel lbl9;
    private rc.swing.JLabel lblMain;
    private rc.sudokugenius.views.components.JPopupMenu popMenu;
    // End of variables declaration//GEN-END:variables
}
