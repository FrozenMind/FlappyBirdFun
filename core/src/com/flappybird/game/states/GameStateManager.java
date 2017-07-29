package com.flappybird.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by valentin on 7/29/17.
 */

//handles the game state loops, i.e. between menu and running game

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    public void push(State state){
        states.push(state);
    }
    //remove state and dispose it
    public void pop(){
        states.pop().dispose();
    }

    //pushs and pop in one method
    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt){   //dt=deltatime
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
