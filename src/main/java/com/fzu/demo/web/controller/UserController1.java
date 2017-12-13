package com.fzu.demo.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.fzu.demo.common.JSONResult;
import com.fzu.demo.web.entity.CourseEntity;
import com.fzu.demo.web.entity.UserEntity;
import com.fzu.demo.web.mapper.CourseMapper;
import com.fzu.demo.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController1 {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/getUsers")
    @ResponseBody
    public JSONObject getUsers() {
        JSONResult result = JSONResult.build();
        List<UserEntity> users = userService.getAllUser();
        result.set("users", users);
        return result.getJSON();
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public JSONObject getUser(Long id, String userName) {
        JSONResult result = JSONResult.build();
        List<UserEntity> user = userService.getUser(id, userName);
        result.set("users", user);
        return result.getJSON();
    }

    @RequestMapping("/add")
    public void save(UserEntity user) {
        userService.insert(user);
    }

    @RequestMapping(value = "update")
    public void update(UserEntity user) {
        userService.update(user);
    }

    @RequestMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}