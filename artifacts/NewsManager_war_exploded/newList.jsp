<%--
  Created by IntelliJ IDEA.
  User: ZhangSenmiao
  Date: 2021/1/23
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%--设置页面信息--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入标签库--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>新闻列表</title>
    <script type="text/javascript">
        function selectAll(all) {
            var deleteId = document.getElementsByName("deleteId");
            for (var i = 0; i <= deleteId.length; i++) {
                deleteId[i].checked = all.checked;
            }
        }
        function changeForm(){
            document.getElementById("oprate").value="batchReview";
            document.forms[0].submit();
        }
    </script>
</head>
<body>
<a href="addNews.jsp"><button>添加</button></a>
<form action="#" method="post" style="text-align: center">

    <input type="submit" value="批量删除"/>
    <input type="button" value="批量审核" onclick="changeForm()"/>
    <input type="hidden" id="oprate" name="oprate" value="batchDeletion">
    <table width="1000px" cellpadding="0px" cellspacing="0px" border="1px"
           style="border: 1px solid gray;border-collapse: collapse;text-align: center;margin: 0 auto">
        <tr>
            <th><input type="checkbox" name="all" onclick="selectAll(this)"/></th>
            <th>文章ID</th>
            <th>文章标题</th>
            <th>所属栏目</th>
            <th>创建时间</th>
            <th>是否审核</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${newsList}" var="news">
            <tr>
                <td><input type="checkbox" name="deleteId" value="${news.newsId}" /></td>
                <td>${news.newsId}</td>
                <td>${news.newsTitle}</td>
                <td>${news.newsType}</td>
                <td>${news.createTime}</td>
                <td>${news.newsStatus}</td>
                <td>
                    <a href="NewsServlet?oprate=toupdate&id=${news.newsId}" > 编辑</a>
                        |
                    <a href="NewsServlet?oprate=delete&id=${news.newsId}">删除</a></td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="7">
                <a href="NewsServlet?pageNumber=1">首页</a>
                <a href="NewsServlet?pageNumber=${pageNumber-1}">上一页</a>
                <a href="NewsServlet?pageNumber=${pageNumber+1}">下一页</a>
                <a href="NewsServlet?pageNumber=${pageCount}">尾页</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
