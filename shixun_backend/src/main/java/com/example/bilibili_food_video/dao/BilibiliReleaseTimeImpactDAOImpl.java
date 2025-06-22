package com.example.bilibili_food_video.dao;

import com.example.bilibili_food_video.domain.BilibiliReleaseTimeImpact;
import com.example.bilibili_food_video.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现 BilibiliReleaseTimeImpactDAO 接口，通过 JDBC 操作数据库
 */
public class BilibiliReleaseTimeImpactDAOImpl implements BilibiliReleaseTimeImpactDAO {
    private static final String SQL_FIND_ALL =
            "SELECT release_period, video_count, total_views, avg_views, min_views, max_views " +
                    "FROM bilibili_release_time_impact";

    private static final String SQL_FIND_BY_AVG_VIEWS_ASC =
            "SELECT release_period, video_count, total_views, avg_views, min_views, max_views " +
                    "FROM bilibili_release_time_impact ORDER BY avg_views ASC";

    private static final String SQL_FIND_BY_AVG_VIEWS_DESC =
            "SELECT release_period, video_count, total_views, avg_views, min_views, max_views " +
                    "FROM bilibili_release_time_impact ORDER BY avg_views DESC";

    private static final String SQL_FIND_PERIOD_WITH_MAX_VIDEO_COUNT =
            "SELECT release_period, video_count, total_views, avg_views, min_views, max_views " +
                    "FROM bilibili_release_time_impact WHERE video_count = (SELECT MAX(video_count) FROM bilibili_release_time_impact)";

    @Override
    public List<BilibiliReleaseTimeImpact> findAll() {
        List<BilibiliReleaseTimeImpact> resultList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(SQL_FIND_ALL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                BilibiliReleaseTimeImpact impact = mapResultSetToObject(rs);
                resultList.add(impact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return resultList;
    }

    @Override
    public List<BilibiliReleaseTimeImpact> findByAvgViewsOrder(boolean ascending) {
        List<BilibiliReleaseTimeImpact> resultList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = ascending ? SQL_FIND_BY_AVG_VIEWS_ASC : SQL_FIND_BY_AVG_VIEWS_DESC;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                BilibiliReleaseTimeImpact impact = mapResultSetToObject(rs);
                resultList.add(impact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return resultList;
    }

    @Override
    public BilibiliReleaseTimeImpact findPeriodWithMaxVideoCount() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        BilibiliReleaseTimeImpact impact = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(SQL_FIND_PERIOD_WITH_MAX_VIDEO_COUNT);
            rs = stmt.executeQuery();

            if (rs.next()) {
                impact = mapResultSetToObject(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return impact;
    }

    /**
     * 将结果集映射为实体对象
     */
    private BilibiliReleaseTimeImpact mapResultSetToObject(ResultSet rs) throws SQLException {
        BilibiliReleaseTimeImpact impact = new BilibiliReleaseTimeImpact();
        impact.setReleasePeriod(rs.getString("release_period"));
        impact.setVideoCount(rs.getInt("video_count"));
        impact.setTotalViews(rs.getLong("total_views"));
        impact.setAvgViews(rs.getLong("avg_views"));
        impact.setMinViews(rs.getLong("min_views"));
        impact.setMaxViews(rs.getLong("max_views"));
        return impact;
    }
}