package top.zywork.generator.generator;

import org.apache.commons.lang3.StringUtils;
import top.zywork.common.DateFormatUtils;
import top.zywork.enums.DatePatternEnum;
import top.zywork.generator.bean.Generator;
import top.zywork.generator.bean.TableColumns;
import top.zywork.generator.common.GeneratorUtils;
import top.zywork.generator.constant.TemplateConstants;

import java.util.Calendar;
import java.util.List;

/**
 * 控制器自动生成代码封装类<br/>
 *
 * 创建于2018-03-12<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
public class ControllerGenerator {

    /**
     * 生成Controller类文件
     * @param generator
     * @param tableColumns 表数据
     */
    public static void generateController(Generator generator, TableColumns tableColumns) {
        String beanName = GeneratorUtils.tableNameToClassName(tableColumns.getTableName(), generator.getTablePrefix());
        String moduleName = GeneratorUtils.getModuleName(tableColumns.getTableName(), generator.getTablePrefix());
        String packagePath = GeneratorUtils.createPackage(generator, generator.getControllerPackage());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.CONTROLLER_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.CREATE_DATE,
                DateFormatUtils.format(Calendar.getInstance(), DatePatternEnum.DATE.getValue()))
                .replace(TemplateConstants.AUTHOR, generator.getAuthor())
                .replace(TemplateConstants.BEAN_NAME, beanName).replace(TemplateConstants.MODULE_NAME, moduleName)
                .replace(TemplateConstants.BEAN_NAME_LOWER_CASE, StringUtils.uncapitalize(beanName))
                .replace(TemplateConstants.ADD_MODAL_PAGE, beanName + generator.getViewAddModalSuffix().replace(".jsp", ""))
                .replace(TemplateConstants.EDIT_MODAL_PAGE, beanName + generator.getViewEditModalSuffix().replace(".jsp", ""))
                .replace(TemplateConstants.DETAIL_MODAL_PAGE, beanName + generator.getViewDetailModalSuffix().replace(".jsp", ""))
                .replace(TemplateConstants.SEARCH_MODAL_PAGE, beanName + generator.getViewSearchModalSuffix().replace(".jsp", ""));
        GeneratorUtils.writeFile(fileContent, packagePath, beanName + generator.getControllerSuffix());
    }

    /**
     * 生成关联表的Controller
     * @param beanName bean名称
     * @param mappingUrl 控制器映射url
     * @param generator Generator实例
     */
    public static void generateJoinController(String beanName, String mappingUrl, Generator generator) {
        String packagePath = GeneratorUtils.createPackage(generator, generator.getControllerPackage());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.CONTROLLER_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.CREATE_DATE,
                DateFormatUtils.format(Calendar.getInstance(), DatePatternEnum.DATE.getValue()))
                .replace(TemplateConstants.AUTHOR, generator.getAuthor())
                .replace(TemplateConstants.BEAN_NAME, beanName).replace(TemplateConstants.MODULE_NAME, mappingUrl)
                .replace(TemplateConstants.BEAN_NAME_LOWER_CASE, StringUtils.uncapitalize(beanName))
                .replace(TemplateConstants.ADD_MODAL_PAGE, beanName + generator.getViewAddModalSuffix().replace(".jsp", ""))
                .replace(TemplateConstants.EDIT_MODAL_PAGE, beanName + generator.getViewEditModalSuffix().replace(".jsp", ""))
                .replace(TemplateConstants.DETAIL_MODAL_PAGE, beanName + generator.getViewDetailModalSuffix().replace(".jsp", ""))
                .replace(TemplateConstants.SEARCH_MODAL_PAGE, beanName + generator.getViewSearchModalSuffix().replace(".jsp", ""));
        GeneratorUtils.writeFile(fileContent, packagePath, beanName + generator.getControllerSuffix());
    }

    /**
     * 生成所有的Controller类
     * @param generator
     * @param tableColumnsList 所有表数据
     */
    public static void generateControllers(Generator generator, List<TableColumns> tableColumnsList) {
        for (TableColumns tableColumns : tableColumnsList) {
            generateController(generator, tableColumns);
        }
    }

}
