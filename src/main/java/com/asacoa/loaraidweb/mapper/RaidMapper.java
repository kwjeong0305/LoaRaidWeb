package com.asacoa.loaraidweb.mapper;

import com.asacoa.loaraidweb.data.RaidDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RaidMapper {
    List<RaidDto> selectAllRaids(String guildId) throws Exception;

    void insertRaid(RaidDto raidDto) throws Exception;

    void updateRaid(RaidDto raidDto) throws Exception;

    void deleteRaid(RaidDto raidDto) throws Exception;
}
