package top.zywork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.dao.UserSocialDAO;
import top.zywork.dos.UserSocialDO;
import top.zywork.dto.UserSocialDTO;
import top.zywork.service.AbstractBaseService;
import top.zywork.service.UserSocialService;

import javax.annotation.PostConstruct;

/**
 * UserSocialServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-02<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
@Service(value = "userSocialService")
public class UserSocialServiceImpl extends AbstractBaseService implements UserSocialService {

    private UserSocialDAO userSocialDAO;

    @Autowired
    public void setUserSocialDAO(UserSocialDAO userSocialDAO) {
        super.setBaseDAO(userSocialDAO);
        this.userSocialDAO = userSocialDAO;
    }

    @PostConstruct
    public void init() {
        super.init(UserSocialDO.class, UserSocialDTO.class);
    }
}
