package com.fzu.demo.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.fzu.demo.common.JSONResult;
import com.fzu.demo.common.XGameConstant;
import com.fzu.demo.web.entity.GameEntity;
import com.fzu.demo.web.entity.UserEntity;
import com.fzu.demo.web.service.ICommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zzx
 *         Created by zzx on 2017/12/27.
 */
@Controller
@RequestMapping("/commend")
public class CommendController {

    @Autowired
    private ICommendService commendService;

    @RequestMapping("/commendList")
    @ResponseBody
    public JSONObject commendList(HttpServletRequest request) {
        JSONResult result = JSONResult.build();
        HttpSession session = request.getSession();
        Integer userID = ((UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY)).getId();
        try {
            List<GameEntity> games = commendService.getCommendList(userID);
            result.set("games", games);
        } catch (Exception e) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, e.getMessage());
        }
        return result.getJSON();
    }

    @RequestMapping("/cosSimilarBaseOnContent")
    @ResponseBody
    public JSONObject cosSimilarBaseOnContent(HttpServletRequest request) {
        JSONResult result = JSONResult.build();
        HttpSession session = request.getSession();
        Integer userID = ((UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY)).getId();
        try {
            commendService.cosSimilarBaseOnContent(userID);
        } catch (Exception e) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, e.getMessage());
        }
        return result.getJSON();
    }

    @RequestMapping("/jaccardSimilarBaseOnContent")
    @ResponseBody
    public JSONObject jaccardSimilarBaseOnContent(HttpServletRequest request) {
        JSONResult result = JSONResult.build();
        HttpSession session = request.getSession();
        Integer userID = ((UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY)).getId();
        try {
            commendService.jaccardSimilarBaseOnContent(userID);
        } catch (Exception e) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, e.getMessage());
        }
        return result.getJSON();
    }

    @RequestMapping("/cosSimilarBaseOnFiltering")
    @ResponseBody
    public JSONObject cosSimilarBaseOnFiltering(HttpServletRequest request) {
        JSONResult result = JSONResult.build();
        HttpSession session = request.getSession();
        Integer userID = ((UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY)).getId();
        try {
            commendService.cosSimilarBaseOnFiltering(userID);
        } catch (Exception e) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, e.getMessage());
        }
        return result.getJSON();
    }

    @RequestMapping("/jaccardSimilarBaseOnFiltering")
    @ResponseBody
    public JSONObject jaccardSimilarBaseOnFiltering(HttpServletRequest request) {
        JSONResult result = JSONResult.build();
        HttpSession session = request.getSession();
        Integer userID = ((UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY)).getId();
        try {
            commendService.jaccardSimilarBaseOnFiltering(userID);
        } catch (Exception e) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, e.getMessage());
        }
        return result.getJSON();
    }

}
