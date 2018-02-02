package com.csanysoft.donto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Music;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.csanysoft.donto.GlobalClasses.Assets;
import com.csanysoft.donto.MyBaseClasses.Scene2D.MyStage;
import com.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteStaticActor;
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
    OneSpriteStaticActor bg;
    TextButton btnOn;
    int i=0;
    Music sound;


    public GameStage(Viewport viewport, Batch batch, Donto game) {
        super(viewport, batch, game);


        platformActor.setZIndex(3);
        androidActor.setZIndex(2);
        bg.setZIndex(1);
        sound = Assets.manager.get(Assets.ThemeSound);

        addActor(platformActor=new PlatformActor(i++,100));
        platforms.add(platformActor);
        platformActor.setZIndex(3);
        addActor(androidActor=new AndroidActor(Assets.manager.get(Assets.WALK_TEXTURE)));
        androidActor.setPosition(platformActor.getX(),platformActor.getY()+platformActor.getHeight()+20);
        for(;i < 11; i++) {
            addActor(platformActor=new PlatformActor(i*1000,rand.nextInt(500)+100));
            platformActor.setZIndex(3);
            platforms.add(platformActor);
        }

        bg = new OneSpriteStaticActor(Assets.manager.get(Assets.BACKGROUND_TEXTURE));
        addActor(bg);
        bg.setZIndex(1);
        androidActor.setZIndex(2);

        sound.play();
        sound.setVolume(0.4f);
        sound.setLooping(true);


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
    boolean onPlatform = false;

    @Override
    public void init() {

    }


    @Override
    public void act(float delta) {
        super.act(delta);
        if(androidActor!=null){

            if(bg!=null) bg.setPosition(androidActor.getX()-200, androidActor.getY()-200);

            androidActor.setSpeedX(4);
            //TODO: Majd megcsinálom szebbé by ifa
            if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                androidActor.up();
            } else if(!onPlatform) androidActor.down();
            else androidActor.land();

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
                    onPlatform = true;
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
                platformActor.setZIndex(3);
                platforms.remove(removablePlatform);
                removePlatformFromArrayList=false;
            }

            for (Actor a : deleteActor) {
                actors.removeValue(a, true);
            }
            setCameraMoveToXY(androidActor.getX(), androidActor.getY(), 1.5f);
        }


        if(androidActor.getY() > 800) {
            if(rand.nextBoolean()) {
                androidActor.setPosition(rand.nextInt()+10, rand.nextInt()-10);
            } else {
                androidActor.setPosition(rand.nextInt()-10, rand.nextInt()+10);
            }

        }
    }
}

