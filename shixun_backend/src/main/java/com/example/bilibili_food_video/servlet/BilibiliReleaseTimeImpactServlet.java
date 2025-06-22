package com.example.bilibili_food_video.servlet;

import com.example.bilibili_food_video.dao.BilibiliReleaseTimeImpactDAO;
import com.example.bilibili_food_video.dao.BilibiliReleaseTimeImpactDAOImpl;
import com.example.bilibili_food_video.domain.BilibiliReleaseTimeImpact;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 处理发布时段流量影响数据请求的 Servlet
 */
@WebServlet("/api/release-time-impact")
public class BilibiliReleaseTimeImpactServlet extends HttpServlet {
    private final BilibiliReleaseTimeImpactDAO releaseTimeImpactDAO = new BilibiliReleaseTimeImpactDAOImpl();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try {
            // 获取查询参数
            String sortParam = request.getParameter("sort");
            String maxVideoCountParam = request.getParameter("maxVideoCount");

            List<BilibiliReleaseTimeImpact> resultList;

            if (sortParam != null) {
                // 按平均播放量排序查询
                boolean ascending = sortParam.equalsIgnoreCase("asc");
                resultList = releaseTimeImpactDAO.findByAvgViewsOrder(ascending);
            } else if (maxVideoCountParam != null && maxVideoCountParam.equalsIgnoreCase("true")) {
                // 查询视频数量最多的时段
                BilibiliReleaseTimeImpact impact = releaseTimeImpactDAO.findPeriodWithMaxVideoCount();
                response.getWriter().write(objectMapper.writeValueAsString(impact));
                return;
            } else {
                // 查询所有数据
                resultList = releaseTimeImpactDAO.findAll();
            }

            // 转换为 JSON 并返回
            String jsonData = objectMapper.writeValueAsString(resultList);
            response.getWriter().write(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"查询发布时段流量影响数据失败\"}");
        }
    }
}