package com.CoderDroids.MassDroids.game;

public class GameplayManager {

    //region Singleton
    private GameplayManager()
    {
        turn = 0;
    }
    private static GameplayManager instance = null;
    public static GameplayManager getInstance()
    {
        if( instance == null )
            instance = new GameplayManager();

        return instance;
    }
    //endregion

    public int turn;

    public void takeTurn()
    {
        turn += 1;
    }



}
