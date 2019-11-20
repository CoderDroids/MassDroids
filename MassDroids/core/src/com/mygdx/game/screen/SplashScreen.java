package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.MyGame;
import com.mygdx.game.base.GameBeta;
import com.mygdx.game.base.ScreenBeta;
import com.mygdx.game.screen.MainMenuScreen;

import static com.mygdx.game.MyGame.SCREEN_WIDTH;
import static com.mygdx.game.MyGame.SCREEN_HEIGHT;

public class SplashScreen extends ScreenBeta {

    public SplashScreen(GameBeta game )
    {
        super(game);
    }

    @Override
    public void initialize()
    {
        Skin skin = new Skin(Gdx.files.internal("skins/quantum-horizon/skin/quantum-horizon-ui.json"));

        float btnWidth = SCREEN_WIDTH / 4;
        float btnHeight = SCREEN_HEIGHT / 4;


        Image backgroundColor = new Image(new Texture("blackBackground.jpg"));
        backgroundColor.setPosition(-300,0);
        backgroundColor.setScale(30.0f);
        uiStage.addActor(backgroundColor);


        Image playerInfo = new Image(new Texture("GameCover.jpg"));
        playerInfo.setPosition(-300,150);
        playerInfo.setScale(2.0f);
        uiStage.addActor(playerInfo);

        Label titleText = new Label("MASS DROIDS", skin);
        titleText.setPosition( MyGame.SCREEN_WIDTH * 0.45f, MyGame.SCREEN_HEIGHT * 0.7f);
        titleText.setAlignment( Align.center );
        titleText.setFontScale(8, 8);

        uiStage.addActor(titleText);


        Label infoText = new Label("Touch to start", skin);
        infoText.setPosition( MyGame.SCREEN_WIDTH * 0.45f, MyGame.SCREEN_HEIGHT * 0.3f);
        infoText.setAlignment( Align.center );
        infoText.setFontScale(4, 4);

        uiStage.addActor(infoText);

    }

    @Override
    public void update(float dt) {
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        mainGame.setScreen( new MainMenuScreen(mainGame) );
        return true;
    }
}
