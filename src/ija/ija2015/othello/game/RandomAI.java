package ija.ija2015.othello.game;

import java.io.Console;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by kader on 04.05.2016.
 */
public class RandomAI extends Player implements AI
{
    public RandomAI(boolean isWhite)
    {
        super(isWhite);
        isHuman = false;
        name = "RandomAI";
    }

    @Override
    public boolean Turn()
    {
        ArrayList<int[]> possible = PossibleTurns();

        if (possible.size() == 0)
        {
            return false;
        }

        int randomNum = 0 + (int)(Math.random() * ((possible.size() -1 - 0) + 1));
        System.out.println("AI: " + possible.get(randomNum)[0] + " : " + possible.get(randomNum)[1]);

        return putDisk(board.getField(possible.get(randomNum)[0], possible.get(randomNum)[1]));
    }
}
