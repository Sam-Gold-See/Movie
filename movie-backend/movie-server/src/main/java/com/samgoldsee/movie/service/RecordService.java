package com.samgoldsee.movie.service;

import com.samgoldsee.movie.result.PageResult;
import com.samgoldsee.movie.vo.MovieRankVO;
import jakarta.servlet.http.HttpServletResponse;

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

    /**
     * 导出近30天播放数据报表
     *
     * @param response response
     */
    void exportBusinessData(HttpServletResponse response);
}
