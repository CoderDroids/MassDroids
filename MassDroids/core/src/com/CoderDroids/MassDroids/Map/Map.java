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
import com.badlogic.gdx.math.MathUtils;

import java.util.Random;


public class Map extends ActorBeta {

    int mapWidth = 15; //number of tiles to each axis
    int mapHeight = 20;
    int cameraX =0; //camera coordinates
    int cameraY =0;
    int mapScale = 7; //visual zoom feature


    FrameBuffer m_fbo;
    Texture loadedTexture;
    Array<TextureRegion> textureArray;
    SpriteBatch batch;
    static int WIDTH = Gdx.graphics.getWidth();
    static int HEIGHT = Gdx.graphics.getHeight();
    public Array<Tile> mapTiles = new Array<Tile>();

    public Map(Stage s) {
        super(0, 0, s);
        loadedTexture = new Texture(Gdx.files.internal("tiles.png"));
        loadedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);
        textureArray = new Array<TextureRegion>();
        for (int i =0;i<112;i++) {
            textureArray.add(new TextureRegion(loadedTexture, (i%8)*16, (i/8) *16, 16, 16));
        }

        //exapmple usage
        //note i will set all the values per tile type so the only thing that needs to be set here is the type
        //it should be fine to randomly select from tiles to build map
        for (int i =0;i<mapWidth*mapHeight;i++)
        {
            Tile testTile = new Tile();
            testTile.tileType = 36;
            testTile.playerThatOwns = 0;
            testTile.buildium = 12;
            testTile.gold = 3;
            testTile.attackers =0;
            testTile.defenders =0;
            testTile.defensiveValue =0;
            mapTiles.add(testTile);
        }
        getTile2D(2,2).tileType = 5;
        getTile2D(2,2).playerThatOwns = 0;
        getTile2D(2,2).gold = 10;
        getTile2D(2,2).buildium = 50;
        getTile2D(2,2).defenders = 5;
        getTile2D(2,2).attackers = 1;
        getTile2D(2,2).defensiveValue = 2;

        for( int i = 0; i < 5; i++ )
        {
            Random rnd = new Random();
            int x = rnd.nextInt(10) + 3;
            int y = rnd.nextInt(15) + 3;
            getTile2D(x,y).tileType = 0;
            getTile2D(x,y).playerThatOwns = i + 1;
            getTile2D(x,y).gold = 10;
            getTile2D(x,y).buildium = 50;
            getTile2D(x,y).defenders = 5;
            getTile2D(x,y).attackers = 1;
            getTile2D(x,y).defensiveValue = 2;
        }



        //mapTiles.get(4).tileType = 0;

        // end example

        batch =  new SpriteBatch();
        m_fbo = new FrameBuffer(Pixmap.Format.RGB565, (int)(16* mapWidth*mapScale), (int)(16* mapHeight*mapScale), false);
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

    public void redrawMap()
    {
        m_fbo.begin();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
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
}