package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.MyGame;
import com.mygdx.game.base.ScreenBeta;
import com.mygdx.game.base.GameBeta;

import javax.swing.GroupLayout;

public class OptionMenuScreen extends ScreenBeta {

    public OptionMenuScreen(GameBeta game )
    {
        super(game);
    }

    Preferences optionPrefs;

    @Override
    public void initialize()
    {
        Skin skin = new Skin(Gdx.files.internal("skins/star-soldier/skin/star-soldier-ui.json"));
        optionPrefs = Gdx.app.getPreferences("OptionPrefs");

        float btnWidth = MyGame.SCREEN_WIDTH * 0.4f;
        float btnHeight = MyGame.SCREEN_HEIGHT * 0.1f;


        Label menuText = new Label( "Options", skin );
        menuText.setPosition( MyGame.SCREEN_WIDTH * 0.25f, MyGame.SCREEN_HEIGHT * 0.8f  );
        menuText.setSize(MyGame.SCREEN_WIDTH * 0.5f, MyGame.SCREEN_HEIGHT * 0.1f );
        menuText.setAlignment( Align.center );
        menuText.setFontScale(8.0f);
        uiStage.addActor( menuText );

        Table table = new Table();
        //table.setFillParent(true);
        table.setPosition(MyGame.SCREEN_WIDTH * 0.5f, MyGame.SCREEN_HEIGHT * 0.5f );

        final CheckBox musicButton = new CheckBox("Music", skin );
        musicButton.getImage().setScaling(Scaling.fill);
        musicButton.getImageCell().width(50);
        musicButton.getImageCell().height(50);
        musicButton.setSize(MyGame.SCREEN_WIDTH * 0.5f, MyGame.SCREEN_HEIGHT * 0.05f);
        musicButton.getLabel().setFontScale(4.0f);
        musicButton.getLabelCell().pad(20);
        musicButton.setChecked(optionPrefs.getBoolean("Option.Music", true) );
        musicButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                optionPrefs.putBoolean("Option.Music", musicButton.isChecked() );
                optionPrefs.flush();
            }
        });

        final CheckBox effectButton = new CheckBox("Effects", skin );
        effectButton.getImage().setScaling(Scaling.fill);
        effectButton.getImageCell().width(50);
        effectButton.getImageCell().height(50);
        effectButton.setSize(MyGame.SCREEN_WIDTH * 0.5f, MyGame.SCREEN_HEIGHT * 0.05f);
        effectButton.getLabel().setFontScale(4.0f);
        effectButton.getLabelCell().pad(20);
        effectButton.setChecked(optionPrefs.getBoolean("Option.Effects", true ) );
        effectButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                optionPrefs.putBoolean("Option.Effects", effectButton.isChecked() );
                optionPrefs.flush();
            }
        });

        table.add( musicButton ).center().width(MyGame.SCREEN_WIDTH * 0.25f).height(100);
        table.row();
        table.add( effectButton ).center().width(MyGame.SCREEN_WIDTH * 0.25f).height(100);
        table.row();
        uiStage.addActor(table);

        TextButton aboutButton = new TextButton("Credits", skin);
        aboutButton.setPosition( MyGame.SCREEN_WIDTH * 0.5f - btnWidth * 0.5f, MyGame.SCREEN_HEIGHT * 0.2f );
        aboutButton.setSize(btnWidth, btnHeight );
        aboutButton.getLabel().setFontScale(4.0f);
        aboutButton.addListener(new ClickListener(){
                                     @Override
                                     public void clicked(InputEvent event, float x, float y) {
                 mainGame.setScreen( new ExitScreen(mainGame) );
             };
         }
        );
        uiStage.addActor(aboutButton);

        TextButton backButton = new TextButton("Back", skin);
        backButton.setPosition( MyGame.SCREEN_WIDTH * 0.5f- btnWidth * 0.5f, MyGame.SCREEN_HEIGHT * 0.1f  );
        backButton.setSize(btnWidth, btnHeight );
        backButton.getLabel().setFontScale(4.0f );
        backButton.addListener(new ClickListener(){
                                   @Override
                                   public void clicked(InputEvent event, float x, float y) {
               mainGame.setScreen( new MainMenuScreen(mainGame) );
           };
       }
        );
        uiStage.addActor(backButton);
    }

    @Override
    public void update(float dt)
    {}
}
