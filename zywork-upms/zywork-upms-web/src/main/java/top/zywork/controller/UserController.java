package top.zywork.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zywork.common.AuthUtils;
import top.zywork.common.WebUtils;
import top.zywork.dto.UserTokenDTO;
import top.zywork.enums.UserControllerStatusEnum;
import top.zywork.security.shiro.CustomToken;
import top.zywork.service.UserService;
import top.zywork.vo.LoginStatusVO;
import top.zywork.vo.UserLoginVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户控制器类<br/>
 *
 * 创建于2018-01-19
 *
 * @author 王振宇
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    /**
     * 用户使用账号及密码进行登录操作
     * @param userLoginVO 包含有用户名和密码的VO对象
     * @return
     */
    @PostMapping("login")
    public LoginStatusVO login(UserLoginVO userLoginVO) {
        LoginStatusVO statusVO = new LoginStatusVO();
        Subject subject = SecurityUtils.getSubject();
        String username = userLoginVO.getAccount();
        String hashPassword = userLoginVO.getPassword();
        try {
            // 使用账号和密码进行登录，此时并未生成token，不需要使用token进行用户认证
            subject.login(new CustomToken(username, hashPassword, null,null));
        } catch (AuthenticationException e) {
            statusVO.dataErrorStatus(UserControllerStatusEnum.USER_LOGIN_DATA_ERROR.getCode(),
                    UserControllerStatusEnum.USER_LOGIN_DATA_ERROR.getMessage());
            return statusVO;
        }
        statusVO.okStatus(UserControllerStatusEnum.USER_LOGIN_SUCCESS.getCode(),
                UserControllerStatusEnum.USER_LOGIN_SUCCESS.getMessage());
        // 登录成功后，把会话id和生成的用户token返回到客户端，以便客户端保留用于身份认证，并把生成的token缓存到redis中
        Long timestamp = System.currentTimeMillis();
        statusVO.setToken(AuthUtils.generateToken(username, timestamp, hashPassword));
        statusVO.setSessionId(subject.getSession().getId().toString());
        userService.saveUserToken(new UserTokenDTO(username, timestamp, statusVO.getToken()));
        return statusVO;
    }

    /**
     * 检查用户认证
     * @param request HttpServletRequest对象
     * @param username 由客户端传来的用户名
     * @param userToken 由客户端传来的用户Token
     * @return
     */
    @PostMapping("check_login")
    public LoginStatusVO isLogin(HttpServletRequest request, String username, String userToken) {
        LoginStatusVO statusVO = new LoginStatusVO();
        // 从redis缓存中获取UserTokenDTO
        UserTokenDTO userTokenDTO = userService.getUserToken(username);
        if (userTokenDTO != null && userToken.equals(userTokenDTO.getToken())) {
            Subject subject = SecurityUtils.getSubject();
            String theSessionId = subject.getSession().getId().toString();
            if (theSessionId.equals(WebUtils.getSessionIdFromCookie(request))) {
                // 如果sessionid一致，表示会话正常
                statusVO.okStatus(UserControllerStatusEnum.USER_CHECK_LOGIN_SUCCESS.getCode(),
                        UserControllerStatusEnum.USER_CHECK_LOGIN_SUCCESS.getMessage());
            } else {
                // 如果sessionid不一致，表示为新会话，需要重新进行登录验证
                // 此时不需要用户再次输入用户名和密码，而是直接使用token进行用户身份认证
                try {
                    subject.login(new CustomToken(username, userToken, userTokenDTO.getTimestamp(), userToken));
                } catch (AuthenticationException e) {
                    statusVO.dataErrorStatus(UserControllerStatusEnum.USER_LOGIN_DATA_ERROR.getCode(),
                            UserControllerStatusEnum.USER_LOGIN_DATA_ERROR.getMessage());
                    return statusVO;
                }
                // 告诉客户端更新session id。有些客户端，如手机app需要保存会话id
                statusVO.okStatus(UserControllerStatusEnum.USER_SESSION_UPDATED.getCode(),
                        UserControllerStatusEnum.USER_SESSION_UPDATED.getMessage());
                statusVO.setSessionId(theSessionId);
            }
        } else {
            // 如果redis缓存中没有用户Token，则用户认证失败
            statusVO.errorStatus(UserControllerStatusEnum.USER_CHECK_LOGIN_ERROR.getCode(),
                    UserControllerStatusEnum.USER_CHECK_LOGIN_ERROR.getMessage());
        }
        return statusVO;
    }

    @PostMapping("logout")
    public LoginStatusVO logout(String username, String userToken) {
        LoginStatusVO statusVO = new LoginStatusVO();
        UserTokenDTO userTokenDTO = userService.getUserToken(username);
        if (userTokenDTO != null && userToken.equals(userTokenDTO.getToken())) {
            userService.removeUserToken(username);
            statusVO.okStatus(UserControllerStatusEnum.USER_LOGOUT_SUCCESS.getCode(),
                    UserControllerStatusEnum.USER_LOGOUT_SUCCESS.getMessage());
        } else {
            statusVO.errorStatus(UserControllerStatusEnum.USER_LOGOUT_ERROR.getCode(),
                    UserControllerStatusEnum.USER_LOGOUT_ERROR.getMessage());
        }
        return statusVO;
    }

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
