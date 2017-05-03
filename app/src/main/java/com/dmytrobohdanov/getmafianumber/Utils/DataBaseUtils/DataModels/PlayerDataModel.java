package com.dmytrobohdanov.getmafianumber.Utils.DataBaseUtils.DataModels;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * todo
 */
@IgnoreExtraProperties
public class PlayerDataModel {
    @Exclude
    private int id;

    private String name;
    private String alias;

    public PlayerDataModel() {
    }

    /**
     * Constructor to create
     */
    public PlayerDataModel(String name) {
        this.name = name;
    }

    public PlayerDataModel(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    public PlayerDataModel(int id, String name, String alias) {
        this.id = id;
        this.name = name;
        this.alias = alias;
    }

    /**
     * Verifying player's name
     * - check is it has illegal characters
     * - is it has characters from different alphabets
     *
     * @param playersName to verify
     * @return "true" or error message
     */
    public static String verifyName(String playersName) {
        char[] name = playersName.toLowerCase().toCharArray();

        //check for whitespace
        for (char letter : name) {
            if (letter == ' ') {
                return "cannot contain whitespace";
            }
        }


        //checking for illegal characters
        for (char letter : name) {
            if (!((letter >= 'a' && letter <= 'z') ||
                    (letter >= 'а' && letter <= 'я'))) {
                return "illegal characters";
            }
        }

        //detecting language by first letter
        char firstLetter = name[0];
        boolean eng = (firstLetter >= 'a' && firstLetter <= 'z');

        for (char letter : name) {
            if (eng) {
                if (!(letter >= 'a' && letter <= 'z')) {
                    return "illegal character";
                }
            } else {
                if (!(letter >= 'а' && letter <= 'я')) {
                    return "illegal character";
                }
            }
        }

        return "true";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

//    /**
//     * Translating name from russian to eng, or from eng to rus
//     */
//    public static String getAliasFromName(String name) {
//        //detecting language by first letter
//        char firstLetter = name.charAt(0);
//        boolean eng = (firstLetter >= 'a' && firstLetter <= 'z');
//
//        if(eng){
//
//        }
//    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
