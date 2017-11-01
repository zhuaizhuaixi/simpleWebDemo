package com.fzu.demo.web.mapper;

import com.fzu.demo.web.entity.CourseEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by zzx on 2017/10/24.
 */
@Mapper
public interface CourseMapper {

    List<CourseEntity> getAll();

}
