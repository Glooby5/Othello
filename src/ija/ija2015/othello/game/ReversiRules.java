/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.othello.game;

import ija.ija2015.othello.board.*;

/**
 * Reprezentuje herní pravidle hry Reversi
 */
public class ReversiRules implements Rules {

    private int size;
    private int disks;

    /**
     * Inicializace pravidel.
     * @param size
     */
    public ReversiRules(int size)
    {
        this.size = size;
        this.disks = this.size * this.size / 2;
    }


    /**
     * Vytvoří odpovídající pole na zadaných indexech.
     * @param row
     * @param col
     * @return
     */
    public Field createField(int row, int col)
    {
        return new ReversiField(row, col);
    }

    /**
     * Vrací velikost desky.
     * @return
     */
    public int getSize()
    {
        return this.size;
    }

    /**
     * Vrací počet kamenů jednotlivých hráčů.
     * @return
     */
    public int numberDisks()
    {
        return this.disks;
    }
}
