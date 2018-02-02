package com.csanysoft.donto;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.csanysoft.donto.GlobalClasses.Assets;
import com.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

/**
 * Created by Majzer on 02/02/2018.
 */

public class AndroidActor extends OneSpriteAnimatedActor {

    private float speedX = 4, speedY = 0, fallingTime = 0;
    private boolean moveX = true;
    private int movingState = 0; //0-Séta, 1-Repülés, 2-Zuhanás
    private final int GRAVITACIOS_GYORSULAS = 20;

    public AndroidActor() {
        super(Assets.manager.get(Assets.WALK_TEXTURE));
        setFps(6);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(movingState == 2){//Zuhanás
            fallingTime += delta;
        }else
            fallingTime = 0;

        //jobbra menetel
        if(moveX)
            setX(getX() + speedX);

        switch (movingState){
            case 0://Sétálás
                speedY=0;
                break;
            case 1://Repülés
                speedY=5;
                break;
            case 2://Zuhanás
                speedY= -GRAVITACIOS_GYORSULAS*fallingTime;
                break;
        }

        setY(getY()+speedY);
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public void up(){
        movingState = 1;//Repülés
    }

    public void down(){
        movingState = 2; //Zuhanás
    }

    public void land(){
        movingState = 0;//Sétálás
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

}
