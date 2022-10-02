package com.asacoa.loaraidweb.dto;

import lombok.Data;

@Data
public class RaidDto {
    private int raid_id;
    private String guild_id;
    private String raid_type;
    private String raid_members;
}
