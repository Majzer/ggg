package com.csanysoft.donto;

import com.badlogic.gdx.graphics.Texture;
import com.csanysoft.donto.GlobalClasses.Assets;
import com.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteStaticActor;

/**
 * Created by Majzer on 02/02/2018.
 */

public class PlatformActor extends OneSpriteStaticActor {
    public PlatformActor(float x, float y) {
        super(Assets.manager.get(Assets.PLATFORM1_TEXTURE));
        setPosition(x,y);
    }
}
