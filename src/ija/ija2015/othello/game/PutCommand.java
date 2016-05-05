package ija.ija2015.othello.game;

import ija.ija2015.othello.board.Disk;
import ija.ija2015.othello.board.Field;

import java.util.ArrayList;

/**
 * Created by kader on 05.05.2016.
 */
public class PutCommand
{
    private Player player;
    private Field field;
    private ArrayList<Disk> turnedDisks;

    public PutCommand(Player player, Field field)
    {
        this.player = player;
        this.field = field;
    }

    public boolean Execute()
    {
        boolean success = player.putDisk(field);

        turnedDisks = new ArrayList<Disk>(((ReversiField)field).getTurnedDisks());

        return success;
    }

    public Field getField()
    {
        return field;
    }

    public ArrayList<Disk> getTurnedDisks()
    {
        return turnedDisks;
    }
}
