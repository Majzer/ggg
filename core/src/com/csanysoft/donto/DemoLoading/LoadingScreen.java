package com.csanysoft.donto.DemoLoading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.csanysoft.donto.Donto;
import com.csanysoft.donto.Donto;
import com.csanysoft.donto.GlobalClasses.*;
import com.csanysoft.donto.MainScreen;
import com.csanysoft.donto.MenuScreen;
import com.csanysoft.donto.MyBaseClasses.Scene2D.MyScreen;


public class LoadingScreen extends MyScreen {


    public LoadingScreen(Donto game) {
		super(game);
    }
	BitmapFont bitmapFont = new BitmapFont();

    @Override
	public void show() {
	    Assets.manager.finishLoading();
		Assets.load();
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		spriteBatch.begin();
		bitmapFont.draw(spriteBatch,"Loading: " + Assets.manager.getLoadedAssets() + "/" + (Assets.manager.getQueuedAssets()+ Assets.manager.getLoadedAssets()) + " (" + ((int)(Assets.manager.getProgress()*100f)) + "%)", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		spriteBatch.end();
		if (Assets.manager.update()) {
			Assets.afterLoaded();
			game.setScreen(new MenuScreen(game));
		}
	}


	@Override
	public void hide() {

	}

	@Override
	public void init() {
		setBackGroundColor(0f, 0f, 0f);
	}
}
