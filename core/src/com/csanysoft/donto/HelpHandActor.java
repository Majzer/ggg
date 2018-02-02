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

    public HelpHandActor(float x, float y) {
        super(Assets.manager.get(Assets.HELP_HAND_TEXTURE));
        setPosition(x,y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setSize(getWidth() + (float)Math.cos(elapsedTime*10)*2, getHeight() + (float)Math.sin(elapsedTime*10)*2);
    }
}
