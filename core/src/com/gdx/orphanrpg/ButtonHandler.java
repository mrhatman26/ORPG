package com.gdx.orphanrpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class ButtonHandler {
    private Array<Button> buttons;
    private BitmapFont buttonFont;
    private Vector3 mousePos;

    ButtonHandler(){
        this.buttons = new Array<Button>();
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Helvetica.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 60;
        this.buttonFont = fontGenerator.generateFont(fontParameter);
        fontGenerator.dispose();
        this.mousePos = new Vector3();
    }

    public void createNewButton(String buttonText, int x, int y, String command, boolean executeInstantly){
        this.buttons.add(new Button(buttonText, x, y, buttonFont, command, executeInstantly));
    }

    public void loadButtonsFromFile(String buttonFileName){
        ButtonLoader.createButtonsFromFile(this, buttonFileName);
    }

    public void updateButtons(SpriteBatch spriteBatch, OrthographicCamera camera){
        mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mousePos);
        for (Button button: buttons){
            button.update(spriteBatch, mousePos);
        }
    }

    public void disposeButtons(){
        for (Button button: buttons){
            button.dispose();
        }
    }
}
