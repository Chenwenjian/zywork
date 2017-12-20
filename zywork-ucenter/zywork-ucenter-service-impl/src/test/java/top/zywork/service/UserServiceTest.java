package top.zywork.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.zywork.dto.UserDTO;

public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSave() {
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("test");
        userService.save(userDTO);
    }

}
