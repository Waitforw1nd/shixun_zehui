package com.example.bilibili_food_video.dao;

import com.example.bilibili_food_video.domain.BilibiliInteractionRatio;
import java.util.List;

/**
 * 定义对视频互动比例数据操作的接口规范
 */
public interface BilibiliInteractionRatioDAO {
    /**
     * 查询所有视频互动比例数据
     */
    List<BilibiliInteractionRatio> findAll();

    /**
     * 根据标题模糊查询视频互动比例数据
     */
    List<BilibiliInteractionRatio> findByTitle(String title);

    /**
     * 查询三连行为的视频
     */
    List<BilibiliInteractionRatio> findByTripleBehavior(String behavior);
}