package com.asacoa.loaraidweb.mapper;

import com.asacoa.loaraidweb.data.BossDto;
import com.asacoa.loaraidweb.data.RaidDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RaidMapper {

    List<BossDto> selectAllBosses() throws Exception;

    List<RaidDto> selectBossesByRaid() throws Exception;

    List<RaidDto> selectAllRaids() throws Exception;

    int insertRaid(RaidDto raidDto) throws Exception;

    int updateRaid(RaidDto raidDto) throws Exception;

    int deleteRaid(RaidDto raidDto) throws Exception;
}
