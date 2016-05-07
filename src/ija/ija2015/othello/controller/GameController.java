package ija.ija2015.othello.controller;


import ija.ija2015.othello.board.Board;
import ija.ija2015.othello.game.*;
import ija.ija2015.othello.gui.*;
import ija.ija2015.othello.gui.FrmGame;

import com.sun.media.jfxmediaimpl.MediaDisposer;
import javax.swing.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by XZEMAN53
 */
public class GameController implements MediaDisposer.Disposable {

    public static final int PLAYER_USER = 0;
    public static final int PLAYER_ALG1 = 1;
    public static final int PLAYER_ALG2 = 2;

    private ImagePanel[][] Fields;
    private JPanel BoardPanel;
    private JButton BtnClose;
    private JButton BtnSave;
    private JButton BtnUndo;
    private FrmGame Frame;

    private Timer AISleepTimer;
    private int BoardSize;
    private Game game;

    public GameController(int size, int Player1, int Player2)
    {
        BoardSize = size;
        getComponents();
        initListeners();
        prepareGame(Player1, Player2);
    }

    public GameController(int size, int Player1, int Player2, int fc, int fi, int fb)
    {
        BoardSize = size;
        getComponents();
        initListeners();
        prepareGame(Player1, Player2);
        setFreeze(fc, fi, fb);
    }

    public GameController(Game loadedGame) {
        game = loadedGame;
        getComponents();
        initListeners();
    }

    public void Show()
    {
        Frame.setVisible(true);
    }

    public void runGame() {
        drawBoard();
    }

    private void getComponents() {
        Frame = new FrmGame(BoardSize);
        BoardPanel = Frame.getBoardPanel();
        BtnClose = Frame.getBtnClose();
        BtnSave = Frame.getBtnSave();
        BtnUndo = Frame.getBtnUndo();
        Fields = Frame.getFields();
        AISleepTimer = new Timer();
    }

    private void setFreeze(int fc, int fi, int fb) {
        if(game != null) {
            game.setDiskFreezing(fi, fb, fc);
            game.setFreezeListener((evt) -> {
                drawBoard();
            });
        }
    }

    private void prepareGame(int Player1, int Player2) {
        if(game == null) {
            ReversiRules rules = new ReversiRules(BoardSize);
            Board board = new Board(rules);
            game = new ija.ija2015.othello.game.Game(board);
            addPlayer(Player1, true);
            addPlayer(Player2, false);
        }
    }

    private void addPlayer(int playerType, boolean isWhite) {
        switch (playerType) {
            case PLAYER_USER:
                game.addPlayer(new Player(isWhite));
                break;
            case PLAYER_ALG1:
                game.addPlayer(new RandomAI(isWhite));
                break;
            case PLAYER_ALG2:
                game.addPlayer(new CostAI(isWhite));
                break;
        }
    }

    private void initListeners()
    {
        if(BtnClose instanceof JButton){
            BtnClose.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Frame.dispose();
                }
            });
        }

        if(BtnSave instanceof JButton) {
            BtnSave.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String FileName = "";
                    JFileChooser c = new JFileChooser();
                    int rVal = c.showOpenDialog(Frame);
                    if (rVal == JFileChooser.APPROVE_OPTION)
                        FileName = c.getCurrentDirectory().toString() + "\\" + c.getSelectedFile().getName();
                    try  { new GameSaver(game).Save(FileName); }
                    catch (Exception ex) { }
                }
            });
        }
        if(BtnUndo instanceof JButton) {
            BtnUndo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(!game.playerOne().isHuman() && !game.playerTwo().isHuman())
                        return;

                    game.Undo();
                    drawBoard();
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
                        game.nextPlayer();

                    drawBoard();

                    if(!game.currentPlayer().isHuman())
                        AiTurn();
                }
                else {
                    if(game.currentPlayer().isHuman())
                        game.Place(game.getBoard().getField(col+1, row+1));

                    drawBoard();

                    if(!game.currentPlayer().isHuman())
                        AiTurn();
                }
            }
        });
    }

    private void AiTurn() {
        if (game.currentPlayer().PossibleTurns().size() == 0) {
            game.nextPlayer();

            drawBoard();

            if(!game.currentPlayer().isHuman())
                AiTurn();
        } else {
            AISleepTimer.schedule(new TimerTask() {

                @Override
                public void run() {
                    game.Place(((AI) game.currentPlayer()).Turn());
                    drawBoard();
                }

            }, (1 * 1000));
        }
    }

    private void drawBoard() {
        Board board = game.getBoard();
        BoardPanel.removeAll();

        boolean bothAi = (!game.playerOne().isHuman()) && (!game.playerTwo().isHuman());

        if(game.currentPlayer().isHuman()) {
            for (int[] turn : game.currentPlayer().PossibleTurns())
                Frame.drawDisk(turn[1] - 1, turn[0] - 1, Frame.getHelpField());
        }

        for (int col = 0; col < board.getSize(); col++)
        {
            for (int row = 0; row < board.getSize(); row++)
            {
                if(!board.getField(col+1, row+1).isEmpty()) {
                    if (board.getField(col + 1, row + 1).getDisk().isFrozen())
                        Frame.drawDisk(row, col, Frame.getFreezedDisk());
                    else if (board.getField(col + 1, row + 1).getDisk().isWhite())
                        Frame.drawDisk(row, col, Frame.getWhiteDisk());
                    else
                        Frame.drawDisk(row, col, Frame.getBlackDisk());
                } else
                    Frame.drawDisk(row, col, Frame.getEmptyField());
                if(!bothAi)
                    setFieldEvent(row, col);
            }
        }

        Frame.setScore(game.getScore()[0], game.getScore()[1]);
        Frame.changePlayer(game.currentPlayer().isWhite() ? Frame.getWhiteDisk(): Frame.getBlackDisk());

        if(game.isEnd()) {
            JOptionPane.showMessageDialog(null,
                    "Vyhrál " + ((game.getWinner().isWhite()) ? "bílý" : "černý") + " hráč.",
                    "Konec hry", JOptionPane.INFORMATION_MESSAGE);
        } else if(bothAi)
            AiTurn();
    }

    @Override
    public void dispose() {
        Frame.dispatchEvent(new WindowEvent(Frame, WindowEvent.WINDOW_CLOSING));
    }
}
