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
        /*
        {
        "도비스도디언": [
          {
            "id": "1",
            "difficulty": {"id": '', "name": ''},
            "members": ["가나다라마바사아자차카타", "가나다라마바사아자차카타", "가나다라마바사아자차카타", "가나다라마바사아자차카타"]
          },
          {"id": "2", "difficulty": {"id": '', "name": ''}, "members": [1, 2, 3, 4]},
          {"id": "3", "difficulty": {"id": '', "name": ''}, "members": [4, 3, 2, 1]}
        ],
        "쿠크세이튼": [
          {"id": "4", "difficulty": {"id": "normal", "name": "노말"}, "members": [1, 2, 3, 4]},
          {"id": "5", "difficulty": {"id": "normal", "name": "노말"}, "members": [4, 3, 2, 1]},
          {"id": "6", "difficulty": {"id": "normal", "name": "노말"}, "members": [11, 22, 33, 44]}
        ],
        "아브렐슈드": [
          {
            "id": "7",
            "difficulty": {"id": {"id": "normal", "name": "노말"}, "name": "노말"},
            "members": [1, 2, 3, 4, 5, 6]
          },
          {"id": "8", "difficulty": {"id": "normal", "name": "노말"}, "members": [1, 2, 32]},
          {"id": "9", "difficulty": {"id": "normal", "name": "노말"}, "members": [13]},
          {"id": "10", "difficulty": {"id": "hard", "name": "하드"}, "members": [1, 2, 3, 4, 5, 6]},
          {"id": "11", "difficulty": {"id": "hard", "name": "하드"}, "members": [1, 2, 32]},
          {"id": "12", "difficulty": {"id": "hard", "name": "하드"}, "members": [13, 2, 3, 54, 6, 6]}
        ],
        "일리아칸": [
          {"id": "13", "difficulty": {"id": "normal", "name": "노말"}, "members": [1, 2, 3, 4, 5, 6]},
          {"id": "14", "difficulty": {"id": "normal", "name": "노말"}, "members": [1, 2, 32]},
          {"id": "15", "difficulty": {"id": "normal", "name": "노말"}, "members": [13]},
          {"id": "16", "difficulty": {"id": "hard", "name": "하드"}, "members": [1, 2, 3, 4, 5, 6]},
          {"id": "17", "difficulty": {"id": "hard", "name": "하드"}, "members": [1, 2, 32]},
          {"id": "18", "difficulty": {"id": "hard", "name": "하드"}, "members": [13]}
        ]
      }
        * */

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
        raidMapper.deleteRaid(raidDto);
    }
}
