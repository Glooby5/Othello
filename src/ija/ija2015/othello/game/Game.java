/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.othello.game;

import ija.ija2015.othello.board.*;

import java.io.Console;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author XKADER13, XZEMAN53
 */
public class Game implements Serializable
{
    private final Board board;
    private CommandManager commandManager;

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

    /**
     * Vrátí hrací desku.
     * @return Hrací deska
     */
    public Board getBoard()
    {
        return this.board;
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

    public void Place(Field field)
    {
        commandManager.Execute(new PutCommand(currentPlayer, field));
        nextPlayer();
    }

    public void Undo()
    {
        commandManager.Undo();
        commandManager.Undo();
    }
}
