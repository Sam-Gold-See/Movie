package com.samgoldsee.movie.mapper;

import com.samgoldsee.movie.vo.MovieVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MovieMapper {

    /**
     * 查询所有电影
     */
    @Select("SELECT * FROM movie")
    List<MovieVO> selectAll();
}
