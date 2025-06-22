package com.example.bilibili_food_video.dao;

import com.example.bilibili_food_video.domain.BilibiliMonthlyPlayTrend;
import java.util.List;

/**
 * 定义对月度播放量趋势数据操作的接口规范
 */
public interface BilibiliMonthlyPlayTrendDAO {
    /**
     * 查询所有月度播放量趋势数据
     */
    List<BilibiliMonthlyPlayTrend> findAll();

    /**
     * 根据年份查询月度数据
     */
    List<BilibiliMonthlyPlayTrend> findByYear(int year);

    /**
     * 查询节假日月份的数据
     */
    List<BilibiliMonthlyPlayTrend> findHolidayMonths();
}