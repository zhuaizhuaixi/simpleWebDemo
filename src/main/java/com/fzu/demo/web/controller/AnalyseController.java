package com.fzu.demo.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.fzu.demo.common.JSONResult;
import com.fzu.demo.common.XGameConstant;
import com.fzu.demo.web.entity.GameEntity;
import com.fzu.demo.web.entity.UserEntity;
import com.fzu.demo.web.service.IGameService;
import com.fzu.demo.web.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

/**
 * @author zzx
 *         Created by zzx on 2018/1/6.
 */
@Controller
@RequestMapping("/analyse")
public class AnalyseController {

    @Autowired
    private ITagService tagService;

    @Autowired
    private IGameService gameService;

    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping("tagPercentAnalyse")
    @ResponseBody
    public JSONObject tagPercentAnalyse(HttpServletRequest request) {
        JSONResult result = JSONResult.build();
        HttpSession session = request.getSession();
        Integer userID = ((UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY)).getId();
        try {
            Map<String, Integer> map = tagService.getTagPercentage(userID);
            result.set("resultMap", map);
        } catch (Exception e) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, e.getMessage());
        }
        return result.getJSON();
    }

    @RequestMapping("recentGameAnalyse")
    @ResponseBody
    public JSONObject recentGameAnalyse(HttpServletRequest request) {
        JSONResult result = JSONResult.build();
        HttpSession session = request.getSession();
        Integer userID = ((UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY)).getId();
        List<String> dateList = new ArrayList<>(15);
        int[] dataList = new int[15];
        Date currentDate = Date.from(Instant.now());
        try {
            List<GameEntity> recentGames = gameService.getRecentGames(userID);
            for (int i = 14; i >= 0; i--) {
                Calendar date = Calendar.getInstance();
                date.setTime(currentDate);
                date.set(Calendar.DATE, date.get(Calendar.DATE) - i);
                dateList.add(df.format(date.getTime()));
            }
            for (GameEntity game : recentGames) {
                Date purchaseTime = game.getDate();
                int timeInterval = (int) (currentDate.getTime() - df.parse(df.format(purchaseTime.getTime())).getTime()) / (1000 * 60 * 60 * 24);
                dataList[14 - timeInterval]++;
            }
            result.set("date", dateList);
            result.set("data", dataList);
        } catch (Exception e) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, e.getMessage());
        }
        return result.getJSON();
    }

    @RequestMapping("tagPreference")
    @ResponseBody
    public JSONObject tagPreference(HttpServletRequest request) {
        JSONResult result = JSONResult.build();
        HttpSession session = request.getSession();
        Integer userID = ((UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY)).getId();
        try {
            Map<String, Integer> map = tagService.getTagPercentage(userID);
            List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
            //降序排序
            list.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()) * -1);
            result.set("resultList", list.subList(0, 5));
            result.set("max", list.get(0).getValue());
        } catch (Exception e) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, e.getMessage());
        }
        return result.getJSON();
    }

}
