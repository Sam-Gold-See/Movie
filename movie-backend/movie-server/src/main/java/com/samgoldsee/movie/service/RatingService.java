package com.samgoldsee.movie.service;

import com.samgoldsee.movie.result.PageResult;
import com.samgoldsee.movie.vo.MovieRankVO;

public interface RatingService {


    /**
     * 用户评分
     *
     * @param userId  用户ID
     * @param movieId 电影ID
     * @param rating  评分
     */
    void upload(Integer userId, Integer movieId, Integer rating);

    /**
     * 查询好评排行
     */
    PageResult<MovieRankVO> rank();
}
