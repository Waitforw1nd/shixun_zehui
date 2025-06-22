package com.example.bilibili_food_video.domain;

/**
 * 封装 Bilibili 高产出 UP 主排行榜数据的实体类
 */
public class BilibiliUpRanking {
    private String publisher;    // UP 主名称
    private int videoCount;      // 视频数量
    private long avgViews;       // 平均播放量
    private double score;        // 综合得分

    public BilibiliUpRanking() {
    }

    public BilibiliUpRanking(String publisher, int videoCount, long avgViews, double score) {
        this.publisher = publisher;
        this.videoCount = videoCount;
        this.avgViews = avgViews;
        this.score = score;
    }

    // Getter 和 Setter 方法
    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public int getVideoCount() { return videoCount; }
    public void setVideoCount(int videoCount) { this.videoCount = videoCount; }

    public long getAvgViews() { return avgViews; }
    public void setAvgViews(long avgViews) { this.avgViews = avgViews; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }

    @Override
    public String toString() {
        return "BilibiliUpRanking{" +
                "publisher='" + publisher + '\'' +
                ", videoCount=" + videoCount +
                ", avgViews=" + avgViews +
                ", score=" + score +
                '}';
    }
}