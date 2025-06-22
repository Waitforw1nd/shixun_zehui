package com.example.bilibili_food_video.dao;

import com.example.bilibili_food_video.domain.BilibiliInteractionRatio;
import com.example.bilibili_food_video.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现 BilibiliInteractionRatioDAO 接口，通过 JDBC 操作数据库
 */
public class BilibiliInteractionRatioDAOImpl implements BilibiliInteractionRatioDAO {
    private static final String SQL_FIND_ALL =
            "SELECT title, total_views, likes, favorites, coins, " +
                    "favorite_like_ratio, coin_like_ratio, triple_behavior " +
                    "FROM bilibili_interaction_ratio";

    private static final String SQL_FIND_BY_TITLE =
            "SELECT title, total_views, likes, favorites, coins, " +
                    "favorite_like_ratio, coin_like_ratio, triple_behavior " +
                    "FROM bilibili_interaction_ratio WHERE title LIKE ?";

    private static final String SQL_FIND_BY_TRIPLE_BEHAVIOR =
            "SELECT title, total_views, likes, favorites, coins, " +
                    "favorite_like_ratio, coin_like_ratio, triple_behavior " +
                    "FROM bilibili_interaction_ratio WHERE triple_behavior = ?";

    @Override
    public List<BilibiliInteractionRatio> findAll() {
        List<BilibiliInteractionRatio> resultList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(SQL_FIND_ALL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                BilibiliInteractionRatio ratio = mapResultSetToObject(rs);
                resultList.add(ratio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return resultList;
    }

    @Override
    public List<BilibiliInteractionRatio> findByTitle(String title) {
        List<BilibiliInteractionRatio> resultList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(SQL_FIND_BY_TITLE);
            stmt.setString(1, "%" + title + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                BilibiliInteractionRatio ratio = mapResultSetToObject(rs);
                resultList.add(ratio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return resultList;
    }

    @Override
    public List<BilibiliInteractionRatio> findByTripleBehavior(String behavior) {
        List<BilibiliInteractionRatio> resultList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(SQL_FIND_BY_TRIPLE_BEHAVIOR);
            stmt.setString(1, behavior);
            rs = stmt.executeQuery();

            while (rs.next()) {
                BilibiliInteractionRatio ratio = mapResultSetToObject(rs);
                resultList.add(ratio);
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
    private BilibiliInteractionRatio mapResultSetToObject(ResultSet rs) throws SQLException {
        BilibiliInteractionRatio ratio = new BilibiliInteractionRatio();
        ratio.setTitle(rs.getString("title"));
        ratio.setTotalViews(rs.getLong("total_views"));
        ratio.setLikes(rs.getInt("likes"));
        ratio.setFavorites(rs.getInt("favorites"));
        ratio.setCoins(rs.getInt("coins"));
        ratio.setFavoriteLikeRatio(rs.getDouble("favorite_like_ratio"));
        ratio.setCoinLikeRatio(rs.getDouble("coin_like_ratio"));
        ratio.setTripleBehavior(rs.getString("triple_behavior"));
        return ratio;
    }
}