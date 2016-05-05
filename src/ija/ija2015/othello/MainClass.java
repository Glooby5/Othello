package ija.ija2015.othello;

import ija.ija2015.othello.controller.*;

import javax.swing.*;
import java.awt.*;

public class MainClass
{
    public static void main(String[] args)
    {
        GameController control = new GameController(12);
        control.Show();
        control.RunGame();
    }
}