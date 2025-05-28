package com.samgoldsee.movie.service;

import com.samgoldsee.movie.result.PageResult;
import com.samgoldsee.movie.vo.MovieRankVO;

public interface RecordService {

    /**
     * 查询全部播放情况
     */
    PageResult<MovieRankVO> rankAll();
}
