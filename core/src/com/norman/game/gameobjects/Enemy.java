package com.norman.game.gameobjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Norman on 11/16/2015.
 */
public abstract class Enemy {
    public Vector2 position;
    public Vector2 velocity,acceleration;

    public Enemy(float V,float posx,float posy){
        this.acceleration.x = V;

    }

    public abstract void update(float delta);

}
