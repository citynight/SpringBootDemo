package cn.citynight.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

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
