/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.othello.game;

import ija.ija2015.othello.board.*;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author XKADER13, XZEMAN53
 */
public class Game {

    private final Board board;
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
    }

    /**
     * Přidá hráče a současně vyvolá jeho inicializaci.
     * @param player
     * @return
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
     * @return
     */
    public Player currentPlayer()
    {
        return this.currentPlayer;
    }

    /**
     * Vrátí hrací desku.
     * @return
     */
    public Board getBoard()
    {
        return this.board;
    }

    /**
     * Změní aktuálního hráče.
     * @return
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
}
