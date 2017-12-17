package com.fzu.demo.web.service;

import com.fzu.demo.web.entity.GameEntity;

import java.util.List;

/**
 * @author zzx
 *         Created by zzx on 2017/12/13.
 */
public interface IGameService {
    /**
     * 获得所有游戏
     *
     * @return 游戏列表
     */
    List<GameEntity> getAllGames();

    /**
     * 通过页数获得游戏列表
     * @param page 页数
     * @param pageSize 页大小
     * @return 游戏列表
     */
    List<GameEntity> getGames(Integer page,Integer pageSize);

    /**
     * 通过ID获得游戏
     *
     * @param id 游戏ID
     * @return 游戏实体
     */
    GameEntity getGameById(Integer id);
}
