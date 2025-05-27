package com.samgoldsee.movie.mapper;

import com.samgoldsee.movie.entity.Actor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActorMapper {

    /**
     * 查询所有演员
     */
    @Select("SELECT * FROM `actor`")
    List<Actor> selectAll();
}
