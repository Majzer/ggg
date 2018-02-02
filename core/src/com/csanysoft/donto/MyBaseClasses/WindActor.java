package com.csanysoft.donto.MyBaseClasses;

import com.csanysoft.donto.GlobalClasses.Assets;
import com.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

/**
 * Created by tanulo on 2018. 02. 02..
 */

public class WindActor extends OneSpriteAnimatedActor {
    public WindActor() {
        super(Assets.manager.get(Assets.WIND_TEXTURE));
        setFps(6);
        setZIndex(5);
    }
}
