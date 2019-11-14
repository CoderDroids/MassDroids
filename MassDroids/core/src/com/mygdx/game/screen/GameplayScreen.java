package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGame;
import com.mygdx.game.base.ActorBeta;
import com.mygdx.game.base.GameBeta;
import com.mygdx.game.base.ScreenBeta;

import static com.mygdx.game.MyGame.SCREEN_HEIGHT;
import static com.mygdx.game.MyGame.SCREEN_WIDTH;

public class GameplayScreen extends ScreenBeta {

    Touchpad touchPad;

    public boolean isGameStarted;
    public boolean isGameEnded;

    final float btnWidth = 100;
    final float btnHeight = 100;

    final float padWidth = 150;
    final float padHeight = 150;

    public GameplayScreen(GameBeta game )
    {
        super(game);
    }

    @Override
    public void initialize()
    {
        ActorBeta.setWorldBounds(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        startGame();
    }

    @Override
    public void update(float dt)
    {
    }

    void onTouchpadMoved( float dx, float dy )
    {

    }

    @Override
    public void dispose()
    {
        super.dispose();
    }

    public void startGame()
    {
        Skin arcadeSkin = new Skin(Gdx.files.internal("skins/arcade/skin/arcade-ui.json"));
        Skin touchPadSkin = new Skin(Gdx.files.internal("skins/shade/skin/uiskin.json"));

        touchPad = new Touchpad(10, touchPadSkin);
        touchPad.setPosition( SCREEN_WIDTH * 0.05f, SCREEN_HEIGHT * 0.15f );
        touchPad.setSize( padWidth * 2.0f, padHeight * 2.0f );
        uiStage.addActor(touchPad);

        Button pauseButton = new Button(arcadeSkin);
        pauseButton.setPosition(MyGame.SCREEN_WIDTH - btnWidth, btnHeight * 1.5f);
        pauseButton.setSize(btnWidth * 0.5f, btnHeight * 0.5f );
        pauseButton.addListener(new ClickListener(){
                                    @Override
                                    public void clicked(InputEvent event, float x, float y) {
                                        mainGame.setScreen( new PauseMenuScreen(mainGame) );
                                    };
                                }
        );
        uiStage.addActor(pauseButton);

    }

}
