package com.gdx.orphanrpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class Room {
    private int roomID;
    private Rectangle roomRect;
    private Texture roomTexture;
    Room(int roomID, int x, int y){
        this.roomID = roomID;
        this.roomTexture = StaticMethods.spriteTest(Gdx.files.internal("GUI/Maps/mapRoom.png"));
        this.roomRect.x = x;
        this.roomRect.y = y;
        this.roomRect.width = this.roomTexture.getWidth();
        this.roomRect.height = this.roomTexture.getHeight();
    }

    public int getRoomID(){
        return this.roomID;
    }

    public void update(SpriteBatch spriteBatch){
        spriteBatch.draw(this.roomTexture, this.roomRect.x, this.roomRect.y);
    }

    public void dispose(){
        this.roomTexture.dispose();
    }
}
