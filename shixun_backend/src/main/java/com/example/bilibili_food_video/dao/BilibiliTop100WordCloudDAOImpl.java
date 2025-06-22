package com.example.bilibili_food_video.dao;

import com.example.bilibili_food_video.domain.BilibiliTop100WordCloud;
import com.example.bilibili_food_video.utils.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BilibiliTop100WordCloudDAOImpl implements BilibiliTop100WordCloudDAO {
    private static final String SQL_FIND_ALL = "SELECT word, freq FROM bilibili_top100_word_cloud";

    @Override
    public List<BilibiliTop100WordCloud> findAll() {
        List<BilibiliTop100WordCloud> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // 1. 获取数据库连接
            conn = JDBCUtil.getConnection();
            // 2. 创建SQL语句
            stmt = conn.prepareStatement(SQL_FIND_ALL);
            // 3. 执行查询
            rs = stmt.executeQuery();
            // 4. 处理结果集
            while (rs.next()) {
                String word = rs.getString("word");
                int freq = rs.getInt("freq");
                result.add(new BilibiliTop100WordCloud(word, freq));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 可添加自定义异常处理
        } finally {
            // 5. 关闭资源
            JDBCUtil.close(rs, stmt, conn);
        }

        return result;
    }
}