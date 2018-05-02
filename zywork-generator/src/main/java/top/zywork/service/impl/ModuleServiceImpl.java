package top.zywork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.dao.ModuleDAO;
import top.zywork.dos.ModuleDO;
import top.zywork.dto.ModuleDTO;
import top.zywork.service.AbstractBaseService;
import top.zywork.service.ModuleService;

import javax.annotation.PostConstruct;

/**
 * ModuleServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-02<br/>
 *
 * @author http://zywork.top 王振宇
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

    @PostConstruct
    public void init() {
        super.init(ModuleDO.class, ModuleDTO.class);
    }
}
