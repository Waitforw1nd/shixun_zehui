package com.example.bilibili_food_video.dao;

import com.example.bilibili_food_video.domain.BilibiliReleaseTimeImpact;
import java.util.List;

/**
 * 定义对发布时段流量影响数据操作的接口规范
 */
public interface BilibiliReleaseTimeImpactDAO {
    /**
     * 查询所有发布时段的流量影响数据
     */
    List<BilibiliReleaseTimeImpact> findAll();

    /**
     * 根据平均播放量排序查询发布时段数据
     */
    List<BilibiliReleaseTimeImpact> findByAvgViewsOrder(boolean ascending);

    /**
     * 查询视频数量最多的发布时段
     */
    BilibiliReleaseTimeImpact findPeriodWithMaxVideoCount();
}