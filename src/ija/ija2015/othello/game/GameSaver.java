package ija.ija2015.othello.game;

import ija.ija2015.othello.board.Field;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by kader on 05.05.2016.
 */
public class GameSaver
{
    private Game game;
    private BufferedWriter writer;

    public GameSaver(Game game)
    {
        this.game = game;
    }

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

    private void AddCommands() throws IOException
    {
        for (PutCommand command : game.getCommandManager().getCommands())
        {
            ReversiField field = (ReversiField)command.getField();
            writer.write(field.getRow() + ":" + field.getCol() + "|");
        }

        writer.newLine();
    }

    private void OpenToWrite(String filename) throws Exception
    {
        Path path = Paths.get(filename);
        writer = Files.newBufferedWriter(path);
    }

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

    private void AddFreezing() throws IOException
    {
        DiskFreezing freezing = game.getDiskFreezing();

        if (freezing == null)
            return;

        writer.write(freezing.getTimerInterval() + ":" + freezing.getFreezeInterval() + ":" + freezing.getFreezeCount());
        writer.newLine();
    }
}
