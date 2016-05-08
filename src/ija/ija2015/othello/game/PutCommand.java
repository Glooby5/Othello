package ija.ija2015.othello.game;

import ija.ija2015.othello.board.Disk;
import ija.ija2015.othello.board.Field;

import java.util.ArrayList;

/**
 * Reprezentuje tah hráče.
 *
 * @author XKADER13, XZEMAN53
 */
public class PutCommand
{
    private Player player;
    private Field field;
    private ArrayList<Disk> turnedDisks;

    /**
     * Konsturktor
     *
     * @param player Hráč
     * @param field Pole
     */
    public PutCommand(Player player, Field field)
    {
        this.player = player;
        this.field = field;
    }

    /**
     * Vykoná příkaz.
     *
     * @return True pokud se povedlo provést akci, jinak False
     */
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

    /**
     * Vrací List otočených kamenů
     * @return Otočené disky
     */
    public ArrayList<Disk> getTurnedDisks()
    {
        return turnedDisks;
    }
}
