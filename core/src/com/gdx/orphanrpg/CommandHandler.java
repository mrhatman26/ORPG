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
    public static void executeButtonCommand(int command, int buttonLayer){
        StaticMethods.systemMessage("CommandHandler", "executeButtonCommand", "Button command is: " + String.valueOf(command),true);
        StaticMethods.systemMessage("CommandHandler", "executeButtonCommand", "Button Layer is: " + String.valueOf(buttonLayer), true);
        StaticMethods.systemMessage("CommandHandler", "executeButtonCommand", "Global Layer is: " + String.valueOf(ORPG.guiLayer), true);
        if (buttonLayer == ORPG.guiLayer) {
            switch (command) {
                case 0: //EXIT
                    StaticMethods.systemMessage("CommandHandler", "executeButtonCommand", "Command is EXIT", true);
                    if (ORPG.guiLayer == 0){
                        StaticMethods.systemMessage("CommandHandler", "executeButtonCommand", "Exiting game", true);
                        StaticMethods.exitGame(false);
                    }
                    else{
                        StaticMethods.systemMessage("CommandHandler", "executeButtonCommand", "Layer is 1, clearing save GUI", true);
                        ORPG.guiLayer = 0;
                        if (ORPG.saveHandler != null){
                            ORPG.saveHandler.dispose();
                            ORPG.saveHandler = null;
                            //Delete Buttons!
                        }
                    }
                    break;
                case 1: //SAVE
                    StaticMethods.systemMessage("CommandHandler", "executeButtonCommand", "Command is SAVE", true);
                    ORPG.guiLayer = 1;
                    ORPG.saveHandler = new SaveHandler();
                    break;
                case 2: //LOAD
                    StaticMethods.systemMessage("CommandHandler", "executeButtonCommand", "Command is LOAD", true);
                    return;
                //break;
                case 3: //SETTINGS
                    StaticMethods.systemMessage("CommandHandler", "executeButtonCommand", "Command is SETTINGS", true);
                    return;
                case 4: //UP
                    StaticMethods.systemMessage("CommandHandler", "executeButtonCommand", "Command is UP", true);
                    ORPG.player.moveInDirection(0);
                    break;
                case 5: //DOWN
                    StaticMethods.systemMessage("CommandHandler", "executeButtonCommand", "Command is DOWN", true);
                    ORPG.player.moveInDirection(1);
                    break;
                case 6: //LEFT
                    StaticMethods.systemMessage("CommandHandler", "executeButtonCommand", "Command is LEFT", true);
                    ORPG.player.moveInDirection(2);
                    break;
                case 7: //RIGHT
                    StaticMethods.systemMessage("CommandHandler", "executeButtonCommand", "Command is RIGHT", true);
                    ORPG.player.moveInDirection(3);
                    break;
                //break;
                default:
                    StaticMethods.systemMessage("CommandHandler", "executeButtonCommand", "Unknown command applied to a button, no action taken", true);
                    return;
            }
        }
        else{
            StaticMethods.systemMessage("CommandHandler", "executeButtonCommand", "Button command not executed; the button is on the wrong layer", true);
        }
    }
}
