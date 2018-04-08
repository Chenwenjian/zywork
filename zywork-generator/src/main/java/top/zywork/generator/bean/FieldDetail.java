package top.zywork.generator.bean;

/**
 * 属性信息的封装类<br/>
 *
 * 创建于2018-03-22<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
public class FieldDetail {

    private String name;
    private String javaType;
    private String nameCN;

    public FieldDetail() {

    }

    public FieldDetail(String name, String javaType, String nameCN) {
        this.name = name;
        this.javaType = javaType;
        this.nameCN = nameCN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getNameCN() {
        return nameCN;
    }

    public void setNameCN(String nameCN) {
        this.nameCN = nameCN;
    }
}
