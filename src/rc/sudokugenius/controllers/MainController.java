package rc.sudokugenius.controllers;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.jfree.data.xy.XYSeries;
import rc.collections.ArrayList;
import rc.mvc.Controller;
import rc.sudokugenius.algorithm.BruteForce;
import rc.sudokugenius.algorithm.genetic.GeneticAlgorithm;
import rc.sudokugenius.clipboard.TransferableImage;
import rc.sudokugenius.global.Global;
import rc.sudokugenius.sudoku.Algorithm;
import rc.sudokugenius.sudoku.operations.BoardVerifier;
import rc.sudokugenius.sudoku.technique.NakedSingle;
import rc.sudokugenius.sudoku.operations.PuzzleMaker;
import rc.sudokugenius.sudoku.operations.PuzzleAnalyzer;
import rc.sudokugenius.sudoku.operations.PuzzleGenerator;
import rc.sudokugenius.sudoku.technique.ColumnHiddenSingle;
import rc.sudokugenius.sudoku.technique.RegionHiddenSingle;
import rc.sudokugenius.sudoku.technique.RowHiddenSingle;
import rc.sudokugenius.views.MainView;
import rc.sudokugenius.views.components.Board;
import rc.sudokugenius.views.components.ChalkCell;
import static rc.sudokugenius.global.Global.PROPERTIES;

public class MainController extends Controller<MainView> implements ClipboardOwner {

    private int[][] solution;
    private int[][] puzzle;
    private int currentDifficulty = 0;
    private PuzzleAnalyzer puzzleAnalyzer;
    private GeneticAlgorithm geneticAlgorithm;
    private StandardPBEStringEncryptor encryptor;

    public MainController() {
        view = new MainView(this);
        puzzleAnalyzer = new PuzzleAnalyzer();
        PROPERTIES.encryptValues(Global.ENCRYPT_VALUES);
        PROPERTIES.setPassword(Global.PASSWORD);
        encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(Global.PASSWORD);
    }

    public void generatePuzzle(int difficulty, int puzzleType) {
        solution = PuzzleGenerator.generate();
        puzzle = PuzzleMaker.makePuzzle(solution, difficulty, puzzleType);
        currentDifficulty = difficulty;

        view.getBoard().clear();
        view.getBoard().setCellValues(puzzle);
        view.getBoard().lock();

        if (view.getStopwatch().isStarted()) {
            view.getStopwatch().reset();
        }

        view.getStopwatch().start();
    }

    public void savePuzzle(File file, boolean confirm) {
        if (!file.getName().endsWith(".puz")) {
            file = new File(file.getAbsolutePath() + ".puz");
        }

        if (file.exists() && confirm && !view.confirm("Overwrite?")) {
            return;
        }

        StringBuilder data = new StringBuilder().append(view.getBoard().getCellValuesAsString()).
                append(";").
                append(currentDifficulty).
                append(";").
                append(view.getStopwatch().getTotalTime());

        try {
            FileUtils.writeStringToFile(file, encryptor.encrypt(data.toString()));
        } catch (IOException e) {
        }

        view.setStatusText("Puzzle saved");
    }

    public void loadPuzzle(File file) {
        String load = null;

        try {
            load = FileUtils.readFileToString(file);
        } catch (IOException e) {
        }

        StringTokenizer tokenizer = new StringTokenizer(encryptor.decrypt(load), ";");
        String puz = tokenizer.nextToken();
        int difficulty = Integer.parseInt(tokenizer.nextToken());
        String time = tokenizer.nextToken();

        currentDifficulty = difficulty;

        tokenizer = new StringTokenizer(time, ":");
        int hour = Integer.parseInt(tokenizer.nextToken());
        int min = Integer.parseInt(tokenizer.nextToken());
        int sec = Integer.parseInt(tokenizer.nextToken());
        view.getStopwatch().setHour(hour);
        view.getStopwatch().setMin(min);
        view.getStopwatch().setSec(sec);
        view.getStopwatch().start();

        view.getBoard().clear();
        view.getBoard().setCellValues(puz.substring(0, 81));
        view.getBoard().lock();
        view.getBoard().fillInBlanks(puz.substring(81));
    }

    public void capturePuzzle(File file) {
        if (!file.getName().endsWith(".png")) {
            file = new File(file.getAbsolutePath() + ".png");
        }

        if (file.exists() && !view.confirm("Overwrite?")) {
            return;
        }

        Board board = view.getBoard();
        BufferedImage image = new BufferedImage(board.getWidth(), board.getHeight(), BufferedImage.TYPE_INT_RGB);
        board.paint(image.createGraphics());

        try {
            file.createNewFile();
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            file.delete();
        }
    }

    public void copyPuzzleAsImage() {
        Board board = view.getBoard();
        BufferedImage image = new BufferedImage(board.getWidth(), board.getHeight(), BufferedImage.TYPE_INT_RGB);
        board.paint(image.createGraphics());

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new TransferableImage(image), this);

