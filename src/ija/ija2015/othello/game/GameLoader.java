package ija.ija2015.othello.game;

import ija.ija2015.othello.board.Board;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by kader on 05.05.2016.
 */
public class GameLoader
{
    private Game game;
    private BufferedReader reader;

    public Game Load(String filename) throws Exception
    {
        OpenToWrite(filename);

        ReversiRules rules = new ReversiRules(Integer.parseInt(reader.readLine()));
        Board board = new Board(rules);
        game = new Game(board);

        LoadPlayer("W");
        LoadPlayer("B");

        LoadTurns();

        return game;
    }

    private void LoadTurns() throws Exception
    {
        String input = reader.readLine();
        String[] turns = input.split("\\|");

        for (String turn : turns)
        {
            String[] data = turn.split(":");

            int row = Integer.parseInt(data[0]);
            int col = Integer.parseInt(data[1]);

            game.Place(game.getBoard().getField(row, col));
        }
    }

    private void LoadPlayer(String color) throws Exception
    {
        Player player;
        String line = reader.readLine();
        boolean isHuman = true;

        if (line.isEmpty())
            throw new Exception();

        String[] data = line.split(":");

        if (data.length != 2)
        {
            game.addPlayer(new Player(color == "W" ? true : false));
            return;
        }

        if (!data[0].equals(color))
            throw new Exception();


        if (data[1].equals("RandomAI"))
        {
            player = new RandomAI(color == "W" ? true : false);
        }
        else if (data[1].equals("CostAI"))
        {
            player = new CostAI(color == "W" ? true : false);
        }
        else
        {
            player = new Player(color == "W" ? true : false, true, data[1]);
        }

        game.addPlayer(player);
    }

    private void OpenToWrite(String filename) throws Exception
    {
        Path path = Paths.get(filename);
        reader = Files.newBufferedReader(path);
    }
}
