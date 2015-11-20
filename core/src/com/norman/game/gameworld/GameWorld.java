package com.norman.game.gameworld;

import com.badlogic.gdx.Game;
import com.norman.game.gameobjects.Background;
import com.norman.game.gameobjects.EnemyHandler;
import com.norman.game.gameobjects.Hero;
import com.norman.game.gameobjects.Ogre;
import com.norman.game.gfhelpers.AssetLoader;

/**
 * Created by Norman on 11/15/2015.
 */
public class GameWorld {
    private Hero hero;
    private Ogre ogre;
    private Background frontBg, backBg;

    private GameRenderer renderer;
    private GameState currentState;

    public enum GameState {
        MENU, READY, RUNNING, GAMEOVER
    }

    public Background getFrontBg() {
        return frontBg;
    }

    public Background getBackBg() {
        return backBg;
    }

    public GameWorld(){
        currentState = GameState.MENU;
        hero = new Hero(20,21);
        frontBg = new Background(AssetLoader.cbg,-1);
        backBg = new Background(AssetLoader.bg,0);
        ogre = new Ogre(200,21);
    }

    public void updateRunning(float delta) {
        if (delta > .15f) {
            delta = .15f;
        }

        hero.update(delta);
        backBg.update(delta);
        frontBg.update(delta);
        ogre.update(delta);

        if (ogre.collides(hero)){
            ogre.die();
        }

    }

    public void update(float delta){


        switch (currentState) {
            case READY:
            case MENU:
                updateReady(delta);
                break;

            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;
        }

    }

    private void updateReady(float delta){
        hero.updateReady();
        frontBg.updateReady(delta);
        backBg.updateReady(delta);
        ogre.updateReady(delta);
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }

    public boolean isMenu() {
        return currentState == GameState.MENU;
    }

    public void start() {

        currentState = GameState.RUNNING;
        hero.start();
        ogre.start();
    }

    public void ready() {

        currentState = GameState.READY;
        hero.ready();
        ogre.ready();
        renderer.prepareTransition(0, 0, 0, 1f);

    }

    public void setRenderer(GameRenderer renderer) { this.renderer = renderer; }

    public void restart () {
        currentState = GameState.READY;
        hero.onRestart(20, 21);
        ogre.onRestart(200,21);
        frontBg.onRestart();
        backBg.onRestart();
        renderer.prepareTransition(255,255,255,1f);
    }

    public Hero getHero() { return hero; }

    public Ogre getOgre() { return ogre; }


}

