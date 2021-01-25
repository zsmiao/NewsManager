package xyz.itclay.newsmanager.setvlet;

import xyz.itclay.newsmanager.dao.NewsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author ZhangSenmiao
 * @date   2021/1/25 23:58
 **/
@WebServlet("/addNews")
public class NewsServlet1 extends BaseServlet {
    private NewsDao newsDao;
    @Override
    public void init() throws ServletException {
        newsDao = new NewsDao();
    }

    protected void addNews(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
//        String oprate = req.getParameter("oprate");
        //如果该次请求是addNews,则是添加新闻的请求
//        if ("addNews".equals(oprate)) {
            String newstitle = req.getParameter("newstitle");
            String newstype = req.getParameter("newstype");
            String newscontent = req.getParameter("newscontent");

            int row = newsDao.addNews(newstitle, newstype, newscontent);
            resp.sendRedirect("NewsServlet");
//        }
    }
}