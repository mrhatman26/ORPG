package com.gdx.orphanrpg;

public class Player {
    private String playerName;
    //Generic RPG stats
    private int playerLevel, playerXP, playerXPToNext, playerHealth, playerHealthMax, playerAttack, playerDefence;
    private int playerClass; //-1 = Unset, 0 = Brawler, 1 = Thief, 2 = Gunner/Tank, 3 = Medic/Diplomat
    //ToDo

    Player(){
        this.playerName = "Hope";
        this.playerLevel = 1;
        this.playerXP = 0;
        this.playerXPToNext = 100;
        this.playerHealth = 0;
        this.playerHealthMax = 10;
        this.playerAttack = 2;
        this.playerDefence = 8;
    }
}
