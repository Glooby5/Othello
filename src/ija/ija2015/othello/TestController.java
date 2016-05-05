package ija.ija2015.othello;

import ija.ija2015.othello.board.Board;
import ija.ija2015.othello.game.*;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by kader on 04.05.2016.
 */
public class TestController
{
    static Game game;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main (String args[]) throws IOException
    {
        int size = 6;

        ReversiRules rules = new ReversiRules(size);

        Board board = new Board(rules);
        game = new Game(board);

        Player p1 = new Player(true, true, "Honza");
        //Player p1 = new CostAI(true);
        Player p2 = new Player(false);
        //Player p2 = new RandomAI(false);

        game.addPlayer(p1);
        game.addPlayer(p2);

        String coords = br.readLine();
        System.out.println(game.getBoard().toString());

        if (coords.startsWith("l"))
        {
            GameLoader loader = new GameLoader();

            try
            {
                game = loader.Load(coords.substring(2));
            }
            catch (Exception ex) { }
        }

        System.out.println(game.getBoard().toString());

        game.addFreezeListener(evt -> { System.out.println("FREEZE"); System.out.println(game.getBoard().toString()); });

        while (!game.isEnd())
        {
            System.out.println("SCORE: " + game.getScore()[0] + " : " + game.getScore()[1]);
            System.out.print(game.currentPlayer().getName() + " " + game.currentPlayer() + "\n");

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

            if (game.currentPlayer().isHuman())
            {
                humanTurn();
            }
            else
            {
                br.readLine();
                game.Place(((AI)game.currentPlayer()).Turn());
            }

            System.out.println("\n" + game.getBoard());
        }

        System.out.println("Winner: " + game.getWinner());
        System.out.println("SCORE: " + game.getScore()[0] + " : " + game.getScore()[1]);
    }

    private static void humanTurn() throws IOException
    {
        System.out.print(" Enter:\n");
        String coords = "";
        String splitString[];
        int inX = 0;
        int inY = 0;

        coords = br.readLine();

        if (coords.equals("u"))
        {
            game.Undo();
            return;
        }
        else if (coords.equals("s"))
        {
            GameSaver saver = new GameSaver(game);

            try
            {
                saver.Save("game.game");
            }
            catch (Exception ex)
            {

            }
        }

        try
        {
            splitString = coords.split(" ");

            if (splitString.length == 1)
                return;

            inX = Integer.parseInt(splitString[0]);
            inY = Integer.parseInt(splitString[1]);
        }
        catch (NumberFormatException nfe)
        {
            System.err.println("Invalid Format!");
        }

        game.Place(game.getBoard().getField(inX, inY));
    }
}
