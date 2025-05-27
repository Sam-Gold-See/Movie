package com.samgoldsee.movie.service.impl;

import com.samgoldsee.movie.service.ActorService;
import com.samgoldsee.movie.entity.Actor;
import com.samgoldsee.movie.mapper.ActorMapper;
import com.samgoldsee.movie.result.PageResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Resource
    private ActorMapper actorMapper;

    /**
     * 获取全部演员信息
     */
    @Override
    public PageResult<Actor> getAll() {
        List<Actor> actors = actorMapper.selectAll();
        PageResult<Actor> pageResult = new PageResult<>();

        pageResult.setTotal(actors.size());
        pageResult.setRecords(actors);

        return pageResult;
    }
}
