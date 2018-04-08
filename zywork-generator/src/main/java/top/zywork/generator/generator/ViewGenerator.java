package top.zywork.generator.generator;

import top.zywork.generator.bean.ColumnDetail;
import top.zywork.generator.bean.Generator;
import top.zywork.generator.bean.TableColumns;
import top.zywork.generator.common.GeneratorUtils;
import top.zywork.generator.constant.TemplateConstants;

import java.util.List;

/**
 * View视图自动生成代码封装类<br/>
 *
 * 创建于2018-03-12<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
public class ViewGenerator {

    private static final String ADD_FORM_FIELD_SUFFIX = "";
    private static final String EDIT_FORM_FIELD_SUFFIX = "Edit";

    /**
     * 生成单表的JS文件
     * @param generator Generator实例
     * @param tableColumns 表字段信息
     */
    public static void generateJs(Generator generator, TableColumns tableColumns) {
        String beanName = GeneratorUtils.tableNameToClassName(tableColumns.getTableName(), generator.getTablePrefix());
        String saveDir = GeneratorUtils.createViewResDir(generator, generator.getJsFileDir() + beanName);
        String moduleName = GeneratorUtils.getModuleName(tableColumns.getTableName(), generator.getTablePrefix());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VIEW_JS_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.VIEW_TABLE_FIELDS, generateTableFields(generator, tableColumns))
                .replace(TemplateConstants.VIEW_REMOVE_URL, "/" + moduleName + "/remove/")
                .replace(TemplateConstants.VIEW_TABLE_URL, "/" + moduleName + "/pager-cond");
        GeneratorUtils.writeFile(fileContent, saveDir, beanName + ".js");
    }

    /**
     * 生成所有表的JS文件
     * @param generator Generator实例
     * @param tableColumnsList 所有表的字段信息列表
     */
    public static void generateJss(Generator generator, List<TableColumns> tableColumnsList) {
        for (TableColumns tableColumns : tableColumnsList) {
            generateJs(generator, tableColumns);
        }
    }

    /**
     * 生成表格中的列columns信息
     * @param generator Generator实例
     * @param tableColumns 表字段信息
     * @return
     */
    private static String generateTableFields(Generator generator, TableColumns tableColumns) {
        List<ColumnDetail> columnDetailList = tableColumns.getColumns();
        StringBuilder columnFields = new StringBuilder();
        columnFields.append("{\n" +
                "\tfield: '_checkbox',\n" +
                "\tcheckbox: true\n" +
                "},\n" +
                "{\n" +
                "\tfield: 'id',\n" +
                "\talign: 'center',\n" +
                "\tvisible: false\n" +
                "},\n" +
                "{\n" +
                "\ttitle: '序号',\n" +
                "\tfield: '_number',\n" +
                "\talign: 'center',\n" +
                "\tformatter: formatTableIndex\n" +
                "}");
        for (ColumnDetail columnDetail : columnDetailList) {
            String name = columnDetail.getFieldName();
            if (!name.equals("id")) {
                columnFields.append(",\n{\n")
                        .append("\ttitle: '")
                        .append(columnDetail.getComment())
                        .append("',\n")
                        .append("\tfield: '")
                        .append(columnDetail.getFieldName())
                        .append("',\n")
                        .append("\talign: 'center'\n")
                        .append("}");
            }
        }
        return columnFields.toString();
    }

    /**
     * 生成单表对应的视图
     * @param generator Generator实例
     * @param tableColumns 表字段信息
     */
    public static void generateView(Generator generator, TableColumns tableColumns) {
        String beanName = GeneratorUtils.tableNameToClassName(tableColumns.getTableName(), generator.getTablePrefix());
        String saveDir = GeneratorUtils.createViewDir(generator, beanName);
        String moduleName = GeneratorUtils.getModuleName(tableColumns.getTableName(), generator.getTablePrefix());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VIEW_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.VIEW_PAGE_TITLE, beanName)
                .replace(TemplateConstants.VIEW_ADD_FORM_FIELDS, generateFormFields(generator, tableColumns, ADD_FORM_FIELD_SUFFIX))
                .replace(TemplateConstants.VIEW_SAVE_URL, "/" + moduleName + "/save")
                .replace(TemplateConstants.VIEW_TABLE_URL, "/" + moduleName + "/pager-cond")
                .replace(TemplateConstants.VIEW_EDIT_FORM_FIELDS, generateFormFields(generator, tableColumns, EDIT_FORM_FIELD_SUFFIX))
                .replace(TemplateConstants.VIEW_EDIT_URL, "/" + moduleName + "/update")
                .replace(TemplateConstants.VIEW_JS_FILE_NAME, beanName + "/" + beanName + ".js");
        GeneratorUtils.writeFile(fileContent, saveDir, beanName + ".jsp");
    }

    /**
     * 生成所有表的视图
     * @param generator Generator实例
     * @param tableColumnsList 所有表字段信息的列表
     */
    public static void generateViews(Generator generator, List<TableColumns> tableColumnsList) {
        for (TableColumns tableColumns : tableColumnsList) {
            generateView(generator, tableColumns);
        }
    }

    /**
     * 生成视图中添加和修改的表单字段信息
     * @param generator Generator实例
     * @param tableColumns 表字段信息
     * @param fieldSuffix 表单字段id的后缀
     * @return
     */
    private static String generateFormFields(Generator generator, TableColumns tableColumns, String fieldSuffix) {
        String text = GeneratorUtils.readTemplate(generator, TemplateConstants.VIEW_TEXT_TEMPLATE);
        List<ColumnDetail> columnDetailList = tableColumns.getColumns();
        StringBuilder formFields = new StringBuilder();
        for (ColumnDetail columnDetail : columnDetailList) {
            String fieldName = columnDetail.getFieldName();
            String title = columnDetail.getComment();
            if (!fieldName.equals("id")) {
                formFields.append(text.replace(TemplateConstants.VIEW_FIELD_NAME_EN, fieldName)
                        .replace(TemplateConstants.VIEW_ID_FIELD_NAME_EN, fieldName + fieldSuffix)
                        .replace(TemplateConstants.VIEW_FIELD_NAME_CN, title)
                        .replace(TemplateConstants.VIEW_FIELD_PLACEHOLDER, "请输入" + title))
                        .append("\n");
            }
        }
        return formFields.toString();
    }

}
