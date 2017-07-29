package com.flappybird.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by valentin on 7/30/17.
 */

public class Tube {
    public static final int TUBE_WIDTH=52;
    private static final int FLUCTUATION=130;   //tube can move up & down between 0 and this
    private static final int TUBE_GAP=100;  //min tube gap between both tubes
    private static final int LOWEST_OPENING=120;    //lowest top of the bottom tube
    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBotTube;
    private Rectangle boundsTop, boundsBot;
    private Random rand;

    public Tube(float x){
        this.topTube = new Texture("toptube.png");
        this.bottomTube = new Texture("bottomtube.png");
        this.rand = new Random();

        this.posTopTube = new Vector2(x, this.rand.nextInt(this.FLUCTUATION) + this.TUBE_GAP + this.LOWEST_OPENING);
        this.posBotTube = new Vector2(x, this.posTopTube.y - this.TUBE_GAP - this.bottomTube.getHeight());
        //create invisible rectangle to handle collision
        this.boundsTop = new Rectangle(this.posTopTube.x, this.posTopTube.y, this.topTube.getWidth(), this.topTube.getHeight()); //x,y,width,height
        this.boundsBot = new Rectangle(this.posBotTube.x, this.posBotTube.y, this.bottomTube.getWidth(), this.bottomTube.getHeight());
    }

    //reposition all tubes, to x value
    public void reposition(float x){
        this.posTopTube.set(x, this.rand.nextInt(this.FLUCTUATION) + this.TUBE_GAP + this.LOWEST_OPENING);
        this.posBotTube.set(x, this.posTopTube.y - this.TUBE_GAP - this.bottomTube.getHeight());
        this.boundsTop.setPosition(this.posTopTube.x, this.posTopTube.y);
        this.boundsBot.setPosition(this.posBotTube.x, this.posBotTube.y);
    }

    public boolean collides(Rectangle player){
        //returns true if player rect hits tube rects
        return player.overlaps(this.boundsTop) || player.overlaps(this.boundsBot);
    }

    public Texture getTopTube() {
        return this.topTube;
    }

    public Texture getBottomTube() {
        return this.bottomTube;
    }

    public Vector2 getPosTopTube() {
        return this.posTopTube;
    }

    public Vector2 getPosBotTube() {
        return this.posBotTube;
    }

    public void dispose(){
        this.topTube.dispose();
        this.bottomTube.dispose();
    }
}
