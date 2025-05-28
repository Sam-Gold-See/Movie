package com.samgoldsee.movie.service.impl;

import com.samgoldsee.movie.mapper.RecordMapper;
import com.samgoldsee.movie.result.PageResult;
import com.samgoldsee.movie.service.RecordService;
import com.samgoldsee.movie.vo.MovieRankVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Resource
    private RecordMapper recordMapper;

    /**
     * 查询全部播放情况
     */
    @Override
    public PageResult<MovieRankVO> rankAll() {
        PageResult<MovieRankVO> pageResult = new PageResult<>();

        List<MovieRankVO> res = recordMapper.rankAll();

        pageResult.setTotal(res.size());
        pageResult.setRecords(res);

        return pageResult;
    }

    /**
     * 查询本月播放情况
     */
    @Override
    public PageResult<MovieRankVO> rankMonth() {
        PageResult<MovieRankVO> pageResult = new PageResult<>();

        List<MovieRankVO> res = recordMapper.rankMonth();

        pageResult.setTotal(res.size());
        pageResult.setRecords(res);

        return pageResult;
    }
}