        view.setStatusText("Image copied to clipboard");
    }

    public void solveOriginal() {
        view.getBoard().setCellValues(solution);
    }

    public void solve(int algorithm) {
        int[] chromosome;
        boolean pass;

        puzzle = view.getBoard().getCellValues();
        puzzleAnalyzer.setPuzzle(puzzle);
        puzzleAnalyzer.analyze();

        do {
            pass = view.nakedSingleSelected() ? !NakedSingle.filter(puzzleAnalyzer).isEmpty() : false;

            pass = pass || view.hiddenSingleSelected()
                    ? !RegionHiddenSingle.filter(puzzleAnalyzer).isEmpty()
                    || !RowHiddenSingle.filter(puzzleAnalyzer).isEmpty()
                    || !ColumnHiddenSingle.filter(puzzleAnalyzer).isEmpty()
                    : false;
        } while (pass);

        System.out.println(puzzleAnalyzer.getEmptyCellsNum() + " empty cell(s)");

        if ((chromosome = algorithm == Algorithm.GENETIC_ALGORITHM ? solveGA() : solveBruteForce()) != null) {
            for (int row = 0, chr = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    if (puzzle[row][col] == 0) {
                        puzzle[row][col] = chromosome[chr++];
                    }
                }
            }

            view.getBoard().setCellValues(puzzle);
        }
    }

    private int[] solveGA() {
        geneticAlgorithm = new GeneticAlgorithm().setPopulationSize(view.getPopulationSize()).
                setMaxGenerations(view.getMaxGenerations()).
                setMaxAttempt(-1).
                setCrossoverRate(view.getCrossoverRate()).
                setCrossoverPoints(view.getCrossoverPoints()).
                setMutationRate(view.getMutationRate()).
                setChromosomeLength(puzzleAnalyzer.getEmptyCellsNum()).
                setCandidates(puzzleAnalyzer.getCandidates()).
                setMutableGenes(puzzleAnalyzer.getMutableCells()).
                setMutableMap(puzzleAnalyzer.getMutableMap()).
                setPuzzle(puzzleAnalyzer.getPuzzle());

        return geneticAlgorithm.evolve(view.optimizedMutationSelected());
    }

    private int[] solveBruteForce() {
        return new BruteForce().setCandidates(puzzleAnalyzer.getCandidates()).
                attack(puzzleAnalyzer.getPuzzle());
    }

    public XYSeries getSeries() {
        return geneticAlgorithm.getSeries();
    }

    public void showHint() {
        puzzleAnalyzer.setPuzzle(view.getBoard().getCellValues());
        int[] hint = NakedSingle.getOne(puzzleAnalyzer);
        hint = hint == null ? RegionHiddenSingle.getOne(puzzleAnalyzer) : hint;
        hint = hint == null ? RowHiddenSingle.getOne(puzzleAnalyzer) : hint;
        hint = hint == null ? ColumnHiddenSingle.getOne(puzzleAnalyzer) : hint;

        if (hint != null) {
            int row = hint[0];
            int col = hint[1];

            view.getBoard().getCells()[row][col].requestFocus();
        }

        view.setStatusText(hint == null ? "Hint not available"
                : hint[3] == 0 ? "Naked Single"
                : hint[3] == 1 ? "Row Hidden Single"
                : hint[3] == 2 ? "Column Hidden Single"
                : "Region Hidden Single");
    }

    public void solveAllNakedSingles() {
        puzzleAnalyzer.setPuzzle(view.getBoard().getCellValues());
        ArrayList<int[]> nakedSingles = NakedSingle.filter(puzzleAnalyzer);
        ChalkCell[][] cells = view.getBoard().getCells();

        for (int[] each : nakedSingles) {
            int row = each[0];
            int col = each[1];
            int value = each[2];

            cells[row][col].setValue(value);
        }
    }

    public void solveAllHiddenSingles() {
        puzzleAnalyzer.setPuzzle(view.getBoard().getCellValues());
        ArrayList<int[]> hiddenSingles = RegionHiddenSingle.filter(puzzleAnalyzer);
        hiddenSingles.addAll(RowHiddenSingle.filter(puzzleAnalyzer));
        hiddenSingles.addAll(ColumnHiddenSingle.filter(puzzleAnalyzer));
        ChalkCell[][] cells = view.getBoard().getCells();

        for (int[] each : hiddenSingles) {
            int row = each[0];
            int col = each[1];
            int value = each[2];

            cells[row][col].setValue(value);
        }
    }

    public void verifyPuzzle() {
        Board board = view.getBoard();
        ArrayList<int[]> errorCells = BoardVerifier.verify(board.getCellValues());

        if (errorCells.isEmpty()) {
            view.getStopwatch().stop();
            view.alert("Puzzle complete.");

            String name = view.prompt("Enter your name");

            if (name != null) {
                name = name.trim().
                        replaceAll(",", "").
                        replaceAll(";", "");

                StringBuilder str = new StringBuilder();
                str.append(PROPERTIES.get(currentDifficulty, "")).
                        append(name).
                        append(",").
                        append(view.getStopwatch().getTotalTime()).
                        append(";");

                PROPERTIES.set(currentDifficulty, str.toString());
                PROPERTIES.save();

                view.showBestTimes();
            }
        } else {
            board.setCellError(errorCells);
        }
    }

    public void lostOwnership(Clipboard clipboard, Transferable contents) {
    }
}
