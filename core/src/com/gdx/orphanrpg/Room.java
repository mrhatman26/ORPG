package com.gdx.orphanrpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class Room {
    private int roomID;
    private Integer[] roomAccessIDs; //0 = UP //1 = DOWN //2 = LEFT //3 = RIGHT
    private Rectangle roomRect;
    private Texture roomTexture;
    Room(int roomID, int x, int y, Integer[] roomAccessIDs){
        this.roomID = roomID;
        this.roomTexture = StaticMethods.spriteTest(Gdx.files.internal("GUI/Maps/mapRoom.png"));
        this.roomRect = new Rectangle();
        this.roomRect.x = x;
        this.roomRect.y = y;
        this.roomRect.width = this.roomTexture.getWidth();
        this.roomRect.height = this.roomTexture.getHeight();
        this.roomAccessIDs = roomAccessIDs;
        StaticMethods.systemMessage("Room", "Constructor", "Room [x, y] is: [" + String.valueOf(this.roomRect.x) + ", " + String.valueOf(this.roomRect.y) + "]", true);
    }

    public int getRoomID(){
        return this.roomID;
    }

    public void update(SpriteBatch spriteBatch){
        StaticMethods.systemMessage("No", "Nein", "Sad Face", true);
        spriteBatch.draw(this.roomTexture, this.roomRect.x, this.roomRect.y);
    }

    public void dispose(){
        this.roomTexture.dispose();
    }
}
