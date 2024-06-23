package com.gdx.orphanrpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import sun.security.provider.SHA;

import java.awt.*;

public class SaveHandler {
    public static boolean buttonsCreated = false;
    private static Texture saveTexture;
    private static Texture shadowTexture;
    private static Rectangle saveRect;
    SaveHandler(){
        saveTexture = StaticMethods.spriteTest(Gdx.files.internal("GUI/SaveGUI.png"));
        shadowTexture = StaticMethods.spriteTest(Gdx.files.internal("GUI/Shadow.png"));
        saveRect = new Rectangle();
        saveRect.width = 1367;
        saveRect.height = 809;
        saveRect.x = 276;
        saveRect.y = 135;
        if (!buttonsCreated) {
            ORPG.buttonHandler.loadButtonsFromFile("SaveButtons.txt");
            buttonsCreated = true;
        }
    }

    public void update(SpriteBatch batch){
        batch.draw(shadowTexture, 0, 0);
        batch.draw(saveTexture, saveRect.x, saveRect.y);
        ORPG.buttonHandler.updateButtons(batch, 1);
    }

    public void dispose(){
        saveTexture.dispose();
        shadowTexture.dispose();
    }
}
