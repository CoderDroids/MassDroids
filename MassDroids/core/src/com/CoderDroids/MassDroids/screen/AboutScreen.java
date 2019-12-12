package com.CoderDroids.MassDroids.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.CoderDroids.MassDroids.MyGame;
import com.CoderDroids.MassDroids.base.GameBeta;
import com.CoderDroids.MassDroids.base.ScreenBeta;

public class AboutScreen extends ScreenBeta {

    public AboutScreen(GameBeta game )
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

        Label titleText = new Label("About Us", skin);
        titleText.setPosition( MyGame.SCREEN_WIDTH * 0.4f, MyGame.SCREEN_HEIGHT * 0.8f);
        titleText.setAlignment( Align.center );
        titleText.setFontScale(4, 4);

        uiStage.addActor(titleText);


        String abouutUsInfo = "We are coder droids \n A group dedicated to creating \n  " +
                "new and innovative takes \n on game genre for mobile \n " +
                "   Our current project is \n Mass Droids \n a combination of the \n popular clicker and RTS genres \n \n" +
                "Members: \n" +
                "Ted Bissada \n" +
                "Younggi Kim \n" +
                "Nikola Kordic \n" +
                "Ben LeBlanc";

        Label infoText = new Label(abouutUsInfo, skin);
        infoText.setPosition( MyGame.SCREEN_WIDTH * 0.3f, MyGame.SCREEN_HEIGHT * 0.6f);
        infoText.setAlignment( Align.center );
        infoText.setFontScale(2, 2);

        uiStage.addActor(infoText);

        Label returnText = new Label("Tap to return to Menu", skin);
        returnText.setPosition( MyGame.SCREEN_WIDTH * 0.3f, MyGame.SCREEN_HEIGHT * 0.25f);
        returnText.setAlignment( Align.center );
        returnText.setFontScale(2, 2);

        uiStage.addActor(returnText);

    }

    @Override
    public void update(float dt) {

    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        mainGame.setScreen( new MainMenuScreen(mainGame) );
        return true;
    }

}
