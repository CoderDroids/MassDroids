package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.MyGame;
import com.mygdx.game.base.GameBeta;
import com.mygdx.game.base.ScreenBeta;

public class MainMenuScreen extends ScreenBeta {

    public MainMenuScreen(GameBeta game )
    {
        super(game);
    }


    @Override
    public void initialize()
    {
        Skin skin = new Skin(Gdx.files.internal("skins/star-soldier/skin/star-soldier-ui.json"));

        float btnWidth = MyGame.SCREEN_WIDTH / 4;
        float btnHeight = MyGame.SCREEN_HEIGHT/ 8;

        TextButton playButton = new TextButton("Play", skin);
        playButton.setPosition( MyGame.SCREEN_WIDTH * 0.7f, MyGame.SCREEN_HEIGHT * 0.4f  );
        playButton.setSize(btnWidth, btnHeight );
        playButton.getLabel().setFontScale(3, 3 );
        playButton.addListener(new ClickListener(){
               @Override
               public void clicked(InputEvent event, float x, float y) {
                   mainGame.setScreen( new GameplayScreen(mainGame) );
               };
           }
        );
        uiStage.addActor(playButton);

        TextButton optionButton = new TextButton("Help", skin);
        optionButton.setPosition( MyGame.SCREEN_WIDTH * 0.7f, MyGame.SCREEN_HEIGHT * 0.25f  );
        optionButton.setSize(btnWidth, btnHeight );
        optionButton.getLabel().setFontScale(3, 3 );
        optionButton.addListener(new ClickListener(){
               @Override
               public void clicked(InputEvent event, float x, float y) {
                   mainGame.setScreen( new HelpScreen(mainGame) );
               };
           }
        );
        uiStage.addActor(optionButton);

        Label titleText = new Label("Mass Droids", skin);
        titleText.setPosition( MyGame.SCREEN_WIDTH * 0.5f, MyGame.SCREEN_HEIGHT * 0.7f);
        titleText.setAlignment( Align.center );
        titleText.setFontScale(8, 8);
        uiStage.addActor(titleText);

//        ParticleActor particle = new ParticleActor("effects/fire_splash.pfx", "effects");
//        particle.setPosition(SCREEN_WIDTH * 0.3f, SCREEN_HEIGHT * 0.5f );
//        particle.setScale( 20.0f, 20.0f );
//        particle.start();
//        mainStage.addActor(particle);
    }



    @Override
    public void update(float dt)
    {}

}
