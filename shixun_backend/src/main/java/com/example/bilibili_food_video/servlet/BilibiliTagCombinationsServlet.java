package com.example.bilibili_food_video.servlet;

import com.example.bilibili_food_video.dao.BilibiliTagCombinationsDAO;
import com.example.bilibili_food_video.dao.BilibiliTagCombinationsDAOImpl;
import com.example.bilibili_food_video.domain.BilibiliTagCombinations;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 处理标签组合数据相关 HTTP 请求的 Servlet
 */
@WebServlet("/api/tag-combinations")
public class BilibiliTagCombinationsServlet extends HttpServlet {

    private final BilibiliTagCombinationsDAO tagCombinationsDAO = new BilibiliTagCombinationsDAOImpl();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型为 JSON，编码为 UTF-8
        response.setContentType("application/json;charset=UTF-8");
        try {
            // 调用 DAO 查询数据
            List<BilibiliTagCombinations> tagCombinationsList = tagCombinationsDAO.findAll();
            // 将数据转换为 JSON 格式并响应给前端
            String jsonData = objectMapper.writeValueAsString(tagCombinationsList);
            response.getWriter().write(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            // 发生异常时，设置响应状态码为 500 并返回错误信息
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"查询标签组合数据失败，请稍后重试\"}");
        }
    }
}