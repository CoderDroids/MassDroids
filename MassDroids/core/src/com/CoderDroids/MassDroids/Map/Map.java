package com.CoderDroids.MassDroids.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Matrix4;
import com.CoderDroids.MassDroids.base.ActorBeta;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.CoderDroids.MassDroids.Map.Tile;
import com.CoderDroids.MassDroids.Map.TileSettings;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.MathUtils;

import java.util.Random;


public class Map extends ActorBeta {

    static int WIDTH = Gdx.graphics.getWidth();
    static int HEIGHT = Gdx.graphics.getHeight();

    int mapWidth = 15; //number of tiles to each axis
    int mapHeight = 20;
    int cameraX =0; //camera coordinates
    int cameraY =0;

    int mapScale = MathUtils.ceil(((WIDTH/1080.0f)>(HEIGHT/1920.0f)) ? (WIDTH/1080.0f) *7 : (HEIGHT/1920.0f)*7); //visual zoom feature


    FrameBuffer m_fbo;
    Texture loadedTexture;
    Array<TextureRegion> textureArray;
    SpriteBatch batch;
    public Array<Tile> mapTiles = new Array<Tile>();
    TileSettings tileMasterList = new TileSettings();

    public Map(Stage s) {
        super(0, 0, s);

        loadedTexture = new Texture(Gdx.files.internal("tiles.png"));
        loadedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);
        textureArray = new Array<TextureRegion>();
        for (int i =0;i<112;i++) {
            textureArray.add(new TextureRegion(loadedTexture, (i%8)*16, (i/8) *16, 16, 16));
        }

        for (int i =0;i<mapWidth*mapHeight;i++)
        {
            mapTiles.add(new Tile());
        }
        for (int i =0;i<mapWidth*mapHeight;i++)
        {
            addTile(mapTiles.get(i),36);
        }
        addTile(getTile2D(2,2),5);  //player 1 base
        addTile(getTile2D(12,17),0);//player 2 base
        addTile(getTile2D(7,4),7);  //player 3 base
        addTile(getTile2D(2,11),26);//player 4 base
        addTile(getTile2D(11,9),28);//player 5 base
        addTile(getTile2D(7,14),30);//player 6 base
        addTile(getTile2D(4,7),32);//player 7 base

        addTile(getTile2D(7,10),6);// lake
        addTile(getTile2D(5,5),2);// mountain
        addTile(getTile2D(10,15),2);// mountain


        addTile(getTile2D(0,8),46);// river
        for(int i =1;i<10;i++)
        {
            addTile(getTile2D(i,8),45);
        }
        for(int i =5;i<14;i++)
        {
            addTile(getTile2D(i,12),45);
        }
        for(int i =9;i<12;i++)
        {
            addTile(getTile2D(9,i),51);
            addTile(getTile2D(5,i),51);
        }


        addTile(getTile2D(9,12),78);
        addTile(getTile2D(5,8),79);
        addTile(getTile2D(9,8),85);
        addTile(getTile2D(5,12),83);

        addTile(getTile2D(14,12),44);
        //river


        for(int i =0;i<16;i++) {
            addTile(getTile2D(10 + i%4, 2+i/4), 104);
        }

        for(int i =0;i<16;i++) {
            addTile(getTile2D(2 + i%4, 15+i/4), 104);
        }

        batch =  new SpriteBatch();
        m_fbo = new FrameBuffer(Pixmap.Format.RGB565, (int)(16* mapWidth*mapScale), (int)(16* mapHeight*mapScale), false);
        redrawMap();
    }

    public void redrawMap()
    {
        m_fbo.begin();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        Matrix4 matrix = new Matrix4();
        matrix.setToOrtho2D(0, 0,  (int)(16* mapWidth*mapScale),(int)(16* mapHeight*mapScale)); // here is the actual size you want
        batch.setProjectionMatrix(matrix);
        for (int i =0;i<mapWidth*mapHeight;i++)
        {
            batch.draw(textureArray.get(35), ((i%mapWidth)+1) *16*mapScale,((i/mapWidth)+1) *16*mapScale,0, 0, 16,16,mapScale,mapScale,180);
        }
        for (int i =0;i<mapWidth*mapHeight;i++)
        {
            batch.draw(textureArray.get(mapTiles.get(i).tileType), ((i%mapWidth)+1) *16*mapScale,((i/mapWidth)+1) *16*mapScale,0, 0, 16,16,mapScale,mapScale,180);
        }
        batch.end();
        m_fbo.end();

    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        //batch.draw(m_fbo.getColorBufferTexture(), 0, 0,0,0,WIDTH,HEIGHT);
        batch.draw(m_fbo.getColorBufferTexture(),0, 0 ,WIDTH , HEIGHT, cameraX, cameraY, WIDTH, HEIGHT,false,false);
    }

    @Override
    public void act(float dt) {
        // temporary to show scrolling
        //cameraX++;
        //end example
    }

    public Tile screenSpaceCoordinatesToTile(int x,int y) //give x,y coordiantes on screen returns the tile that was touched
    {
        x += cameraX;
        y += cameraY;
        x = x/(16*mapScale);
        y = y/(16*mapScale);
        return getTile2D(x,y);
    }

    public Tile getTile2D(int x,int y) //converts 1D tile array into a 2D array
    {
        return mapTiles.get(mapWidth * y + x);
    }

    public void scrollCamera(int x,int y)
    {
        cameraX = MathUtils.clamp(cameraX +x,0,(int)(16* mapWidth*mapScale)-WIDTH);
        cameraY = MathUtils.clamp(cameraY +y,0,(int)(16* mapHeight*mapScale)-HEIGHT);
    }

    public void setCamera(int x,int y)
    {
        cameraX = MathUtils.clamp(x,0,(int)(16* mapWidth*mapScale)-WIDTH);
        cameraY = MathUtils.clamp(y,0,(int)(16* mapHeight*mapScale)-HEIGHT);
    }

    private void addTile(Tile mapTile, int tileNumber)
    {
        Tile masterTile = tileMasterList.tilesMaster.get(tileNumber);
        mapTile.tileType = masterTile.tileType;
        mapTile.playerThatOwns = masterTile.playerThatOwns;
        mapTile.buildium = masterTile.buildium;
        mapTile.gold = masterTile.gold;
        mapTile.attackers =masterTile.attackers;
        mapTile.defenders =masterTile.defenders;
        mapTile.defensiveValue =masterTile.defensiveValue;
    }
}
