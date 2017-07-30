package com.flappybird.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by valentin on 7/30/17.
 */

//fly animation for bird
public class Animation {
    private Array<TextureRegion> frames; //store all frames
    private float maxFrameTime; //how long a frame should stay
    private float currentFrame; //time that animation has been in the current frame
    private int frameCount; //number of frames in animations
    private int frame; //current frame we're in

    public Animation(TextureRegion region, int frameCount, float cycleTime){
        this.frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount; //get width of a single sprite
        //split all frames in one sprite and add them one by one to the array
        for(int i = 0; i < frameCount; i++){
            this.frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        this.maxFrameTime = cycleTime / frameCount;
        this.frame = 0;
    }

    public void update(float dt){
        this.currentFrame += dt;
        //if frame has been there to long, take the next one
        if(this.currentFrame > this.maxFrameTime){
            this.frame++;
            this.currentFrame = 0;
        }
        //if frame is out of range, get the fisrt one
        if(this.frame >= this.frameCount)
            this.frame = 0;
    }
    //return current frame
    public TextureRegion getFrame(){
        return this.frames.get(this.frame);
    }
}
