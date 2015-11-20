package com.norman.game.gameobjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.norman.game.gfhelpers.AssetLoader;

/**
 * Created by Norman on 11/16/2015.
 */
public class Ogre {
    private Animation currentAnimation;
    private Animation runAnimation;
    private float elapsedTime;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    public Ogre(int posx,int posy){
        position = new Vector2(posx,posy);
        velocity = new Vector2(0,0);
        runAnimation = AssetLoader.ogreRun;
        acceleration = new Vector2(0,0);
        currentAnimation = runAnimation;
    }

    public void update(float delta) {
        elapsedTime += delta;
        //speed handling
        position.add(velocity.cpy().scl(delta));
        velocity.add(acceleration.cpy().scl(delta));
    }

    public void updateReady(float delta){

    }

    public boolean collides (Hero hero) {
        return (position.x <= hero.getPosition().x);
    }

    public void die(){
        velocity.y = -300;
    }

    public Animation getCurrentAnimation() {
        return currentAnimation;
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    public void ready() {
        acceleration.x = 0;

    }

    public void start() {
        acceleration.x = -200;
    }
    
    public void onRestart(int x, int y){
        position.set(x,y);
    }

    public Vector2 getPosition() {
        return position;
    }
}
