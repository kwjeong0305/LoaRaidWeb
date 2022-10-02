package com.asacoa.loaraidweb.controller;

import com.asacoa.loaraidweb.data.RaidDto;
import com.asacoa.loaraidweb.service.RaidService;
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
    public String getGuildAllRaids(@PathVariable String guildId) throws Exception {

        return raidService.getAllRaids(guildId).toString();
    }

    @PostMapping("/raid/insert")
    @ResponseBody
    public String createRaid(@RequestBody final RaidDto raidDto) throws Exception {

        raidService.createRaid(raidDto);

        return "success";
    }

    @PutMapping("/raid/update")
    @ResponseBody
    public String saveRaid(@RequestBody final RaidDto raidDto) throws Exception {

        raidService.saveRaid(raidDto);

        return "success";
    }

    @DeleteMapping("/raid/delete")
    @ResponseBody
    public String deleteRaid(@RequestBody final RaidDto raidDto) throws Exception {

        raidService.deleteRaid(raidDto);

        return "success";
    }
}
