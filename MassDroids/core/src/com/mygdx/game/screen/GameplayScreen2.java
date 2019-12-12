package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Map.Tile;
import com.mygdx.game.base.ActorBeta;
import com.mygdx.game.base.GameBeta;
import com.mygdx.game.base.ScreenBeta;
import com.mygdx.game.Map.Map;

public class GameplayScreen2 extends ScreenBeta {

    public GameplayScreen2(GameBeta game )
    {
        super(game);
    }
    Skin skin;
    Label buildiumLabel;
    Label goldLabel;
    float buildium;
    float gold;

    TextButton homeButton;
    TextButton mainMenuButton;
    TextButton backButton;

    TextButton exampleTile;
    TextButton build1;
    TextButton build2;
    TextButton buildBackButton;

    float buildBuildiumCost;
    float buildGoldCost;

    Map gameMapTest;

    int tapX,tapY;
    float tapTime;
    boolean tapDown;

    @Override
    public void initialize() {
        ActorBeta.setWorldBounds(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Skin skin = new Skin(Gdx.files.internal("skins/star-soldier/skin/star-soldier-ui.json"));

        gameMapTest = new Map(mainStage);
        //Tile test = new Tile();
        //test = gameMapTest.mapTiles.get(24);
        //test = gameMapTest.getTile2D(1,2);
        buildium = 100;
        gold = 20;
        buildBuildiumCost = 20.0f;
        buildGoldCost = 20.0f;

        buildiumLabel = new Label("Buildium Available: "+ buildium, skin);
        buildiumLabel.setFontScale(3);
        buildiumLabel.setAlignment(Align.center);
        buildiumLabel.setWrap(true);
        buildiumLabel.setSize(Gdx.graphics.getWidth()/2, 150);
        buildiumLabel.setPosition(0, Gdx.graphics.getHeight() - buildiumLabel.getHeight());

        goldLabel = new Label("Gold Available: "+ gold, skin);
        goldLabel.setFontScale(3);
        goldLabel.setAlignment(Align.center);
        goldLabel.setWrap(true);
        goldLabel.setSize(Gdx.graphics.getWidth()/2, 150);
        goldLabel.setPosition(buildiumLabel.getWidth(), Gdx.graphics.getHeight() - buildiumLabel.getHeight());

        homeButton = new TextButton("Options", skin);
        homeButton.setSize(400, 200 );
        homeButton.setPosition(0,0);
        homeButton.getLabel().setFontScale(3.0f);
        homeButton.addListener(new ClickListener(){
                                     @Override
                                     public void clicked(InputEvent event, float x, float y) {
                                        mainMenuButton.setVisible(true);
                                        backButton.setVisible(true);
                                     }
                                 }
        );

        mainMenuButton = new TextButton("Home", skin);
        mainMenuButton.setSize(500, 200 );
        mainMenuButton.setPosition(Gdx.graphics.getWidth()/2 -  mainMenuButton.getWidth()/2, Gdx.graphics.getHeight()/2 - mainMenuButton.getHeight()/2);
        mainMenuButton.getLabel().setFontScale(3.0f);
        mainMenuButton.setVisible(false);
        mainMenuButton.addListener(new ClickListener(){
                                       @Override
                                       public void clicked(InputEvent event, float x, float y) {
                                           mainGame.setScreen( new MainMenuScreen(mainGame) );
                                       }
                                   }
        );

        backButton = new TextButton("Back", skin);
        backButton.setSize(500, 200 );
        backButton.setPosition(Gdx.graphics.getWidth()/2 - mainMenuButton.getWidth()/2, Gdx.graphics.getHeight()/2 - mainMenuButton.getHeight()/2 - mainMenuButton.getHeight());
        backButton.getLabel().setFontScale(3.0f);
        backButton.setVisible(false);
        backButton.addListener(new ClickListener(){
                                   @Override
                                   public void clicked(InputEvent event, float x, float y) {
                                       mainMenuButton.setVisible(false);
                                       backButton.setVisible(false);
                                   }
                               }
        );


//--------------Example-------------------//
        /*exampleTile = new TextButton("'Tile'", skin);
        exampleTile.setSize(300, 300 );
        exampleTile.setPosition(500, 600);
        exampleTile.getLabel().setFontScale(3.0f);
        exampleTile.setVisible(true);
        exampleTile.addListener(new ClickListener(){
                                   @Override
                                   public void clicked(InputEvent event, float x, float y) {
                                        build1.setVisible(true);
                                        build2.setVisible(true);
                                        buildBackButton.setVisible(true);
                                   }
                               }
        );

        build1 = new TextButton("Buildium = " + buildBuildiumCost, skin);
        build1.setSize(700, 200 );
        build1.setPosition(Gdx.graphics.getWidth()/2 -  build1.getWidth()/2, Gdx.graphics.getHeight()/2 - build1.getHeight()/2);
        build1.getLabel().setFontScale(3.0f);
        build1.setVisible(false);
        build1.addListener(new ClickListener(){
                                       @Override
                                       public void clicked(InputEvent event, float x, float y) {
                                           build1.setVisible(false);
                                           build2.setVisible(false);
                                           buildBackButton.setVisible(false);
                                           exampleTile.setVisible(false);
                                           buildium -= 20.0f;
                                       }
                                   }
        );

        build2 = new TextButton("Gold = " + buildGoldCost, skin);
        build2.setSize(700, 200 );
        build2.setPosition(Gdx.graphics.getWidth()/2 - build2.getWidth()/2, Gdx.graphics.getHeight()/2 - build2.getHeight()/2 - build1.getHeight());
        build2.getLabel().setFontScale(3.0f);
        build2.setVisible(false);
        build2.addListener(new ClickListener(){
                                   @Override
                                   public void clicked(InputEvent event, float x, float y) {
                                       build1.setVisible(false);
                                       build2.setVisible(false);
                                       buildBackButton.setVisible(false);
                                       exampleTile.setVisible(false);
                                       gold -= 20.0f;
                                   }
                               }
        );

        buildBackButton = new TextButton("Back", skin);
        buildBackButton.setSize(700, 200 );
        buildBackButton.setPosition(Gdx.graphics.getWidth()/2 - buildBackButton.getWidth()/2, Gdx.graphics.getHeight()/2 - buildBackButton.getHeight()/2 - build1.getHeight() - build2.getHeight());
        buildBackButton.getLabel().setFontScale(3.0f);
        buildBackButton.setVisible(false);
        buildBackButton.addListener(new ClickListener(){
                               @Override
                               public void clicked(InputEvent event, float x, float y) {
                                   build1.setVisible(false);
                                   build2.setVisible(false);
                                   buildBackButton.setVisible(false);
                               }
                           }
        );
*/
        mainStage.addActor(buildiumLabel);
        mainStage.addActor(goldLabel);
        //mainStage.addActor(exampleTile);
        //mainStage.addActor(build1);
        //mainStage.addActor(build2);
       // mainStage.addActor(buildBackButton);
        mainStage.addActor(mainMenuButton);
        mainStage.addActor(backButton);
        mainStage.addActor(homeButton);
    }

    @Override
    public void update(float dt) {
        //buildiumLabel.setText("Buildium Available: "+ buildium);
        //goldLabel.setText("Gold Available: "+ gold);
        if(tapDown)
            tapTime+=dt;

    }

    @Override
    public void dispose() {
        super.dispose();
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        tapX = screenX;
        tapY = screenY;
        tapDown = true;
        tapTime = 0.0f;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        tapDown = false;
        if(tapTime > 0.32f)
            return false;

        Tile test = gameMapTest.screenSpaceCoordinatesToTile(screenX,screenY);
        buildiumLabel.setText("Buildium Available: "+ test.buildium);
        goldLabel.setText("Gold Available: "+ test.gold);

        return false;
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer){
        
        gameMapTest.scrollCamera(tapX-screenX,tapY-screenY);
        tapX =screenX;
        tapY = screenY;
        return false;
    }

}
