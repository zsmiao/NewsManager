<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/1/24
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加新闻</title>
    <script type="text/javascript" src="kindeditor-4.1.10/kindeditor.js"></script>
    <script type="text/javascript">
        KindEditor.ready(function (K) {
            K.create('#content', {
                uploadJson: 'kindeditor-4.1.10/jsp/upload_json.jsp',
                fileManagerJson: 'kindeditor-4.1.10/jsp/file_manager_json.jsp',
                allowFileManager: true
            });
        });
    </script>
</head>
<body>
<form action="NewsServlet" method="post" autocomplete="off">
    <input type="hidden" value="addNews" name="oprate">
    <input type="text" name="newstitle" placeholder="请输入新闻标题"/><br>
    新闻栏目
    <select name="newstype">
        <option value="html">html</option>
        <option value="html">java</option>
        <option value="html">javascript</option>
    </select>
    <br/>
    <textarea id="content" name="newscontent" rows="8" cols="70"></textarea>
    <input type="submit" value="添加"/>
</form>
</body>
</html>