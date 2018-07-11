package top.zywork.aspect;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.zywork.annotation.SysLog;
import top.zywork.common.IPUtils;
import top.zywork.common.WebUtils;
import top.zywork.dto.SysLogDTO;
import top.zywork.service.SysLogService;

import java.sql.Timestamp;

/**
 * 系统日志切面，在方法上使用@SysLog注解用于记录系统日志<br />
 * 创建于2017-12-19<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
@Component
@Aspect
public class SysLogAspect {

    private SysLogService sysLogService;

    private long startTime;

    @Pointcut("@annotation(top.zywork.annotation.SysLog)")
    public void methodAspect() {}

    @Before("methodAspect()")
    public void before(JoinPoint joinPoint) {
        startTime = System.currentTimeMillis();
    }

    @Around("methodAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        SysLogDTO sysLogDTO = createSysLogDTO(signature, endTime - startTime);
        sysLogDTO.setExecuteTime(new Timestamp(endTime));
        sysLogService.save(sysLogDTO);
        return result;
    }

    @AfterThrowing(pointcut = "methodAspect()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        long endTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        SysLogDTO sysLogDTO = createSysLogDTO(signature, endTime - startTime);
        sysLogDTO.setExecuteTime(new Timestamp(endTime));
//        sysLogDTO.setHasException((byte) 1);
//        sysLogDTO.setExceptionMsg(e.getMessage());
        sysLogService.save(sysLogDTO);
    }

    private SysLogDTO createSysLogDTO(MethodSignature signature, long costTime) {
        SysLog sysLog = signature.getMethod().getDeclaredAnnotation(SysLog.class);
        SysLogDTO sysLogDTO = new SysLogDTO();
        sysLogDTO.setUserAccount(SecurityUtils.getSubject().getPrincipal().toString());
        sysLogDTO.setDescription(sysLog.description());
        sysLogDTO.setExecuteClass(signature.getDeclaringTypeName());
        sysLogDTO.setExecuteMethod(signature.getName());
        sysLogDTO.setExecuteCostTime(costTime);
        sysLogDTO.setExecuteIp(IPUtils.getIP(WebUtils.getServletRequest()));
        return sysLogDTO;
    }

    @Autowired
    public void setSysLogService(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }
}