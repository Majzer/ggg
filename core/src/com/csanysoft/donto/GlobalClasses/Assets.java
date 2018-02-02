//https://github.com/tuskeb/mester
package com.csanysoft.donto.GlobalClasses;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

import java.util.Random;


public class Assets {
	// https://github.com/libgdx/libgdx/wiki/Managing-your-assets
	// http://www.jacobplaster.net/using-libgdx-asset-manager
	// https://www.youtube.com/watch?v=JXThbQir2gU
	// https://github.com/Matsemann/libgdx-loading-screen/blob/master/Main/src/com/matsemann/libgdxloadingscreen/screen/LoadingScreen.java

	public static AssetManager manager;
	public static final String CHARS = "0123456789öüóqwertzuiopőúasdfghjkléáűíyxcvbnm'+!%/=()ÖÜÓQWERTZUIOPŐÚASDFGHJKLÉÁŰÍYXCVBNM?:_*<>#&@{}[],-.";


	static final FreetypeFontLoader.FreeTypeFontLoaderParameter fontParameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
	static {
		fontParameter.fontFileName = "arial.ttf";
		fontParameter.fontParameters.size = 30;
		fontParameter.fontParameters.characters = CHARS;
		fontParameter.fontParameters.color = Color.WHITE;
	}


	//Betútípus
	public static final AssetDescriptor<BitmapFont> ARIAL_30_FONT
			= new AssetDescriptor<BitmapFont>(fontParameter.fontFileName, BitmapFont.class, fontParameter);
	//---------------------------------------------------------------------------------------------------------

	//Texturák
	public static final AssetDescriptor<Texture> PLATFORM1_TEXTURE
			= new AssetDescriptor<Texture>("platforms/platform1.png", Texture.class);

	public static final AssetDescriptor<Texture> BACKGROUND_TEXTURE
			= new AssetDescriptor<Texture>("background.png", Texture.class);
	//---------------------------------------------------------------------------------------------------------

	//Animált dolgok

	//Android bábú
	public static final AssetDescriptor<TextureAtlas> WALK_TEXTURE
			= new AssetDescriptor<TextureAtlas>("android/walk.atlas", TextureAtlas.class);

	//Ventilátor
	public static final AssetDescriptor<TextureAtlas> BLOWER_TEXTURE
			= new AssetDescriptor<TextureAtlas>("blower/rotate.atlas", TextureAtlas.class);

	//Szél
	public static final AssetDescriptor<TextureAtlas> WIND_TEXTURE
			= new AssetDescriptor<TextureAtlas>("wind/winding.atlas", TextureAtlas.class);
	//---------------------------------------------------------------------------------------------------------

	//Gomb texturák
	public static final AssetDescriptor<Texture> BTN_ON_BUTTON_TEXTURE
			= new AssetDescriptor<Texture>("buttons/on_button/on_button.png", Texture.class);

	public static final AssetDescriptor<Texture> BTN_ON_BUTTON_DOWN_TEXTURE
			= new AssetDescriptor<Texture>("buttons/on_button/on_button_down.png", Texture.class);

	public static final AssetDescriptor<Texture> BTN_START_TEXTURE
			= new AssetDescriptor<Texture>("buttons/menu/start.png", Texture.class);

	public static final AssetDescriptor<Texture> BTN_START_DOWN_TEXTURE
			= new AssetDescriptor<Texture>("buttons/menu/start_down.png", Texture.class);

	public static final AssetDescriptor<Texture> BTN_EXIT_TEXTURE
			= new AssetDescriptor<Texture>("buttons/menu/exit.png", Texture.class);

	public static final AssetDescriptor<Texture> BTN_EXIT_DOWN_TEXTURE
			= new AssetDescriptor<Texture>("buttons/menu/exit_down.png", Texture.class);
	//---------------------------------------------------------------------------------------------------------

	//Hangok
	public static final AssetDescriptor<Music> ThemeSound
			=new AssetDescriptor<Music>("sound/game_themesound.wav", Music.class);

	public static final AssetDescriptor<Music> MenuTheme
			=new AssetDescriptor<Music>("sound/mune_theme_sound.wav", Music.class);

	public static final AssetDescriptor<Music> WindSound
			=new AssetDescriptor<Music>("sound/wind_sound_2.0.wav", Music.class);

	public static final AssetDescriptor<Music> FanSound
			=new AssetDescriptor<Music>("sound/mune_theme_sound.wav", Music.class);
	//---------------------------------------------------------------------------------------------------------

	public static void prepare() {
		manager = new AssetManager();
		Texture.setAssetManager(manager);
	}



	public static void load() {
		FileHandleResolver resolver = new InternalFileHandleResolver();
		manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
		manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
		manager.setLoader(BitmapFont.class, ".otf", new FreetypeFontLoader(resolver));


		manager.load(ARIAL_30_FONT);
		// TODO: 2018. 02. 02. itt van a háttér loadja
		//manager.load(BACKGROUND_TEXTURE);


		//Actorok
        manager.load(WALK_TEXTURE);


		//Texturák
		manager.load(PLATFORM1_TEXTURE);
		manager.load(BACKGROUND_TEXTURE);


		//Animált texturák atlaszai
		manager.load(WALK_TEXTURE);
		manager.load(BLOWER_TEXTURE);
		manager.load(WIND_TEXTURE);

		//Gombok
		manager.load(BTN_ON_BUTTON_TEXTURE);
		manager.load(BTN_ON_BUTTON_DOWN_TEXTURE);
		manager.load(BTN_START_TEXTURE);
		manager.load(BTN_START_DOWN_TEXTURE);
		manager.load(BTN_EXIT_TEXTURE);
		manager.load(BTN_EXIT_DOWN_TEXTURE);

		//Hangok
		manager.load(ThemeSound);
		manager.load(MenuTheme);
		manager.load(WindSound);
		manager.load(FanSound);
	}

    public static void afterLoaded() {
        //manager.get(MUSIC).setLooping(true);
    }

	public static void unload() {
		manager.dispose();
	}

}
