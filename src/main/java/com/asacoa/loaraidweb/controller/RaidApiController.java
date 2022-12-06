package com.asacoa.loaraidweb.controller;

import com.asacoa.loaraidweb.data.RaidDto;
import com.asacoa.loaraidweb.service.RaidService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class RaidApiController {

    @Autowired
    private RaidService raidService;

    // 모든 레이드파티 조회
    @GetMapping("/bosses/select")
    @ResponseBody
    public String getAllBosses() throws Exception {
        return raidService.getAllBosses().toString();
    }


    // 모든 레이드파티 조회
    @GetMapping("/raids/select")
    @ResponseBody
    public String getAllRaids() throws Exception {
        return raidService.getAllRaids().toString();
    }

    @PostMapping("/raids/insert")
    @ResponseBody
    public JSONObject createRaid(@RequestBody final RaidDto raidDto) throws Exception {
        raidDto.setRaidMember(Arrays.toString(raidDto.getRaidMembers()).replaceAll("\\[|\\]| ", ""));

        raidService.createRaid(raidDto);
        return new JSONObject();
    }

    @PostMapping("/raids/update")
    @ResponseBody
    public JSONObject saveRaid(@RequestBody final RaidDto raidDto) throws Exception {
        raidDto.setRaidMember(Arrays.toString(raidDto.getRaidMembers()));
        raidService.saveRaid(raidDto);

        return new JSONObject();
    }

    @PostMapping("/raids/delete")
    @ResponseBody
    public JSONObject deleteRaid(@RequestBody final RaidDto raidDto) throws Exception {
        raidService.deleteRaid(raidDto);

        return new JSONObject();
    }
}