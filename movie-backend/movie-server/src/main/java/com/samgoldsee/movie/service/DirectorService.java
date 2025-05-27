package com.samgoldsee.movie.service;

import com.samgoldsee.movie.entity.Director;
import com.samgoldsee.movie.result.PageResult;

public interface DirectorService {

    /**
     * 获取全部导演信息
     */
    PageResult<Director> getAll();
}
