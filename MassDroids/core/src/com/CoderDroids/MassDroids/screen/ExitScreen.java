package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.base.GameBeta;
import com.mygdx.game.base.ScreenBeta;

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

    @Override
    public void initialize()
    {
        skin = new Skin(Gdx.files.internal("skins/star-soldier/skin/star-soldier-ui.json"));
        texture = new Texture(Gdx.files.internal("MassDroidsLogo.jpg"));
        image = new Image(texture);
        image.setPosition(Gdx.graphics.getWidth()/2 - 325, Gdx.graphics.getHeight()/2 + 360);
        label = new Label("Thank you for playing!", skin);
        label.setWrap(true);
        label.setFontScale(4);
        label.setSize(Gdx.graphics.getWidth(), 100);
        label.setPosition(0, Gdx.graphics.getHeight()/2 - image.getImageHeight());
        button = new TextButton("Exit", skin);
        button.setSize(300, 200);
        button.setPosition(Gdx.graphics.getWidth()/2-150, 400);
        button.addListener(new ClickListener(){
                                     @Override
                                     public void clicked(InputEvent event, float x, float y) {
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
