package top.zywork.generator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.zywork.generator.bean.Generator;
import top.zywork.generator.bean.TableColumns;
import top.zywork.generator.constant.TemplateConstants;
import top.zywork.generator.generator.CodeGenerator;
import top.zywork.vo.ControllerStatusVO;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/generator")
public class GeneratorController {

    @GetMapping("all-codes")
    @ResponseBody
    public ControllerStatusVO generateAllCodes(HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        Generator generator = (Generator) servletContext.getAttribute("generator");
        List<TableColumns> tableColumnsList = (List<TableColumns>) servletContext.getAttribute("tableColumnsList");
        CodeGenerator.generateCodes(generator, tableColumnsList);
        ControllerStatusVO statusVO = new ControllerStatusVO();
        statusVO.okStatus(200, "成功生成所有表的代码！共生成" + tableColumnsList.size() * TemplateConstants.TOTAL_TEMPLATES + "个文件");
        return statusVO;
    }

    @GetMapping("code/{tableName}")
    @ResponseBody
    public ControllerStatusVO generateCode(@PathVariable("tableName") String tableName, HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        Generator generator = (Generator) servletContext.getAttribute("generator");
        List<TableColumns> tableColumnsList = (List<TableColumns>) servletContext.getAttribute("tableColumnsList");
        for (TableColumns tableColumns : tableColumnsList) {
            if (tableName.equals(tableColumns.getTableName())) {
                CodeGenerator.generateCode(generator, tableColumns);
                break;
            }
        }
        ControllerStatusVO statusVO = new ControllerStatusVO();
        statusVO.okStatus(200, "成功生成所选表的代码！共生成" + TemplateConstants.TOTAL_TEMPLATES + "个文件");
        return statusVO;
    }

    @PostMapping("join-code")
    @ResponseBody
    public ControllerStatusVO generateJoinCode(String beanName, String requestMapping, String primaryTable,
                                               String[] columns, String whereClause, HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        Generator generator = (Generator) servletContext.getAttribute("generator");
        List<TableColumns> tableColumnsList = (List<TableColumns>) servletContext.getAttribute("tableColumnsList");
        CodeGenerator.generateJoinCodes(beanName, requestMapping, generator, primaryTable, columns, tableColumnsList, whereClause);
        ControllerStatusVO statusVO = new ControllerStatusVO();
        statusVO.okStatus(200, "成功生成所选关联表的代码！共生成" + TemplateConstants.TOTAL_TEMPLATES + "个文件");
        return statusVO;
    }

}
