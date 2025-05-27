package com.samgoldsee.movie.service.impl;

import com.samgoldsee.movie.constant.MessageConstant;
import com.samgoldsee.movie.entity.Movie;
import com.samgoldsee.movie.exception.AccountException;
import com.samgoldsee.movie.mapper.MovieMapper;
import com.samgoldsee.movie.mapper.UserMapper;
import com.samgoldsee.movie.result.PageResult;
import com.samgoldsee.movie.service.MovieService;
import com.samgoldsee.movie.vo.MovieVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Resource
    private MovieMapper movieMapper;

    @Resource
    private UserMapper userMapper;

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

    /**
     * 获取电影详情
     *
     * @param id 电影ID
     */
    @Override
    public MovieVO getById(Integer id) {
        Movie movieDB = movieMapper.selectById(id);

        MovieVO movieVO = new MovieVO();
        BeanUtils.copyProperties(movieDB, movieVO);

        return movieVO;
    }

    /**
     * 播放电影
     *
     * @param userId  用户ID
     * @param movieId 电影ID
     */
    @Override
    public void play(Integer userId, Integer movieId) {
        Boolean user = userMapper.getById(userId).getType();
        Boolean movie = movieMapper.selectById(movieId).getPermission();

        if (!user && movie)
            throw new AccountException(MessageConstant.PERMISSION_ERROR);
    }
}
