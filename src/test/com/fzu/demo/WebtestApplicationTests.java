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
        Date beginDate = Date.from(Instant.now());
        System.out.println(beginDate);
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - 2);
        System.out.println(df.format(date.getTime()));

        System.out.println((beginDate.getTime() - df.parse(df.format(date.getTime())).getTime()) / (1000 * 60 * 60 * 24));
        System.out.println(1000 * 60 * 60 * 24);

        int[] dataList = new int[15];
        dataList[0]++;
        System.out.println(dataList);

       /* List<GameEntity> games = gameMapper.getRecentGames(1, df.parse(df.format(date.getTime())), beginDate);
        System.out.println(games);
        for (GameEntity game : games) {
            System.out.println(game.getDate() +" 对比 "+df.parse(df.format(date.getTime())));
            if (game.getDate() .equals( df.parse(df.format(date.getTime())))  ) {
                System.out.println(game.getName()+" "+game.getDate());
            }
        }*/
    }

}
