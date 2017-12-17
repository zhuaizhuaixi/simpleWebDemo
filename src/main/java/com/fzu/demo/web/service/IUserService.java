package com.fzu.demo.web.service;

import com.fzu.demo.web.entity.UserEntity;

import java.util.Date;
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
     * 通过用户名获得用户
     * @param username 用户名
     * @return 用户实体
     */
    UserEntity getUserByUsername(String username);

    /**
     * 获得单个用户
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户实体
     */
    UserEntity getUserByUsernameAndPassword(String username, String password);


    /**
     * 通过ID获得用户信息
     *
     * @param id 用户ID
     * @return 用户实体
     */
    UserEntity getUserByID(Integer id);

    /**
     * 更新用户信息
     *
     * @param nickname 昵称
     * @param sex      性别
     * @param birthday 生日
     * @param userID   用户ID
     */
    void updateUser(String nickname, String sex, Date birthday, Integer userID);

    /**
     * 修改密码
     *
     * @param username    用户名
     * @param newPassword 新密码
     */
    void changePassword(String username, String newPassword);

    /**
     * 修改头像
     *
     * @param userID 用户ID
     * @param photo  头像字节数组
     */
    void changePhoto(Integer userID, byte[] photo);



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
