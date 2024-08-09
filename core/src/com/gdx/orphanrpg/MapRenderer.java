package com.gdx.orphanrpg;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

public class MapRenderer {
    private static Array<Room> rooms = new Array<Room>();
    private static ShapeRenderer shapeRenderer = new ShapeRenderer();

    public static void createMap(String mapDefFile){
        rooms = MapReader.readMapDefFile(mapDefFile);
    }

    public static void updateRooms(SpriteBatch spriteBatch){
        StaticMethods.systemMessage("Wow", "Boo", String.valueOf(rooms.size), true);
        for (Room room: rooms) {
            room.update(spriteBatch);
        }
    }

    public static void disposeRooms(){
        for (Room room: rooms){
            room.dispose();
        }
    }
}
