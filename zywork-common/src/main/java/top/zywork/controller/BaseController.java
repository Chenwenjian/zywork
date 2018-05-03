package top.zywork.controller;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import top.zywork.enums.AuthStatusEnum;
import top.zywork.vo.ControllerStatusVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 所有Controller类的父类<br/>
 *
 * 创建于2018-03-12<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
public class BaseController {

    private Mapper beanMapper;

    /**
     * 对Apache Shiro用户认证异常的处理
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler({UnauthenticatedException.class, AuthenticationException.class})
    @ResponseBody
    public ControllerStatusVO authenticationException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        statusVO.errorStatus(AuthStatusEnum.UNAUTHENTICATED.getCode(), AuthStatusEnum.UNAUTHENTICATED.getMessage());
        return statusVO;
    }

    /**
     * 对Apache Shiro用户授权异常的处理
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    @ResponseBody
    public ControllerStatusVO authorizationException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        statusVO.errorStatus(AuthStatusEnum.UNAUTHORIZED.getCode(), AuthStatusEnum.UNAUTHORIZED.getMessage());
        return statusVO;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @Autowired
    public void setBeanMapper(Mapper beanMapper) {
        this.beanMapper = beanMapper;
    }

    public Mapper getBeanMapper() {
        return beanMapper;
    }

}
