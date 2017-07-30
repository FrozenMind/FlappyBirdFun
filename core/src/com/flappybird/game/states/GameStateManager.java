package com.flappybird.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by valentin on 7/29/17.
 */

//handles the game state, i.e. between menu and running game
public class GameStateManager {
    private Stack<State> states; //stack so we pop the top one, to return to one before
    public SpriteBatch sb;

    public GameStateManager(SpriteBatch sb){
        this.states = new Stack<State>();
        this.sb = sb;
    }
    //push new state
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
    } //update the one on the top

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    } //render the one on the top
}
