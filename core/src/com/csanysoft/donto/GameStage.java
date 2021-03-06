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
import com.csanysoft.donto.MyBaseClasses.WindActor;
import com.sun.org.apache.xpath.internal.operations.And;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Majzer on 02/02/2018.
 */

public class GameStage extends MyStage {

    ArrayList<PlatformActor> platforms = new ArrayList<PlatformActor>();
    ArrayList<WindActor> winds = new ArrayList<WindActor>();
    PlatformActor platformActor;
    AndroidActor androidActor;
    WindActor windActor1, windActor2, windActor3, windActor4, windActor5;
    FanActor fanActor;
    HelpHandActor helpHand;
    Random rand = new Random();
    OneSpriteStaticActor bg;
    TextButton btnOn;
    int i=0;
    Music sound;
    Music walk;
    Music theme;




    public GameStage(Viewport viewport, Batch batch, Donto game) {
        super(viewport, batch, game);
        bg = new OneSpriteStaticActor(Assets.manager.get(Assets.BACKGROUND_TEXTURE));
        bg.setSize(getViewport().getScreenWidth()*1.5f,getViewport().getScreenHeight()*1.5f);
        bg.setPosition(0,0);
        addActor(bg);

        bg.setZIndex(1);

        sound = Assets.manager.get(Assets.ThemeSound);
        walk = Assets.manager.get(Assets.WalkSound);
        theme = Assets.manager.get(Assets.MenuTheme);


        addActor(platformActor=new PlatformActor(i++,100));
        addActor(androidActor=new AndroidActor());
        androidActor.setPosition(platformActor.getX(),platformActor.getY()+platformActor.getHeight()+20);
        addActor(fanActor = new FanActor());
        fanActor.setZIndex(5);

        platforms.add(platformActor);
        platformActor.setZIndex(3);
        winds.add(windActor1= new WindActor());
        winds.add(windActor2= new WindActor());
        winds.add(windActor3= new WindActor());
        winds.add(windActor4= new WindActor());
        winds.add(windActor5= new WindActor());
        for(WindActor windActor : winds){
            addActor(windActor);
            windActor.setZIndex(5);
            windActor.setVisible(false);
        }
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
        theme.pause();
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
    //setDebugAll(true);
    }

    boolean removePlatformFromArrayList = false;
    PlatformActor removablePlatform = null;
    boolean onPlatform = false;
    float elapsedTimeForAndroidActor=0;
    float baseSpeed=4;
    float first = 10000;
    boolean egyszer = true;
    boolean mehetFel = true;
    boolean mehetFel2 = true;


    @Override
    public void init() {

    }


    @Override
    public void act(float delta) {
        super.act(delta);

//        helpHand.setPosition(androidActor.getX(), androidActor.getY());
        //helpHand.setSize(getWidth() + (float)Math.cos(elapsedTime*10)/40, getHeight() + (float)Math.sin(elapsedTime*10)/40);

        helpHand.setPosition(androidActor.getX()+androidActor.getWidth()/2-40, androidActor.getY()+androidActor.getHeight()/2-35);

        if(androidActor!=null){
        if(elapsedTime-elapsedTimeForAndroidActor>10){
            androidActor.setFps(androidActor.getFps()+1);
            baseSpeed++;
            elapsedTimeForAndroidActor=elapsedTime;
        }

            if(fanActor!=null){
                fanActor.setY(androidActor.getY()-575);
                fanActor.setX(androidActor.getX()-fanActor.getWidth()/2+androidActor.getWidth()/2);
            }
            androidActor.setSpeedX(baseSpeed);

            if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isTouched()){
                if(mehetFel) {
                    if (egyszer) {
                        fanActor.ido = elapsedTime;
                        egyszer = false;
                    }
                    if (elapsedTime - fanActor.ido > 3) {
                        mehetFel = false;
                        androidActor.down();
                        fanActor.turnOff();
                        for(WindActor windActor : winds){
                            windActor.setVisible(false);
                        }
                        //androidActor.land();
                    } else {
                        if(mehetFel2){
                            androidActor.up();
                            fanActor.turnOn();
                            for (WindActor windActor : winds) {
                                windActor.setVisible(true);
                                windActor.setPosition(fanActor.getX() + rand.nextInt((int) fanActor.getWidth()), fanActor.getY() + fanActor.getHeight() + rand.nextInt(getViewport().getScreenHeight()));
                            }
                            walk.pause();
                            helpHand.setVisible(false);
                        }

                    }
                }
            } else if(!onPlatform){
                androidActor.down();
                fanActor.turnOff();
                mehetFel2=false;
                egyszer = true;
                for(WindActor windActor : winds){
                    windActor.setVisible(false);
                }
                walk.pause();
            }
            else {
                mehetFel=true;
                mehetFel2=true;
                androidActor.land();
                walk.play();
                walk.setVolume(0.4f);
                walk.setLooping(true);
            }

            setCameraMoveToXY(androidActor.getX(), androidActor.getY(), 1.5f);
            if(bg!=null){
                bg.setPosition(getCameraMoveToX()-getViewport().getScreenWidth()+40, getCameraMoveToY()-getViewport().getScreenHeight());
                bg.setSize(getViewport().getScreenWidth()*2f, getViewport().getScreenHeight()*2f);
            }

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
                    if((int)(androidActor.getX() + androidActor.getWidth()) > pa.getX()-50 && (int)(androidActor.getX() + androidActor.getWidth()) < pa.getX()+10){
                        androidActor.setSpeedX(0);
                    }
                    
                    else if (aY+androidActor.getHeight() < pa.getY()+10){
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
                platformActor.setZIndex(4);
                platforms.remove(removablePlatform);
                removePlatformFromArrayList=false;
            }

            for (Actor a : deleteActor) {
                actors.removeValue(a, true);
            }
        }


        if (androidActor.getY() < -200) {
            game.setScreen(new MenuScreen(game));
        }
    }
}

