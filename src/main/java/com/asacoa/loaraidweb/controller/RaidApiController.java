package com.asacoa.loaraidweb.controller;

import com.asacoa.loaraidweb.data.RaidDto;
import com.asacoa.loaraidweb.service.RaidService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RaidApiController {

    @Autowired
    private RaidService raidService;

    // 모든 레이드파티 조회
    @GetMapping("/raid/guild/{guildId}")
    @ResponseBody
    public String getGuildAllRaids(@PathVariable Long guildId) throws Exception {
        return raidService.getAllRaids(guildId).toString();
    }

    @PostMapping("/raid/insert")
    @ResponseBody
    public JSONObject createRaid(@RequestBody final RaidDto raidDto) throws Exception {
        raidService.createRaid(raidDto);
        return new JSONObject();
    }

    @PutMapping("/raid/update")
    @ResponseBody
    public JSONObject saveRaid(@RequestBody final RaidDto raidDto) throws Exception {

        raidService.saveRaid(raidDto);

        return new JSONObject();
    }

    @DeleteMapping("/raid/delete")
    @ResponseBody
    public JSONObject deleteRaid(@RequestBody final RaidDto raidDto) throws Exception {

        raidService.deleteRaid(raidDto);

        return new JSONObject();
    }
}
