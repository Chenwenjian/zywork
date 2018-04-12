<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>zywork代码自动生成系统Generator配置</title>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <link href="<%=path%>/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=path%>/static/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=path%>/static/css/animate.css" rel="stylesheet">
    <link href="<%=path%>/static/css/style.css" rel="stylesheet">
    <link href="<%=path%>/static/css/plugins/sweetalert/sweetalert2.min.css" rel="stylesheet"/>
</head>
<body class="gray-bg">
    <div class="row wrapper wrapper-content animated fadeInRight">
        <div class="col-sm-12">
            <h4>Generator配置</h4>
            <form id="generator-form" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-3 control-label">字符编码：</label>
                    <div class="col-sm-9">
                        <input type="text" name="charset" placeholder="字符编码" class="form-control" value="${applicationScope.generator.charset}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">Java注释作者名称：</label>

                    <div class="col-sm-9">
                        <input type="text" name="author" placeholder="Java注释作者名称" class="form-control" value="${applicationScope.generator.author}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">数据表前缀：</label>

                    <div class="col-sm-9">
                        <input type="text" name="tablePrefix" placeholder="数据表前缀" class="form-control" value="${applicationScope.generator.tablePrefix}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">文件保存根路径：</label>

                    <div class="col-sm-9">
                        <input type="text" name="saveBaseDir" placeholder="文件保存根路径" class="form-control" value="${applicationScope.generator.saveBaseDir}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">Java代码保存路径：</label>

                    <div class="col-sm-9">
                        <input type="text" name="javaSrcDir" placeholder="Java代码保存路径" class="form-control" value="${applicationScope.generator.javaSrcDir}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">资源文件保存路径：</label>

                    <div class="col-sm-9">
                        <input type="text" name="resourceDir" placeholder="资源文件保存路径" class="form-control" value="${applicationScope.generator.resourceDir}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">模板文件读取路径：</label>

                    <div class="col-sm-9">
                        <input type="text" name="templateDir" placeholder="模板文件读取路径" class="form-control" value="${applicationScope.generator.templateDir}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">基础包名：</label>

                    <div class="col-sm-9">
                        <input type="text" name="basePackage" placeholder="基础包名" class="form-control" value="${applicationScope.generator.basePackage}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">DO实体类包名：</label>

                    <div class="col-sm-9">
                        <input type="text" name="doPackage" placeholder="DO实体类包名" class="form-control" value="${applicationScope.generator.doPackage}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">DTO类包名：</label>

                    <div class="col-sm-9">
                        <input type="text" name="dtoPackage" placeholder="DTO类包名" class="form-control" value="${applicationScope.generator.dtoPackage}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">VO类包名：</label>

                    <div class="col-sm-9">
                        <input type="text" name="voPackage" placeholder="VO类包名" class="form-control" value="${applicationScope.generator.voPackage}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">Query类包名：</label>

                    <div class="col-sm-9">
                        <input type="text" name="queryPackage" placeholder="Query类包名" class="form-control" value="${applicationScope.generator.queryPackage}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">DAO接口包名：</label>

                    <div class="col-sm-9">
                        <input type="text" name="daoPackage" placeholder="DAO接口包名" class="form-control" value="${applicationScope.generator.daoPackage}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">Service接口包名：</label>

                    <div class="col-sm-9">
                        <input type="text" name="servicePackage" placeholder="Service接口包名" class="form-control" value="${applicationScope.generator.servicePackage}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">Service实现类包名：</label>

                    <div class="col-sm-9">
                        <input type="text" name="serviceImplPackage" placeholder="Service实现类包名" class="form-control" value="${applicationScope.generator.serviceImplPackage}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">Controller类包名：</label>

                    <div class="col-sm-9">
                        <input type="text" name="controllerPackage" placeholder="Controller类包名" class="form-control" value="${applicationScope.generator.controllerPackage}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">Mapper映射文件保存路径：</label>

                    <div class="col-sm-9">
                        <input type="text" name="mapperDir" placeholder="Mapper映射文件保存路径" class="form-control" value="${applicationScope.generator.mapperDir}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">DO实体类Java后缀：</label>

                    <div class="col-sm-9">
                        <input type="text" name="doSuffix" placeholder="DO实体类Java后缀" class="form-control" value="${applicationScope.generator.doSuffix}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">DTO类Java后缀：</label>

                    <div class="col-sm-9">
                        <input type="text" name="dtoSuffix" placeholder="DTO实体类Java后缀" class="form-control" value="${applicationScope.generator.dtoSuffix}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">VO类Java后缀：</label>

                    <div class="col-sm-9">
                        <input type="text" name="voSuffix" placeholder="VO类Java后缀" class="form-control" value="${applicationScope.generator.voSuffix}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">Query类Java后缀：</label>

                    <div class="col-sm-9">
                        <input type="text" name="querySuffix" placeholder="Query类Java后缀" class="form-control" value="${applicationScope.generator.querySuffix}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">DAO接口Java后缀：</label>

                    <div class="col-sm-9">
                        <input type="text" name="daoSuffix" placeholder="DAO接口Java后缀" class="form-control" value="${applicationScope.generator.daoSuffix}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">Service接口Java后缀：</label>

                    <div class="col-sm-9">
                        <input type="text" name="serviceSuffix" placeholder="Service接口Java后缀" class="form-control" value="${applicationScope.generator.serviceSuffix}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">Service实现类Java后缀：</label>

                    <div class="col-sm-9">
                        <input type="text" name="serviceImplSuffix" placeholder="Service实现类Java后缀" class="form-control" value="${applicationScope.generator.serviceImplSuffix}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">Controller类Java后缀：</label>

                    <div class="col-sm-9">
                        <input type="text" name="controllerSuffix" placeholder="Controller类Java后缀" class="form-control" value="${applicationScope.generator.controllerSuffix}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">Mapper映射文件后缀：</label>

                    <div class="col-sm-9">
                        <input type="text" name="mapperSuffix" placeholder="Mapper映射文件Java后缀" class="form-control" value="${applicationScope.generator.mapperSuffix}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">JS文件存储目录：</label>

                    <div class="col-sm-9">
                        <input type="text" name="jsFileDir" placeholder="JS文件存储目录" class="form-control" value="${applicationScope.generator.jsFileDir}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">CSS文件存储目录：</label>

                    <div class="col-sm-9">
                        <input type="text" name="cssFileDir" placeholder="CSS文件存储目录" class="form-control" value="${applicationScope.generator.cssFileDir}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">视图文件存储目录：</label>

                    <div class="col-sm-9">
                        <input type="text" name="viewFileDir" placeholder="视图文件存储目录" class="form-control" value="${applicationScope.generator.viewFileDir}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">添加和编辑表单排除字段：</label>

                    <div class="col-sm-9">
                        <input type="text" name="exclusiveAddEditColumns" placeholder="添加和编辑表单排除字段" class="form-control" value="${applicationScope.generator.exclusiveAddEditColumns}"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-9">
                        <button class="btn btn-primary" type="button" onclick="saveGenerator();">确定</button>
                    </div>
                </div>
            </form>
        </div>

    </div>
</body>
<!-- 全局js -->
<script src="<%=path%>/static/js/jquery.min.js"></script>
<script src="<%=path%>/static/js/bootstrap.min.js"></script>
<script src="<%=path%>/static/js/plugins/metisMenu/metisMenu.min.js"></script>
<script src="<%=path%>/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="<%=path%>/static/js/plugins/sweetalert/sweetalert2.min.js"></script>

<script>

    function saveGenerator() {
        $.post('<%=path%>/setting/generator',
            $('#generator-form').serialize(),
            function (data) {
                swal('提示', data.message, "success");
            }, 'json'
        );
    }
</script>

</html>
