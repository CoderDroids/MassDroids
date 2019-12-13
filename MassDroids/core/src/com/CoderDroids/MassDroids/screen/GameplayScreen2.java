package com.CoderDroids.MassDroids.screen;

import com.CoderDroids.MassDroids.game.GameplayManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.CoderDroids.MassDroids.Map.Tile;
import com.CoderDroids.MassDroids.base.ActorBeta;
import com.CoderDroids.MassDroids.base.GameBeta;
import com.CoderDroids.MassDroids.base.ScreenBeta;
import com.CoderDroids.MassDroids.Map.Map;

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
    TextButton endTurnButton;
    TextButton BuildAttackersBuildiumButton;
    TextButton BuildDefendersBuildiumButton;
    TextButton BuildAttackersGoldButton;
    TextButton BuildDefendersGoldButton;
    Label BuildiumCostLabel;
    Label GoldCostLabel;

    Label tileInfoText;
    TextButton tileInfoBackButton;

    Stage infoPopupStage;

    float buildBuildiumCost;
    float buildGoldCost;

    Map gameMapTest;

    Tile currentTile;
    boolean currentTileSelected;
    boolean isShowingInfoPopup = false;

    int tapX,tapY;
    float tapTime;
    boolean tapDown;

    Preferences optionPrefs;

    @Override
    public void initialize() {
        ActorBeta.setWorldBounds(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Skin skin = new Skin(Gdx.files.internal("skins/star-soldier/skin/star-soldier-ui.json"));
        Skin skinWithBg = new Skin(Gdx.files.internal("skins/star-soldier/skin/star-soldier-ui.json"));
        optionPrefs = Gdx.app.getPreferences("OptionPrefs");
        final Sound click = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));


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
                                        if( optionPrefs.getBoolean("Option.Effects", true))
                                             click.play(1.0f);
                                        mainMenuButton.setVisible(true);
                                        backButton.setVisible(true);
                                        hideInfoPopup();

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
                                           if( optionPrefs.getBoolean("Option.Effects", true))
                                               click.play(1.0f);
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
                                       if( optionPrefs.getBoolean("Option.Effects", true))
                                           click.play(1.0f);
                                       mainMenuButton.setVisible(false);
                                       backButton.setVisible(false);
                                       hideInfoPopup();
                                   }
                               }
        );

        endTurnButton = new TextButton("End Turn", skin);
        endTurnButton.setSize(400, 200 );
        endTurnButton.setPosition(0,200);
        endTurnButton.getLabel().setFontScale(3.0f);
        endTurnButton.addListener(new ClickListener(){
               @Override
               public void clicked(InputEvent event, float x, float y) {

                     if( optionPrefs.getBoolean("Option.Effects", true))
                       click.play(1.0f);
                   onTakeTurn();
                   hideInfoPopup();
               }
           }
        );


//-------------Build options--------------//

        BuildAttackersBuildiumButton = new TextButton("Build Attackers", skin);
        BuildAttackersBuildiumButton.setSize(550, 200 );
        BuildAttackersBuildiumButton.setPosition(Gdx.graphics.getWidth()/2 - BuildAttackersBuildiumButton.getWidth(), Gdx.graphics.getHeight()/2);
        BuildAttackersBuildiumButton.getLabel().setFontScale(2.0f);
        BuildAttackersBuildiumButton.addListener(new ClickListener(){
                                                     @Override
                                                     public void clicked(InputEvent event, float x, float y) {
                                                         if( optionPrefs.getBoolean("Option.Effects", true))
                                                             click.play(1.0f);
                                                         BuildDroids(1,1,20);
                                                     }
                                                 }
        );

        BuildDefendersBuildiumButton = new TextButton("Build Defenders", skin);
        BuildDefendersBuildiumButton.setSize(550, 200 );
        BuildDefendersBuildiumButton.setPosition(Gdx.graphics.getWidth()/2 - BuildDefendersBuildiumButton.getWidth(), Gdx.graphics.getHeight()/2 - BuildAttackersBuildiumButton.getHeight() );
        BuildDefendersBuildiumButton.getLabel().setFontScale(2.0f);
        BuildDefendersBuildiumButton.addListener(new ClickListener(){
                                                     @Override
                                                     public void clicked(InputEvent event, float x, float y) {
                                                         if( optionPrefs.getBoolean("Option.Effects", true))
                                                             click.play(1.0f);
                                                         BuildDroids(2,1,20);
                                                     }
                                                 }
        );

        BuildAttackersGoldButton = new TextButton("Build Attackers", skin);
        BuildAttackersGoldButton.setSize(550, 200 );
        BuildAttackersGoldButton.setPosition(Gdx.graphics.getWidth()/2 , Gdx.graphics.getHeight()/2 );
        BuildAttackersGoldButton.getLabel().setFontScale(2.0f);
        BuildAttackersGoldButton.addListener(new ClickListener(){
                                                 @Override
                                                 public void clicked(InputEvent event, float x, float y) {
                                                     if( optionPrefs.getBoolean("Option.Effects", true))
                                                         click.play(1.0f);
                                                     BuildDroids(1,2,5);
                                                 }
                                             }
        );

        BuildDefendersGoldButton = new TextButton("Build Defenders", skin);
        BuildDefendersGoldButton.setSize(550, 200 );
        BuildDefendersGoldButton.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 - BuildAttackersGoldButton.getHeight());
        BuildDefendersGoldButton.getLabel().setFontScale(2.0f);
        BuildDefendersGoldButton.addListener(new ClickListener(){
                                                 @Override
                                                 public void clicked(InputEvent event, float x, float y) {
                                                     if( optionPrefs.getBoolean("Option.Effects", true))
                                                         click.play(1.0f);
                                                     BuildDroids(2,2,5);
                                                 }
                                             }
        );

        BuildiumCostLabel = new Label("Buildium", skin);
        BuildiumCostLabel.setFontScale(3);
        BuildiumCostLabel.setAlignment(Align.center);
        BuildiumCostLabel.setWrap(true);
        BuildiumCostLabel.setSize(Gdx.graphics.getWidth()/2, 150);
        BuildiumCostLabel.setPosition(BuildAttackersBuildiumButton.getX(), BuildAttackersBuildiumButton.getY() + BuildiumCostLabel.getHeight());

        GoldCostLabel = new Label("Gold", skin);
        GoldCostLabel.setFontScale(3);
        GoldCostLabel.setAlignment(Align.center);
        GoldCostLabel.setWrap(true);
        GoldCostLabel.setSize(Gdx.graphics.getWidth()/2, 150);
        GoldCostLabel.setPosition(BuildAttackersGoldButton.getX(), BuildAttackersGoldButton.getY() + GoldCostLabel.getHeight());
