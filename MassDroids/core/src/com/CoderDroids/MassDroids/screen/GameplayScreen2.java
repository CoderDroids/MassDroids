package com.CoderDroids.MassDroids.screen;

import com.CoderDroids.MassDroids.game.GameType.Troop;
import com.CoderDroids.MassDroids.game.GameType.Currency;
import com.CoderDroids.MassDroids.Sound.SoundManager;
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
    Label tileInfoText;
    Label buildiumLabel;
    Label goldLabel;
    Label attackerLabel;
    Label defenderLabel;

    int buildium;
    int gold;
    int numAttackers;
    int numDefenders;

    TextButton homeButton;
    TextButton mainMenuButton;
    TextButton backButton;
    TextButton endTurnButton;


    Stage infoPopupStage;
    boolean isShowingInfoPopup = false;
    Stage attackPopupStage;
    boolean isShowingAttackPopup = false;

    float buildBuildiumCost;
    float buildGoldCost;

    Map gameMapTest;

    Tile currentTile;
    boolean currentTileSelected;


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


        gameMapTest = new Map(mainStage);
        //Tile test = new Tile();
        //test = gameMapTest.mapTiles.get(24);
        //test = gameMapTest.getTile2D(1,2);

        buildiumLabel = new Label("Buildium : "+ buildium, skin);
        buildiumLabel.setFontScale(3);
        buildiumLabel.setAlignment(Align.center);
        buildiumLabel.setWrap(true);
        buildiumLabel.setSize(Gdx.graphics.getWidth()/2, 150);
        buildiumLabel.setPosition(0, Gdx.graphics.getHeight() - buildiumLabel.getHeight());

        goldLabel = new Label("Gold : ", skin);
        goldLabel.setFontScale(3);
        goldLabel.setAlignment(Align.center);
        goldLabel.setWrap(true);
        goldLabel.setSize(Gdx.graphics.getWidth()/2, 150);
        goldLabel.setPosition(buildiumLabel.getWidth(), Gdx.graphics.getHeight() - goldLabel.getHeight());

        attackerLabel = new Label("Attacker : ", skin);
        attackerLabel.setFontScale(3);
        attackerLabel.setAlignment(Align.center);
        attackerLabel.setWrap(true);
        attackerLabel.setSize(Gdx.graphics.getWidth()/2, 150);
        attackerLabel.setPosition(0, Gdx.graphics.getHeight() - attackerLabel.getHeight() * 1.5f);

        defenderLabel = new Label("Defender : ", skin);
        defenderLabel.setFontScale(3);
        defenderLabel.setAlignment(Align.center);
        defenderLabel.setWrap(true);
        defenderLabel.setSize(Gdx.graphics.getWidth()/2, 150);
        defenderLabel.setPosition(attackerLabel.getWidth(), Gdx.graphics.getHeight() - defenderLabel.getHeight() * 1.5f) ;


        homeButton = new TextButton("Options", skin);
        homeButton.setSize(400, 200 );
        homeButton.setPosition(0,0);
        homeButton.getLabel().setFontScale(3.0f);
        homeButton.addListener(new ClickListener(){
                                     @Override
                                     public void clicked(InputEvent event, float x, float y) {
                                        if( optionPrefs.getBoolean("Option.Effects", true))
                                             SoundManager.getInstance().click.play(1.0f);
                                        mainMenuButton.setVisible(true);
                                        backButton.setVisible(true);
                                         hideAllPopup();

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
                                               SoundManager.getInstance().click.play(1.0f);
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
                                           SoundManager.getInstance().click.play(1.0f);
                                       mainMenuButton.setVisible(false);
                                       backButton.setVisible(false);
                                       hideAllPopup();
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
                         SoundManager.getInstance().click.play(1.0f);
                   onTakeTurn();
                   hideAllPopup();
               }
           }
        );


