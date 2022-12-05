package com.asacoa.loaraidweb.controller;

import com.asacoa.loaraidweb.data.RaidDto;
import com.asacoa.loaraidweb.service.RaidService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class RaidApiController {

    @Autowired
    private RaidService raidService;

    // 모든 레이드파티 조회
    @GetMapping("/raid/select")
    @ResponseBody
    public String getGuildAllRaids() throws Exception {
        return raidService.getAllRaids().toString();
    }

    @PostMapping("/raid/insert")
    @ResponseBody
    public JSONObject createRaid(@RequestBody final RaidDto raidDto) throws Exception {
        // raid Data 수정
        raidDto.setRaidId(String.valueOf(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE));
        raidDto.setRaidMember(Arrays.toString(raidDto.getRaidMembers()));

        raidService.createRaid(raidDto);
        return new JSONObject();
    }

    @PostMapping("/raid/update")
    @ResponseBody
    public JSONObject saveRaid(@RequestBody final RaidDto raidDto) throws Exception {
        raidDto.setRaidMember(Arrays.toString(raidDto.getRaidMembers()));
        raidService.saveRaid(raidDto);

        return new JSONObject();
    }

    @PostMapping("/raid/delete")
    @ResponseBody
    public JSONObject deleteRaid(@RequestBody final RaidDto raidDto) throws Exception {
        raidService.deleteRaid(raidDto);

        return new JSONObject();
    }
}
