package top.zywork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.dao.SysLogDAO;
import top.zywork.dos.SysLogDO;
import top.zywork.dto.SysLogDTO;
import top.zywork.service.AbstractBaseService;
import top.zywork.service.SysLogService;

import javax.annotation.PostConstruct;

/**
 * 系统日志服务接口实现类<br />
 * 创建于2017-12-19<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
@Service(value = "sysLogService")
public class SysLogServiceImpl extends AbstractBaseService implements SysLogService {

    private SysLogDAO logDAO;

    @Autowired
    public void setLogDAO(SysLogDAO logDAO) {
        super.setBaseDAO(logDAO);
        this.logDAO = logDAO;
    }

    @PostConstruct
    public void init() {
        super.init(SysLogDO.class, SysLogDTO.class);
    }

}
