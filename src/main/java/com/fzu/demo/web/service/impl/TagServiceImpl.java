package com.fzu.demo.web.service.impl;

import com.fzu.demo.web.entity.GameEntity;
import com.fzu.demo.web.entity.TagEntity;
import com.fzu.demo.web.mapper.GameMapper;
import com.fzu.demo.web.mapper.TagMapper;
import com.fzu.demo.web.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Map.Entry;

/**
 * @author zzx
 *         Created by zzx on 2017/12/12.
 */
@Service
public class TagServiceImpl implements ITagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private GameMapper gameMapper;

    @Override
    public List<TagEntity> getAllTags() {
        return tagMapper.getAllTags();
    }

    @Override
    public List<TagEntity> getUserTags(Integer userID) {
        return tagMapper.getUserTags(userID);
    }

    @Override
    public List<TagEntity> getGameTags(Integer gameID) {
        return tagMapper.getGameTags(gameID);
    }

    @Override
    public TagEntity getTagByName(String tagName) {
        return tagMapper.getTagByName(tagName);
    }

    @Override
    public void updateUserTags(Integer userID, List<TagEntity> tags) {
        tagMapper.deleteAllUserTags(userID);
        tagMapper.insertTags(tags, userID);
    }

    @Override
    public void updateGameTags(Integer gameID, List<TagEntity> tags) {
        tagMapper.deleteAllGameTags(gameID);
        tagMapper.insertGameTags(tags, gameID);
    }

    @Override
    public void insertGameTags(Integer gameID, List<TagEntity> tags) {
        tagMapper.insertGameTags(tags, gameID);
    }

    @Override
    public void deleteTag(Integer tagID) {
        tagMapper.deleteTag(tagID);
    }

    @Override
    public void insertTag(String tagName) {
        tagMapper.insertTag(tagName);
    }

    @Override
    public Map<String, Integer> getTagPercentage(Integer userID) {
        Map<String, Integer> map = new TreeMap<>();
        List<GameEntity> games = gameMapper.getMyGames(userID, 0, 10000);
        for (GameEntity game : games) {
            List<TagEntity> tags = tagMapper.getGameTags(game.getId());
            for (TagEntity tag : tags) {
                String tagName = tag.getName();
                map.merge(tagName, 1, (a, b) -> a + b);
            }
        }
        return map;
    }

    @Override
    public List<Map<String, Object>> getPopularTags() {
        return tagMapper.getPopularTags();
    }
}
