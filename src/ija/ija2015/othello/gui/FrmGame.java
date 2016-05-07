package ija.ija2015.othello.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static java.awt.Toolkit.getDefaultToolkit;

/**
 * @author XZEMAN53
 */
public class FrmGame extends JFrame {

    // difined constants
    private static String DefaultFont = "Segoe UI Light";
    private static String ImagesPath = "resources/";
    private static int FIELDSIZE = 50;

    // Resource images
    private BufferedImage Board;
    private BufferedImage WhiteDisk;
    private BufferedImage BlackDisk;
    private BufferedImage FreezedDisk;
    private BufferedImage EmptyField;
    private BufferedImage HelpField;
    private BufferedImage FrameIcon;

    // From panels
    private JPanel StatsPanel;
    private JPanel ActPlayerPanel;
    private JPanel BoardPanel;
    private JPanel SettingsPanel;

    // Board fields
    private ImagePanel[][] Fields;

    // Settings
    private JButton BtnClose;
    private JButton BtnSave;
    private JButton BtnUndo;

    //Statistics
    private ImagePanel ActualPlayer;
    private ImagePanel WhitePlayer;
    private ImagePanel BlackPlayer;
    private JLabel ScoreP1;
    private JLabel ScoreP2;

    // Board size
    private int BoardSize;

    public FrmGame(int size) {
        this.BoardSize = size;
        loadImages();
        initFields();
        initFrame();
        initStatistics();
        initBoard();
        initSettings();
    }

    public BufferedImage getFreezedDisk() {
        return FreezedDisk;
    }

    public BufferedImage getWhiteDisk() {
        return WhiteDisk;
    }

    public BufferedImage getBlackDisk() {
        return BlackDisk;
    }

    public BufferedImage getEmptyField() {
        return EmptyField;
    }

    public BufferedImage getHelpField() {
        return HelpField;
    }

    public JPanel getBoardPanel() {
        return BoardPanel;
    }

    public JButton getBtnSave() {
        return BtnSave;
    }

    public JButton getBtnClose() {
        return BtnClose;
    }

    public JButton getBtnUndo() {
        return BtnUndo;
    }

    public ImagePanel[][] getFields() {
        return Fields;
    }

    public void drawDisk(int row, int col, BufferedImage image) {
        ImagePanel field = Fields[row][col];
        Insets insets = BoardPanel.getInsets();
        field = new ImagePanel(image);
        field.setBounds(2 + (row*50) + insets.left, 2 + (col*50) + insets.top, field.getPreferredSize().width, field.getPreferredSize().height);
        Fields[row][col] = field;
        BoardPanel.add(field);
        BoardPanel.updateUI();
    }

    public void setScore(int p1, int p2) {
        ScoreP1.setText("" + p1);
        ScoreP2.setText("" + p2);
        StatsPanel.updateUI();
    }

    public void changePlayer(BufferedImage disk) {
        ActPlayerPanel.removeAll();
        ActualPlayer = new ImagePanel(disk);
        ActPlayerPanel.add(ActualPlayer);
        StatsPanel.updateUI();
    }

    private void initFields() {
        Fields = new ImagePanel[BoardSize][BoardSize];

        for (int r = 0; r < BoardSize; r++)
            for (int c = 0; c < BoardSize; c++)
                Fields[r][c] = new ImagePanel(WhiteDisk);
    }

    private void initFrame() {
        Dimension dim = getDefaultToolkit().getScreenSize();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("OTHELLO - HRA");
        setIconImage(FrameIcon);
        setLayout(new BorderLayout());
        setResizable(false);
        setSize(BoardSize*FIELDSIZE + 5,BoardSize*FIELDSIZE + 129);
        setLocation(dim.width/2-this.getSize().width/2,
                    dim.height/2-this.getSize().height/2);
    }

