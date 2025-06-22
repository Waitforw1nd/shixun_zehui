package com.example.bilibili_food_video.servlet; // 注意包名是 servlet

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// import com.alibaba.fastjson.JSON; // 如果需要更复杂的JSON序列化，可以取消注释并引入fastjson

@WebServlet("/testApi") // 使用注解方式映射URL
public class TestApiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应头，解决跨域问题
        response.setHeader("Access-Control-Allow-Origin", "*"); // 允许所有域，生产环境请替换为具体前端域名
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, X-Requested-With, accept, Origin, Access-Control-Request-Method, Access-Control-Request-Headers");
        response.setHeader("Access-Control-Max-Age", "3600"); // 预检请求的缓存时间

        // 对于预检请求（OPTIONS），直接返回200
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 硬编码的测试数据
        String testJson = "{\"message\": \"Hello from TestApiServlet!\", \"status\": \"success\", \"data\": {\"testKey\": \"testValue\", \"randomNumber\": " + Math.random() * 100 + "}}";

        response.getWriter().write(testJson);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // 简单起见，POST请求也由doGet处理
    }
}