package com.samgoldsee.movie.service;

import com.samgoldsee.movie.result.PageResult;
import com.samgoldsee.movie.vo.MovieVO;

public interface MovieService {

    /**
     * 获取随机推荐电影
     */
    PageResult<MovieVO> getRec();

    /**
     * 获取电影详情
     *
     * @param id 电影ID
     */
    MovieVO getById(Integer id);

    /**
     * 播放电影
     *
     * @param userId 用户ID
     * @param movieId 电影ID
     */
    void play(Integer userId, Integer movieId);
}
