package com.gdx.orphanrpg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;

public class ORPG extends ApplicationAdapter {
	public static boolean debug = true;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Texture GUIBackground;
	private ButtonLoader buttonLoader;
	private ButtonHandler buttonHandler;
	
	@Override
	public void create () {
		this.batch = new SpriteBatch();
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.GUIBackground = StaticMethods.spriteTest(Gdx.files.internal("GUI/MainGUI.png"));
		this.buttonHandler = new ButtonHandler();
		this.buttonHandler.loadButtonsFromFile("DefaultButtons.txt");
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		batch.draw(GUIBackground, 0, 0); //Todo
		buttonHandler.updateButtons(batch, camera);
		batch.end();
		camera.update();
		StaticMethods.miscControls();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		this.GUIBackground.dispose();
		buttonHandler.disposeButtons();
	}
}
