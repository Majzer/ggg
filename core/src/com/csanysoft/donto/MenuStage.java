package com.csanysoft.donto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.csanysoft.donto.GlobalClasses.Assets;
import com.csanysoft.donto.MyBaseClasses.Scene2D.MyButton;
import com.csanysoft.donto.MyBaseClasses.Scene2D.MyStage;
import com.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteStaticActor;

/**
 * Created by tanulo on 2018. 01. 15..
 */

public class MenuStage extends MyStage {

    TextButton btnStart , btnExit;
    OneSpriteStaticActor penlogo , whiterect;
    OneSpriteStaticActor bg;
    Music menu;

    public MenuStage(Viewport viewport, Batch batch, Donto game) {
        super(viewport, batch, game);
        menu = Assets.manager.get(Assets.MenuTheme);

        menu.play();
        menu.setVolume(0.4f);
        menu.setLooping(true);


    }

    @Override
    public void act(float delta) {
    }


    @Override
    public void init () {

        btnStart = new MyButton("", game.btnStart());
        btnStart.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new MainScreen(game));
            }
        });
        addActor(btnStart);

        btnExit = new MyButton("", game.btnExit());
        btnExit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.exit();
            }
        });
        addActor(btnExit);

        btnStart.setPosition(getViewport().getScreenWidth()/2-100, getViewport().getWorldHeight()/2);
        btnExit.setPosition(btnStart.getX() , btnStart.getY()-btnExit.getHeight()-50);

        btnStart.setZIndex(2);
        btnExit.setZIndex(3);
    }

    @Override
    public void dispose () {
        super.dispose();
    }

    }

