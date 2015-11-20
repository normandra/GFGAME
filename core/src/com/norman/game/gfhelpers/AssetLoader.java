package com.norman.game.gfhelpers;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Norman on 11/15/2015.
 */
public class AssetLoader {

    private static TextureAtlas mainAtlas;

    public static TextureRegion splash, bg, cbg;

    public static Animation heroRun;
    public static Animation heroAttack;
    public static Animation heroIdle;

    public static Animation ogreRun;

    public static BitmapFont font, shadow;

    public static void load(){
        mainAtlas = new TextureAtlas(Gdx.files.internal("data/alpha.pack"));

        //Hero Animation------------------------------------------------
        heroRun = new Animation(1/15f,
                (mainAtlas.findRegion("MainCharacterRun0000")),
                (mainAtlas.findRegion("MainCharacterRun0001")),
                (mainAtlas.findRegion("MainCharacterRun0002")),
                (mainAtlas.findRegion("MainCharacterRun0003")),
                (mainAtlas.findRegion("MainCharacterRun0004")),
                (mainAtlas.findRegion("MainCharacterRun0005")),
                (mainAtlas.findRegion("MainCharacterRun0006")),
                (mainAtlas.findRegion("MainCharacterRun0007")));
        heroRun.setPlayMode(Animation.PlayMode.LOOP);

        heroAttack = new Animation(1/15f,
                (mainAtlas.findRegion("MainCharacterRun+Attack0000")),
                (mainAtlas.findRegion("MainCharacterRun+Attack0001")),
                (mainAtlas.findRegion("MainCharacterRun+Attack0002")),
                (mainAtlas.findRegion("MainCharacterRun+Attack0003")),
                (mainAtlas.findRegion("MainCharacterRun+Attack0004")),
                (mainAtlas.findRegion("MainCharacterRun+Attack0005")),
                (mainAtlas.findRegion("MainCharacterRun+Attack0006")),
                (mainAtlas.findRegion("MainCharacterRun+Attack0007")));

        heroIdle = new Animation(1/8f,
                (mainAtlas.findRegion("MainCharacterIdle0000")),
                (mainAtlas.findRegion("MainCharacterIdle0001")),
                (mainAtlas.findRegion("MainCharacterIdle0002")),
                (mainAtlas.findRegion("MainCharacterIdle0003")));

        heroIdle.setPlayMode(Animation.PlayMode.LOOP);
        //Ogre Animation-------------------------------------------------
        ogreRun = new Animation(1/15f,
                (mainAtlas.findRegion("GoblinRun0000")),
                (mainAtlas.findRegion("GoblinRun0001")),
                (mainAtlas.findRegion("GoblinRun0002")),
                (mainAtlas.findRegion("GoblinRun0003")),
                (mainAtlas.findRegion("GoblinRun0004")),
                (mainAtlas.findRegion("GoblinRun0005")),
                (mainAtlas.findRegion("GoblinRun0006")),
                (mainAtlas.findRegion("GoblinRun0007")));
        ogreRun.setPlayMode(Animation.PlayMode.LOOP);
        //backgrounds---------------------------------------------------
        splash = new TextureRegion(mainAtlas.findRegion("logo"));
        bg = new TextureRegion(mainAtlas.findRegion("bluebg"));
        cbg = new TextureRegion(mainAtlas.findRegion("castlebg"));

        //font------------------------------
        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.getData().setScale(.25f, .25f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.getData().setScale(.25f, .25f);

    }

    public static void dispose(){
        mainAtlas.dispose();
        font.dispose();
        shadow.dispose();
    }
}
