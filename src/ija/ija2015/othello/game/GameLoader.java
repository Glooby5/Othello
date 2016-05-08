package ija.ija2015.othello.game;

import ija.ija2015.othello.board.Board;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Třída pro načítání uložených her.
 *
 * @author XKADER13, XZEMAN53
 */
public class GameLoader
{
    private Game game;
    private BufferedReader reader;

    /**
     * Načte uloženou hru.
     *
     * @param filename Cesta k souboru s uloženou hrou
     * @return Načtená hra typu Game
     * @throws Exception ex
     */
    public Game Load(String filename) throws Exception
    {
        OpenToRead(filename);

        ReversiRules rules = new ReversiRules(Integer.parseInt(reader.readLine()));
        Board board = new Board(rules);
        game = new Game(board);

        LoadPlayer("W");
        LoadPlayer("B");

        LoadTurns();
        LoadFreezing();

        return game;
    }

    /**
     * Načte nastavení zamrzání.
     *
     * @throws Exception ex
     */
    private void LoadFreezing() throws Exception
    {
        String input = reader.readLine();

        if (input == null || input.isEmpty())
            return;

        String[] data = input.split(":");

        if (data.length != 3)
            throw new Exception();

        int timeInterval = Integer.parseInt(data[0]);
        int freezeInterval = Integer.parseInt(data[0]);
        int countInterval = Integer.parseInt(data[0]);

        game.setDiskFreezing(timeInterval, freezeInterval, countInterval);
    }

    /**
     * Načte provedené tahy hráčů.
     *
     * @throws Exception ex
     */
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

    /**
     * Načte hráče ve hře.
     *
     * @param color Barva W/B který hráč se má načíst.
     * @throws Exception ex
     */
    private void LoadPlayer(String color) throws Exception
    {
        String line = reader.readLine();

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


        createPLayer(color, data[1]);
    }

    /**
     * Vytvoří instanci hráče.
     *
     * @param color barva
     * @param s vstup
     */
    private void createPLayer(String color, String s)
    {
        Player player;

        if (s.equals("RandomAI"))
        {
            player = new RandomAI(color == "W" ? true : false);
        }
        else if (s.equals("CostAI"))
        {
            player = new CostAI(color == "W" ? true : false);
        }
        else
        {
            player = new Player(color == "W" ? true : false, true, s);
        }

        game.addPlayer(player);
    }

    /**
     * Otevře soubor pro čtení.
     *
     * @param filename Jméno souboru
     * @throws Exception ex
     */
    private void OpenToRead(String filename) throws Exception
    {
        Path path = Paths.get(filename);
        reader = Files.newBufferedReader(path);
    }
}
