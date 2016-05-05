/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.othello.game;

import ija.ija2015.othello.board.*;

import java.awt.event.ActionListener;
import java.io.Serializable;

/**
 *
 * @author XKADER13, XZEMAN53
 */
public class Game implements Serializable
{
    private final Board board;
    private CommandManager commandManager;
    private DiskFreezing diskFreezing;

    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;

    /**
     * Inicializuje hru.
     * @param board
     */
    public Game(Board board)
    {
        this.board = board;
        this.commandManager = new CommandManager();
    }

    public void Place(Field field)
    {
        if (!commandManager.Execute(new PutCommand(currentPlayer, field)))
            return;

        nextPlayer();

        if (diskFreezing != null)
            diskFreezing.SetFreeze();
    }

    public void Undo()
    {
        if (diskFreezing != null)
            diskFreezing.UnfreezeAll();

        commandManager.Undo();
        commandManager.Undo();
    }

    //region DiskFreezing

    public void setDiskFreezing(DiskFreezing diskFreezing)
    {
        this.diskFreezing = diskFreezing;
    }

    /**
     * Přidá možnost zamrzání kamenů
     *
     * @param timerInterval Maximální doba pro zamrznutí kamenů
     * @param freezeInterval Doba zamrznutí kamenů
     * @param freezeCount Počet kamenů k zamrznutí
     */
    public void setDiskFreezing(int timerInterval, int freezeInterval, int freezeCount)
    {
        this.diskFreezing = new DiskFreezing(this, timerInterval, freezeInterval, freezeCount);
    }

    /**
     * Nastavení listeneru pro zachycení zamrznutí
     *
     * @param listener
     */
    public void setFreezeListener(ActionListener listener)
    {
        if (diskFreezing != null)
            diskFreezing.setFreezeListener(listener);
    }

    //endregion

    //region Player

    /**
     * Přidá hráče a současně vyvolá jeho inicializaci.
     *
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

    //endregion

    /**
     * Ověří zda již není konec hry
     *
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
     * Získání skóre
     *
     * @return Skóre hráčů. [0] - první hráč, [1] - druhý hráč
     */
    public int[] getScore()
    {
        int[] score = new int[2];

        score[0] = this.playerOne.getScore();
        score[1] = this.playerTwo.getScore();

        return score;
    }


    public Board getBoard()
    {
        return this.board;
    }

    public CommandManager getCommandManager()
    {
        return commandManager;
    }
}
