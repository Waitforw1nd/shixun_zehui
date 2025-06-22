package com.example.bilibili_food_video.dao;

import com.example.bilibili_food_video.domain.BilibiliNicheUps;
import com.example.bilibili_food_video.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现 BilibiliNicheUpsDAO 接口，通过 JDBC 操作数据库
 */
public class BilibiliNicheUpsDAOImpl implements BilibiliNicheUpsDAO {
    private static final String SQL_FIND_ALL =
            "SELECT publisher, avg_views, avg_interaction_rate, video_count " +
                    "FROM bilibili_niche_ups";

    private static final String SQL_FIND_BY_INTERACTION_RATE =
            "SELECT publisher, avg_views, avg_interaction_rate, video_count " +
                    "FROM bilibili_niche_ups " +
                    "WHERE avg_interaction_rate BETWEEN ? AND ? " +
                    "ORDER BY avg_interaction_rate DESC";

    private static final String SQL_FIND_BY_VIDEO_COUNT =
            "SELECT publisher, avg_views, avg_interaction_rate, video_count " +
                    "FROM bilibili_niche_ups " +
                    "WHERE video_count BETWEEN ? AND ? " +
                    "ORDER BY video_count DESC";

    @Override
    public List<BilibiliNicheUps> findAll() {
        List<BilibiliNicheUps> resultList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(SQL_FIND_ALL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                BilibiliNicheUps nicheUp = mapResultSetToObject(rs);
                resultList.add(nicheUp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return resultList;
    }

    @Override
    public List<BilibiliNicheUps> findByInteractionRateRange(double minRate, double maxRate) {
        List<BilibiliNicheUps> resultList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(SQL_FIND_BY_INTERACTION_RATE);
            stmt.setDouble(1, minRate);
            stmt.setDouble(2, maxRate);
            rs = stmt.executeQuery();

            while (rs.next()) {
                BilibiliNicheUps nicheUp = mapResultSetToObject(rs);
                resultList.add(nicheUp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return resultList;
    }

    @Override
    public List<BilibiliNicheUps> findByVideoCount(int minCount, int maxCount) {
        List<BilibiliNicheUps> resultList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(SQL_FIND_BY_VIDEO_COUNT);
            stmt.setInt(1, minCount);
            stmt.setInt(2, maxCount);
            rs = stmt.executeQuery();

            while (rs.next()) {
                BilibiliNicheUps nicheUp = mapResultSetToObject(rs);
                resultList.add(nicheUp);
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
    private BilibiliNicheUps mapResultSetToObject(ResultSet rs) throws SQLException {
        BilibiliNicheUps nicheUp = new BilibiliNicheUps();
        nicheUp.setPublisher(rs.getString("publisher"));
        nicheUp.setAvgViews(rs.getLong("avg_views"));
        nicheUp.setAvgInteractionRate(rs.getDouble("avg_interaction_rate"));
        nicheUp.setVideoCount(rs.getInt("video_count"));
        return nicheUp;
    }
}