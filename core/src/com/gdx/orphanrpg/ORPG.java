package com.gdx.orphanrpg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Map;

public class ORPG extends ApplicationAdapter {
	public static boolean debug = true;
	public static int guiLayer = 0;
	private SpriteBatch batch;
	public static OrthographicCamera camera;
	private Texture GUIBackground;
	public static ButtonHandler buttonHandler;
	public static SaveHandler saveHandler;
	public static Player player = new Player(0);
	private ShapeRenderer shapeRenderer;
	public static GL20 gl = Gdx.graphics.getGL20();
	
	@Override
	public void create () {
		this.batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.GUIBackground = StaticMethods.spriteTest(Gdx.files.internal("GUI/MainGUI.png"));
		buttonHandler = new ButtonHandler();
		buttonHandler.loadButtonsFromFile("DefaultButtons.txt");
		MapRenderer.createMap("GUI/Maps/MapDefs/mapOrphanage.txt");
		this.shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		batch.draw(GUIBackground, 0, 0); //Todo
		buttonHandler.updateButtons(batch, 0);
		MapRenderer.updateRooms(batch, shapeRenderer);
		if (saveHandler != null){
			saveHandler.update(batch);
		}
		batch.end();
		camera.update();
		StaticMethods.miscControls();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		this.GUIBackground.dispose();
		buttonHandler.disposeButtons();
		if (saveHandler != null){
			saveHandler.dispose();
		}
		MapRenderer.disposeRooms();
	}
}
