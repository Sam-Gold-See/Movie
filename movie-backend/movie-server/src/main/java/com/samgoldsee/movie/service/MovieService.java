package com.samgoldsee.movie.service;

import com.samgoldsee.movie.result.PageResult;
import com.samgoldsee.movie.vo.MovieVO;

public interface MovieService {

    /**
     * 获取随机推荐电影
     */
    PageResult<MovieVO> getRec();
}
