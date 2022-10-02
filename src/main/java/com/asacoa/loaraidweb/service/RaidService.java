package com.asacoa.loaraidweb.service;

import com.asacoa.loaraidweb.dto.RaidDto;
import com.asacoa.loaraidweb.mapper.RaidMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaidService {

    @Autowired
    private RaidMapper raidMapper;

    public RaidService(RaidMapper raidMapper) {
        this.raidMapper = raidMapper;
    }

    public List<RaidDto> getAllRaids(String guildId) throws Exception {
        return raidMapper.selectAllRaids(guildId);
    }

    public void createRaid(RaidDto raidDto) {

    }

    public void saveRaid(RaidDto raidDto) {

    }

    public void deleteRaid(RaidDto raidDto) {

    }
}
