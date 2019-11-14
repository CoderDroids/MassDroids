package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.base.GameBeta;
import com.mygdx.game.screen.SplashScreen;

public class MyGame extends GameBeta {

    SplashScreen splashScreen;

    public static float SCREEN_WIDTH;
    public static float SCREEN_HEIGHT;


    @Override
    public void create(){
        super.create();

        SCREEN_WIDTH = Gdx.graphics.getWidth();
        SCREEN_HEIGHT = Gdx.graphics.getHeight();

        splashScreen = new SplashScreen(this);
        setActiveScreen( splashScreen );
    }

}
