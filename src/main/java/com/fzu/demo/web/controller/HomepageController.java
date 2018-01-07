package com.fzu.demo.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.fzu.demo.common.JSONResult;
import com.fzu.demo.common.XGameConstant;
import com.fzu.demo.enums.ResultEnum;
import com.fzu.demo.web.entity.GameEntity;
import com.fzu.demo.web.entity.TagEntity;
import com.fzu.demo.web.entity.UserEntity;
import com.fzu.demo.web.service.IGameService;
import com.fzu.demo.web.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public JSONObject gameInformation(Integer id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userID = ((UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY)).getId();

        JSONResult result = JSONResult.build();
        GameEntity game = gameService.getGameById(userID, id);
        List<TagEntity> tags = tagService.getGameTags(id);
        boolean hasPurchased = gameService.hasPurchasedGame(userID, id);
        boolean hasStared = gameService.hasStaredGame(userID, id);
        result.set("game", game);
        result.set("tags", tags);
        result.set("hasPurchased", hasPurchased);
        result.set("hasStared", hasStared);
        return result.getJSON();
    }

    @RequestMapping("/gamePurchase")
    @ResponseBody
    public JSONObject gamePurchase(Integer gameID, HttpServletRequest request) {
        JSONResult result = JSONResult.build();
        HttpSession session = request.getSession();
        Integer userID = ((UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY)).getId();
        try {
            if (gameService.hasPurchasedGame(userID, gameID)) {
                result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, ResultEnum.PURCHASE_FAIL.getMessage());
            } else {
                gameService.buyGame(userID, gameID);
                result.setNote(ResultEnum.PURCHASE_SUCCESS.getMessage());
            }
        } catch (Exception e) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, e.getMessage());
        }
        return result.getJSON();
    }

    @RequestMapping("/gameUnPurchase")
    @ResponseBody
    public JSONObject gameUnPurchase(Integer gameID, HttpServletRequest request) {
        JSONResult result = JSONResult.build();
        HttpSession session = request.getSession();
        Integer userID = ((UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY)).getId();
        try {
            gameService.unBuyGame(userID, gameID);
            result.setNote(ResultEnum.UNPURCHASE_SUCCESS.getMessage());
        } catch (Exception e) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, e.getMessage());
        }
        return result.getJSON();
    }

    @RequestMapping("/gameStar")
    @ResponseBody
    public JSONObject gameStar(Integer gameID, HttpServletRequest request) {
        JSONResult result = JSONResult.build();
        HttpSession session = request.getSession();
        Integer userID = ((UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY)).getId();
        try {
            if (gameService.hasStaredGame(userID, gameID)) {
                result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, ResultEnum.STAR_FAIL.getMessage());
            } else {
                gameService.starGame(userID, gameID);
                result.setNote(ResultEnum.STAR_SUCCESS.getMessage());
            }
        } catch (Exception e) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, e.getMessage());
        }
        return result.getJSON();
    }

    @RequestMapping("/gameUnStar")
    @ResponseBody
    public JSONObject gameUnStar(Integer gameID, HttpServletRequest request) {
        JSONResult result = JSONResult.build();
        HttpSession session = request.getSession();
        Integer userID = ((UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY)).getId();
        try {
            gameService.unStarGame(userID, gameID);
            result.setNote(ResultEnum.UNSTAR_SUCCESS.getMessage());
        } catch (Exception e) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, e.getMessage());
        }
        return result.getJSON();
    }

    @RequestMapping("/randomGames")
    @ResponseBody
    public JSONObject randomGames() {
        JSONResult result = JSONResult.build();
        try {
            List<GameEntity> games = gameService.getRandomGames();
            result.set("games", games);
        } catch (Exception e) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, e.getMessage());
        }
        return result.getJSON();
    }

}
