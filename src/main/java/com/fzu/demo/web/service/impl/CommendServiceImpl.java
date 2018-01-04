package com.fzu.demo.web.service.impl;

import com.fzu.demo.common.AlgorithmUtils;
import com.fzu.demo.web.entity.GameEntity;
import com.fzu.demo.web.entity.TagEntity;
import com.fzu.demo.web.mapper.GameMapper;
import com.fzu.demo.web.mapper.TagMapper;
import com.fzu.demo.web.service.ICommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    public void produceRecommendList(Integer userID) {
        List<TagEntity> userTags = tagMapper.getUserTags(userID);
        List<GameEntity> games = gameMapper.getAllGames();
        List<GameEntity> recommendGames = new ArrayList<>();
        for (GameEntity game : games) {
            List<TagEntity> gameTags = tagMapper.getGameTags(game.getId());
            double similar = AlgorithmUtils.recommendationAlgorithmBaseOnContent(userTags, gameTags);
            game.setRecommendIndex(similar * 100);
            if (similar >= 0.60) {
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
}
