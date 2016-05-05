package ija.ija2015.othello.game;

import ija.ija2015.othello.board.Disk;
import ija.ija2015.othello.board.Field;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by kader on 04.05.2016.
 */
public class CostAI extends Player implements AI
{
    public CostAI(boolean isWhite)
    {
        super(isWhite);
        isHuman = false;
        name = "RandomAI";
    }

    @Override
    public Field Turn()
    {
        ArrayList<int[]> possible = PossibleTurns();

        if (possible.size() == 0)
        {
            return null;
        }

        int max = 0;
        int maxIndex = 0;
        int index = -1;

        for (int[] turn : possible)
        {
            index++;
            Queue<Disk> disks = new ArrayDeque<>();
            Queue<Disk> temp = new ArrayDeque<>();

            Field next;
            Field fieldItem = board.getField(turn[0], turn[1]);
            boolean succes;

            for (Field.Direction dir : Field.Direction.values())
            {
                succes = false;
                temp.clear();

                if (fieldItem.nextField(dir).isEmpty())
                    continue;

                if (fieldItem.nextField(dir).getDisk().isWhite() == isWhite())
                    continue;

                temp.add(fieldItem.nextField(dir).getDisk());
                next = fieldItem.nextField(dir).nextField(dir);

                while(!next.isEmpty())
                {
                    if (next.getDisk().isWhite() == isWhite())
                    {
                        succes = true;
                        break;
                    }

                    temp.add(next.getDisk());
                    next = next.nextField(dir);
                }

                if (succes)
                {
                    if (temp.size() > max)
                    {
                        max = temp.size();
                        maxIndex = index;
                    }
                }
            }
        }

        System.out.println("max: " + max);

        return board.getField(possible.get(maxIndex)[0], possible.get(maxIndex)[1]);
        //return putDisk(board.getField(possible.get(maxIndex)[0], possible.get(maxIndex)[1]));
    }
}
