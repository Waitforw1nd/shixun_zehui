package com.example.bilibili_food_video.servlet;

import com.example.bilibili_food_video.dao.BilibiliTrueTripleVideosDAO;
import com.example.bilibili_food_video.dao.BilibiliTrueTripleVideosDAOImpl;
import com.example.bilibili_food_video.domain.BilibiliTrueTripleVideos;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 处理三连行为视频数据请求的 Servlet
 */
@WebServlet("/api/true-triple-videos")
public class BilibiliTrueTripleVideosServlet extends HttpServlet {
    private final BilibiliTrueTripleVideosDAO trueTripleVideosDAO = new BilibiliTrueTripleVideosDAOImpl();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try {
            // 获取查询参数
            String title = request.getParameter("title");
            String minViewsStr = request.getParameter("minViews");
            String maxViewsStr = request.getParameter("maxViews");

            List<BilibiliTrueTripleVideos> resultList;

            if (title != null && !title.isEmpty()) {
                // 按标题模糊查询
                resultList = trueTripleVideosDAO.findByTitle(title);
            } else if (minViewsStr != null && maxViewsStr != null) {
                // 按播放量范围查询
                long minViews = Long.parseLong(minViewsStr);
                long maxViews = Long.parseLong(maxViewsStr);
                resultList = trueTripleVideosDAO.findByViewsRange(minViews, maxViews);
            } else {
                // 查询所有数据
                resultList = trueTripleVideosDAO.findAll();
            }

            // 转换为 JSON 并返回
            String jsonData = objectMapper.writeValueAsString(resultList);
            response.getWriter().write(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"查询三连行为视频数据失败\"}");
        }
    }
}