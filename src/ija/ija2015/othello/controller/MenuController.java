package ija.ija2015.othello.controller;

import com.sun.media.jfxmediaimpl.MediaDisposer;
import ija.ija2015.othello.gui.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Created by XZEMAN53
 */
public class MenuController implements MediaDisposer.Disposable {

    private FrmMenu Frame;
    private JSpinner SPlayer1Type;
    private JSpinner SPlayer2Type;
    private JSpinner SBoardSize;
    private JButton BtnRunGame;
    private JButton BtnLoadGame;

    private ArrayList<GameController> Games;

    public MenuController()
    {
        getComponents();
        initListeners();
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

        Games = new ArrayList();
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

    private void initListeners()
    {
        if(BtnRunGame instanceof JButton){
            BtnRunGame.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Games.add(new GameController(getBoardSize()));
                    Games.get(Games.size()-1).RunGame(getPlayer1Type(), getPlayer2Type());
                    Games.get(Games.size()-1).Show();
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

        Frame.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    @Override
    public void dispose() {
        for(GameController game : Games)
          game.dispose();
    }
}
