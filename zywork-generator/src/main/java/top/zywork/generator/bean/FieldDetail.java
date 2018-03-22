package top.zywork.generator.bean;

public class FieldDetail {

    private String name;
    private String javaType;

    public FieldDetail() {

    }

    public FieldDetail(String name, String javaType) {
        this.name = name;
        this.javaType = javaType;
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
}
