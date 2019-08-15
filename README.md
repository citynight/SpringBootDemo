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
2. 通过方法完成Servlet组件注册

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
    
    
## 整合Filter
1. 通过注解扫描完成Filter组件的注册
    1. 编写Filter
        ```
            import java.io.IOException;
            import javax.servlet.*;
            import javax.servlet.annotation.WebFilter;
            
            /*SpringBoot整合Filter方式一
            *
            * <filter>
            *     <filter-name>FirstFilter</filter-name>
            *     <filter-class>cn.citynight.filter</filter-class>
            * </filter>
            *
            * <filter-mapping>
            *     <filter-name>FirstFilter</filter-name>
            *     <url-pattern>/hello</url-pattern>
            * </filter-mapping>
            * */
            //@WebFilter(filterName = "FirstFilter",urlPatterns = {"*.do","*.jsp"})
            @WebFilter(filterName = "FirstFilter",urlPatterns = "/first")
            public class FirstFilter implements Filter {
                @Override
                public void init(FilterConfig filterConfig) throws ServletException {
            
                }
            
                @Override
                public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                    System.out.println("进入filter");
                    filterChain.doFilter(servletRequest,servletResponse);
                    System.out.println("离开filter");
                }
            
                @Override
                public void destroy() {
    
                    }
                }
        ```
        
    2. 编写启动类
        ```
        @SpringBootApplication
        @ServletComponentScan
        public class App {
            
            public static void main(String[] args) {
                SpringApplication.run(App.class,args);
            }
        }
        ```
2. 通过方法完成Filter组件的注册
    1. 编写filter
        ```
            public class SecondFilter implements Filter {
                @Override
                public void init(FilterConfig filterConfig) throws ServletException {
            
                }
            
                @Override
                public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                    System.out.println("进入2222filter");
                    filterChain.doFilter(servletRequest,servletResponse);
                    System.out.println("离开2222filter");
                }
            
                @Override
                public void destroy() {
            
                }
            }
        ```
    
    2. 编写启动类
        
        ```
            @SpringBootApplication
            public class App2 {
                public static void main(String[] args) {
                    SpringApplication.run(App2.class,args);
                }
            
                /*注册Servlet*/
                @Bean
                public ServletRegistrationBean getServletRegistrationBean() {
                    ServletRegistrationBean bean = new ServletRegistrationBean(new SecondServlet());
                    bean.addUrlMappings("/second");
                    return  bean;
                }
            
                @Bean
                public FilterRegistrationBean getFilterRegistrationBean() {
                    FilterRegistrationBean bean = new FilterRegistrationBean(new SecondFilter());
            //        bean.addUrlPatterns(new String[]{"*.do","%.jsp"});
                    bean.addUrlPatterns("/second");
                    return bean;
                }
            }

        ```
