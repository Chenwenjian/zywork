package top.zywork.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.zywork.common.*;
import top.zywork.dto.PagerDTO;
import top.zywork.dto.UserDTO;
import top.zywork.dto.UserTokenDTO;
import top.zywork.enums.UserControllerStatusEnum;
import top.zywork.exception.ServiceException;
import top.zywork.query.PageQuery;
import top.zywork.query.StatusQueries;
import top.zywork.query.StatusQuery;
import top.zywork.query.UserQuery;
import top.zywork.security.shiro.CustomToken;
import top.zywork.service.UserService;
import top.zywork.vo.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * UserController控制器类<br/>
 *
 * 创建于2018-05-02<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @GetMapping("page")
    public String page() {
        return "User/User";
    }

    @GetMapping("add-modal")
    public String addModal() {
        return "User/UserAddModal";
    }

    @GetMapping("edit-modal")
    public String editModal() {
        return "User/UserEditModal";
    }

    @GetMapping("detail-modal")
    public String detailModal() {
        return "User/UserDetailModal";
    }

    @GetMapping("search-modal")
    public String searchModal() {
        return "User/UserSearchModal";
    }

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(@Validated UserVO userVO, BindingResult bindingResult) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        if (bindingResult.hasErrors()) {
            statusVO.dataErrorStatus(500, BindingResultUtils.errorString(bindingResult));
        } else {
            try {
                userService.save(getBeanMapper().map(userVO, UserDTO.class));
                statusVO.okStatus(200, "添加成功");
            } catch (ServiceException e) {
                logger.error("添加失败：{}", e.getMessage());
                statusVO.errorStatus(500, "添加失败");
            }
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(UserVO userVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            userService.remove(getBeanMapper().map(userVO, UserDTO.class));
            statusVO.okStatus(200, "删除成功");
        } catch (ServiceException e) {
            logger.error("删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "删除失败");
        }
        return statusVO;
    }

    @GetMapping("remove/{id}")
    @ResponseBody
    public ControllerStatusVO removeById(@PathVariable("id") Long id) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            userService.removeById(id);
            statusVO.okStatus(200, "删除成功");
        } catch (ServiceException e) {
            logger.error("删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "删除失败");
        }
        return statusVO;
    }

    @PostMapping("batch-remove")
    @ResponseBody
    public ControllerStatusVO removeByIds(String ids) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            userService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(200, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(@Validated UserVO userVO, BindingResult bindingResult) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        if (bindingResult.hasErrors()) {
            statusVO.dataErrorStatus(500, BindingResultUtils.errorString(bindingResult));
        } else {
            try {
                userService.update(getBeanMapper().map(userVO, UserDTO.class));
                statusVO.okStatus(200, "更新成功");
            } catch (ServiceException e) {
                logger.error("更新失败：{}", e.getMessage());
                statusVO.errorStatus(500, "更新失败");
            }
        }
        return statusVO;
    }

    @PostMapping("active")
    @ResponseBody
    public ControllerStatusVO updateActiveStatus(StatusQuery statusQuery) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            userService.updateActiveStatus(statusQuery);
            statusVO.okStatus(200, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

    @PostMapping("batch-active")
    @ResponseBody
    public ControllerStatusVO updateActiveStatuses(String ids, StatusQueries statusQueries) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        statusQueries.setIds(StringUtils.strToLongArray(ids, ","));
        try {
            userService.updateActiveStatuses(statusQueries);
            statusVO.okStatus(200, statusQueries.getStatus() == 0 ? "批量激活成功" : "批量冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQueries.getStatus() == 0 ? "批量激活失败" : "批量冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public UserVO getById(@PathVariable("id") Long id) {
        UserVO userVO = new UserVO();
        try {
            Object obj = userService.getById(id);
            if (obj != null) {
                userVO = getBeanMapper().map(obj, UserVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return userVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<UserVO> listAll() {
        List<UserVO> userVOList = new ArrayList<>();
        try {
            List<Object> objectList = userService.listAll();
            userVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, UserVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return userVOList;
    }

    @RequestMapping("pager")
    @ResponseBody
    public PagerVO listPage(int offset, int limit, String sort, String order) {
        PagerVO pagerVO = new PagerVO();
        PageQuery pageQuery = new PageQuery(offset / limit + 1, limit, sort, order);
        try {
            PagerDTO pagerDTO = userService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), UserVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @RequestMapping("pager-cond")
    @ResponseBody
    public PagerVO listPageByCondition(int offset, int limit, String sort, String order, UserQuery userQuery) {
        PagerVO pagerVO = new PagerVO();
        PageQuery pageQuery = new PageQuery(offset / limit + 1, limit, sort, order);
        try {
            PagerDTO pagerDTO = userService.listPageByCondition(pageQuery, userQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), UserVO.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    /**
     * 用户使用账号及密码进行登录操作
     * @param userLoginVO 包含有用户名和密码的VO对象
     * @return
     */
    @PostMapping("login")
    @ResponseBody
    public LoginStatusVO login(UserLoginVO userLoginVO) {
        LoginStatusVO statusVO = new LoginStatusVO();
        Subject subject = SecurityUtils.getSubject();
        String username = userLoginVO.getAccount();
        String hashPassword = userLoginVO.getPassword();
        try {
            // 使用账号和密码进行登录，此时并未生成token，不需要使用token进行用户认证
            subject.login(new CustomToken(username, hashPassword, null,null));
        } catch (AuthenticationException e) {
            logger.error(e.getMessage());
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
    @ResponseBody
    public LoginStatusVO checkLogin(HttpServletRequest request, String username, String userToken) {
        LoginStatusVO statusVO = new LoginStatusVO();
        if (username != null && userToken != null) {
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
                        logger.error(e.getMessage());
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
        } else {
            statusVO.errorStatus(UserControllerStatusEnum.USER_CHECK_LOGIN_ERROR.getCode(),
                    UserControllerStatusEnum.USER_CHECK_LOGIN_ERROR.getMessage());
        }
        return statusVO;
    }

    @PostMapping("logout")
    @ResponseBody
    public LoginStatusVO logout(String username, String userToken) {
        LoginStatusVO statusVO = new LoginStatusVO();
        if (username != null && userToken != null) {
            UserTokenDTO userTokenDTO = userService.getUserToken(username);
            if (userTokenDTO != null && userToken.equals(userTokenDTO.getToken())) {
                Subject subject = SecurityUtils.getSubject();
                subject.logout();
                userService.removeUserToken(username);
                statusVO.okStatus(UserControllerStatusEnum.USER_LOGOUT_SUCCESS.getCode(),
                        UserControllerStatusEnum.USER_LOGOUT_SUCCESS.getMessage());
            } else {
                statusVO.errorStatus(UserControllerStatusEnum.USER_LOGOUT_ERROR.getCode(),
                        UserControllerStatusEnum.USER_LOGOUT_ERROR.getMessage());
            }
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
