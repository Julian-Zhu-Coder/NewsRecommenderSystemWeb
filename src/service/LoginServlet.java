package service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");  //设置request的编码格式,防止乱码
        response.setContentType("type/html;charset=utf-8"); //设置相应内容类型
        String user_id = request.getParameter("user_id");
        response.sendRedirect("/article_list.jsp");
        System.out.println(user_id);
    }
    protected void doPost(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException,IOException {
        doGet(request,response);
    }
}
