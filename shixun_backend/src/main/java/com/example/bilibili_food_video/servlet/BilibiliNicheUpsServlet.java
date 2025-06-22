package com.example.bilibili_food_video.servlet;

import com.example.bilibili_food_video.dao.BilibiliNicheUpsDAO;
import com.example.bilibili_food_video.dao.BilibiliNicheUpsDAOImpl;
import com.example.bilibili_food_video.domain.BilibiliNicheUps;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 处理小众UP主分析数据请求的 Servlet
 */
@WebServlet("/api/niche-ups")
public class BilibiliNicheUpsServlet extends HttpServlet {
    private final BilibiliNicheUpsDAO nicheUpsDAO = new BilibiliNicheUpsDAOImpl();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try {
            // 获取查询参数
            String minInteractionRateParam = request.getParameter("minInteractionRate");
            String maxInteractionRateParam = request.getParameter("maxInteractionRate");
            String minVideoCountParam = request.getParameter("minVideoCount");
            String maxVideoCountParam = request.getParameter("maxVideoCount");

            List<BilibiliNicheUps> resultList;

            if (minInteractionRateParam != null && maxInteractionRateParam != null) {
                // 按互动率范围查询
                double minRate = Double.parseDouble(minInteractionRateParam);
                double maxRate = Double.parseDouble(maxInteractionRateParam);
                resultList = nicheUpsDAO.findByInteractionRateRange(minRate, maxRate);
            } else if (minVideoCountParam != null && maxVideoCountParam != null) {
                // 按视频数量查询
                int minCount = Integer.parseInt(minVideoCountParam);
                int maxCount = Integer.parseInt(maxVideoCountParam);
                resultList = nicheUpsDAO.findByVideoCount(minCount, maxCount);
            } else {
                // 查询所有数据
                resultList = nicheUpsDAO.findAll();
            }

            // 转换为 JSON 并返回
            String jsonData = objectMapper.writeValueAsString(resultList);
            response.getWriter().write(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"查询小众UP主数据失败\"}");
        }
    }
}