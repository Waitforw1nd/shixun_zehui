package com.example.bilibili_food_video.dao;

import com.example.bilibili_food_video.domain.BilibiliTagPlayStats;
import com.example.bilibili_food_video.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现 BilibiliTagPlayStatsDAO 接口，通过 JDBC 操作数据库
 */
public class BilibiliTagPlayStatsDAOImpl implements BilibiliTagPlayStatsDAO {
    private static final String SQL_FIND_ALL =
            "SELECT tag, video_count, avg_views, views_stddev, min_views, max_views " +
                    "FROM bilibili_tag_play_stats";

    private static final String SQL_FIND_BY_AVG_VIEWS =
            "SELECT tag, video_count, avg_views, views_stddev, min_views, max_views " +
                    "FROM bilibili_tag_play_stats " +
                    "WHERE avg_views BETWEEN ? AND ? " +
                    "ORDER BY avg_views DESC";

    private static final String SQL_FIND_TAG_WITH_MAX_VIDEO_COUNT =
            "SELECT tag, video_count, avg_views, views_stddev, min_views, max_views " +
                    "FROM bilibili_tag_play_stats " +
                    "WHERE video_count = (SELECT MAX(video_count) FROM bilibili_tag_play_stats)";

    private static final String SQL_FIND_TAG_WITH_MIN_STDDEV =
            "SELECT tag, video_count, avg_views, views_stddev, min_views, max_views " +
                    "FROM bilibili_tag_play_stats " +
                    "WHERE views_stddev = (SELECT MIN(views_stddev) FROM bilibili_tag_play_stats)";

    @Override
    public List<BilibiliTagPlayStats> findAll() {
        List<BilibiliTagPlayStats> resultList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(SQL_FIND_ALL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                BilibiliTagPlayStats stats = mapResultSetToObject(rs);
                resultList.add(stats);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return resultList;
    }

    @Override
    public List<BilibiliTagPlayStats> findByAvgViewsRange(long minAvgViews, long maxAvgViews) {
        List<BilibiliTagPlayStats> resultList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(SQL_FIND_BY_AVG_VIEWS);
            stmt.setLong(1, minAvgViews);
            stmt.setLong(2, maxAvgViews);
            rs = stmt.executeQuery();

            while (rs.next()) {
                BilibiliTagPlayStats stats = mapResultSetToObject(rs);
                resultList.add(stats);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return resultList;
    }

    @Override
    public BilibiliTagPlayStats findTagWithMaxVideoCount() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        BilibiliTagPlayStats stats = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(SQL_FIND_TAG_WITH_MAX_VIDEO_COUNT);
            rs = stmt.executeQuery();

            if (rs.next()) {
                stats = mapResultSetToObject(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return stats;
    }

    @Override
    public BilibiliTagPlayStats findTagWithMinStddev() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        BilibiliTagPlayStats stats = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(SQL_FIND_TAG_WITH_MIN_STDDEV);
            rs = stmt.executeQuery();

            if (rs.next()) {
                stats = mapResultSetToObject(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return stats;
    }

    /**
     * 将结果集映射为实体对象
     */
    private BilibiliTagPlayStats mapResultSetToObject(ResultSet rs) throws SQLException {
        BilibiliTagPlayStats stats = new BilibiliTagPlayStats();
        stats.setTag(rs.getString("tag"));
        stats.setVideoCount(rs.getInt("video_count"));
        stats.setAvgViews(rs.getLong("avg_views"));
        stats.setViewsStddev(rs.getDouble("views_stddev"));
        stats.setMinViews(rs.getLong("min_views"));
        stats.setMaxViews(rs.getLong("max_views"));
        return stats;
    }
}