//--------------Example-------------------//


        tileInfoText = new Label("", skinWithBg);
        tileInfoText.setFontScale(2.0f);
        tileInfoText.setAlignment(Align.left);
        tileInfoText.setWrap(true);
        tileInfoText.setSize(Gdx.graphics.getWidth()/2 +50, 200);
        tileInfoText.setPosition(Gdx.graphics.getWidth() - tileInfoText.getWidth() -200, 200 + tileInfoText.getHeight());


        Pixmap labelColor = new Pixmap(Gdx.graphics.getWidth()/2 , 200, Pixmap.Format.RGB888);
        labelColor.setColor(Color.BLACK);
        labelColor.fill();
        tileInfoText.getStyle().background = new Image(new Texture(labelColor)).getDrawable();


        tileInfoBackButton = new TextButton("close", skin);
        tileInfoBackButton.setSize(350, 100 );
        tileInfoBackButton.setPosition(Gdx.graphics.getWidth() - tileInfoBackButton.getWidth(),0);
        tileInfoBackButton.getLabel().setFontScale(2.0f);
        tileInfoBackButton.addListener(new ClickListener(){
                               @Override
                               public void clicked(InputEvent event, float x, float y) {

                                   if( optionPrefs.getBoolean("Option.Effects", true))
                                     click.play(1.0f);
                                   hideInfoPopup();
                               }
                           }
        );
        // end example
        infoPopupStage = new Stage();
        infoPopupStage.addActor(tileInfoText);
        infoPopupStage.addActor(tileInfoBackButton);
        infoPopupStage.addActor(BuildAttackersBuildiumButton);
        infoPopupStage.addActor(BuildDefendersBuildiumButton);
        infoPopupStage.addActor(BuildAttackersGoldButton);
        infoPopupStage.addActor(BuildDefendersGoldButton);
        infoPopupStage.addActor(BuildiumCostLabel);
        infoPopupStage.addActor(GoldCostLabel);
        //addStage(infoPopupStage);

        uiStage.addActor(buildiumLabel);
        uiStage.addActor(goldLabel);
        uiStage.addActor(mainMenuButton);
        uiStage.addActor(backButton);
        uiStage.addActor(homeButton);
        uiStage.addActor(endTurnButton);

    }

    @Override
    public void update(float dt) {
        buildiumLabel.setText("Buildium Available: "+ buildium);
        goldLabel.setText("Gold Available: "+ gold);
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

        showInfoPopup();
        Tile test = gameMapTest.screenSpaceCoordinatesToTile(screenX,screenY);
        if(!currentTileSelected)
        {
            currentTile = test;
            currentTileSelected = true;
        }

        tileInfoText.setText("Owner: Player " +  test.playerThatOwns
                + "\nBuildium: " +  test.buildium
                + "\nGold: " + test.gold
                + "\nDefenders: " + test.defenders
                + "\nAttckers: " + test.attackers
                + "\nDefensive bonus: " + test.defensiveValue );
        tileInfoText.setPosition(Gdx.graphics.getWidth() - tileInfoText.getWidth(), 0 + tileInfoText.getHeight());
        tileInfoText.setFontScale(2.0f);
        return false;
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer){

        gameMapTest.scrollCamera(tapX-screenX,tapY-screenY);
        tapX =screenX;
        tapY = screenY;
        return false;
    }

    public void BuildDroids(int TypeOfDroid, int CurrencyTypeUsed, int Cost)
    {/*
    TypeOfDroid
        1 - Attacker
        2 - Defender

    CurrencyTypeUsed
        1 - Buildium
        2 - Gold

    */
        if(CurrencyTypeUsed == 1)
        {
            if(buildium >= Cost)
                buildium -= Cost;
            else
                return;
        }
        else if (CurrencyTypeUsed == 2)
        {
            if(gold >= Cost)
                gold -= Cost;
            else
                return;
        }

        if(TypeOfDroid == 1)
            currentTile.attackers += 1;
        else if (TypeOfDroid == 2)
            currentTile.defenders += 1;

        hideInfoPopup();
        currentTileSelected = false;
    }

    void init()
    {
        buildium = GameplayManager.getInstance().player.buildium;
        gold = GameplayManager.getInstance().player.gold;
    }


    void onTakeTurn()
    {
        GameplayManager.getInstance().takeTurn();
        buildium = GameplayManager.getInstance().player.buildium;
        gold = GameplayManager.getInstance().player.gold;
    }

    void showInfoPopup()
    {
        if( !isShowingInfoPopup )
        {
            addStage(infoPopupStage);
            isShowingInfoPopup = true;
        }
    }

    void hideInfoPopup()
    {
        removeStage(infoPopupStage);
        isShowingInfoPopup = false;
    }

}
