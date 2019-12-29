<%--
  Created by IntelliJ IDEA.
  User: 朱宇瀚
  Date: 2019/12/24
  Time: 14:34
  查看新闻内容
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="dao.*" import="vo.*" %>

<%
    int user_id = Integer.parseInt(request.getParameter("user_id"));
    int article_id = Integer.parseInt(request.getParameter("article_id"));

    ArticleDao articleDao = new ArticleDao();
    UserDao userDao = new UserDao();

    User user = new UserDao().getOneById(user_id);
    Article article = new ArticleDao().getOneById(article_id);
/*
 * 1uE46C是小黑空格,
 * 8emsp；是中文的空格8nbsp；是英文的空格让前端显示换行和段薯开头空两行
 */
    article.setContent("&emsp;&emsp;"+article.getContent().replaceAll("\ue40c","<br>&emsp;&emsp;"));

    pageContext.setAttribute("user",user);
    pageContext.setAttribute("article",article);

    System.out.print("前台"+user.toString());
    System.out.print("前台"+article.toString());
%>
<html>
<head>
    <title>Title</title>

    <style>
        #table1 {
            width: 100%;
        }

        #table1 tr td {
            padding: 10px 10px;
        }

        #table1 tr td.td_index {
            width: 10px;
            padding: 20px 20px;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <h2>New Recommneder System</h2>
    <p>
        当前位置：<a href="index.jsp">网站首页</a> >> <a href="article_list.jsp">新闻列表</a></a> >> <a href="article_view.jsp">新闻内容</a>
    </p>
    <div id="content">
        <div id="main">
            <table id="table1">
                <caption>新闻内容</caption>
                <div class="td">
<%--                   文章信息存在小空格乱码--%>

                    ${article.content}
                </div>
            </table>
        </div>
        <div id="side">
            <div class="box">
                <form action="article_list.jsp" method="post">
                     <p><strong>当前用户：${pageScope.user.username}</strong></p>
                     <p>性别：${pageScope.user.gender}，出生年份：${pageScope.user.yearofbirth}</p>
                     <p>评分栏
                    <p></p>
                        一分<input id="oneScore" type="radio" name="1" />
                        两分<input id="twoScore" type="radio"  name="1"/>
                         三分<input id="threeScore" type="radio" checked="checked" name="1" />
                         四分<input id="fourScore" type="radio"  name="1"/>
                         五分<input id="fiveScore" type="radio" name="1" />
                        <input type="submit" value="提交">
                     </p>
                </form>
            </div>
        </div>
        <div class="clear"></div>
    </div>
</div>
<div>
<p>&nbsp;</p>
<p id="copyright">版权所有 &copy Justin 2019-2020</p>
<p>&nbsp;</p>
</div>
</body>
</html>
