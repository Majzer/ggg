package com.csanysoft.donto.DemoLoading;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.csanysoft.donto.Donto;
import com.csanysoft.donto.GlobalClasses.Assets;
import com.csanysoft.donto.MyBaseClasses.Scene2D.MyStage;
import com.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteStaticActor;

/**
 * Created by tanulo on 2018. 02. 02..
 */

public class PenlogoStage extends MyStage {

    OneSpriteStaticActor penlogo, whiterect;

    public PenlogoStage(Viewport viewport, Batch batch, Donto game) {
        super(viewport, batch, game);
        penlogo = new OneSpriteStaticActor(Assets.manager.get(Assets.PENLOGO_TEXTURE));
        addActor(penlogo);
        penlogo.setPosition(getViewport().getWorldWidth()/2-penlogo.getWidth()/2,getViewport().getWorldHeight()/2-penlogo.getHeight()/2);
        whiterect = new OneSpriteStaticActor(Assets.manager.get(Assets.WHITE_RECTANGLE_TEXTURE));
        addActor(whiterect);
        whiterect.setPosition(getViewport().getWorldWidth()/2-penlogo.getWidth()/2,getViewport().getWorldHeight()/2-penlogo.getHeight()/2);
    }

    @Override
    public void init() {

    }


    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    protected void resized() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        whiterect.setY(whiterect.getY()+elapsedTime*3);
    }
}
