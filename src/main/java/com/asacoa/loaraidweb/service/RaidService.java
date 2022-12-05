package com.asacoa.loaraidweb.service;

import com.asacoa.loaraidweb.data.RaidDto;
import com.asacoa.loaraidweb.mapper.RaidMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RaidService {

    @Autowired
    private RaidMapper raidMapper;

    public JSONObject getAllRaids() throws Exception {

        List<RaidDto> raidList = raidMapper.selectAllRaids();
        System.out.println(Arrays.toString(raidList.toArray()));
        System.out.printf("raidList : %s", Arrays.toString(raidList.toArray()));

        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();

        for (RaidDto raid : raidList) {
            result.put(raid.getRaidType(), new JSONArray());
        }

        for (RaidDto raid : raidList) {
            JSONObject temp = new JSONObject()
                    .put("id", raid.getRaidId())
                    .put("difficulty", new JSONObject().put("id", raid.getRaidDifficulty()).put("name", raid.getDifficultyName()))
                    .put("members", raid.getRaidMember().replaceAll("\\[|\\]| ", "").split(","));
            result.getJSONArray(raid.getRaidType()).put(temp);
        }



        if (raidList.size() > 0) {
            data.put("result", "success");
            data.put("data", result);
        } else {
            data.put("result", "fail");
            data.put("data", "no data");
        }

        return data;
    }

    public void createRaid(RaidDto raidDto) throws Exception {
        raidMapper.insertRaid(raidDto);
    }

    public void saveRaid(RaidDto raidDto) throws Exception {
        raidMapper.updateRaid(raidDto);
    }

    public void deleteRaid(RaidDto raidDto) throws Exception {
        for (String raidId : raidDto.getRaidMembers()) {
            raidDto.setRaidId(raidId);
            raidMapper.deleteRaid(raidDto);
        }
    }
}
