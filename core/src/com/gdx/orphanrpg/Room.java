package com.gdx.orphanrpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.awt.*;

public class Room {
    private int roomID;
    private Integer[] roomAccessIDs; //0 = UP //1 = DOWN //2 = LEFT //3 = RIGHT
    private Rectangle roomRect;
    private Texture roomTexture, roomTextureActive;
    Room(int roomID, int x, int y, Integer[] roomAccessIDs){
        this.roomID = roomID;
        this.roomTexture = StaticMethods.spriteTest(Gdx.files.internal("GUI/Maps/mapRoom.png"));
        this.roomTextureActive = StaticMethods.spriteTest(Gdx.files.internal("GUI/Maps/mapRoomActive.png"));
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

    public Vector2 getRoomRect(){
        return new Vector2(this.roomRect.x, this.roomRect.y);
    }

    public int getNextRoomID(int direction){
        return this.roomAccessIDs[direction];
    }

    public void update(SpriteBatch spriteBatch, Array<Room> rooms, ShapeRenderer shapeRenderer){
        if (ORPG.player.getMapPos() == this.roomID){
            spriteBatch.draw(this.roomTextureActive, this.roomRect.x, this.roomRect.y);
        }
        else {
            spriteBatch.draw(this.roomTexture, this.roomRect.x, this.roomRect.y);
        }
        for (int i = 0; i < roomAccessIDs.length; i++){
            if (roomAccessIDs[i] >= 0){
                Gdx.gl.glLineWidth(2);
                shapeRenderer.setProjectionMatrix(ORPG.camera.combined);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                shapeRenderer.setColor(Color.GRAY);
                shapeRenderer.line(this.getRoomRect(), rooms.get(roomAccessIDs[i]).getRoomRect());
                shapeRenderer.end();
                Gdx.gl.glLineWidth(1);
            }
        }
    }

    public void dispose(){
        this.roomTexture.dispose();
        this.roomTextureActive.dispose();
    }
}
