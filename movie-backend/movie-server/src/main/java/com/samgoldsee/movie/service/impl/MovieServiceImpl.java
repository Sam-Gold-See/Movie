package com.samgoldsee.movie.service.impl;

import com.samgoldsee.movie.constant.MessageConstant;
import com.samgoldsee.movie.entity.*;
import com.samgoldsee.movie.entity.Record;
import com.samgoldsee.movie.exception.AccountException;
import com.samgoldsee.movie.mapper.*;
import com.samgoldsee.movie.result.PageResult;
import com.samgoldsee.movie.service.MovieService;
import com.samgoldsee.movie.vo.MovieDetailVO;
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

    @Resource
    private DirectorMapper directorMapper;

    @Resource
    private ActorMapper actorMapper;

    @Resource
    private RecordMapper recordMapper;

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
     * @param movieId 电影ID
     */
    @Override
    public MovieDetailVO getDetailById(Integer movieId) {
        Director directorDB = directorMapper.getByMovieId(movieId);
        Actor actorDB = actorMapper.getByMovieId(movieId);
        Movie movieDB = movieMapper.selectById(movieId);

        MovieDetailVO movieDetailVO = MovieDetailVO.builder()
                .directorId(directorDB.getId())
                .directorName(directorDB.getName())
                .actorId(actorDB.getId())
                .actorName(actorDB.getName())
                .build();
        BeanUtils.copyProperties(movieDB, movieDetailVO);

        return movieDetailVO;
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

        Record record = Record.builder()
                .movieId(movieId)
                .userId(userId)
                .build();
        recordMapper.insert(record);
    }

    /**
     * 查询电影种类分类
     */
    @Override
    public PageResult<MovieType> getType() {
        PageResult<MovieType> pageResult = new PageResult<>();

        List<MovieType> res = movieMapper.selectType();

        pageResult.setTotal(res.size());
        pageResult.setRecords(res);

        return pageResult;
    }
}
