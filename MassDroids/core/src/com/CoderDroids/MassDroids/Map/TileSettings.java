package com.CoderDroids.MassDroids.Map;
import com.badlogic.gdx.utils.Array;
import com.CoderDroids.MassDroids.Map.Tile;

public class TileSettings
{

    public Array<Tile> tilesMaster = new Array<Tile>();

    TileSettings()
    {
        for (int i =0;i<112;i++) {
            Tile defaultTile = new Tile();
            defaultTile.tileType = i;
            defaultTile.playerThatOwns = 0;
            defaultTile.buildium = 0;
            defaultTile.gold = 0;
            defaultTile.attackers =0;
            defaultTile.defenders =0;
            defaultTile.defensiveValue =0;
            tilesMaster.add(defaultTile);
        }

        setTile(36,12,3,0,0,0,0); //grass
        setTile(2,50,20,2,3,0,0); //mountain
        setTile(6,10,50,0,0,0,0); //lake
        setTile(0,50,20,3,5,1,1); //player base
        setTile(5,50,20,3,5,1,2); //player base

        setTile(46,0,0,-1,0,0,0); //river
        setTile(45,0,0,-1,0,0,0); //river
        setTile(51,0,0,-1,0,0,0); //river
        setTile(78,0,0,-1,0,0,0); //river
        setTile(79,0,0,-1,0,0,0); //river
        setTile(85,0,0,-1,0,0,0); //river
        setTile(83,0,0,-1,0,0,0); //river
        setTile(44,0,0,-1,0,0,0); //river

        setTile(104,0,20,2,0,0,0); //trees
    }

    private void setTile(int number, int buildium, int gold, int defensiveValue , int defenders , int attackers, int playerThatOwns)
    {
        tilesMaster.get(number).buildium = buildium;
        tilesMaster.get(number).gold = gold;
        tilesMaster.get(number).defensiveValue = defensiveValue;
        tilesMaster.get(number).attackers = attackers;
        tilesMaster.get(number).defenders = defenders;
        tilesMaster.get(number).playerThatOwns = playerThatOwns;
    }

}
