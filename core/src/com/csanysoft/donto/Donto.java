package com.csanysoft.donto;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.csanysoft.donto.DemoLoading.LoadingScreen;
import com.csanysoft.donto.GlobalClasses.Assets;
import com.csanysoft.donto.MyBaseClasses.Game.MyGame;

public class Donto extends MyGame {
	@Override
	public void create() {
		Gdx.input.setCatchBackKey(true);
		Assets.prepare();
		setScreen(new LoadingScreen(this));
	}



	public TextButton.TextButtonStyle btnOn() {
		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = Assets.manager.get(Assets.ARIAL_30_FONT);
		textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.BTN_ON_BUTTON_TEXTURE)));
		textButtonStyle.over = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.BTN_ON_BUTTON_TEXTURE)));
		textButtonStyle.down = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.BTN_ON_BUTTON_DOWN_TEXTURE)));
		return textButtonStyle;
	}

	public TextButton.TextButtonStyle btnStart() {
		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = Assets.manager.get(Assets.ARIAL_30_FONT);
		textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.BTN_START_TEXTURE)));
		textButtonStyle.over = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.BTN_START_TEXTURE)));
		textButtonStyle.down = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.BTN_START_DOWN_TEXTURE)));
		return textButtonStyle;
	}

	public TextButton.TextButtonStyle btnExit() {
		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = Assets.manager.get(Assets.ARIAL_30_FONT);
		textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.BTN_EXIT_TEXTURE)));
		textButtonStyle.over = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.BTN_EXIT_TEXTURE)));
		textButtonStyle.down = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.BTN_EXIT_DOWN_TEXTURE)));
		return textButtonStyle;
	}
}
