package com.example.bilibili_food_video.dao;

import com.example.bilibili_food_video.domain.BilibiliUpRanking;
import java.util.List;

/**
 * 定义对 UP 主排行榜数据操作的接口规范
 */
public interface BilibiliUpRankingDAO {
    /**
     * 查询所有 UP 主排行榜数据
     * @return 包含 BilibiliUpRanking 对象的列表
     */
    List<BilibiliUpRanking> findAll();

    /**
     * 根据 UP 主名称查询数据（可选扩展方法）
     */
    BilibiliUpRanking findByPublisher(String publisher);
}