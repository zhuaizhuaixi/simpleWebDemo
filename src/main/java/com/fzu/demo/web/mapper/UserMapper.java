package com.fzu.demo.web.mapper;

import com.fzu.demo.web.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author zzx
 */
@Mapper
public interface UserMapper {

    /**
     * 获得所有用户
     *
     * @return 用户列表
     */
    List<UserEntity> getAll();

    /**
     * 获得单个用户
     *
     * @param id       用户ID
     * @param userName 用户名
     * @return 用户
     */
    List<UserEntity> getOne(@Param("id") Long id, @Param("userName") String userName);

    /**
     * 通过用户名和密码获得用户
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户
     */
    UserEntity getUser(@Param("username") String username, @Param("password") String password);

    /**
     * 通过ID获得用户
     *
     * @param id 用户ID
     * @return 用户
     */
    UserEntity getUserByID(@Param("id") Integer id);

    /**
     * 更新用户信息
     *
     * @param nickname 昵称
     * @param sex      性别
     * @param birthday 生日
     * @param id       用户ID
     */
    void updateUser(@Param("nickname") String nickname, @Param("sex") String sex, @Param("birthday") Date birthday, @Param("id") Integer id);

    /**
     * 修改密码
     *
     * @param username 用户名
     * @param password 新密码
     */
    void changePassword(@Param("username") String username, @Param("newPassword") String password);

    /**
     * 修改头像
     *
     * @param id    用户ID
     * @param photo 头像字节数组
     */
    void changePhoto(@Param("id") Integer id, @Param("photo") byte[] photo);

    /**
     * 插入用户
     *
     * @param user 用户
     */
    void insert(UserEntity user);

    /**
     * 更新用户
     *
     * @param user 用户
     */
    void update(UserEntity user);

    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    void delete(Long id);

}