package com.flappybird.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.flappybird.game.FlappyBird;
import com.flappybird.game.sprites.Bird;
import com.flappybird.game.sprites.Score;
import com.flappybird.game.sprites.Tube;

/**
 * Created by valentin on 7/29/17.
 */

//the play state, actual game
public class PlayState extends State{
    private static final int TUBE_SPACING=125; //space between 2 tubes
    private static final int TUBE_COUNT=4; //tubes
    private static final int GROUND_Y_OFFSET=-30; //cause ground pic is too big

    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private Score score;

    private Array<Tube> tubes;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        this.bird = new Bird(50, 200);
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2); //false means coordinate symstem 0/0 is bottom left
        this.bg = new Texture("bg.png");
        this.ground = new Texture("ground.png");
        this.groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, this.GROUND_Y_OFFSET); //there are 2 grounds to have a ground movement
        this.groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + this.ground.getWidth(), this.GROUND_Y_OFFSET);
        //add tubes
        this.tubes = new Array<Tube>();
        for(int i = 1; i <= this.TUBE_COUNT; i++){
            this.tubes.add(new Tube(i* (this.TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
        this.score = new Score(gsm.sb);
    }

    @Override
    protected void handleInput() {
        //if user touches screen let bird jump
        if(Gdx.input.justTouched())
            this.bird.jump();
    }

    @Override
    public void update(float dt) {
        handleInput(); //check for user input
        updateGround(); //update ground movement
        this.bird.update(dt); //update bird
        cam.position.x = bird.getPosition().x + 80; //reposition cam
        //move tubes
        for(int i = 0; i < this.tubes.size; i++){    //for each doesn't work here --> iterator err (?)
            Tube tube = this.tubes.get(i);
            if(cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
                this.score.increase();
            }
            //if bird collides with a tube reset game
            if(tube.collides(this.bird.getBounds())){
                this.gsm.set(new PlayState(this.gsm));
                this.score.reset();
            }
        }
        //check if birds hits bottom --> reset
        if(this.bird.getPosition().y <= this.ground.getHeight() + this.GROUND_Y_OFFSET)
            this.gsm.set(new PlayState(this.gsm));
        cam.update();
        this.score.update();
    }

    //add all textures to the SpriteBatch
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(this.bg, cam. position.x - (cam.viewportWidth / 2), 0);
        sb.draw(this.bird.getTexture(), this.bird.getPosition().x, this.bird.getPosition().y);
        for(Tube tube : this.tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        //draw ground
        sb.draw(this.ground, this.groundPos1.x, this.groundPos1.y);
        sb.draw(this.ground, this.groundPos2.x, this.groundPos2.y);
        sb.end();
    }

    @Override
    public void dispose() {
        this.bg.dispose();
        this.bird.dispose();
        for(Tube tube : this.tubes){
            tube.dispose();
        }
        this.ground.dispose();
        System.out.println("Play state disposed");
    }
    //move ground
    private void updateGround(){
        //if grounds are out of bounds reset
        if(cam.position.x - (cam.viewportWidth) / 2 > this.groundPos1.x + this.ground.getWidth())
            this.groundPos1.add(ground.getWidth() * 2, 0);
        if(cam.position.x - (cam.viewportWidth / 2) > this.groundPos2.x + this.ground.getWidth())
            this.groundPos2.add(this.ground.getWidth() * 2, 0);
    }
}
