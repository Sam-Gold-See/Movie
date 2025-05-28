package com.samgoldsee.movie.mapper;

import com.samgoldsee.movie.entity.Rating;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RatingMapper {

    /**
     * 根据用户ID和电影ID查询评分记录
     *
     * @param userId  用户ID
     * @param movieId 电影ID
     */
    @Select("SELECT * FROM `rating` WHERE `user_id` = #{userID} AND `movie_id` = #{movieId} ")
    Rating selectByUMId(Integer userId, Integer movieId);
}
