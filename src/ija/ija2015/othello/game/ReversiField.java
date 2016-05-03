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
public class ReversiField extends AbstractField {
    
    public ReversiField(int row, int col)
    {
        super(row, col);
    }
    
    @Override
    public boolean canPutDisk(Disk disk)
    {
        if(disk == null && !this.isEmpty())
            return false;
        
        Field.Direction[] dirs = Field.Direction.values();
        for(int i = 0; i < 8; i++)
        {
            Field next = nextField(dirs[i]);
            
            if((next != null) && !(next instanceof BorderField) && !next.isEmpty()) // not border and not empty
            {
                if(next.getDisk().isWhite() != disk.isWhite())
                {
                    Field afterNext = next.nextField(dirs[i]);
                    while((afterNext != null) && !(afterNext instanceof BorderField) && !afterNext.isEmpty())
                    {
                        if(afterNext.getDisk().isWhite() == disk.isWhite())
                            return true;
                        
                        afterNext = afterNext.nextField(dirs[i]);
                    }
                }
            }
        }
        
        return false;
    }
    
    //Vloží na pole kámen.
    @Override
    public boolean putDisk(Disk disk)
    {
        if(disk == null)
            return false;
        
        Field.Direction[] dirs = Field.Direction.values();
        for(int i = 0; i < 8; i++)
        {
            Field next = nextField(dirs[i]);
            if(next != null && !(next instanceof BorderField) && !next.isEmpty())
            {
                if(next.getDisk().isWhite() != disk.isWhite())
                {
                    Field afterNext = next.nextField(dirs[i]);
                    while((afterNext != null) && !(afterNext instanceof BorderField) && !afterNext.isEmpty())
                    {
                        next.getDisk().turn();
                        
                        if(afterNext.getDisk().isWhite() == disk.isWhite())
                            break;
                        
                        next = afterNext;
                        afterNext = afterNext.nextField(dirs[i]);
                    }
                }
            }
        }
        
        super.Disk = disk;
        return true;
    }
}
