package ija.ija2015.othello.controller;

import ija.ija2015.othello.gui.*;
import javax.swing.*;

/**
 * Created by Ondřej on 4. 5. 2016.
 */
public class MainController {

    private JFrame menuFrame;
    private JPanel panel;

    public MainController()
    {
        panel = new MenuPanel();
        menuFrame = new JFrame("Othello - Menu");
        menuFrame.setSize(300,400);
        menuFrame.setContentPane(panel);
    }

    public void Show()
    {
        menuFrame.setVisible(true);
    }
}
