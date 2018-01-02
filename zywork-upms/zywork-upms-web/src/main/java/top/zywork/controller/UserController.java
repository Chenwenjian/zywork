package top.zywork.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zywork.service.UserService;
import top.zywork.vo.UserLoginVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @PostMapping("login")
    public String login(HttpSession session, UserLoginVO userLoginVO) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(userLoginVO.getAccount(), new Md5Hash(userLoginVO.getPassword()).toBase64()));
            // Session session = subject.getSession();
            session.setAttribute("user", "user");
        } catch (AuthenticationException e) {
            System.out.println("登录账号或密码有误");
        }
        return "ok";
    }

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
