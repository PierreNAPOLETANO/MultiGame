package com.example.multigame.manager;

import com.example.multigame.model.Player;

public class PlayerManager {

    private static PlayerManager instance;

    private Player player;

    public static PlayerManager getInstance(){
        if (instance == null ){
            instance = new PlayerManager();
        }
        return instance;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}