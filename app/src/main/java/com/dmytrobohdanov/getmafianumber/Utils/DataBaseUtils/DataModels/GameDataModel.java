package com.dmytrobohdanov.getmafianumber.Utils.DataBaseUtils.DataModels;


import java.util.HashMap;

/**
 * todo
 */
public class GameDataModel {
    private int id;
    private String killedFirst;
    private HashMap<String, String> playersRoles;
    private BestShotDataModel bestShot;
    private String wonTeam;
    private String bestPlayerMafia;
    private String bestPlayerCitizens;
    private int eventId;

    public GameDataModel() {
    }

    public GameDataModel(int id, String killedFirst, HashMap<String, String> playersRoles,
                         BestShotDataModel bestShot, String wonTeam, String bestPlayerMafia,
                         String bestPlayerCitizens, int eventId) {
        this.id = id;
        this.killedFirst = killedFirst;
        this.playersRoles = playersRoles;
        this.bestShot = bestShot;
        this.wonTeam = wonTeam;
        this.bestPlayerMafia = bestPlayerMafia;
        this.bestPlayerCitizens = bestPlayerCitizens;
        this.eventId = eventId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKilledFirst() {
        return killedFirst;
    }

    public void setKilledFirst(String killedFirst) {
        this.killedFirst = killedFirst;
    }

    public HashMap<String, String> getPlayersRoles() {
        return playersRoles;
    }

    public void setPlayersRoles(HashMap<String, String> playersRoles) {
        this.playersRoles = playersRoles;
    }

    public BestShotDataModel getBestShot() {
        return bestShot;
    }

    public void setBestShot(BestShotDataModel bestShot) {
        this.bestShot = bestShot;
    }

    public String getWonTeam() {
        return wonTeam;
    }

    public void setWonTeam(String wonTeam) {
        this.wonTeam = wonTeam;
    }

    public String getBestPlayerMafia() {
        return bestPlayerMafia;
    }

    public void setBestPlayerMafia(String bestPlayerMafia) {
        this.bestPlayerMafia = bestPlayerMafia;
    }

    public String getBestPlayerCitizens() {
        return bestPlayerCitizens;
    }

    public void setBestPlayerCitizens(String bestPlayerCitizens) {
        this.bestPlayerCitizens = bestPlayerCitizens;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
