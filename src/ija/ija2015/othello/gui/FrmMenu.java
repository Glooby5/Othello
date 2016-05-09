package ija.ija2015.othello.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
    private static int HEIGHT = 500;

    // Resource images
    private BufferedImage Logo;
    private BufferedImage MenuImg;
    private BufferedImage FrameIcon;

    // Panels
    private JPanel MainPanel;
    private ImagePanel IPMenuImage;
    private ImagePanel IPLogoImage;

    // Labels
    private JLabel LMenu;
    private JLabel LPlayer1Type;
    private JLabel LPlayer2Type;
    private JLabel LFreeze;
    private JLabel LFreezeTimer;
    private JLabel LFreezeInterval;
    private JLabel LFreezeCount;
    private JLabel LBoardSize;

    // Settings
    private JSpinner SBoardSize;
    private JSpinner SPlayer1Type;
    private JSpinner SPlayer2Type;
    private JSpinner SFreezeTimer;
    private JSpinner SFreezeInterval;
    private JSpinner SFreezeCount;

    private JCheckBox ChbFreeze;

    // Buttons
    private JButton BtnRunGame;
    private JButton BtnLoadGame;

    public FrmMenu() {
        loadImages();
        initFrame();
        initPanel();
        initComponents();
    }


    public JCheckBox getChbFreeze() {
        return ChbFreeze;
    }

    public JSpinner getSFreezeCount() {
        return SFreezeCount;
    }

    public JSpinner getSFreezeTimer() {
        return SFreezeTimer;
    }

    public JSpinner getSFreezeInterval() {
        return SFreezeInterval;
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
        setTitle("OTHELLO - MENU");
        setIconImage(FrameIcon);
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
        IPLogoImage.setBounds(0, 145-IPLogoImage.getPreferredSize().height/2,
                IPLogoImage.getPreferredSize().width,
                IPLogoImage.getPreferredSize().height);
        MainPanel.add(IPLogoImage);

        LBoardSize = new JLabel();
        LBoardSize.setFont(new java.awt.Font(DefaultFont, 0, 24));
        LBoardSize.setText("Rozměr");
        LBoardSize.setBounds(20, HEIGHT - 240,
                LBoardSize.getPreferredSize().width,
                LBoardSize.getPreferredSize().height);
        MainPanel.add(LBoardSize);

        SBoardSize = new JSpinner();
        SBoardSize.setPreferredSize(new Dimension(100, 25));
        SBoardSize.setModel(new javax.swing.SpinnerListModel(BoardTypes));
        SBoardSize.setValue(BoardTypes[1]);
        SBoardSize.setBounds(WIDTH - SBoardSize.getPreferredSize().width - 20, HEIGHT - 234,
                SBoardSize.getPreferredSize().width,
                SBoardSize.getPreferredSize().height);
        MainPanel.add(SBoardSize);

        LPlayer1Type = new JLabel();
        LPlayer1Type.setFont(new java.awt.Font(DefaultFont, 0, 24));
        LPlayer1Type.setText("Bílý hráč");
        LPlayer1Type.setBounds(20, HEIGHT - 200,
                LPlayer1Type.getPreferredSize().width,
                LPlayer1Type.getPreferredSize().height);
        MainPanel.add(LPlayer1Type);

        SPlayer1Type = new JSpinner();
        SPlayer1Type.setModel(new javax.swing.SpinnerListModel(PlayerTypes));
        SPlayer1Type.setPreferredSize(new Dimension(100, 25));
        SPlayer1Type.setBounds(WIDTH - SPlayer1Type.getPreferredSize().width - 20, HEIGHT - 194,
                SPlayer1Type.getPreferredSize().width,
                SPlayer1Type.getPreferredSize().height);
        MainPanel.add(SPlayer1Type);

        LPlayer2Type = new JLabel();
        LPlayer2Type.setFont(new java.awt.Font(DefaultFont, 0, 24));
        LPlayer2Type.setText("Černý hráč");
        LPlayer2Type.setBounds(20, HEIGHT - 160,
                LPlayer2Type.getPreferredSize().width,
                LPlayer2Type.getPreferredSize().height);
        MainPanel.add(LPlayer2Type);

        SPlayer2Type = new JSpinner();
        SPlayer2Type.setPreferredSize(new Dimension(100, 25));
        SPlayer2Type.setModel(new javax.swing.SpinnerListModel(PlayerTypes));
        SPlayer2Type.setBounds(WIDTH - SPlayer2Type.getPreferredSize().width - 20, HEIGHT - 154,
                SPlayer2Type.getPreferredSize().width,
                SPlayer2Type.getPreferredSize().height);
        MainPanel.add(SPlayer2Type);

        LFreeze = new JLabel();
        LFreeze.setFont(new java.awt.Font(DefaultFont, 0, 24));
        LFreeze.setText("Zamrzání");
        LFreeze.setBounds(20, HEIGHT - 120,
                LFreeze.getPreferredSize().width,
                LFreeze.getPreferredSize().height);
        MainPanel.add(LFreeze);

        SFreezeTimer = new JSpinner();
        SFreezeTimer.setPreferredSize(new Dimension(50, 25));
        SFreezeTimer.setModel(new SpinnerNumberModel(5, 0, 30, 1));
        SFreezeTimer.setEnabled(false);
        SFreezeTimer.setBounds(WIDTH - SFreezeTimer.getPreferredSize().width - 20, HEIGHT - 114,
                SFreezeTimer.getPreferredSize().width,
                SFreezeTimer.getPreferredSize().height);
        MainPanel.add(SFreezeTimer);

        LFreezeTimer = new JLabel();
        LFreezeTimer.setFont(new java.awt.Font(DefaultFont, 0, 12));
        LFreezeTimer.setText("opakování po");
        LFreezeTimer.setBounds(WIDTH - LFreezeTimer.getPreferredSize().width - SFreezeTimer.getPreferredSize().width - 25, HEIGHT - 110,
                LFreezeTimer.getPreferredSize().width,
                LFreezeTimer.getPreferredSize().height);
        MainPanel.add(LFreezeTimer);

        SFreezeInterval = new JSpinner();
        SFreezeInterval.setPreferredSize(new Dimension(50, 25));
        SFreezeInterval.setEnabled(false);
        SFreezeInterval.setModel(new SpinnerNumberModel(5, 0, 30, 1));
        SFreezeInterval.setBounds(WIDTH - SFreezeInterval.getPreferredSize().width - 20, HEIGHT - 74,
                SFreezeInterval.getPreferredSize().width,
                SFreezeInterval.getPreferredSize().height);
        MainPanel.add(SFreezeInterval);

        LFreezeInterval = new JLabel();
        LFreezeInterval.setFont(new java.awt.Font(DefaultFont, 0, 12));
        LFreezeInterval.setText("doba trvání");
        LFreezeInterval.setBounds(WIDTH - LFreezeInterval.getPreferredSize().width - SFreezeTimer.getPreferredSize().width - 25, HEIGHT - 70,
                LFreezeInterval.getPreferredSize().width,
                LFreezeInterval.getPreferredSize().height);
        MainPanel.add(LFreezeInterval);
        
        SFreezeCount = new JSpinner();
        SFreezeCount.setPreferredSize(new Dimension(50, 25));
        SFreezeCount.setEnabled(false);
        SFreezeCount.setModel(new SpinnerNumberModel(5, 0, 10, 1));
        SFreezeCount.setBounds(WIDTH - SFreezeCount.getPreferredSize().width - 20, HEIGHT - 34,
                SFreezeCount.getPreferredSize().width,
                SFreezeCount.getPreferredSize().height);
        MainPanel.add(SFreezeCount);

        LFreezeCount = new JLabel();
        LFreezeCount.setFont(new java.awt.Font(DefaultFont, 0, 12));
        LFreezeCount.setText("počet kamenů");
        LFreezeCount.setBounds(WIDTH - LFreezeCount.getPreferredSize().width - SFreezeTimer.getPreferredSize().width - 25, HEIGHT - 30,
                LFreezeCount.getPreferredSize().width,
                LFreezeCount.getPreferredSize().height);
        MainPanel.add(LFreezeCount);

        ChbFreeze = new JCheckBox();
        ChbFreeze.setOpaque(false);
        ChbFreeze.setBounds(0, HEIGHT - 112,
                ChbFreeze.getPreferredSize().width,
                ChbFreeze.getPreferredSize().height);
        ChbFreeze.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    SFreezeTimer.setEnabled(true);
                    SFreezeInterval.setEnabled(true);
                    SFreezeCount.setEnabled(true);
                } else {
                    SFreezeTimer.setEnabled(false);
                    SFreezeInterval.setEnabled(false);
                    SFreezeCount.setEnabled(false);
                }
            }
        });
        MainPanel.add(ChbFreeze);
    }

    private void loadImages() {
        try {
            Logo = ImageIO.read(this.getClass().getResource(ImagesPath + "logo.png"));
            MenuImg = ImageIO.read(this.getClass().getResource(ImagesPath + "menu.png"));
            FrameIcon = ImageIO.read(this.getClass().getResource(ImagesPath + "icon.png"));
        }
        catch(IOException ioe) {
            System.out.println("Chyba při načítání obrázků z resources.");
            ioe.printStackTrace();
        }
    }
}
