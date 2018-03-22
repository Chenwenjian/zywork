package top.zywork.controller;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.zywork.common.DozerMapperUtils;
import top.zywork.common.StringUtils;
import top.zywork.dto.PagerDTO;
import top.zywork.dto.UserDTO;
import top.zywork.exception.ServiceException;
import top.zywork.query.PageQuery;
import top.zywork.query.StatusQuery;
import top.zywork.query.UserQuery;
import top.zywork.service.UserService;
import top.zywork.vo.ControllerStatusVO;
import top.zywork.vo.PagerVO;
import top.zywork.vo.UserVO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class TestController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    private UserService userService;

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(UserVO userVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            userService.save(getBeanMapper().map(userVO, UserDTO.class));
            statusVO.okStatus(200, "添加成功");
        } catch (ServiceException e) {
            logger.error("添加失败");
            statusVO.errorStatus(500, "添加失败");
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(UserVO userVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            userService.remove(getBeanMapper().map(userVO, UserDTO.class));
            statusVO.okStatus(200, "删除成功");
        } catch (ServiceException e) {
            logger.error("删除失败");
            statusVO.errorStatus(500, "删除失败");
        }
        return statusVO;
    }

    @GetMapping("remove/{id}")
    @ResponseBody
    public ControllerStatusVO removeById(@PathVariable("id") Long id) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            userService.removeById(id);
            statusVO.okStatus(200, "删除成功");
        } catch (ServiceException e) {
            logger.error("删除失败");
            statusVO.errorStatus(500, "删除失败");
        }
        return statusVO;
    }

    @PostMapping("batch-remove")
    @ResponseBody
    public ControllerStatusVO removeByIds(String ids) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            userService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(200, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败");
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(UserVO userVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            userService.update(getBeanMapper().map(userVO, UserDTO.class));
            statusVO.okStatus(200, "更新成功");
        } catch (ServiceException e) {
            logger.error("更新失败");
            statusVO.errorStatus(500, "更新失败");
        }
        return statusVO;
    }

    @PostMapping("active")
    @ResponseBody
    public ControllerStatusVO updateActiveStatus(StatusQuery statusQuery) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            userService.updateActiveStatus(statusQuery);
            statusVO.okStatus(200, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败");
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public UserVO getById(@PathVariable("id") Long id) {
        UserVO userVO = new UserVO();
        try {
            Object obj = userService.getById(id);
            if (obj != null) {
                userVO = getBeanMapper().map(obj, UserVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败");
        }
        return userVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<UserVO> listAll() {
        List<UserVO> userVOList = new ArrayList<>();
        try {
            List<Object> objectList = userService.listAll();
            userVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, UserVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败");
        }
        return userVOList;
    }

    @PostMapping("page")
    @ResponseBody
    public PagerVO listPage(PageQuery pageQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = userService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), UserVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败");
        }
        return pagerVO;
    }

    @PostMapping("page-cond")
    @ResponseBody
    public PagerVO listPageByCondition(PageQuery pageQuery, UserQuery userQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = userService.listPageByCondition(pageQuery, userQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), UserVO.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败");
        }
        return pagerVO;
    }

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
