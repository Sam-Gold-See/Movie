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
}
