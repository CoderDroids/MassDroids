
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.CoderDroids.MassDroids.MyGame;
import com.CoderDroids.MassDroids.base.GameBeta;
import com.CoderDroids.MassDroids.base.ScreenBeta;

public class ReportScreen extends ScreenBeta {

    public ReportScreen(GameBeta game )
    {
        super(game);
    }

    Button backToGameButton;
    Label ReportNo1Label;
    Label infrastructureText;
    int titleFontSize;
    int fontSize;

    @Override
    public void initialize()
    {
        Skin skin = new Skin(Gdx.files.internal("skins/quantum-horizon/skin/quantum-horizon-ui.json"));
        titleFontSize = 5;
        fontSize = 3;


        Label reportText = new Label("Reports", skin);
        reportText.setPosition( MyGame.SCREEN_WIDTH * 0.45f, MyGame.SCREEN_HEIGHT * 0.7f);
        reportText.setAlignment( Align.center );
        reportText.setFontScale(6, 6);

        uiStage.addActor(reportText);

        ReportNo1Label = new Label("Report #1", skin);
        ReportNo1Label.setFontScale(titleFontSize);
        ReportNo1Label.setPosition( MyGame.SCREEN_WIDTH * 0.20f, MyGame.SCREEN_HEIGHT * 0.7f - 150);
        uiStage.addActor(ReportNo1Label);

        infrastructureText = new Label("This is where all of the text relating to a report would go", skin);
        infrastructureText.setFontScale(fontSize);
        infrastructureText.setWrap(true);
        infrastructureText.setPosition( MyGame.SCREEN_WIDTH * 0.20f, MyGame.SCREEN_HEIGHT * 0.7f - 300);
        uiStage.addActor(infrastructureText);



        backToGameButton = new Button(skin);
        backToGameButton.addListener(new ClickListener(){ public void clicked(InputEvent event, float x, float y) {
                                             mainGame.setScreen( new GameplayScreen(mainGame) ); }});

        backToGameButton.setSize(100,100);
        backToGameButton.setPosition( MyGame.SCREEN_WIDTH * 0.50f, MyGame.SCREEN_HEIGHT * 0.7f - 600);
        uiStage.addActor(backToGameButton);
    }

    @Override
    public void update(float dt)
    {

    }
}
