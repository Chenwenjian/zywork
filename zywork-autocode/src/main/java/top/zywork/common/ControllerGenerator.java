package top.zywork.common;

import org.apache.commons.lang3.StringUtils;
import top.zywork.bean.TableColumns;
import top.zywork.constant.GeneratorConstants;
import top.zywork.constant.TemplateConstants;
import top.zywork.enums.DatePatternEnum;

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
     * @param tableColumns 表数据
     */
    public static void generateController(TableColumns tableColumns) {
        String beanName = GeneratorUtils.tableNameToClassName(tableColumns.getTableName(), "t");
        String moduleName = GeneratorUtils.getModuleName(tableColumns.getTableName(), "t");
        String packagePath = GeneratorUtils.createPackage(GeneratorConstants.CONTROLLER_PACKAGE);
        String fileContent = GeneratorUtils.readTemplate(GeneratorConstants.CONTROLLER_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.CREATE_DATE,
                DateFormatUtils.format(Calendar.getInstance(), DatePatternEnum.DATE.getValue()))
                .replace(TemplateConstants.AUTHOR, GeneratorConstants.AUTHOR)
                .replace(TemplateConstants.BEAN_NAME, beanName).replace(TemplateConstants.MODULE_NAME, moduleName)
        .replace(TemplateConstants.BEAN_NAME_LOWER_CASE, StringUtils.uncapitalize(beanName));
        GeneratorUtils.writeFile(fileContent, packagePath, beanName + GeneratorConstants.CONTROLLER_JAVA_SUFFIX);
    }

    /**
     * 生成所有的Controller类
     * @param tableColumnsList 所有表数据
     */
    public static void generateControllers(List<TableColumns> tableColumnsList) {
        for (TableColumns tableColumns : tableColumnsList) {
            generateController(tableColumns);
        }
    }

}
