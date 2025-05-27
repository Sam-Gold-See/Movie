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

    /**
     * 根据电影ID查询演员信息
     *
     * @param movieId 电影ID
     */
    @Select("SELECT * FROM actor a " +
            "JOIN `actor-movie` am ON a.id = am.actor_id " +
            "WHERE movie_id = #{movieId}")
    Actor getByMovieId(Integer movieId);
}
