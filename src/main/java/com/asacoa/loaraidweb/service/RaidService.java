package com.asacoa.loaraidweb.service;

import com.asacoa.loaraidweb.data.BossDto;
import com.asacoa.loaraidweb.data.RaidDto;
import com.asacoa.loaraidweb.mapper.RaidMapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RaidService {

    @Autowired
    private RaidMapper raidMapper;
    public JSONObject getAllBosses() throws Exception {
        List<BossDto> bossList = raidMapper.selectAllBosses();

        JSONObject data = new JSONObject();
        JSONObject bossObject = new JSONObject();
        ArrayList<String> bossArrayList = new ArrayList<String>();


        for (BossDto boss : bossList) {
            JSONObject temp = new JSONObject()
                    .put("name", boss.getBossName())
                    .put("maxMember", boss.getBossMaxMember())
                    .put("difficulty", boss.getDifficultyJSONArray());

            bossArrayList.add(boss.getBossName());
            bossObject.put(boss.getBossName(), temp);
        }

        if (bossList.size() > 0) {
            data.put("result", "success");
            data.put("data1", bossObject);
            data.put("data2", bossArrayList);
        } else {
            data.put("result", "fail");
            data.put("result_desc", "no data");
            data.put("data", bossObject);
        }
        return data;
    }

    public JSONObject getAllRaids() throws Exception {

        List<RaidDto> bossList = raidMapper.selectBossesByRaid();
        List<RaidDto> raidList = raidMapper.selectAllRaids();

        JSONObject data = new JSONObject();
        JSONArray raidArray = new JSONArray();

        for (RaidDto boss : bossList) {
            JSONArray raidArrayItem = new JSONArray();

            for (RaidDto raid : raidList) {
                if (boss.getBossName().equals(raid.getRaidType()) && boss.getBossDifficulty().equals(raid.getRaidDifficulty())) {
                    raidArrayItem.put(
                            new JSONObject()
                                    .put("id", raid.getRaidId())
                                    .put("difficulty", raid.getRaidDifficulty())
                                    .put("members", raid.getRaidMember().split(",")));
                }
            }

            if (!boss.getDifficultyName().equals("")) {
                raidArray.put(new JSONObject().put(boss.getBossName() + "-" + boss.getDifficultyName(), raidArrayItem));
            } else {
                raidArray.put(new JSONObject().put(boss.getBossName(), raidArrayItem));
            }
        }

        if (raidList.size() > 0) {
            data.put("result", "success");
            data.put("data", raidArray);
        } else {
            data.put("result", "fail");
            data.put("result_desc", "no  data");
            data.put("data", raidArray);
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
        for (String raidId : raidDto.getDeleteRaidId()) {
            raidDto.setRaidId(raidId);
            raidMapper.deleteRaid(raidDto);
        }
    }
}
