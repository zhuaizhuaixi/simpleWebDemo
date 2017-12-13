package com.fzu.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zzx
 * Created by zzx on 2017/12/12.
 */
@Controller
@RequestMapping("/homepage")
public class HomepageController {

    @RequestMapping("/index")
    public String homepage() {
        return "homepage/homepage";
    }
}
