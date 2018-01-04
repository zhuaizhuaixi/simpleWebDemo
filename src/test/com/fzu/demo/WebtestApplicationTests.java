package com.fzu.demo;

import com.alibaba.fastjson.JSONObject;
import com.fzu.demo.common.AlgorithmUtils;
import com.fzu.demo.web.entity.TagEntity;
import com.fzu.demo.web.entity.UserEntity;
import com.fzu.demo.web.mapper.GameMapper;
import com.fzu.demo.web.mapper.UserMapper;
import com.fzu.demo.web.vo.CompanyInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.fzu.demo.web.mapper")
public class WebtestApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GameMapper gameMapper;

    @Test
    public void contextLoads() throws Exception {
        List<UserEntity> users = userMapper.getAll();
        for (UserEntity user : users) {
            user.setGames(gameMapper.getMyGames(user.getId(), 0, 100));
        }
        AlgorithmUtils.collaborativeFilteringRecommendation(users, 1);
    }

}
