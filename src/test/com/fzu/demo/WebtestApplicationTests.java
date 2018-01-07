package com.fzu.demo;

import com.fzu.demo.common.AlgorithmUtils;
import com.fzu.demo.web.entity.GameEntity;
import com.fzu.demo.web.entity.UserEntity;
import com.fzu.demo.web.mapper.GameMapper;
import com.fzu.demo.web.mapper.UserMapper;
import org.apache.catalina.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
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

    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void contextLoads() throws Exception {
        System.out.println(gameMapper.getGameSale());
    }

}
