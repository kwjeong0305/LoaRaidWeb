package com.asacoa.loaraidweb.controller;

import oracle.jdbc.proxy.annotation.Post;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/raids")
public class RaidController {


    // 모든 레이드파티 조회
    @GetMapping("")
    public String getAllRaids() {
        return "모든 레이드파티 조회";
    }
}
