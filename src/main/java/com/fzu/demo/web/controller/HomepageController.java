package com.fzu.demo.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.fzu.demo.common.JSONResult;
import com.fzu.demo.web.entity.GameEntity;
import com.fzu.demo.web.entity.TagEntity;
import com.fzu.demo.web.service.IGameService;
import com.fzu.demo.web.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zzx
 *         Created by zzx on 2017/12/12.
 */
@Controller
@RequestMapping("/homepage")
public class HomepageController {

    @Autowired
    private IGameService gameService;

    @Autowired
    private ITagService tagService;

    @RequestMapping("/index")
    public String homepage() {
        return "homepage/homepage";
    }

    @RequestMapping("/allGames")
    @ResponseBody
    public JSONObject allGames() {
        JSONResult result = JSONResult.build();

        List<GameEntity> games = gameService.getAllGames();

        result.set("games", games);
        return result.getJSON();
    }

    @RequestMapping("/gameList")
    @ResponseBody
    public JSONObject gameList(Integer page, Integer pageSize) {
        JSONResult result = JSONResult.build();
        try {
            List<GameEntity> games = gameService.getGames(page, pageSize);
            result.set("games", games);
        } catch (Exception e) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, e.getMessage());
        }
        return result.getJSON();
    }

    @RequestMapping("/game")
    public String game(Integer id, HttpServletRequest request) {
        request.setAttribute("gameID", id);
        return "/homepage/game";
    }

    @RequestMapping("/gameInformation")
    @ResponseBody
    public JSONObject gameInformation(Integer id) {
        JSONResult result = JSONResult.build();
        GameEntity game = gameService.getGameById(id);
        List<TagEntity> tags = tagService.getGameTags(id);
        result.set("game", game);
        result.set("tags", tags);
        return result.getJSON();
    }

}
