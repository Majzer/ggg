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
    float ido;


    public FanActor() {
        super(Assets.manager.get(Assets.BLOWER_TEXTURE));
        fanMusic.setVolume(1.2f);
        fanMusic.setLooping(false);
        setZIndex(5);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        fanMusic.pause();
        if(isRunning){
            //fut
            setFps(24);
            fanMusic.play();
        }else{
            //áll
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
