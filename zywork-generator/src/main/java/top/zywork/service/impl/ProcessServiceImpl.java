package top.zywork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.dao.ProcessDAO;
import top.zywork.dos.ProcessDO;
import top.zywork.dto.ProcessDTO;
import top.zywork.service.AbstractBaseService;
import top.zywork.service.ProcessService;

import javax.annotation.PostConstruct;

/**
 * ProcessServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-02<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
@Service(value = "processService")
public class ProcessServiceImpl extends AbstractBaseService implements ProcessService {

    private ProcessDAO processDAO;

    @Autowired
    public void setProcessDAO(ProcessDAO processDAO) {
        super.setBaseDAO(processDAO);
        this.processDAO = processDAO;
    }

    @PostConstruct
    public void init() {
        super.init(ProcessDO.class, ProcessDTO.class);
    }
}
