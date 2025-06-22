package com.example.bilibili_food_video.servlet;

import com.example.bilibili_food_video.dao.BilibiliMonthlyPlayTrendDAO;
import com.example.bilibili_food_video.dao.BilibiliMonthlyPlayTrendDAOImpl;
import com.example.bilibili_food_video.domain.BilibiliMonthlyPlayTrend;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 处理月度播放量趋势数据请求的 Servlet
 */
@WebServlet("/api/monthly-play-trend")
public class BilibiliMonthlyPlayTrendServlet extends HttpServlet {
    private final BilibiliMonthlyPlayTrendDAO monthlyPlayTrendDAO = new BilibiliMonthlyPlayTrendDAOImpl();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try {
            // 获取查询参数
            String yearParam = request.getParameter("year");
            String holidayParam = request.getParameter("holiday");

            List<BilibiliMonthlyPlayTrend> resultList;

            if (yearParam != null && !yearParam.isEmpty()) {
                // 按年份查询
                int year = Integer.parseInt(yearParam);
                resultList = monthlyPlayTrendDAO.findByYear(year);
            } else if (holidayParam != null && holidayParam.equalsIgnoreCase("true")) {
                // 查询节假日月份
                resultList = monthlyPlayTrendDAO.findHolidayMonths();
            } else {
                // 查询所有数据
                resultList = monthlyPlayTrendDAO.findAll();
            }

            // 转换为 JSON 并返回
            String jsonData = objectMapper.writeValueAsString(resultList);
            response.getWriter().write(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"查询月度播放量趋势数据失败\"}");
        }
    }
}