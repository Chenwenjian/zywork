package top.zywork.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.common.DozerMapperUtils;
import top.zywork.common.ExceptionUtils;
import top.zywork.dao.RoleDAO;
import top.zywork.dos.RoleDO;
import top.zywork.dto.RoleDTO;
import top.zywork.exception.DAOException;
import top.zywork.service.AbstractBaseService;
import top.zywork.service.RoleService;

import java.util.List;

/**
 * 角色Service实现类<br />
 * 创建于2017-09-11
 *
 * @author 王振宇
 * @version 1.0
 */
@Service(value = "roleService")
public class RoleServiceImpl extends AbstractBaseService implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    private RoleDAO roleDAO;

    @Override
    public List<RoleDTO> listByAccount(String account) {
        try {
            List<RoleDO> roleDOList = roleDAO.listByAccount(account);
            return DozerMapperUtils.map(getBeanMapper(), roleDOList, RoleDTO.class);
        } catch (DAOException e) {
            logger.error(e.getMessage());
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        super.setBaseDAO(roleDAO);
        this.roleDAO = roleDAO;
    }
}
