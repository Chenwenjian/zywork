# zywork-report

*作者：王振宇*

zywork-report为报表管理系统，实现了POI对Excel文件的导入导出，并支持Excel图片；JasperReport报表模板的上传下载，PDF的生成与导出；基于HighCharts的HTML5 WEB报表的快速生成。


#### ExcelImportService和ExcelExportService
此系统中提供了Excel导入导出的基本服务类，可以完成简单的Excel导入与导出，并且不需要重复写导入导出的代码，可以通过```JSON```配置文件动态指定导入数据放到哪个```JavaBean```，导出数据的```JavaBean```属性与Excel文档哪些列相对应。

对于```ExcelImportService```， 可使用如下的```JSON```配置：

```
{
  "beginRow": 2,
  "destinationClass":"top.zywork.service.UserImportDTO",
  "properties":[
    "id",
    "name",
    "phone"
  ]
}
```

对于```ExcelExportService```，可使用如下的```JSON```配置：

```
{
  "title": "用户信息",
  "columnNames": [
    "编号",
    "姓名",
    "手机号"
  ],
  "properties":[
    "id",
    "name",
    "phone"
  ]
}
```