//-------------Build options--------------//

        TextButton BuildAttackersBuildiumButton = new TextButton("Build Attackers", skin);
        BuildAttackersBuildiumButton.setSize(550, 200 );
        BuildAttackersBuildiumButton.setPosition(Gdx.graphics.getWidth()/2 - BuildAttackersBuildiumButton.getWidth(), Gdx.graphics.getHeight()/2);
        BuildAttackersBuildiumButton.getLabel().setFontScale(2.0f);
        BuildAttackersBuildiumButton.addListener(new ClickListener(){
                                                     @Override
                                                     public void clicked(InputEvent event, float x, float y) {
                                                         if( optionPrefs.getBoolean("Option.Effects", true))

                                                         SoundManager.getInstance().click.play(1.0f);
                                                         BuildDroids(Troop.Attacker, Currency.Buildium);
                                                     }
                                                 }
        );

        TextButton BuildDefendersBuildiumButton = new TextButton("Build Defenders", skin);
        BuildDefendersBuildiumButton.setSize(550, 200 );
        BuildDefendersBuildiumButton.setPosition(Gdx.graphics.getWidth()/2 - BuildDefendersBuildiumButton.getWidth(), Gdx.graphics.getHeight()/2 - BuildAttackersBuildiumButton.getHeight() );
        BuildDefendersBuildiumButton.getLabel().setFontScale(2.0f);
        BuildDefendersBuildiumButton.addListener(new ClickListener(){
                                                     @Override
                                                     public void clicked(InputEvent event, float x, float y) {
                                                         if( optionPrefs.getBoolean("Option.Effects", true))

                                                         SoundManager.getInstance().click.play(1.0f);
                                                         BuildDroids(Troop.Defender,Currency.Buildium);
                                                     }
                                                 }
        );

        TextButton BuildAttackersGoldButton = new TextButton("Build Attackers", skin);
        BuildAttackersGoldButton.setSize(550, 200 );
        BuildAttackersGoldButton.setPosition(Gdx.graphics.getWidth()/2 , Gdx.graphics.getHeight()/2 );
        BuildAttackersGoldButton.getLabel().setFontScale(2.0f);
        BuildAttackersGoldButton.addListener(new ClickListener(){
                                                 @Override
                                                 public void clicked(InputEvent event, float x, float y) {
                                                     if( optionPrefs.getBoolean("Option.Effects", true))
                                                     SoundManager.getInstance().click.play(1.0f);
                                                     BuildDroids(Troop.Attacker,Currency.Gold);
                                                 }
                                             }
        );

        TextButton BuildDefendersGoldButton = new TextButton("Build Defenders", skin);
        BuildDefendersGoldButton.setSize(550, 200 );
        BuildDefendersGoldButton.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 - BuildAttackersGoldButton.getHeight());
        BuildDefendersGoldButton.getLabel().setFontScale(2.0f);
        BuildDefendersGoldButton.addListener(new ClickListener(){
                                                 @Override
                                                 public void clicked(InputEvent event, float x, float y) {
                                                     if( optionPrefs.getBoolean("Option.Effects", true))
                                                     SoundManager.getInstance().click.play(1.0f);
                                                     BuildDroids(Troop.Defender, Currency.Gold);
                                                 }
                                             }
        );

        Label BuildiumCostLabel = new Label("Buildium", skin);
        BuildiumCostLabel.setFontScale(3);
        BuildiumCostLabel.setAlignment(Align.center);
        BuildiumCostLabel.setWrap(true);
        BuildiumCostLabel.setSize(Gdx.graphics.getWidth()/2, 150);
        BuildiumCostLabel.setPosition(BuildAttackersBuildiumButton.getX(), BuildAttackersBuildiumButton.getY() + BuildiumCostLabel.getHeight());

        Label GoldCostLabel = new Label("Gold", skin);
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


        TextButton tileInfoBackButton = new TextButton("close", skin);
        tileInfoBackButton.setSize(350, 100 );
        tileInfoBackButton.setPosition(Gdx.graphics.getWidth() - tileInfoBackButton.getWidth(),0);
        tileInfoBackButton.getLabel().setFontScale(2.0f);
        tileInfoBackButton.addListener(new ClickListener(){
                               @Override
                               public void clicked(InputEvent event, float x, float y) {

                                   if( optionPrefs.getBoolean("Option.Effects", true))
                                       SoundManager.getInstance().click.play(1.0f);
                                   hideAllPopup();
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

        attackPopupStage = new AttackStage();

        uiStage.addActor(buildiumLabel);
        uiStage.addActor(goldLabel);
        uiStage.addActor(attackerLabel);
        uiStage.addActor(defenderLabel);
        uiStage.addActor(mainMenuButton);
        uiStage.addActor(backButton);
        uiStage.addActor(homeButton);
        uiStage.addActor(endTurnButton);

        /// init game play variables
        gold = GameplayManager.getInstance().player.gold;
        buildium = GameplayManager.getInstance().player.buildium;
        numAttackers = GameplayManager.getInstance().player.numAttackers;
        numDefenders = GameplayManager.getInstance().player.numDefenders;
    }

    @Override
    public void update(float dt) {
        buildiumLabel.setText("Buildium : "+ buildium);
        goldLabel.setText("Gold : "+ gold);
        attackerLabel.setText("Attackers : "+ numAttackers);
        defenderLabel.setText("Defenders : "+ numDefenders);
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
        //test.playerThatOwns != 0

        if(test.playerThatOwns > 0) {
            tileInfoText.setVisible(true);
            tileInfoText.setText("Owner: Player " + test.playerThatOwns
                    + "\nBuildium: " + test.buildium
                    + "\nGold: " + test.gold
                    + "\nDefenders: " + test.defenders
                    + "\nAttckers: " + test.attackers
                    + "\nDefensive bonus: " + test.defensiveValue
                    + "\nTile Type: " + test.tileType);
            tileInfoText.setPosition(Gdx.graphics.getWidth() - tileInfoText.getWidth(), 0 + tileInfoText.getHeight());
            tileInfoText.setFontScale(2.0f);

            if( test.playerThatOwns == 1 )
                showInfoPopup();
            else
                showAttackPopup();
        }else{
            tileInfoText.setVisible(false);
            hideAllPopup();
        }

        return false;
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer){

        gameMapTest.scrollCamera(tapX-screenX,tapY-screenY);
        tapX =screenX;
        tapY = screenY;
        return false;
    }

    public void BuildDroids(Troop TypeOfDroid, Currency CurrencyTypeUsed)
    {
        boolean purchased = GameplayManager.getInstance().trainTroop(TypeOfDroid, 1, CurrencyTypeUsed);
        if( purchased )
        {
            gold = GameplayManager.getInstance().player.gold;
            buildium = GameplayManager.getInstance().player.buildium;
            numAttackers = GameplayManager.getInstance().player.numAttackers;
            numDefenders = GameplayManager.getInstance().player.numDefenders;
        }

        hideAllPopup();
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
        if( isShowingAttackPopup )
            hideAttackPopup();

        removeStage(infoPopupStage);
        isShowingInfoPopup = false;

    }

    void hideAttackPopup()
    {
        removeStage( attackPopupStage );
        isShowingAttackPopup = false;
    }

    void hideAllPopup()
    {
        hideInfoPopup();
        hideAttackPopup();
    }

    void showAttackPopup()
    {
        if(isShowingInfoPopup)
            hideInfoPopup();

        if( !isShowingAttackPopup )
        {
            addStage( attackPopupStage );
            isShowingAttackPopup = true;
        }
    }
}
