package com.asacoa.loaraidweb.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RaidDao {

    static {

    }

    public String getGuildAllRaids(String guildId) {
        return guildId;
    }
}
