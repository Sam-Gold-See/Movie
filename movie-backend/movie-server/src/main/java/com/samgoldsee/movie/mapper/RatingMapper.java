package com.samgoldsee.movie.mapper;

import com.samgoldsee.movie.annotation.AutoFill;
import com.samgoldsee.movie.entity.Rating;
import com.samgoldsee.movie.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
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
    @Select("SELECT * FROM `rating` WHERE `user_id` = #{userId} AND `movie_id` = #{movieId} ")
    Rating selectByUMId(Integer userId, Integer movieId);

    /**
     * 插入用户影评
     *
     * @param ratingOb 评分实体对象
     */
    @AutoFill(operation = OperationType.INSERT)
    @Insert("INSERT INTO `rating` (`rating`, `user_id`, `movie_id`, `create_time`, `update_time`) VALUES (#{rating}, #{userId}, #{movieId}, #{createTime}, #{updateTime})")
    void insert(Rating ratingOb);

    /**
     * 更新用户影评
     *
     * @param ratingOb 评分实体对象
     */
    @AutoFill(operation = OperationType.UPDATE)
    void update(Rating ratingOb);
}
