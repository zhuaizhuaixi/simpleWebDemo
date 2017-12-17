package com.fzu.demo.web.service.impl;

import com.fzu.demo.web.entity.TagEntity;
import com.fzu.demo.web.mapper.TagMapper;
import com.fzu.demo.web.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zzx on 2017/12/12.
 */
@Service
public class TagServiceImpl implements ITagService {

    @Autowired
    private TagMapper tagMapper;

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
}
