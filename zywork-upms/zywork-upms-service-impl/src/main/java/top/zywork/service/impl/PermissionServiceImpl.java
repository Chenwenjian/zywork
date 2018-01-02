package top.zywork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.common.DozerMapperUtils;
import top.zywork.common.ExceptionUtils;
import top.zywork.dao.PermissionDAO;
import top.zywork.dos.PermissionDO;
import top.zywork.dto.PermissionDTO;
import top.zywork.exception.DAOException;
import top.zywork.service.AbstractBaseService;
import top.zywork.service.PermissionService;

import java.util.List;

/**
 * 权限Service实现类<br />
 * 创建于2017-09-11
 *
 * @author 王振宇
 * @version 1.0
 */
@Service(value = "permissionService")
public class PermissionServiceImpl extends AbstractBaseService implements PermissionService {

    private PermissionDAO permissionDAO;

    @Override
    public List<PermissionDTO> listByModuleId(String moduleId) {
        return null;
    }

    @Override
    public List<PermissionDTO> listByRoleId(String roleId) {
        return null;
    }

    @Override
    public List<PermissionDTO> listByRoleIds(List<Long> roleIds) {
        try {
            List<PermissionDO> permissionDOList = permissionDAO.listByRoleIds(roleIds);
            return DozerMapperUtils.map(getBeanMapper(), permissionDOList, PermissionDTO.class);
        } catch (DAOException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Override
    public List<PermissionDTO> listByAccount(String account) {
        try {
            List<PermissionDO> permissionDOList = permissionDAO.listByAccount(account);
            return DozerMapperUtils.map(getBeanMapper(), permissionDOList, PermissionDTO.class);
        } catch (DAOException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Autowired
    public void setPermissionDAO(PermissionDAO permissionDAO) {
        super.setBaseDAO(permissionDAO);
        this.permissionDAO = permissionDAO;
    }
}
