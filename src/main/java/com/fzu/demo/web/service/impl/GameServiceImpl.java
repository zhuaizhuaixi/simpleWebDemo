package com.fzu.demo.web.service.impl;

import com.fzu.demo.common.XGameConstant;
import com.fzu.demo.web.entity.GameEntity;
import com.fzu.demo.web.entity.HistoryEntity;
import com.fzu.demo.web.entity.TagEntity;
import com.fzu.demo.web.mapper.GameMapper;
import com.fzu.demo.web.mapper.HistoryMapper;
import com.fzu.demo.web.mapper.TagMapper;
import com.fzu.demo.web.service.ICommendService;
import com.fzu.demo.web.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

/**
 * @author zzx
 *         Created by zzx on 2017/12/13.
 */
@Service
public class GameServiceImpl implements IGameService {

    @Autowired
    private GameMapper gameMapper;

    @Autowired
    private HistoryMapper historyMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ICommendService commendService;

    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public List<GameEntity> getAllGames() {
        return gameMapper.getAllGames();
    }

    @Override
    public List<GameEntity> getGames(Integer page, Integer pageSize) {
        Integer begin = (page - 1) * pageSize;
        return gameMapper.getGames(begin, pageSize);
    }

    @Override
    public GameEntity getGameById(Integer userID, Integer id) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        GameEntity game = gameMapper.getGameById(id);
        String operation = "浏览了" + game.getName() + "。";
        HistoryEntity history = new HistoryEntity(userID, operation, timestamp, XGameConstant.VISIT_HISTORY);

        historyMapper.insertHistory(history);
        return game;
    }

    @Override
    public boolean hasPurchasedGame(Integer userID, Integer gameID) {
        Integer num = gameMapper.hasPurchasedGame(userID, gameID);
        return num > 0;
    }

    @Override
    public void buyGame(Integer userID, Integer gameID) {
        Date dt = Date.from(Instant.now());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        GameEntity game = gameMapper.getGameById(gameID);
        String operation = "购买了" + game.getName() + "。";
        HistoryEntity history = new HistoryEntity(userID, operation, timestamp, XGameConstant.PURCHASE_HISTORY);
        historyMapper.insertHistory(history);

        gameMapper.buyGame(userID, gameID, dt);

        produceRecentRecommendList(userID);
    }

    /**
     * 生成推荐列表（包括最近购买的物品）
     *
     * @param userID 用户ID
     */
    @Override
    public void produceRecentRecommendList(Integer userID) {
        List<GameEntity> recentGames = gameMapper.getMyRecentGames(userID, XGameConstant.RECOMMEND_STRATEGY_CHANGE_THRESHOLD);
        if (recentGames.size() >= XGameConstant.RECOMMEND_STRATEGY_CHANGE_THRESHOLD) {
            Set<TagEntity> tagSet = new HashSet<>(20);
            for (GameEntity recentGame : recentGames) {
                List<TagEntity> tags = tagMapper.getGameTags(recentGame.getId());
                tagSet.addAll(tags);
            }
            //进行推荐列表生成
            List<TagEntity> recentUserTags = new ArrayList<>(tagSet);
            commendService.produceRecommendList(userID, recentUserTags);
        } else {
            commendService.produceRecommendList(userID, tagMapper.getUserTags(userID));
        }
    }

    @Override
    public void unBuyGame(Integer userID, Integer gameID) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        GameEntity game = gameMapper.getGameById(gameID);
        String operation = "退购了" + game.getName() + "。";
        HistoryEntity history = new HistoryEntity(userID, operation, timestamp, XGameConstant.PURCHASE_HISTORY);

        historyMapper.insertHistory(history);
        gameMapper.unBuyGame(userID, gameID);
    }

    @Override
    public boolean hasStaredGame(Integer userID, Integer gameID) {
        Integer num = gameMapper.hasStaredGame(userID, gameID);
        return num > 0;
    }

    @Override
    public void starGame(Integer userID, Integer gameID) {
        gameMapper.starGame(userID, gameID);
    }

    @Override
    public void unStarGame(Integer userID, Integer gameID) {
        gameMapper.unStarGame(userID, gameID);
    }

    @Override
    public Integer getPurchasedGameNumber(Integer userID) {
        return gameMapper.getPurchasedGameNumber(userID);
    }

    @Override
    public Integer getStaredGameNumber(Integer userID) {
        return gameMapper.getStaredGameNumber(userID);
    }

    @Override
    public List<GameEntity> getMyGames(Integer userID, Integer page, Integer pageSize) {
        Integer begin = (page - 1) * pageSize;
        return gameMapper.getMyGames(userID, begin, pageSize);
    }

    @Override
    public List<GameEntity> getStaredGames(Integer userID, Integer page, Integer pageSize) {
        Integer begin = (page - 1) * pageSize;
        return gameMapper.getStaredGames(userID, begin, pageSize);
    }

    @Override
    public void updateGameInformation(GameEntity game) {
        gameMapper.updateGameInformation(game);
    }

    @Override
    public void deleteGame(Integer gameID) {
        gameMapper.deleteGame(gameID);
    }

    @Override
    public Integer addGame(GameEntity game) {
        return gameMapper.addGame(game);
    }

    @Override
    public List<GameEntity> getSearchResult(String keyword) {
        return gameMapper.getSearchResult(keyword);
    }

    @Override
    public List<GameEntity> getRecentGames(Integer userID) throws Exception {
        Date currentDate = Date.from(Instant.now());
        Calendar date = Calendar.getInstance();
        date.setTime(currentDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - 15);
        return gameMapper.getRecentGames(userID, df.parse(df.format(date.getTime())), currentDate);
    }
}
