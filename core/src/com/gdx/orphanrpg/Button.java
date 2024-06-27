package com.gdx.orphanrpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;

import java.awt.*;

public class Button {
    private final int WIDTH = 300;
    private final int HALF_WIDTH = WIDTH / 2;
    private final int HEIGHT = 140;
    private int textWidth;
    private int halfTextWidth;
    private int guiLayer;
    private boolean pressed;
    private boolean commandExecuted;
    private boolean executeInstantly;
    private boolean buttonPressed;
    private String buttonText;
    private String command;
    private Rectangle buttonRect;
    private Texture buttonNormalTexture;
    private Texture buttonPressedTexture;
    private BitmapFont font;

    Button(String buttonText, int x, int y, BitmapFont font, String command, boolean executeInstantly, int guiLayer){
        this.buttonText = buttonText;
        this.pressed = false;
        this.commandExecuted = false;
        this.executeInstantly = executeInstantly;
        this.buttonRect = new Rectangle();
        this.buttonRect.width = WIDTH;
        this.buttonRect.height = HEIGHT;
        this.buttonRect.x = x;
        this.buttonRect.y = y;
        this.buttonNormalTexture = StaticMethods.spriteTest(Gdx.files.internal("GUI/ButtonNormal.png"));
        this.buttonPressedTexture = StaticMethods.spriteTest(Gdx.files.internal("GUI/ButtonPressed.png"));
        this.font = font;
        this.font.setColor(0, 0, 0, 1);
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, buttonText);
        this.textWidth = (int)glyphLayout.width;
        this.halfTextWidth = textWidth / 2;
        this.command = command;
        this.buttonPressed = false;
        this.guiLayer = guiLayer;
    }

    public int getGuiLayer(){
        return this.guiLayer;
    }

    public void update(SpriteBatch spriteBatch, Vector3 mousePos){
        //Button command is still executed when the user takes their mouse off of the button and it is no longer "pressed".
        //Fix this!
        if (StaticMethods.checkMB(false) && this.guiLayer == ORPG.guiLayer){
            this.pressed = StaticMethods.checkPosInPos(mousePos, buttonRect.x, buttonRect.y, WIDTH, HEIGHT);
        }
        else{
            this.pressed = false;
        }
        if (this.pressed){
            spriteBatch.draw(buttonPressedTexture, buttonRect.x, buttonRect.y);
            font.draw(spriteBatch, buttonText, buttonRect.x + (HALF_WIDTH - halfTextWidth), buttonRect.y + 85);
            if (!commandExecuted){
                if (executeInstantly) {
                    CommandHandler.executeButtonCommand(CommandHandler.getCommandNoFromName(this.command), this.guiLayer);
                    commandExecuted = true;
                }
                else{
                    buttonPressed = true;
                }
            }
        }
        else{
            spriteBatch.draw(buttonNormalTexture, buttonRect.x, buttonRect.y);
            font.draw(spriteBatch, buttonText, buttonRect.x + (HALF_WIDTH - halfTextWidth), buttonRect.y + 95); //buttonRect,x + 84
            commandExecuted = false;
            if (StaticMethods.checkPosInPos(mousePos, buttonRect.x, buttonRect.y, WIDTH, HEIGHT)) {
                if (buttonPressed) {
                    CommandHandler.executeButtonCommand(CommandHandler.getCommandNoFromName(this.command), this.guiLayer);
                    buttonPressed = false;
                }
            }
            else{
                buttonPressed = false;
            }
        }
    }

    public void dispose(){
        buttonNormalTexture.dispose();
        buttonPressedTexture.dispose();
        font.dispose();
    }
}
