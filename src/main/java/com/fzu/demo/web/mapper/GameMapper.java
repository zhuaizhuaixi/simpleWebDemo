package com.fzu.demo.web.mapper;

import com.fzu.demo.web.entity.GameEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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
     *
     * @param offset 起始索引
     * @param limit  结束索引
     * @return 游戏列表
     */
    List<GameEntity> getGames(@Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 通过ID获得对应游戏实体
     *
     * @param id 游戏ID
     * @return 游戏实体
     */
    GameEntity getGameById(@Param("ID") Integer id);

    /**
     * 查看用户是否购买过游戏
     *
     * @param userID 用户ID
     * @param gameID 游戏ID
     * @return 记录数，大于0则代表购买过
     */
    Integer hasPurchasedGame(@Param("userID") Integer userID, @Param("gameID") Integer gameID);

    /**
     * 购买游戏
     *
     * @param userID 用户ID
     * @param gameID 游戏ID
     * @param date   购买时间
     */
    void buyGame(@Param("userID") Integer userID, @Param("gameID") Integer gameID, @Param("date") Date date);

    /**
     * 退购游戏
     *
     * @param userID 用户ID
     * @param gameID 游戏ID
     */
    void unBuyGame(@Param("userID") Integer userID, @Param("gameID") Integer gameID);

    /**
     * 查看用户是否关注过游戏
     *
     * @param userID 用户ID
     * @param gameID 游戏ID
     * @return 记录数，大于0则代表关注过
     */
    Integer hasStaredGame(@Param("userID") Integer userID, @Param("gameID") Integer gameID);

    /**
     * 关注游戏
     *
     * @param userID 用户ID
     * @param gameID 游戏ID
     */
    void starGame(@Param("userID") Integer userID, @Param("gameID") Integer gameID);

    /**
     * 取消关注游戏
     *
     * @param userID 用户ID
     * @param gameID 游戏ID
     */
    void unStarGame(@Param("userID") Integer userID, @Param("gameID") Integer gameID);

    /**
     * 已购买游戏数量
     *
     * @param userID 用户ID
     * @return 游戏数量
     */
    Integer getPurchasedGameNumber(@Param("userID") Integer userID);

    /**
     * 已关注游戏数量
     *
     * @param userID 用户ID
     * @return 游戏数量
     */
    Integer getStaredGameNumber(@Param("userID") Integer userID);

    /**
     * 获得我的游戏
     *
     * @param userID 用户ID
     * @param offset 起始索引
     * @param limit  结束索引
     * @return 我的游戏列表
     */
    List<GameEntity> getMyGames(@Param("userID") Integer userID, @Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 获得我关注的游戏
     *
     * @param userID 用户ID
     * @param offset 起始索引
     * @param limit  结束索引
     * @return 关注游戏列表
     */
    List<GameEntity> getStaredGames(@Param("userID") Integer userID, @Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 更新游戏信息
     *
     * @param gameEntity 游戏实体
     */
    void updateGameInformation(GameEntity gameEntity);

    /**
     * 删除游戏
     *
     * @param gameID 游戏ID
     */
    void deleteGame(Integer gameID);

    /**
     * 新增游戏
     *
     * @param gameEntity 游戏实体
     * @return 游戏ID
     */
    Integer addGame(GameEntity gameEntity);
}
