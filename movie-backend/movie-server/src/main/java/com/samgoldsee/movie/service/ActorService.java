package com.samgoldsee.movie.service;

import com.samgoldsee.movie.entity.Actor;
import com.samgoldsee.movie.result.PageResult;

public interface ActorService {

    /**
     * 获取全部演员信息
     */
    PageResult<Actor> getAll();
}
