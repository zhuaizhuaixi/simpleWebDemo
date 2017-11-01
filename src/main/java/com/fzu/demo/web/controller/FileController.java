package com.fzu.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by zzx on 2017/9/18.
 */
@Controller
@RequestMapping("sign")
public class FileController {
    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/upload")
    public String upload(@RequestParam("text") String str, @RequestParam("image") MultipartFile image) {
        System.out.println(str + " " + image);
        return "index";
    }

    @RequestMapping(value = "/test")
    public String test() {
        return "test";
    }

    @RequestMapping(value = "/text")
    public String text() {
        return "text";
    }
}
