package com.example.bilibili_food_video.dao;

import com.example.bilibili_food_video.domain.BilibiliMonthlyPlayTrend;
import com.example.bilibili_food_video.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现 BilibiliMonthlyPlayTrendDAO 接口，通过 JDBC 操作数据库
 */
public class BilibiliMonthlyPlayTrendDAOImpl implements BilibiliMonthlyPlayTrendDAO {
    private static final String SQL_FIND_ALL =
            "SELECT publish_year_month, total_views, video_count, avg_views, " +
                    "views_stddev, is_holiday_month, holiday_description " +
                    "FROM bilibili_monthly_play_trend ORDER BY publish_year_month";

    private static final String SQL_FIND_BY_YEAR =
            "SELECT publish_year_month, total_views, video_count, avg_views, " +
                    "views_stddev, is_holiday_month, holiday_description " +
                    "FROM bilibili_monthly_play_trend WHERE publish_year_month LIKE ? " +
                    "ORDER BY publish_year_month";

    private static final String SQL_FIND_HOLIDAY_MONTHS =
            "SELECT publish_year_month, total_views, video_count, avg_views, " +
                    "views_stddev, is_holiday_month, holiday_description " +
                    "FROM bilibili_monthly_play_trend WHERE is_holiday_month = 1 " +
                    "ORDER BY publish_year_month";

    @Override
    public List<BilibiliMonthlyPlayTrend> findAll() {
        List<BilibiliMonthlyPlayTrend> resultList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(SQL_FIND_ALL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                BilibiliMonthlyPlayTrend trend = mapResultSetToObject(rs);
                resultList.add(trend);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return resultList;
    }

    @Override
    public List<BilibiliMonthlyPlayTrend> findByYear(int year) {
        List<BilibiliMonthlyPlayTrend> resultList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(SQL_FIND_BY_YEAR);
            stmt.setString(1, year + "-%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                BilibiliMonthlyPlayTrend trend = mapResultSetToObject(rs);
                resultList.add(trend);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return resultList;
    }

    @Override
    public List<BilibiliMonthlyPlayTrend> findHolidayMonths() {
        List<BilibiliMonthlyPlayTrend> resultList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(SQL_FIND_HOLIDAY_MONTHS);
            rs = stmt.executeQuery();

            while (rs.next()) {
                BilibiliMonthlyPlayTrend trend = mapResultSetToObject(rs);
                resultList.add(trend);
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
    private BilibiliMonthlyPlayTrend mapResultSetToObject(ResultSet rs) throws SQLException {
        BilibiliMonthlyPlayTrend trend = new BilibiliMonthlyPlayTrend();
        trend.setPublishYearMonth(rs.getString("publish_year_month"));
        trend.setTotalViews(rs.getLong("total_views"));
        trend.setVideoCount(rs.getInt("video_count"));
        trend.setAvgViews(rs.getLong("avg_views"));
        trend.setViewsStddev(rs.getDouble("views_stddev"));
        trend.setHolidayMonth(rs.getInt("is_holiday_month") == 1);
        trend.setHolidayDescription(rs.getString("holiday_description"));
        return trend;
    }
}