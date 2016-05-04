/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.othello.game;

import ija.ija2015.othello.board.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author XKADER13, XZEMAN53
 */
public class ReversiField extends AbstractField {

    private ArrayList<Disk> turnedDisks;

    public ReversiField(int row, int col)
    {
        super(row, col);
        turnedDisks = new ArrayList<Disk>();
    }

    @Override
    public boolean canPutDisk(Disk disk)
    {
        if (!this.isEmpty())
            return false;

        //Queue<Disk> disks = new ArrayDeque<>();
        Field next;
        for (Direction dir : Direction.values())
        {
            if (this.nextField(dir).isEmpty())
                continue;

            if (this.nextField(dir).getDisk().isWhite() == disk.isWhite())
                continue;

            next = this.nextField(dir).nextField(dir);

            while(!next.isEmpty())
            {
                if (next.getDisk().isWhite() == disk.isWhite())
                    return true;

                next = next.nextField(dir);
            }
        }

        return false;
    }

    public boolean turnDisks(Disk disk)
    {
        Queue<Disk> disks = new ArrayDeque<>();
        Queue<Disk> temp = new ArrayDeque<>();
        turnedDisks.clear();

        Field next;
        boolean succes;

        for (Direction dir : Direction.values())
        {
            succes = false;
            temp.clear();

            if (this.nextField(dir).isEmpty())
                continue;

            if (this.nextField(dir).getDisk().isWhite() == disk.isWhite())
                continue;

            temp.add(this.nextField(dir).getDisk());
            next = this.nextField(dir).nextField(dir);

            while(!next.isEmpty())
            {
                if (next.getDisk().isWhite() == disk.isWhite())
                {
                    succes = true;
                    break;
                }

                temp.add(next.getDisk());
                next = next.nextField(dir);
            }

            if (succes) {
                disks.addAll(temp);
            }
        }

        disks.forEach(d -> d.turn());
        turnedDisks.addAll(disks);

        return false;
    }

    @Override
    public boolean putDisk(Disk disk)
    {
        if (super.Disk != null)
            return false;

        super.Disk = disk;
        this.turnDisks(disk);

        return true;
    }

    public ArrayList<Disk> getTurnedDisks()
    {
        return this.turnedDisks;
    }
}
