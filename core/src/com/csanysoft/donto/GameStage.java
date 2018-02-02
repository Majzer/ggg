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
        bg.setSize(getViewport().getScreenWidth()*1.5f,getViewport().getScreenHeight()*1.5f);
        bg.setPosition(0,0);
        addActor(bg);
        bg.setZIndex(1);
        androidActor.setZIndex(2);
        platformActor.setZIndex(3);
        androidActor.setZIndex(2);
        sound.play();
        sound.setVolume(0.4f);
        sound.setLooping(true);


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



            androidActor.setSpeedX(4);
            //TODO: Majd megcsinálom szebbé by ifa
            if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                androidActor.up();
            } else if(!onPlatform){
                androidActor.down();
            }
            else androidActor.land();

            setCameraMoveToXY(androidActor.getX(), androidActor.getY(), 1.5f);
            if(bg!=null) bg.setPosition(getCameraMoveToX()-getViewport().getScreenWidth()/1.335f, getCameraMoveToY()-getViewport().getScreenHeight()/1.335f);

            Array<Actor> actors = getActors();
            ArrayList<Actor> deleteActor = new ArrayList<Actor>();
            onPlatform=false;
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
        }


        /*if (androidActor.getY() > 800) {
            if(rand.nextBoolean()) {
                androidActor.setPosition(rand.nextInt()+10, rand.nextInt()-10);
            } else {
                androidActor.setPosition(rand.nextInt()-10, rand.nextInt()+10);
            }

        }*/
    }
}

