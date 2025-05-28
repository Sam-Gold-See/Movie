package com.samgoldsee.movie.service;

import com.samgoldsee.movie.result.PageResult;
import com.samgoldsee.movie.vo.MovieRankVO;

public interface RecordService {

    /**
     * 查询全部播放情况
     */
    PageResult<MovieRankVO> rankAll();

    /**
     * 查询本月播放情况
     */
    PageResult<MovieRankVO> rankMonth();

    /**
     * 查询本周播放情况
     */
    PageResult<MovieRankVO> rankWeek();
}
