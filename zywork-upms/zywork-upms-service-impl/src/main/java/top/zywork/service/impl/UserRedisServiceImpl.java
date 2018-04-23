package top.zywork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import top.zywork.common.ExceptionUtils;
import top.zywork.dao.UserDAO;
import top.zywork.dos.UserDO;
import top.zywork.dto.UserDTO;
import top.zywork.exception.DAOException;
import top.zywork.query.UserAccountPasswordQuery;
import top.zywork.service.AbstractBaseService;
import top.zywork.service.UserRedisService;

/**
 * 用户Service实现类<br />
 * 创建于2017-09-05
 *
 * @author 王振宇
 * @version 1.0
 */
@Service
public class UserRedisServiceImpl extends AbstractBaseService implements UserRedisService {

    private UserDAO userDAO;
    private RedisTemplate<String, UserDO> redisTemplate;

    @Override
    public UserDTO getByAccountPassword(UserAccountPasswordQuery userAccountPasswordQuery) {
        ValueOperations<String, UserDO> valueOperations = redisTemplate.opsForValue();
        UserDO userDO = valueOperations.get("user:" + userAccountPasswordQuery.getAccount());
        if (userDO != null) {
            System.out.println("从缓存获取用户对象");
            return getBeanMapper().map(userDO, UserDTO.class);
        }
        try {
            userDO = userDAO.getByAccountPassword(userAccountPasswordQuery);
            if (userDO != null) {
                System.out.println("从数据库获取用户对象");
                valueOperations.set("user:" + userAccountPasswordQuery.getAccount(), userDO);
                return getBeanMapper().map(userDO, UserDTO.class);
            }
            return null;
        } catch (DAOException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        super.setBaseDAO(userDAO);
        this.userDAO = userDAO;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, UserDO> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
