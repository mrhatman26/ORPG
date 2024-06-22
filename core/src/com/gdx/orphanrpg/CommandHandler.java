package com.gdx.orphanrpg;

import com.badlogic.gdx.Gdx;

import java.io.File;
import java.util.Scanner;

public class CommandHandler {
    public static int getCommandNoFromName(String command){
        try {
            String currentLine = "";
            File commandFile = Gdx.files.internal("GUI/ButtonCommands.txt").file();
            Scanner commandFileScanner = new Scanner(commandFile);
            while (commandFileScanner.hasNextLine()){
                currentLine = commandFileScanner.nextLine();
                if (currentLine.contains(command)){
                    break;
                }
            }
            if (currentLine.equals("")){
                return -1;
            }
            else{
                return Integer.valueOf(currentLine.replace(", " + command, ""));
            }
        }
        catch (Exception error){
            StaticMethods.systemMessage("CommandHandler", "getCommandNoFromName", "An error occurred when trying to find a command named " + command, true);
            error.printStackTrace();
            return -99;
        }
    }
    public static void executeButtonCommand(int command){
        System.out.println(command);
        switch (command){
            case 0: //EXIT
                StaticMethods.exitGame(false);
                break;
            case 1: //SAVE
                return;
                //break;
            case 2: //LOAD
                return;
                //break;
            case 3: //SETTINGS
                return;
                //break;
            default:
                StaticMethods.systemMessage("CommandHandler", "executeButtonCommand", "Unknown command applied to a button, no action taken", true);
                return;
        }
    }
}
