/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.othello.game;

import ija.ija2015.othello.board.*;

/**
 *
 * @author XKADER13, XZEMAN53
 */
public class Player {
    
    private boolean IsWhite;
    private int diskCount;
    
    //Inicializace hráče.
    public Player(boolean isWhite)
    {
        IsWhite = isWhite;
    }
    
    //Test, zda je možné vložit nový kámen hráče na dané pole.
    public boolean canPutDisk(Field field)
    {
        return field.canPutDisk(new Disk(IsWhite));
    }

    //Test prázdnosti sady kamenů, které má hráč k dispozici.
    public boolean emptyPool()
    {
        return diskCount == 0;
    }

    //Inicializace hráče v rámci hrací desky.
    public void init(Board board)
    {
        diskCount = board.getRules().numberDisks();
    }

    //Test barvy hráče.
    public boolean IsWhite()
    {
        return IsWhite;
    }

    //Vloží nový kámen hráče na dané pole, pokud to pravidla umožňují.
    public boolean putDisk(Field field)
    {
        return field.putDisk(new Disk(IsWhite));
    }
    
    @Override
    public String toString()
    {
        return IsWhite ? "white" : "black";
    }


}
