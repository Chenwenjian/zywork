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

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * PermissionServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-02<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
@Service(value = "permissionService")
public class PermissionServiceImpl extends AbstractBaseService implements PermissionService {

    private PermissionDAO permissionDAO;

    @Override
    public List<Object> listByModuleId(String moduleId) {
        return null;
    }

    @Override
    public List<Object> listByRoleId(String roleId) {
        return null;
    }

    @Override
    public List<Object> listByRoleIds(List<Long> roleIds) {
        try {
            List<Object> dtoObjList = new ArrayList<>();
            List<Object> doObjList = permissionDAO.listByRoleIds(roleIds);
            if (doObjList != null && doObjList.size() > 0) {
                dtoObjList = DozerMapperUtils.mapList(getBeanMapper(), doObjList, getDtoClass());;
            }
            return dtoObjList;
        } catch (DAOException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Override
    public List<Object> listByAccount(String account) {
        try {
            List<Object> dtoObjList = new ArrayList<>();
            List<Object> doObjList = permissionDAO.listByAccount(account);
            if (doObjList != null && doObjList.size() > 0) {
                dtoObjList = DozerMapperUtils.mapList(getBeanMapper(), doObjList, getDtoClass());
            }
            return dtoObjList;
        } catch (DAOException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Autowired
    public void setPermissionDAO(PermissionDAO permissionDAO) {
        super.setBaseDAO(permissionDAO);
        this.permissionDAO = permissionDAO;
    }

    @PostConstruct
    public void init() {
        super.init(PermissionDO.class, PermissionDTO.class);
    }
}
