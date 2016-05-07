package ija.ija2015.othello.game;

import java.util.Stack;

/**
 * Zajišťuje režii pro tahy hráčů. Má kontrolu nad tím jaké tahy byly provedeny.
 */
public class CommandManager
{
    protected Stack<PutCommand> commands;

    public CommandManager()
    {
        this.commands = new Stack<>();
    }

    /**
     * Vykoná zadaný příkaz a vloží jej na zásobník.
     * @param command Příkaz, který se má provést
     * @return True pokud to bylo možné, jinak False
     */
    public boolean Execute(PutCommand command)
    {
        boolean success = command.Execute();

        commands.push(command);

        return success;
    }

    /**
     * Vrátí zpět předchozí tah.
     */
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
