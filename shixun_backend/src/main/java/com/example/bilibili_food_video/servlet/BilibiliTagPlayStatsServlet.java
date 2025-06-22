package com.example.bilibili_food_video.servlet;

import com.example.bilibili_food_video.dao.BilibiliTagPlayStatsDAO;
import com.example.bilibili_food_video.dao.BilibiliTagPlayStatsDAOImpl;
import com.example.bilibili_food_video.domain.BilibiliTagPlayStats;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 处理标签播放量相关性数据请求的 Servlet
 */
@WebServlet("/api/tag-play-stats")
public class BilibiliTagPlayStatsServlet extends HttpServlet {
    private final BilibiliTagPlayStatsDAO tagPlayStatsDAO = new BilibiliTagPlayStatsDAOImpl();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try {
            // 获取查询参数
            String minAvgViewsParam = request.getParameter("minAvgViews");
            String maxAvgViewsParam = request.getParameter("maxAvgViews");
            String maxVideoCountParam = request.getParameter("maxVideoCount");
            String minStddevParam = request.getParameter("minStddev");

            List<BilibiliTagPlayStats> resultList;

            if (minAvgViewsParam != null && maxAvgViewsParam != null) {
                // 按平均播放量范围查询
                long minAvgViews = Long.parseLong(minAvgViewsParam);
                long maxAvgViews = Long.parseLong(maxAvgViewsParam);
                resultList = tagPlayStatsDAO.findByAvgViewsRange(minAvgViews, maxAvgViews);
            } else if (maxVideoCountParam != null && maxVideoCountParam.equalsIgnoreCase("true")) {
                // 查询视频数量最多的标签
                BilibiliTagPlayStats stats = tagPlayStatsDAO.findTagWithMaxVideoCount();
                response.getWriter().write(objectMapper.writeValueAsString(stats));
                return;
            } else if (minStddevParam != null && minStddevParam.equalsIgnoreCase("true")) {
                // 查询播放量最稳定的标签
                BilibiliTagPlayStats stats = tagPlayStatsDAO.findTagWithMinStddev();
                response.getWriter().write(objectMapper.writeValueAsString(stats));
                return;
            } else {
                // 查询所有数据
                resultList = tagPlayStatsDAO.findAll();
            }

            // 转换为 JSON 并返回
            String jsonData = objectMapper.writeValueAsString(resultList);
            response.getWriter().write(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"查询标签播放量相关性数据失败\"}");
        }
    }
}