package top.zywork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import top.zywork.dao.UserDAO;
import top.zywork.dos.UserDO;
import top.zywork.dto.UserDTO;
import top.zywork.dto.UserTokenDTO;
import top.zywork.query.UserAccountPasswordQuery;
import top.zywork.service.AbstractBaseService;
import top.zywork.service.UserService;

import java.util.concurrent.TimeUnit;

/**
 * 用户Service实现类，使用了Redis<br/>
 *
 * 创建于2017-09-05
 * 更新于2018-01-19
 *
 * @author 王振宇
 * @version 1.1
 */
@Service(value = "userService")
public class UserServiceImpl extends AbstractBaseService implements UserService {

    private UserDAO userDAO;

    private RedisTemplate<String, UserTokenDTO> redisTemplate;

    @Override
    public UserDTO getByAccountPassword(UserAccountPasswordQuery userAccountPasswordQuery) {
        UserDO userDO = userDAO.getByAccountPassword(userAccountPasswordQuery);
        if (userDO != null) {
            return getBeanMapper().map(userDO, UserDTO.class);
        }
        return null;
    }

    @Override
    public String getPassword(String username) {
        return userDAO.getPassword(username);
    }

    @Override
    public void saveUserToken(UserTokenDTO userTokenDTO) {
        ValueOperations<String, UserTokenDTO> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(userTokenDTO.getUsername(), userTokenDTO);
        redisTemplate.expire(userTokenDTO.getUsername(), 7, TimeUnit.DAYS);
    }

    @Override
    public UserTokenDTO getUserToken(String username) {
        ValueOperations<String, UserTokenDTO> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(username);
    }

    @Override
    public void removeUserToken(String username) {
        redisTemplate.delete(username);
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        super.setBaseDAO(userDAO);
        this.userDAO = userDAO;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, UserTokenDTO> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
