<%--
  Created by IntelliJ IDEA.
  User: 朱宇瀚
  Date: 2019/12/24
  Time: 14:34
  随机显示 10 条新闻
  To change this template use File | Settings | File Templates.

--%>
<%--
用<!---->注释jsp代码只能在客户端进行注释
而用  进行注释的时候， 会在服务端也进行注释，
--%>
<%--
编辑xml文件时，
注释：CTRL + SHIFT + /
取消注释：CTRL + SHIFT + \
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="dao.*" import="vo.*" %>

<%
    ArticleDao dao = new ArticleDao();
////通过getlist新建一个含有十条新闻的对象list
    List<Article> list = dao.getList();
////!--把 list 放入 page 页面范围中
////    pageContext对象  这个对象代表页面上下文，该对象主要用于访问JSP之间的共享数据。
        pageContext.setAttribute("list", list);
//        ————————————————————————————————————————————————————————————————————

//    Parses the string argument as a signed decimal integer. [解析一个字符串参数为带符号的十进制整数]
    String name = request.getParameter("name");
    String yearofbirth = request.getParameter("birthofyear");
    String gender = request.getParameter("gender");
    int user_id;
    if (name != null ) {
        User user = new User();
        user.setUsername(name);
        user.setGender(gender);
        user.setYearofbirth(Integer.parseInt(yearofbirth));
        user.setId(0);
        pageContext.setAttribute("user", user);
    }
//        user.setGender();
    else {
        user_id = Integer.parseInt(request.getParameter("user_id"));
        // TODO: 2019/12/25  测试注册和登录逻辑
        // System.out.println(user_id);
        ////      user对象含有用户信息
        User user = new UserDao().getOneById(user_id);
        pageContext.setAttribute("user", user);
    }

//

////    System.out.println("qianduan"+user.toString());
////    把 user 放入 page 页面范围中

%>


<html>
<head>
    <title>article_view.jsp</title>
<%--    <style> 标签用于为 HTML 文档定义样式信息。在 style 中，您可以规定在浏览器中如何呈现 HTML 文档。--%>
    <style>
        #table1 {
            width: 100%;
        }

        #table1 tr td {
            padding: 10px 10px;
        }

        #table1 tr td.td_index {
            width: 10px;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <h1>News Recommender System</h1>
    <p>
        当前位置：<a href="index.jsp">网站首页</a> >> <a href="article_list.jsp">新闻列表</a>
    </p>
    <div id="content">
        <div id="main">
            <table id="table1">
                <caption>推荐新闻</caption>
<%--                <c:forEach>标签是更加通用的标签，因为它迭代一个集合中的对象。
                    items	要被循环的信息
                    var	代表当前条目的变量名称
                    varStatus	代表循环状态的变量名称
--%>
                <c:forEach var="article" items="${pageScope.list}" varStatus="status">
                <tr>
<%--                    <tr>之间为一行
                        <td>之间为一竖
--%>
                    <td class="td_index">${status.index+1}</td>
                    <td><a href="article_view.jsp?article_id=${article.id}&user_id=${user.id}">${article.title}</a></td>
                </tr>
                </c:forEach>
            </table>
        </div>
        <div id="side">
            <div class="box">
<%--                <p> 标签定义段落。--%>
                <p><strong>当前用户：${pageScope.user.username}</strong></p>
    <%

    %>
<%--                 ${}打印函数返回值--%>
                <p>性别：${pageScope.user.gender}，出生年份：${pageScope.user.yearofbirth}</p>
            </div>
        </div>
<%--                清除浮动
(1) 如果在标准流中一个盒子里面有多个标签，并且该盒子没有设置高度，那么盒子的高度就由盒子内的多个标签的内容高度撑起。
（2）如果一个盒子里面多个标签都被设为了浮动，那么浮动元素内容的高是不可以撑起盒子的高。

如果一个盒子里面多个标签都被设为了浮动，那么浮动元素内容的高是不可以撑起盒子的高。
在父盒子的里面的最后面增加一个div，然后设置其clear:both,这样可以清楚浮动并撑起高度

--%>
        <div class="clear"></div>
    </div>
<%--    <p>&nbsp;</p>为空格标记--%>
    <p>&nbsp;</p>
    <p id="copyright">版权所有 &copy Justin 2019-2020</p>
    <p>&nbsp;</p>
</div>
</body>
</html>
