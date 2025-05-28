package com.samgoldsee.movie.service.impl;

import com.samgoldsee.movie.constant.MessageConstant;
import com.samgoldsee.movie.exception.FileException;
import com.samgoldsee.movie.mapper.RecordMapper;
import com.samgoldsee.movie.result.PageResult;
import com.samgoldsee.movie.service.RecordService;
import com.samgoldsee.movie.vo.MovieRankVO;
import com.samgoldsee.movie.vo.ReportVO;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

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

    /**
     * 查询本周播放情况
     */
    @Override
    public PageResult<MovieRankVO> rankWeek() {
        PageResult<MovieRankVO> pageResult = new PageResult<>();

        List<MovieRankVO> res = recordMapper.rankWeek();

        pageResult.setTotal(res.size());
        pageResult.setRecords(res);

        return pageResult;
    }

    /**
     * 导出近30天播放数据报表
     *
     * @param response response
     */
    @Override
    public void exportBusinessData(HttpServletResponse response) {
        List<ReportVO> data = recordMapper.getExportRecords();

        int totalPlays = data.size();
        long totalDuration = data.stream()
                .mapToLong(record -> Duration.between(record.getStartTime(), record.getEndTime()).toMinutes())
                .sum();

        //通过POI将数据写入到Excel文件中
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("xlsx/播放数据报表模板.xlsx");
        try {
            XSSFWorkbook excel = new XSSFWorkbook(Objects.requireNonNull(in));
            XSSFSheet sheet = excel.getSheet("Sheet1");

            // 设置行高为 25 磅（25磅 = 25 * 20 = 500 twips）
            Row row4 = sheet.getRow(3); // 第4行，索引是3
            if (row4 == null) {
                row4 = sheet.createRow(3);
            }
            row4.setHeightInPoints(25);

            // 单元格样式：边框
            CellStyle borderStyle = excel.createCellStyle();
            borderStyle.setBorderTop(BorderStyle.THIN);
            borderStyle.setBorderBottom(BorderStyle.THIN);
            borderStyle.setBorderLeft(BorderStyle.THIN);
            borderStyle.setBorderRight(BorderStyle.THIN);

            // 设置总播放量（第4行第3列 = C4）
            Cell totalPlaysCell = row4.createCell(2); // C列索引是2
            totalPlaysCell.setCellValue(totalPlays);
            totalPlaysCell.setCellStyle(borderStyle);

            // 设置总播放时长（第4行第6列 = F4）
            Cell totalDurationCell = row4.createCell(5); // F列索引是5
            totalDurationCell.setCellValue(totalDuration + " 分钟");
            totalDurationCell.setCellStyle(borderStyle);

            int rowIndex = 6;

            for (ReportVO record : data) {
                Row row = sheet.createRow(rowIndex++);
                row.setHeightInPoints(25); // 明细数据也设置行高为25磅

                createStyledCell(row, 1, String.valueOf(record.getRecordId()), borderStyle);
                createStyledCell(row, 2, record.getUserName(), borderStyle);
                createStyledCell(row, 3, record.getGender().equals("M") ? "男" : "女", borderStyle);
                createStyledCell(row, 4, record.getMovieName(), borderStyle);
                createStyledCell(row, 5, record.getStartTime().toString(), borderStyle);
                createStyledCell(row, 6, record.getEndTime().toString(), borderStyle);
            }

            // 设置下载响应头
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            String fileName = URLEncoder.encode("近30天播放记录报表.xlsx", StandardCharsets.UTF_8);
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

            // 输出到客户端
            ServletOutputStream out = response.getOutputStream();
            excel.write(out);
            out.close();
            in.close();
        } catch (IOException ioe) {
            throw new FileException(MessageConstant.FILE_DOWNLOAD_ERROR);
        }
    }

    private void createStyledCell(Row row, int column, String value, CellStyle style) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }
}
