package com.fzu.demo.web.service.Impl;

import com.fzu.demo.common.Md5Util;
import com.fzu.demo.common.XGameConstant;
import com.fzu.demo.web.entity.UserEntity;
import com.fzu.demo.web.mapper.UserMapper;
import com.fzu.demo.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<UserEntity> getAllUser() {
        return userMapper.getAll();
    }

    @Override
    public List<UserEntity> getUser(Long id, String userName) {
        return userMapper.getOne(id, userName);
    }

    @Override
    public UserEntity getUserByUsernameAndPassword(String username, String password) {
        return userMapper.getUser(username, Md5Util.sign(password + XGameConstant.PASSWORD_KEY));
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

}
