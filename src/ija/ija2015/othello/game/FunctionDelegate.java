package ija.ija2015.othello.game;

/**
 * Created by kader on 04.05.2016.
 */
public interface FunctionDelegate<T>
{
    public void Run(int row, int col, Object parent);

    public T Return();
}
