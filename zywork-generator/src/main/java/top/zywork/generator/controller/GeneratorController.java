package top.zywork.generator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.zywork.common.FileUtils;
import top.zywork.generator.bean.Generator;
import top.zywork.generator.bean.TableColumns;
import top.zywork.generator.constant.TemplateConstants;
import top.zywork.generator.generator.CodeGenerator;
import top.zywork.vo.ControllerStatusVO;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * 代码生成控制器<br/>
 *
 * 创建于2018-03-22<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
@Controller
@RequestMapping("/generator/generator")
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

    @PostMapping("codes")
    @ResponseBody
    public ControllerStatusVO generateCodes(String[] tableNames, HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        Generator generator = (Generator) servletContext.getAttribute("generator");
        List<TableColumns> tableColumnsList = (List<TableColumns>) servletContext.getAttribute("tableColumnsList");
        for (String tableName : tableNames) {
            for (TableColumns tableColumns : tableColumnsList) {
                if (tableName.equals(tableColumns.getTableName())) {
                    CodeGenerator.generateCode(generator, tableColumns);
                }
            }
        }
        ControllerStatusVO statusVO = new ControllerStatusVO();
        statusVO.okStatus(200, "成功生成所选表的代码！共生成" + tableNames.length * TemplateConstants.TOTAL_TEMPLATES + "个文件");
        return statusVO;
    }

    @PostMapping("join-code")
    @ResponseBody
    public ControllerStatusVO generateJoinCode(String beanName, String requestMapping, String primaryTable,
                                               String[] columns, String whereClause, HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        Generator generator = (Generator) servletContext.getAttribute("generator");
        String dir = generator.getSaveBaseDir() + generator.getJavaSrcDir()
                + generator.getBasePackage().replace(".", File.separator) + File.separator + generator.getDoPackage();
        String fileName = beanName + generator.getDoSuffix();
        ControllerStatusVO statusVO = new ControllerStatusVO();
        if (FileUtils.exist(dir, fileName)) {
            statusVO.errorStatus(500, "已经存在指定名称的实体类，请重新填写实体类名称后再生成代码");
        } else {
            List<TableColumns> tableColumnsList = (List<TableColumns>) servletContext.getAttribute("tableColumnsList");
            CodeGenerator.generateJoinCodes(beanName, requestMapping, generator, primaryTable, columns, tableColumnsList, whereClause);
            statusVO.okStatus(200, "成功生成所选关联表的代码！共生成" + TemplateConstants.TOTAL_JOIN_TEMPLATES + "个文件");
        }
        return statusVO;
    }

}
