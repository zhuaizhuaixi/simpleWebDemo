package com.fzu.demo.web.service.impl;

import com.fzu.demo.common.Md5Util;
import com.fzu.demo.common.XGameConstant;
import com.fzu.demo.web.entity.GameEntity;
import com.fzu.demo.web.entity.HistoryEntity;
import com.fzu.demo.web.entity.UserEntity;
import com.fzu.demo.web.mapper.GameMapper;
import com.fzu.demo.web.mapper.HistoryMapper;
import com.fzu.demo.web.mapper.UserMapper;
import com.fzu.demo.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author zzx
 *         Created by zzx on 2017/11/1.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public List<UserEntity> getAllUser() {
        return userMapper.getAll();
    }

    @Override
    public List<UserEntity> getUser(Long id, String userName) {
        return userMapper.getOne(id, userName);
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public UserEntity getUserByUsernameAndPassword(String username, String password) {
        UserEntity user = userMapper.getUser(username, Md5Util.sign(password + XGameConstant.PASSWORD_KEY));
        if (user != null) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String operation = "登录系统。";
            HistoryEntity history = new HistoryEntity(user.getId(), operation, timestamp, XGameConstant.LOGIN_HISTORY);
            historyMapper.insertHistory(history);
        }
        return user;
    }

    @Override
    public UserEntity getUserByID(Integer id) {
        return userMapper.getUserByID(id);
    }

    @Override
    public void updateUser(String nickname, String sex, Date birthday, Integer userID) {
        userMapper.updateUser(nickname, sex, birthday, userID);
    }

    @Override
    public void changePassword(String username, String newPassword) {
        userMapper.changePassword(username, Md5Util.sign(newPassword + XGameConstant.PASSWORD_KEY));
    }

    @Override
    public void changePhoto(Integer userID, byte[] photo) {
        userMapper.changePhoto(userID, photo);
    }

    @Override
    public void insert(UserEntity user) {
        userMapper.insert(user);
    }

    @Override
    public void update(UserEntity user) {
        userMapper.update(user);
    }

    @Override
    public void delete(Long id) {
        userMapper.delete(id);
    }

    @Override
    public List<HistoryEntity> getHistory(Integer userID, Integer type, Integer page, Integer pageSize) {
        Integer begin = (page - 1) * pageSize;
        return historyMapper.getHistory(userID, type, begin, pageSize);
    }

    @Override
    public Integer getHistoryCount(Integer userID, Integer type){
        return historyMapper.getHistoryCount(userID, type);
    }

    @Override
    public void deleteHistory(Integer historyID) {
        historyMapper.deleteHistory(historyID);
    }
}
