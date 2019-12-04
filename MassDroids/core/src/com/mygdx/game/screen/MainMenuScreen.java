package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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


        Image backgroundColor = new Image(new Texture("blackBackground.jpg"));
        backgroundColor.setPosition(-300,0);
        backgroundColor.setScale(30.0f);
        uiStage.addActor(backgroundColor);

        Image playerInfo = new Image(new Texture("GameCover.jpg"));
        playerInfo.setPosition(-300,150);
        playerInfo.setScale(2.0f);
        uiStage.addActor(playerInfo);


        Label titleText = new Label("Mass Droids", skin);
        titleText.setPosition( MyGame.SCREEN_WIDTH * 0.4f, MyGame.SCREEN_HEIGHT * 0.7f);
        titleText.setAlignment( Align.center );
        titleText.setFontScale(5, 5);
        uiStage.addActor(titleText);

        float btnWidth = MyGame.SCREEN_WIDTH / 2;
        float btnHeight = MyGame.SCREEN_HEIGHT/ 8;

        TextButton playButton = new TextButton("Play", skin);
        playButton.setPosition( MyGame.SCREEN_WIDTH * 0.3f, MyGame.SCREEN_HEIGHT * 0.4f  );
        playButton.setSize(btnWidth, btnHeight );
        playButton.getLabel().setFontScale(3, 3 );
        playButton.addListener(new ClickListener(){
               @Override
               public void clicked(InputEvent event, float x, float y) {
                   mainGame.setScreen( new GameplayScreen2(mainGame) );
               };
           }
        );
        uiStage.addActor(playButton);

        TextButton optionButton = new TextButton("Options", skin);
        optionButton.setPosition( MyGame.SCREEN_WIDTH * 0.3f, MyGame.SCREEN_HEIGHT * 0.25f  );
        optionButton.setSize(btnWidth, btnHeight );
        optionButton.getLabel().setFontScale(3, 3 );
        optionButton.addListener(new ClickListener(){
               @Override
               public void clicked(InputEvent event, float x, float y) {
                   mainGame.setScreen( new OptionMenuScreen(mainGame) );
               };
           }
        );
        uiStage.addActor(optionButton);

        TextButton aboutButton = new TextButton("About Us", skin);
        aboutButton.setPosition( MyGame.SCREEN_WIDTH * 0.3f, MyGame.SCREEN_HEIGHT * 0.10f  );
        aboutButton.setSize(btnWidth, btnHeight );
        aboutButton.getLabel().setFontScale(3, 3 );
        aboutButton.addListener(new ClickListener(){
               @Override
               public void clicked(InputEvent event, float x, float y) {
                   mainGame.setScreen( new AboutScreen(mainGame) );
               };
           }
        );
        uiStage.addActor(aboutButton);

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
