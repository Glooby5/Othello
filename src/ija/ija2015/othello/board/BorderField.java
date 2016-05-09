/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.othello.board;

/**
 * Hraniční pole.
 *
 * @author XKADER13, XZEMAN53
 */
public class BorderField implements Field {

    public BorderField() 
    {
    }

    /**
     * Nedělá nic.
     * @param dirs směr
     * @param field pole
     */
    @Override
    public void	addNextField(Field.Direction dirs, Field field) {}
    
    @Override
    public boolean canPutDisk(Disk disk)
    {
        return false;
    }

    /**
     * Vrací kámen, který je vložen na pole.
     * @return Disk
     */
    @Override
    public Disk	getDisk()
    {
        return null;
    }
    
    @Override
    public boolean isEmpty()
    {
        return true;
    }

    /**
     * Vrátí sousední pole v daném směru dirs
     * @param dirs směr
     * @return Pole
     */
    @Override
    public Field nextField(Field.Direction dirs)
    {
        return null;
    }

    /**
     * Vloží na pole kámen.
     * @param disk Disk
     * @return Úspěch
     */
    @Override
    public boolean putDisk(Disk disk)
    {
        return false;
    }

    @Override
    public boolean removeDisk()
    {
        return false;
    }
    
    @Override
    public String toString()
    {
        return "";
    }
}
