package com.norman.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.norman.game.GFgame;
import com.norman.game.TweenAccessors.Value;
import com.norman.game.TweenAccessors.ValueAccessor;
import com.norman.game.gameobjects.Background;
import com.norman.game.gameobjects.Hero;
import com.norman.game.gameobjects.Ogre;
import com.norman.game.gfhelpers.AssetLoader;
import com.norman.game.ui.SimpleButton;

import java.util.List;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by Norman on 11/15/2015.
 */
public class GameRenderer {

    private OrthographicCamera cam;
    private GameWorld myWorld;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    private Hero hero;
    private Ogre ogre;

    private Background frontBg, backBg;

    //tween
    private TweenManager manager;
    private Value alpha = new Value();

    // Buttons
    private List<SimpleButton> menuButtons;
    private Color transitionColor;

    public GameRenderer(GameWorld world){
        cam = new OrthographicCamera();
        cam.setToOrtho(false, GFgame.WIDTH/2,GFgame.HEIGHT/2);
        myWorld = world;

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        initGameObjects();
        setupTweens();

        transitionColor = new Color();
        prepareTransition(255, 255, 255, .5f);
    }

    private void setupTweens() {
        Tween.registerAccessor(Value.class, new ValueAccessor());
        manager = new TweenManager();
        Tween.to(alpha, -1, .5f).target(0).ease(TweenEquations.easeOutQuad)
                .start(manager);
    }

    private void initGameObjects() {
        hero = myWorld.getHero();
        frontBg = myWorld.getFrontBg();
        backBg = myWorld.getBackBg();
        ogre = myWorld.getOgre();
    }


    public void prepareTransition(int r, int g, int b, float duration) {
        transitionColor.set(r / 255.0f, g / 255.0f, b / 255.0f, 1);
        alpha.setValue(1);
        Tween.registerAccessor(Value.class, new ValueAccessor());
        manager = new TweenManager();
        Tween.to(alpha, -1, duration).target(0)
                .ease(TweenEquations.easeOutQuad).start(manager);
    }

    private void drawBackground(){
        batcher.draw(backBg.getTexture(),backBg.getPosition().x,backBg.getPosition().y);
        batcher.draw(frontBg.getTexture(), frontBg.getPosition().x,frontBg.getPosition().y);

        batcher.draw(backBg.getTexture(),backBg.getloopPosition().x,backBg.getloopPosition().y);
        batcher.draw(frontBg.getTexture(), frontBg.getloopPosition().x,frontBg.getloopPosition().y);
    }

    private void drawMenuUI() {
        // Draw shadow first
        AssetLoader.shadow.draw(batcher, "Tap to start", cam.viewportWidth/2, 160);
        AssetLoader.shadow.draw(batcher, "Game by Norman and Niko", cam.viewportWidth/2-50, 80);

        // Draw text
        AssetLoader.font.draw(batcher, "Tap to start", cam.viewportWidth/2, 160);
        AssetLoader.font.draw(batcher, "Game by Norman and Niko", cam.viewportWidth/2-50, 80);

        //for (SimpleButton button : menuButtons) {
        //    button.draw(batcher);
        //}

    }

    /*private void drawScore(){
        // Convert integer into String
        String life = myWorld.getHp() + "";

        // Draw shadow first
        AssetLoader.shadow.draw(batcher, "" + myWorld.getHp(), (5)
                - (3 * life.length()), 6);
        // Draw text
        AssetLoader.font.draw(batcher, "" + myWorld.getHp(), (5)
                - (3 * life.length() - 1), 5);
    }*/

    private void drawTransition(float delta) {
        if (alpha.getValue() > 0) {
            manager.update(delta);
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(transitionColor.r, transitionColor.g,
                    transitionColor.b, alpha.getValue());
            shapeRenderer.rect(0, 0, cam.viewportWidth, cam.viewportHeight);
            shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);

        }
    }

    private void drawHero(){
        batcher.draw(hero.getCurrentAnimation().getKeyFrame(hero.getElapsedTime()),hero.getPosition().x,hero.getPosition().y);
    }

    private void drawEnemy(){
        batcher.draw(ogre.getCurrentAnimation().getKeyFrame(ogre.getElapsedTime()),ogre.getPosition().x,ogre.getPosition().y);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        // Begin SpriteBatch
        batcher.begin();
        batcher.enableBlending();
        drawBackground();

        if (myWorld.isRunning()) {
            drawHero();
            drawEnemy();
            //drawScore();
        } else if (myWorld.isReady()) {
            drawHero();
            //drawScore();
        } else if (myWorld.isMenu()) {
            drawHero();
            drawMenuUI();
        } else if (myWorld.isGameOver()) {
            drawHero();
            drawEnemy();
            //drawScore();
        }


        // End SpriteBatch
        batcher.end();

        drawTransition(delta);


    }

}
