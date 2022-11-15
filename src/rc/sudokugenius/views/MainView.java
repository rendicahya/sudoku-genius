package rc.sudokugenius.views;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileFilter;
import org.jfree.data.xy.XYSeries;
import rc.mvc.View;
import rc.sudokugenius.controllers.MainController;
import rc.sudokugenius.global.Global;
import rc.sudokugenius.sudoku.Algorithm;
import rc.sudokugenius.sudoku.Difficulty;
import rc.sudokugenius.sudoku.PuzzleType;
import rc.sudokugenius.views.components.Board;
import rc.sudokugenius.views.components.Stopwatch;
import rc.util.UiUtil;

public class MainView extends View<MainController> {

    private boolean ownPuzzle;
    private File savedFile;
    private BestTimes bestTimes;
    private Solve solveGA;

    public MainView(MainController controller) {
        super(controller);

        setLaf(Global.DEFAULT_LAF);
        initComponents();
        centerFrame();
        setAppIcon(Global.APP_ICON);
        board.requestFocus();

        bestTimes = new BestTimes();
        bestTimes.centerOnScreen();

        solveGA = new Solve();
        solveGA.centerOnScreen();
        solveGA.setView(this);

        ownPuzzle = true;
        puzzleFileChooser.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".puz");
            }

            @Override
            public String getDescription() {
                return "Sudoku Puzzle File (*.puz)";
            }
        });

        imageFileChooser.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".png");
            }

            @Override
            public String getDescription() {
                return "PNG Image File (*.png)";
            }
        });
    }

    private void newPuzzle(int puzzleType, int diffculty) {
        controller.generatePuzzle(diffculty, puzzleType);

        chkAlwaysShowCandidatesActionPerformed(null);
        solveGA.clearTime();
        solveGA.clearChart();
        savedFile = null;
        ownPuzzle = false;
    }

    public Board getBoard() {
        return board;
    }

    public Stopwatch getStopwatch() {
        return stopwatch;
    }

    public int getPopulationSize() {
        return solveGA.getPopulationSize();
    }

    public int getMaxGenerations() {
        return solveGA.getMaxGenerations();
    }

    public double getCrossoverRate() {
        return solveGA.getCrossoverRate();
    }

    public int getCrossoverPoints() {
        return solveGA.getCrossoverPoints();
    }

    public double getMutationRate() {
        return solveGA.getMutationRate();
    }

    public boolean optimizedMutationSelected() {
        return solveGA.optimizedMutationSelected();
    }

    public class RunAlgorithm extends SwingWorker {

        private int algorithm;

        public RunAlgorithm(int algorithm) {
            this.algorithm = algorithm;
        }

        @Override
        protected Object doInBackground() {
            if (ownPuzzle) {
                board.lock();
            } else {
                board.clearBlanks();
            }

            controller.solve(algorithm);

            return null;
        }
    }

    public void startGA() {
//        new RunAlgorithm(Algorithm.GENETIC_ALGORITHM).execute();
        if (ownPuzzle) {
            board.lock();
        } else {
            board.clearBlanks();
        }

        controller.solve(Algorithm.GENETIC_ALGORITHM);
    }

    public void setStatusText(String text) {
        lblStatus.setText(text);
    }

    public boolean nakedSingleSelected() {
        return solveGA.nakedSingleSelected();
    }

    public boolean hiddenSingleSelected() {
        return solveGA.hiddenSingleSelected();
    }

    public XYSeries getSeries() {
        return controller.getSeries();
    }

    public void showBestTimes() {
        bestTimes.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        puzzleFileChooser = new javax.swing.JFileChooser();
        imageFileChooser = new javax.swing.JFileChooser();
        popDifficulty = new rc.sudokugenius.views.components.JPopupMenu();
        mnuVeryEasy = new rc.sudokugenius.views.components.JMenuItem();
        mnuEasy = new rc.sudokugenius.views.components.JMenuItem();
        mnuNormal = new rc.sudokugenius.views.components.JMenuItem();
        mnuHard = new rc.sudokugenius.views.components.JMenuItem();
        mnuVeryHard = new rc.sudokugenius.views.components.JMenuItem();
        jMenu10 = new rc.sudokugenius.views.components.JMenu();
        mnuShapeTwoTwo = new rc.sudokugenius.views.components.JMenuItem();
        mnuShapeHeart = new rc.sudokugenius.views.components.JMenuItem();
        popOptions = new rc.sudokugenius.views.components.JPopupMenu();
        mnuCopyImage = new rc.sudokugenius.views.components.JMenuItem();
        mnuBestTimes = new rc.sudokugenius.views.components.JMenuItem();
        jMenu7 = new rc.sudokugenius.views.components.JMenu();
        chkAlwaysShowCandidates = new rc.sudokugenius.views.components.JCheckBoxMenuItem();
        chkShowCandidatesOnRollover = new rc.sudokugenius.views.components.JCheckBoxMenuItem();
        popSave = new rc.sudokugenius.views.components.JPopupMenu();
        mnuSavePuzzle = new rc.sudokugenius.views.components.JMenuItem();
        mnuSaveImagePlain = new rc.sudokugenius.views.components.JMenuItem();
        mnuSaveImageWithCandidates = new rc.sudokugenius.views.components.JMenuItem();
        popSolve = new rc.sudokugenius.views.components.JPopupMenu();
        mnuSolveOriginal = new rc.sudokugenius.views.components.JMenuItem();
        mnuSolveGA = new rc.sudokugenius.views.components.JMenuItem();
        background1 = new rc.sudokugenius.views.components.Background();
        jStatusBar1 = new rc.sudokugenius.views.components.JStatusBar();
        lblStatus = new rc.swing.JLabel();
        lblDifficulty = new rc.swing.JLabel();
        lblTimer = new rc.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        pnlLeft = new javax.swing.JPanel();
        cmdNew = new rc.sudokugenius.views.components.ChalkButton();
        cmdLoad = new rc.sudokugenius.views.components.ChalkButton();
        cmdSave = new rc.sudokugenius.views.components.ChalkButton();
        cmdHint = new rc.sudokugenius.views.components.ChalkButton();
        cmdVerify = new rc.sudokugenius.views.components.ChalkButton();
        cmdOptions = new rc.sudokugenius.views.components.ChalkButton();
        cmdClear = new rc.sudokugenius.views.components.ChalkButton();
        cmdQuit = new rc.sudokugenius.views.components.ChalkButton();
        cmdSolve = new rc.sudokugenius.views.components.ChalkButton();
        cmdRestart = new rc.sudokugenius.views.components.ChalkButton();
        stopwatch = new rc.sudokugenius.views.components.Stopwatch();
        board = new rc.sudokugenius.views.components.Board();

        FormListener formListener = new FormListener();

        mnuVeryEasy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rc/sudokugenius/views/images/bullet_green.png"))); // NOI18N
        mnuVeryEasy.setText("Very Easy");
        mnuVeryEasy.addActionListener(formListener);
        popDifficulty.add(mnuVeryEasy);

        mnuEasy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rc/sudokugenius/views/images/bullet_blue.png"))); // NOI18N
        mnuEasy.setText("Easy");
        mnuEasy.addActionListener(formListener);
        popDifficulty.add(mnuEasy);

        mnuNormal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rc/sudokugenius/views/images/bullet_yellow.png"))); // NOI18N
        mnuNormal.setText("Normal");
        mnuNormal.addActionListener(formListener);
        popDifficulty.add(mnuNormal);

        mnuHard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rc/sudokugenius/views/images/bullet_orange.png"))); // NOI18N
        mnuHard.setText("Hard");
        mnuHard.addActionListener(formListener);
        popDifficulty.add(mnuHard);

        mnuVeryHard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rc/sudokugenius/views/images/bullet_red.png"))); // NOI18N
        mnuVeryHard.setText("Very Hard");
        mnuVeryHard.addActionListener(formListener);
        popDifficulty.add(mnuVeryHard);

        jMenu10.setText("Shape");

        mnuShapeTwoTwo.setText("Two Two");
        mnuShapeTwoTwo.addActionListener(formListener);
        jMenu10.add(mnuShapeTwoTwo);

        mnuShapeHeart.setText("Heart");
        mnuShapeHeart.addActionListener(formListener);
        jMenu10.add(mnuShapeHeart);

        popDifficulty.add(jMenu10);

        mnuCopyImage.setText("Copy as Image");
        mnuCopyImage.addActionListener(formListener);
        popOptions.add(mnuCopyImage);

        mnuBestTimes.setText("Best Times");
        mnuBestTimes.addActionListener(formListener);
        popOptions.add(mnuBestTimes);

        jMenu7.setText("Show Candidates");

        chkAlwaysShowCandidates.setText("Always");
        chkAlwaysShowCandidates.addActionListener(formListener);
        jMenu7.add(chkAlwaysShowCandidates);

        chkShowCandidatesOnRollover.setSelected(true);
        chkShowCandidatesOnRollover.setText("On Rollover");
        chkShowCandidatesOnRollover.addActionListener(formListener);
        jMenu7.add(chkShowCandidatesOnRollover);

        popOptions.add(jMenu7);

        mnuSavePuzzle.setText("Puzzle...");
        mnuSavePuzzle.addActionListener(formListener);
        popSave.add(mnuSavePuzzle);

        mnuSaveImagePlain.setText("Image (Plain)...");
        mnuSaveImagePlain.addActionListener(formListener);
        popSave.add(mnuSaveImagePlain);

        mnuSaveImageWithCandidates.setText("Image (with Candidates)...");
        mnuSaveImageWithCandidates.addActionListener(formListener);
        popSave.add(mnuSaveImageWithCandidates);

        mnuSolveOriginal.setText("Original");
        mnuSolveOriginal.addActionListener(formListener);
        popSolve.add(mnuSolveOriginal);

        mnuSolveGA.setText("Genetic Algorithm");
        mnuSolveGA.addActionListener(formListener);
        popSolve.add(mnuSolveGA);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Sudoku Genius");
        setResizable(false);

        lblStatus.setText("Ready");

        lblDifficulty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblTimer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTimer.setText("00:00");

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jStatusBar1Layout = new javax.swing.GroupLayout(jStatusBar1);
        jStatusBar1.setLayout(jStatusBar1Layout);
        jStatusBar1Layout.setHorizontalGroup(
            jStatusBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jStatusBar1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jStatusBar1Layout.setVerticalGroup(
            jStatusBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator8, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
            .addComponent(jSeparator7, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jStatusBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        pnlLeft.setOpaque(false);

        cmdNew.setText("New");
        cmdNew.addActionListener(formListener);

        cmdLoad.setText("Load");
        cmdLoad.addActionListener(formListener);

        cmdSave.setText("Save");
        cmdSave.addActionListener(formListener);

        cmdHint.setText("Hint");
        cmdHint.addActionListener(formListener);

        cmdVerify.setText("Verify");
        cmdVerify.addActionListener(formListener);

        cmdOptions.setText("Options");
        cmdOptions.addActionListener(formListener);

        cmdClear.setText("Clear");
        cmdClear.addActionListener(formListener);

        cmdQuit.setText("Quit");
        cmdQuit.addActionListener(formListener);

        cmdSolve.setText("Solve");
        cmdSolve.addActionListener(formListener);

        cmdRestart.setText("Restart");
        cmdRestart.addActionListener(formListener);

        javax.swing.GroupLayout pnlLeftLayout = new javax.swing.GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cmdNew, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
            .addComponent(cmdLoad, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
            .addComponent(cmdSave, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
            .addComponent(cmdHint, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
            .addComponent(cmdVerify, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
            .addComponent(cmdOptions, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
            .addComponent(cmdClear, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
            .addComponent(cmdQuit, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
            .addComponent(cmdSolve, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
            .addComponent(cmdRestart, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
        );
        pnlLeftLayout.setVerticalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addComponent(cmdNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdLoad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdHint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdSolve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdVerify, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdRestart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdQuit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jStatusBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE)
            .addGroup(background1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(board, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(245, Short.MAX_VALUE))
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(328, 328, 328)
                .addComponent(stopwatch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(407, Short.MAX_VALUE))
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(stopwatch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(board, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jStatusBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == cmdNew) {
                MainView.this.cmdNewActionPerformed(evt);
            }
            else if (evt.getSource() == cmdLoad) {
                MainView.this.cmdLoadActionPerformed(evt);
            }
            else if (evt.getSource() == cmdSave) {
                MainView.this.cmdSaveActionPerformed(evt);
            }
            else if (evt.getSource() == cmdHint) {
                MainView.this.cmdHintActionPerformed(evt);
            }
            else if (evt.getSource() == cmdVerify) {
                MainView.this.cmdVerifyActionPerformed(evt);
            }
            else if (evt.getSource() == cmdOptions) {
                MainView.this.cmdOptionsActionPerformed(evt);
            }
            else if (evt.getSource() == cmdClear) {
                MainView.this.cmdClearActionPerformed(evt);
            }
            else if (evt.getSource() == cmdQuit) {
                MainView.this.cmdQuitActionPerformed(evt);
            }
            else if (evt.getSource() == cmdSolve) {
                MainView.this.cmdSolveActionPerformed(evt);
            }
            else if (evt.getSource() == cmdRestart) {
                MainView.this.cmdRestartActionPerformed(evt);
            }
            else if (evt.getSource() == mnuVeryEasy) {
                MainView.this.mnuVeryEasyActionPerformed(evt);
            }
            else if (evt.getSource() == mnuEasy) {
                MainView.this.mnuEasyActionPerformed(evt);
            }
            else if (evt.getSource() == mnuNormal) {
                MainView.this.mnuNormalActionPerformed(evt);
            }
            else if (evt.getSource() == mnuHard) {
                MainView.this.mnuHardActionPerformed(evt);
            }
            else if (evt.getSource() == mnuVeryHard) {
                MainView.this.mnuVeryHardActionPerformed(evt);
            }
            else if (evt.getSource() == mnuShapeTwoTwo) {
                MainView.this.mnuShapeTwoTwoActionPerformed(evt);
            }
            else if (evt.getSource() == mnuShapeHeart) {
                MainView.this.mnuShapeHeartActionPerformed(evt);
            }
            else if (evt.getSource() == mnuCopyImage) {
                MainView.this.mnuCopyImageActionPerformed(evt);
            }
            else if (evt.getSource() == mnuBestTimes) {
                MainView.this.mnuBestTimesActionPerformed(evt);
            }
            else if (evt.getSource() == chkAlwaysShowCandidates) {
                MainView.this.chkAlwaysShowCandidatesActionPerformed(evt);
            }
            else if (evt.getSource() == chkShowCandidatesOnRollover) {
                MainView.this.chkShowCandidatesOnRolloverActionPerformed(evt);
            }
            else if (evt.getSource() == mnuSavePuzzle) {
                MainView.this.mnuSavePuzzleActionPerformed(evt);
            }
            else if (evt.getSource() == mnuSaveImagePlain) {
                MainView.this.mnuSaveImagePlainActionPerformed(evt);
            }
            else if (evt.getSource() == mnuSaveImageWithCandidates) {
                MainView.this.mnuSaveImageWithCandidatesActionPerformed(evt);
            }
            else if (evt.getSource() == mnuSolveOriginal) {
                MainView.this.mnuSolveOriginalActionPerformed(evt);
            }
            else if (evt.getSource() == mnuSolveGA) {
                MainView.this.mnuSolveGAActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void mnuSolveOriginalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSolveOriginalActionPerformed
        controller.solveOriginal();
}//GEN-LAST:event_mnuSolveOriginalActionPerformed

    private void mnuVeryEasyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuVeryEasyActionPerformed
        newPuzzle(PuzzleType.RANDOM, Difficulty.VERY_EASY);
        lblDifficulty.setText("Very Easy");
    }//GEN-LAST:event_mnuVeryEasyActionPerformed

    private void mnuEasyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEasyActionPerformed
        newPuzzle(PuzzleType.RANDOM, Difficulty.EASY);
        lblDifficulty.setText("Easy");
    }//GEN-LAST:event_mnuEasyActionPerformed

    private void mnuNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuNormalActionPerformed
        newPuzzle(PuzzleType.RANDOM, Difficulty.NORMAL);
        lblDifficulty.setText("Normal");
    }//GEN-LAST:event_mnuNormalActionPerformed

    private void mnuHardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuHardActionPerformed
        newPuzzle(PuzzleType.RANDOM, Difficulty.HARD);
        lblDifficulty.setText("Hard");
    }//GEN-LAST:event_mnuHardActionPerformed

    private void mnuVeryHardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuVeryHardActionPerformed
        newPuzzle(PuzzleType.RANDOM, Difficulty.VERY_HARD);
        lblDifficulty.setText("Very Hard");
    }//GEN-LAST:event_mnuVeryHardActionPerformed

    private void chkShowCandidatesOnRolloverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkShowCandidatesOnRolloverActionPerformed
        board.enableRollover(chkShowCandidatesOnRollover.isSelected());
    }//GEN-LAST:event_chkShowCandidatesOnRolloverActionPerformed

    private void chkAlwaysShowCandidatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAlwaysShowCandidatesActionPerformed
        board.alwaysShowCandidates(chkAlwaysShowCandidates.isSelected());
        board.enableRollover(!chkAlwaysShowCandidates.isSelected() && chkShowCandidatesOnRollover.isSelected());
        chkShowCandidatesOnRollover.setEnabled(!chkAlwaysShowCandidates.isSelected());
    }//GEN-LAST:event_chkAlwaysShowCandidatesActionPerformed

    private void mnuSaveImagePlainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSaveImagePlainActionPerformed
        boolean showCandidates = chkAlwaysShowCandidates.isSelected();
        chkAlwaysShowCandidates.setSelected(false);
        chkAlwaysShowCandidatesActionPerformed(evt);

        if (imageFileChooser.showSaveDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
            controller.capturePuzzle(imageFileChooser.getSelectedFile());
        }

        if (showCandidates) {
            chkAlwaysShowCandidates.setSelected(showCandidates);
            chkAlwaysShowCandidatesActionPerformed(evt);
        }
    }//GEN-LAST:event_mnuSaveImagePlainActionPerformed

    private void mnuSaveImageWithCandidatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSaveImageWithCandidatesActionPerformed
        boolean showCandidates = chkAlwaysShowCandidates.isSelected();
        chkAlwaysShowCandidates.setSelected(true);
        chkAlwaysShowCandidatesActionPerformed(evt);

        if (imageFileChooser.showSaveDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
            controller.capturePuzzle(imageFileChooser.getSelectedFile());
        }

        if (!showCandidates) {
            chkAlwaysShowCandidates.setSelected(showCandidates);
            chkAlwaysShowCandidatesActionPerformed(evt);
        }
    }//GEN-LAST:event_mnuSaveImageWithCandidatesActionPerformed

    private void mnuCopyImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCopyImageActionPerformed
        controller.copyPuzzleAsImage();
    }//GEN-LAST:event_mnuCopyImageActionPerformed

    private void mnuShapeTwoTwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuShapeTwoTwoActionPerformed
        newPuzzle(PuzzleType.TWOTWO, 0);
    }//GEN-LAST:event_mnuShapeTwoTwoActionPerformed

    private void mnuShapeHeartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuShapeHeartActionPerformed
        newPuzzle(PuzzleType.HEART, 0);
    }//GEN-LAST:event_mnuShapeHeartActionPerformed

    private void cmdLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLoadActionPerformed
        if (puzzleFileChooser.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
            controller.loadPuzzle(puzzleFileChooser.getSelectedFile());

            savedFile = puzzleFileChooser.getSelectedFile();
        }
    }//GEN-LAST:event_cmdLoadActionPerformed

    private void cmdSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaveActionPerformed
        popSave.show(pnlLeft, (int) (cmdSave.getX() + cmdSave.getWidth() * .7), cmdSave.getY() + (cmdSave.getHeight() / 2));
    }//GEN-LAST:event_cmdSaveActionPerformed

    private void cmdNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNewActionPerformed
        popDifficulty.show(cmdNew, (int) (cmdNew.getX() + cmdNew.getWidth() * .7), cmdNew.getY() + (cmdNew.getHeight() / 2));
    }//GEN-LAST:event_cmdNewActionPerformed

    private void cmdHintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdHintActionPerformed
        controller.showHint();
    }//GEN-LAST:event_cmdHintActionPerformed

    private void cmdVerifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdVerifyActionPerformed
        controller.verifyPuzzle();
    }//GEN-LAST:event_cmdVerifyActionPerformed

    private void cmdClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdClearActionPerformed
        board.clear();
        stopwatch.reset();
        ownPuzzle = true;
        UiUtil.clear(lblDifficulty);
    }//GEN-LAST:event_cmdClearActionPerformed

    private void cmdQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdQuitActionPerformed
        quitApp();
    }//GEN-LAST:event_cmdQuitActionPerformed

    private void cmdOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOptionsActionPerformed
        popOptions.show(pnlLeft, (int) (cmdOptions.getX() + cmdOptions.getWidth() * .7), cmdOptions.getY() + (cmdOptions.getHeight() / 2));
    }//GEN-LAST:event_cmdOptionsActionPerformed

    private void mnuBestTimesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuBestTimesActionPerformed
        showBestTimes();
    }//GEN-LAST:event_mnuBestTimesActionPerformed

    private void mnuSavePuzzleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSavePuzzleActionPerformed
        if (savedFile != null) {
            controller.savePuzzle(savedFile, false);
        } else if (puzzleFileChooser.showSaveDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = puzzleFileChooser.getSelectedFile();
            controller.savePuzzle(selectedFile, true);
            savedFile = selectedFile;
        }
    }//GEN-LAST:event_mnuSavePuzzleActionPerformed

    private void cmdSolveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSolveActionPerformed
        popSolve.show(pnlLeft, (int) (cmdSolve.getX() + cmdSolve.getWidth() * .7), cmdSolve.getY() + (cmdSolve.getHeight() / 2));
    }//GEN-LAST:event_cmdSolveActionPerformed

    private void mnuSolveGAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSolveGAActionPerformed
        solveGA.setVisible(true);
    }//GEN-LAST:event_mnuSolveGAActionPerformed

    private void cmdRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRestartActionPerformed
        board.clearBlanks();
        stopwatch.reset();
        stopwatch.start();
        solveGA.clearChart();
        solveGA.clearTime();
    }//GEN-LAST:event_cmdRestartActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rc.sudokugenius.views.components.Background background1;
    private rc.sudokugenius.views.components.Board board;
    private rc.sudokugenius.views.components.JCheckBoxMenuItem chkAlwaysShowCandidates;
    private rc.sudokugenius.views.components.JCheckBoxMenuItem chkShowCandidatesOnRollover;
    private rc.sudokugenius.views.components.ChalkButton cmdClear;
    private rc.sudokugenius.views.components.ChalkButton cmdHint;
    private rc.sudokugenius.views.components.ChalkButton cmdLoad;
    private rc.sudokugenius.views.components.ChalkButton cmdNew;
    private rc.sudokugenius.views.components.ChalkButton cmdOptions;
    private rc.sudokugenius.views.components.ChalkButton cmdQuit;
    private rc.sudokugenius.views.components.ChalkButton cmdRestart;
    private rc.sudokugenius.views.components.ChalkButton cmdSave;
    private rc.sudokugenius.views.components.ChalkButton cmdSolve;
    private rc.sudokugenius.views.components.ChalkButton cmdVerify;
    private javax.swing.JFileChooser imageFileChooser;
    private rc.sudokugenius.views.components.JMenu jMenu10;
    private rc.sudokugenius.views.components.JMenu jMenu7;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private rc.sudokugenius.views.components.JStatusBar jStatusBar1;
    private rc.swing.JLabel lblDifficulty;
    private rc.swing.JLabel lblStatus;
    private rc.swing.JLabel lblTimer;
    private rc.sudokugenius.views.components.JMenuItem mnuBestTimes;
    private rc.sudokugenius.views.components.JMenuItem mnuCopyImage;
    private rc.sudokugenius.views.components.JMenuItem mnuEasy;
    private rc.sudokugenius.views.components.JMenuItem mnuHard;
    private rc.sudokugenius.views.components.JMenuItem mnuNormal;
    private rc.sudokugenius.views.components.JMenuItem mnuSaveImagePlain;
    private rc.sudokugenius.views.components.JMenuItem mnuSaveImageWithCandidates;
    private rc.sudokugenius.views.components.JMenuItem mnuSavePuzzle;
    private rc.sudokugenius.views.components.JMenuItem mnuShapeHeart;
    private rc.sudokugenius.views.components.JMenuItem mnuShapeTwoTwo;
    private rc.sudokugenius.views.components.JMenuItem mnuSolveGA;
    private rc.sudokugenius.views.components.JMenuItem mnuSolveOriginal;
    private rc.sudokugenius.views.components.JMenuItem mnuVeryEasy;
    private rc.sudokugenius.views.components.JMenuItem mnuVeryHard;
    private javax.swing.JPanel pnlLeft;
    private rc.sudokugenius.views.components.JPopupMenu popDifficulty;
    private rc.sudokugenius.views.components.JPopupMenu popOptions;
    private rc.sudokugenius.views.components.JPopupMenu popSave;
    private rc.sudokugenius.views.components.JPopupMenu popSolve;
    private javax.swing.JFileChooser puzzleFileChooser;
    private rc.sudokugenius.views.components.Stopwatch stopwatch;
    // End of variables declaration//GEN-END:variables
}
