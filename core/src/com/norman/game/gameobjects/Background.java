package com.norman.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Norman on 10/30/2015.
 */
public class Background {
    private Vector2 position;
    private Vector2 position2;
    private TextureRegion bg;
    private float velX;

    public Background(TextureRegion T, float vel){
        this.velX = vel;
        position = new Vector2(0, 0);
        position2 = new Vector2(800, 0);    // make this dynamic
        bg = T;
    }

    public void update(float dt){
        if (position.x == -800){
            position.x = 800;
        }
        if (position2.x == -800){
            position2.x = 800;
        }
        position.x += velX;
        position2.x += velX;
    }

    public void updateReady(float dt){

    }

    public void onRestart(){
        position.x = 0;
        position2.x = 800;
    }

    public Vector2 getPosition() { return position; }
    public Vector2 getloopPosition() {return position2;}
    public TextureRegion getTexture() { return bg; }

}
