package com.csanysoft.donto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.csanysoft.donto.GlobalClasses.Assets;
import com.csanysoft.donto.MyBaseClasses.Scene2D.MyStage;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Majzer on 02/02/2018.
 */

public class GameStage extends MyStage {

    ArrayList<PlatformActor> platforms = new ArrayList<PlatformActor>();
    PlatformActor platformActor;
    AndroidActor androidActor;
    Random rand = new Random();
    TextButton btnOn;
    int i=0;


    public GameStage(Viewport viewport, Batch batch, Donto game) {
        super(viewport, batch, game);
        addActor(platformActor=new PlatformActor(i++,100));
        addActor(androidActor=new AndroidActor(Assets.manager.get(Assets.WALK_TEXTURE)));
        androidActor.setPosition(platformActor.getX(),platformActor.getY()+platformActor.getHeight()-8);
        for(;i < 11; i++) {
            addActor(platformActor=new PlatformActor(i*1000,rand.nextInt(500)+100));
            platforms.add(platformActor);
        }

        // TODO: 2018. 02. 02.  kommentet kitörölni
        //btnOn = new MyButton("", game.btnOn());
        /*

        btnOn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
            }
        });
        addActor(btnOn);
        */
    setDebugAll(true);
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(androidActor!=null){
            //TODO: Majd megcsinálom szebbé by ifa
            if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                androidActor.up();
            } else if(!(androidActor.getY()<100)) androidActor.down();

            setCameraMoveToXY(androidActor.getX()+200, 400);

            Array<Actor> actors = getActors();
            ArrayList<Actor> deleteActor = new ArrayList<Actor>();

            for(PlatformActor pa : platforms){
                if(pa.getY()<androidActor.getY()-200) {
                    deleteActor.add(pa);
                    addActor(platformActor=new PlatformActor(i*1000,rand.nextInt(500)+100));
                    i++;
                }
            }

            for (Actor a : deleteActor) {
                actors.removeValue(a, true);
            }
            setCameraMoveToXY(androidActor.getX(), androidActor.getY(), 1.5f);
        }
    }
}

