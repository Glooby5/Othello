package ija.ija2015.othello.game;

import ija.ija2015.othello.board.Field;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Třída pro uložení rozehrané hry.
 *
 * @author XKADER13, XZEMAN53
 */
public class GameSaver
{
    private Game game;
    private BufferedWriter writer;

    /**
     * Konstruktor
     * @param game Rozehraná hra
     */
    public GameSaver(Game game)
    {
        this.game = game;
    }

    /**
     * Uloží rozehranou hru do zadaného souboru.
     *
     * @param filename Název souboru
     * @throws Exception ex
     */
    public void Save(String filename) throws Exception
    {
        OpenToWrite(filename);

        writer.write(Integer.toString(game.getBoard().getSize()));
        writer.newLine();

        AddPlayers();
        AddCommands();
        AddFreezing();

        writer.flush();
        writer.close();
    }

    /**
     * Přidá provedené tahy hráčů.
     *
     * @throws IOException ex
     */
    private void AddCommands() throws IOException
    {
        for (PutCommand command : game.getCommandManager().getCommands())
        {
            ReversiField field = (ReversiField)command.getField();
            writer.write(field.getRow() + ":" + field.getCol() + "|");
        }

        writer.newLine();
    }

    /**
     * Otevře soubor pro zápis.
     *
     * @param filename Název souboru
     * @throws Exception ex
     */
    private void OpenToWrite(String filename) throws Exception
    {
        Path path = Paths.get(filename);
        writer = Files.newBufferedWriter(path);
    }

    /**
     * Přidá hráče
     *
     * @throws Exception ex
     */
    private void AddPlayers() throws Exception
    {
        if (!game.currentPlayer().isWhite())
        {
            game.nextPlayer();
        }

        writer.write("W:" + game.currentPlayer().getName());
        writer.newLine();

        writer.write("B:" + game.nextPlayer().getName());
        writer.newLine();
    }

    /**
     * Přidá nastavení zamrzání kamenů.
     *
     * @throws IOException ex
     */
    private void AddFreezing() throws IOException
    {
        DiskFreezing freezing = game.getDiskFreezing();

        if (freezing == null)
            return;

        writer.write(freezing.getTimerInterval() + ":" + freezing.getFreezeInterval() + ":" + freezing.getFreezeCount());
        writer.newLine();
    }
}
