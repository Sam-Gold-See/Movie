package com.samgoldsee.movie.mapper;

import com.samgoldsee.movie.annotation.AutoFill;
import com.samgoldsee.movie.entity.User;
import com.samgoldsee.movie.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
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

    /**
     * 新增用户记录
     *
     * @param user 用户对象实体类
     */
    @AutoFill(operation = OperationType.INSERT)
    @Insert("INSERT INTO `user` " +
            "(`name`, `email`, `password`, `gender`, `type`, `avatar`, `create_time`, `update_time`) " +
            "VALUES (#{name}, #{email}, #{password}, #{gender}, #{type}, #{avatar}, #{createTime}, #{updateTime})")
    void insert(User user);
}
