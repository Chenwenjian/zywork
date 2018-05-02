package top.zywork.security.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import top.zywork.common.AuthUtils;
import top.zywork.dto.PermissionDTO;
import top.zywork.dto.RoleDTO;
import top.zywork.query.UserAccountPasswordQuery;
import top.zywork.service.PermissionService;
import top.zywork.service.RoleService;
import top.zywork.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 自定义的Shiro Realm<br />
 * 创建于2017-09-05
 *
 * @author 王振宇
 * @version 1.0
 */
@Component
public class AppShiroRealm extends AuthorizingRealm {

    private UserService userService;
    private RoleService roleService;
    private PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<Object> roleDtoObjList = roleService.listByAccount(username);
        if (roleDtoObjList != null && roleDtoObjList.size() > 0) {
            for (Object obj : roleDtoObjList) {
                RoleDTO roleDTO = (RoleDTO) obj;
                authorizationInfo.addRole(roleDTO.getTitle());
            }
        }
        List<Object> permissionDtoObjList = permissionService.listByAccount(username);
        if (permissionDtoObjList != null && permissionDtoObjList.size() > 0) {
            for (Object obj : permissionDtoObjList) {
                PermissionDTO permissionDTO = (PermissionDTO) obj;
                authorizationInfo.addStringPermission(permissionDTO.getPermission());
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        CustomToken customToken = (CustomToken) authenticationToken;
        if (customToken.getToken() == null) {
            String username = customToken.getPrincipal().toString();
            String password = String.valueOf((char[]) customToken.getCredentials());
            Object userDtoObj = userService.getByAccountPassword(new UserAccountPasswordQuery(username, password));
            if (userDtoObj != null) {
                return new SimpleAuthenticationInfo(customToken.getPrincipal(), password, getName());
            }
        } else {
            String username = customToken.getPrincipal().toString();
            String hashPassword = userService.getPassword(username);
            if (hashPassword != null && AuthUtils.generateToken(username, customToken.getTimestamp(), hashPassword).equals(customToken.getToken())) {
                return new SimpleAuthenticationInfo(customToken.getPrincipal(), customToken.getToken(), getName());
            }
        }
        return null;
    }

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Resource
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Resource
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }
}
