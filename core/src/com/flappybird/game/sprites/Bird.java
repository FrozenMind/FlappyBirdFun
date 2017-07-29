package com.flappybird.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by valentin on 7/29/17.
 */

public class Bird {
    private static final int GRAVITY=-15;
    private static final int MOVEMENT=100;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Texture texture;
    private Sound flap;

    public Bird(int x, int y){
        this.position = new Vector3(x, y, 0);    //0 for z axis
        this.velocity = new Vector3(0, 0, 0);
        this.texture = new Texture("birdanimation.png");
        this.birdAnimation = new Animation(new TextureRegion((this.texture)), 3, 0.5f);
        //invisible player rectangle
        this.bounds = new Rectangle(x, y, this.texture.getWidth() / 3, this.texture.getHeight());
        this.flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
        this.flap.play(0.1f);
    }

    public void update(float dt){
        this.birdAnimation.update(dt);
        if(this.position.y > 0)
            this.velocity.add(0, this.GRAVITY, 0);    //add gravity to bird
        this.velocity.scl(dt);   //scale by deltatime
        this.position.add(this.MOVEMENT * dt, this.velocity.y, 0);
        if(this.position.y < 0)
            this.position.y = 0;
        this.velocity.scl(1/dt);
        this.bounds.setPosition(this.position.x, this.position.y);
    }

    public void jump(){
        this.velocity.y = 250;
        flap.play();
    }

    public Rectangle getBounds(){
        return this.bounds;
    }

    public Vector3 getPosition() {
        return this.position;
    }

    public TextureRegion getTexture() {
        return this.birdAnimation.getFrame();
    }

    public void dispose(){
        this.texture.dispose();
        this.flap.dispose();
    }

}
