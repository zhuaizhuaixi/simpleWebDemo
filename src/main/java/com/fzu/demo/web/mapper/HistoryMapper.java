package com.fzu.demo.web.mapper;

import com.fzu.demo.web.entity.HistoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zzx
 *         Created by zzx on 2017/12/20.
 */
@Mapper
public interface HistoryMapper {
    /**
     * 添加历史记录
     *
     * @param historyEntity 历史记录实体
     */
    void insertHistory(HistoryEntity historyEntity);

    /**
     * 获得历史记录
     *
     * @param userID 用户ID
     * @param type   历史记录类型1、登录记录；2、购买记录；3、浏览记录
     * @param offset 起始索引
     * @param limit  结束索引
     * @return 历史记录列表
     */
    List<HistoryEntity> getHistory(@Param("userID") Integer userID, @Param("type") Integer type, @Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 获得历史记录数
     *
     * @param userID 用户ID
     * @param type   历史记录类型1、登录记录；2、购买记录；3、浏览记录
     * @return 历史记录数
     */
    Integer getHistoryCount(@Param("userID") Integer userID, @Param("type") Integer type);

    /**
     * 删除历史记录
     *
     * @param historyID 历史记录ID
     */
    void deleteHistory(@Param("historyID") Integer historyID);
}
