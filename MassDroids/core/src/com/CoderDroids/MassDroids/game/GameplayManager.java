package com.CoderDroids.MassDroids.game;

import java.util.ArrayList;
import java.util.List;

public class GameplayManager {

    //region Singleton

    private static GameplayManager instance = null;
    public static GameplayManager getInstance()
    {
        if( instance == null )
            instance = new GameplayManager();

        return instance;
    }
    //endregion

    public int turn;
    public PlayerInfo player;
    public List<PlayerInfo> opponents;
    public GameInfo gameData;

    private GameplayManager()
    {
        turn = 0;
        player = new PlayerInfo();
        opponents = new ArrayList<PlayerInfo>();
        gameData = new GameInfo();
    }

    public void takeTurn()
    {
        turn += 1;

        player.gold += player.earningGoldPerTurn;
        player.buildium += player.earningBuildiumPerTurn;
    }



}
