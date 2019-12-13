package com.CoderDroids.MassDroids.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.CoderDroids.MassDroids.base.GameBeta;
import com.CoderDroids.MassDroids.base.ScreenBeta;

public class ExitScreen extends ScreenBeta {

    public ExitScreen(GameBeta game )
    {
        super(game);
    }

    Skin skin;
    Texture texture;
    Image image;
    Label label;
    TextButton button;
    Preferences optionPrefs;


    @Override
    public void initialize()
    {
        skin = new Skin(Gdx.files.internal("skins/star-soldier/skin/star-soldier-ui.json"));
        optionPrefs = Gdx.app.getPreferences("OptionPrefs");
        final Sound click = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));

        texture = new Texture(Gdx.files.internal("MassDroidsLogo.jpg"));
        image = new Image(texture);
        image.setPosition(Gdx.graphics.getWidth()/2 - (texture.getWidth()/2) - 160, Gdx.graphics.getHeight()/2 + 360);
        image.setScale(1.5f);
        label = new Label("Thank you for playing!", skin);
        label.setWrap(true);
        label.setFontScale(4);
        label.setSize(Gdx.graphics.getWidth(), 100);
        label.setPosition(0, Gdx.graphics.getHeight()/2 - image.getImageHeight());
        button = new TextButton("Exit", skin);
        button.setSize(300, 200);
        button.getLabel().setFontScale(3.5f);
        button.setPosition(Gdx.graphics.getWidth()/2-150, 400);
        button.addListener(new ClickListener(){
                                     @Override
                                     public void clicked(InputEvent event, float x, float y) {
                                         if( optionPrefs.getBoolean("Option.Effects", true))
                                             click.play(1.0f);
                                         Gdx.app.exit();
                                     };
                                 }
        );

        uiStage.addActor(image);
        uiStage.addActor(label);
        uiStage.addActor(button);
    }

    @Override
    public void update(float dt)
    {

    }
}
