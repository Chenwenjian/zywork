package top.zywork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 显示首页和主页的控制器<br/>
 *
 * 创建于2018-03-22<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("backend/index")
    public String index() {
        return "backend/index";
    }

    @GetMapping("backend/home")
    public String home() {
        return "backend/home";
    }

}
