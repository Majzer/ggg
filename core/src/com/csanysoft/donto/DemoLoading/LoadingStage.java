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

public class LoadingStage extends MyStage {

    OneSpriteStaticActor penlogo, whiterect;

    public LoadingStage(Viewport viewport, Batch batch, Donto game) {
        super(viewport, batch, game);

        penlogo = new OneSpriteStaticActor(Assets.manager.get(Assets.PENLOGO_TEXTURE));
        whiterect = new OneSpriteStaticActor(Assets.manager.get(Assets.WHITE_RECTANGLE_TEXTURE));
        addActor(penlogo);
        addActor(whiterect);

    }

    @Override
    public void init() {

    }


}
