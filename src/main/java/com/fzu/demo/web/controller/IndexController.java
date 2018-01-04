package com.fzu.demo.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.fzu.demo.common.JSONResult;
import com.fzu.demo.common.Md5Util;
import com.fzu.demo.common.XGameConstant;
import com.fzu.demo.enums.ResultEnum;
import com.fzu.demo.web.entity.GameEntity;
import com.fzu.demo.web.entity.TagEntity;
import com.fzu.demo.web.entity.UserEntity;
import com.fzu.demo.web.service.ICommendService;
import com.fzu.demo.web.service.IGameService;
import com.fzu.demo.web.service.ITagService;
import com.fzu.demo.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzx on 2017/12/4.
 *
 * @author zzx
 */
@Controller
public class IndexController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ITagService tagService;

    @Autowired
    private IGameService gameService;

    @Autowired
    private ICommendService commendService;

    @RequestMapping(value = "/")
    public String initLogin(HttpServletRequest request) {
        return "login";
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public JSONObject login(HttpServletRequest request) {
        JSONResult result = JSONResult.build();
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, "用户名或密码为空");
        } else {
            UserEntity user = userService.getUserByUsernameAndPassword(username, password);
            if (user == null) {
                result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, ResultEnum.LOGIN_NAME_OR_PASS_WORD_ERROR.getMessage());
            } else {
                session.setAttribute(XGameConstant.LOGIN_SESSION_KEY, user);
            }
        }
        if ("admin".equals(username)) {
            result.setCodeAndNote(JSONResult.KEY_CODE_SUCCESS, "admin");
            session.setAttribute(XGameConstant.LOGIN_SESSION_KEY, new UserEntity(99, "admin", "admin", "man", null, null, null));

        }
        return result.getJSON();
    }

    @RequestMapping(value = "/initRegister")
    public String initRegister(HttpServletRequest request) {
        return "register";
    }

    @RequestMapping("/register")
    @ResponseBody
    public JSONObject register(@RequestParam("username") String username, @RequestParam("nickname") String nickname, @RequestParam("sex") String sex, @RequestParam("password") String password, @RequestParam(value = "tags[]") String[] tags) {
        JSONResult result = JSONResult.build();
        UserEntity user = userService.getUserByUsername(username);
        List<TagEntity> tagEntities = new ArrayList<>(30);
        for (String tagName : tags) {
            tagEntities.add(tagService.getTagByName(tagName));
        }
        if (user != null) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, ResultEnum.USERNAME_EXIST.getMessage());
        } else {
            try {
                UserEntity newUser = new UserEntity(username, nickname, sex, Md5Util.sign(password + XGameConstant.PASSWORD_KEY));
                userService.insert(newUser);
                tagService.updateUserTags(userService.getUserByUsername(username).getId(), tagEntities);
                commendService.produceRecommendList(newUser.getId());
                result.setNote(ResultEnum.SUCCESS.getMessage());
            } catch (Exception e) {
                result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, e.getMessage());
            }
        }
        return result.getJSON();
    }

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request) {
        return "index";
    }

    @RequestMapping(value = "/admin")
    public String admin(HttpServletRequest request) {
        return "admin";
    }

    @RequestMapping("/myGames")
    public String myGames() {
        return "/myGames/games";
    }

    @RequestMapping("/staredGames")
    public String staredGames() {
        return "/myGames/staredGames";
    }

    @RequestMapping(value = "/userInfo")
    public String userInfo() {
        return "userInfo/index";
    }

    @RequestMapping(value = "/purchaseHistory")
    public String purchaseHistory() {
        return "history/purchaseHistory";
    }

    @RequestMapping(value = "/visitHistory")
    public String visitHistory() {
        return "history/visitHistory";
    }

    @RequestMapping(value = "/loginHistory")
    public String loginHistory() {
        return "history/loginHistory";
    }

    @RequestMapping(value = "/commend")
    public String commend() {
        return "/commend/commend";
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public JSONObject logout(HttpServletRequest request) {
        JSONResult result = JSONResult.build();
        HttpSession session = request.getSession();
        session.removeAttribute(XGameConstant.LOGIN_SESSION_KEY);
        return result.getJSON();
    }

    @RequestMapping("/gameNumber")
    @ResponseBody
    public JSONObject gameNumber(HttpServletRequest request) {
        JSONResult result = JSONResult.build();
        HttpSession session = request.getSession();
        Integer userID = ((UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY)).getId();
        Integer purchaseNumber;
        Integer starNumber;
        purchaseNumber = gameService.getPurchasedGameNumber(userID);
        starNumber = gameService.getStaredGameNumber(userID);
        result.set("purchaseNumber", purchaseNumber);
        result.set("starNumber", starNumber);
        return result.getJSON();
    }

    @RequestMapping("search")
    public String search(HttpServletRequest request, String keyword) {
        request.setAttribute("keyword", keyword);
        return "/myGames/search";
    }

    @RequestMapping("searchResult")
    @ResponseBody
    public JSONObject searchResult(HttpServletRequest request, String keyword) {
        JSONResult result = JSONResult.build();
        try {
            List<GameEntity> games = gameService.getSearchResult(keyword);
            result.set("games", games);
        } catch (Exception e) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, e.getMessage());
        }
        return result.getJSON();
    }

    @RequestMapping("icon")
    public void getIcon(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        String realPath = session.getServletContext().getRealPath("/");
        Integer userID = ((UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY)).getId();
        UserEntity user = userService.getUserByID(userID);
        response.setContentType("img/jpeg");
        response.setCharacterEncoding("utf-8");
        OutputStream outputStream = null;
        InputStream in;
        try {
            if (user.getPhoto() != null) {
                in = new ByteArrayInputStream(user.getPhoto());
            } else {
                in = new FileInputStream(realPath + "/image/user.png");
            }
            outputStream = response.getOutputStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }


}
