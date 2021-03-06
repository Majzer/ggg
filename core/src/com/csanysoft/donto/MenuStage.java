package com.csanysoft.donto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
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
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by tanulo on 2018. 01. 15..
 */

public class MenuStage extends MyStage {

    TextButton btnStart , btnExit;
    OneSpriteStaticActor penlogo , whiterect;
    OneSpriteStaticActor bg;
    Label ismerteto;
    PlatformActor platformActor1 , platformActor2 , platformActor3 , platformActor4;

    Music menu;
    Music theme;

    public MenuStage(Viewport viewport, Batch batch, Donto game) {
        super(viewport, batch, game);


        addActor(platformActor1 = new PlatformActor(650 , 35));
        addActor(platformActor3 = new PlatformActor(200 , 150));

        theme = Assets.manager.get(Assets.MenuTheme);
        menu = Assets.manager.get(Assets.ThemeSound);
        menu.pause();
        theme.play();
        theme.setVolume(0.8f);
        theme.setLooping(true);

        //setDebugAll(true);
    }

    @Override
    public void act(float delta) {
    }

    public Label.LabelStyle getLabelStyle() {
        Label.LabelStyle style;
        style = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle();
        style.font = Assets.manager.get(Assets.ARIAL_30_FONT);
        style.fontColor = Color.BLACK;
        return style;
    }

    @Override
    public void init () {

        ismerteto = new Label("Ön meddig tud eljutni PENdroid-dal?\nPróbája meg ön is!", getLabelStyle());
        ismerteto.setPosition(getViewport().getWorldHeight()/2-ismerteto.getHeight(),getViewport().getWorldWidth()/2.5f-ismerteto.getWidth()/2.5f);
        ismerteto.setZIndex(100);
        addActor(ismerteto);
        btnStart = new MyButton("", game.btnStart());
        btnStart.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new MainScreen(game));
            }
        });
        btnStart.setZIndex(2);
        addActor(btnStart);

        btnExit = new MyButton("", game.btnExit());
        btnExit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.exit();
            }
        });
        btnExit.setZIndex(2);
        addActor(btnExit);

        btnStart.setPosition(getViewport().getWorldWidth()/2-btnStart.getWidth()/2, getViewport().getWorldHeight()/1.5f);
        btnExit.setPosition(btnStart.getX() , btnStart.getY()-btnExit.getHeight()-50);


        btnStart.setZIndex(5);
        btnExit.setZIndex(5);
    }

    @Override
    public void dispose () {
        super.dispose();
    }

    }

