package ija.ija2015.othello.game;

import ija.ija2015.othello.board.Board;
import ija.ija2015.othello.board.Disk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class DiskFreezing
{
    private Game game;
    private Random rand;
    private ActionListener freezeListener;

    private int timerInterval;
    private int freezeInterval;
    private int freezeCount;

    private boolean unfreeze;
    private boolean isFreeze;

    private Timer freezeTimer;
    private Timer unfreezeTimer;

    public DiskFreezing(Game game, int timerInterval, int freezeInterval, int freezeCount)
    {
        this.game = game;
        this.rand = new Random();

        this.timerInterval = timerInterval;
        this.freezeInterval = freezeInterval;
        this.freezeCount = freezeCount;

        this.freezeTimer = new Timer();
        this.unfreezeTimer = new Timer();
    }

    //region Public

    public void SetFreeze()
    {
        if (!unfreeze && isFreeze)
            return;

        if (isFreeze)
            UnfreezeAll();

        setFreezeTimer();
    }

    public void UnfreezeAll()
    {
        getDisks().forEach(disk -> disk.unfreeze());
    }

    //endregion

    //region Private

    private void freezeDisks()
    {
        ArrayList<Disk> disks = getDisks();

        freezeCount = randInt(0, freezeCount);
        freezeCount = freezeCount > disks.size() ? disks.size() : freezeCount;

        for (int i = 0; i < freezeCount; i++)
        {
            disks.get(i).freeze();
        }

        unfreeze = false;
        isFreeze = true;

        if (freezeListener != null)
            freezeListener.actionPerformed(new ActionEvent(this, 0, ""));
    }

    private void setUnfreezeTimer()
    {
        unfreezeTimer.cancel();
        unfreezeTimer = new Timer();
        unfreezeTimer.schedule(new TimerTask() {

            @Override
            public void run()
            {
                unfreeze = true;
            }

        }, randInt(0, freezeInterval) * 1000);
    }

    private void setFreezeTimer()
    {
        freezeTimer.cancel();
        freezeTimer = new Timer();
        freezeTimer.schedule(new TimerTask() {

            @Override
            public void run()
            {
                freezeDisks();
                setUnfreezeTimer();
            }

        }, randInt(0, timerInterval) * 1000);
    }

    private ArrayList<Disk> getDisks()
    {
        ArrayList<Disk> disks = new ArrayList<>();
        Board board = game.getBoard();

        for (int row = 1; row <= board.getSize(); row++)
        {
            for (int col = 1; col <= board.getSize(); col++)
            {
                if (!board.getField(row, col).isEmpty())
                    disks.add(board.getField(row, col).getDisk());
            }
        }

        return disks;
    }

    //endregion

    //region Getters/Setters

    public void setFreezeListener(ActionListener freezeListener)
    {
        this.freezeListener = freezeListener;
    }

    public int getTimerInterval()
    {
        return timerInterval;
    }

    public void setTimerInterval(int timerInterval)
    {
        this.timerInterval = timerInterval;
    }

    public int getFreezeInterval()
    {
        return freezeInterval;
    }

    public void setFreezeInterval(int freezeInterval)
    {
        this.freezeInterval = freezeInterval;
    }

    public int getFreezeCount()
    {
        return freezeCount;
    }

    public void setFreezeCount(int freezeCount)
    {
        this.freezeCount = freezeCount;
    }

    //endregion

    private int randInt(int min, int max)
    {
        return rand.nextInt((max - min) + 1) + min;
    }
}
