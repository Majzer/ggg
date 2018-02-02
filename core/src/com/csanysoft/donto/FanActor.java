package com.csanysoft.donto;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.csanysoft.donto.GlobalClasses.Assets;
import com.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

/**
 * Created by tanulo on 2018. 02. 02..
 */

public class FanActor extends OneSpriteAnimatedActor {
    private boolean isRunning = false;
    private Music fanMusic = Assets.manager.get(Assets.FanSound);
    private float time = 3;

    public FanActor() {
        super(Assets.manager.get(Assets.BLOWER_TEXTURE));
        fanMusic.setVolume(0.4f);
        setZIndex(5);
    }

    public float getTime() {
        return time;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        System.out.println(time);
        if(time<0){
            time += delta;
            turnOff();
            return;
        }
        if(isRunning){
            //fut
            time -= delta*2;
            setFps(24);
            fanMusic.play();
        }else{
            //áll
            if(time<3)
                time += delta;
            setFps(0);
            fanMusic.pause();
        }
    }

    public void turnOn(){
        isRunning = true;
    }

    public void turnOff(){
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
