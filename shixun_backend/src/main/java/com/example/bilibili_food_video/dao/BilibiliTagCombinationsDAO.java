package com.example.bilibili_food_video.dao;

import com.example.bilibili_food_video.domain.BilibiliTagCombinations;
import java.util.List;

/**
 * 定义对标签组合数据操作的接口规范
 */
public interface BilibiliTagCombinationsDAO {

    /**
     * 查询所有标签组合及对应数量数据
     * @return 包含 BilibiliTagCombinations 对象的列表
     */
    List<BilibiliTagCombinations> findAll();
}