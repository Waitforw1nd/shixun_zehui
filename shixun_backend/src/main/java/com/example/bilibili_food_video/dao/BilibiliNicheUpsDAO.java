package com.example.bilibili_food_video.dao;

import com.example.bilibili_food_video.domain.BilibiliNicheUps;
import java.util.List;

/**
 * 定义对小众UP主分析数据操作的接口规范
 */
public interface BilibiliNicheUpsDAO {
    /**
     * 查询所有小众UP主数据
     */
    List<BilibiliNicheUps> findAll();

    /**
     * 根据互动率范围查询小众UP主
     */
    List<BilibiliNicheUps> findByInteractionRateRange(double minRate, double maxRate);

    /**
     * 根据视频数量查询小众UP主
     */
    List<BilibiliNicheUps> findByVideoCount(int minCount, int maxCount);
}