package com.example.bilibili_food_video.domain;

public class BilibiliTop100WordCloud {
    private String word;
    private int freq;

    // 构造函数
    public BilibiliTop100WordCloud() {}
    public BilibiliTop100WordCloud(String word, int freq) {
        this.word = word;
        this.freq = freq;
    }

    // Getters and Setters
    public String getWord() { return word; }
    public void setWord(String word) { this.word = word; }
    public int getFreq() { return freq; }
    public void setFreq(int freq) { this.freq = freq; }

    @Override
    public String toString() {
        return "BilibiliTop100WordCloud{" +
                "word='" + word + '\'' +
                ", freq=" + freq +
                '}';
    }
}