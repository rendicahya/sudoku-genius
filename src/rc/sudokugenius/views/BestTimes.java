package rc.sudokugenius.views;

import java.util.StringTokenizer;
import rc.sudokugenius.global.Global;
import rc.sudokugenius.sudoku.Difficulty;

public class BestTimes extends rc.swing.JDialog {

    public BestTimes() {
        initComponents();

        setResizable(false);
    }

    private void showBestTimes(int difficulty) {
        String get = Global.PROPERTIES.get(difficulty, "");

        if (!get.isEmpty()) {
            get = get.substring(0, get.length() - 1);

            StringTokenizer tokenizer = new StringTokenizer(get, ";");
            StringBuilder str = new StringBuilder();
            int i = 0;

            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                String[] split = token.split(",");
                String name = split[0];
                String time = split[1];

                str.append(++i).
                        append(". ").
                        append(name).
                        append(" - ").
                        append(time).
                        append("\n");
            }

            txtBestTimes.setText(str.toString());
        } else {
            txtBestTimes.setText("Record Empty");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        chalkText1 = new rc.sudokugenius.views.components.ChalkText();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        cmdVeryEasy = new rc.sudokugenius.views.components.ChalkButton();
        cmdEasy = new rc.sudokugenius.views.components.ChalkButton();
        cmdNormal = new rc.sudokugenius.views.components.ChalkButton();
        cmdHard = new rc.sudokugenius.views.components.ChalkButton();
        cmdVeryHard = new rc.sudokugenius.views.components.ChalkButton();
        cmdClose = new rc.sudokugenius.views.components.ChalkButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtBestTimes = new rc.sudokugenius.views.components.ChalkTextArea();

        FormListener formListener = new FormListener();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Best Times");
        setModal(true);

        jPanel1.setBackground(new java.awt.Color(61, 66, 56));

        chalkText1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chalkText1.setText("- Best Times -");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel3.setOpaque(false);

        jPanel2.setOpaque(false);

        cmdVeryEasy.setText("Very Easy");
        cmdVeryEasy.addActionListener(formListener);

        cmdEasy.setText("Easy");
        cmdEasy.addActionListener(formListener);

        cmdNormal.setText("Normal");
        cmdNormal.addActionListener(formListener);

        cmdHard.setText("Hard");
        cmdHard.addActionListener(formListener);

        cmdVeryHard.setText("Very Hard");
        cmdVeryHard.addActionListener(formListener);

        cmdClose.setText("Close");
        cmdClose.addActionListener(formListener);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cmdVeryEasy, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
            .addComponent(cmdEasy, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
            .addComponent(cmdNormal, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
            .addComponent(cmdHard, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
            .addComponent(cmdVeryHard, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
            .addComponent(cmdClose, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(cmdVeryEasy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdEasy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdNormal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdHard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdVeryHard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane1.setBorder(null);

        txtBestTimes.setColumns(20);
        txtBestTimes.setRows(5);
        jScrollPane1.setViewportView(txtBestTimes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chalkText1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chalkText1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == cmdVeryEasy) {
                BestTimes.this.cmdVeryEasyActionPerformed(evt);
            }
            else if (evt.getSource() == cmdEasy) {
                BestTimes.this.cmdEasyActionPerformed(evt);
            }
            else if (evt.getSource() == cmdNormal) {
                BestTimes.this.cmdNormalActionPerformed(evt);
            }
            else if (evt.getSource() == cmdHard) {
                BestTimes.this.cmdHardActionPerformed(evt);
            }
            else if (evt.getSource() == cmdVeryHard) {
                BestTimes.this.cmdVeryHardActionPerformed(evt);
            }
            else if (evt.getSource() == cmdClose) {
                BestTimes.this.cmdCloseActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void cmdVeryEasyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdVeryEasyActionPerformed
        showBestTimes(Difficulty.VERY_EASY);
    }//GEN-LAST:event_cmdVeryEasyActionPerformed

    private void cmdEasyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEasyActionPerformed
        showBestTimes(Difficulty.EASY);
    }//GEN-LAST:event_cmdEasyActionPerformed

    private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseActionPerformed
        dispose();
    }//GEN-LAST:event_cmdCloseActionPerformed

    private void cmdNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNormalActionPerformed
        showBestTimes(Difficulty.NORMAL);
    }//GEN-LAST:event_cmdNormalActionPerformed

    private void cmdHardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdHardActionPerformed
        showBestTimes(Difficulty.HARD);
    }//GEN-LAST:event_cmdHardActionPerformed

    private void cmdVeryHardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdVeryHardActionPerformed
        showBestTimes(Difficulty.VERY_HARD);
    }//GEN-LAST:event_cmdVeryHardActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rc.sudokugenius.views.components.ChalkText chalkText1;
    private rc.sudokugenius.views.components.ChalkButton cmdClose;
    private rc.sudokugenius.views.components.ChalkButton cmdEasy;
    private rc.sudokugenius.views.components.ChalkButton cmdHard;
    private rc.sudokugenius.views.components.ChalkButton cmdNormal;
    private rc.sudokugenius.views.components.ChalkButton cmdVeryEasy;
    private rc.sudokugenius.views.components.ChalkButton cmdVeryHard;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private rc.sudokugenius.views.components.ChalkTextArea txtBestTimes;
    // End of variables declaration//GEN-END:variables
}