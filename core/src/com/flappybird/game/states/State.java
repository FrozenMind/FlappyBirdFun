package com.flappybird.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by valentin on 7/29/17.
 */

//abstract class for states, to make sure all methods are there
public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    protected State(GameStateManager gsm){
        this.gsm = gsm;
        this.cam = new OrthographicCamera();
        this.mouse = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public  abstract void render(SpriteBatch sb);   //container for everything that need to rendered
    public abstract void dispose();
}

