package com.csanysoft.donto.DemoLoading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.csanysoft.donto.Donto;
import com.csanysoft.donto.Donto;
import com.csanysoft.donto.GlobalClasses.*;
import com.csanysoft.donto.MainScreen;
import com.csanysoft.donto.MenuScreen;
import com.csanysoft.donto.MyBaseClasses.Scene2D.MyScreen;
import com.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteStaticActor;


public class LoadingScreen extends MyScreen {


    public LoadingScreen(Donto game) {
		super(game);
    }
	BitmapFont bitmapFont = new BitmapFont();


	public LoadingScreen(Donto game, BitmapFont bitmapFont) {
		super(game);
		this.bitmapFont = bitmapFont;

	}

	@Override
	public void show() {
	    Assets.manager.finishLoading();
		Assets.load();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		spriteBatch.begin();
		spriteBatch.end();
		if (Assets.manager.update()) {
			Assets.afterLoaded();
			game.setScreen(new PenlogoScreen(game));
		}
	}




	@Override
	public void hide() {

	}

	@Override
	public void init() {
		setBackGroundColor(1f, 1f, 1f);
	}
}
