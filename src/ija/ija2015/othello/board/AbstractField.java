/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.othello.board;

import java.util.Arrays;


/**
 *
 * @author XKADER13, XZEMAN53
 */
public abstract class AbstractField implements Field
{
    protected int Row;
    protected int Col;
    protected Disk Disk;
    protected Field[] surrFields;
    
    public AbstractField(int row, int col)
    {
        Row = row;
        Col = col;
        Disk = null;
        surrFields = new Field[8];
    }

    /**
     * Přidá sousední pole field v daném směru dirs.
     * @param dirs
     * @param field
     */
    @Override
    public void	addNextField(Field.Direction dirs, Field field)
    {
        this.surrFields[dirs.ordinal()] = field;
    }
    
    @Override
    public boolean canPutDisk(Disk disk)
    {
        return isEmpty();
    }

    /**
     * Vrací kámen, který je vložen na pole.
     * @return
     */
    @Override
    public Disk	getDisk()
    {
        return this.Disk;
    }
    
    @Override
    public boolean isEmpty()
    {
        return Disk == null;
    }

    /**
     * Vrátí sousední pole v daném směru dirs.
     * @param dirs
     * @return
     */
    @Override
    public Field nextField(Field.Direction dirs)
    {
        return this.surrFields[dirs.ordinal()];
    }


    /**
     * Vloží na pole kámen.
     * @param disk
     * @return
     */
    @Override
    public boolean putDisk(Disk disk)
    {
        if(disk == null)
            return false;
        
        this.Disk = disk;
        return true;
    }
    
    @Override
    public boolean equals(java.lang.Object obj) 
    {
        return (obj instanceof AbstractField) && (this.Col == ((AbstractField)obj).Col && this.Row == ((AbstractField)obj).Row);
    }
    
    @Override
    public int hashCode() 
    {
        return Arrays.hashCode(this.surrFields) + this.Row + this.Col;
    }
    
    @Override
    public String toString()
    {
        return (Disk == null) ? " " : String.format("%1$s", Disk.toString());
    }
}
