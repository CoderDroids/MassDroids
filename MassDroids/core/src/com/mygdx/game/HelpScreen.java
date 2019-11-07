package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.base.ActorBeta;
import com.mygdx.game.base.GameBeta;
import com.mygdx.game.base.ScreenBeta;

import static com.mygdx.game.MyGame.SCREEN_HEIGHT;
import static com.mygdx.game.MyGame.SCREEN_WIDTH;

public class HelpScreen extends ScreenBeta {

    public HelpScreen(GameBeta game )
    {
        super(game);
    }

    @Override
    public void initialize()
    {

        Skin skin = new Skin(Gdx.files.internal("skins/star-soldier/skin/star-soldier-ui.json"));
        Skin arcadeSkin = new Skin(Gdx.files.internal("skins/arcade/skin/arcade-ui.json"));

        float btnWidth = SCREEN_WIDTH / 4;
        float btnHeight = SCREEN_HEIGHT/ 8;

        TextButton menuButton = new TextButton("Menu", skin);
        menuButton.setPosition( SCREEN_WIDTH * 0.7f, SCREEN_HEIGHT * 0.4f  );
        menuButton.setSize(btnWidth, btnHeight );
        menuButton.getLabel().setFontScale(3, 3 );
        menuButton.addListener(new ClickListener(){
                                   @Override
                                   public void clicked(InputEvent event, float x, float y) {
                                       mainGame.setScreen( new MainMenuScreen(mainGame) );
                                   };
                               }
        );
        uiStage.addActor(menuButton);
    }

    @Override
    public void update(float dt)
    {}

}
