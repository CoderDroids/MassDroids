package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.base.ActorBeta;
import com.mygdx.game.base.GameBeta;
import com.mygdx.game.base.ScreenBeta;

import java.awt.HeadlessException;

import static com.mygdx.game.MyGame.SCREEN_WIDTH;
import static com.mygdx.game.MyGame.SCREEN_HEIGHT;

public class StartScreen extends ScreenBeta {

    public StartScreen(GameBeta game )
    {
        super(game);
    }

    @Override
    public void initialize()
    {
        Skin skin = new Skin(Gdx.files.internal("skins/quantum-horizon/skin/quantum-horizon-ui.json"));

        float btnWidth = SCREEN_WIDTH / 4;
        float btnHeight = SCREEN_HEIGHT / 4;

        Label infoText = new Label("Touch to start", skin);
        infoText.setPosition( MyGame.SCREEN_WIDTH * 0.5f, MyGame.SCREEN_HEIGHT * 0.3f);
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
