package com.example.bilibili_food_video.servlet;

import com.example.bilibili_food_video.dao.BilibiliUpRankingDAO;
import com.example.bilibili_food_video.dao.BilibiliUpRankingDAOImpl;
import com.example.bilibili_food_video.domain.BilibiliUpRanking;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 处理 UP 主排行榜数据请求的 Servlet
 */
@WebServlet("/api/up-ranking")
public class BilibiliUpRankingServlet extends HttpServlet {
    private final BilibiliUpRankingDAO upRankingDAO = new BilibiliUpRankingDAOImpl();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try {
            // 处理查询参数（例如按 UP 主名称查询）
            String publisher = request.getParameter("publisher");
            if (publisher != null && !publisher.isEmpty()) {
                // 按单个 UP 主查询
                BilibiliUpRanking upRanking = upRankingDAO.findByPublisher(publisher);
                String jsonData = objectMapper.writeValueAsString(upRanking);
                response.getWriter().write(jsonData);
            } else {
                // 查询所有 UP 主数据
                List<BilibiliUpRanking> upRankingList = upRankingDAO.findAll();
                String jsonData = objectMapper.writeValueAsString(upRankingList);
                response.getWriter().write(jsonData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"查询 UP 主排行榜数据失败\"}");
        }
    }
}