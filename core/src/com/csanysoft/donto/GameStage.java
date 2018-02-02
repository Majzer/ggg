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
import com.csanysoft.donto.MyBaseClasses.Scene2D.MyButton;
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
    FanActor fanActor;
    HelpHandActor helpHand;
    Random rand = new Random();
    OneSpriteStaticActor bg;
    TextButton btnOn;
    int i=0;
    Music sound;



    public GameStage(Viewport viewport, Batch batch, Donto game) {
        super(viewport, batch, game);
        bg = new OneSpriteStaticActor(Assets.manager.get(Assets.BACKGROUND_TEXTURE));
        bg.setSize(getViewport().getScreenWidth()*1.5f,getViewport().getScreenHeight()*1.5f);
        bg.setPosition(0,0);
        addActor(bg);

        bg.setZIndex(1);

        sound = Assets.manager.get(Assets.ThemeSound);


        addActor(platformActor=new PlatformActor(i++,100));
        addActor(fanActor = new FanActor());
        fanActor.setY(-400);
        platforms.add(platformActor);
        addActor(androidActor=new AndroidActor());
        androidActor.setPosition(platformActor.getX(),platformActor.getY()+platformActor.getHeight()+20);
        platformActor.setZIndex(3);
        for(;i < 11; i++) {
            addActor(platformActor=new PlatformActor(i*1100 + rand.nextInt(500),rand.nextInt(500)+100));
            platformActor.setZIndex(3);
            platforms.add(platformActor);
    }

        addActor(helpHand = new HelpHandActor(androidActor.getX(),androidActor.getY()));
        helpHand.setZIndex(4);


        bg.setZIndex(1);
        platformActor.setZIndex(3);
        androidActor.setZIndex(2);
        setDebugAll(true);

        sound.play();
        sound.setVolume(0.4f);
        sound.setLooping(true);


        /*

        btnOn = new MyButton("", game.btnOn());
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
    float elapsedTimeForAndroidActor=0;
    float baseSpeed=4;

    @Override
    public void init() {

    }


    @Override
    public void act(float delta) {
        super.act(delta);
        fanActor.setX(androidActor.getX());
        helpHand.setPosition(androidActor.getX()+androidActor.getWidth()/2-40, androidActor.getY()+androidActor.getHeight()/2-35);

        if(androidActor!=null){
        if(elapsedTime-elapsedTimeForAndroidActor>10){
            androidActor.setFps(androidActor.getFps()+1);
            baseSpeed++;
            elapsedTimeForAndroidActor=elapsedTime;
        }


            androidActor.setSpeedX(baseSpeed);
            if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isTouched()){
                androidActor.up();
                fanActor.turnOn();
                helpHand.setVisible(false);
            } else if(!onPlatform){
                androidActor.down();
                fanActor.turnOff();
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
                    addActor(platformActor=new PlatformActor(i*1100 + rand.nextInt(500),rand.nextInt(500)+100));
                    removePlatformFromArrayList = true;
                    removablePlatform = pa;
                    i++;
                }

                if(androidActor.overlaps(ShapeType.Rectangle,pa)){
                    int aY = (int)androidActor.getY();
                    float paYH = pa.getY()+pa.getHeight();
                    System.out.println("androidActor.getSpeedY() = " + androidActor.getSpeedY());
                    System.out.println("(int)(androidActor.getX() + androidActor.getWidth()) = " + (int)(androidActor.getX() + androidActor.getWidth()));
                    System.out.println("pa.getX() = " + pa.getX());
                    if((int)(androidActor.getX() + androidActor.getWidth()) > pa.getX()-50 && (int)(androidActor.getX() + androidActor.getWidth()) < pa.getX()+10){
                        System.out.println("fejicjgirjticgr");
                        androidActor.setSpeedX(0);
                    }
                    
                    else if (aY+androidActor.getHeight() < pa.getY()+10){
                        System.out.println("alulról");
                        androidActor.setY(pa.getY()-androidActor.getHeight());
                    }
                    else{
                        onPlatform = true;
                        androidActor.setY(paYH);
                    }
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

