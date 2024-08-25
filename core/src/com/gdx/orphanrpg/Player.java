package com.gdx.orphanrpg;

public class Player {
    private String playerName;
    //Generic RPG stats
    private int playerLevel, playerXP, playerXPToNext, playerHealth, playerHealthMax, playerAttack, playerDefence;
    private int playerClass; //-1 = Unset, 0 = Brawler, 1 = Thief, 2 = Gunner/Tank, 3 = Medic/Diplomat
    private int mapPos, newPos;
    //ToDo

    Player(int mapPos){
        this.playerName = "Hope";
        this.playerLevel = 1;
        this.playerXP = 0;
        this.playerXPToNext = 100;
        this.playerHealth = 0;
        this.playerHealthMax = 10;
        this.playerAttack = 2;
        this.playerDefence = 8;
        this.mapPos = mapPos;
        this.newPos = -1;
    }

    public int getMapPos(){
        return this.mapPos;
    }

    public void moveInDirection(int direction){
        this.newPos = MapRenderer.getNextRoomID(this.getMapPos(), direction);
        if (this.newPos >= 0){
            this.mapPos = newPos;
            StaticMethods.systemMessage("Player", "moveInDirection", "Moved player up to room with the ID of " + String.valueOf(newPos), true);
        }
        else{
            StaticMethods.systemMessage("Player", "moveInDirection", "No room above current room, player not moved", true);
        }
    }
}
