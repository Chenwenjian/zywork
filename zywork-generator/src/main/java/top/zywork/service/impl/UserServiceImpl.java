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

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

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
    private RedisTemplate<String, UserTokenDTO> redisTemplate;

    @Override
    public Object getByAccountPassword(UserAccountPasswordQuery userAccountPasswordQuery) {
        Object obj = userDAO.getByAccountPassword(userAccountPasswordQuery);
        if (obj != null) {
            return getBeanMapper().map(obj, getDtoClass());
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
        valueOperations.set("user:" + userTokenDTO.getUsername(), userTokenDTO);
        redisTemplate.expire(userTokenDTO.getUsername(), 7, TimeUnit.DAYS);
    }

    @Override
    public UserTokenDTO getUserToken(String username) {
        ValueOperations<String, UserTokenDTO> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get("user:" + username);
    }

    @Override
    public void removeUserToken(String username) {
        redisTemplate.delete("user:" + username);
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

    @PostConstruct
    public void init() {
        super.init(UserDO.class, UserDTO.class);
    }
}
