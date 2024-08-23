package com.gdx.orphanrpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.awt.*;

public class Room {
    private int roomID, guiLayer, halfWidth, halfHeight;
    private Integer[] roomAccessIDs; //0 = UP //1 = DOWN //2 = LEFT //3 = RIGHT
    private Rectangle roomRect;
    private Texture roomTexture, roomTextureActive;
    Room(int roomID, int x, int y, Integer[] roomAccessIDs, int guiLayer){
        this.roomID = roomID;
        this.guiLayer = guiLayer;
        this.roomTexture = StaticMethods.spriteTest(Gdx.files.internal("GUI/Maps/mapRoom.png"));
        this.roomTextureActive = StaticMethods.spriteTest(Gdx.files.internal("GUI/Maps/mapRoomActive.png"));
        this.roomRect = new Rectangle();
        this.roomRect.x = x;
        this.roomRect.y = y;
        this.roomRect.width = this.roomTexture.getWidth();
        this.roomRect.height = this.roomTexture.getHeight();
        this.halfWidth = this.roomRect.width / 2;
        this.halfHeight = this.roomRect.height / 2;
        this.roomAccessIDs = roomAccessIDs;
        StaticMethods.systemMessage("Room", "Constructor", "Room [x, y] is: [" + String.valueOf(this.roomRect.x) + ", " + String.valueOf(this.roomRect.y) + "]", true);
    }

    public int getRoomID(){
        return this.roomID;
    }

    public int getGuiLayer(){
        return this.guiLayer;
    }

    public Vector2 getRoomRect(boolean centre, Integer xOffset, Integer yOffset){
        if (centre){
            if (xOffset != null && yOffset != null){
                return new Vector2(this.roomRect.x + (this.halfWidth) + xOffset, this.roomRect.y + (this.halfHeight) + yOffset);
            }
            else {
                return new Vector2(this.roomRect.x + (this.halfWidth), this.roomRect.y + (this.halfHeight / 2));
            }
        }
        else {
            if (xOffset != null && yOffset != null){
                return new Vector2(this.roomRect.x + xOffset, this.roomRect.y + yOffset);
            }
            else {
                return new Vector2(this.roomRect.x, this.roomRect.y);
            }
        }
    }

    public int getNextRoomID(int direction){
        return this.roomAccessIDs[direction];
    }

    public void update(SpriteBatch spriteBatch, Array<Room> rooms){
        StaticMethods.drawDebugText(this.roomRect.x, this.roomRect.y, spriteBatch, String.valueOf(this.roomID));
        if (ORPG.player.getMapPos() == this.roomID){
            spriteBatch.draw(this.roomTextureActive, this.roomRect.x, this.roomRect.y);
        }
        else {
            spriteBatch.draw(this.roomTexture, this.roomRect.x, this.roomRect.y);
        }
        StaticMethods.systemMessage("Room", "update", "roomAccessIDs.length is " + String.valueOf(roomAccessIDs.length), true);
        StaticMethods.systemMessage("Room", "update", "rooms.size is " + String.valueOf(rooms.size), true);
    }

    public void drawLines(SpriteBatch spriteBatch, Array<Room> rooms, ShapeRenderer shapeRenderer){
        for (int i = 0; i < roomAccessIDs.length; i++){
            StaticMethods.systemMessage("Room", "update", "Current direction (i) is " + String.valueOf(i), true);
            if (roomAccessIDs[i] >= 0) {
                switch (i) {
                    case 0: //UP
                        StaticMethods.drawLine(this.getRoomRect(true, 0, this.halfHeight), rooms.get(roomAccessIDs[i]).getRoomRect(true, 0, this.halfHeight * -1), 2, Color.GRAY, ORPG.camera.combined, shapeRenderer);
                        break;
                    case 1: //DOWN
                        StaticMethods.drawLine(this.getRoomRect(true, 0, this.halfHeight * -1), rooms.get(roomAccessIDs[i]).getRoomRect(true, 0, this.halfHeight), 4, Color.GRAY, ORPG.camera.combined, shapeRenderer);
                        break;
                    case 2: //LEFT
                        StaticMethods.drawLine(this.getRoomRect(true, this.halfWidth * -1, 0), rooms.get(roomAccessIDs[i]).getRoomRect(true, this.halfWidth, 0), 4, Color.GRAY, ORPG.camera.combined, shapeRenderer);
                        break;
                    case 3: //RIGHT
                        StaticMethods.drawLine(this.getRoomRect(true, this.halfWidth, 0), rooms.get(roomAccessIDs[i]).getRoomRect(true, this.halfWidth * -1, 0), 4, Color.GRAY, ORPG.camera.combined, shapeRenderer);
                        break;
                    default: //UNKOWN
                        StaticMethods.errorMessage("Room", "update", String.valueOf(i) + " is an invalid direction.");
                }
            }
            else{
                StaticMethods.systemMessage("Room", "update", "Direction " + String.valueOf(i) + " is -1. No line to be drawn", true);
            }
        }
    }

    public void dispose(){
        this.roomTexture.dispose();
        this.roomTextureActive.dispose();
    }
}
