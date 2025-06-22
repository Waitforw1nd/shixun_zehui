package com.example.bilibili_food_video.dao;

import com.example.bilibili_food_video.domain.BilibiliTagPlayStats;
import java.util.List;

/**
 * 定义对标签播放量相关性数据操作的接口规范
 */
public interface BilibiliTagPlayStatsDAO {
    /**
     * 查询所有标签的播放量统计数据
     */
    List<BilibiliTagPlayStats> findAll();

    /**
     * 根据平均播放量范围查询标签
     */
    List<BilibiliTagPlayStats> findByAvgViewsRange(long minAvgViews, long maxAvgViews);

    /**
     * 查询视频数量最多的标签
     */
    BilibiliTagPlayStats findTagWithMaxVideoCount();

    /**
     * 查询播放量最稳定的标签（标准差最小）
     */
    BilibiliTagPlayStats findTagWithMinStddev();
}