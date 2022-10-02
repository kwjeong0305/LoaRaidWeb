package com.asacoa.loaraidweb.data;

import lombok.Data;

@Data
public class RaidDto {
    private String raid_id;
    private String guild_id;
    private String raid_type;
    private String raid_members;
}
