package com.example.bilibili_food_video.servlet;

import com.example.bilibili_food_video.dao.BilibiliTop100WordCloudDAO;
import com.example.bilibili_food_video.dao.BilibiliTop100WordCloudDAOImpl;
import com.example.bilibili_food_video.domain.BilibiliTop100WordCloud;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/top100-word-cloud")
public class BilibiliTop100WordCloudServlet extends HttpServlet {
    private final BilibiliTop100WordCloudDAO dao = new BilibiliTop100WordCloudDAOImpl();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 设置响应格式
        resp.setContentType("application/json;charset=utf-8");

        try {
            // 2. 调用DAO获取数据
            List<BilibiliTop100WordCloud> wordCloudList = dao.findAll();

            // 3. 将数据转换为JSON并返回
            String jsonResult = objectMapper.writeValueAsString(wordCloudList);
            resp.getWriter().write(jsonResult);
        } catch (Exception e) {
            e.printStackTrace();
            // 返回错误信息
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"查询失败\"}");
        }
    }
}