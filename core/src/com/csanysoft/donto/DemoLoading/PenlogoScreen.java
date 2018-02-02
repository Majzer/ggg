package com.csanysoft.donto.DemoLoading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.csanysoft.donto.Donto;
import com.csanysoft.donto.MenuScreen;
import com.csanysoft.donto.MyBaseClasses.Scene2D.MyScreen;
import com.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteStaticActor;

/**
 * Created by tanulo on 2018. 02. 02..
 */

public class PenlogoScreen extends MyScreen {

    protected PenlogoStage penlogoStage;
    long ido = System.currentTimeMillis();

    public PenlogoScreen(Donto game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if(System.currentTimeMillis()-ido > 2500){
            game.setScreen(new MenuScreen(game));
        }
        penlogoStage.act(delta);
        penlogoStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        penlogoStage.resize(width, height);
    }

    @Override
    public void dispose() {
        penlogoStage.dispose();
        super.dispose();
    }

    @Override
    public void init() {
        penlogoStage = new PenlogoStage(new ExtendViewport(1280,720, new OrthographicCamera(1280,720)), spriteBatch, game);
        Gdx.input.setInputProcessor(penlogoStage);
    }
}
