package com.asacoa.loaraidweb.service;

import com.asacoa.loaraidweb.dao.RaidDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaidService {
    @Autowired
    private RaidDao raidDao;

    // 길드의 모든 레이드파티 조회
    public String getGuildAllRaids(String guildId) {
        return raidDao.getGuildAllRaids(guildId);
    }

}
