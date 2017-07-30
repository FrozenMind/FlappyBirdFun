package com.flappybird.game.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.flappybird.game.FlappyBird;

import javax.swing.text.View;

/**
 * Created by valentin on 7/30/17.
 */

//this class creates a new viewport, so score label has a fixed position
public class Score {
    public Stage stage;
    private Viewport viewport;
    private int scoreVal;
    private Label scoreLbl;

    public Score(SpriteBatch sb){
        this.scoreVal = 0;
        this.viewport = new FitViewport(FlappyBird.WIDTH, FlappyBird.HEIGHT, new OrthographicCamera());
        this.stage = new Stage(this.viewport, sb);

        this.scoreLbl = new Label("Score: " + this.scoreVal, new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        this.scoreLbl.setPosition(FlappyBird.WIDTH / 2 - this.scoreLbl.getWidth() / 2, FlappyBird.HEIGHT - 50);
        this.stage.addActor(this.scoreLbl);
    }

    public void update(){
        this.scoreLbl.setText("Score: " + this.scoreVal);
        this.stage.act();
        this.stage.draw();
    }

    public void increase(){
        this.scoreVal++;
    }

    public void reset(){
        this.scoreVal = 0;
    }

    public Stage getStage(){
        return this.stage;
    }

    public int getScore(){
        return this.scoreVal;
    }

    public void dispose(){

    }
}
