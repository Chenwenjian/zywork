<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>zywork关联表代码自动生成</title>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <link href="<%=path%>/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=path%>/static/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=path%>/static/css/animate.css" rel="stylesheet">
    <link href="<%=path%>/static/css/style.css" rel="stylesheet">
    <link href="<%=path%>/static/css/plugins/select2/select2.min.css" rel="stylesheet"/>
    <link href="<%=path%>/static/css/plugins/select2/select2-bootstrap.min.css" rel="stylesheet"/>
    <link href="<%=path%>/static/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet"/>
    <link href="<%=path%>/static/css/plugins/sweetalert/sweetalert2.min.css" rel="stylesheet"/>
    <link href="<%=path%>/static/css/plugins/iCheck/skins/all.css" rel="stylesheet"/>
    
</head>
<body class="gray-bg">
<div class="row wrapper wrapper-content animated fadeInRight">
    <div class="col-sm-12">
        <h4>关联表代码生成</h4>
        <div class="row">
            <div class="col-sm-12">
                数据表总数：<span id="total-tables"></span><br/>
                <button class="btn btn-primary" onclick="refreshTables();">刷新所有表</button>
                <button class="btn btn-primary" onclick="confirmTables();">确定所选表</button>
                <button class="btn btn-primary" onclick="generateJoinCode();">生成所选表及字段的代码</button>
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="col-sm-12">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="all-tables" class="col-sm-3 control-label">请选择多个数据表：</label>

                        <div class="col-sm-9">
                            <select id="all-tables" class="form-control" multiple></select>
                        </div>
                    </div>

                </form>
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="col-sm-12">

                <form id="table-column-info" class="form-horizontal">
                    <div class="form-group">
                        <label for="bean_name" class="col-sm-3 control-label">请输入实体类名称：</label>

                        <div class="col-sm-9">
                            <input id="bean_name" name="beanName" class="form-control" placeholder="请输入实体类名称"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="request_mapping" class="col-sm-3 control-label">请输入控制器映射URL：</label>

                        <div class="col-sm-9">
                            <input id="request_mapping" name="requestMapping" class="form-control" placeholder="请输入控制器映射URL"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="where_clause" class="col-sm-3 control-label">请输入SQL语句WHERE条件查询部分：</label>

                        <div class="col-sm-9">
                            <textarea id="where_clause" name="whereClause" class="form-control" rows="3" placeholder="使用完整的[表名.字段]的形式输入查询条件，如t_user.id = t_role.user_id"></textarea>
                        </div>
                    </div>
                    <h4>所选表的字段信息</h4>
                    <div id="table-columns"></div>
                </form>
            </div>
        </div>
    </div>

</div>

</body>
<!-- 全局js -->
<script src="<%=path%>/static/js/jquery.min.js"></script>
<script src="<%=path%>/static/js/bootstrap.min.js"></script>
<script src="<%=path%>/static/js/plugins/metisMenu/metisMenu.min.js"></script>
<script src="<%=path%>/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="<%=path%>/static/js/plugins/select2/select2.min.js"></script>
<script src="<%=path%>/static/js/plugins/select2/i18n/zh-CN.js"></script>
<script src="<%=path%>/static/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="<%=path%>/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path%>/static/js/plugins/sweetalert/sweetalert2.min.js"></script>
<script src="<%=path%>/static/js/plugins/iCheck/icheck.min.js"></script>

<script>
    let usingSelect2 = false;

    function refreshTables() {
        if (usingSelect2) {
            $("#all-tables").select2('destroy').empty();
        }
        $.get('<%=path%>/table/all', function (data) {
            $('#total-tables').text(data.length);

            $("#all-tables").select2({
                data: data,
                language: 'zh-CN',
                placeholder:'请选择数据表',
                width: '100%',
                theme: "bootstrap"
            });
            usingSelect2 = true;
        }, 'json');
    }

    $(function () {

        refreshTables();

    });

    function confirmTables() {
        let tables = $('#all-tables').val();
        if (tables === null || tables === '') {
            swal('提示', '请选择数据表', 'warning');
        } else {
            $('#table-columns').empty();
            let tableArray = (tables + "").split(',');
            $.each(tableArray, function (index, item) {
                $.get('<%=path%>/table/column-details/' + item, function (data) {
                    printColumns(item, data)
                }, 'json');
            });
        }
    }

    function printColumns(table, tableColumns) {
        let div = '<div class="col-sm-12">'
                    + '<h3>'
                    + table
                    + '&nbsp;&nbsp;<input id="' + table + '" type="radio" name="primaryTable" value="' + table + '"/>&nbsp;&nbsp;'
                    + '<label for="' + table + '">设置为主表</label>'
                    + '</h3>';
        $.each(tableColumns, function (index, columnDetail) {
            let id = table + '-' + columnDetail.name + '-' + columnDetail.javaTypeName;
            div += '<div class="form-group col-xs-12 col-sm-6 col-md-3 col-lg-2" style="margin-top:10px;">'
                    + '<input id="' + id + '" type="checkbox" name="columns" value="' + id + '"/>&nbsp;&nbsp;'
                    + '<label for="' + id + '">'
                    + columnDetail.name
                    + '</label>'
                    + '</div>';

        });
        div += '</div>';
        $('#table-columns').append(div);
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
            increaseArea: '20%'
        });
    }

    function generateJoinCode() {
        let tables = $('#all-tables').val();
        if (tables === null || tables === '' || (tables + '').indexOf(",") === -1) {
            swal('提示', '请选择需要关联的数据表', 'warning');
        } else if ($('#bean_name').val().trim() === '') {
            swal('提示', '请输入实体类名称', 'warning');
        } else if ($('#request_mapping').val().trim() === '') {
            swal('提示', '请输入控制映射URL', 'warning');
        } else if ($('#where_clause').val().trim() === '') {
            swal('提示', '请输入WHERE查询条件', 'warning');
        } else if ($('input[name="columns"]:checked').length <= 0) {
            swal('提示', '请选择需要查询的字段', 'warning');
        } else {
            $.post('<%=path%>/generator/join-code',
                $('#table-column-info').serialize(),
                function (data) {
                    if (data.code === 200) {
                        swal('提示', data.message, 'success');
                    } else {
                        swal('提示', data.message, 'warning');
                    }
                }, 'json');
        }
    }

</script>
</html>
