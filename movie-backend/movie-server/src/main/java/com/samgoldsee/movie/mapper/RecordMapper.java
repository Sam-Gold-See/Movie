package com.samgoldsee.movie.mapper;

import com.samgoldsee.movie.annotation.AutoFill;
import com.samgoldsee.movie.entity.Record;
import com.samgoldsee.movie.enumeration.OperationType;
import com.samgoldsee.movie.vo.MovieRankVO;
import com.samgoldsee.movie.vo.ReportVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordMapper {

    /**
     * 插入观影记录
     *
     * @param record 记录实体类对象
     */
    @AutoFill(operation = OperationType.INSERT)
    @Insert("INSERT INTO `movie_record` (movie_id, user_id, create_time, update_time) " +
            "VALUES (#{movieId}, #{userId}, #{createTime}, #{updateTime})")
    void insert(Record record);

    /**
     * 查询全部播放情况
     */
    List<MovieRankVO> rankAll();

    /**
     * 查询本月播放情况
     */
    List<MovieRankVO> rankMonth();


    /**
     * 查询本周播放情况
     */
    List<MovieRankVO> rankWeek();

    /**
     * 导出近30天播放数据
     */
    List<ReportVO> getExportRecords();
}
