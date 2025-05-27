package com.samgoldsee.movie.mapper;

import com.samgoldsee.movie.entity.Director;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DirectorMapper {

    /**
     * 查询所有导演
     */
    @Select("SELECT * FROM director")
    List<Director> selectAll();

    /**
     * 根据电影ID查询导演信息
     *
     * @param movieId 电影ID
     */
    @Select("SELECT * FROM director d " +
            "JOIN `director-movie` dm ON d.id = dm.director_id " +
            "WHERE dm.movie_id = #{movieId}")
    Director getByMovieId(Integer movieId);
}
