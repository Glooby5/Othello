/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.othello.game;

import ija.ija2015.othello.board.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author XKADER13, XZEMAN53
 */
public class Game {
    
    private Board Board;
    private List<Player> Players;
    private int ActualPlayer;
    
    //Inicializuje hru.
    public Game(Board board)
    {
        Board = board;
        Players = new ArrayList<Player>();
        ActualPlayer = 0;
    }
    
    //Přidá hráče a současně vyvolá jeho inicializaci.
    public boolean addPlayer(Player player)
    {
        player.init(Board);
        return Players.add(player);
    }
    //Vrátí aktuálního hráče, který je na tahu.
    public Player currentPlayer()
    {
        return Players.get(ActualPlayer);
    }
    //Vrátí hrací desku.
    public Board getBoard()
    {
        return Board;
    }
    //Změní aktuálního hráče.
    public Player nextPlayer()
    {
        return Players.get((ActualPlayer++) % Players.size());
    }

}
