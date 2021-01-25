package xyz.itclay.newsmanager.setvlet;

import xyz.itclay.newsmanager.dao.NewsDao;
import xyz.itclay.newsmanager.domain.News;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author ZhangSenmiao
 * @date 2021/1/23 20:14
 **/
public class NewsServlet extends HttpServlet {
    private NewsDao newsDao;

    @Override
    public void init() throws ServletException {
        newsDao = new NewsDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String oprate = req.getParameter("oprate");
        //如果该次请求是addNews,则是添加新闻的请求
        if ("addNews".equals(oprate)) {
            String newstitle = req.getParameter("newstitle");
            String newstype = req.getParameter("newstype");
            String newscontent = req.getParameter("newscontent");

//            int row = newsDao.addNews(newstitle, newstype, newscontent);
            resp.sendRedirect("NewsServlet");
        } else if ("delete".equals(oprate)) {
            //如果请求是delete，则是删除新闻的请求
            Integer id = Integer.valueOf(req.getParameter("id"));
            int row = newsDao.deleteNewsById(id);
            resp.sendRedirect("NewsServlet");
        } else if ("batchDeletion".equals(oprate)) {
            //如果接收到的参数是batchDeletion，则是批量删除的请求
            String[] deleteIds = req.getParameterValues("deleteId");
            for (String deleteId : deleteIds) {
                Integer integer = Integer.valueOf(deleteId);
                newsDao.deleteNewsById(integer);
            }
            resp.sendRedirect("NewsServlet");
        }else if ("batchReview".equals(oprate)){
            String[] deleteIds = req.getParameterValues("deleteId");
            for (String deleteId : deleteIds) {
                Integer integer = Integer.valueOf(deleteId);
                int row=newsDao.batchReview(integer);
            }
            resp.sendRedirect("NewsServlet");
        }else if ("toupdate".equals(oprate)){
            Integer id = Integer.valueOf(req.getParameter("id"));
            News news=newsDao.getNews(id);
            req.setAttribute("news",news);
            req.getRequestDispatcher("newsUpdate.jsp").forward(req,resp);

        }else if ("updatenews".equals(oprate)){
            Integer id = Integer.valueOf(req.getParameter("id"));
            String newsTitle = req.getParameter("newstitle");
            String newsType = req.getParameter("newstype");
            String newsContent = req.getParameter("newscontent");
            int row = newsDao.updateNews(id, newsTitle, newsType, newsContent);
            resp.sendRedirect("NewsServlet");
        }
        else {
            Integer pageNumber = 1;
            //控制分页效果
            String pageNumber1 = req.getParameter("pageNumber");
            if (pageNumber1 != null) {
                pageNumber = Integer.valueOf(pageNumber1);
            }
            if (pageNumber <= 1) {
                pageNumber = 1;
            }
            //新闻总条数
            int count = newsDao.getCount();
            Integer pageSize = 10;
            int pageCount = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
            if (pageNumber > pageCount) {
                pageNumber = pageCount;
            }
            //调用查询集合的方法
            List<News> newsList = newsDao.getNewsList(pageNumber, pageSize);
            //添加到request作用域中
            req.setAttribute("newsList", newsList);
            //转发到newList.jsp界面
            req.setAttribute("pageNumber", pageNumber);
            req.setAttribute("pageCount", pageCount);
  /*      for (News news : newsList) {
            System.out.println(news.toString());
        }*/
            req.getRequestDispatcher("newList.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
