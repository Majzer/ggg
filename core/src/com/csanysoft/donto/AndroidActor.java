package com.csanysoft.donto;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

/**
 * Created by Majzer on 02/02/2018.
 */

public class AndroidActor extends OneSpriteAnimatedActor {

    private int speed = 4;

    public AndroidActor(TextureAtlas textureAtlas) {
        super(textureAtlas);
        setFps(24);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setX(getX() + speed);
    }

    public int getSpeed() {
        return speed;
    }

    public void up(){
        setY(getY()+1);
    }

    public void down(){
        setY(getY()-1);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
