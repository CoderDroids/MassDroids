package com.mygdx.game.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.mygdx.game.base.ActorBeta;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Map.Tile;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map extends ActorBeta {

    int mapWidth = 20; //number of tiles to each axis
    int mapHeight = 40;
    int cameraX =0; //camera coordinates
    int cameraY =0;
    int mapScale = 4; //visual zoom feature


    FrameBuffer m_fbo;
    Texture loadedTexture;
    Array<TextureRegion> textureArray;
    SpriteBatch batch;
    static int WIDTH = Gdx.graphics.getWidth();
    static int HEIGHT = Gdx.graphics.getHeight();
    Array<Tile> mapTiles = new Array<Tile>();

    public Map(Stage s) {
        super(0, 0, s);
        loadedTexture = new Texture(Gdx.files.internal("tiles.png"));
        loadedTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);
        textureArray = new Array<TextureRegion>();
        for (int i =0;i<112;i++) {
            textureArray.add(new TextureRegion(loadedTexture, (i%8)*16, (i/8) *16, 16, 16));
        }

        //exapmple usage
        for (int i =0;i<mapWidth*mapHeight;i++)
        {
            Tile testTile = new Tile();
            testTile.tileType = 36;
            mapTiles.add(testTile);
        }
        mapTiles.get(42).tileType = 5;
        // end example

        batch =  new SpriteBatch();
        m_fbo = new FrameBuffer(Pixmap.Format.RGB565, (int)(16* mapWidth*mapScale), (int)(16* mapHeight*mapScale), false);
        m_fbo.begin();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        for (int i =0;i<mapWidth*mapHeight;i++)
        {
            batch.draw(textureArray.get(36), i%mapWidth *16*mapScale,i/mapWidth *16*mapScale,0, 0, 16,16,mapScale,mapScale,180);
        }
        for (int i =0;i<mapWidth*mapHeight;i++)
        {
            batch.draw(textureArray.get(mapTiles.get(i).tileType), i%mapWidth *16*mapScale,i/mapWidth *16*mapScale,0, 0, 16,16,mapScale,mapScale,180);
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
            batch.draw(textureArray.get(36), i%mapWidth *16*mapScale,i/mapWidth *16*mapScale,0, 0, 16,16,mapScale,mapScale,180);
        }
        for (int i =0;i<mapWidth*mapHeight;i++)
        {
            batch.draw(textureArray.get(mapTiles.get(i).tileType), i%mapWidth *16*mapScale,i/mapWidth *16*mapScale,0, 0, 16,16,mapScale,mapScale,180);
        }
        batch.end();
        m_fbo.end();

    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        // temporary to show scrolling
        cameraX++;
        //end example
        batch.draw(m_fbo.getColorBufferTexture(), 0, 0,cameraX,cameraY,WIDTH,HEIGHT);
    }
}
