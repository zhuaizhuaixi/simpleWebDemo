package com.fzu.demo.web.service.impl;

import com.fzu.demo.common.AlgorithmUtils;
import com.fzu.demo.common.XGameConstant;
import com.fzu.demo.web.entity.GameEntity;
import com.fzu.demo.web.entity.TagEntity;
import com.fzu.demo.web.entity.UserEntity;
import com.fzu.demo.web.mapper.GameMapper;
import com.fzu.demo.web.mapper.TagMapper;
import com.fzu.demo.web.mapper.UserMapper;
import com.fzu.demo.web.service.ICommendService;
import com.fzu.demo.web.service.IGameService;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

/**
 * @author zzx
 *         Created by zzx on 2017/12/27.
 */
@Service
public class CommendServiceImpl implements ICommendService {

    @Autowired
    private GameMapper gameMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private IGameService gameService;

    @Autowired
    private UserMapper userMapper;

    private DecimalFormat df = new DecimalFormat("######0.00");


    @Override
    public List<GameEntity> getCommendList(Integer userID) {
        List<GameEntity> recommendGames = gameMapper.getRecommendList(userID);
        for (GameEntity game : recommendGames) {
            List<TagEntity> gameTags = tagMapper.getGameTags(game.getId());

            StringBuilder tagStr = new StringBuilder();
            for (int i = 0; i < gameTags.size(); i++) {
                if (i != 0) {
                    tagStr.append("/");
                }
                tagStr.append(gameTags.get(i).getName());
            }
            game.setTags(tagStr.toString());


        }
        return recommendGames;
    }

    @Override
    public void produceRecommendList(Integer userID, List<TagEntity> userTags) {
        List<GameEntity> games = gameMapper.getAllGames();
        List<GameEntity> recommendGames = new ArrayList<>();
        for (GameEntity game : games) {
            List<TagEntity> gameTags = tagMapper.getGameTags(game.getId());
            double similar = AlgorithmUtils.recommendationAlgorithmBaseOnContent(userTags, gameTags);
            game.setRecommendIndex(similar * 100);
            if (gameMapper.hasPurchasedGame(userID, game.getId()) == 0) {
                recommendGames.add(game);
            }
        }
        games.sort(Comparator.comparingDouble(GameEntity::getRecommendIndex));
        Collections.reverse(games);

        gameMapper.deleteUserRecommendGames(userID);
        if (recommendGames.size() > 0) {
            gameMapper.insertRecommendGames(userID, recommendGames);
        }
    }

    @Override
    public void cosSimilarBaseOnContent(Integer userID) {
        gameService.produceRecentRecommendList(userID);
    }

    @Override
    public void jaccardSimilarBaseOnContent(Integer userID) {
        List<GameEntity> recentGames = gameMapper.getMyRecentGames(userID, XGameConstant.RECOMMEND_STRATEGY_CHANGE_THRESHOLD);
        if (recentGames.size() >= XGameConstant.RECOMMEND_STRATEGY_CHANGE_THRESHOLD) {
            Set<TagEntity> tagSet = new HashSet<>(20);
            for (GameEntity recentGame : recentGames) {
                List<TagEntity> tags = tagMapper.getGameTags(recentGame.getId());
                tagSet.addAll(tags);
            }
            //进行推荐列表生成
            List<TagEntity> recentUserTags = new ArrayList<>(tagSet);
            produceRecommendListJaccard(userID, recentUserTags);
        } else {
            produceRecommendListJaccard(userID, tagMapper.getUserTags(userID));
        }
    }

    private void produceRecommendListJaccard(Integer userID, List<TagEntity> userTags) {
        List<GameEntity> games = gameMapper.getAllGames();
        List<GameEntity> recommendGames = new ArrayList<>();
        for (GameEntity game : games) {
            List<TagEntity> gameTags = tagMapper.getGameTags(game.getId());
            double similar = AlgorithmUtils.jaccardSimilar(userTags, gameTags);
            game.setRecommendIndex(similar * 100);
            if (gameMapper.hasPurchasedGame(userID, game.getId()) == 0) {
                recommendGames.add(game);
            }
        }
        games.sort(Comparator.comparingDouble(GameEntity::getRecommendIndex));
        Collections.reverse(games);

        gameMapper.deleteUserRecommendGames(userID);
        if (recommendGames.size() > 0) {
            gameMapper.insertRecommendGames(userID, recommendGames);
        }
    }

    @Override
    public void cosSimilarBaseOnFiltering(Integer userID) {
        List<GameEntity> recommendGames = new ArrayList<>(50);
        List<UserEntity> users = userMapper.getAll();
        for (UserEntity user : users) {
            user.setGames(gameMapper.getMyGames(user.getId(), 0, 100));
        }
        Map<Integer, Double> similarMap = AlgorithmUtils.collaborativeFilteringRecommendation(users, userID);
        for (Map.Entry<Integer, Double> entry : similarMap.entrySet()) {
            GameEntity game = gameMapper.getGameById(entry.getKey());
            game.setRecommendIndex(entry.getValue() * 100);
            recommendGames.add(game);
        }
        gameMapper.deleteUserRecommendGames(userID);
        gameMapper.insertRecommendGames(userID, recommendGames);
    }

    @Override
    public void jaccardSimilarBaseOnFiltering(Integer userID) {
        List<GameEntity> recommendGames = new ArrayList<>(50);
        UserEntity recommendUser = userMapper.getUserByID(userID);
        recommendUser.setGames(gameMapper.getMyGames(userID, 0, 100));
        List<UserEntity> users = userMapper.getAll();
        for (UserEntity user : users) {
            user.setGames(gameMapper.getMyGames(user.getId(), 0, 100));
        }
        Map<Integer, Double> similarMap = AlgorithmUtils.jaccardCollaborativeFilteringRecommendation(users, recommendUser);
        for (Map.Entry<Integer, Double> entry : similarMap.entrySet()) {
            GameEntity game = gameMapper.getGameById(entry.getKey());
            game.setRecommendIndex(entry.getValue() * 100);
            recommendGames.add(game);
        }
        gameMapper.deleteUserRecommendGames(userID);
        gameMapper.insertRecommendGames(userID, recommendGames);
    }


}
