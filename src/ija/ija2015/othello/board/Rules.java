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
public interface Rules {

    /**
     * Vytvoří odpovídající pole na zadaných indexech.
     * @param row
     * @param col
     * @return
     */
    public Field createField(int row, int col);

    /**
     * Vrací velikost desky.
     * @return
     */
    public int getSize();

    /**
     * Vrací počet kamenů jednotlivých hráčů
     * @return
     */
    public int numberDisks();
}
