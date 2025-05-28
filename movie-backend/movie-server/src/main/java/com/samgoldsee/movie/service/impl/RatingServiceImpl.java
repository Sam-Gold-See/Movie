package com.samgoldsee.movie.service.impl;

import com.samgoldsee.movie.entity.Rating;
import com.samgoldsee.movie.mapper.RatingMapper;
import com.samgoldsee.movie.result.PageResult;
import com.samgoldsee.movie.service.RatingService;
import com.samgoldsee.movie.vo.MovieRankVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Resource
    private RatingMapper ratingMapper;

    /**
     * 用户评分
     *
     * @param userId  用户ID
     * @param movieId 电影ID
     * @param rating  评分
     */
    @Override
    public void upload(Integer userId, Integer movieId, Integer rating) {
        Rating ratingDB = ratingMapper.selectByUMId(userId, movieId);
        Rating ratingOb;
        if (ratingDB == null) {
            ratingOb = Rating.builder()
                    .userId(userId)
                    .movieId(movieId)
                    .rating(rating)
                    .build();
            ratingMapper.insert(ratingOb);
        } else {
            ratingOb = Rating.builder()
                    .id(ratingDB.getId())
                    .rating(rating)
                    .build();
            ratingMapper.update(ratingOb);
        }
    }

    /**
     * 查询好评排行
     */
    @Override
    public PageResult<MovieRankVO> rank() {
        PageResult<MovieRankVO> pageResult = new PageResult<>();

        List<MovieRankVO> res = ratingMapper.rank();

        pageResult.setTotal(res.size());
        pageResult.setRecords(res);

        return pageResult;
    }
}
