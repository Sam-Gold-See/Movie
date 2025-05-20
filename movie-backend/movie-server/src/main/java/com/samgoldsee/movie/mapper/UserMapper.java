package com.samgoldsee.movie.mapper;

import com.samgoldsee.movie.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    /**
     * 根据邮箱查找用户
     *
     * @param email 用户邮箱
     * @return User类对象
     */
    @Select("SELECT * FROM `user` WHERE `email` = #{email}")
    User getByEmail(String email);
}
