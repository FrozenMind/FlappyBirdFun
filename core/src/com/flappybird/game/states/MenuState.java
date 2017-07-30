package com.flappybird.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flappybird.game.FlappyBird;

/**
 * Created by valentin on 7/29/17.
 */

//menu screen
public class MenuState extends State{
    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        this.background = new Texture("bg.png");
        this.playBtn = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        //on click anywhere start an game
        if(Gdx.input.justTouched()){
            this.gsm.set(new PlayState(this.gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();  //check if user done sth
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin(); //open box
        //draw bg & playbutton
        sb.draw(this.background, 0, 0); //texture,x,y,{width},{height}
        sb.draw(this.playBtn, cam.position.x - playBtn.getWidth() / 2, cam.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        this.background.dispose();
        this.playBtn.dispose();
        System.out.println("Menu state disposed");
    }
}
