package ija.ija2015.othello.game;

import ija.ija2015.othello.board.Field;

/**
 * Interface pro reprezentaci nějakého herního algoritmu.
 *
 * @author XKADER13, XZEMAN53
 */
public interface AI
{
    /**
     * Provede výběr pole, kam AI určí, že chce hrát.
     * @return Pole
     */
    public Field Turn();
}
