package com.CoderDroids.MassDroids.screen;

import com.CoderDroids.MassDroids.base.GameBeta;
import com.CoderDroids.MassDroids.base.ScreenBeta;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class AttackStage extends Stage {
    Skin skin;

    Label PlayerBattleReadiness;
    Label EnemyBattleReadiness;
    Label BattleResults;

    TextButton AttackButton;
    TextButton BackButton;

    int AttackerValue;
    int DefenderValue;

    int AttackerResults;
    int DefenderResults;

    public AttackStage()
    {
        initialize();
    }

    public void initialize() {
        Skin skin = new Skin(Gdx.files.internal("skins/star-soldier/skin/star-soldier-ui.json"));

        Table table = new Table();
        table.setFillParent(true);

        PlayerBattleReadiness = new Label("Player Attack: " + AttackerValue, skin);
        PlayerBattleReadiness.setFontScale(3);
        PlayerBattleReadiness.setAlignment(Align.center);
        PlayerBattleReadiness.setWrap(true);
        PlayerBattleReadiness.setSize(Gdx.graphics.getWidth()/2, 150);
        PlayerBattleReadiness.setPosition(Gdx.graphics.getWidth()/2 - PlayerBattleReadiness.getWidth(), Gdx.graphics.getHeight() - PlayerBattleReadiness.getHeight() * 2);

        EnemyBattleReadiness = new Label("Opponent Defense: " + DefenderValue, skin);
        EnemyBattleReadiness.setFontScale(3);
        EnemyBattleReadiness.setAlignment(Align.center);
        EnemyBattleReadiness.setWrap(true);
        EnemyBattleReadiness.setSize(Gdx.graphics.getWidth()/2, 150);
        EnemyBattleReadiness.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight() - EnemyBattleReadiness.getHeight() * 2);

        table.add(PlayerBattleReadiness);
        table.row();
        table.add(EnemyBattleReadiness);
        table.row();

        BattleResults = new Label("Attacker lost: " + AttackerResults + " units. Defender lost: " + DefenderResults, skin);
        BattleResults.setFontScale(3);
        BattleResults.setAlignment(Align.center);
        BattleResults.setWrap(true);
        BattleResults.setVisible(false);
        BattleResults.setSize(Gdx.graphics.getWidth()/2, 400);
        BattleResults.setPosition(Gdx.graphics.getWidth()/2 - BattleResults.getWidth()/2, Gdx.graphics.getHeight()/2);

        table.add(BattleResults);
        table.row();

        AttackButton = new TextButton("Attack", skin);
        AttackButton.setSize(400, 200 );
        AttackButton.setPosition(Gdx.graphics.getWidth()/2 - AttackButton.getWidth()/2,Gdx.graphics.getHeight()/4);
        AttackButton.getLabel().setFontScale(3.0f);
        AttackButton.addListener(new ClickListener(){
                                   @Override
                                   public void clicked(InputEvent event, float x, float y) {
                                        BattleResults.setVisible(true);
                                   }
                               }
        );



        BackButton = new TextButton("Back", skin);
        BackButton.setSize(400, 200 );
        BackButton.setPosition(Gdx.graphics.getWidth()/2 - AttackButton.getWidth()/2,AttackButton.getY() - BackButton.getHeight());
        BackButton.getLabel().setFontScale(3.0f);
        BackButton.addListener(new ClickListener(){
                                     @Override
                                     public void clicked(InputEvent event, float x, float y) {

                                     }
                                 }
        );

        table.add(AttackButton);
        table.add(BackButton);
        table.row();


//        addActor(PlayerBattleReadiness);
//        addActor(EnemyBattleReadiness);
//        addActor(BattleResults);
//        addActor(AttackButton);
//        addActor(BackButton);


        addActor(table);


    }
}
