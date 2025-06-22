package com.example.bilibili_food_video.dao;

import com.example.bilibili_food_video.domain.BilibiliUpRanking;
import com.example.bilibili_food_video.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现 BilibiliUpRankingDAO 接口，通过 JDBC 操作数据库
 */
public class BilibiliUpRankingDAOImpl implements BilibiliUpRankingDAO {
    private static final String SQL_FIND_ALL =
            "SELECT publisher, video_count, avg_views, score FROM bilibili_up_ranking";
    private static final String SQL_FIND_BY_PUBLISHER =
            "SELECT publisher, video_count, avg_views, score FROM bilibili_up_ranking WHERE publisher = ?";

    @Override
    public List<BilibiliUpRanking> findAll() {
        List<BilibiliUpRanking> resultList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(SQL_FIND_ALL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String publisher = rs.getString("publisher");
                int videoCount = rs.getInt("video_count");
                long avgViews = rs.getLong("avg_views");
                double score = rs.getDouble("score");

                resultList.add(new BilibiliUpRanking(publisher, videoCount, avgViews, score));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return resultList;
    }

    @Override
    public BilibiliUpRanking findByPublisher(String publisher) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        BilibiliUpRanking upRanking = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(SQL_FIND_BY_PUBLISHER);
            stmt.setString(1, publisher);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int videoCount = rs.getInt("video_count");
                long avgViews = rs.getLong("avg_views");
                double score = rs.getDouble("score");
                upRanking = new BilibiliUpRanking(publisher, videoCount, avgViews, score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return upRanking;
    }
}