package rc.sudokugenius.views.components;

import java.awt.Color;
import javax.swing.JPanel;
import rc.collections.ArrayList;
import rc.sudokugenius.sudoku.operations.CellCandidatesFinder;

public class Board extends JPanel {

    private ChalkCell[][] cells;

    public Board() {
        initComponents();

        setBackground(new Color(1, 1, 1, .15f));

        cells = new ChalkCell[][]{
                    {cell00, cell01, cell02, cell03, cell04, cell05, cell06, cell07, cell08},
                    {cell10, cell11, cell12, cell13, cell14, cell15, cell16, cell17, cell18},
                    {cell20, cell21, cell22, cell23, cell24, cell25, cell26, cell27, cell28},
                    {cell30, cell31, cell32, cell33, cell34, cell35, cell36, cell37, cell38},
                    {cell40, cell41, cell42, cell43, cell44, cell45, cell46, cell47, cell48},
                    {cell50, cell51, cell52, cell53, cell54, cell55, cell56, cell57, cell58},
                    {cell60, cell61, cell62, cell63, cell64, cell65, cell66, cell67, cell68},
                    {cell70, cell71, cell72, cell73, cell74, cell75, cell76, cell77, cell78},
                    {cell80, cell81, cell82, cell83, cell84, cell85, cell86, cell87, cell88}
                };

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                cells[r][c].setBoard(this);
            }
        }
    }

    public void clear() {
        for (ChalkCell[] row : cells) {
            for (ChalkCell cell : row) {
                ArrayList<Integer> candidates = new ArrayList<Integer>();
                candidates.addAll(1, 2, 3, 4, 5, 6, 7, 8, 9);

                cell.clear();
                cell.setCandidates(candidates);
            }
        }
    }

    public void lock() {
        for (ChalkCell[] row : cells) {
            for (ChalkCell cell : row) {
                if (cell.getValue() != 0) {
                    cell.setEditable(false);
                }
            }
        }
    }

    public void recalculateCandidates() {
        int[][] puzzle = getCellValues();

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (cells[row][col].getValue() == 0) {
                    cells[row][col].setCandidates(CellCandidatesFinder.find(puzzle, row, col));
                }
            }
        }
    }

    public int[][] getCellValues() {
        int[][] puzzle = new int[9][9];

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                puzzle[r][c] = cells[r][c].getValue();
            }
        }

        return puzzle;
    }

    public void enableRollover(boolean enable) {
        for (ChalkCell[] row : cells) {
            for (ChalkCell cell : row) {
                cell.enableRollover(enable);
            }
        }
    }

    public void alwaysShowCandidates(boolean show) {
        for (ChalkCell[] row : cells) {
            for (ChalkCell cell : row) {
                cell.alwaysShowCandidates(show);
            }
        }
    }

    public String getCellValuesAsString() {
        StringBuilder puzzle = new StringBuilder();
        StringBuilder answer = new StringBuilder();

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int value = cells[row][col].getValue();

                if (cells[row][col].isEditable()) {
                    answer.append(value);
                    puzzle.append(0);
                } else {
                    puzzle.append(value);
                }
            }
        }

        return puzzle.append(answer).toString();
    }

    public ChalkCell[][] getCells() {
        return cells;
    }

    public boolean isFilled() {
        for (ChalkCell[] row : cells) {
            for (ChalkCell cell : row) {
                if (cell.getValue() == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isEmpty() {
        for (ChalkCell[] row : cells) {
            for (ChalkCell cell : row) {
                if (cell.getValue() != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public void resetForegroundColor() {
        final Color foregound = new Color(102, 102, 102);

        for (ChalkCell[] row : cells) {
            for (ChalkCell col : row) {
                col.setForeground(foregound);
            }
        }
    }

    public void setCellError(ArrayList<int[]> errorCells) {
        for (int[] cell : errorCells) {
            cells[cell[0]][cell[1]].setForeground(Color.RED);
        }
    }

    public void fillInBlanks(String value) {
        for (int row = 0, chr = 0; row < 9; row++) {
            for (int col = 0; col < 9 && chr < value.length(); col++) {
                if (cells[row][col].isEditable()) {
                    if (value.charAt(chr) != '0') {
                        cells[row][col].setValue(Character.getNumericValue(value.charAt(chr)));
                    }

                    chr++;
                }
            }
        }

        recalculateCandidates();
    }

    public void clearBlanks() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (cells[row][col].isEditable()) {
                    cells[row][col].clear();
                }
            }
        }
    }

    public void setCellValues(String value) {
        final int length = value.length();
        char[] charValue = value.toCharArray();

        for (int row = 0, chr = 0; row < 9; row++) {
            for (int col = 0; col < 9 && chr < length; col++, chr++) {
                if (Character.isDigit(charValue[chr]) && charValue[chr] != '0') {
                    cells[row][col].setValue(Character.getNumericValue(charValue[chr]));
                }
            }
        }
    }

    public void setCellValues(int[][] values) {
        if (values != null) {
            for (int row = 0, cell = 0; row < 9 && row < values.length; row++) {
                for (int col = 0; col < 9 && col < values[row].length; col++, cell++) {
                    if (values[row][col] != 0) {
                        cells[row][col].setValue(values[row][col]);
                    }
                }
            }

            recalculateCandidates();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cell00 = new rc.sudokugenius.views.components.ChalkCell();
        cell01 = new rc.sudokugenius.views.components.ChalkCell();
        cell02 = new rc.sudokugenius.views.components.ChalkCell();
        cell10 = new rc.sudokugenius.views.components.ChalkCell();
        cell11 = new rc.sudokugenius.views.components.ChalkCell();
        cell12 = new rc.sudokugenius.views.components.ChalkCell();
        cell20 = new rc.sudokugenius.views.components.ChalkCell();
        cell21 = new rc.sudokugenius.views.components.ChalkCell();
        cell22 = new rc.sudokugenius.views.components.ChalkCell();
        jPanel2 = new javax.swing.JPanel();
        cell03 = new rc.sudokugenius.views.components.ChalkCell();
        cell04 = new rc.sudokugenius.views.components.ChalkCell();
        cell05 = new rc.sudokugenius.views.components.ChalkCell();
        cell13 = new rc.sudokugenius.views.components.ChalkCell();
        cell14 = new rc.sudokugenius.views.components.ChalkCell();
        cell15 = new rc.sudokugenius.views.components.ChalkCell();
        cell23 = new rc.sudokugenius.views.components.ChalkCell();
        cell24 = new rc.sudokugenius.views.components.ChalkCell();
        cell25 = new rc.sudokugenius.views.components.ChalkCell();
        jPanel3 = new javax.swing.JPanel();
        cell06 = new rc.sudokugenius.views.components.ChalkCell();
        cell07 = new rc.sudokugenius.views.components.ChalkCell();
        cell08 = new rc.sudokugenius.views.components.ChalkCell();
        cell16 = new rc.sudokugenius.views.components.ChalkCell();
        cell17 = new rc.sudokugenius.views.components.ChalkCell();
        cell18 = new rc.sudokugenius.views.components.ChalkCell();
        cell26 = new rc.sudokugenius.views.components.ChalkCell();
        cell27 = new rc.sudokugenius.views.components.ChalkCell();
        cell28 = new rc.sudokugenius.views.components.ChalkCell();
        jPanel4 = new javax.swing.JPanel();
        cell30 = new rc.sudokugenius.views.components.ChalkCell();
        cell31 = new rc.sudokugenius.views.components.ChalkCell();
        cell32 = new rc.sudokugenius.views.components.ChalkCell();
        cell40 = new rc.sudokugenius.views.components.ChalkCell();
        cell41 = new rc.sudokugenius.views.components.ChalkCell();
        cell42 = new rc.sudokugenius.views.components.ChalkCell();
        cell50 = new rc.sudokugenius.views.components.ChalkCell();
        cell51 = new rc.sudokugenius.views.components.ChalkCell();
        cell52 = new rc.sudokugenius.views.components.ChalkCell();
        jPanel5 = new javax.swing.JPanel();
        cell33 = new rc.sudokugenius.views.components.ChalkCell();
        cell34 = new rc.sudokugenius.views.components.ChalkCell();
        cell35 = new rc.sudokugenius.views.components.ChalkCell();
        cell43 = new rc.sudokugenius.views.components.ChalkCell();
        cell44 = new rc.sudokugenius.views.components.ChalkCell();
        cell45 = new rc.sudokugenius.views.components.ChalkCell();
        cell53 = new rc.sudokugenius.views.components.ChalkCell();
        cell54 = new rc.sudokugenius.views.components.ChalkCell();
        cell55 = new rc.sudokugenius.views.components.ChalkCell();
        jPanel6 = new javax.swing.JPanel();
        cell36 = new rc.sudokugenius.views.components.ChalkCell();
        cell37 = new rc.sudokugenius.views.components.ChalkCell();
        cell38 = new rc.sudokugenius.views.components.ChalkCell();
        cell46 = new rc.sudokugenius.views.components.ChalkCell();
        cell47 = new rc.sudokugenius.views.components.ChalkCell();
        cell48 = new rc.sudokugenius.views.components.ChalkCell();
        cell56 = new rc.sudokugenius.views.components.ChalkCell();
        cell57 = new rc.sudokugenius.views.components.ChalkCell();
        cell58 = new rc.sudokugenius.views.components.ChalkCell();
        jPanel7 = new javax.swing.JPanel();
        cell60 = new rc.sudokugenius.views.components.ChalkCell();
        cell61 = new rc.sudokugenius.views.components.ChalkCell();
        cell62 = new rc.sudokugenius.views.components.ChalkCell();
        cell70 = new rc.sudokugenius.views.components.ChalkCell();
        cell71 = new rc.sudokugenius.views.components.ChalkCell();
        cell72 = new rc.sudokugenius.views.components.ChalkCell();
        cell80 = new rc.sudokugenius.views.components.ChalkCell();
        cell81 = new rc.sudokugenius.views.components.ChalkCell();
        cell82 = new rc.sudokugenius.views.components.ChalkCell();
        jPanel8 = new javax.swing.JPanel();
        cell63 = new rc.sudokugenius.views.components.ChalkCell();
        cell64 = new rc.sudokugenius.views.components.ChalkCell();
        cell65 = new rc.sudokugenius.views.components.ChalkCell();
        cell73 = new rc.sudokugenius.views.components.ChalkCell();
        cell74 = new rc.sudokugenius.views.components.ChalkCell();
        cell75 = new rc.sudokugenius.views.components.ChalkCell();
        cell83 = new rc.sudokugenius.views.components.ChalkCell();
        cell84 = new rc.sudokugenius.views.components.ChalkCell();
        cell85 = new rc.sudokugenius.views.components.ChalkCell();
        jPanel9 = new javax.swing.JPanel();
        cell66 = new rc.sudokugenius.views.components.ChalkCell();
        cell67 = new rc.sudokugenius.views.components.ChalkCell();
        cell68 = new rc.sudokugenius.views.components.ChalkCell();
        cell76 = new rc.sudokugenius.views.components.ChalkCell();
        cell77 = new rc.sudokugenius.views.components.ChalkCell();
        cell78 = new rc.sudokugenius.views.components.ChalkCell();
        cell86 = new rc.sudokugenius.views.components.ChalkCell();
        cell87 = new rc.sudokugenius.views.components.ChalkCell();
        cell88 = new rc.sudokugenius.views.components.ChalkCell();

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setPreferredSize(new java.awt.Dimension(468, 468));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cell00.setBackground(new java.awt.Color(49, 53, 45));
        jPanel1.add(cell00, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        cell01.setBackground(new java.awt.Color(49, 53, 45));
        jPanel1.add(cell01, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 2, -1, -1));

        cell02.setBackground(new java.awt.Color(49, 53, 45));
        jPanel1.add(cell02, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 2, -1, -1));

        cell10.setBackground(new java.awt.Color(49, 53, 45));
        jPanel1.add(cell10, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 52, -1, -1));

        cell11.setBackground(new java.awt.Color(49, 53, 45));
        jPanel1.add(cell11, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 52, -1, -1));

        cell12.setBackground(new java.awt.Color(49, 53, 45));
        jPanel1.add(cell12, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 52, -1, -1));

        cell20.setBackground(new java.awt.Color(49, 53, 45));
        jPanel1.add(cell20, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 102, -1, -1));

        cell21.setBackground(new java.awt.Color(49, 53, 45));
        jPanel1.add(cell21, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 102, -1, -1));

        cell22.setBackground(new java.awt.Color(49, 53, 45));
        jPanel1.add(cell22, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 102, -1, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 154, 154));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cell03.setBackground(new java.awt.Color(68, 72, 64));
        jPanel2.add(cell03, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        cell04.setBackground(new java.awt.Color(68, 72, 64));
        jPanel2.add(cell04, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 2, -1, -1));

        cell05.setBackground(new java.awt.Color(68, 72, 64));
        jPanel2.add(cell05, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 2, -1, -1));

        cell13.setBackground(new java.awt.Color(68, 72, 64));
        jPanel2.add(cell13, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 52, -1, -1));

        cell14.setBackground(new java.awt.Color(68, 72, 64));
        jPanel2.add(cell14, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 52, -1, -1));

        cell15.setBackground(new java.awt.Color(68, 72, 64));
        jPanel2.add(cell15, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 52, -1, -1));

        cell23.setBackground(new java.awt.Color(68, 72, 64));
        jPanel2.add(cell23, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 102, -1, -1));

        cell24.setBackground(new java.awt.Color(68, 72, 64));
        jPanel2.add(cell24, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 102, -1, -1));

        cell25.setBackground(new java.awt.Color(68, 72, 64));
        jPanel2.add(cell25, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 102, -1, -1));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(157, 5, 154, 154));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cell06.setBackground(new java.awt.Color(49, 53, 45));
        jPanel3.add(cell06, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        cell07.setBackground(new java.awt.Color(49, 53, 45));
        jPanel3.add(cell07, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 2, -1, -1));

        cell08.setBackground(new java.awt.Color(49, 53, 45));
        jPanel3.add(cell08, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 2, -1, -1));

        cell16.setBackground(new java.awt.Color(49, 53, 45));
        jPanel3.add(cell16, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 52, -1, -1));

        cell17.setBackground(new java.awt.Color(49, 53, 45));
        jPanel3.add(cell17, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 52, -1, -1));

        cell18.setBackground(new java.awt.Color(49, 53, 45));
        jPanel3.add(cell18, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 52, -1, -1));

        cell26.setBackground(new java.awt.Color(49, 53, 45));
        jPanel3.add(cell26, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 102, -1, -1));

        cell27.setBackground(new java.awt.Color(49, 53, 45));
        jPanel3.add(cell27, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 102, -1, -1));

        cell28.setBackground(new java.awt.Color(49, 53, 45));
        jPanel3.add(cell28, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 102, -1, -1));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(309, 5, 154, 154));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cell30.setBackground(new java.awt.Color(68, 72, 64));
        jPanel4.add(cell30, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        cell31.setBackground(new java.awt.Color(68, 72, 64));
        jPanel4.add(cell31, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 2, -1, -1));

        cell32.setBackground(new java.awt.Color(68, 72, 64));
        jPanel4.add(cell32, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 2, -1, -1));

        cell40.setBackground(new java.awt.Color(68, 72, 64));
        jPanel4.add(cell40, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 52, -1, -1));

        cell41.setBackground(new java.awt.Color(68, 72, 64));
        jPanel4.add(cell41, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 52, -1, -1));

        cell42.setBackground(new java.awt.Color(68, 72, 64));
        jPanel4.add(cell42, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 52, -1, -1));

        cell50.setBackground(new java.awt.Color(68, 72, 64));
        jPanel4.add(cell50, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 102, -1, -1));

        cell51.setBackground(new java.awt.Color(68, 72, 64));
        jPanel4.add(cell51, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 102, -1, -1));

        cell52.setBackground(new java.awt.Color(68, 72, 64));
        jPanel4.add(cell52, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 102, -1, -1));

        add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 157, 154, 154));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cell33.setBackground(new java.awt.Color(49, 53, 45));
        jPanel5.add(cell33, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        cell34.setBackground(new java.awt.Color(49, 53, 45));
        jPanel5.add(cell34, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 2, -1, -1));

        cell35.setBackground(new java.awt.Color(49, 53, 45));
        jPanel5.add(cell35, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 2, -1, -1));

        cell43.setBackground(new java.awt.Color(49, 53, 45));
        jPanel5.add(cell43, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 52, -1, -1));

        cell44.setBackground(new java.awt.Color(49, 53, 45));
        jPanel5.add(cell44, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 52, -1, -1));

        cell45.setBackground(new java.awt.Color(49, 53, 45));
        jPanel5.add(cell45, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 52, -1, -1));

        cell53.setBackground(new java.awt.Color(49, 53, 45));
        jPanel5.add(cell53, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 102, -1, -1));

        cell54.setBackground(new java.awt.Color(49, 53, 45));
        jPanel5.add(cell54, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 102, -1, -1));

        cell55.setBackground(new java.awt.Color(49, 53, 45));
        jPanel5.add(cell55, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 102, -1, -1));

        add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(157, 157, 154, 154));

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cell36.setBackground(new java.awt.Color(68, 72, 64));
        jPanel6.add(cell36, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        cell37.setBackground(new java.awt.Color(68, 72, 64));
        jPanel6.add(cell37, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 2, -1, -1));

        cell38.setBackground(new java.awt.Color(68, 72, 64));
        jPanel6.add(cell38, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 2, -1, -1));

        cell46.setBackground(new java.awt.Color(68, 72, 64));
        jPanel6.add(cell46, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 52, -1, -1));

        cell47.setBackground(new java.awt.Color(68, 72, 64));
        jPanel6.add(cell47, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 52, -1, -1));

        cell48.setBackground(new java.awt.Color(68, 72, 64));
        jPanel6.add(cell48, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 52, -1, -1));

        cell56.setBackground(new java.awt.Color(68, 72, 64));
        jPanel6.add(cell56, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 102, -1, -1));

        cell57.setBackground(new java.awt.Color(68, 72, 64));
        jPanel6.add(cell57, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 102, -1, -1));

        cell58.setBackground(new java.awt.Color(68, 72, 64));
        jPanel6.add(cell58, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 102, -1, -1));

        add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(309, 157, 154, 154));

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cell60.setBackground(new java.awt.Color(49, 53, 45));
        jPanel7.add(cell60, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        cell61.setBackground(new java.awt.Color(49, 53, 45));
        jPanel7.add(cell61, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 2, -1, -1));

        cell62.setBackground(new java.awt.Color(49, 53, 45));
        jPanel7.add(cell62, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 2, -1, -1));

        cell70.setBackground(new java.awt.Color(49, 53, 45));
        jPanel7.add(cell70, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 52, -1, -1));

        cell71.setBackground(new java.awt.Color(49, 53, 45));
        jPanel7.add(cell71, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 52, -1, -1));

        cell72.setBackground(new java.awt.Color(49, 53, 45));
        jPanel7.add(cell72, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 52, -1, -1));

        cell80.setBackground(new java.awt.Color(49, 53, 45));
        jPanel7.add(cell80, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 102, -1, -1));

        cell81.setBackground(new java.awt.Color(49, 53, 45));
        jPanel7.add(cell81, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 102, -1, -1));

        cell82.setBackground(new java.awt.Color(49, 53, 45));
        jPanel7.add(cell82, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 102, -1, -1));

        add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 309, 154, 154));

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cell63.setBackground(new java.awt.Color(68, 72, 64));
        jPanel8.add(cell63, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        cell64.setBackground(new java.awt.Color(68, 72, 64));
        jPanel8.add(cell64, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 2, -1, -1));

        cell65.setBackground(new java.awt.Color(68, 72, 64));
        jPanel8.add(cell65, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 2, -1, -1));

        cell73.setBackground(new java.awt.Color(68, 72, 64));
        jPanel8.add(cell73, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 52, -1, -1));

        cell74.setBackground(new java.awt.Color(68, 72, 64));
        jPanel8.add(cell74, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 52, -1, -1));

        cell75.setBackground(new java.awt.Color(68, 72, 64));
        jPanel8.add(cell75, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 52, -1, -1));

        cell83.setBackground(new java.awt.Color(68, 72, 64));
        jPanel8.add(cell83, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 102, -1, -1));

        cell84.setBackground(new java.awt.Color(68, 72, 64));
        jPanel8.add(cell84, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 102, -1, -1));

        cell85.setBackground(new java.awt.Color(68, 72, 64));
        jPanel8.add(cell85, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 102, -1, -1));

        add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(157, 309, 154, 154));

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cell66.setBackground(new java.awt.Color(49, 53, 45));
        jPanel9.add(cell66, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        cell67.setBackground(new java.awt.Color(49, 53, 45));
        jPanel9.add(cell67, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 2, -1, -1));

        cell68.setBackground(new java.awt.Color(49, 53, 45));
        jPanel9.add(cell68, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 2, -1, -1));

        cell76.setBackground(new java.awt.Color(49, 53, 45));
        jPanel9.add(cell76, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 52, -1, -1));

        cell77.setBackground(new java.awt.Color(49, 53, 45));
        jPanel9.add(cell77, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 52, -1, -1));

        cell78.setBackground(new java.awt.Color(49, 53, 45));
        jPanel9.add(cell78, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 52, -1, -1));

        cell86.setBackground(new java.awt.Color(49, 53, 45));
        jPanel9.add(cell86, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 102, -1, -1));

        cell87.setBackground(new java.awt.Color(49, 53, 45));
        jPanel9.add(cell87, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 102, -1, -1));

        cell88.setBackground(new java.awt.Color(49, 53, 45));
        jPanel9.add(cell88, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 102, -1, -1));

        add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(309, 309, 154, 154));
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rc.sudokugenius.views.components.ChalkCell cell00;
    private rc.sudokugenius.views.components.ChalkCell cell01;
    private rc.sudokugenius.views.components.ChalkCell cell02;
    private rc.sudokugenius.views.components.ChalkCell cell03;
    private rc.sudokugenius.views.components.ChalkCell cell04;
    private rc.sudokugenius.views.components.ChalkCell cell05;
    private rc.sudokugenius.views.components.ChalkCell cell06;
    private rc.sudokugenius.views.components.ChalkCell cell07;
    private rc.sudokugenius.views.components.ChalkCell cell08;
    private rc.sudokugenius.views.components.ChalkCell cell10;
    private rc.sudokugenius.views.components.ChalkCell cell11;
    private rc.sudokugenius.views.components.ChalkCell cell12;
    private rc.sudokugenius.views.components.ChalkCell cell13;
    private rc.sudokugenius.views.components.ChalkCell cell14;
    private rc.sudokugenius.views.components.ChalkCell cell15;
    private rc.sudokugenius.views.components.ChalkCell cell16;
    private rc.sudokugenius.views.components.ChalkCell cell17;
    private rc.sudokugenius.views.components.ChalkCell cell18;
    private rc.sudokugenius.views.components.ChalkCell cell20;
    private rc.sudokugenius.views.components.ChalkCell cell21;
    private rc.sudokugenius.views.components.ChalkCell cell22;
    private rc.sudokugenius.views.components.ChalkCell cell23;
    private rc.sudokugenius.views.components.ChalkCell cell24;
    private rc.sudokugenius.views.components.ChalkCell cell25;
    private rc.sudokugenius.views.components.ChalkCell cell26;
    private rc.sudokugenius.views.components.ChalkCell cell27;
    private rc.sudokugenius.views.components.ChalkCell cell28;
    private rc.sudokugenius.views.components.ChalkCell cell30;
    private rc.sudokugenius.views.components.ChalkCell cell31;
    private rc.sudokugenius.views.components.ChalkCell cell32;
    private rc.sudokugenius.views.components.ChalkCell cell33;
    private rc.sudokugenius.views.components.ChalkCell cell34;
    private rc.sudokugenius.views.components.ChalkCell cell35;
    private rc.sudokugenius.views.components.ChalkCell cell36;
    private rc.sudokugenius.views.components.ChalkCell cell37;
    private rc.sudokugenius.views.components.ChalkCell cell38;
    private rc.sudokugenius.views.components.ChalkCell cell40;
    private rc.sudokugenius.views.components.ChalkCell cell41;
    private rc.sudokugenius.views.components.ChalkCell cell42;
    private rc.sudokugenius.views.components.ChalkCell cell43;
    private rc.sudokugenius.views.components.ChalkCell cell44;
    private rc.sudokugenius.views.components.ChalkCell cell45;
    private rc.sudokugenius.views.components.ChalkCell cell46;
    private rc.sudokugenius.views.components.ChalkCell cell47;
    private rc.sudokugenius.views.components.ChalkCell cell48;
    private rc.sudokugenius.views.components.ChalkCell cell50;
    private rc.sudokugenius.views.components.ChalkCell cell51;
    private rc.sudokugenius.views.components.ChalkCell cell52;
    private rc.sudokugenius.views.components.ChalkCell cell53;
    private rc.sudokugenius.views.components.ChalkCell cell54;
    private rc.sudokugenius.views.components.ChalkCell cell55;
    private rc.sudokugenius.views.components.ChalkCell cell56;
    private rc.sudokugenius.views.components.ChalkCell cell57;
    private rc.sudokugenius.views.components.ChalkCell cell58;
    private rc.sudokugenius.views.components.ChalkCell cell60;
    private rc.sudokugenius.views.components.ChalkCell cell61;
    private rc.sudokugenius.views.components.ChalkCell cell62;
    private rc.sudokugenius.views.components.ChalkCell cell63;
    private rc.sudokugenius.views.components.ChalkCell cell64;
    private rc.sudokugenius.views.components.ChalkCell cell65;
    private rc.sudokugenius.views.components.ChalkCell cell66;
    private rc.sudokugenius.views.components.ChalkCell cell67;
    private rc.sudokugenius.views.components.ChalkCell cell68;
    private rc.sudokugenius.views.components.ChalkCell cell70;
    private rc.sudokugenius.views.components.ChalkCell cell71;
    private rc.sudokugenius.views.components.ChalkCell cell72;
    private rc.sudokugenius.views.components.ChalkCell cell73;
    private rc.sudokugenius.views.components.ChalkCell cell74;
    private rc.sudokugenius.views.components.ChalkCell cell75;
    private rc.sudokugenius.views.components.ChalkCell cell76;
    private rc.sudokugenius.views.components.ChalkCell cell77;
    private rc.sudokugenius.views.components.ChalkCell cell78;
    private rc.sudokugenius.views.components.ChalkCell cell80;
    private rc.sudokugenius.views.components.ChalkCell cell81;
    private rc.sudokugenius.views.components.ChalkCell cell82;
    private rc.sudokugenius.views.components.ChalkCell cell83;
    private rc.sudokugenius.views.components.ChalkCell cell84;
    private rc.sudokugenius.views.components.ChalkCell cell85;
    private rc.sudokugenius.views.components.ChalkCell cell86;
    private rc.sudokugenius.views.components.ChalkCell cell87;
    private rc.sudokugenius.views.components.ChalkCell cell88;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    // End of variables declaration//GEN-END:variables
}
