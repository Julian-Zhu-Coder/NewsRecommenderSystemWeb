<%--
  Created by IntelliJ IDEA.
  User: 朱宇瀚
  Date: 2019/12/24
  Time: 14:34
  用户登录页面
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="dao.*" import="vo.*" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
    UserDao dao = new UserDao();
    List<User> user = dao.getList();
// 把 list 放入 page 页面范围中
//    pageContext.setAttribute("list", list);

//    response.setStatus(302); //设置响应行的状态码为302 重定向
//    response.setHeader("Location", "/Servlet/servlet2"); //设置响应头的属性 跳转到Servlet2
pageContext.setAttribute("user", user);
%>

<html>
<head>
    <title>Index</title>
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
        当前位置：<a href="index.jsp">网站首页</a>
    </p>
    <div id="content">
        <div id="main">
            <table id="table1">
                <caption>账号登录</caption>
                <%--                <c:forEach>标签是更加通用的标签，因为它迭代一个集合中的对象。
                                    items	要被循环的信息
                                    var	代表当前条目的变量名称
                                    varStatus	代表循环状态的变量名称
                --%>
                <c:forEach var="user" items="${pageScope.user}" varStatus="status">
                    <tr>
                            <%--                    <tr>之间为一行
                                                    <td>之间为一竖
                            --%>
                                <%--                 ${}打印函数返回值--%>
                        <td class="td_index">${status.index+1}</td>
                        <td><a href="article_list.jsp?user_id=${user.id}">${user.username}</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div id="side">
            <div class="box">
                <%--                <p> 标签定义段落。--%>
              <p><strong>当前用户</strong>：当前未选择任何用户</p>
              <p>性别：,年龄：</p>
            </div>
            <div class="box">
                <form action="article_list.jsp" method="post">
                    <tr>
                        <td>临时注册（开发中）</td>
                    </tr>
                    <tr>
                        <td>用户名：</td>
                        <td><input type="text" name="name"></td>
                    </tr>
                    <tr>
                        <td>出生年代：</td>
                        <td><input type="text" name="birthofyear"></td>
                    </tr>
                    <tr>
                        性别：<input id="man" type="radio" checked="checked" name="gender" value="1"/>男
                        <input id="woman" type="radio"  name="gender" value="1"/>女
                    </tr>
                    <tr>
                        <td><input type="submit" value="登录"></td>
                    </tr>
                </form>
            </div>
            <div class="box">
<%--                <%--%>
<%--                    SimpleDateFormat sdf = new SimpleDateFormat("YYYY年MM月dd日 HH:mm:ss");--%>
<%--                    Date d = new Date(session.getCreationTime());--%>
<%--                %>>--%>
<%--                你的进入网站时间：<%= sdf.format(d) %>--%>
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
</div>
</body>
</html>