package com.example.bilibili_food_video.dao;

import com.example.bilibili_food_video.domain.BilibiliTrueTripleVideos;
import java.util.List;

/**
 * 定义对三连行为视频数据操作的接口规范
 */
public interface BilibiliTrueTripleVideosDAO {
    /**
     * 查询所有三连行为视频数据
     */
    List<BilibiliTrueTripleVideos> findAll();

    /**
     * 根据标题模糊查询三连行为视频
     */
    List<BilibiliTrueTripleVideos> findByTitle(String title);

    /**
     * 根据播放量范围查询三连行为视频
     */
    List<BilibiliTrueTripleVideos> findByViewsRange(long minViews, long maxViews);
}