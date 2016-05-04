package ija.ija2015.othello;

import ija.ija2015.othello.board.Board;
import ija.ija2015.othello.board.Field;
import ija.ija2015.othello.game.Game;
import ija.ija2015.othello.game.Player;
import ija.ija2015.othello.game.ReversiRules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringJoiner;

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

        Player p1 = new Player(true);
        Player p2 = new Player(false);

        game.addPlayer(p1);
        game.addPlayer(p2);

        System.out.print(game.getBoard().toString());


        Field f1 = game.getBoard().getField(3, 4);
        Field f2 = game.getBoard().getField(4, 6);

        game.currentPlayer().putDisk(f2);

        System.out.println(game.getBoard());


        game.nextPlayer();

        f2 = game.getBoard().getField(5, 6);
        game.currentPlayer().putDisk(f2);

        System.out.println(game.getBoard());

        String coords = "";
        String splitString[];
        int inX = 0;
        int inY = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true)
        {
            System.out.print(game.currentPlayer() + "\n");
            game.PossibleTurns();
            System.out.print("Enter:");
            coords = br.readLine();

            try
            {
                splitString = coords.split(" ");
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
    }
}
