package com.fzu.demo.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fzu.demo.common.JSONResult;
import com.fzu.demo.enums.ResultEnum;
import com.fzu.demo.web.entity.GameEntity;
import com.fzu.demo.web.entity.TagEntity;
import com.fzu.demo.web.service.IGameService;
import com.fzu.demo.web.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zzx
 *         Created by zzx on 2017/12/23.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IGameService gameService;

    @Autowired
    private ITagService tagService;

    @RequestMapping("gameView")
    public String gameView(HttpServletRequest request, Integer gameID) {
        request.setAttribute("gameID", gameID);
        return "admin/gameView";
    }

    @RequestMapping("gameManage")
    public String gameManage(HttpServletRequest request, Integer gameID) {
        request.setAttribute("gameID", gameID);
        return "admin/gameManage";
    }

    @RequestMapping("newGame")
    public String newGame(HttpServletRequest request) {
        return "admin/newGame";
    }

    @RequestMapping("newTag")
    public String newTag(HttpServletRequest request) {
        return "admin/newTag";
    }

    @RequestMapping("editGameInformation")
    @ResponseBody
    public JSONObject editGameInformation(Integer gameID,
                                          @RequestParam("name") String name,
                                          @RequestParam("description") String description,
                                          @RequestParam("price") Double price,
                                          @RequestParam("image") String image,
                                          @RequestParam("checkedTags[]") String[] checkedTags) {
        JSONResult result = JSONResult.build();
        GameEntity game = new GameEntity(gameID, name, description, image, price);
        try {
            List<TagEntity> tags = new ArrayList<>(30);
            for (String tagName : checkedTags) {
                tags.add(tagService.getTagByName(tagName));
            }
            gameService.updateGameInformation(game);
            tagService.updateGameTags(gameID, tags);
            result.setNote(ResultEnum.GAME_INFORMATION_UPDATE_SUCCESS.getMessage());
        } catch (Exception e) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, ResultEnum.GAME_INFORMATION_UPDATE_FAIL.getMessage());
        }
        return result.getJSON();
    }

    @RequestMapping("/deleteGame")
    @ResponseBody
    public JSONObject deleteGame(Integer gameID) {
        JSONResult result = JSONResult.build();
        try {
            gameService.deleteGame(gameID);
            result.setNote(ResultEnum.DELETE_GAME_SUCCESS.getMessage());
        } catch (Exception e) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, e.getMessage());
        }
        return result.getJSON();
    }

    @RequestMapping("addGame")
    @ResponseBody
    public JSONObject addGame(@RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("price") Double price,
                              @RequestParam("image") String image,
                              @RequestParam("checkedTags[]") String[] checkedTags) {
        JSONResult result = JSONResult.build();
        GameEntity game = new GameEntity(name, description, image, price);
        try {
            List<TagEntity> tags = new ArrayList<>(30);
            for (String tagName : checkedTags) {
                tags.add(tagService.getTagByName(tagName));
            }
            gameService.addGame(game);
            tagService.insertGameTags(game.getId(), tags);
            result.setNote(ResultEnum.ADD_GAME_SUCCESS.getMessage());
        } catch (Exception e) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, e.getMessage());
        }
        return result.getJSON();
    }

    @RequestMapping("/allTags")
    @ResponseBody
    public JSONObject allTags() {
        JSONResult result = JSONResult.build();
        List<TagEntity> tags = tagService.getAllTags();
        result.set("tags", tags);
        return result.getJSON();
    }

    @RequestMapping("/deleteTag")
    @ResponseBody
    public JSONObject deleteTag(Integer tagID) {
        JSONResult result = JSONResult.build();
        try {
            tagService.deleteTag(tagID);
            result.setNote(ResultEnum.DELETE_TAG_SUCCESS.getMessage());
        } catch (Exception e) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, e.getMessage());
        }
        return result.getJSON();
    }

    @RequestMapping("/addTag")
    @ResponseBody
    public JSONObject addTag(@RequestParam("tagName") String tagName) {
        JSONResult result = JSONResult.build();
        try {
            tagService.insertTag(tagName);
            result.setNote(ResultEnum.ADD_TAG_SUCCESS.getMessage());
        } catch (Exception e) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, e.getMessage());
        }
        return result.getJSON();
    }
}
