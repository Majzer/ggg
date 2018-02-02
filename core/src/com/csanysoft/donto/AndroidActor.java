package com.csanysoft.donto;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

/**
 * Created by Majzer on 02/02/2018.
 */

public class AndroidActor extends OneSpriteAnimatedActor {

    private int speedX = 4, speedY = 0;

    public AndroidActor(TextureAtlas textureAtlas) {
        super(textureAtlas);
        setFps(24);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setX(getX() + speedX);
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void up(){
        setY(getY()*1.02f);
    }

    public void down(){
        setY(getY()/1.02f);
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

}
