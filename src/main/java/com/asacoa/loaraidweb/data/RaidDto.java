package com.asacoa.loaraidweb.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RaidDto {
    private Long raidId;
    private Long guildId;
    private String raidType;
    private String raidDifficulty;
    private String raidMembers;
}
