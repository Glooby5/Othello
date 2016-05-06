package ija.ija2015.othello.controller;

import ija.ija2015.othello.board.Board;
import ija.ija2015.othello.game.*;
import ija.ija2015.othello.gui.*;
import ija.ija2015.othello.gui.FrmGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by XZEMAN53
 */
public class GameController {

    public static final int PLAYER_USER = 0;
    public static final int PLAYER_ALG1 = 1;
    public static final int PLAYER_ALG2 = 2;

    private ImagePanel[][] Fields;
    private JPanel BoardPanel;
    private JButton BtnClose;
    private JButton BtnSave;
    private FrmGame Frame;

    private int BoardSize;
    private Game game;

    public GameController(int size)
    {
        BoardSize = size;
        getComponents();
        initButtons();
    }


    public void RunGame(int Player1, int Player2) {
        prepareGame(Player1, Player2);
        drawBoard();
    }

    public void Show()
    {
        Frame.setVisible(true);
    }


    private void getComponents() {
        Frame = new FrmGame(BoardSize);
        BoardPanel = Frame.getBoardPanel();
        BtnClose = Frame.getBtnClose();
        BtnSave = Frame.getBtnSave();
        Fields = Frame.getFields();
    }

    private void prepareGame(int Player1, int Player2) {
        ReversiRules rules = new ReversiRules(BoardSize);
        Board board = new Board(rules);
        game = new ija.ija2015.othello.game.Game(board);
        addPlayer(Player1, true);
        addPlayer(Player2, false);
    }

    private void addPlayer(int playerType, boolean isWhite) {
        switch (playerType) {
            case PLAYER_USER:
                game.addPlayer(new Player(isWhite, true, ""));
                break;
            case PLAYER_ALG1:
                game.addPlayer(new RandomAI(isWhite));
                break;
            case PLAYER_ALG2:
                game.addPlayer(new CostAI(isWhite));
                break;
        }
    }

    private void initButtons()
    {
        if(BtnClose instanceof JButton){
            BtnClose.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Frame.dispose();
                }
            });
        }

        if(BtnSave instanceof JButton) {
            BtnClose.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    GameSaver saver = new GameSaver(game);
                    try  { saver.Save("game.game"); }
                    catch (Exception ex) { }
                }
            });
        }
    }

    private void setFieldEvent(final int row, final int col) {
        Fields[row][col].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (game.isEnd())
                    return;

                if (game.currentPlayer().PossibleTurns().size() == 0) {
                    System.out.println("PASS jak hovado\n");
                    game.nextPlayer();
                }
                else {
                    game.Place(game.getBoard().getField(col+1, row+1));
                    System.out.println("\n" + game.getBoard());
                    drawBoard();
                }
                if(!game.currentPlayer().isHuman()) {
                    game.Place(((AI
                            )game.currentPlayer()).Turn());
                    System.out.println("\n" + game.getBoard());
                    drawBoard();
                }
            }
        });
    }

    private void drawBoard() {
        Board board = game.getBoard();
        BoardPanel.removeAll();

        for (int[] turn : game.currentPlayer().PossibleTurns())
            Frame.drawDisk(turn[1]-1, turn[0]-1, Frame.getHelpField());

        for (int col = 0; col < board.getSize(); col++)
        {
            for (int row = 0; row < board.getSize(); row++)
            {
                if(!board.getField(col+1, row+1).isEmpty()) {
                    if (board.getField(col + 1, row + 1).getDisk().isWhite())
                        Frame.drawDisk(row, col, Frame.getWhiteDisk());
                    else
                        Frame.drawDisk(row, col, Frame.getBlackDisk());
                } else
                    Frame.drawDisk(row, col, Frame.getEmptyField());
                setFieldEvent(row, col);
            }
        }

        Frame.setScore(game.getScore()[0], game.getScore()[1]);
        Frame.changePlayer(game.currentPlayer().isWhite() ? Frame.getWhiteDisk(): Frame.getBlackDisk());
    }
}
