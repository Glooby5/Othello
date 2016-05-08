/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.othello.board;

/**
 * Hrací deska.
 *
 * @author XKADER13, XZEMAN53
 */
public class Board {
    private Rules rules;
    private Field fieldArray[][];

    /**
     * Inicializuje desku. Vytvoří a uloží si všechna pole. Pro všechna pole nastaví jejich okolí.
     * Na neaktivních polích jsou umístěny instance třídy BorderField.
     * Objekty aktivních polí jsou získány metodou Rules.createField(int, int).
     * @param rules
     */
    public Board(Rules rules)
    {
        this.fieldArray = new Field[rules.getSize() + 2][rules.getSize() + 2];
        this.rules = rules;

        addFields(rules.getSize() + 2);
        setDirections(rules.getSize());
    }

    /**
     * Přidá všechny hrací pole
     * @param size
     */
    private void addFields(int size)
    {
        for (int i = 1; i < size; i++)
        {
            for (int j = 1; j < size; j++)
            {
                this.fieldArray[i][j] = this.rules.createField(i, j);
            }

            this.fieldArray[i][0] = new BorderField();
            this.fieldArray[i][size - 1] = new BorderField();
        }

        for (int i = 0; i < size; i++)
        {
            this.fieldArray[0][i] = new BorderField();
            this.fieldArray[size - 1][i] = new BorderField();
        }
    }

    /**
     * Nastaví hraícm polí jejich okolí
     *
     * @param size velikost
     */
    private void setDirections(int size)
    {
        for (int i = 1; i <= size; i++) // řádky
        {
            for (int j = 1; j <= size; j++) // sloupce
            {
                this.fieldArray[i][j].addNextField(Field.Direction.R, this.fieldArray[i][j + 1]);
                this.fieldArray[i][j].addNextField(Field.Direction.L, this.fieldArray[i][j - 1]);
                this.fieldArray[i][j].addNextField(Field.Direction.U, this.fieldArray[i - 1][j]);
                this.fieldArray[i][j].addNextField(Field.Direction.D, this.fieldArray[i + 1][j]);
                this.fieldArray[i][j].addNextField(Field.Direction.LU, this.fieldArray[i - 1][j - 1]);
                this.fieldArray[i][j].addNextField(Field.Direction.LD, this.fieldArray[i + 1][j - 1]);
                this.fieldArray[i][j].addNextField(Field.Direction.RU, this.fieldArray[i - 1][j + 1]);
                this.fieldArray[i][j].addNextField(Field.Direction.RD, this.fieldArray[i + 1][j + 1]);
            }
        }
    }

    public Field getField(int row, int col)
    {
        return this.fieldArray[row][col];
    }
    /**
     * Vrací objekt pravidel.
     * @return
     */
    public Rules getRules()
    {
        return this.rules;
    }

    /**
     * Vrací velikost (rozměr) desky.
     * @return
     */
    public int getSize()
    {
        return this.rules.getSize();
    }

    @Override
    public String toString()
    {
        String boardVisual = "   ";

        for(int i = 1; i <= getSize(); i++)
            boardVisual += String.format(" %1$d ", i);

        boardVisual += "\n   ";

        for(int i = 1; i <= getSize(); i++)
            boardVisual += " _ ";

        boardVisual += "\n ";

        for(int r = 1; r < getSize() + 1; r++)
        {
            boardVisual += String.format("%1$d|", r);
            for(int c = 1; c < getSize() + 1; c++)
            {
                if (this.getField(r, c).isEmpty())
                    boardVisual += "   ";
                else
                    boardVisual += String.format(" %1$s ", this.getField(r, c).toString());
            }

            boardVisual += String.format(" |%1$d\n ", r);
        }

        boardVisual += "  ";

        for(int i = 1; i <= getSize(); i++)
            boardVisual += " _ ";

        boardVisual += "\n";

        return boardVisual;
    }
}
