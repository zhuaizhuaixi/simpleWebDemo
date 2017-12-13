package com.fzu.demo.web.mapper;

import com.fzu.demo.web.entity.TagEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zzx
 *         Created by zzx on 2017/12/12.
 */
@Mapper
public interface TagMapper {

    /**
     * 获得所有标签
     *
     * @return 所有标签的列表
     */
    List<TagEntity> getAllTags();

    /**
     * 获得用户的标签
     *
     * @param userID 用户ID
     * @return 用户标签列表
     */
    List<TagEntity> getUserTags(@Param("userID") Integer userID);

    /**
     * 通过标签名获得标签
     *
     * @param name 标签名
     * @return 标签
     */
    TagEntity getTagByName(@Param("name") String name);

    /**
     * 删除所有用户标签
     *
     * @param userID 用户ID
     */
    void deleteAllUserTags(@Param("userID") Integer userID);

    /**
     * 插入用户标签
     *
     * @param tags   标签列表
     * @param userID 用户ID
     */
    void insertTags(@Param("tags") List<TagEntity> tags, @Param("userID") Integer userID);
}
