package top.zywork.generator.bean;

/**
 * 列相关信息的封装类<br/>
 *
 * 创建于2018-03-12<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
public class ColumnDetail {

    private String name;
    private String fieldName;
    private String comment;
    private Integer type;
    private String jdbcTypeName;
    private String javaTypeName;

    public ColumnDetail() {}

    public ColumnDetail(String name, String comment, Integer type, String jdbcTypeName, String javaTypeName) {
        this.name = name;
        this.comment = comment;
        this.type = type;
        this.jdbcTypeName = jdbcTypeName;
        this.javaTypeName = javaTypeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getJdbcTypeName() {
        return jdbcTypeName;
    }

    public void setJdbcTypeName(String jdbcTypeName) {
        this.jdbcTypeName = jdbcTypeName;
    }

    public String getJavaTypeName() {
        return javaTypeName;
    }

    public void setJavaTypeName(String javaTypeName) {
        this.javaTypeName = javaTypeName;
    }
}
