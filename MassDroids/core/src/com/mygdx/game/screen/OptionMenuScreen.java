package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGame;
import com.mygdx.game.base.ScreenBeta;
import com.mygdx.game.base.GameBeta;

public class OptionMenuScreen extends ScreenBeta {

    final float btnWidth = 300;
    final float btnHeight = 50;

    public OptionMenuScreen(GameBeta game )
    {
        super(game);
    }

    @Override
    public void initialize()
    {
        Skin skin = new Skin(Gdx.files.internal("skins/freezing/skin/freezing-ui.json"));

        TextButton resumeButton = new TextButton("Main Menu", skin);
        resumeButton.setPosition( MyGame.SCREEN_WIDTH / 2, MyGame.SCREEN_HEIGHT * 0.5f + btnHeight * 0.5f  );
        resumeButton.setSize(btnWidth, btnHeight );
        resumeButton.getLabel().setFontScale(2, 2 );
        resumeButton.addListener(new ClickListener(){
                                   @Override
                                   public void clicked(InputEvent event, float x, float y) {
                   mainGame.setScreen( new MainMenuScreen(mainGame) );
               };
           }
        );
        uiStage.addActor(resumeButton);
    }

    @Override
    public void update(float dt)
    {}
}
