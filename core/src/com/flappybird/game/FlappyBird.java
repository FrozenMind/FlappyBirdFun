package com.flappybird.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flappybird.game.states.GameStateManager;
import com.flappybird.game.states.MenuState;

//main class, to control everything
public class FlappyBird extends ApplicationAdapter {
	public static final int WIDTH=480;
	public static final int HEIGHT=800;
	public static final String TITLE="Flappy Bird";

    private GameStateManager gsm; //manage the diverent states (menu, game, highscoreboard, lossscreen)
	private SpriteBatch batch; //draw area of the game

    private Music music; //just music
	
	@Override
	public void create () {
        this.batch = new SpriteBatch();
        this.gsm = new GameStateManager(batch);
        this.music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3")); //load music file
        this.music.setLooping(true); //endless loop music
        this.music.setVolume(0.1f); //set volume
        this.music.play(); //start music
        Gdx.gl.glClearColor(1, 0, 0, 1); //wipes the screen and redraws everything fresh
        this.gsm.push(new MenuState(this.gsm)); //load menu state
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.gsm.update(Gdx.graphics.getDeltaTime()); //update state
        this.gsm.render(this.batch); //render state
	}

	//clean up memory
	@Override
	public void dispose () {
        this.batch.dispose();
        this.music.dispose();
	}
}
