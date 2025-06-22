package com.example.bilibili_food_video.servlet;

import com.example.bilibili_food_video.dao.BilibiliInteractionRatioDAO;
import com.example.bilibili_food_video.dao.BilibiliInteractionRatioDAOImpl;
import com.example.bilibili_food_video.domain.BilibiliInteractionRatio;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 处理视频互动比例数据请求的 Servlet
 */
@WebServlet("/api/interaction-ratio")
public class BilibiliInteractionRatioServlet extends HttpServlet {
    private final BilibiliInteractionRatioDAO interactionRatioDAO = new BilibiliInteractionRatioDAOImpl();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try {
            // 获取查询参数
            String title = request.getParameter("title");
            String tripleBehavior = request.getParameter("tripleBehavior");

            List<BilibiliInteractionRatio> resultList;

            if (title != null && !title.isEmpty()) {
                // 按标题模糊查询
                resultList = interactionRatioDAO.findByTitle(title);
            } else if (tripleBehavior != null && !tripleBehavior.isEmpty()) {
                // 按三连行为查询
                resultList = interactionRatioDAO.findByTripleBehavior(tripleBehavior);
            } else {
                // 查询所有数据
                resultList = interactionRatioDAO.findAll();
            }

            // 转换为 JSON 并返回
            String jsonData = objectMapper.writeValueAsString(resultList);
            response.getWriter().write(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"查询互动比例数据失败\"}");
        }
    }
}