package com.csanysoft.donto;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
}
