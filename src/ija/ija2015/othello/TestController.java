package ija.ija2015.othello;

import ija.ija2015.othello.board.Board;
import ija.ija2015.othello.game.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by kader on 04.05.2016.
 */
public class TestController
{
    public static void main (String args[]) throws IOException
    {
        System.out.println ("Hello World!");

        System.out.println("Game");
        int size = 8;

        ReversiRules rules = new ReversiRules(size);
        Board board = new Board(rules);
        Game game = new Game(board);

        //Player p1 = new Player(true);
        Player p1 = new CostAI(true);
        //Player p2 = new Player(false);
        Player p2 = new RandomAI(false);

        game.addPlayer(p1);
        game.addPlayer(p2);

        System.out.println(game.getBoard().toString());

        String coords = "";
        String splitString[];
        int inX = 0;
        int inY = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true)
        {
            System.out.println("SCORE: " + game.currentPlayer().getScore());
            if (game.currentPlayer().isHuman())
            {
                System.out.print(game.currentPlayer() + "\n");

                ArrayList<int[]> possibleTurns = game.currentPlayer().PossibleTurns();

                if (possibleTurns.size() == 0)
                {
                    System.out.println("PASS");
                    game.nextPlayer();
                    continue;
                }

                for (int[] turn : possibleTurns)
                {
                    System.out.print(turn[0] + " : " + turn[1] + " | ");
                }

                System.out.print(" Enter:\n");
                coords = br.readLine();

                try
                {
                    splitString = coords.split(" ");
                    if (splitString.length == 1)
                        continue;

                    inX = Integer.parseInt(splitString[0]);
                    inY = Integer.parseInt(splitString[1]);
                } catch (NumberFormatException nfe)
                {
                    System.err.println("Invalid Format!");
                }

                game.currentPlayer().putDisk(game.getBoard().getField(inX, inY));
                System.out.println(game.getBoard());
                game.nextPlayer();
            }
            else
            {
                br.readLine();

                ArrayList<int[]> possibleTurns = game.currentPlayer().PossibleTurns();

                if (possibleTurns.size() == 0)
                {
                    System.out.println("PASS");
                    game.nextPlayer();
                    continue;
                }
                ((AI)game.currentPlayer()).Turn();
                for (int[] turn : possibleTurns)
                {
                    System.out.print(turn[0] + " : " + turn[1] + " | ");
                }
                System.out.println("\n" + game.getBoard());
                game.nextPlayer();
            }
        }
    }
}
