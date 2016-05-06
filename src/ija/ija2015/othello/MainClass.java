package ija.ija2015.othello;

import ija.ija2015.othello.controller.*;

public class MainClass
{
    public static void main(String[] args)
    {
        MenuController menu = new MenuController();
        menu.Show();
        /*
        GameController control = new GameController(12);
        control.Show();
        control.RunGame(GameController.PLAYER_USER, GameController.PLAYER_USER);*/
    }
}