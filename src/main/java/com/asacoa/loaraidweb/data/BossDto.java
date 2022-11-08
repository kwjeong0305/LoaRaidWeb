package com.asacoa.loaraidweb.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

@Data
@Getter
@Setter
public class BossDto {

    private String bossName;
    private String bossDifficulty;
    private int bossMaxMember;

    public JSONArray getDifficultyJSONArray() {
        JSONArray difficultyArray = new JSONArray();
        JSONObject difficultyObject;
        for (String difficulty : bossDifficulty.split(",")) {
            difficultyObject = new JSONObject();
            difficultyObject.put("id", difficulty);
            difficultyObject.put("name", getDifficultyName(difficulty));
            difficultyArray.put(difficultyObject);
        }
        return difficultyArray;
    }

    public String getDifficultyName(String difficulty) {
        switch (difficulty) {
            case "normal":
                return "노말";
            case "hard":
                return "하드";
            case "hard1":
                return "하드1";
            case "hard2":
                return "하드2";
            case "hard3":
                return "하드3";
            default:
                return "";
        }
    }
}
