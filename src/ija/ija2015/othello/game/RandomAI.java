package ija.ija2015.othello.game;

import ija.ija2015.othello.board.Field;

import java.io.Console;
import java.util.ArrayList;
import java.util.Random;

/**
 * Algoritmus, který vybere náhodně z možných tahů hráče.
 *
 * @author XKADER13, XZEMAN53
 */
public class RandomAI extends Player implements AI
{
    public RandomAI(boolean isWhite)
    {
        super(isWhite, false, "RandomAI");
    }

    /**
     * Určí na jaké pole, chce algoritmus zahrát.
     *
     * @return Pole kam chce hrát.
     */
    @Override
    public Field Turn()
    {
        ArrayList<int[]> possible = PossibleTurns();

        if (possible.size() == 0)
        {
            return null;
        }

        int randomNum = 0 + (int)(Math.random() * ((possible.size() -1 - 0) + 1));

        System.out.println("AI: " + possible.get(randomNum)[0] + " : " + possible.get(randomNum)[1]);

        return board.getField(possible.get(randomNum)[0], possible.get(randomNum)[1]);
    }
}
