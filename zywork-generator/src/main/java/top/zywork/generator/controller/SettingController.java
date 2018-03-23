package top.zywork.generator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.zywork.generator.bean.Generator;
import top.zywork.generator.bean.JDBC;
import top.zywork.vo.ControllerStatusVO;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * 显示JDBC和Generator配置页面的控制器<br/>
 *
 * 创建于2018-03-22<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
@Controller
@RequestMapping("/setting")
public class SettingController {

    @GetMapping("jdbc-page")
    public String jdbcPage() {
        return "jdbc_setting";
    }

    @GetMapping("generator-page")
    public String generatorPage() {
        return "generator_setting";
    }

    @PostMapping("jdbc")
    @ResponseBody
    public ControllerStatusVO saveJdbc(JDBC jdbc, HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("jdbc", jdbc);
        servletContext.removeAttribute("tableColumnsList");
        ControllerStatusVO statusVO = new ControllerStatusVO();
        statusVO.okStatus(200, "已修改JDBC配置");
        return statusVO;
    }

    @PostMapping("generator")
    @ResponseBody
    public ControllerStatusVO saveGenerator(Generator generator, HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("generator", generator);
        ControllerStatusVO statusVO = new ControllerStatusVO();
        statusVO.okStatus(200, "已修改Generator配置");
        return statusVO;
    }

}
