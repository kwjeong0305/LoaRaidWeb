package com.asacoa.loaraidweb.mapper;

import com.asacoa.loaraidweb.data.RaidDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RaidMapper {
    public List<RaidDto> selectAllRaids() throws Exception;

    public int insertRaid(RaidDto raidDto) throws Exception;

    public int updateRaid(RaidDto raidDto) throws Exception;

    public int deleteRaid(RaidDto raidDto) throws Exception;
}
