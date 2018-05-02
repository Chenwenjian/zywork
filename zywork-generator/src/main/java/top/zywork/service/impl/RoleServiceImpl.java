package top.zywork.service.impl;

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

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * RoleServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-02<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
@Service(value = "roleService")
public class RoleServiceImpl extends AbstractBaseService implements RoleService {

    private RoleDAO roleDAO;

    @Override
    public List<Object> listByAccount(String account) {
        try {
            List<Object> dtoObjList = new ArrayList<>();
            List<Object> doObjList = roleDAO.listByAccount(account);
            if (doObjList != null) {
                dtoObjList = DozerMapperUtils.mapList(getBeanMapper(), doObjList, getDtoClass());;
            }
            return dtoObjList;
        } catch (DAOException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        super.setBaseDAO(roleDAO);
        this.roleDAO = roleDAO;
    }

    @PostConstruct
    public void init() {
        super.init(RoleDO.class, RoleDTO.class);
    }
}
