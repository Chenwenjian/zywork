package top.zywork.service;

import top.zywork.dto.UserDTO;
import top.zywork.dto.UserTokenDTO;
import top.zywork.query.UserAccountPasswordQuery;

/**
 * 用户Service接口<br />
 * 创建于2017-09-05
 *
 * @author 王振宇
 * @version 1.0
 */
public interface UserService extends BaseService {

    /**
     * 根据用户账号及密码查询用户
     * @param userAccountPasswordQuery 用户账号及密码组成的查询对象，账号可以是邮箱，手机号，账户名
     * @return 指定账号及密码的用户对象
     */
    UserDTO getByAccountPassword(UserAccountPasswordQuery userAccountPasswordQuery);

    /**
     * 根据用户名获取用户密码
     * @param username 用户名
     * @return 用户密码
     */
    String getPassword(String username);

    /**
     * 保存用户token信息到Redis中，包含用户名，生成token的时间和生成的token
     * @param userTokenDTO 由用户名，生成token的时间和生成的token组成的DTO对象
     */
    void saveUserToken(UserTokenDTO userTokenDTO);

    /**
     * 通过用户名去Redis中获取UserTokenDTO
     * @param username 用户名
     * @return 由用户名，生成token的时间和生成的token组成的DTO对象
     */
    UserTokenDTO getUserToken(String username);

    /**
     * 根据用户名从Redis中移除用户Token
     * @param username 用户名
     */
    void removeUserToken(String username);
}
