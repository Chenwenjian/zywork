package top.zywork.common;

import org.apache.commons.lang3.StringUtils;
import top.zywork.bean.ColumnDetail;
import top.zywork.bean.TableColumns;
import top.zywork.constant.GeneratorConstants;
import top.zywork.constant.TemplateConstants;

import java.util.List;

/**
 * Mapper映射自动生成代码封装类<br/>
 *
 * 创建于2018-03-12<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
public class MapperGenerator {

    /**
     * 生成Mapper映射xml文件
     * @param tableColumns 表数据
     */
    public static void generateMapper(TableColumns tableColumns) {
        String beanName = GeneratorUtils.tableNameToClassName(tableColumns.getTableName(), "t");
        String resDir = GeneratorUtils.createResDir(GeneratorConstants.MAPPER_RESOURCE_DIR);
        String fileContent = GeneratorUtils.readTemplate(GeneratorConstants.MAPPER_TEMPLATE);
        fileContent = fileContent.replace(TemplateConstants.BEAN_NAME, beanName)
                .replace(TemplateConstants.BEAN_NAME_LOWER_CASE, StringUtils.uncapitalize(beanName))
                .replace(TemplateConstants.TABLE_NAME, tableColumns.getTableName());
        fileContent = generateInsertColumns(fileContent, tableColumns);
        fileContent = generateInsertValues(fileContent, tableColumns);
        fileContent = generateSetClause(fileContent, tableColumns);
        fileContent = generateSelectColumns(fileContent, tableColumns);
        fileContent = generateWhereClause(fileContent, tableColumns);
        GeneratorUtils.writeFile(fileContent, resDir, beanName + GeneratorConstants.MAPPER_XML_SUFFIX);
    }

    /**
     * 生成所有Mapper映射文件
     * @param tableColumnsList 所有表数据
     */
    public static void generateMappers(List<TableColumns> tableColumnsList) {
        for (TableColumns tableColumns : tableColumnsList) {
            generateMapper(tableColumns);
        }
    }

    /**
     * 生成Mapper映射文件insert语句中的column部分
     * @param fileContent 文件内容
     * @param tableColumns 表数据
     * @return 添加了insert语句的文件内容
     */
    private static String generateInsertColumns(String fileContent, TableColumns tableColumns) {
        List<ColumnDetail> columnDetails = tableColumns.getColumns();
        StringBuilder insertColumns = new StringBuilder("");
        for (ColumnDetail columnDetail : columnDetails) {
            insertColumns.append("<if test=\"")
                    .append(PropertyUtils.columnToProperty(columnDetail.getName()))
                    .append(" != null\">\n\t\t\t\t")
                    .append(columnDetail.getName())
                    .append(",\n\t\t\t</if>\n\t\t\t");
        }
        return fileContent.replace(TemplateConstants.INSERT_COLUMNS, insertColumns.toString());
    }

    /**
     * 生成Mapper映射文件insert语句中的值部分
     * @param fileContent 文件内容
     * @param tableColumns 表数据
     * @return 添加了insert语句的文件内容
     */
    private static String generateInsertValues(String fileContent, TableColumns tableColumns){
        List<ColumnDetail> columnDetails = tableColumns.getColumns();
        StringBuilder insertValues = new StringBuilder("");
        for (ColumnDetail columnDetail : columnDetails) {
            String field = PropertyUtils.columnToProperty(columnDetail.getName());
            insertValues.append("<if test=\"")
                    .append(field)
                    .append(" != null\">\n\t\t\t\t")
                    .append("#{")
                    .append(field)
                    .append("}")
                    .append(",\n\t\t\t</if>\n\t\t\t");
        }
        return fileContent.replace(TemplateConstants.INSERT_VALUES, insertValues.toString());
    }

    /**
     * 生成Mapper映射文件update语句的set部分
     * @param fileContent 文件内容
     * @param tableColumns 表数据
     * @return 添加了update语句的文件内容
     */
    private static String generateSetClause(String fileContent, TableColumns tableColumns){
        List<ColumnDetail> columnDetails = tableColumns.getColumns();
        StringBuilder setClause = new StringBuilder("");
        for (ColumnDetail columnDetail : columnDetails) {
            String field = PropertyUtils.columnToProperty(columnDetail.getName());
            setClause.append("<if test=\"")
                    .append(field)
                    .append(" != null\">\n\t\t\t\t")
                    .append(columnDetail.getName())
                    .append(" = ")
                    .append("#{")
                    .append(field)
                    .append("}")
                    .append(",\n\t\t\t</if>\n\t\t\t");
        }
        return fileContent.replace(TemplateConstants.SET_CLAUSE, setClause.toString());
    }

    /**
     * 生成Mapper映射文件select字段部分
     * @param fileContent 文件内容
     * @param tableColumns 表数据
     * @return 添加了select通用字段部分的文件内容
     */
    private static String generateSelectColumns(String fileContent, TableColumns tableColumns){
        List<ColumnDetail> columnDetails = tableColumns.getColumns();
        StringBuilder selectColumns = new StringBuilder("");
        for (ColumnDetail columnDetail : columnDetails) {
            selectColumns.append(", ")
                    .append(columnDetail.getName());
        }
        return fileContent.replace(TemplateConstants.SELECT_COLUMNS, selectColumns.toString().replaceFirst(", ", ""));
    }

    /**
     * 生成Mapper映射文件查询语句where部分的内容
     * @param fileContent 文件内容
     * @param tableColumns 表数据
     * @return 添加了查询语句where部分内容的文件内容
     */
    private static String generateWhereClause(String fileContent, TableColumns tableColumns){
        List<ColumnDetail> columnDetails = tableColumns.getColumns();
        StringBuilder whereClause = new StringBuilder("");
        for (int i = 0, size = columnDetails.size(); i < size; i++) {
            ColumnDetail columnDetail = columnDetails.get(i);
            String field = PropertyUtils.columnToProperty(columnDetail.getName());
            whereClause.append("<if test=\"query != null and query.")
                    .append(field);
            if (columnDetail.getJavaTypeName().equals("String")) {
                whereClause.append(" != null and query.").append(field).append(" != ''\">\n\t\t\t");
            } else {
                whereClause.append(" != null\">\n\t\t\t");
            }

            if (i != 0) {
                whereClause.append("and ");
            }
            whereClause.append(columnDetail.getName())
                    .append(" = ")
                    .append("#{")
                    .append(field)
                    .append("}")
                    .append(",\n\t\t</if>\n\t\t");
        }
        return fileContent.replace(TemplateConstants.WHERE_CLAUSE, whereClause.toString());
    }

}
