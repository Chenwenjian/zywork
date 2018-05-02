package top.zywork.controller;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.zywork.common.BindingResultUtils;
import top.zywork.common.DozerMapperUtils;
import top.zywork.common.StringUtils;
import top.zywork.dto.PagerDTO;
import top.zywork.dto.RoleDTO;
import top.zywork.exception.ServiceException;
import top.zywork.query.PageQuery;
import top.zywork.query.StatusQueries;
import top.zywork.query.StatusQuery;
import top.zywork.query.RoleQuery;
import top.zywork.service.RoleService;
import top.zywork.vo.ControllerStatusVO;
import top.zywork.vo.PagerVO;
import top.zywork.vo.RoleVO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * RoleController控制器类<br/>
 *
 * 创建于2018-05-02<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    private RoleService roleService;

    @GetMapping("page")
    public String page() {
        return "Role/Role";
    }

    @GetMapping("add-modal")
    public String addModal() {
        return "Role/RoleAddModal";
    }

    @GetMapping("edit-modal")
    public String editModal() {
        return "Role/RoleEditModal";
    }

    @GetMapping("detail-modal")
    public String detailModal() {
        return "Role/RoleDetailModal";
    }

    @GetMapping("search-modal")
    public String searchModal() {
        return "Role/RoleSearchModal";
    }

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(@Validated RoleVO roleVO, BindingResult bindingResult) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        if (bindingResult.hasErrors()) {
            statusVO.dataErrorStatus(500, BindingResultUtils.errorString(bindingResult));
        } else {
            try {
                roleService.save(getBeanMapper().map(roleVO, RoleDTO.class));
                statusVO.okStatus(200, "添加成功");
            } catch (ServiceException e) {
                logger.error("添加失败：{}", e.getMessage());
                statusVO.errorStatus(500, "添加失败");
            }
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(RoleVO roleVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            roleService.remove(getBeanMapper().map(roleVO, RoleDTO.class));
            statusVO.okStatus(200, "删除成功");
        } catch (ServiceException e) {
            logger.error("删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "删除失败");
        }
        return statusVO;
    }

    @GetMapping("remove/{id}")
    @ResponseBody
    public ControllerStatusVO removeById(@PathVariable("id") Long id) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            roleService.removeById(id);
            statusVO.okStatus(200, "删除成功");
        } catch (ServiceException e) {
            logger.error("删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "删除失败");
        }
        return statusVO;
    }

    @PostMapping("batch-remove")
    @ResponseBody
    public ControllerStatusVO removeByIds(String ids) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            roleService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(200, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(@Validated RoleVO roleVO, BindingResult bindingResult) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        if (bindingResult.hasErrors()) {
            statusVO.dataErrorStatus(500, BindingResultUtils.errorString(bindingResult));
        } else {
            try {
                roleService.update(getBeanMapper().map(roleVO, RoleDTO.class));
                statusVO.okStatus(200, "更新成功");
            } catch (ServiceException e) {
                logger.error("更新失败：{}", e.getMessage());
                statusVO.errorStatus(500, "更新失败");
            }
        }
        return statusVO;
    }

    @PostMapping("active")
    @ResponseBody
    public ControllerStatusVO updateActiveStatus(StatusQuery statusQuery) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            roleService.updateActiveStatus(statusQuery);
            statusVO.okStatus(200, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

    @PostMapping("batch-active")
        @ResponseBody
        public ControllerStatusVO updateActiveStatuses(String ids, StatusQueries statusQueries) {
            ControllerStatusVO statusVO = new ControllerStatusVO();
            statusQueries.setIds(StringUtils.strToLongArray(ids, ","));
            try {
                roleService.updateActiveStatuses(statusQueries);
                statusVO.okStatus(200, statusQueries.getStatus() == 0 ? "批量激活成功" : "批量冻结成功");
            } catch (ServiceException e) {
                logger.error("激活或冻结失败：{}", e.getMessage());
                statusVO.errorStatus(500, statusQueries.getStatus() == 0 ? "批量激活失败" : "批量冻结失败");
            }
            return statusVO;
        }

    @RequestMapping("one/{id}")
    @ResponseBody
    public RoleVO getById(@PathVariable("id") Long id) {
        RoleVO roleVO = new RoleVO();
        try {
            Object obj = roleService.getById(id);
            if (obj != null) {
                roleVO = getBeanMapper().map(obj, RoleVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return roleVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<RoleVO> listAll() {
        List<RoleVO> roleVOList = new ArrayList<>();
        try {
            List<Object> objectList = roleService.listAll();
            roleVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, RoleVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return roleVOList;
    }

    @RequestMapping("pager")
    @ResponseBody
    public PagerVO listPage(int offset, int limit, String sort, String order) {
        PagerVO pagerVO = new PagerVO();
        PageQuery pageQuery = new PageQuery(offset / limit + 1, limit, sort, order);
        try {
            PagerDTO pagerDTO = roleService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), RoleVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @RequestMapping("pager-cond")
    @ResponseBody
    public PagerVO listPageByCondition(int offset, int limit, String sort, String order, RoleQuery roleQuery) {
        PagerVO pagerVO = new PagerVO();
        PageQuery pageQuery = new PageQuery(offset / limit + 1, limit, sort, order);
        try {
            PagerDTO pagerDTO = roleService.listPageByCondition(pageQuery, roleQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), RoleVO.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}
