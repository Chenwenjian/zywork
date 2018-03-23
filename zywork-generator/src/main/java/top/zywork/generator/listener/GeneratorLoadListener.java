package top.zywork.generator.listener;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.zywork.common.FileUtils;
import top.zywork.generator.bean.Generator;
import top.zywork.generator.bean.JDBC;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.IOException;

/**
 * 自动代码生成监听器<br/>
 * 当启动项目时，需要去读取JDBC和Generator的默认配置信息，并显示到页面中<br/>
 *
 * 创建于2018-03-22<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
@WebListener
public class GeneratorLoadListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(GeneratorLoadListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("zywork-generator启动，开始读取JDBC和Generator默认配置……");
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
        logger.info("zywork-generator关闭……");
    }
}
