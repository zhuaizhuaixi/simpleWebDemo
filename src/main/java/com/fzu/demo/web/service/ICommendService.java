package com.fzu.demo.web.service;

import com.fzu.demo.web.entity.GameEntity;

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
     * @param userID 用户ID
     */
    void produceRecommendList(Integer userID);
}
