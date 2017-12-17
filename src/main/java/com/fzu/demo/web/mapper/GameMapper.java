package com.fzu.demo.web.mapper;

import com.fzu.demo.web.entity.GameEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zzx
 *         Created by zzx on 2017/12/13.
 */
@Mapper
public interface GameMapper {

    /**
     * 获得所有游戏信息
     *
     * @return 游戏列表
     */
    List<GameEntity> getAllGames();

    /**
     * 获得分页游戏信息
     * @param fromIndex 起始索引
     * @param toIndex 结束索引
     * @return 游戏列表
     */
    List<GameEntity> getGames(@Param("offset")Integer offset ,@Param("limit") Integer limit);

    /**
     * 通过ID获得对应游戏实体
     *
     * @param ID 游戏ID
     * @return 游戏实体
     */
    GameEntity getGameById(@Param("ID") Integer id);
}
