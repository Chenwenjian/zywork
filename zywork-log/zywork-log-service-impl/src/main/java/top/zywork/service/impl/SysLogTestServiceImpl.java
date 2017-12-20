package top.zywork.service.impl;

import org.springframework.stereotype.Service;
import top.zywork.annotation.SysLog;
import top.zywork.service.SysLogTestService;

/**
 * 系统日志测试服务接口实现类<br />
 * 创建于2017-12-19<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
@Service
public class SysLogTestServiceImpl implements SysLogTestService {

    @Override
    @SysLog(description = "系统日志测试")
    public void testSysLog() {
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            sum += i;
        }
    }
}
