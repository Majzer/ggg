package com.csanysoft.donto;

import com.badlogic.gdx.graphics.Texture;
import com.csanysoft.donto.GlobalClasses.Assets;
import com.csanysoft.donto.MyBaseClasses.Scene2D.MyActor;
import com.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteStaticActor;

/**
 * Created by tanulo on 2018. 02. 02..
 */

public class HelpHandActor extends OneSpriteStaticActor {

    GameStage gameStage;

    public HelpHandActor(float x, float y, GameStage gameStage) {
        super(Assets.manager.get(Assets.HELP_HAND_TEXTURE));
        setSize(1 ,1);
        setPosition(x - getWidth()/2 , y - getHeight()/2);
        this.gameStage = gameStage;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
