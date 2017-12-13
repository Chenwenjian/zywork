package top.zywork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.dao.UserDAO;
import top.zywork.dto.PagerDTO;
import top.zywork.query.PageQuery;
import top.zywork.query.StatusQuery;
import top.zywork.service.UserService;

import java.io.Serializable;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Override
    public void save(Object dataTransferObj) {
        userDAO.save(dataTransferObj);
    }

    @Override
    public void remove(Object dataTransferObj) {

    }

    @Override
    public void removeById(Serializable id) {

    }

    @Override
    public void update(Object dataTransferObj) {

    }

    @Override
    public void updateActiveStatus(StatusQuery statusQuery) {

    }

    @Override
    public Object getById(Serializable id) {
        return null;
    }

    @Override
    public List<Object> listAll() {
        return null;
    }

    @Override
    public PagerDTO listPage(PageQuery pageQuery) {
        return null;
    }

    @Override
    public PagerDTO listPageByCondition(PageQuery pageQuery, Object queryObj) {
        return null;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
