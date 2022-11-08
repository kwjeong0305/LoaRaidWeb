package com.asacoa.loaraidweb.service;

import com.asacoa.loaraidweb.data.RaidDto;
import com.asacoa.loaraidweb.mapper.RaidMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaidService {

    @Autowired
    private RaidMapper raidMapper;

    public JSONObject getAllRaids(Long guildId) throws Exception {

        List<RaidDto> raidList = raidMapper.selectAllRaids(guildId);

        JSONObject jsonObject = new JSONObject();

        if(raidList.size() > 0) {
            jsonObject.put("result", "success");
            jsonObject.put("data", raidList);
        } else {
            jsonObject.put("result", "fail");
            jsonObject.put("data", "no data");
        }

        return jsonObject;
    }

    public void createRaid(RaidDto raidDto) throws Exception {
        raidMapper.insertRaid(raidDto);
    }

    public void saveRaid(RaidDto raidDto) throws Exception {
        raidMapper.updateRaid(raidDto);
    }

    public void deleteRaid(RaidDto raidDto) throws Exception {
        raidMapper.deleteRaid(raidDto);
    }
}
