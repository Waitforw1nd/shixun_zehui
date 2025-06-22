package com.example.bilibili_food_video.domain;

/**
 * 封装 Bilibili 月度播放量趋势分析数据的实体类
 */
public class BilibiliMonthlyPlayTrend {
    private String publishYearMonth;    // 发布年月（格式：yyyy-MM）
    private long totalViews;            // 月总播放量
    private int videoCount;             // 月视频数
    private long avgViews;              // 月均播放量
    private double viewsStddev;         // 播放量标准差
    private boolean isHolidayMonth;     // 是否节假日月份
    private String holidayDescription;  // 节假日说明

    // Getter 和 Setter 方法
    public String getPublishYearMonth() { return publishYearMonth; }
    public void setPublishYearMonth(String publishYearMonth) { this.publishYearMonth = publishYearMonth; }

    public long getTotalViews() { return totalViews; }
    public void setTotalViews(long totalViews) { this.totalViews = totalViews; }

    public int getVideoCount() { return videoCount; }
    public void setVideoCount(int videoCount) { this.videoCount = videoCount; }

    public long getAvgViews() { return avgViews; }
    public void setAvgViews(long avgViews) { this.avgViews = avgViews; }

    public double getViewsStddev() { return viewsStddev; }
    public void setViewsStddev(double viewsStddev) { this.viewsStddev = viewsStddev; }

    public boolean isHolidayMonth() { return isHolidayMonth; }
    public void setHolidayMonth(boolean holidayMonth) { isHolidayMonth = holidayMonth; }

    public String getHolidayDescription() { return holidayDescription; }
    public void setHolidayDescription(String holidayDescription) { this.holidayDescription = holidayDescription; }

    @Override
    public String toString() {
        return "BilibiliMonthlyPlayTrend{" +
                "publishYearMonth='" + publishYearMonth + '\'' +
                ", totalViews=" + totalViews +
                ", videoCount=" + videoCount +
                ", avgViews=" + avgViews +
                ", viewsStddev=" + viewsStddev +
                ", isHolidayMonth=" + isHolidayMonth +
                ", holidayDescription='" + holidayDescription + '\'' +
                '}';
    }
}