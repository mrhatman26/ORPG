package com.gdx.orphanrpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL32;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class StaticMethods {

    public static Texture spriteTest(FileHandle internalPath){
        Texture newTexture;
        try {
            newTexture = new Texture(internalPath);
        }
        catch (Exception error){
            newTexture = new Texture("Missing.png");
        }
        return newTexture;
    }

    public static boolean checkPosInPos(Vector3 posToCheck, int x, int y, int width, int height){
        if (posToCheck.x >= x && posToCheck.y >= y && posToCheck.x <= x + width && posToCheck.y <= y + height){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean checkMB(boolean checkRightMB){
        if (checkRightMB) {
            return Gdx.input.isButtonPressed(Input.Buttons.RIGHT);
        } else {
            return Gdx.input.isButtonPressed(Input.Buttons.LEFT);
        }
    }

    public static void miscControls() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            exitGame(false);
        }
    }

    public static void exitGame(boolean restart){
        if (restart){
            Gdx.app.exit();
        }
        else{
            System.exit(0);
        }
    }

    public static void systemMessage(String className, String methodName, String message, boolean isDebugMessage){
        if (isDebugMessage){
            if (ORPG.debug){
                if (methodName != null && !methodName.equals("")) {
                    System.out.println("(" + className + ":" + methodName + "): " + message);
                }
                else{
                    System.out.println("(" + className + "): " + message);
                }
            }
        }
        else{
            if (methodName != null && !methodName.equals("")) {
                System.out.println("(" + className + ":" + methodName + "): " + message);
            }
            else{
                System.out.println("(" + className + "): " + message);
            }
        }
    }

    public static void errorMessage(String className, String methodName, String message){
        if (ORPG.debug) {
            if (methodName != null && !methodName.equals("")) {
                throw new RuntimeException("(" + className + ":" + methodName + "): " + message);
            } else {
                throw new RuntimeException("(" + className + "): " + message);
            }
        }
    }

    public static void drawLine(Vector2 lineStart, Vector2 lineEnd, int lineWidth, Color colour, Matrix4 matrix, ShapeRenderer shapeRenderer){ //Thank you "Madmenyo"!: https://stackoverflow.com/questions/21835062/libgdx-draw-line
        Gdx.gl.glLineWidth(lineWidth);
        shapeRenderer.setProjectionMatrix(matrix);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(colour);
        shapeRenderer.line(lineStart, lineEnd);
        shapeRenderer.end();
        shapeRenderer.setColor(Color.BLACK);
        Gdx.gl.glLineWidth(1);
    }

    public static void drawDebugText(int x, int y, SpriteBatch spriteBatch, String text){
        if (ORPG.debug && ORPG.debugFont != null){
            ORPG.debugFont.draw(spriteBatch, text, x, y);
        }
    }
}
