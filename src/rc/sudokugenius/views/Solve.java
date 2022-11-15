package rc.sudokugenius.views;

import javax.swing.GroupLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import rc.util.UiUtil;

public class Solve extends rc.swing.JDialog {

    private MainView view;
    private ChartPanel chartPanel;

    public Solve() {
        initComponents();

        chartPanel = new ChartPanel(null);

        GroupLayout layout = new GroupLayout(chartPanelContainer);
        chartPanelContainer.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).
                addGroup(layout.createSequentialGroup().
                addComponent(chartPanel)));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).
                addGroup(layout.createSequentialGroup().
                addComponent(chartPanel)));
    }

    public void setView(MainView view) {
        this.view = view;
    }

    public int getPopulationSize() {
        return txtPopulationSize.getInt();
    }

    public int getMaxGenerations() {
        return txtMaxGenerations.getInt();
    }

    public double getCrossoverRate() {
        return Double.parseDouble(txtCrossoverRate.getText().trim());
    }

    public int getCrossoverPoints() {
        return Integer.parseInt(txtCrossoverPoints.getText().trim());
    }

    public double getMutationRate() {
        return Double.parseDouble(txtMutationRate.getText().trim());
    }

    public boolean optimizedMutationSelected() {
        return chkOptimizedEvolution.isSelected();
    }

    public boolean nakedSingleSelected() {
        return chkNakedSingle.isSelected();
    }

    public boolean hiddenSingleSelected() {
        return chkHiddenSingle.isSelected();
    }

    public void clearTime() {
        lblTime.setText(null);
    }

    public void clearChart() {
        chartPanel.setChart(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new rc.swing.JLabel();
        txtCrossoverPoints = new rc.swing.JIntegerTextField();
        chkOptimizedEvolution = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtCrossoverRate = new rc.swing.JTextField();
        jLabel5 = new rc.swing.JLabel();
        txtMutationRate = new rc.swing.JTextField();
        jLabel6 = new rc.swing.JLabel();
        jLabel7 = new rc.swing.JLabel();
        jLabel8 = new rc.swing.JLabel();
        txtPopulationSize = new rc.swing.JIntegerTextField();
        txtMaxGenerations = new rc.swing.JIntegerTextField();
        cmdGaStart = new javax.swing.JButton();
        chkNakedSingle = new javax.swing.JCheckBox();
        chkHiddenSingle = new javax.swing.JCheckBox();
        lblTime = new javax.swing.JLabel();
        chartPanelContainer = new javax.swing.JPanel();

        FormListener formListener = new FormListener();

        jLabel1.setText("Crossover Points:");

        txtCrossoverPoints.setText("1");

        chkOptimizedEvolution.setText("Optimized Evolution");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Genetic Algorithm");
        setModal(true);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Genetic Algorithm"));
        jPanel3.setPreferredSize(new java.awt.Dimension(250, 261));

        txtCrossoverRate.setText("0.8");

        jLabel5.setText("Population Size:");

        txtMutationRate.setText("0.2");

        jLabel6.setText("Mutation Rate:");

        jLabel7.setText("Max Generation:");

        jLabel8.setText("Crossover Rate:");

        txtPopulationSize.setText("20");

        txtMaxGenerations.setText("1000");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMutationRate, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(txtMaxGenerations, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(txtCrossoverRate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(txtPopulationSize, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPopulationSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaxGenerations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCrossoverRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMutationRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        cmdGaStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rc/sudokugenius/views/images/control_play_blue.png"))); // NOI18N
        cmdGaStart.setText("Start");
        cmdGaStart.addActionListener(formListener);

        chkNakedSingle.setSelected(true);
        chkNakedSingle.setText("Naked Single");

        chkHiddenSingle.setSelected(true);
        chkHiddenSingle.setText("Hidden Single");

        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkHiddenSingle)
                    .addComponent(chkNakedSingle)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdGaStart)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkNakedSingle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkHiddenSingle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdGaStart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        chartPanelContainer.setBorder(javax.swing.BorderFactory.createTitledBorder("Chart"));

        javax.swing.GroupLayout chartPanelContainerLayout = new javax.swing.GroupLayout(chartPanelContainer);
        chartPanelContainer.setLayout(chartPanelContainerLayout);
        chartPanelContainerLayout.setHorizontalGroup(
            chartPanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
        chartPanelContainerLayout.setVerticalGroup(
            chartPanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 329, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chartPanelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                    .addComponent(chartPanelContainer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == cmdGaStart) {
                Solve.this.cmdGaStartActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void cmdGaStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGaStartActionPerformed
        final long started = System.currentTimeMillis();
        view.startGA();
        lblTime.setText("Done in " + (System.currentTimeMillis() - started) + " ms");

        XYSeries series = view.getSeries();
        XYSeriesCollection dataSet = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart("Genetic Algorithm", "Generation", "Fitness", dataSet, PlotOrientation.VERTICAL, false, false, false);
        chartPanel.setChart(chart);
}//GEN-LAST:event_cmdGaStartActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartPanelContainer;
    private javax.swing.JCheckBox chkHiddenSingle;
    private javax.swing.JCheckBox chkNakedSingle;
    private javax.swing.JCheckBox chkOptimizedEvolution;
    private javax.swing.JButton cmdGaStart;
    private rc.swing.JLabel jLabel1;
    private rc.swing.JLabel jLabel5;
    private rc.swing.JLabel jLabel6;
    private rc.swing.JLabel jLabel7;
    private rc.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblTime;
    private rc.swing.JIntegerTextField txtCrossoverPoints;
    private rc.swing.JTextField txtCrossoverRate;
    private rc.swing.JIntegerTextField txtMaxGenerations;
    private rc.swing.JTextField txtMutationRate;
    private rc.swing.JIntegerTextField txtPopulationSize;
    // End of variables declaration//GEN-END:variables
}