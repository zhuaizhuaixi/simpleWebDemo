package com.fzu.demo.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.fzu.demo.common.JSONResult;
import com.fzu.demo.common.XGameConstant;
import com.fzu.demo.enums.ResultEnum;
import com.fzu.demo.web.entity.TagEntity;
import com.fzu.demo.web.entity.UserEntity;
import com.fzu.demo.web.service.ITagService;
import com.fzu.demo.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zzx on 2017/12/9.
 *
 * @author zzx
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ITagService tagService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(value = "/information")
    @ResponseBody
    public JSONObject information(HttpServletRequest request) {
        JSONResult result = JSONResult.build();
        HttpSession session = request.getSession();
        Integer userID = ((UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY)).getId();
        UserEntity user = userService.getUserByID(userID);
        result.set("user", user);
        return result.getJSON();
    }

    @RequestMapping(value = "/updateInformation")
    @ResponseBody
    public JSONObject updateInformation(HttpServletRequest request) {
        JSONResult result = JSONResult.build();
        HttpSession session = request.getSession();
        Integer userID = ((UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY)).getId();
        String nickname = request.getParameter("nickname");
        String sex = request.getParameter("sex");
        try {
            Date birthday = sdf.parse(request.getParameter("birthday"));
            userService.updateUser(nickname, sex, birthday, userID);
            UserEntity user = (UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY);
            user.setNickname(nickname);
            user.setSex(sex);
            user.setBirthday(birthday);
            session.setAttribute(XGameConstant.LOGIN_SESSION_KEY, user);
        } catch (Exception e) {
            result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, e.getMessage());
        }
        return result.getJSON();
    }

    @RequestMapping("/initChangePassword")
    public String initChangePassword() {
        return "/userInfo/changePassword";
    }

    @RequestMapping("/changePassword")
    @ResponseBody
    public JSONObject changePassword(HttpServletRequest request) {
        JSONResult result = JSONResult.build();
        HttpSession session = request.getSession();
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String username = ((UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY)).getUsername();
        UserEntity user = userService.getUserByUsernameAndPassword(username, oldPassword);
        if (user == null) {
            result.setCodeAndNote(ResultEnum.CHANGE_PWD_FAIL.getCode(), ResultEnum.CHANGE_PWD_FAIL.getMessage());
        } else {
            userService.changePassword(username, newPassword);
            result.setNote(ResultEnum.CHANGE_PWD_SUCCESS.getMessage());
        }
        return result.getJSON();
    }

    @RequestMapping("/initChangePhoto")
    public String initChangePhoto() {
        return "/userInfo/changePhoto";
    }

    @RequestMapping(value = "/changePhoto")
    @ResponseBody
    public JSONObject photo(@RequestParam("image") MultipartFile image, HttpServletRequest request) throws Exception {
        JSONResult result = JSONResult.build();
        HttpSession session = request.getSession();
        Integer userID = ((UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY)).getId();
        byte[] bytes = image.getBytes();
        userService.changePhoto(userID, bytes);
        return result.getJSON();
    }

    @RequestMapping("/initTagSetting")
    public String initTagSetting() {
        return "/userInfo/tagSetting";
    }

    @RequestMapping("/allTags")
    @ResponseBody
    public JSONObject allTags() {
        JSONResult result = JSONResult.build();
        List<TagEntity> tags = tagService.getAllTags();
        List<String> tagsValue = new ArrayList<>(40);
        for (TagEntity tag : tags) {
            tagsValue.add(tag.getName());
        }
        result.set("tags", tagsValue);
        return result.getJSON();
    }

    @RequestMapping("/userTags")
    @ResponseBody
    public JSONObject userTags(HttpServletRequest request) {
        JSONResult result = JSONResult.build();
        List<String> tagsValue = new ArrayList<>(40);
        HttpSession session = request.getSession();
        Integer userID = ((UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY)).getId();
        List<TagEntity> tags = tagService.getUserTags(userID);
        for (TagEntity tag : tags) {
            tagsValue.add(tag.getName());
        }
        result.set("tags", tagsValue);
        return result.getJSON();
    }

    @RequestMapping("/updateTags")
    @ResponseBody
    public JSONObject updateTags(HttpServletRequest request, @RequestParam(value = "checkedTags[]") String[] checkedTags) {
        JSONResult result = JSONResult.build();
        HttpSession session = request.getSession();
        Integer userID = ((UserEntity) session.getAttribute(XGameConstant.LOGIN_SESSION_KEY)).getId();
        List<TagEntity> tags = new ArrayList<>(30);
        for (String tagName : checkedTags) {
            tags.add(tagService.getTagByName(tagName));
        }
        tagService.updateUserTags(userID,tags);
        return result.getJSON();
    }
}
