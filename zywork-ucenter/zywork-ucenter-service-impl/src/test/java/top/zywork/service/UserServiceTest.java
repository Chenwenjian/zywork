package top.zywork.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.zywork.dos.UserDO;

public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSave() {
        UserDO userDO = new UserDO();
        userDO.setPassword("test");
        userService.save(userDO);
    }

}
