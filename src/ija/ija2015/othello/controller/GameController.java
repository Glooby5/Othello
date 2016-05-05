package ija.ija2015.othello.controller;

import ija.ija2015.othello.board.Board;
import ija.ija2015.othello.game.*;
import ija.ija2015.othello.gui.*;
import ija.ija2015.othello.gui.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Ondřej on 4. 5. 2016.
 */
public class GameController {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static ija.ija2015.othello.game.Game game;
    private static String ImagesPath = "resources/";

    private JFrame gameFrame;
    private Game panel;
    private int BoardSize;
    private JPanel BoardPanel;
    private JButton BtnClose;
    private JButton BtnSave;
    private ImagePanel[][] Fields;

    public GameController(int size)
    {
        BoardSize = size;

        panel = new Game(BoardSize);

        BoardPanel = panel.getBoardPanel();
        BtnClose = panel.getBtnClose();
        BtnSave = panel.getBtnClose();
        Fields = panel.getFields();
        //game.currentPlayer().putDisk(game.getBoard().getField(inX, inY));
        gameFrame = new JFrame();
        gameFrame.setLayout(new BorderLayout());
        gameFrame.setTitle("Othello - hra");
        gameFrame.setSize(615,739);
        gameFrame.setContentPane(panel);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void RunGame() {
        ReversiRules rules = new ReversiRules(BoardSize);

        Board board = new Board(rules);
        game = new ija.ija2015.othello.game.Game(board);

        Player p1 = new Player(true, true, "Honza");
        //Player p1 = new CostAI(true);
        //Player p2 = new Player(false);
        Player p2 = new RandomAI(false);

        game.addPlayer(p1);
        game.addPlayer(p2);
        DrawBoard();
        while (!game.isEnd())
        {
            System.out.println("SCORE: " + game.getScore()[0] + " : " + game.getScore()[1]);
            System.out.print(game.currentPlayer().getName() + " " + game.currentPlayer() + "\n");

            ArrayList<int[]> possibleTurns = game.currentPlayer().PossibleTurns();

            if (possibleTurns.size() == 0)
            {
                System.out.println("PASS");
                game.nextPlayer();
                continue;
            }

            for (int[] turn : possibleTurns)
            {
                System.out.print(turn[0] + " : " + turn[1] + " | ");
            }

            if (game.currentPlayer().isHuman())
            {
                try{
                br.readLine();}catch(Exception e){}
            }
            else
            {

                ((AI)game.currentPlayer()).Turn();
            }

            System.out.println("\n" + game.getBoard());
            DrawBoard();
            game.nextPlayer();
        }
    }

    public void SetEvents() {
        Board board = game.getBoard();
        for (int row = 0; row < board.getSize(); row++)
        {
            for (int col = 0; col < board.getSize(); col++)
            {
                final int r = row;
                final int c = col;

                Fields[row][col].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        game.currentPlayer().putDisk(game.getBoard().getField(r+1, c+1));
                    }
                });
            }
        }
    }

    public void DrawBoard() {
        BoardPanel.removeAll();
        Board board = game.getBoard();
        for (int row = 0; row < board.getSize(); row++)
        {
            for (int col = 0; col < board.getSize(); col++)
            {
                ImagePanel field = Fields[row][col];
                if(!board.getField(row+1, col+1).isEmpty()) {
                    if (board.getField(row + 1, col + 1).getDisk().isWhite())
                        panel.DrawDisk(row, col, panel.getWhiteDisk());
                    else
                        panel.DrawDisk(row, col, panel.getBlackDisk());
                } else
                    panel.DrawDisk(row, col, panel.getEmptyField());
            }
        }
        panel.updateUI();
    }

    public void Show()
    {
        gameFrame.setVisible(true);
    }
}
