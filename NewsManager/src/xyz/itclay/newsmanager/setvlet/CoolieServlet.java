package xyz.itclay.newsmanager.setvlet;

import xyz.itclay.newsmanager.dao.NewsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录页面记住状态
 *
 * @author ZhangSenmiao
 * @date 2021/1/25 16:57
 **/
@WebServlet("/cookieDemo")
public class CoolieServlet extends HttpServlet {
    private NewsDao newsDao;

    @Override
    public void init() throws ServletException {
        newsDao = new NewsDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean res = newsDao.isUser(username, password);
        if (res) {
            Cookie cookie = new Cookie("username", username);
            Cookie password1 = new Cookie("password", password);
            String save = req.getParameter("save");
            if (save != null) {
                //设置存活时间
                cookie.setMaxAge(7 * 24 * 60 * 60);
                password1.setMaxAge(7 * 24 * 60 * 60);
            } else {
                cookie.setMaxAge(0);
                password1.setMaxAge(0);
            }
            //设置存储路径
            cookie.setPath("/");
            password1.setPath("/");
            resp.addCookie(cookie);
            resp.addCookie(password1);
            resp.sendRedirect("index.jsp");
        } else {
            resp.sendRedirect("login.jsp");
        }
    }
}
