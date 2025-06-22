package com.example.bilibili_food_video.domain;

/**
 * 封装 Bilibili 三连行为视频数据的实体类
 */
public class BilibiliTrueTripleVideos {
    private String title;               // 视频标题
    private long totalViews;            // 总播放量
    private int likes;                  // 点赞数
    private int favorites;              // 收藏量
    private int coins;                  // 投币数
    private double favoriteLikeRatio;   // 收藏点赞比
    private double coinLikeRatio;       // 投币点赞比
    private String tripleBehavior;      // 是否三连行为（是/否）

    // Getter 和 Setter 方法
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public long getTotalViews() { return totalViews; }
    public void setTotalViews(long totalViews) { this.totalViews = totalViews; }

    public int getLikes() { return likes; }
    public void setLikes(int likes) { this.likes = likes; }

    public int getFavorites() { return favorites; }
    public void setFavorites(int favorites) { this.favorites = favorites; }

    public int getCoins() { return coins; }
    public void setCoins(int coins) { this.coins = coins; }

    public double getFavoriteLikeRatio() { return favoriteLikeRatio; }
    public void setFavoriteLikeRatio(double favoriteLikeRatio) { this.favoriteLikeRatio = favoriteLikeRatio; }

    public double getCoinLikeRatio() { return coinLikeRatio; }
    public void setCoinLikeRatio(double coinLikeRatio) { this.coinLikeRatio = coinLikeRatio; }

    public String getTripleBehavior() { return tripleBehavior; }
    public void setTripleBehavior(String tripleBehavior) { this.tripleBehavior = tripleBehavior; }

    @Override
    public String toString() {
        return "BilibiliTrueTripleVideos{" +
                "title='" + title + '\'' +
                ", totalViews=" + totalViews +
                ", likes=" + likes +
                ", favorites=" + favorites +
                ", coins=" + coins +
                ", favoriteLikeRatio=" + favoriteLikeRatio +
                ", coinLikeRatio=" + coinLikeRatio +
                ", tripleBehavior='" + tripleBehavior + '\'' +
                '}';
    }
}