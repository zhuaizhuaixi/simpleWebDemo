package com.fzu.demo.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.fzu.demo.common.JSONResult;
import com.fzu.demo.common.Md5Util;
import com.fzu.demo.common.XGameConstant;
import com.fzu.demo.web.entity.UserEntity;
import com.fzu.demo.web.service.IUserService;
import com.fzu.demo.web.service.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;

/**
 * Created by zzx on 2017/12/4.
 *
 * @author zzx
 */
@Controller
public class IndexController {

    @Autowired
    private IUserService userService;

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
                result.setCodeAndNote(JSONResult.KEY_CODE_FAIL, "密码错误");
            } else {
                session.setAttribute(XGameConstant.LOGIN_SESSION_KEY, user);
            }
        }
        return result.getJSON();
    }

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request) {
        return "index";
    }

    @RequestMapping(value = "/userInfo")
    public String userInfo() {
        return "userInfo/index";
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public JSONObject logout(HttpServletRequest request) {
        JSONResult result = JSONResult.build();
        HttpSession session = request.getSession();
        session.removeAttribute(XGameConstant.LOGIN_SESSION_KEY);
        return result.getJSON();
    }

    @RequestMapping("icon")
    public void getIcon(HttpServletRequest request, HttpServletResponse response) throws IOException {

        FileInputStream fis = null;
        OutputStream os = null;
        byte[] buffer = null;
        try {
            fis = new FileInputStream("E://springboot/webtest/src/main/webapp/image/user.png");
            os = response.getOutputStream();
            int count = 0;
            buffer = new byte[1024 * 8];
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
                os.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        byte[] data = buffer;
        response.setContentType("img/jpeg");
        response.setCharacterEncoding("utf-8");
        try {

            OutputStream outputStream = response.getOutputStream();
            InputStream in = new ByteArrayInputStream(data);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
