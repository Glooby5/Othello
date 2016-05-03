/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.othello.game;

import ija.ija2015.othello.board.*;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *
 * @author XKADER13, XZEMAN53
 */
public class Player
{

    private boolean isWhite;
    private Queue<Disk> disks;

    public Player(boolean isWhite)
    {
        this.isWhite = isWhite;
        this.disks = new ArrayDeque<>();
    }

    public boolean isWhite()
    {
        return this.isWhite;
    }

    /**
     * Test, zda je možné vložit nový kámen hráče na dané pole.
     *
     * @param field
     * @return
     */
    public boolean canPutDisk(Field field)
    {
        return field.canPutDisk(this.disks.peek());
    }

    /**
     * Test prázdnosti sady kamenů, které má hráč k dispozici.
     *
     * @return
     */
    public boolean emptyPool()
    {
        return this.disks.isEmpty();
    }

    /**
     * Inicializace hráče v rámci hrací desky.
     *
     * @param board
     */
    public void init(Board board)
    {
        for (int i = 0; i < board.getSize() * board.getSize() / 2; i++)
        {
            this.disks.add(new Disk(this.isWhite));
        }

        if (this.isWhite)
        {
            board.getField(board.getSize() / 2, board.getSize() / 2).putDisk(this.disks.poll());
            board.getField(board.getSize() / 2 + 1, board.getSize() / 2 + 1).putDisk(this.disks.poll());
        }
        else
        {
            board.getField(board.getSize() / 2, board.getSize() / 2 + 1).putDisk(this.disks.poll());
            board.getField(board.getSize() / 2 + 1, board.getSize() / 2).putDisk(this.disks.poll());
        }

    }

    /**
     * Vloží nový kámen hráče na dané pole, pokud to pravidla umožňují.
     *
     * @param field
     * @return
     */
    public boolean putDisk(Field field)
    {
        if (this.emptyPool())
            return false;

        if (!field.canPutDisk(this.disks.peek()))
            return false;

        if (!field.putDisk(this.disks.peek()))
            return false;

        this.disks.poll();

        return true;
    }

    public String toString()
    {
        return this.isWhite ? "white" : "black";
    }

}

