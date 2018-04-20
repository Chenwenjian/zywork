package top.zywork.generator.generator;

import org.apache.commons.lang3.StringUtils;
import top.zywork.generator.bean.ColumnDetail;
import top.zywork.generator.bean.Generator;
import top.zywork.generator.bean.TableColumns;
import top.zywork.generator.common.GeneratorUtils;
import top.zywork.generator.common.PropertyUtils;
import top.zywork.generator.constant.TemplateConstants;

import java.sql.DatabaseMetaData;
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
    private static final String DETAIL_SUFFIX = "Detail";

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
                .replace(TemplateConstants.VIEW_VALIDATE_FIELDS, generateValidateFields(generator, tableColumns))
                .replace(TemplateConstants.VIEW_EDIT_MODAL_URL, "/" + moduleName + "/edit-modal")
                .replace(TemplateConstants.VIEW_DETAIL_MODAL_URL, "/" + moduleName + "/detail-modal")
                .replace(TemplateConstants.VIEW_ACTIVE_URL, "/" + moduleName + "/active")
                .replace(TemplateConstants.VIEW_REMOVE_URL, "/" + moduleName + "/remove/")
                .replace(TemplateConstants.VIEW_TABLE_URL, "/" + moduleName + "/pager-cond")
                .replace(TemplateConstants.VIEW_ID_FIELD, "id")
                .replace(TemplateConstants.VIEW_ROW_DETAIL_FIELD_TITLES, generateTableRowDetail(generator, tableColumns))
                .replace(TemplateConstants.VIEW_IS_ACTIVE_FIELD, "isActive");
        GeneratorUtils.writeFile(fileContent, saveDir, beanName + ".js");
    }

    /**
     * 生成单表的JS文件
     * @param generator Generator实例
     * @param tableColumns 表字段信息
     */
    public static void generateJsSearch(Generator generator, TableColumns tableColumns) {
        String beanName = GeneratorUtils.tableNameToClassName(tableColumns.getTableName(), generator.getTablePrefix());
        String saveDir = GeneratorUtils.createViewResDir(generator, generator.getJsFileDir() + beanName);
        String moduleName = GeneratorUtils.getModuleName(tableColumns.getTableName(), generator.getTablePrefix());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VIEW_JS_MODAL_SEARCH);
        fileContent = fileContent.replace(TemplateConstants.VIEW_MODULE_NAME, moduleName)
                .replace(TemplateConstants.VIEW_TABLE_FIELDS, generateTableFields(generator, tableColumns))
                .replace(TemplateConstants.VIEW_TABLE_URL, "/" + moduleName + "/pager-cond")
                .replace(TemplateConstants.VIEW_ID_FIELD, "id");
        GeneratorUtils.writeFile(fileContent, saveDir, beanName + generator.getJsSearchModalSuffix());
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
        fileContent = fileContent.replace(TemplateConstants.VIEW_TABLE_FIELDS, generateJoinTableFields(generator, primaryTable, columns, tableColumnsList))
                .replace(TemplateConstants.VIEW_VALIDATE_FIELDS, generateJoinValidateFields(generator, primaryTable, columns, tableColumnsList))
                .replace(TemplateConstants.VIEW_EDIT_MODAL_URL, "/" + mappingUrl + "/edit-modal")
                .replace(TemplateConstants.VIEW_DETAIL_MODAL_URL, "/" + mappingUrl + "/detail-modal")
                .replace(TemplateConstants.VIEW_ACTIVE_URL, "/" + mappingUrl + "/active")
                .replace(TemplateConstants.VIEW_REMOVE_URL, "/" + mappingUrl + "/remove/")
                .replace(TemplateConstants.VIEW_TABLE_URL, "/" + mappingUrl + "/pager-cond")
                .replace(TemplateConstants.VIEW_ID_FIELD, StringUtils.uncapitalize(GeneratorUtils.tableNameToClassName(primaryTable,
                        generator.getTablePrefix())) + StringUtils.capitalize(PropertyUtils.columnToProperty("id")))
                .replace(TemplateConstants.VIEW_ROW_DETAIL_FIELD_TITLES, generateJoinTableRowDetail(generator, primaryTable, columns, tableColumnsList))
                .replace(TemplateConstants.VIEW_IS_ACTIVE_FIELD, StringUtils.uncapitalize(GeneratorUtils.tableNameToClassName(primaryTable,
                        generator.getTablePrefix())) + "IsActive");
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
     * 生成所有表的JS文件
     * @param generator Generator实例
     * @param tableColumnsList 所有表的字段信息列表
     */
    public static void generateJssSearch(Generator generator, List<TableColumns> tableColumnsList) {
        for (TableColumns tableColumns : tableColumnsList) {
            generateJsSearch(generator, tableColumns);
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
        columnFields.append(commonField("id"));
        for (ColumnDetail columnDetail : columnDetailList) {
            String name = columnDetail.getFieldName();
            if (!name.equals("id")) {
                columnFields.append(columnField(columnDetail.getComment(), name, columnDetail.getJavaTypeName()));
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
        StringBuilder primaryColumnFields = new StringBuilder();
        String id = StringUtils.uncapitalize(GeneratorUtils.tableNameToClassName(primaryTable,
                generator.getTablePrefix())) + StringUtils.capitalize(PropertyUtils.columnToProperty("id"));
        primaryColumnFields.append(commonField(id));
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
                            String title = columnDetail.getComment();
                            String javaTypeName = columnDetail.getJavaTypeName();
                            if (!field.equals(id)) {
                                if (tableName.equals(primaryTable)) {
                                    primaryColumnFields.append(columnField(title, field, javaTypeName));
                                } else {
                                    columnFields.append(columnField(title, field, javaTypeName));
                                }
                            }
                        }
                    }
                }
            }
        }
        return primaryColumnFields.append(columnFields).toString();
    }

    private static String commonField(String id) {
        return "{\n" +
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
                "\tformatter: formatTableIndex\n" +
                "}";
    }

    private static String columnField(String title, String fieldName, String javaTypeName) {
        StringBuilder columnField = new StringBuilder();
        columnField.append(",\n{\n")
                .append("\ttitle: '")
                .append(title)
                .append("',\n")
                .append("\tfield: '")
                .append(fieldName)
                .append("',\n")
                .append("\talign: 'center',\n")
                .append("\tsortable: true");
        if (javaTypeName.equals("Date")) {
            columnField.append(",\n")
                    .append("\tformatter: formatDate");
        }
        columnField.append("\n}");
        return columnField.toString();
    }

    /**
     * 生成表格中行详情的标题
     * @param generator Generator实例
     * @param tableColumns 所选表的字段信息
     * @return
     */
    private static String generateTableRowDetail(Generator generator, TableColumns tableColumns) {
        List<ColumnDetail> columnDetailList = tableColumns.getColumns();
        StringBuilder rowDetailFieldTitles = new StringBuilder();
        for (int i = 0, len = columnDetailList.size(); i < len; i++) {
            ColumnDetail columnDetail = columnDetailList.get(i);
            rowDetailFieldTitles.append(rowDetailFieldTitle(columnDetail.getComment(), columnDetail.getFieldName(), columnDetail.getJavaTypeName()));
        }
        return rowDetailFieldTitles.toString().substring(1);
    }

    /**
     * 生成关联表格中行详情的标题
     * @param generator Generator实例
     * @param primaryTable 主表名称
     * @param columns 所选表字段信息
     * @param tableColumnsList 所有表的字段信息
     * @return
     */
    private static String generateJoinTableRowDetail(Generator generator, String primaryTable, String[] columns, List<TableColumns> tableColumnsList) {
        StringBuilder rowDetailTitles = new StringBuilder();
        StringBuilder primaryRowDetailTitles = new StringBuilder();
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
                            String field = StringUtils.uncapitalize(GeneratorUtils.tableNameToClassName(tableName, generator.getTablePrefix()))
                                    + StringUtils.capitalize(PropertyUtils.columnToProperty(columnName));
                            String title = columnDetail.getComment();
                            String javaTypeName = columnDetail.getJavaTypeName();
                            if (tableName.equals(primaryTable)) {
                                primaryRowDetailTitles.append(rowDetailFieldTitle(title, field, javaTypeName));
                            } else {
                                rowDetailTitles.append(rowDetailFieldTitle(title, field, javaTypeName));
                            }
                        }
                    }
                }
            }
        }
        return primaryRowDetailTitles.append(rowDetailTitles).toString().substring(1);
    }

    private static String rowDetailFieldTitle(String title, String field, String javaTypeName) {
        StringBuilder rowDetailFieldTitle = new StringBuilder();
        if (javaTypeName.equals("Date")) {
            rowDetailFieldTitle.append(",'").append(field).append("-").append("date':");
        } else {
            rowDetailFieldTitle.append(",'").append(field).append("':");
        }
        rowDetailFieldTitle.append("'")
                .append(title)
                .append("'");
        return rowDetailFieldTitle.toString();
    }

    /**
     * 生成添加和修改表单验证
     * @param generator Generator实例
     * @param tableColumns 选中的表字段列表
     * @return
     */
    public static String generateValidateFields(Generator generator, TableColumns tableColumns) {
        StringBuilder validateFields = new StringBuilder();
        List<ColumnDetail> columnDetailList = tableColumns.getColumns();
        for (ColumnDetail columnDetail : columnDetailList) {
            String fieldName = columnDetail.getFieldName();
            if (!fieldName.equals("id")) {
                validateFields.append(validateField(fieldName, columnDetail.getComment(), columnDetail.getName(),
                        generator.getExclusiveAddEditColumns().split(","), columnDetail.getColumnSize(),
                        columnDetail.getNullable(), columnDetail.getJavaTypeName()));
            }
        }
        return validateFields.toString().substring(1);
    }

    /**
     * 生成关联表的添加和修改表单验证
     * @param generator Generator实例
     * @param primaryTable 主表名称
     * @param columns 所选表的所有字段
     * @param tableColumnsList 所有表字段列表
     * @return
     */
    public static String generateJoinValidateFields(Generator generator, String primaryTable, String[] columns, List<TableColumns> tableColumnsList) {
        StringBuilder validateFields = new StringBuilder();
        String id = StringUtils.uncapitalize(GeneratorUtils.tableNameToClassName(primaryTable,
                generator.getTablePrefix())) + StringUtils.capitalize(PropertyUtils.columnToProperty("id"));
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
                            String title = columnDetail.getComment();
                            String javaTypeName = columnDetail.getJavaTypeName();
                            if (!field.equals(id)) {
                                validateFields.append(validateField(field, title, columnName, generator.getExclusiveAddEditColumns().split(","),
                                        columnDetail.getColumnSize(), columnDetail.getNullable(), javaTypeName));
                            }
                        }
                    }
                }
            }
        }
        return validateFields.toString().substring(1);
    }

    private static String validateField(String field, String title, String column,
                                        String[] exclusiveColumns, int columnSize, int nullable, String javaType) {
        StringBuilder validateField = new StringBuilder();
        if (!top.zywork.common.StringUtils.isInArray(exclusiveColumns, column)) {
            validateField.append(",\n")
                    .append(field)
                    .append(": {\n")
                    .append("\tvalidators: {\n");
            if (nullable == DatabaseMetaData.columnNoNulls) {
                validateField.append("\t\tnotEmpty: {\n")
                        .append("\t\t\tmessage: '")
                        .append(title)
                        .append("是必须项'")
                        .append("\n\t\t},");
            }
            if (javaType.equals("String") && nullable == DatabaseMetaData.columnNoNulls) {
                validateField.append("\n\t\tstringLength: {\n")
                        .append("\t\t\tmin: 1,\n")
                        .append("\t\t\tmax: ")
                        .append(columnSize / 2)
                        .append(",\n")
                        .append("\t\t\tmessage: '")
                        .append("必须是1-")
                        .append(columnSize / 2)
                        .append("个中文字符'\n")
                        .append("\t\t},");
            }
            if (javaType.equals("String") && nullable == DatabaseMetaData.columnNullable) {
                validateField.append("\n\t\tstringLength: {\n")
                        .append("\t\t\tmin: 0,\n")
                        .append("\t\t\tmax: ")
                        .append(columnSize / 2)
                        .append(",\n")
                        .append("\t\t\tmessage: '")
                        .append("必须小于")
                        .append(columnSize / 2)
                        .append("个中文字符'\n")
                        .append("\t\t},");
            }
        }
        validateField = !validateField.toString().equals("") ?
                new StringBuilder(validateField.substring(0, validateField.length() - 1)).append("\n\t}\n}") : new StringBuilder(" ");
        return validateField.toString();
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
                .replace(TemplateConstants.VIEW_ADD_MODAL_URL, "/" + moduleName + "/add-modal")
                .replace(TemplateConstants.VIEW_ADD_FORM_FIELDS, generateFormFields(generator, tableColumns, ADD_FORM_FIELD_SUFFIX))
                .replace(TemplateConstants.VIEW_SAVE_URL, "/" + moduleName + "/save")
                .replace(TemplateConstants.VIEW_TABLE_URL, "/" + moduleName + "/pager-cond")
                .replace(TemplateConstants.VIEW_EDIT_FORM_FIELDS, generateFormFields(generator, tableColumns, EDIT_FORM_FIELD_SUFFIX))
                .replace(TemplateConstants.VIEW_EDIT_URL, "/" + moduleName + "/update")
                .replace(TemplateConstants.VIEW_BATCH_REMOVE_URL, "/" + moduleName + "/batch-remove")
                .replace(TemplateConstants.VIEW_ID_FIELD, "id")
                .replace(TemplateConstants.VIEW_JS_FILE_NAME, beanName + "/" + beanName + ".js");
        GeneratorUtils.writeFile(fileContent, saveDir, beanName + ".jsp");
    }

    /**
     * 生成单表对应的视图
     * @param generator Generator实例
     * @param tableColumns 表字段信息
     */
    public static void generateViewAdd(Generator generator, TableColumns tableColumns) {
        String beanName = GeneratorUtils.tableNameToClassName(tableColumns.getTableName(), generator.getTablePrefix());
        String saveDir = GeneratorUtils.createViewDir(generator, beanName);
        String moduleName = GeneratorUtils.getModuleName(tableColumns.getTableName(), generator.getTablePrefix());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VIEW_MODAL_ADD);
        fileContent = fileContent.replace(TemplateConstants.VIEW_ADD_FORM_FIELDS, generateFormFields(generator, tableColumns, ADD_FORM_FIELD_SUFFIX))
                .replace(TemplateConstants.VIEW_SAVE_URL, "/" + moduleName + "/save")
                .replace(TemplateConstants.VIEW_TABLE_URL, "/" + moduleName + "/pager-cond");
        GeneratorUtils.writeFile(fileContent, saveDir, beanName + generator.getViewAddModalSuffix());
    }

    /**
     * 生成单表对应的视图
     * @param generator Generator实例
     * @param tableColumns 表字段信息
     */
    public static void generateViewEdit(Generator generator, TableColumns tableColumns) {
        String beanName = GeneratorUtils.tableNameToClassName(tableColumns.getTableName(), generator.getTablePrefix());
        String saveDir = GeneratorUtils.createViewDir(generator, beanName);
        String moduleName = GeneratorUtils.getModuleName(tableColumns.getTableName(), generator.getTablePrefix());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VIEW_MODAL_EDIT);
        fileContent = fileContent.replace(TemplateConstants.VIEW_EDIT_FORM_FIELDS, generateFormFields(generator, tableColumns, ADD_FORM_FIELD_SUFFIX))
                .replace(TemplateConstants.VIEW_EDIT_URL, "/" + moduleName + "/update")
                .replace(TemplateConstants.VIEW_TABLE_URL, "/" + moduleName + "/pager-cond")
                .replace(TemplateConstants.VIEW_ID_FIELD, "id");
        GeneratorUtils.writeFile(fileContent, saveDir, beanName + generator.getViewEditModalSuffix());
    }

    /**
     * 生成单表对应的视图
     * @param generator Generator实例
     * @param tableColumns 表字段信息
     */
    public static void generateViewDetail(Generator generator, TableColumns tableColumns) {
        String beanName = GeneratorUtils.tableNameToClassName(tableColumns.getTableName(), generator.getTablePrefix());
        String saveDir = GeneratorUtils.createViewDir(generator, beanName);
        String moduleName = GeneratorUtils.getModuleName(tableColumns.getTableName(), generator.getTablePrefix());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VIEW_MODAL_DETAIL);
        fileContent = fileContent.replace(TemplateConstants.VIEW_DETAILS, generateDetails(generator, tableColumns));
        GeneratorUtils.writeFile(fileContent, saveDir, beanName + generator.getViewDetailModalSuffix());
    }

    /**
     * 生成表格中行详情
     * @param generator Generator实例
     * @param tableColumns 所选表的字段信息
     * @return
     */
    private static String generateDetails(Generator generator, TableColumns tableColumns) {
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VIEW_TEXT_DETAIL_TEMPLATE);
        List<ColumnDetail> columnDetailList = tableColumns.getColumns();
        StringBuilder details = new StringBuilder();
        for (int i = 0, len = columnDetailList.size(); i < len; i++) {
            ColumnDetail columnDetail = columnDetailList.get(i);
            details.append(fileContent.replace(TemplateConstants.VIEW_FIELD_NAME_EN, columnDetail.getFieldName() + DETAIL_SUFFIX)
                    .replace(TemplateConstants.VIEW_FIELD_NAME_CN, columnDetail.getComment()))
                    .append("\n");
        }
        return details.toString();
    }

    /**
     * 生成单表对应的视图
     * @param generator Generator实例
     * @param tableColumns 表字段信息
     */
    public static void generateViewSearch(Generator generator, TableColumns tableColumns) {
        String beanName = GeneratorUtils.tableNameToClassName(tableColumns.getTableName(), generator.getTablePrefix());
        String saveDir = GeneratorUtils.createViewDir(generator, beanName);
        String moduleName = GeneratorUtils.getModuleName(tableColumns.getTableName(), generator.getTablePrefix());
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VIEW_MODAL_SEARCH);
        fileContent = fileContent.replace(TemplateConstants.VIEW_SEARCH_FORM_FIELDS, generateSearchFormFields(generator, tableColumns))
                .replace(TemplateConstants.VIEW_TABLE_URL, "/" + moduleName + "/pager-cond")
                .replace(TemplateConstants.VIEW_ID_FIELD, "id")
                .replace(TemplateConstants.VIEW_MODULE_NAME, moduleName);
        GeneratorUtils.writeFile(fileContent, saveDir, beanName + generator.getViewSearchModalSuffix());
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
                .replace(TemplateConstants.VIEW_ADD_MODAL_URL, "/" + mappingUrl + "/add-modal")
                .replace(TemplateConstants.VIEW_ADD_FORM_FIELDS, generateJoinFormFields(generator, primaryTable, columns, tableColumnsList, ADD_FORM_FIELD_SUFFIX))
                .replace(TemplateConstants.VIEW_SAVE_URL, "/" + mappingUrl + "/save")
                .replace(TemplateConstants.VIEW_TABLE_URL, "/" + mappingUrl + "/pager-cond")
                .replace(TemplateConstants.VIEW_EDIT_FORM_FIELDS, generateJoinFormFields(generator, primaryTable, columns, tableColumnsList, EDIT_FORM_FIELD_SUFFIX))
                .replace(TemplateConstants.VIEW_EDIT_URL, "/" + mappingUrl + "/update")
                .replace(TemplateConstants.VIEW_BATCH_REMOVE_URL, "/" + mappingUrl + "/batch-remove")
                .replace(TemplateConstants.VIEW_ID_FIELD, StringUtils.uncapitalize(GeneratorUtils.tableNameToClassName(primaryTable,
                        generator.getTablePrefix())) + StringUtils.capitalize(PropertyUtils.columnToProperty("id")))
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
    public static void generateJoinViewAdd(String beanName, String mappingUrl, Generator generator, String primaryTable, String[] columns, List<TableColumns> tableColumnsList) {
        String saveDir = GeneratorUtils.createViewDir(generator, beanName);
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VIEW_MODAL_ADD);
        fileContent = fileContent.replace(TemplateConstants.VIEW_ADD_FORM_FIELDS, generateJoinFormFields(generator, primaryTable, columns, tableColumnsList, ADD_FORM_FIELD_SUFFIX))
                .replace(TemplateConstants.VIEW_SAVE_URL, "/" + mappingUrl + "/save")
                .replace(TemplateConstants.VIEW_TABLE_URL, "/" + mappingUrl + "/pager-cond");
        GeneratorUtils.writeFile(fileContent, saveDir, beanName + generator.getViewAddModalSuffix());
    }

    /**
     * 生成关联表对应的视图
     * @param beanName 实体类名称
     * @param mappingUrl url映射
     * @param generator Generator实例
     * @param primaryTable 主表名称
     * @param columns 所选表字段信息
     */
    public static void generateJoinViewEdit(String beanName, String mappingUrl, Generator generator, String primaryTable, String[] columns, List<TableColumns> tableColumnsList) {
        String saveDir = GeneratorUtils.createViewDir(generator, beanName);
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VIEW_MODAL_EDIT);
        fileContent = fileContent.replace(TemplateConstants.VIEW_TABLE_URL, "/" + mappingUrl + "/pager-cond")
                .replace(TemplateConstants.VIEW_EDIT_FORM_FIELDS, generateJoinFormFields(generator, primaryTable, columns, tableColumnsList, EDIT_FORM_FIELD_SUFFIX))
                .replace(TemplateConstants.VIEW_EDIT_URL, "/" + mappingUrl + "/update")
                .replace(TemplateConstants.VIEW_ID_FIELD, StringUtils.uncapitalize(GeneratorUtils.tableNameToClassName(primaryTable,
                        generator.getTablePrefix())) + StringUtils.capitalize(PropertyUtils.columnToProperty("id")));
        GeneratorUtils.writeFile(fileContent, saveDir, beanName + generator.getViewEditModalSuffix());
    }

    /**
     * 生成关联表对应的视图
     * @param beanName 实体类名称
     * @param mappingUrl url映射
     * @param generator Generator实例
     * @param primaryTable 主表名称
     * @param columns 所选表字段信息
     */
    public static void generateJoinViewDetail(String beanName, String mappingUrl, Generator generator, String primaryTable, String[] columns, List<TableColumns> tableColumnsList) {
        String saveDir = GeneratorUtils.createViewDir(generator, beanName);
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VIEW_MODAL_DETAIL);
        fileContent = fileContent.replace(TemplateConstants.VIEW_DETAILS, generateJoinDetails(generator, primaryTable, columns, tableColumnsList));
        GeneratorUtils.writeFile(fileContent, saveDir, beanName + generator.getViewDetailModalSuffix());
    }

    /**
     * 生成关联表中行详情
     * @param generator Generator实例
     * @param primaryTable 主表名称
     * @param columns 所选表字段信息
     * @param tableColumnsList 所有表的字段信息
     * @return
     */
    private static String generateJoinDetails(Generator generator, String primaryTable, String[] columns, List<TableColumns> tableColumnsList) {
        String fileContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VIEW_TEXT_DETAIL_TEMPLATE);
        StringBuilder rowDetails = new StringBuilder();
        StringBuilder primaryDetails = new StringBuilder();
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
                            String field = StringUtils.uncapitalize(GeneratorUtils.tableNameToClassName(tableName, generator.getTablePrefix()))
                                    + StringUtils.capitalize(PropertyUtils.columnToProperty(columnName));
                            String title = columnDetail.getComment();
                            if (tableName.equals(primaryTable)) {
                                primaryDetails.append(fileContent.replace(TemplateConstants.VIEW_FIELD_NAME_EN, field + DETAIL_SUFFIX)
                                        .replace(TemplateConstants.VIEW_FIELD_NAME_CN, title))
                                        .append("\n");
                            } else {
                                rowDetails.append(fileContent.replace(TemplateConstants.VIEW_FIELD_NAME_EN, field + DETAIL_SUFFIX)
                                        .replace(TemplateConstants.VIEW_FIELD_NAME_CN, title))
                                        .append("\n");
                            }
                        }
                    }
                }
            }
        }
        return primaryDetails.append(rowDetails).toString();
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
     * 生成所有表的视图
     * @param generator Generator实例
     * @param tableColumnsList 所有表字段信息的列表
     */
    public static void generateViewsAdd(Generator generator, List<TableColumns> tableColumnsList) {
        for (TableColumns tableColumns : tableColumnsList) {
            generateViewAdd(generator, tableColumns);
        }
    }

    /**
     * 生成所有表的视图
     * @param generator Generator实例
     * @param tableColumnsList 所有表字段信息的列表
     */
    public static void generateViewsEdit(Generator generator, List<TableColumns> tableColumnsList) {
        for (TableColumns tableColumns : tableColumnsList) {
            generateViewEdit(generator, tableColumns);
        }
    }

    /**
     * 生成所有表的视图
     * @param generator Generator实例
     * @param tableColumnsList 所有表字段信息的列表
     */
    public static void generateViewsDetail(Generator generator, List<TableColumns> tableColumnsList) {
        for (TableColumns tableColumns : tableColumnsList) {
            generateViewDetail(generator, tableColumns);
        }
    }

    /**
     * 生成所有表的视图
     * @param generator Generator实例
     * @param tableColumnsList 所有表字段信息的列表
     */
    public static void generateViewsSearch(Generator generator, List<TableColumns> tableColumnsList) {
        for (TableColumns tableColumns : tableColumnsList) {
            generateViewSearch(generator, tableColumns);
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
            if (!fieldName.equals("id")) {
                String title = columnDetail.getComment();
                String javaTypeName = columnDetail.getJavaTypeName();
                formField(formFields, textFileContent, dateFileContent, fieldName, fieldSuffix, title, columnDetail.getName(), javaTypeName, exclusiveColumns);
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
        String dateContent = GeneratorUtils.readTemplate(generator, TemplateConstants.VIEW_DATE_TEMPLATE);
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
                                if (!id.equals(field)) {
                                    String title = columnDetail.getComment();
                                    String javaTypeName = columnDetail.getJavaTypeName();
                                    formField(formFields, textFileContent, dateFileContent, field, fieldSuffix, title, columnName, javaTypeName, exclusiveColumns);
                                }
                            }
                        }
                    }
                }

            }
        }
        return formFields.toString();
    }

    private static void formField(StringBuilder formFields, String textFileContent, String dateFileContent,
                                  String field, String fieldSuffix, String title, String column , String javaTypeName, String[] exclusiveColumns) {
        if (fieldSuffix.equals(SEARCH_FORM_FIELD_SUFFIX)) {
            if (javaTypeName.equals("Date")) {
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
        } else if (!fieldSuffix.equals(SEARCH_FORM_FIELD_SUFFIX) && !top.zywork.common.StringUtils.isInArray(exclusiveColumns, column)) {
            if (javaTypeName.equals("Date")) {
                formFields.append(dateFileContent.replace(TemplateConstants.VIEW_FIELD_NAME_EN, field)
                        .replace(TemplateConstants.VIEW_ID_FIELD_NAME_EN, field + fieldSuffix)
                        .replace(TemplateConstants.VIEW_FIELD_NAME_CN, title)
                        .replace(TemplateConstants.VIEW_FIELD_PLACEHOLDER, "请选择" + title))
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
