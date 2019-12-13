package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGame;
import com.mygdx.game.base.GameBeta;
import com.mygdx.game.base.ScreenBeta;

public class PauseMenuScreen extends ScreenBeta {

    public PauseMenuScreen(GameBeta game )
    {
        super(game);
    }

    @Override
    public void initialize()
    {
        Skin skin = new Skin(Gdx.files.internal("skins/star-soldier/skin/star-soldier-ui.json"));

        float btnWidth = MyGame.SCREEN_WIDTH / 4;
        float btnHeight = MyGame.SCREEN_HEIGHT/ 8;

        TextButton resumeButton = new TextButton("Resume", skin);
        resumeButton.setPosition( MyGame.SCREEN_WIDTH / 2, MyGame.SCREEN_HEIGHT * 0.5f + btnHeight * 0.5f  );
        resumeButton.setSize(btnWidth, btnHeight );
        resumeButton.getLabel().setFontScale(3, 3 );
        resumeButton.addListener(new ClickListener(){
                 @Override
                 public void clicked(InputEvent event, float x, float y) {
                     mainGame.setScreen( new GameplayScreen(mainGame) );
                 };
             }
        );
        uiStage.addActor(resumeButton);

        TextButton quitButton = new TextButton("Quit", skin);
        quitButton.setPosition( MyGame.SCREEN_WIDTH / 2, MyGame.SCREEN_HEIGHT * 0.4f + btnHeight * 0.5f  );
        quitButton.setSize(btnWidth, btnHeight );
        quitButton.getLabel().setFontScale(2, 2 );
        quitButton.addListener(new ClickListener(){
                 @Override
                 public void clicked(InputEvent event, float x, float y) {
                     mainGame.setScreen( new MainMenuScreen(mainGame) );
                 };
             }
        );
        uiStage.addActor(quitButton);
    }

    @Override
    public void update(float dt)
    {}

}
