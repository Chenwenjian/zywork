package top.zywork.aspect;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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

    @Pointcut("@annotation(top.zywork.annotation.SysLog)")
    public void methodAspect() {}

    @Around("methodAspect()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        saveLog(joinPoint, System.currentTimeMillis() - startTime);
        return result;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long costTime) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        SysLog sysLog = signature.getMethod().getDeclaredAnnotation(SysLog.class);
        SysLogDTO sysLogDTO = new SysLogDTO();
        sysLogDTO.setUserAccount(SecurityUtils.getSubject().getPrincipal().toString());
        sysLogDTO.setDescription(sysLog.description());
        sysLogDTO.setExecuteClass(signature.getDeclaringTypeName());
        sysLogDTO.setExecuteMethod(signature.getName());
        sysLogDTO.setExecuteCostTime(costTime);
        sysLogDTO.setExecuteTime(new Timestamp(System.currentTimeMillis()));
        sysLogDTO.setExecuteIp(IPUtils.getIP(WebUtils.getServletRequest()));
        sysLogService.save(sysLogDTO);
    }

    @Autowired
    public void setSysLogService(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }
}