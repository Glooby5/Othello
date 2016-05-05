package ija.ija2015.othello.game;

import java.util.Stack;

/**
 * Created by kader on 05.05.2016.
 */
public class CommandManager
{
    protected Stack<PutCommand> commands;

    public CommandManager()
    {
        this.commands = new Stack<>();
    }

    public boolean Execute(PutCommand command)
    {
        boolean success = command.Execute();

        commands.push(command);

        return success;
    }

    public void Undo()
    {
        if (commands.isEmpty())
        {
            return;
        }

        PutCommand command = commands.pop();

        command.getField().removeDisk();
        command.getTurnedDisks().forEach(disk -> disk.turn());
    }

    public Stack<PutCommand> getCommands()
    {
        return commands;
    }
}
