package top.zywork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.zywork.dos.UserDO;
import top.zywork.service.UserService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private UserService userService;

    @RequestMapping("save")
    @ResponseBody
    public String save() {
        UserDO userDO = new UserDO();
        userDO.setPassword("test");
        userService.save(userDO);
        return "ok";
    }

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
