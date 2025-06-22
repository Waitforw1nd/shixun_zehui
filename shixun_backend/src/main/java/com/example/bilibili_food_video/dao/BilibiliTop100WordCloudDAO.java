package com.example.bilibili_food_video.dao;

import com.example.bilibili_food_video.domain.BilibiliTop100WordCloud;
import java.util.List;

public interface BilibiliTop100WordCloudDAO {
    /**
     * 查询TOP100高频词
     */
    List<BilibiliTop100WordCloud> findAll();
}