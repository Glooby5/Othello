/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.othello.game;

import ija.ija2015.othello.board.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author XKADER13, XZEMAN53
 */
public class Game implements Serializable
{
    private final Board board;
    private CommandManager commandManager;
    private ActionListener freezeListener;
    private Random rand;

    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;

    private int timerInterval;
    private int freezeInterval;
    private int freezeCount;
    private boolean unfreeze;
    private boolean isFreeze;

    /**
     * Inicializuje hru.
     * @param board
     */
    public Game(Board board)
    {
        this.board = board;
        this.commandManager = new CommandManager();
        this.rand = new Random();

        timerInterval = 5;
        freezeInterval = 8;
        freezeCount = 8;

        unfreeze = false;
        isFreeze = false;
    }


    /**
     * Přidá hráče a současně vyvolá jeho inicializaci.
     * @param player Hráč
     * @return Pokud šlo přidat
     */
    public boolean addPlayer(Player player)
    {
        player.init(this.getBoard());

        if (this.playerOne == null)
        {
            this.playerOne = player;
            this.currentPlayer = player;
        }
        else if (this.playerTwo == null)
        {
            this.playerTwo = player;
        }
        else
        {
            return false;
        }

        return true;
    }

    /**
     * Vrátí aktuálního hráče, který je na tahu.
     * @return Aktuální hráč
     */
    public Player currentPlayer()
    {
        return this.currentPlayer;
    }

    /**
     * Změní aktuálního hráče.
     * @return Další hráč
     */
    public Player nextPlayer()
    {
        if (this.currentPlayer == this.playerOne)
        {
            this.currentPlayer = this.playerTwo;
        }
        else
        {
            this.currentPlayer = this.playerOne;
        }

        return this.currentPlayer;
    }

    public void addFreezeListener(ActionListener listener)
    {
        freezeListener = listener;
    }

    private void freezeDisks()
    {
        ArrayList<Disk> disks = getDisks();

        freezeCount = randInt(0, freezeCount);
        freezeCount = freezeCount > disks.size() ? disks.size() : freezeCount;

        for (int i = 0; i < freezeCount; i++)
        {
            disks.get(i).freeze();
        }

        unfreeze = false;
        isFreeze = true;

        freezeListener.actionPerformed(new ActionEvent(this, 0, ""));
    }

    private void unfreezeDisks()
    {
        getDisks().forEach(disk -> disk.unfreeze());
    }

    private void setUnfreezeTimer()
    {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run()
            {
                unfreeze = true;
            }
        }, randInt(0, freezeInterval) * 1000);
    }

    private void setFreezeTimer()
    {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {

            @Override
            public void run()
            {
                freezeDisks();
                setUnfreezeTimer();
            }
        }, randInt(0, timerInterval) * 1000);
    }

    private void setFreeze()
    {
        if (!unfreeze && isFreeze)
            return;

        if (isFreeze)
            unfreezeDisks();

        setFreezeTimer();
    }

    public void Place(Field field)
    {
        if (commandManager.Execute(new PutCommand(currentPlayer, field)))
        {
            nextPlayer();
            setFreeze();
        }
    }

    public void Undo()
    {
        commandManager.Undo();
        commandManager.Undo();
    }

    public int randInt(int min, int max)
    {
        return rand.nextInt((max - min) + 1) + min;
    }

    /**
     * Ověří zda již není konec hry
     * @return True pokud je konec hry, jinak False
     */
    public boolean isEnd()
    {
        if (currentPlayer().PossibleTurns().size() == 0)
        {
            if (nextPlayer().PossibleTurns().size() == 0)
            {
                return true;
            }
            nextPlayer();
        }

        return false;
    }

    /**
     * Zjistí vítěze
     * @return vítěz
     */
    public Player getWinner()
    {
        int first = currentPlayer().getScore();
        int second = nextPlayer().getScore();

        if (first > second)
        {
            return nextPlayer();
        }
        else if (first < second)
        {
            return currentPlayer();
        }
        else
        {
            return null;
        }
    }

    public int[] getScore()
    {
        int[] score = new int[2];

        score[0] = this.playerOne.getScore();
        score[1] = this.playerTwo.getScore();

        return score;
    }

    private ArrayList<Disk> getDisks()
    {
        ArrayList<Disk> disks = new ArrayList<>();

        for (int row = 1; row <= board.getSize(); row++)
        {
            for (int col = 1; col <= board.getSize(); col++)
            {
                if (!board.getField(row, col).isEmpty())
                    disks.add(board.getField(row, col).getDisk());
            }
        }

        return disks;
    }

    /**
     * Vrátí hrací desku.
     * @return Hrací deska
     */
    public Board getBoard()
    {
        return this.board;
    }

    public CommandManager getCommandManager()
    {
        return commandManager;
    }
}
