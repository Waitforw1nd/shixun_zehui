package com.example.bilibili_food_video.domain;

/**
 * 封装 Bilibili 小众UP主分析数据的实体类
 */
public class BilibiliNicheUps {
    private String publisher;           // UP主名称
    private long avgViews;              // 平均播放量
    private double avgInteractionRate;  // 平均互动率
    private int videoCount;             // 视频数

    // Getter 和 Setter 方法
    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public long getAvgViews() { return avgViews; }
    public void setAvgViews(long avgViews) { this.avgViews = avgViews; }

    public double getAvgInteractionRate() { return avgInteractionRate; }
    public void setAvgInteractionRate(double avgInteractionRate) { this.avgInteractionRate = avgInteractionRate; }

    public int getVideoCount() { return videoCount; }
    public void setVideoCount(int videoCount) { this.videoCount = videoCount; }

    @Override
    public String toString() {
        return "BilibiliNicheUps{" +
                "publisher='" + publisher + '\'' +
                ", avgViews=" + avgViews +
                ", avgInteractionRate=" + avgInteractionRate +
                ", videoCount=" + videoCount +
                '}';
    }
}