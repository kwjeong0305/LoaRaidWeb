package com.asacoa.loaraidweb.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class RaidDto {
    private String raidId;
    private String[] deleteRaidId;
    private String raidType;
    private String raidDifficulty;
    private String raidMember;
    private String[] raidMembers;

    public String getDifficultyName() {
        switch (raidDifficulty) {
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
