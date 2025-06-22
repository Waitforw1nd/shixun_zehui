package com.example.bilibili_food_video.domain;

/**
 * 封装 bilibili 美食视频标签组合数据的实体类
 */
public class BilibiliTagCombinations {
    private String tagPair; // 标签组合，比如 "美食,探店"
    private int count;      // 该标签组合出现的次数

    public BilibiliTagCombinations() {
    }

    public BilibiliTagCombinations(String tagPair, int count) {
        this.tagPair = tagPair;
        this.count = count;
    }

    public String getTagPair() {
        return tagPair;
    }

    public void setTagPair(String tagPair) {
        this.tagPair = tagPair;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "BilibiliTagCombinations{" +
                "tagPair='" + tagPair + '\'' +
                ", count=" + count +
                '}';
    }
}