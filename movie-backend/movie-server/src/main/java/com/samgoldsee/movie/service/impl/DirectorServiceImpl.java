package com.samgoldsee.movie.service.impl;

import com.samgoldsee.movie.entity.Director;
import com.samgoldsee.movie.mapper.DirectorMapper;
import com.samgoldsee.movie.result.PageResult;
import com.samgoldsee.movie.service.DirectorService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Resource
    private DirectorMapper directorMapper;

    /**
     * 获取全部导演信息
     */
    @Override
    public PageResult<Director> getAll() {
        List<Director> directors = directorMapper.selectAll();
        PageResult<Director> pageResult = new PageResult<>();

        pageResult.setTotal(directors.size());
        pageResult.setRecords(directors);

        return pageResult;
    }
}
