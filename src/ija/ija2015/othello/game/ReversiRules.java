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
public class ReversiRules implements Rules {

    private int Size;
    
    //Inicializace pravidel.
    public ReversiRules(int size)
    {
        Size = size;
    }
    
    protected Disk putDefaultDisks(int row, int col)
    {
        int halfSize = Size/2;

        if ((col == row) && (row == halfSize || row == halfSize+1))
            return new Disk(true);
        else if ((row+1 == col && row == halfSize) || (row-1 == col && col == halfSize))
            return new Disk(false);
        else
            return null;
    }
    
    @Override
    public Field createField(int row, int col)
    {
        Field field = new ReversiField(row, col);
        field.putDisk(putDefaultDisks(row, col));
        return field;
    }
    
    @Override
    public int getSize()
    {
        return Size;
    }

    @Override
    public int numberDisks()
    {
        return (Size*Size)/2;
    }


}
