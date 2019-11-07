package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.base.GameBeta;

public class MyGame extends GameBeta {

    StartScreen startScreen;

    public static float SCREEN_WIDTH;
    public static float SCREEN_HEIGHT;


    @Override
    public void create(){
        super.create();

        SCREEN_WIDTH = Gdx.graphics.getWidth();
        SCREEN_HEIGHT = Gdx.graphics.getHeight();

        startScreen = new StartScreen(this);
        setActiveScreen( startScreen );
    }

}
