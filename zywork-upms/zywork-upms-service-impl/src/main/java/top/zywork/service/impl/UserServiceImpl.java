package top.zywork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.dao.UserDAO;
import top.zywork.dos.UserDO;
import top.zywork.dto.UserDTO;
import top.zywork.query.UserAccountPasswordQuery;
import top.zywork.service.AbstractBaseService;
import top.zywork.service.UserService;

@Service(value = "userService")
public class UserServiceImpl extends AbstractBaseService implements UserService {

    private UserDAO userDAO;

    @Override
    public UserDTO getByAccountPassword(UserAccountPasswordQuery userAccountPasswordQuery) {
        UserDO userDO = userDAO.getByAccountPassword(userAccountPasswordQuery);
        if (userDO != null) {
            return getBeanMapper().map(userDO, UserDTO.class);
        }
        return null;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        super.setBaseDAO(userDAO);
        this.userDAO = userDAO;
    }
}
