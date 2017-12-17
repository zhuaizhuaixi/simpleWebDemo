package com.fzu.demo.web.service.impl;

import com.fzu.demo.web.entity.GameEntity;
import com.fzu.demo.web.mapper.GameMapper;
import com.fzu.demo.web.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zzx
 * Created by zzx on 2017/12/13.
 */
@Service
public class GameServiceImpl implements IGameService {

    @Autowired
    private GameMapper gameMapper;

    @Override
    public List<GameEntity> getAllGames() {
        return gameMapper.getAllGames();
    }

    @Override
    public List<GameEntity> getGames(Integer page,Integer pageSize) {
        Integer begin = (page - 1 ) * pageSize;
        return gameMapper.getGames(begin,pageSize);
    }

    @Override
    public GameEntity getGameById(Integer id) {
        return gameMapper.getGameById(id);
    }
}
