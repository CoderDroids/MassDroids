package com.CoderDroids.MassDroids;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.CoderDroids.MassDroids.base.GameBeta;
import com.CoderDroids.MassDroids.screen.SplashScreen;
import com.badlogic.gdx.audio.Music;

public class MyGame extends GameBeta {

    SplashScreen splashScreen;

    public static float SCREEN_WIDTH;
    public static float SCREEN_HEIGHT;

    public static Music backgroundMusic;

    @Override
    public void create(){
        super.create();

        SCREEN_WIDTH = Gdx.graphics.getWidth();
        SCREEN_HEIGHT = Gdx.graphics.getHeight();
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        splashScreen = new SplashScreen(this);
        setActiveScreen( splashScreen );
    }

}
