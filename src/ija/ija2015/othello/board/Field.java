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
public interface Field {
    enum Direction {
        D,  //Dole (Down) od pole.
        L,  //Vlevo (Left) od pole.
        LD, //Vlevo dole po diagonále (Left-Down) od pole.
        LU, //Vlevo nahoru po diagonále (Left-Up) od pole.
        R,  //Vpravo (Right) od pole.
        RD, //Vpravo dole po diagonále (Right-Down) od pole.
        RU, //Vpravo nahoru po diagonále (Right-Up) od pole.
        U   //Nahoru (Up) od pole.}
    }


    /**
     * Přidá sousední pole field v daném směru dirs.
     */
    public void addNextField(Field.Direction dirs, Field field);

    /**
     * Test, zda je možné vložit na pole kámen.
     * @param disk
     * @return
     */
    public boolean canPutDisk(Disk disk);

    /**
     * Vrací kámen, který je vložen na pole.
     * @return
     */
    public Disk getDisk();
    
    public boolean isEmpty();

    /**
     * Vrátí sousední pole v daném směru dirs.
     * @param dirs
     * @return
     */
    public Field nextField(Field.Direction dirs);

    /**
     * Vloží na pole kámen.
     * @param disk
     * @return
     */
    public boolean putDisk(Disk disk);
}
