/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.othello.board;

/**
 *
 * @author XKADER13, XZEMAN53
 */
public class Board {
    
    private int size;
    private int borderLength;
    private Field[] board;
    private Rules Rules;
    
    public Board(Rules rules) 
    {
        this.Rules = rules;
        this.size = rules.getSize();
        this.borderLength = size+2;
        this.initBoard();
    }
    
    @Override
    public String toString()
    {
        String boardVisual = "";
        
        for(int i = 1; i <= size; i++)
            boardVisual += String.format("%1$d|", i);
        
        boardVisual += "\n";
        
        for(int i = 1; i <= size; i++)
            boardVisual += " _ ";
        
        boardVisual += "\n";
        
        for(int r = 0; r < borderLength; r++){
            for(int c = 0; c < borderLength; c++)
                boardVisual += String.format("|%1$s|", this.getField(r, c).toString());
            boardVisual += String.format("%1$d\n|", r+1);
        }
        
        for(int i = 1; i <= size; i++)
            boardVisual += " _ ";
        
        return boardVisual;
    }
    
    protected void initBoard()
    {
        this.board = new Field[borderLength*borderLength];

        for(int row = 0; row < borderLength; row++)
        {
            for(int col = 0; col < borderLength; col++)
            {   
                if (row == 0 || row == borderLength || col == 0 || (col == borderLength-1))
                    board[row*borderLength+col] = new BorderField();
                else
                    board[row*borderLength+col] = this.Rules.createField(row, col);
            }
        }
        
        for(int i = 0; i < board.length; i++)
            setSurrFields(board[i], i/borderLength, i%borderLength, Field.Direction.D);
    }
    
    protected void setSurrFields(Field field, int row, int col, Field.Direction dirs)
    {
        int[] rows = new int[8];
        int[] cols = new int[8];
        rows[0] = row+1;//D
        cols[0] = col;//D
        rows[1] = row;//L
        cols[1] = col-1;//L
        rows[2] = row+1;//LD
        cols[2] = col-1;//LD
        rows[3] = row-1;//LU
        cols[3] = col-1;//LU
        rows[4] = row;//R
        cols[4] = col+1;//R
        rows[5] = row+1;//RD
        cols[5] = col+1;//RD
        rows[6] = row-1;//RU
        cols[6] = col+1;//RU
        rows[7] = row+1;//U
        cols[7] = col;//U

        for(int i = 0; i < rows.length; i++)
        {
            row = rows[i];
            col = cols[i];
            
            if ((row > 0 && row < borderLength) && (col > 0 && col < borderLength))
                field.addNextField(Field.Direction.values()[i], board[row*borderLength+col]);
        }
    }
    
    //Vrátí pole umístěné na uvedených indexech row a col.
    public Field getField(int row, int col)
    {
        if(row < size && row >= 0 && col < size && col >= 0)
            return board[row*borderLength+col];
        else
            return null;
    }
    
    //Vrací objekt pravidel.
    public Rules getRules()
    {
        return Rules;
    }

    public int	getSize()
    {
        return this.size;
    }

}
