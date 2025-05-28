package com.samgoldsee.movie.service;

import com.samgoldsee.movie.entity.MovieType;
import com.samgoldsee.movie.entity.MovieZone;
import com.samgoldsee.movie.result.PageResult;
import com.samgoldsee.movie.vo.MovieDetailVO;
import com.samgoldsee.movie.vo.MovieVO;

public interface MovieService {

    /**
     * 获取随机推荐电影
     */
    PageResult<MovieVO> getRec();

    /**
     * 获取电影详情
     *
     * @param movieId 电影ID
     */
    MovieDetailVO getDetailById(Integer movieId);

    /**
     * 播放电影
     *
     * @param userId  用户ID
     * @param movieId 电影ID
     */
    void play(Integer userId, Integer movieId);

    /**
     * 查询电影种类分类
     */
    PageResult<MovieType> getType();

    /**
     * 查询电影地区分类
     */
    PageResult<MovieZone> getZone();
}
