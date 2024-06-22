package com.gdx.orphanrpg;

import com.badlogic.gdx.Gdx;

import java.io.File;
import java.util.Scanner;

public class ButtonLoader {
    private static String buttonFileCombinedContents = "";
    private static String[] buttonFileSplitContents; //This is buttonFileCombinedContents, but split into an array using pluses as separators
    private static String[] splitContentsArray; //This is buttonFileSplitContents, but each element is split using commas. (This isn't confusing at all, is it? /s)
    private static String currentLine = "";
    private static File buttonFile;
    private static Scanner buttonFilerScanner;
    private static void readButtonFile(String buttonFileName){
        StaticMethods.systemMessage("ButtonLoader", "readButtonFile", "Reading " + buttonFileName, true);
        try {
            buttonFileName = "GUI/" + buttonFileName;
            buttonFile = Gdx.files.internal(buttonFileName).file();
            buttonFilerScanner = new Scanner(buttonFile);
            while (buttonFilerScanner.hasNextLine()){
                currentLine = buttonFilerScanner.nextLine();
                StaticMethods.systemMessage("ButtonLoader", "readButtonFile", "Current line is: " + currentLine, true);
                if (!currentLine.equals("")){
                    if (buttonFileCombinedContents.equals("")){
                        buttonFileCombinedContents = currentLine;
                    }
                    else{
                        buttonFileCombinedContents = buttonFileCombinedContents + "+" + currentLine;
                    }
                }
            }
            buttonFilerScanner.close();
            StaticMethods.systemMessage("ButtonLoader", "readButtonFile", "Readining finished. buttonFileCombinedContents is: \n" + buttonFileCombinedContents, true);
        }
        catch (Exception error) {
            StaticMethods.systemMessage("ButtonLoader", "readButtonFile", "There was an error when trying to load buttons from " + buttonFileName, true);
            error.printStackTrace();
            buttonFileCombinedContents = null;
        }
    }
    public static boolean createButtonsFromFile(ButtonHandler buttonHandler, String buttonFileName){
        readButtonFile(buttonFileName);
        if (buttonFileCombinedContents != null) {
            StaticMethods.systemMessage("ButtonLoader", "createButtonsFromFile", "Creating defined buttons", true);
            try {
                buttonFileSplitContents = buttonFileCombinedContents.split("\\+");
                for (int i = 0; i < buttonFileSplitContents.length; i++) {
                    StaticMethods.systemMessage("ButtonLoader", "createButtonsFromFile", "buttonFileSplitContents[" + String.valueOf(i) + "] is: " + buttonFileSplitContents[i], true);
                    splitContentsArray = buttonFileSplitContents[i].split(", ");
                    buttonHandler.createNewButton(splitContentsArray[0], Integer.valueOf(splitContentsArray[1]), Integer.valueOf(splitContentsArray[2]), splitContentsArray[3], Boolean.valueOf(splitContentsArray[4]));
                }
                return true;
            }
            catch (Exception error){
                StaticMethods.systemMessage("ButtonLoader", "createButtonsFromFile", "There was an error when trying to create the defined buttons", true);
                StaticMethods.systemMessage("ButtonLoader", "createButtonsFromFile", error.toString(), true);
                return false;
            }
        }
        else{
            return false;
        }
    }
}
