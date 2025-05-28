package com.samgoldsee.movie.mapper;

import com.samgoldsee.movie.entity.Movie;
import com.samgoldsee.movie.entity.MovieType;
import com.samgoldsee.movie.entity.MovieZone;
import com.samgoldsee.movie.vo.MovieVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MovieMapper {

    /**
     * 查询所有电影
     */
    List<MovieVO> selectAll();

    /**
     * 根据ID查询电影
     *
     * @param id 电影ID
     */
    @Select("SELECT * FROM movie WHERE id = #{id}")
    Movie selectById(Integer id);

    /**
     * 查询电影种类分类
     */
    @Select("SELECT * FROM `movie_type` ORDER BY `id`")
    List<MovieType> selectType();

    /**
     * 查询电影地区分类
     */
    @Select("SELECT * FROM `movie_zone` ORDER BY `id`")
    List<MovieZone> selectZone();

    /**
     * 根据条件查询电影
     *
     * @param typeId 种类ID
     * @param zoneId 地区ID
     */
    List<MovieVO> selectOption(Integer typeId, Integer zoneId);
}
