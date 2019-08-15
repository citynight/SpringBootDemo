package cn.citynight.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*SpringBoot 整合Servlet方式一
* 原来xml中的配置信息
* <servlet>
*     <servlet-name>FirstServlet</servlet-name>
*     <servlet-class>cn.citynight.servlet.FirstServlet</servlet-class>
* </servlet>
*
* <servlet-mapping>
*     <servlet-name>
*         FirstServlet
*     </servlet-name>
*     <url-pattern>/first</url-pattern>
* </servlet-mapping>
* */

@WebServlet(name = "FirstServlet",urlPatterns = "/first")
public class FirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        System.out.println("First Servlet ......");
    }
}
