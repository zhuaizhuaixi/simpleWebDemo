package com.fzu.demo.web.service;

import com.fzu.demo.web.entity.GameEntity;
import com.fzu.demo.web.entity.TagEntity;

import java.util.List;

/**
 * @author zzx
 *         Created by zzx on 2017/12/27.
 */
public interface ICommendService {
    /**
     * 获得推荐列表
     *
     * @param userID 用户ID
     * @return 排序过后的推荐列表
     */
    List<GameEntity> getCommendList(Integer userID);

    /**
     * 生成推荐列表
     *
     * @param userID   用户ID
     * @param userTags 用户标签
     */
    void produceRecommendList(Integer userID, List<TagEntity> userTags);

    /**
     * 余弦相似度+基于内容推荐
     *
     * @param userID 用户ID
     */
    void cosSimilarBaseOnContent(Integer userID);

    /**
     * 杰卡德系数+基于内容推荐
     *
     * @param userID 用户ID
     */
    void jaccardSimilarBaseOnContent(Integer userID);

    /**
     * 余弦相似度+协同过滤推荐
     *
     * @param userID 用户ID
     */
    void cosSimilarBaseOnFiltering(Integer userID);

    /**
     * 杰卡德系数+协同过滤推荐
     *
     * @param userID 用户ID
     */
    void jaccardSimilarBaseOnFiltering(Integer userID);
}
