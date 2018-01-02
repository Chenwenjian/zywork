package top.zywork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.dao.ModuleDAO;
import top.zywork.service.AbstractBaseService;
import top.zywork.service.ModuleService;

/**
 * 模块服务实现类<br />
 * 创建于2017-09-12
 *
 * @author 王振宇
 * @version 1.0
 */
@Service(value = "moduleService")
public class ModuleServiceImpl extends AbstractBaseService implements ModuleService {

    private ModuleDAO moduleDAO;

    @Autowired
    public void setModuleDAO(ModuleDAO moduleDAO) {
        super.setBaseDAO(moduleDAO);
        this.moduleDAO = moduleDAO;
    }
}