    private void initStatistics() {
        StatsPanel = new JPanel(null);
        StatsPanel.setBackground(new Color(104, 86, 68));
        StatsPanel.setPreferredSize(new Dimension(BoardSize*FIELDSIZE, 50));

        WhitePlayer = new ImagePanel(WhiteDisk);
        StatsPanel.add(WhitePlayer);
        WhitePlayer.setBounds(5, 2,
                WhitePlayer.getPreferredSize().width,
                WhitePlayer.getPreferredSize().height);

        ScoreP1 = new JLabel("0");
        ScoreP1.setPreferredSize(new Dimension(50, 45));
        ScoreP1.setFont(new Font(DefaultFont, 0, 24));
        ScoreP1.setForeground(Color.WHITE);
        StatsPanel.add(ScoreP1);
        ScoreP1.setBounds(60, 6,
                ScoreP1.getPreferredSize().width,
                ScoreP1.getPreferredSize().height);

        ActPlayerPanel = new JPanel(new BorderLayout());
        ActPlayerPanel.setOpaque(false);
        ActPlayerPanel.setPreferredSize(new Dimension(47,47));
        ActPlayerPanel.setBounds((BoardSize*FIELDSIZE / 2) - 22, 2,
                ActPlayerPanel.getPreferredSize().width,
                ActPlayerPanel.getPreferredSize().height);
        StatsPanel.add(ActPlayerPanel);
        ActualPlayer = new ImagePanel(WhiteDisk);
        ActPlayerPanel.add(ActualPlayer);

        ScoreP2 = new JLabel("0");
        ScoreP2.setPreferredSize(new Dimension(50, 45));
        ScoreP2.setHorizontalTextPosition(JLabel.CENTER);
        ScoreP2.setFont(new Font(DefaultFont, 0, 24));
        ScoreP2.setForeground(Color.WHITE);
        StatsPanel.add(ScoreP2);
        ScoreP2.setBounds(BoardSize*FIELDSIZE - 85, 6,
                ScoreP2.getPreferredSize().width,
                ScoreP2.getPreferredSize().height);

        BlackPlayer = new ImagePanel(BlackDisk);
        StatsPanel.add(BlackPlayer);
        BlackPlayer.setBounds(BoardSize*FIELDSIZE - 55, 2,
                BlackPlayer.getPreferredSize().width,
                BlackPlayer.getPreferredSize().height);

        this.add(StatsPanel, BorderLayout.NORTH);
    }

    private void initBoard() {
        BoardPanel = new ImagePanel(Board);
        BoardPanel.setLayout(null);
        this.add(BoardPanel);
    }

    private void initSettings() {
        SettingsPanel = new JPanel(null);
        SettingsPanel.setBackground(new java.awt.Color(104, 86, 68));
        SettingsPanel.setPreferredSize(new Dimension(BoardSize*FIELDSIZE, 50));

        BtnUndo = new JButton("Zpět");
        SettingsPanel.add(BtnUndo);
        BtnUndo.setBounds(5, 12,
                BtnUndo.getPreferredSize().width,
                BtnUndo.getPreferredSize().height);

        BtnClose = new JButton("Konec");
        SettingsPanel.add(BtnClose);
        BtnClose.setBounds(BoardSize*FIELDSIZE - BtnClose.getPreferredSize().width-10, 12,
                BtnClose.getPreferredSize().width,
                BtnClose.getPreferredSize().height);

        BtnSave = new JButton("Ulož");
        SettingsPanel.add(BtnSave);
        BtnSave.setBounds(BoardSize*FIELDSIZE - BtnClose.getPreferredSize().width - BtnSave.getPreferredSize().width - 15, 12,
                BtnSave.getPreferredSize().width,
                BtnSave.getPreferredSize().height);

        this.add(SettingsPanel, BorderLayout.SOUTH);
    }

    private void loadImages() {
        try {
            Board = ImageIO.read(this.getClass().getResource(ImagesPath + "board.png"));
            WhiteDisk = ImageIO.read(this.getClass().getResource(ImagesPath + "white.png"));
            BlackDisk = ImageIO.read(this.getClass().getResource(ImagesPath + "black.png"));
            FreezedDisk = ImageIO.read(this.getClass().getResource(ImagesPath + "freeze.png"));
            EmptyField = ImageIO.read(this.getClass().getResource(ImagesPath + "empty.png"));
            HelpField = ImageIO.read(this.getClass().getResource(ImagesPath + "help.png"));
            FrameIcon = ImageIO.read(this.getClass().getResource(ImagesPath + "icon.png"));
        }
        catch(IOException ioe) {
            System.out.println("Chyba při načítání obrázků z resources.");
            ioe.printStackTrace();
        }
    }

}
