package top.zywork.generator.bean;

/**
 * Generator配置信息的封装类<br/>
 *
 * 创建于2018-03-22<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
public class Generator {

    private String charset;
    private String author;
    private String tablePrefix;
    private String saveBaseDir;
    private String javaSrcDir;
    private String resourceDir;
    private String templateDir;
    private String basePackage;
    private String doPackage;
    private String dtoPackage;
    private String voPackage;
    private String queryPackage;
    private String daoPackage;
    private String servicePackage;
    private String serviceImplPackage;
    private String controllerPackage;
    private String mapperDir;
    private String doSuffix;
    private String dtoSuffix;
    private String voSuffix;
    private String querySuffix;
    private String daoSuffix;
    private String serviceSuffix;
    private String serviceImplSuffix;
    private String controllerSuffix;
    private String mapperSuffix;
    private String jsFileDir;
    private String cssFileDir;
    private String viewFileDir;
    private String exclusiveAddEditColumns;
    private String jsSearchModalSuffix;
    private String viewSearchModalSuffix;
    private String viewAddModalSuffix;
    private String viewEditModalSuffix;
    private String viewDetailModalSuffix;

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public String getSaveBaseDir() {
        return saveBaseDir;
    }

    public void setSaveBaseDir(String saveBaseDir) {
        this.saveBaseDir = saveBaseDir;
    }

    public String getJavaSrcDir() {
        return javaSrcDir;
    }

    public void setJavaSrcDir(String javaSrcDir) {
        this.javaSrcDir = javaSrcDir;
    }

    public String getResourceDir() {
        return resourceDir;
    }

    public void setResourceDir(String resourceDir) {
        this.resourceDir = resourceDir;
    }

    public String getTemplateDir() {
        return templateDir;
    }

    public void setTemplateDir(String templateDir) {
        this.templateDir = templateDir;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getDoPackage() {
        return doPackage;
    }

    public void setDoPackage(String doPackage) {
        this.doPackage = doPackage;
    }

    public String getDtoPackage() {
        return dtoPackage;
    }

    public void setDtoPackage(String dtoPackage) {
        this.dtoPackage = dtoPackage;
    }

    public String getVoPackage() {
        return voPackage;
    }

    public void setVoPackage(String voPackage) {
        this.voPackage = voPackage;
    }

    public String getQueryPackage() {
        return queryPackage;
    }

    public void setQueryPackage(String queryPackage) {
        this.queryPackage = queryPackage;
    }

    public String getDaoPackage() {
        return daoPackage;
    }

    public void setDaoPackage(String daoPackage) {
        this.daoPackage = daoPackage;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getServiceImplPackage() {
        return serviceImplPackage;
    }

    public void setServiceImplPackage(String serviceImplPackage) {
        this.serviceImplPackage = serviceImplPackage;
    }

    public String getControllerPackage() {
        return controllerPackage;
    }

    public void setControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;
    }

    public String getMapperDir() {
        return mapperDir;
    }

    public void setMapperDir(String mapperDir) {
        this.mapperDir = mapperDir;
    }

    public String getDoSuffix() {
        return doSuffix;
    }

    public void setDoSuffix(String doSuffix) {
        this.doSuffix = doSuffix;
    }

    public String getDtoSuffix() {
        return dtoSuffix;
    }

    public void setDtoSuffix(String dtoSuffix) {
        this.dtoSuffix = dtoSuffix;
    }

    public String getVoSuffix() {
        return voSuffix;
    }

    public void setVoSuffix(String voSuffix) {
        this.voSuffix = voSuffix;
    }

    public String getQuerySuffix() {
        return querySuffix;
    }

    public void setQuerySuffix(String querySuffix) {
        this.querySuffix = querySuffix;
    }

    public String getDaoSuffix() {
        return daoSuffix;
    }

    public void setDaoSuffix(String daoSuffix) {
        this.daoSuffix = daoSuffix;
    }

    public String getServiceSuffix() {
        return serviceSuffix;
    }

    public void setServiceSuffix(String serviceSuffix) {
        this.serviceSuffix = serviceSuffix;
    }

    public String getServiceImplSuffix() {
        return serviceImplSuffix;
    }

    public void setServiceImplSuffix(String serviceImplSuffix) {
        this.serviceImplSuffix = serviceImplSuffix;
    }

    public String getControllerSuffix() {
        return controllerSuffix;
    }

    public void setControllerSuffix(String controllerSuffix) {
        this.controllerSuffix = controllerSuffix;
    }

    public String getMapperSuffix() {
        return mapperSuffix;
    }

    public void setMapperSuffix(String mapperSuffix) {
        this.mapperSuffix = mapperSuffix;
    }

    public String getJsFileDir() {
        return jsFileDir;
    }

    public void setJsFileDir(String jsFileDir) {
        this.jsFileDir = jsFileDir;
    }

    public String getCssFileDir() {
        return cssFileDir;
    }

    public void setCssFileDir(String cssFileDir) {
        this.cssFileDir = cssFileDir;
    }

    public String getViewFileDir() {
        return viewFileDir;
    }

    public void setViewFileDir(String viewFileDir) {
        this.viewFileDir = viewFileDir;
    }

    public String getExclusiveAddEditColumns() {
        return exclusiveAddEditColumns;
    }

    public void setExclusiveAddEditColumns(String exclusiveAddEditColumns) {
        this.exclusiveAddEditColumns = exclusiveAddEditColumns;
    }

    public String getJsSearchModalSuffix() {
        return jsSearchModalSuffix;
    }

    public void setJsSearchModalSuffix(String jsSearchModalSuffix) {
        this.jsSearchModalSuffix = jsSearchModalSuffix;
    }

    public String getViewSearchModalSuffix() {
        return viewSearchModalSuffix;
    }

    public void setViewSearchModalSuffix(String viewSearchModalSuffix) {
        this.viewSearchModalSuffix = viewSearchModalSuffix;
    }

    public String getViewAddModalSuffix() {
        return viewAddModalSuffix;
    }

    public void setViewAddModalSuffix(String viewAddModalSuffix) {
        this.viewAddModalSuffix = viewAddModalSuffix;
    }

    public String getViewEditModalSuffix() {
        return viewEditModalSuffix;
    }

    public void setViewEditModalSuffix(String viewEditModalSuffix) {
        this.viewEditModalSuffix = viewEditModalSuffix;
    }

    public String getViewDetailModalSuffix() {
        return viewDetailModalSuffix;
    }

    public void setViewDetailModalSuffix(String viewDetailModalSuffix) {
        this.viewDetailModalSuffix = viewDetailModalSuffix;
    }
}
