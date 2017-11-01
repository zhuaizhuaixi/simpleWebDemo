package com.fzu.demo.web.service.Impl;

import com.fzu.demo.web.entity.UserEntity;
import com.fzu.demo.web.mapper.UserMapper;
import com.fzu.demo.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zzx
 *         Created by zzx on 2017/11/1.
 */
@Service
public class UserService implements IUserService {

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
