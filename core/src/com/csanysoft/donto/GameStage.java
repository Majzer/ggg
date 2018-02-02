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
import com.csanysoft.donto.MyBaseClasses.Scene2D.ShapeType;

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
        platforms.add(platformActor);
        addActor(androidActor=new AndroidActor(Assets.manager.get(Assets.WALK_TEXTURE)));
        androidActor.setPosition(platformActor.getX(),platformActor.getY()+platformActor.getHeight()+20);
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

    boolean removePlatformFromArrayList = false;
    PlatformActor removablePlatform = null;

    @Override
    public void init() {

    }


    @Override
    public void act(float delta) {
        super.act(delta);

        if(androidActor!=null){
            androidActor.setSpeedX(4);
            //TODO: Majd megcsinálom szebbé by ifa
            if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                androidActor.up();
            } else if(!(androidActor.getY()<100)) androidActor.down();

            setCameraMoveToXY(androidActor.getX()+200, 400);

            Array<Actor> actors = getActors();
            ArrayList<Actor> deleteActor = new ArrayList<Actor>();

            for(PlatformActor pa : platforms){
                if(pa.getX()<androidActor.getX()-getViewport().getScreenWidth()*2) {
                    deleteActor.add(pa);
                    addActor(platformActor=new PlatformActor(i*1000,rand.nextInt(500)+100));
                    removePlatformFromArrayList = true;
                    removablePlatform = pa;
                    i++;
                }

                if(androidActor.overlaps(ShapeType.Rectangle,pa)){
                    System.out.println("Hozzáér");
                    float aY = androidActor.getY();
                    float paYH = pa.getY()+pa.getHeight();
                    System.out.println(aY >= paYH-10);
                    if(aY >= paYH-10 || aY >= paYH-11){
                        androidActor.setY(paYH);
                        System.out.println("teteje");
                    }
                    else if(aY < paYH && aY+androidActor.getHeight() > pa.getY())androidActor.setSpeedX(0);
                }
            }

            if(removePlatformFromArrayList){
                platforms.add(platformActor);
                platforms.remove(removablePlatform);
                removePlatformFromArrayList=false;
            }

            for (Actor a : deleteActor) {
                actors.removeValue(a, true);
            }
            setCameraMoveToXY(androidActor.getX(), androidActor.getY(), 1.5f);
        }
    }
}

