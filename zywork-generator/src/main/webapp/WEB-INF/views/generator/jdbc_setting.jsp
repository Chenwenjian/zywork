<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>zywork代码自动生成系统JDBC配置</title>
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
            <h4>JDBC配置</h4>
            <form id="jdbc-form" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-3 control-label">驱动程序名：</label>
                    <div class="col-sm-9">
                        <input type="text" name="driverClassName" placeholder="驱动程序名" class="form-control" value="${applicationScope.jdbc.driverClassName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">连接URL：</label>

                    <div class="col-sm-9">
                        <input type="text" name="url" placeholder="连接URL" class="form-control" value="${applicationScope.jdbc.url}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">数据库用户名：</label>

                    <div class="col-sm-9">
                        <input type="text" name="username" placeholder="数据库用户名" class="form-control" value="${applicationScope.jdbc.username}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">数据库密码：</label>

                    <div class="col-sm-9">
                        <input type="text" name="password" placeholder="数据库密码" class="form-control" value="${applicationScope.jdbc.password}"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-9">
                        <button class="btn btn-primary" type="button" onclick="saveJdbc()">确定</button>
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
    function saveJdbc() {
        $.post('<%=path%>/setting/jdbc',
            $('#jdbc-form').serialize(),
            function (data) {
                if (data.code === 200) {
                    swal('提示', data.message, "success");
                } else {
                    swal('提示', data.message, "warning");
                }
            }, 'json'
        );
    }
</script>

</html>
