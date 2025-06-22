package com.example.bilibili_food_video.dao;

import com.example.bilibili_food_video.domain.BilibiliTagCombinations;
import com.example.bilibili_food_video.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现 BilibiliTagCombinationsDAO 接口，通过 JDBC 操作数据库
 */
public class BilibiliTagCombinationsDAOImpl implements BilibiliTagCombinationsDAO {

    private static final String SQL_FIND_ALL = "SELECT tag_pair, count FROM bilibili_tag_combinations";

    @Override
    public List<BilibiliTagCombinations> findAll() {
        List<BilibiliTagCombinations> resultList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // 获取数据库连接
            conn = JDBCUtil.getConnection();
            // 准备 SQL 语句
            stmt = conn.prepareStatement(SQL_FIND_ALL);
            // 执行查询
            rs = stmt.executeQuery();
            // 处理结果集
            while (rs.next()) {
                String tagPair = rs.getString("tag_pair");
                int count = rs.getInt("count");
                BilibiliTagCombinations tagCombination = new BilibiliTagCombinations(tagPair, count);
                resultList.add(tagCombination);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭数据库资源
            JDBCUtil.close(rs, stmt, conn);
        }

        return resultList;
    }
}