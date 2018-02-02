package com.csanysoft.donto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.csanysoft.donto.MyBaseClasses.Scene2D.MyScreen;

/**
 * Created by Majzer on 02/02/2018.
 */

public class MainScreen extends MyScreen {

    GameStage gameStage;

    public MainScreen(Donto game) {
        super(game);
        setBackGroundColor(0,0,0);
        gameStage = new GameStage(new ExtendViewport(1024,768),spriteBatch,game);
        InputMultiplexer im = new InputMultiplexer();
        im.addProcessor(gameStage);
        gameStage.addBackEventStackListener();
        Gdx.input.setInputProcessor(im);
    }

    @Override
    public void init() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        gameStage.act(delta);
        gameStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        gameStage.resize(width,height);
    }
}
