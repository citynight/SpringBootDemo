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


## 整合Listener

1. 通过注解扫描完成Listener组件注册

    1. 编写listener

        ```
        /*
            *
            * <listener>
            *     <listener-class>
            *         cn.citynight.listener.FirstLinstener
            *     </listener-class>
            * </listener>
            *
            * */
            @WebListener
            public class FirstListener implements ServletContextListener {
                @Override
                public void contextInitialized(ServletContextEvent sce) {
                    System.out.println("Listener.....init....");
                }
            }
        
        ```
    2. 编写启动器

        ```
        @SpringBootApplication
        @ServletComponentScan
        public class App {
        
            public static void main(String[] args) {
                SpringApplication.run(App.class,args);
            }
        }
        ```
    
1. 通过方法完成Listener组件注册

    1. 编写listener

        ```
        public class SecondListener implements ServletContextListener {
            @Override
            public void contextInitialized(ServletContextEvent sce) {
                System.out.println("SecondListener init----------");
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
        
            @Bean
            public ServletListenerRegistrationBean<SecondListener> getServletListenerRegistrationBean() {
                ServletListenerRegistrationBean<SecondListener> bean = new ServletListenerRegistrationBean(new SecondListener());
                return bean;
            }
        }

        ```


## 访问静态资源

1. SpringBoot从`classpath/static` 的目录
    注意目录名称必须是`static`
    
    ![](https://github.com/lxzzzzzz/SpringBootDemo/blob/master/WX20190816-144158@2x.png)
    
1. ServletContent 根目录下
 在`src/main/webapp`目录名称也必须要叫做`webapp`


## 文件上传
1. 编写上传页面
    
    ```
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>文件上传</title>
    </head>
    <body>
        <form action="fileUploadController" method="post" enctype="multipart/form-data">
            上传文件： <input type="file" name="filename"/>
            <br/>
            <input type="submit"/>
        </form>
    </body>
    </html>
    ```
    
    1. 编写Controller
    
    ```
    /*
    *
    * SpringBoot 文件上传
    *
    * */
    //@Controller
    @RestController // 表示该类下的方法的返回值会自动做json格式的转换
    public class FileUploadController {
        /*
        * filename的名字要和HTML中的filename一致
        * RequestMapping中的path跟HTML中的action一致
        * */
        @RequestMapping("/fileUploadController")
        public Map<String,Object> fileUpload(MultipartFile filename) throws IOException {
            System.out.println(filename.getOriginalFilename());
            File file = new File("/Users/lixiaozheng/Desktop/UploadData/" + filename.getOriginalFilename());
            filename.transferTo(file);
            Map<String,Object> map = new HashMap<>();
            map.put("msg","ok");
            return map;
    
        }
    }
    ```
    
    1. 设置上传文件大小的默认值
        1. 需要添加一个SpringBoot的配置文件，配置文件名称为`application.properties`, 配置文件必须要放在`src/main/resources` 下
        文件的中的配置项如下：
        ```
        #上传文件的大小设置为最大200MB
        spring.servlet.multipart.max-file-size=200MB
        #上传文件的总大小
        spring.servlet.multipart.max-request-size=200MB
        ```