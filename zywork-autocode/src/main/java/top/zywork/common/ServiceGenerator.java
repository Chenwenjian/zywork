package top.zywork.common;

import org.apache.commons.lang3.StringUtils;
import top.zywork.bean.TableColumns;
import top.zywork.constant.GeneratorConstants;
import top.zywork.constant.TemplateConstants;
import top.zywork.enums.DatePatternEnum;

import java.util.Calendar;
import java.util.List;

/**
 * Service自动生成代码封装类<br/>
 *
 * 创建于2018-03-12<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
public class ServiceGenerator {

    /**
     * 生成Service接口
     * @param tableColumns 表数据
     */
    public static void generateService(TableColumns tableColumns) {
        String beanName = GeneratorUtils.tableNameToClassName(tableColumns.getTableName(), "t");
        String packagePath = GeneratorUtils.createPackage(GeneratorConstants.SERVICE_PACKAGE);
        String fileContent = GeneratorUtils.readTemplate(GeneratorConstants.SERVICE_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.CREATE_DATE,
                DateFormatUtils.format(Calendar.getInstance(), DatePatternEnum.DATE.getValue()))
                .replace(TemplateConstants.AUTHOR, GeneratorConstants.AUTHOR)
                .replace(TemplateConstants.BEAN_NAME, beanName);
        GeneratorUtils.writeFile(fileContent, packagePath, beanName + GeneratorConstants.SERVICE_JAVA_SUFFIX);
    }

    /**
     * 生成所有Service接口
     * @param tableColumnsList 所有表数据
     */
    public static void generateServices(List<TableColumns> tableColumnsList) {
        for (TableColumns tableColumns : tableColumnsList) {
            generateService(tableColumns);
        }
    }

    /**
     * 生成Service接口实现类
     * @param tableColumns 表数据
     */
    public static void generateServiceImpl(TableColumns tableColumns) {
        String beanName = GeneratorUtils.tableNameToClassName(tableColumns.getTableName(), "t");
        String packagePath = GeneratorUtils.createPackage(GeneratorConstants.SERVICE_IMPL_PACKAGE);
        String fileContent = GeneratorUtils.readTemplate(GeneratorConstants.SERVICE_IMPL_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.CREATE_DATE,
                DateFormatUtils.format(Calendar.getInstance(), DatePatternEnum.DATE.getValue()))
                .replace(TemplateConstants.AUTHOR, GeneratorConstants.AUTHOR)
                .replace(TemplateConstants.BEAN_NAME, beanName)
        .replace(TemplateConstants.BEAN_NAME_LOWER_CASE, StringUtils.uncapitalize(beanName));
        GeneratorUtils.writeFile(fileContent, packagePath, beanName + GeneratorConstants.SERVICE_IMPL_JAVA_SUFFIX);
    }

    /**
     * 生成所有Service接口实现类
     * @param tableColumnsList 所有实现类
     */
    public static void generateServiceImpls(List<TableColumns> tableColumnsList) {
        for (TableColumns tableColumns : tableColumnsList) {
            generateServiceImpl(tableColumns);
        }
    }

}
