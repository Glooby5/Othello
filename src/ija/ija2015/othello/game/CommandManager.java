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

    public void Execute(PutCommand command)
    {
        command.Execute();

        commands.push(command);
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
}
