package com.example.bilibili_food_video.dao;

import com.example.bilibili_food_video.domain.BilibiliTrueTripleVideos;
import com.example.bilibili_food_video.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现 BilibiliTrueTripleVideosDAO 接口，通过 JDBC 操作数据库
 */
public class BilibiliTrueTripleVideosDAOImpl implements BilibiliTrueTripleVideosDAO {
    private static final String SQL_FIND_ALL =
            "SELECT title, total_views, likes, favorites, coins, " +
                    "favorite_like_ratio, coin_like_ratio, triple_behavior " +
                    "FROM bilibili_true_triple_videos";

    private static final String SQL_FIND_BY_TITLE =
            "SELECT title, total_views, likes, favorites, coins, " +
                    "favorite_like_ratio, coin_like_ratio, triple_behavior " +
                    "FROM bilibili_true_triple_videos WHERE title LIKE ?";

    private static final String SQL_FIND_BY_VIEWS_RANGE =
            "SELECT title, total_views, likes, favorites, coins, " +
                    "favorite_like_ratio, coin_like_ratio, triple_behavior " +
                    "FROM bilibili_true_triple_videos WHERE total_views BETWEEN ? AND ?";

    @Override
    public List<BilibiliTrueTripleVideos> findAll() {
        List<BilibiliTrueTripleVideos> resultList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(SQL_FIND_ALL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                BilibiliTrueTripleVideos video = mapResultSetToObject(rs);
                resultList.add(video);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return resultList;
    }

    @Override
    public List<BilibiliTrueTripleVideos> findByTitle(String title) {
        List<BilibiliTrueTripleVideos> resultList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(SQL_FIND_BY_TITLE);
            stmt.setString(1, "%" + title + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                BilibiliTrueTripleVideos video = mapResultSetToObject(rs);
                resultList.add(video);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return resultList;
    }

    @Override
    public List<BilibiliTrueTripleVideos> findByViewsRange(long minViews, long maxViews) {
        List<BilibiliTrueTripleVideos> resultList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(SQL_FIND_BY_VIEWS_RANGE);
            stmt.setLong(1, minViews);
            stmt.setLong(2, maxViews);
            rs = stmt.executeQuery();

            while (rs.next()) {
                BilibiliTrueTripleVideos video = mapResultSetToObject(rs);
                resultList.add(video);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return resultList;
    }

    /**
     * 将结果集映射为实体对象
     */
    private BilibiliTrueTripleVideos mapResultSetToObject(ResultSet rs) throws SQLException {
        BilibiliTrueTripleVideos video = new BilibiliTrueTripleVideos();
        video.setTitle(rs.getString("title"));
        video.setTotalViews(rs.getLong("total_views"));
        video.setLikes(rs.getInt("likes"));
        video.setFavorites(rs.getInt("favorites"));
        video.setCoins(rs.getInt("coins"));
        video.setFavoriteLikeRatio(rs.getDouble("favorite_like_ratio"));
        video.setCoinLikeRatio(rs.getDouble("coin_like_ratio"));
        video.setTripleBehavior(rs.getString("triple_behavior"));
        return video;
    }
}