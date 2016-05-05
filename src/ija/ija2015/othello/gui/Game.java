/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.othello.gui;

import ija.ija2015.othello.board.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author Ondřej
 */
public class Game extends javax.swing.JPanel {

    private static String DefaultFont = "Segoe UI";
    private static String ImagesPath = "resources/";

    private BufferedImage Board;
    private BufferedImage WhiteDisk;
    private BufferedImage BlackDisk;
    private BufferedImage EmptyField;
    private BufferedImage HelpField;

    private JPanel StatsPanel;
    private JPanel BoardPanel;
    private JPanel SettingsPanel;
    private ImagePanel[][] Fields;
    private JButton BtnClose;
    private JButton BtnSave;

    private int size;

    public Game(int size) {
        this.size = size;
        loadImages();
        initFields();
        initPanel();
        initStatistics();
        initBoard();
        initSettings();
        //initAllDisks();
       /* jBtnClose.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Do Something Clicked");
            }
        });*/
    }

    public void DrawDisk(int row, int col, BufferedImage image)
    {
        Dimension dsize;
        Insets insets = BoardPanel.getInsets();
        ImagePanel field = Fields[row][col];
        dsize = field.getPreferredSize();
        field = new ImagePanel(image);
        field.setBounds(2 + (row*50) + insets.left, 2 + (col*50) + insets.top, dsize.width, dsize.height);
        Fields[row][col] = field;
        BoardPanel.add(field);
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

    public ImagePanel[][] getFields() {
        return Fields;
    }

    private void initFields() {
        Fields = new ImagePanel[size][size];

        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++)
                Fields[r][c] = new ImagePanel(WhiteDisk);
    }

    private void initPanel() {
        setSize(600, 700);
        setBackground(new java.awt.Color(104, 86, 68));
        setLayout(new BorderLayout());
    }

    private void initStatistics() {
        StatsPanel = new javax.swing.JPanel(new BorderLayout());
        StatsPanel.setBackground(new java.awt.Color(104, 86, 68));
        StatsPanel.setPreferredSize(new Dimension(600, 50));
        StatsPanel.setSize(600, 50);

        this.add(StatsPanel, BorderLayout.NORTH);
    }

    private void initBoard() {
        BoardPanel = new ImagePanel(Board);
        BoardPanel.setLayout(null);
        this.add(BoardPanel);
    }

    private void initSettings() {
        SettingsPanel = new javax.swing.JPanel();
        SettingsPanel.setBackground(new java.awt.Color(104, 86, 68));
        SettingsPanel.setPreferredSize(new Dimension(600, 50));
        SettingsPanel.setSize(600, 50);

        BtnClose = new javax.swing.JButton();
        BtnClose.setText("Ukončit hru");
        BtnSave = new javax.swing.JButton();
        BtnSave.setText("Uložit hru");
        SettingsPanel.add(BtnClose);
        SettingsPanel.add(BtnSave);

        this.add(SettingsPanel, BorderLayout.SOUTH);
    }

    private void initAllDisks() {
        Dimension dsize;
        Insets insets = BoardPanel.getInsets();

        for(int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                ImagePanel field = Fields[r][c];
                dsize = field.getPreferredSize();
                field = new ImagePanel(WhiteDisk);
                field.setBounds(2 + (c*50) + insets.left, 2 + (r*50) + insets.top, dsize.width, dsize.height);
                //BoardPanel.add(field);
            }
        }
    }

    private void loadImages() {
        try {
            Board = ImageIO.read(this.getClass().getResource(ImagesPath + "board.png"));
            WhiteDisk = ImageIO.read(this.getClass().getResource(ImagesPath + "white.png"));
            BlackDisk = ImageIO.read(this.getClass().getResource(ImagesPath + "black.png"));
            EmptyField = ImageIO.read(this.getClass().getResource(ImagesPath + "empty.png"));
            HelpField = ImageIO.read(this.getClass().getResource(ImagesPath + "help.png"));
        }
        catch(IOException ioe) {
            System.out.println("Chyba při načítání obrázků z resources.");
            ioe.printStackTrace();
        }
    }

}
