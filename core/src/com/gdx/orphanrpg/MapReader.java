package com.gdx.orphanrpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

import java.io.File;
import java.util.Scanner;

public class MapReader {
    private static String currentLine;
    private static String[] currentRoomDef;
    private static File mapFile;
    private static Scanner mapFileScanner;
    private static Array<String> mapData;
    private static Array<Room> mapRooms;

    public static Array<Room> readMapDefFile(String mapDefFile){
        StaticMethods.systemMessage("MapReader", "readMapDef", "Reading " + mapDefFile, true);
        mapData = new Array<String>();
        try{
            mapFile = Gdx.files.internal(mapDefFile).file();
            mapFileScanner = new Scanner(mapFile);
            while (mapFileScanner.hasNextLine()){
                currentLine = mapFileScanner.nextLine();
                StaticMethods.systemMessage("MapReader", "readMapDef", "Current line is: " + currentLine, true);
                if (!currentLine.equals("")) {
                    mapData.add(currentLine);
                }
                else{
                    StaticMethods.systemMessage("MapReader", "readMapDef", "Reached empty line, stopping reading", true);
                    break;
                }
            }
            StaticMethods.systemMessage("MapReader", "readMapDef", "Reading finished, returning String array", true);
            if (createMapFromDef()) {
                return mapRooms;
            }
            else{
                return null;
            }
        }
        catch (Exception error){
            StaticMethods.systemMessage("MapReader", "readMapDef", "An error occurred while reading " + mapDefFile, true);
            error.printStackTrace();
            return null;
        }
    }

    private static boolean createMapFromDef(){
        StaticMethods.systemMessage("MapReader", "createMapFromDef", "Creating rooms from map definition", true);
        mapRooms = new Array<Room>();
        try {
            for (String roomDef : mapData) {
                StaticMethods.systemMessage("MapReader", "createMapFromDef", "Current def is: " + roomDef, true);
                currentRoomDef = roomDef.split(", ");
                mapRooms.add(new Room(Integer.parseInt(currentRoomDef[0]), Integer.parseInt(currentRoomDef[1]), Integer.parseInt(currentRoomDef[2]), new Integer[]{Integer.parseInt(currentRoomDef[3]), Integer.parseInt(currentRoomDef[4]), Integer.parseInt(currentRoomDef[5]), Integer.parseInt(currentRoomDef[6])}));
            }
            StaticMethods.systemMessage("MapReader", "createMapFromDef", "Finished creating rooms, returning true", true);
            return true;
        }
        catch (Exception error){
            StaticMethods.systemMessage("MapReader", "createMapFromDef", "An error occurred while creating rooms", true);
            error.printStackTrace();
            if (mapRooms.size > 0){
                StaticMethods.systemMessage("MapReader", "createMapFromDef", "Some rooms have already been created, returning true", true);
                return true;
            }
            else{
                StaticMethods.systemMessage("MapReader", "createMapFromDef", "No rooms have been created, returning false", true);
                return false;
            }
        }
    }
}
