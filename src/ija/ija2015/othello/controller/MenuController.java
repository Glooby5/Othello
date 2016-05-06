package ija.ija2015.othello.controller;

import ija.ija2015.othello.gui.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ija.ija2015.othello.controller.GameController.PLAYER_ALG2;

/**
 * Created by XZEMAN53
 */
public class MenuController {

    private FrmMenu Frame;

    private JSpinner SPlayer1Type;
    private JSpinner SPlayer2Type;
    private JSpinner SBoardSize;
    private JButton BtnRunGame;
    private JButton BtnLoadGame;

    public MenuController()
    {
        getComponents();
        initButtons();
    }

    public int getBoardSize() {
        return getBoardType(SBoardSize.getValue().toString());
    }

    public int getPlayer1Type() {
        return getPlayerType(SPlayer1Type.getValue().toString());
    }

    public int getPlayer2Type() {
        return getPlayerType(SPlayer2Type.getValue().toString());
    }

    public void Show()
    {
        Frame.setVisible(true);
    }

    private void getComponents() {
        Frame = new FrmMenu();

        SPlayer1Type = Frame.getSPlayer1Type();
        SPlayer2Type = Frame.getSPlayer2Type();
        SBoardSize = Frame.getSBoardSize();

        BtnRunGame = Frame.getBtnRunGame();
        BtnLoadGame = Frame.getBtnLoadGame();
    }

    private int getPlayerType(String value) {
        switch (value) {
            case "Člověk":
                return GameController.PLAYER_USER;
            case "Algoritmus 1":
                return GameController.PLAYER_ALG1;
            case "Algoritmus 2":
                return GameController.PLAYER_ALG2;
            default:
                return -1;
        }
    }

    private int getBoardType(String value) {
        switch (value) {
            case "6x6":
                return 6;
            case "8x8":
                return 8;
            case "10x10":
                return 10;
            case "12x12":
                return 12;
            default:
                return -1;
        }
    }

    private void initButtons()
    {
        if(BtnRunGame instanceof JButton){
            BtnRunGame.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    /*todo*/
                }
            });
        }

        if(BtnLoadGame instanceof JButton) {
            BtnLoadGame.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    /*todo*/
                }
            });
        }
    }
}
