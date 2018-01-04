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
     *
     * @param page     页数
     * @param pageSize 页大小
     * @return 游戏列表
     */
    List<GameEntity> getGames(Integer page, Integer pageSize);

    /**
     * 通过ID获得游戏
     *
     * @param userID 用户ID
     * @param id     游戏ID
     * @return 游戏实体
     */
    GameEntity getGameById(Integer userID, Integer id);

    /**
     * 查看用户是否购买过该游戏
     *
     * @param userID 用户ID
     * @param gameID 游戏ID
     * @return 是否购买过
     */
    boolean hasPurchasedGame(Integer userID, Integer gameID);

    /**
     * 购买游戏
     *
     * @param userID 用户ID
     * @param gameID 游戏ID
     */
    void buyGame(Integer userID, Integer gameID);

    /**
     * 退购游戏
     *
     * @param userID 用户ID
     * @param gameID 游戏ID
     */
    void unBuyGame(Integer userID, Integer gameID);

    /**
     * 查看用户是否关注过该游戏
     *
     * @param userID 用户ID
     * @param gameID 游戏ID
     * @return 是否关注过
     */
    boolean hasStaredGame(Integer userID, Integer gameID);

    /**
     * 关注游戏
     *
     * @param userID 用户ID
     * @param gameID 游戏ID
     */
    void starGame(Integer userID, Integer gameID);

    /**
     * 取消关注游戏
     *
     * @param userID 用户ID
     * @param gameID 游戏ID
     */
    void unStarGame(Integer userID, Integer gameID);

    /**
     * 购买游戏数量
     *
     * @param userID 用户ID
     * @return 游戏数量
     */
    Integer getPurchasedGameNumber(Integer userID);

    /**
     * 关注游戏数量
     *
     * @param userID 用户ID
     * @return 游戏数量
     */
    Integer getStaredGameNumber(Integer userID);

    /**
     * 获取我的游戏
     *
     * @param userID   用户ID
     * @param page     页数
     * @param pageSize 页大小
     * @return 游戏列表
     */
    List<GameEntity> getMyGames(Integer userID, Integer page, Integer pageSize);

    /**
     * 获取关注游戏
     *
     * @param userID   用户ID
     * @param page     页数
     * @param pageSize 页大小
     * @return 关注游戏列表
     */
    List<GameEntity> getStaredGames(Integer userID, Integer page, Integer pageSize);

    /**
     * 更新游戏
     *
     * @param game 游戏实体
     */
    void updateGameInformation(GameEntity game);

    /**
     * 删除游戏
     *
     * @param gameID 游戏ID
     */
    void deleteGame(Integer gameID);

    /**
     * 新增游戏
     *
     * @param game 游戏实体
     * @return 游戏ID
     */
    Integer addGame(GameEntity game);

    /**
     * 通过关键字查找游戏
     *
     * @param keyword 关键字
     * @return 游戏列表
     */
    List<GameEntity> getSearchResult(String keyword);
}
