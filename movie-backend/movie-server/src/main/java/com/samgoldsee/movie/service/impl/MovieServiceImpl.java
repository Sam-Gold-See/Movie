package com.samgoldsee.movie.service.impl;

import com.samgoldsee.movie.mapper.MovieMapper;
import com.samgoldsee.movie.result.PageResult;
import com.samgoldsee.movie.service.MovieService;
import com.samgoldsee.movie.vo.MovieVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Resource
    private MovieMapper movieMapper;

    /**
     * 获取随机推荐电影
     */
    @Override
    public PageResult<MovieVO> getRec() {
        List<MovieVO> res = movieMapper.selectAll();
        PageResult<MovieVO> pageResult = new PageResult<>();

        Collections.shuffle(res);
        int size = Math.min(res.size(), 4);
        res = res.subList(0, size);

        pageResult.setTotal(res.size());
        pageResult.setRecords(res);

        return pageResult;
    }
}
