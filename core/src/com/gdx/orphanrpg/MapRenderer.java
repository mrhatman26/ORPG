package com.gdx.orphanrpg;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

public class MapRenderer {
    private static Array<Room> rooms = new Array<Room>();
    private static ShapeRenderer shapeRenderer = new ShapeRenderer();

    public static void createMap(String mapDefFile){
        rooms = MapReader.readMapDefFile(mapDefFile);
    }

    public static void updateRooms(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer, int guiLayerToUpdate, boolean drawLines){
        StaticMethods.systemMessage("Wow", "Boo", String.valueOf(rooms.size), true);
        for (Room room: rooms) {
            if (room.getGuiLayer() == guiLayerToUpdate) {
                if (drawLines){
                    room.drawLines(spriteBatch, rooms, shapeRenderer);
                }
                else {
                    room.update(spriteBatch, rooms);
                }
            }
        }
    }

    public static void disposeRooms(){
        for (Room room: rooms){
            room.dispose();
        }
    }
}
