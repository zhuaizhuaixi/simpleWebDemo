package com.fzu.demo.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.fzu.demo.common.JSONResult;
import com.fzu.demo.common.XGameConstant;
import com.fzu.demo.web.entity.GameEntity;
import com.fzu.demo.web.entity.UserEntity;
import com.fzu.demo.web.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zzx
 *         Created by zzx on 2017/12/20.
 */
@Controller
@RequestMapping("/game")
public class GameController {
    @Autowired
    private IGameService gameService;

    @RequestMapping("/myGames")
    @ResponseBody
    public JSONObject myGames(HttpServletRequest request, Integer page, Integer pageSize) {
        JSONResult result = JSONResult.build();
        HttpSession session = request.getSession();
        Integer userID = ((UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY)).getId();
        try {
            List<GameEntity> myGames = gameService.getMyGames(userID, page, pageSize);
            result.set("myGames", myGames);
        } catch (Exception e) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, e.getMessage());
        }
        return result.getJSON();
    }

    @RequestMapping("/myStaredGames")
    @ResponseBody
    public JSONObject myStaredGames(HttpServletRequest request, Integer page, Integer pageSize) {
        JSONResult result = JSONResult.build();
        HttpSession session = request.getSession();
        Integer userID = ((UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY)).getId();
        try {
            List<GameEntity> myGames = gameService.getStaredGames(userID, page, pageSize);
            result.set("myGames", myGames);
        } catch (Exception e) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, e.getMessage());
        }
        return result.getJSON();
    }
}
