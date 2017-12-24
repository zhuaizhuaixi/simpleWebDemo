package com.fzu.demo.web.service;

import com.fzu.demo.web.entity.TagEntity;

import java.util.List;

/**
 * @author zzx
 *         Created by zzx on 2017/12/12.
 */
public interface ITagService {

    /**
     * 获得系统所有标签
     *
     * @return 标签列表
     */
    List<TagEntity> getAllTags();

    /**
     * 获得用户标签
     *
     * @param userID 用户ID
     * @return 标签列表
     */
    List<TagEntity> getUserTags(Integer userID);

    /**
     * 获得游戏标签
     *
     * @param gameID 游戏ID
     * @return 游戏标签列表
     */
    List<TagEntity> getGameTags(Integer gameID);

    /**
     * 通过标签名获得标签
     *
     * @param tagName 标签名
     * @return 标签
     */
    TagEntity getTagByName(String tagName);

    /**
     * 更新用户标签
     *
     * @param tags   新的标签列表
     * @param userID 用户ID
     */
    void updateUserTags(Integer userID, List<TagEntity> tags);

    /**
     * 更新游戏标签
     *
     * @param tags   新的标签列表
     * @param gameID 游戏ID
     */
    void updateGameTags(Integer gameID, List<TagEntity> tags);

    /**
     * 插入游戏标签
     *
     * @param gameID 游戏ID
     * @param tags   游戏标签列表
     */
    void insertGameTags(Integer gameID, List<TagEntity> tags);

    /**
     * 删除标签
     *
     * @param tagID 标签ID
     */
    void deleteTag(Integer tagID);

    /**
     * 新增标签
     *
     * @param tagName 标签名
     */
    void insertTag(String tagName);
}
