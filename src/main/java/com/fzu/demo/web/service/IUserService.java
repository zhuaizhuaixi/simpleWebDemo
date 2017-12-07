package com.fzu.demo.web.service;

import com.fzu.demo.web.entity.UserEntity;

import java.util.List;

/**
 * @author zzx
 *         Created by zzx on 2017/11/1.
 */
public interface IUserService {
    /**
     * 获得所有用户
     *
     * @return 用户列表
     */
    List<UserEntity> getAllUser();

    /**
     * 获得单个用户
     *
     * @param id       用户id
     * @param userName 用户姓名
     * @return 用户实体
     */
    List<UserEntity> getUser(Long id, String userName);

    /**
     * 获得单个用户
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户实体
     */
    UserEntity getUserByUsernameAndPassword( String username,String password);

    /**
     * 新增用户
     *
     * @param user 新增用户实体
     */
    void insert(UserEntity user);

    /**
     * 更新用户实体
     *
     * @param user 更新后的用户实体
     */
    void update(UserEntity user);

    /**
     * 删除用户实体
     *
     * @param id 用户id
     */
    void delete(Long id);
}
