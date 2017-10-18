package com.fzu.demo.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.fzu.demo.entity.UserEntity;
import com.fzu.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController{
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping(value = "/getUsers")
    @ResponseBody
	public JSONObject getUsers() {
        Map<String,Object> result = new HashMap();
        List<UserEntity> users=userMapper.getAll();
        result.put("users", users);
        return new JSONObject(result);
    }
	
    @RequestMapping("/getUser")
    public UserEntity getUser(Long id) {
    	UserEntity user=userMapper.getOne(id);
        return user;
    }
    
    @RequestMapping("/add")
    public void save(UserEntity user) {
    	userMapper.insert(user);
    }
    
    @RequestMapping(value="update")
    public void update(UserEntity user) {
    	userMapper.update(user);
    }
    
    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
    	userMapper.delete(id);
    }
    
    
}