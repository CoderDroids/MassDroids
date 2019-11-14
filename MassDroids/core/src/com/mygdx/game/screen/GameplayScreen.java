package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.MyGame;
import com.mygdx.game.base.ActorBeta;
import com.mygdx.game.base.GameBeta;
import com.mygdx.game.base.ScreenBeta;

import static com.mygdx.game.MyGame.SCREEN_HEIGHT;
import static com.mygdx.game.MyGame.SCREEN_WIDTH;

public class GameplayScreen extends ScreenBeta {

    Touchpad touchPad;

    public boolean isGameStarted;
    public boolean isGameEnded;

    final float btnWidth = 100;
    final float btnHeight = 100;

    final float padWidth = 150;
    final float padHeight = 150;

    public GameplayScreen(GameBeta game )
    {
        super(game);
    }

    @Override
    public void initialize()
    {
        ActorBeta.setWorldBounds(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Skin skin = new Skin(Gdx.files.internal("skins/star-soldier/skin/star-soldier-ui.json"));


        TextButton configureButton = new TextButton("+", skin);
        configureButton.setSize(200, 200 );
        configureButton.getLabel().setFontScale(4.0f);
        configureButton.addListener(new ClickListener(){
                                    @Override
                                    public void clicked(InputEvent event, float x, float y) {
                mainGame.setScreen( new ConfigurationScreen(mainGame) );
            };
        }
        );
        Label configureLabel = new Label("Configure A.I", skin);
        configureLabel.setAlignment( Align.center );
        configureLabel.setFontScale(3.0f);

        TextButton reportButton = new TextButton("+", skin);
        reportButton.setSize(200, 200 );
        reportButton.getLabel().setFontScale(4.0f);
        reportButton.addListener(new ClickListener(){
                                        @Override
                                        public void clicked(InputEvent event, float x, float y) {
                mainGame.setScreen( new ReportScreen(mainGame) );
            };
        }
        );
        Label reportLabel = new Label("Reports", skin);
        reportLabel.setAlignment( Align.center );
        reportLabel.setFontScale(3.0f);

        Label resourceLabel = new Label("Resources", skin);
        resourceLabel.setAlignment( Align.center );
        resourceLabel.setFontScale(3.0f);

        Image playerInfo = new Image(new Texture("badlogic.jpg"));

        TextButton nextButton = new TextButton("->", skin);
        nextButton.setSize(200, 200 );
        nextButton.getLabel().setFontScale(4.0f);
        nextButton.addListener(new ClickListener(){
                 @Override
                 public void clicked(InputEvent event, float x, float y) {
                     nextTurn();
                 };
             }
        );
        Label nextLabel = new Label("Next Turn", skin);
        nextLabel.setAlignment( Align.center );
        nextLabel.setFontScale(3.0f);

        TextButton backButton = new TextButton("<-", skin);
        backButton.setSize(200, 200 );
        backButton.getLabel().setFontScale(4.0f);
        backButton.addListener(new ClickListener(){
               @Override
               public void clicked(InputEvent event, float x, float y) {
                   mainGame.setScreen( new MainMenuScreen(mainGame) );
               };
           }
        );
        Label backLabel = new Label("Back", skin);
        backLabel.setAlignment( Align.center );
        backLabel.setFontScale(3.0f);

        Table table = new Table();
        table.setFillParent(true);

        // configuration buttons
        table.row().height(100);
        table.add(configureButton).width(200).height(200);
        table.add().width(100);
        table.add(reportButton).width(200).height(200);
        table.row();
        table.add(configureLabel).height(100);
        table.add().width(100);
        table.add(reportLabel).height(100);
        table.row().height(50);

        // resouces info
        table.add(resourceLabel).colspan(3);
        table.row();
        table.add();

        // main player information
        table.add(playerInfo);
        table.row();

        // bottom button panel
        table.row().height(100);
        table.add(nextButton).width(200).height(200);
        table.add().width(100);
        table.add(backButton).width(200).height(200);
        table.row();
        table.add(nextLabel).height(100);
        table.add().width(100);
        table.add(backLabel).height(100);
        table.row().height(50);


        uiStage.addActor(table);

    }

    @Override
    public void update(float dt)
    {
    }

    void onTouchpadMoved( float dx, float dy )
    {

    }

    @Override
    public void dispose()
    {
        super.dispose();
    }

    void nextTurn()
    {

    }

}
