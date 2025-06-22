package com.example.bilibili_food_video.domain;

/**
 * 封装 Bilibili 发布时段对流量影响分析数据的实体类
 */
public class BilibiliReleaseTimeImpact {
    private String releasePeriod;    // 发布时段（早上、中午、晚上、凌晨）
    private int videoCount;          // 视频数
    private long totalViews;         // 总播放量
    private long avgViews;           // 平均播放量
    private long minViews;           // 最小播放量
    private long maxViews;           // 最大播放量

    // Getter 和 Setter 方法
    public String getReleasePeriod() { return releasePeriod; }
    public void setReleasePeriod(String releasePeriod) { this.releasePeriod = releasePeriod; }

    public int getVideoCount() { return videoCount; }
    public void setVideoCount(int videoCount) { this.videoCount = videoCount; }

    public long getTotalViews() { return totalViews; }
    public void setTotalViews(long totalViews) { this.totalViews = totalViews; }

    public long getAvgViews() { return avgViews; }
    public void setAvgViews(long avgViews) { this.avgViews = avgViews; }

    public long getMinViews() { return minViews; }
    public void setMinViews(long minViews) { this.minViews = minViews; }

    public long getMaxViews() { return maxViews; }
    public void setMaxViews(long maxViews) { this.maxViews = maxViews; }

    @Override
    public String toString() {
        return "BilibiliReleaseTimeImpact{" +
                "releasePeriod='" + releasePeriod + '\'' +
                ", videoCount=" + videoCount +
                ", totalViews=" + totalViews +
                ", avgViews=" + avgViews +
                ", minViews=" + minViews +
                ", maxViews=" + maxViews +
                '}';
    }
}