package com.CoderDroids.MassDroids.game;

public class PlayerInfo {
    public int gold;
    public int buildium;
    public int numAttackers;
    public int numDefenders;
    public int attackPower;
    public int defensePower;
    public int earningGoldPerTurn;
    public int earningBuildiumPerTurn;

    public PlayerInfo()
    {
        gold = 100;
        buildium = 50;
        numAttackers = 0;
        numDefenders = 0;
        attackPower = 0;
        defensePower = 0;
        earningGoldPerTurn = 10;
        earningBuildiumPerTurn = 5;
    }
}
