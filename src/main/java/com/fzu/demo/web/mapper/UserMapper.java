package com.fzu.demo.web.mapper;

import com.fzu.demo.web.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
	
	List<UserEntity> getAll();

	List<UserEntity> getOne(@Param("id") Long id, @Param("userName") String userName);

	void insert(UserEntity user);

	void update(UserEntity user);

	void delete(Long id);

}