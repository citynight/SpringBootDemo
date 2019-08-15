# SpringBootDemo
学习SpringBoot 练习Demo


## 整合Servlet
1. 通过注解扫描完成Servlet组件注册
    1.1 编写Servlet
    ```
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
                super.doGet(req, resp);
            }
        }

    ```
    
    1.2 编写启动类
    ```
    @SpringBootApplication
    @ServletComponentScan // 在springboot启动时会扫描@WebServlet，并将该类实例化
    public class App {
        public static void main(String[] args) {
            SpringApplication.run(App.class,args);
        }
        
    }
    ```
1. 通过方法完成Servlet组件注册
    2.1 编写Servlet
    ```
    /*SpringBoot 整合Servlet方式二*/
public class SecondServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("SecondServlet -----");
    }
}
    ```
    2.2 编写启动类
    ```
    @SpringBootApplication
public class App2 {
    public static void main(String[] args) {
        SpringApplication.run(App2.class,args);
    }
    @Bean
    public ServletRegistrationBean getServletRegistrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new SecondServlet());
        bean.addUrlMappings("/second");
        return bean;
    }
}
    ```