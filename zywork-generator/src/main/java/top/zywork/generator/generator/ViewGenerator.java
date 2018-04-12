package top.zywork.generator.generator;

import org.apache.commons.lang3.StringUtils;
import top.zywork.generator.bean.ColumnDetail;
import top.zywork.generator.bean.Generator;
import top.zywork.generator.bean.TableColumns;
import top.zywork.generator.common.GeneratorUtils;
import top.zywork.generator.common.PropertyUtils;
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
    private static final String SEARCH_FORM_FIELD_SUFFIX = "Search";

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
        String[] rowDetailDateIndex = generateTableRowDetail(generator, tableColumns);
        fileContent = fileContent.replace(TemplateConstants.VIEW_TABLE_FIELDS, generateTableFields(generator, tableColumns))
                .replace(TemplateConstants.VIEW_REMOVE_URL, "/" + moduleName + "/remove/")
                .replace(TemplateConstants.VIEW_TABLE_URL, "/" + moduleName + "/pager-cond")
                .replace(TemplateConstants.VIEW_ID_FIELD, "id")
                .replace(TemplateConstants.VIEW_ROW_DETAIL_TITLES, rowDetailDateIndex[0])
                .replace(TemplateConstants.VIEW_ROW_DATE_INDEX, rowDetailDateIndex[1]);
        GeneratorUtils.writeFile(fileContent, saveDir, beanName + ".js");
    }

    /**
     * 生成关联表的JS文件
     * @param beanName 实体类名称
     * @param mappingUrl url映射
     * @param generator Generator实例
     * @param columns 所选表字段信息
     */
    public static void generateJoinJs(String beanName, String mappingUrl, Generator generator, String primaryTable, String[] columns, List<TableColumns> tableColumnsList) {
        String saveDir = GeneratorUtils.createViewResDir(generator, generator.getJsFileDir() + beanName);
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VIEW_JS_TEMPLATE);
        String[] rowDetailDateIndex = generateJoinTableRowDetail(generator, primaryTable, columns, tableColumnsList);
        fileContent = fileContent.replace(TemplateConstants.VIEW_TABLE_FIELDS, generateJoinTableFields(generator, primaryTable, columns, tableColumnsList))
                .replace(TemplateConstants.VIEW_REMOVE_URL, "/" + mappingUrl + "/remove/")
                .replace(TemplateConstants.VIEW_TABLE_URL, "/" + mappingUrl + "/pager-cond")
                .replace(TemplateConstants.VIEW_ID_FIELD, StringUtils.uncapitalize(GeneratorUtils.tableNameToClassName(primaryTable,
                        generator.getTablePrefix())) + StringUtils.capitalize(PropertyUtils.columnToProperty("id")))
                .replace(TemplateConstants.VIEW_ROW_DETAIL_TITLES, rowDetailDateIndex[0])
                .replace(TemplateConstants.VIEW_ROW_DATE_INDEX, rowDetailDateIndex[1]);
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
                "\twidth: '60px',\n" +
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
                        .append("\talign: 'center',\n")
                        .append("\twidth: '120px'");
                if (columnDetail.getJavaTypeName().equals("Date")) {
                    columnFields.append(",\n")
                            .append("\tformatter: formatDate");
                }
                columnFields.append("\n}");
            }
        }
        return columnFields.toString();
    }

    /**
     * 生成关联表表格中的列columns信息
     * @param generator Generator实例
     * @param primaryTable 主表名称
     * @param columns 所选表字段信息
     * @param tableColumnsList 所有表的字段信息
     * @return
     */
    private static String generateJoinTableFields(Generator generator, String primaryTable, String[] columns, List<TableColumns> tableColumnsList) {
        StringBuilder columnFields = new StringBuilder();
        String id = StringUtils.uncapitalize(GeneratorUtils.tableNameToClassName(primaryTable,
                generator.getTablePrefix())) + StringUtils.capitalize(PropertyUtils.columnToProperty("id"));
        columnFields.append("{\n" +
                "\tfield: '_checkbox',\n" +
                "\tcheckbox: true\n" +
                "},\n" +
                "{\n" +
                "\tfield: '" + id +
                "',\n" +
                "\talign: 'center',\n" +
                "\tvisible: false\n" +
                "},\n" +
                "{\n" +
                "\ttitle: '序号',\n" +
                "\tfield: '_number',\n" +
                "\talign: 'center',\n" +
                "\twidth: '60px',\n" +
                "\tformatter: formatTableIndex\n" +
                "}");
        for (String column : columns) {
            String[] tableNameAndColumn = column.split("-");
            String tableName = tableNameAndColumn[0];
            String columnName = tableNameAndColumn[1];
            for (TableColumns tableColumns : tableColumnsList) {
                if (tableName.equals(tableColumns.getTableName())) {
                    List<ColumnDetail> columnDetailList = tableColumns.getColumns();
                    for (ColumnDetail columnDetail : columnDetailList) {
                        if (columnName.equals(columnDetail.getName())) {
                            String field = StringUtils.uncapitalize(GeneratorUtils.tableNameToClassName(tableName, generator.getTablePrefix()))
                                    + StringUtils.capitalize(PropertyUtils.columnToProperty(columnName));
                            if (!field.equals(id)) {
                                columnFields.append(",\n{\n")
                                        .append("\ttitle: '")
                                        .append(columnDetail.getComment())
                                        .append("',\n")
                                        .append("\tfield: '")
                                        .append(field)
                                        .append("',\n")
                                        .append("\talign: 'center',\n")
                                        .append("\twidth: '120px'");
                                if (columnDetail.getJavaTypeName().equals("Date")) {
                                    columnFields.append(",\n")
                                            .append("\tformatter: formatDate");
                                }
                                columnFields.append("\n}");
                            }
                        }
                    }
                }
            }
        }
        return columnFields.toString();
    }

    /**
     * 生成表格中行详情的标题
     * @param generator Generator实例
     * @param tableColumns 所选表的字段信息
     * @return
     */
    private static String[] generateTableRowDetail(Generator generator, TableColumns tableColumns) {
        String[] rowDetailAndDateIndex = new String[2];
        List<ColumnDetail> columnDetailList = tableColumns.getColumns();
        StringBuilder rowDetailTitles = new StringBuilder();
        StringBuilder dateIndex = new StringBuilder();
        for (int i = 0, len = columnDetailList.size(); i < len; i++) {
            ColumnDetail columnDetail = columnDetailList.get(i);
            rowDetailTitles.append(",")
                    .append("'")
                    .append(columnDetail.getComment())
                    .append("'");
            if (columnDetail.getJavaTypeName().equals("Date")) {
                dateIndex.append(",").append(i);
            }
        }
        rowDetailAndDateIndex[0] = rowDetailTitles.toString().substring(1);
        rowDetailAndDateIndex[1] = dateIndex.toString().contains(",") ? dateIndex.toString().substring(1) : "-1";
        return rowDetailAndDateIndex;
    }

    /**
     * 生成关联表格中行详情的标题
     * @param generator Generator实例
     * @param primaryTable 主表名称
     * @param columns 所选表字段信息
     * @param tableColumnsList 所有表的字段信息
     * @return
     */
    private static String[] generateJoinTableRowDetail(Generator generator, String primaryTable, String[] columns, List<TableColumns> tableColumnsList) {
        String[] rowDetailAndDateIndex = new String[2];
        StringBuilder rowDetailTitles = new StringBuilder();
        StringBuilder dateIndex = new StringBuilder();
        for (int i = 0, len = columns.length; i < len; i++) {
            String column = columns[i];
            String[] tableNameAndColumn = column.split("-");
            String tableName = tableNameAndColumn[0];
            String columnName = tableNameAndColumn[1];
            for (TableColumns tableColumns : tableColumnsList) {
                if (tableName.equals(tableColumns.getTableName())) {
                    List<ColumnDetail> columnDetailList = tableColumns.getColumns();
                    for (ColumnDetail columnDetail : columnDetailList) {
                        if (columnName.equals(columnDetail.getName())) {
                            rowDetailTitles.append(",")
                                    .append("'")
                                    .append(columnDetail.getComment())
                                    .append("'");
                            if (columnDetail.getJavaTypeName().equals("Date")) {
                                dateIndex.append(",").append(i);
                            }
                        }
                    }
                }
            }
        }
        rowDetailAndDateIndex[0] = rowDetailTitles.toString().substring(1);
        rowDetailAndDateIndex[1] = dateIndex.toString().contains(",") ? dateIndex.toString().substring(1) : "-1";
        return rowDetailAndDateIndex;
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
                .replace(TemplateConstants.VIEW_SEARCH_FORM_FIELDS, generateSearchFormFields(generator, tableColumns))
                .replace(TemplateConstants.VIEW_ADD_FORM_FIELDS, generateFormFields(generator, tableColumns, ADD_FORM_FIELD_SUFFIX))
                .replace(TemplateConstants.VIEW_SAVE_URL, "/" + moduleName + "/save")
                .replace(TemplateConstants.VIEW_TABLE_URL, "/" + moduleName + "/pager-cond")
                .replace(TemplateConstants.VIEW_EDIT_FORM_FIELDS, generateFormFields(generator, tableColumns, EDIT_FORM_FIELD_SUFFIX))
                .replace(TemplateConstants.VIEW_EDIT_URL, "/" + moduleName + "/update")
                .replace(TemplateConstants.VIEW_ID_FIELD, "id")
                .replace(TemplateConstants.VIEW_JS_FILE_NAME, beanName + "/" + beanName + ".js");
        GeneratorUtils.writeFile(fileContent, saveDir, beanName + ".jsp");
    }

    /**
     * 生成关联表对应的视图
     * @param beanName 实体类名称
     * @param mappingUrl url映射
     * @param generator Generator实例
     * @param primaryTable 主表名称
     * @param columns 所选表字段信息
     */
    public static void generateJoinView(String beanName, String mappingUrl, Generator generator, String primaryTable, String[] columns, List<TableColumns> tableColumnsList) {
        String saveDir = GeneratorUtils.createViewDir(generator, beanName);
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VIEW_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.VIEW_PAGE_TITLE, beanName)
                .replace(TemplateConstants.VIEW_SEARCH_FORM_FIELDS, generateJoinSearchFormFields(generator, primaryTable, columns, tableColumnsList))
                .replace(TemplateConstants.VIEW_ADD_FORM_FIELDS, generateJoinFormFields(generator, primaryTable, columns, tableColumnsList, ADD_FORM_FIELD_SUFFIX))
                .replace(TemplateConstants.VIEW_SAVE_URL, "/" + mappingUrl + "/save")
                .replace(TemplateConstants.VIEW_TABLE_URL, "/" + mappingUrl + "/pager-cond")
                .replace(TemplateConstants.VIEW_EDIT_FORM_FIELDS, generateJoinFormFields(generator, primaryTable, columns, tableColumnsList, EDIT_FORM_FIELD_SUFFIX))
                .replace(TemplateConstants.VIEW_EDIT_URL, "/" + mappingUrl + "/update")
                .replace(TemplateConstants.VIEW_ID_FIELD, StringUtils.uncapitalize(GeneratorUtils.tableNameToClassName(primaryTable,
                        generator.getTablePrefix())) + StringUtils.capitalize(PropertyUtils.columnToProperty("id")))
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
        String textContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VIEW_TEXT_TEMPLATE);
        String dateContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VIEW_DATE_TEMPLATE);
        return generateFields(textContent, dateContent, generator, tableColumns, fieldSuffix);
    }

    /**
     * 生成视图中搜索表单字段信息
     * @param generator Generator实例
     * @param tableColumns 表字段信息
     * @return
     */
    private static String generateSearchFormFields(Generator generator, TableColumns tableColumns) {
        String textContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VIEW_SEARCH_TEXT_TEMPLATE);
        String dateContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VIEW_SEARCH_DATE_TEMPLATE);
        return generateFields(textContent, dateContent, generator, tableColumns, SEARCH_FORM_FIELD_SUFFIX);
    }

    /**
     * 生成表单字段信息
     * @param textFileContent 文本框模板文件内容
     * @param dateFileContent 日期选择框模板文件内容
     * @param generator Generator实例
     * @param tableColumns 所选表字段信息
     * @param fieldSuffix 表单字段id后缀
     * @return
     */
    private static String generateFields(String textFileContent, String dateFileContent, Generator generator, TableColumns tableColumns, String fieldSuffix) {
        List<ColumnDetail> columnDetailList = tableColumns.getColumns();
        String[] exclusiveColumns = generator.getExclusiveAddEditColumns().split(",");
        StringBuilder formFields = new StringBuilder();
        for (ColumnDetail columnDetail : columnDetailList) {
            String fieldName = columnDetail.getFieldName();
            if (!fieldName.equals("id")
                    && (fieldSuffix.equals(SEARCH_FORM_FIELD_SUFFIX)
                        || !top.zywork.common.StringUtils.isInArray(exclusiveColumns, columnDetail.getName()))) {
                String title = columnDetail.getComment();
                if (columnDetail.getJavaTypeName().equals("Date")) {
                    formFields.append(dateFileContent.replace(TemplateConstants.VIEW_FIELD_NAME_EN, fieldName + "Start")
                            .replace(TemplateConstants.VIEW_ID_FIELD_NAME_EN, fieldName + "Start" + fieldSuffix)
                            .replace(TemplateConstants.VIEW_FIELD_NAME_CN, title + "(开始)")
                            .replace(TemplateConstants.VIEW_FIELD_PLACEHOLDER, "请选择" + title + "(开始)"))
                            .append("\n");
                    formFields.append(dateFileContent.replace(TemplateConstants.VIEW_FIELD_NAME_EN, fieldName + "End")
                            .replace(TemplateConstants.VIEW_ID_FIELD_NAME_EN, fieldName + "End" + fieldSuffix)
                            .replace(TemplateConstants.VIEW_FIELD_NAME_CN, title + "(结束)")
                            .replace(TemplateConstants.VIEW_FIELD_PLACEHOLDER, "请选择" + title + "(结束)"))
                            .append("\n");
                } else {
                    formFields.append(textFileContent.replace(TemplateConstants.VIEW_FIELD_NAME_EN, fieldName)
                            .replace(TemplateConstants.VIEW_ID_FIELD_NAME_EN, fieldName + fieldSuffix)
                            .replace(TemplateConstants.VIEW_FIELD_NAME_CN, title)
                            .replace(TemplateConstants.VIEW_FIELD_PLACEHOLDER, "请输入" + title))
                            .append("\n");
                }
            }
        }
        return formFields.toString();
    }

    /**
     * 生成关联表视图中主表的添加和修改的表单字段信息
     * @param generator Generator实例
     * @param primaryTable 主表名称
     * @param columns 所选表字段信息
     * @param fieldSuffix 表单字段id的后缀
     * @return
     */
    private static String generateJoinFormFields(Generator generator, String primaryTable, String[] columns, List<TableColumns> tableColumnsList, String fieldSuffix) {
        String textContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VIEW_TEXT_TEMPLATE);
        String dateContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VIEW_SEARCH_DATE_TEMPLATE);
        return generateJoinFields(textContent, dateContent, generator, primaryTable, columns, tableColumnsList, fieldSuffix);
    }

    /**
     * 生成关联表视图中主表的添加和修改的表单字段信息
     * @param generator Generator实例
     * @param primaryTable 主表名称
     * @param columns 所选表字段信息
     * @return
     */
    private static String generateJoinSearchFormFields(Generator generator, String primaryTable, String[] columns, List<TableColumns> tableColumnsList) {
        String textContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VIEW_SEARCH_TEXT_TEMPLATE);
        String dateContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VIEW_SEARCH_DATE_TEMPLATE);
        return generateJoinFields(textContent, dateContent, generator, primaryTable, columns, tableColumnsList, SEARCH_FORM_FIELD_SUFFIX);
    }

    /**
     * 生成关联表视图中主表的添加和修改的表单字段信息
     * @param textFileContent 文本框模板文件内容
     * @param dateFileContent 日期选择框模板文件内容
     * @param generator Generator实例
     * @param primaryTable 主表名称
     * @param columns 所选表字段信息
     * @param fieldSuffix 表单字段id的后缀
     * @return
     */
    private static String generateJoinFields(String textFileContent, String dateFileContent, Generator generator, String primaryTable, String[] columns, List<TableColumns> tableColumnsList, String fieldSuffix) {
        String id = StringUtils.uncapitalize(GeneratorUtils.tableNameToClassName(primaryTable,
                generator.getTablePrefix())) + StringUtils.capitalize(PropertyUtils.columnToProperty("id"));
        String[] exclusiveColumns = generator.getExclusiveAddEditColumns().split(",");
        StringBuilder formFields = new StringBuilder();
        for (String column : columns) {
            String[] tableNameAndColumn = column.split("-");
            String tableName = tableNameAndColumn[0];
            String columnName = tableNameAndColumn[1];
            if (tableName.equals(primaryTable)) {
                for (TableColumns tableColumns : tableColumnsList) {
                    if (tableName.equals(tableColumns.getTableName())) {
                        List<ColumnDetail> columnDetailList = tableColumns.getColumns();
                        for (ColumnDetail columnDetail : columnDetailList) {
                            if (columnName.equals(columnDetail.getName())) {
                                String field = StringUtils.uncapitalize(GeneratorUtils.tableNameToClassName(primaryTable, generator.getTablePrefix()))
                                        + StringUtils.capitalize(PropertyUtils.columnToProperty(columnName));
                                if (!id.equals(field)
                                        && (fieldSuffix.equals(SEARCH_FORM_FIELD_SUFFIX)
                                            || !top.zywork.common.StringUtils.isInArray(exclusiveColumns, columnDetail.getName()))) {
                                    String title = columnDetail.getComment();
                                    if (columnDetail.getJavaTypeName().equals("Date")) {
                                        formFields.append(dateFileContent.replace(TemplateConstants.VIEW_FIELD_NAME_EN, field + "Start")
                                                .replace(TemplateConstants.VIEW_ID_FIELD_NAME_EN, field + "Start" + fieldSuffix)
                                                .replace(TemplateConstants.VIEW_FIELD_NAME_CN, title + "(开始)")
                                                .replace(TemplateConstants.VIEW_FIELD_PLACEHOLDER, "请选择" + title + "(开始)"))
                                                .append("\n");
                                        formFields.append(dateFileContent.replace(TemplateConstants.VIEW_FIELD_NAME_EN, field + "End")
                                                .replace(TemplateConstants.VIEW_ID_FIELD_NAME_EN, field + "End" + fieldSuffix)
                                                .replace(TemplateConstants.VIEW_FIELD_NAME_CN, title + "(结束)")
                                                .replace(TemplateConstants.VIEW_FIELD_PLACEHOLDER, "请选择" + title + "(结束)"))
                                                .append("\n");
                                    } else {
                                        formFields.append(textFileContent.replace(TemplateConstants.VIEW_FIELD_NAME_EN, field)
                                                .replace(TemplateConstants.VIEW_ID_FIELD_NAME_EN, field + fieldSuffix)
                                                .replace(TemplateConstants.VIEW_FIELD_NAME_CN, title)
                                                .replace(TemplateConstants.VIEW_FIELD_PLACEHOLDER, "请输入" + title))
                                                .append("\n");
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
        return formFields.toString();
    }

}
