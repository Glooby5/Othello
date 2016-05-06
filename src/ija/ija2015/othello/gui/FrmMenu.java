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
public class FrmMenu extends JFrame {

    // Constants
    private static String DefaultFont = "Segoe UI Light";
    private static String ImagesPath = "resources/";
    private static final String[] PlayerTypes = {"Člověk", "Algoritmus 1", "Algoritmus 2"};
    private static final String[] BoardTypes = {"6x6", "8x8", "10x10", "12x12"};
    private static int WIDTH = 300;
    private static int HEIGHT = 400;

    // Resource images
    private BufferedImage Logo;
    private BufferedImage MenuImg;

    // Panels
    private JPanel MainPanel;
    private ImagePanel IPMenuImage;
    private ImagePanel IPLogoImage;

    // Labels
    private JLabel LMenu;
    private JLabel LPlayer1Type;
    private JLabel LPlayer2Type;
    private JLabel LBoardSize;

    // Settings
    private JSpinner SBoardSize;
    private JSpinner SPlayer1Type;
    private JSpinner SPlayer2Type;

    // Buttons
    private JButton BtnRunGame;
    private JButton BtnLoadGame;

    public FrmMenu() {
        loadImages();
        initFrame();
        initPanel();
        initComponents();
    }

    public JSpinner getSBoardSize() {
        return SBoardSize;
    }

    public JSpinner getSPlayer1Type() {
        return SPlayer1Type;
    }

    public JSpinner getSPlayer2Type() {
        return SPlayer2Type;
    }

    public JButton getBtnRunGame() {
        return BtnRunGame;
    }

    public JButton getBtnLoadGame() {
        return BtnLoadGame;
    }

    private void initFrame() {
        Dimension dim = getDefaultToolkit().getScreenSize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setSize(WIDTH + 5,HEIGHT + 29);
        setLocation(dim.width/2-this.getSize().width/2,
                dim.height/2-this.getSize().height/2);
    }

    private void initPanel() {
        MainPanel = new JPanel(null);
        MainPanel.setBackground(new java.awt.Color(137, 186, 59));
        add(MainPanel);
    }

    private void initComponents() {

        IPMenuImage = new ImagePanel(MenuImg);
        IPMenuImage.setBounds(5, 20,
                IPMenuImage.getPreferredSize().width,
                IPMenuImage.getPreferredSize().height);
        MainPanel.add(IPMenuImage);

        LMenu = new JLabel();
        LMenu.setFont(new java.awt.Font(DefaultFont, 0, 24));
        LMenu.setText("Menu");

        LMenu.setBounds(30,8,LMenu.getPreferredSize().width, LMenu.getPreferredSize().height);
        MainPanel.add(LMenu);

        BtnRunGame = new JButton();
        BtnRunGame.setText("Hrát");
        BtnRunGame.setBounds(WIDTH-BtnRunGame.getPreferredSize().width-10, 12,
                                BtnRunGame.getPreferredSize().width,
                                BtnRunGame.getPreferredSize().height);
        MainPanel.add(BtnRunGame);

        BtnLoadGame = new JButton();
        BtnLoadGame.setText("Nahrát");
        BtnLoadGame.setBounds(WIDTH-BtnLoadGame.getPreferredSize().width*2-5, 12,
                BtnLoadGame.getPreferredSize().width,
                BtnLoadGame.getPreferredSize().height);
        MainPanel.add(BtnLoadGame);


        IPLogoImage = new ImagePanel(Logo);
        IPLogoImage.setBounds(WIDTH/2-IPLogoImage.getPreferredSize().width/2, 150-IPLogoImage.getPreferredSize().width/2,
                IPLogoImage.getPreferredSize().width,
                IPLogoImage.getPreferredSize().height);
        MainPanel.add(IPLogoImage);

        LBoardSize = new JLabel();
        LBoardSize.setFont(new java.awt.Font(DefaultFont, 0, 24));
        LBoardSize.setText("Rozměr");
        LBoardSize.setBounds(20, HEIGHT - 120,
                LBoardSize.getPreferredSize().width,
                LBoardSize.getPreferredSize().height);
        MainPanel.add(LBoardSize);

        SBoardSize = new JSpinner();
        SBoardSize.setPreferredSize(new Dimension(100, 25));
        SBoardSize.setModel(new javax.swing.SpinnerListModel(BoardTypes));
        SBoardSize.setBounds(WIDTH - SBoardSize.getPreferredSize().width - 20, HEIGHT - 114,
                SBoardSize.getPreferredSize().width,
                SBoardSize.getPreferredSize().height);
        MainPanel.add(SBoardSize);

        LPlayer1Type = new JLabel();
        LPlayer1Type.setFont(new java.awt.Font(DefaultFont, 0, 24));
        LPlayer1Type.setText("1. hráč");
        LPlayer1Type.setBounds(20, HEIGHT - 80,
                LPlayer1Type.getPreferredSize().width,
                LPlayer1Type.getPreferredSize().height);
        MainPanel.add(LPlayer1Type);

        SPlayer1Type = new JSpinner();
        SPlayer1Type.setModel(new javax.swing.SpinnerListModel(PlayerTypes));
        SPlayer1Type.setPreferredSize(new Dimension(100, 25));
        SPlayer1Type.setBounds(WIDTH - SPlayer1Type.getPreferredSize().width - 20, HEIGHT - 74,
                SPlayer1Type.getPreferredSize().width,
                SPlayer1Type.getPreferredSize().height);
        MainPanel.add(SPlayer1Type);

        LPlayer2Type = new JLabel();
        LPlayer2Type.setFont(new java.awt.Font(DefaultFont, 0, 24));
        LPlayer2Type.setText("2. hráč");
        LPlayer2Type.setBounds(20, HEIGHT - 40,
                LPlayer2Type.getPreferredSize().width,
                LPlayer2Type.getPreferredSize().height);
        MainPanel.add(LPlayer2Type);

        SPlayer2Type = new JSpinner();
        SPlayer2Type.setPreferredSize(new Dimension(100, 25));
        SPlayer2Type.setModel(new javax.swing.SpinnerListModel(PlayerTypes));
        SPlayer2Type.setBounds(WIDTH - SPlayer2Type.getPreferredSize().width - 20, HEIGHT - 34,
                SPlayer2Type.getPreferredSize().width,
                SPlayer2Type.getPreferredSize().height);
        MainPanel.add(SPlayer2Type);
    }

    private void loadImages() {
        try {
            Logo = ImageIO.read(this.getClass().getResource(ImagesPath + "logo.png"));
            MenuImg = ImageIO.read(this.getClass().getResource(ImagesPath + "menu.png"));
        }
        catch(IOException ioe) {
            System.out.println("Chyba při načítání obrázků z resources.");
            ioe.printStackTrace();
        }
    }
}
