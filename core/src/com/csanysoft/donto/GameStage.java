package com.csanysoft.donto;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.csanysoft.donto.GlobalClasses.Assets;
import com.csanysoft.donto.MyBaseClasses.Scene2D.MyStage;

/**
 * Created by Majzer on 02/02/2018.
 */

public class GameStage extends MyStage {
    public GameStage(Viewport viewport, Batch batch, Donto game) {
        super(viewport, batch, game);
        addActor(new AndroidActor(Assets.manager.get(Assets.WALK_TEXTURE)));
    }

    @Override
    public void init() {

    }

    @Override
    public void act() {
        super.act();
    }
}
