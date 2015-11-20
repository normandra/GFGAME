package com.norman.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.norman.game.GFgame;
import com.norman.game.gameworld.GameRenderer;
import com.norman.game.gameworld.GameWorld;
import com.norman.game.gfhelpers.InputHandler;

/**
 * Created by Norman on 11/15/2015.
 */
public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;

    public GameScreen() {
        world = new GameWorld();
        renderer = new GameRenderer(world);
        world.setRenderer(renderer);

        Gdx.input.setInputProcessor(new InputHandler(world, Gdx.graphics.getWidth()/ GFgame.WIDTH, Gdx.graphics.getHeight()/ GFgame.HEIGHT));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        world.update(delta);
        renderer.render(delta);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
