package com.CoderDroids.MassDroids.game;

import java.util.ArrayList;
import java.util.List;
import com.CoderDroids.MassDroids.game.GameType.Troop;
import com.CoderDroids.MassDroids.game.GameType.Currency;


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

    public boolean trainTroop(Troop troopType, int amount, Currency costType )
    {
        boolean purchased = false;
        if( troopType == Troop.Attacker )
        {
            if( costType == Currency.Buildium )
            {
                if( player.buildium >= gameData.costTrainAttackerWithBuildium * amount )
                {
                    player.buildium -= gameData.costTrainAttackerWithBuildium * amount;
                    purchased = true;
                }
            }
            else if( costType == Currency.Gold )
            {
                if( player.gold >= gameData.costTrainAttackerWithGold * amount )
                {
                    player.gold -= gameData.costTrainAttackerWithGold * amount;
                    purchased = true;
                }
            }

            if( purchased )
            {
                player.numAttackers += amount;
            }
        }
        else if( troopType == Troop.Defender )
        {
            if( costType == Currency.Buildium )
            {
                if( player.buildium >= gameData.costTrainDefenderWithBuildium * amount )
                {
                    player.buildium -= gameData.costTrainDefenderWithBuildium * amount;
                    purchased = true;
                }
            }
            else if( costType == Currency.Gold )
            {
                if( player.gold >= gameData.costTrainDefenderWithGold * amount )
                {
                    player.gold -= gameData.costTrainDefenderWithGold * amount;
                    purchased = true;
                }
            }

            if( purchased )
            {
                player.numDefenders += amount;
            }
        }
        return purchased;
    }



}
