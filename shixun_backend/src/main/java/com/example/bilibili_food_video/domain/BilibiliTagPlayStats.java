package com.example.bilibili_food_video.domain;

/**
 * 封装 Bilibili 标签播放量相关性分析数据的实体类
 */
public class BilibiliTagPlayStats {
    private String tag;               // 标签
    private int videoCount;           // 使用该标签的视频数
    private long avgViews;            // 平均播放量
    private double viewsStddev;       // 播放量标准差
    private long minViews;            // 最小播放量
    private long maxViews;            // 最大播放量

    // Getter 和 Setter 方法
    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }

    public int getVideoCount() { return videoCount; }
    public void setVideoCount(int videoCount) { this.videoCount = videoCount; }

    public long getAvgViews() { return avgViews; }
    public void setAvgViews(long avgViews) { this.avgViews = avgViews; }

    public double getViewsStddev() { return viewsStddev; }
    public void setViewsStddev(double viewsStddev) { this.viewsStddev = viewsStddev; }

    public long getMinViews() { return minViews; }
    public void setMinViews(long minViews) { this.minViews = minViews; }

    public long getMaxViews() { return maxViews; }
    public void setMaxViews(long maxViews) { this.maxViews = maxViews; }

    @Override
    public String toString() {
        return "BilibiliTagPlayStats{" +
                "tag='" + tag + '\'' +
                ", videoCount=" + videoCount +
                ", avgViews=" + avgViews +
                ", viewsStddev=" + viewsStddev +
                ", minViews=" + minViews +
                ", maxViews=" + maxViews +
                '}';
    }
}