package top.zywork.generator.listener;

import com.alibaba.fastjson.JSON;
import top.zywork.common.FileUtils;
import top.zywork.generator.bean.Generator;
import top.zywork.generator.bean.JDBC;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.IOException;

@WebListener
public class GeneratorLoadListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            JDBC jdbc = JSON.parseObject(org.apache.commons.io.FileUtils.readFileToString(
                    new File(FileUtils.getResourcePath("classpath:/config/jdbc.json")), "utf-8"), JDBC.class);
            Generator generator = JSON.parseObject(org.apache.commons.io.FileUtils.readFileToString(
                    new File(FileUtils.getResourcePath("classpath:/config/generator.json")), "utf-8"), Generator.class);
            ServletContext servletContext = servletContextEvent.getServletContext();
            servletContext.setAttribute("jdbc", jdbc);
            servletContext.setAttribute("generator", generator);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
