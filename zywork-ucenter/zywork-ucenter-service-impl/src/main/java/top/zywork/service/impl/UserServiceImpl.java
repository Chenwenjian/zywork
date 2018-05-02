package top.zywork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.dao.UserDAO;
import top.zywork.dos.UserDO;
import top.zywork.dto.UserDTO;
import top.zywork.service.AbstractBaseService;
import top.zywork.service.UserService;

import javax.annotation.PostConstruct;

/**
 * UserServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-02<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
@Service(value = "userService")
public class UserServiceImpl extends AbstractBaseService implements UserService {

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        super.setBaseDAO(userDAO);
        this.userDAO = userDAO;
    }

    @PostConstruct
    public void init() {
        super.init(UserDO.class, UserDTO.class);
    }
}
