package com.asacoa.loaraidweb.controller;

import com.asacoa.loaraidweb.service.RaidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jda")
public class JdaController {

    @Autowired
    private RaidService raidService;

    /*
     * 디스코드 봇으로 정보 전달을 위한 API
     */

    // url : https://localhost:8080/jda/api/raid/guild/{guildId}
    @GetMapping("/api/raid/guild/{guildId}")
    @ResponseBody
    public String getGuildAllRaids(@PathVariable String guildId) throws Exception {
        return raidService.getAllRaids(guildId).toString();
    }

}
