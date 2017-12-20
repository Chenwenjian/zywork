package top.zywork.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SysLogServiceTest extends BaseTest {

    @Autowired
    private SysLogTestService sysLogTestService;

    @Test
    public void testLog() {
        sysLogTestService.testSysLog();
    }
}
