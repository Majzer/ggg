package com.csanysoft.donto;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.csanysoft.donto.GlobalClasses.Assets;
import com.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteStaticActor;

/**
 * Created by Majzer on 04/02/2018.
 */

public class PowerActor extends OneSpriteStaticActor {

    public PowerActor() {
        super(Assets.manager.get(Assets.SPONSOR_TEXTURE));
    }

    public Sprite getSprite(){
        return sprite;
    }

}