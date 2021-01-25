package xyz.itclay.newsmanager.dao;

import xyz.itclay.newsmanager.domain.News;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangSenmiao
 * @date 2021/1/23 19:54
 **/
public class NewsDao {
    /**
     * 查询新闻集合，返回新闻集合
     *
     * @author ZhangSenmiao
     * @date 2021/1/23 20:08
     **/
    public List<News> getNewsList(Integer pageNumber, Integer pageSize) {
        List<News> newsList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;


        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //驱动管理器获取数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://www.itclay.xyz:3306/StudentManager", "zsmiao", "zsmiao2020@MySQL");
//            System.out.println("数据库连接成功");

            statement = connection.createStatement();
            int startSize = (pageNumber - 1) * pageSize;
            resultSet = statement.executeQuery("select * from news limit " + startSize + "," + pageSize);


            while (resultSet.next()) {
                int newsId = resultSet.getInt("newsId");
                String newTitle = resultSet.getString("newstitle");
                String newsContent = resultSet.getString("newsContent");
                String newsStatus = resultSet.getString("newsStatus");
                String newsType = resultSet.getString("newsType");
                Date createTime = resultSet.getDate("createTime");
                News news = new News(newsId, newTitle, newsContent, newsStatus, newsType, createTime);
                newsList.add(news);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return newsList;
    }

    /**
     * 查询新闻总条数
     *
     * @author ZhangSenmiao
     * @date 2021/1/23 21:35
     **/
    public int getCount() {
        int count = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //驱动管理器获取数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://www.itclay.xyz:3306/StudentManager", "zsmiao", "zsmiao2020@MySQL");
//            System.out.println("数据库连接成功");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select count(*) from news;");
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return count;
    }


    /**
     * 添加新闻的方法
     *
     * @return 数据库受影响的行数
     * @author ZhangSenmiao
     * @date 2021/1/25 10:11
     **/
    public int addNews(String newstitle, String newstype, String newscontent) {
        int row = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //驱动管理器获取数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://www.itclay.xyz:3306/StudentManager", "zsmiao", "zsmiao2020@MySQL");
            String sql = "insert into news(newstitle,newsContent,newsStatus,newsType,createtime) values(?,?,?,?,?);";
            statement = connection.prepareStatement(sql);
            statement.setString(1, newstitle);
            statement.setString(2, newscontent);
            statement.setString(3, "未审核");
            statement.setString(4, newstype);
            statement.setDate(5, new java.sql.Date(2021, 01, 25));
            row = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return row;
    }

    /**
     * 删除新闻的方法
     *
     * @return 数据库受影响的行数
     * @author ZhangSenmiao
     * @date 2021/1/25 11:48
     **/
    public int deleteNewsById(Integer id) {
        int row = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //驱动管理器获取数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://www.itclay.xyz:3306/StudentManager", "zsmiao", "zsmiao2020@MySQL");
            String sql = "delete from news where newsid=?";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, id);
            row = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return row;
    }

    /**
     * 批量审核文章
     *
     * @return 数据库受影响的行数
     * @author ZhangSenmiao
     * @date 2021/1/25 13:28
     **/
    public int batchReview(Integer id) {
        int row = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //驱动管理器获取数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://www.itclay.xyz:3306/StudentManager", "zsmiao", "zsmiao2020@MySQL");
            String sql = "update news set newsStatus='已审核' where newsid=?";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, id);
            row = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return row;
    }
}
