package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.base.ActorBeta;
import com.mygdx.game.base.GameBeta;
import com.mygdx.game.base.ScreenBeta;

public class ConfigurationScreen extends ScreenBeta {

    public ConfigurationScreen(GameBeta game )
    {
        super(game);
    }

    // Variables
    int titleFontSize;
    int fontSize;


    // UI Objects
    Table table;
    Skin skin;
    Label resourcesTitle;
    Slider woodSlider;
    Slider goldSlider;
    Label woodLabel;
    Label goldLabel;
    Slider ironSlider;
    Slider foodSlider;
    Label ironLabel;
    Label foodLabel;
    CheckBox balancedCheckbox;
    CheckBox storageCheckBox;
    Label militaryLabel;
    CheckBox rangeCheckBox;
    CheckBox meleeCheckBox;
    CheckBox defensiveCheckBox;
    Label infrastructureLabel;
    CheckBox housingCheckBox;
    CheckBox defenseCheckBox;
    CheckBox roadsCheckBox;
    CheckBox transportCheckBox;
    Label droidsLabel;
    Slider droidsSlider;
    Label droidsQualityLabel;
    Label droidsQuantityLabel;
    Button backToGameButton;
    Label backToGameLabel;

    @Override
    public void initialize()
    {
        // Variable Inits
        titleFontSize = 4;
        fontSize = 3;

        // UI Object Inits
        table = new Table(skin);
        table.defaults().expand().fill().padBottom(4f);
        table.columnDefaults(0).center().width(Gdx.graphics.getWidth()/3);
        table.columnDefaults(2).center().width(Gdx.graphics.getWidth()/3);
        table.columnDefaults(3).center().width(Gdx.graphics.getWidth()/3);
        table.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        skin = new Skin(Gdx.files.internal("skins/star-soldier/skin/star-soldier-ui.json"));
        resourcesTitle = new Label("Resources", skin);
        resourcesTitle.setFontScale(titleFontSize);

        //---------Row---------//
        woodSlider = new Slider(0,100,1,false,skin);
        goldSlider = new Slider(0,100,1,false,skin);

        //---------Row---------//
        woodLabel = new Label("Wood", skin);
        woodLabel.setFontScale(fontSize);
        goldLabel = new Label("Gold", skin);
        goldLabel.setFontScale(fontSize);

        //---------Row---------//
        ironSlider = new Slider(0, 100, 1, false, skin);
        foodSlider = new Slider(0, 100, 1, false, skin);

        //---------Row---------//
        ironLabel = new Label("Iron", skin);
        ironLabel.setFontScale(fontSize);
        foodLabel = new Label("Food", skin);
        foodLabel.setFontScale(fontSize);

        //---------Row---------//
        balancedCheckbox = new CheckBox("Balanced", skin);
        storageCheckBox = new CheckBox("Storage", skin);

        //---------Row---------//
        militaryLabel = new Label("Military", skin);
        militaryLabel.setFontScale(titleFontSize);

        //---------Row---------//
        rangeCheckBox = new CheckBox("Range", skin);
        meleeCheckBox = new CheckBox("Melee", skin);
        defensiveCheckBox = new CheckBox("Defensive", skin);

        //---------Row---------//
        infrastructureLabel = new Label("Infrastructure", skin);
        infrastructureLabel.setFontScale(titleFontSize);

        //---------Row---------//
        housingCheckBox = new CheckBox("Housing", skin);
        defenseCheckBox = new CheckBox("Defense", skin);

        //---------Row---------//
        roadsCheckBox = new CheckBox("Roads", skin);
        transportCheckBox = new CheckBox("Transport", skin);

        //---------Row---------//
        droidsLabel = new Label("Droids", skin);
        droidsLabel.setFontScale(titleFontSize);

        //---------Row---------//
        droidsSlider = new Slider(1, 100, 1, false, skin);

        //---------Row---------//
        droidsQualityLabel = new Label("Quality", skin);
        droidsQuantityLabel = new Label("Quantity", skin);

        //---------Row---------//
        backToGameButton = new Button(skin);
        backToGameButton.addListener(new ClickListener(){
                                   @Override
                                   public void clicked(InputEvent event, float x, float y) {
                                       mainGame.setScreen( new GameplayScreen(mainGame) );
                                   }
                               }
        );
        backToGameLabel = new Label("Back To Game", skin);
        backToGameLabel.setFontScale(2);


        // Table arrangements
        table.debugTable();
        table.debugCell();
        table.add(resourcesTitle).width(Value.percentWidth(0.50f,table));
        table.row();
        table.add(woodSlider);
        table.add(goldSlider);
        table.row();
        table.add(woodLabel);
        table.add(goldLabel);
        table.row();
        table.add(ironSlider);
        table.add(foodSlider);
        table.row();
        table.add(ironLabel);
        table.add(foodLabel);
        table.row();
        table.add(balancedCheckbox);
        table.add(storageCheckBox);
        table.row();
        table.add(militaryLabel).width(Value.percentWidth(0.50f,table));
        table.row();
        table.add(rangeCheckBox);
        table.add(meleeCheckBox);
        table.add(defensiveCheckBox);
        table.row();
        table.add(infrastructureLabel).width(Value.percentWidth(0.50f,table));
        table.row();
        table.add(housingCheckBox);
        table.add(defenseCheckBox);
        table.row();
        table.add(roadsCheckBox);
        table.add(transportCheckBox);
        table.row();
        table.add(droidsLabel).width(Value.percentWidth(0.50f,table));
        table.row();
        table.add(droidsSlider).fillX();
        table.row();
        table.add(droidsQualityLabel);
        table.add(droidsQuantityLabel);
        table.row();
        table.row();
        table.add(backToGameLabel);
        table.add(backToGameButton);


        uiStage.addActor(table);
    }

    @Override
    public void update(float dt)
    {
    }

}
