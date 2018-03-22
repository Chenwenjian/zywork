<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>zywork单表代码自动生成</title>
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
</head>
<body class="gray-bg">
<div class="row wrapper wrapper-content animated fadeInRight">
    <div class="col-sm-12">
        <h4>单表代码生成</h4>
        <div class="row">
            <div class="col-sm-12">
                数据表总数：<span id="total-tables"></span><br/>
                <button class="btn btn-primary" onclick="refreshTables();">刷新所有表</button>
                <button class="btn btn-primary" onclick="generateAllCodes();">生成所有表的代码</button>
                <button class="btn btn-primary" onclick="generateCode();">生成所选表的代码</button>
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="col-sm-12">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">请选择单个数据表：</label>

                        <div class="col-sm-9">
                            <select id="all-tables" class="form-control" data-placeholder="请选择数据表"></select>
                        </div>
                    </div>

                </form>
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="col-sm-12">
                <h4>以下表格显示您选中的数据表的所有字段信息</h4>

                <table id="table-columns" class="table"></table>

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

<script>
    let usingSelect2 = false;

    function refreshTables() {
        if (usingSelect2) {
            $("#all-tables").select2('destroy').empty();
        }
        $.get('/table/all', function (data) {
            $('#total-tables').text(data.length);

            $("#all-tables").select2({
                data: data,
                language: 'zh-CN',
                placeholder:'请选择数据表',
                width: '100%',
                theme: "bootstrap"
            });
            usingSelect2 = true;

            loadTableData(data[0].id)
        }, 'json');
    }

    $(function () {
        refreshTables();

        $("#all-tables").on("select2:select",function(){
            loadTableData($(this).val());
        });

    });

    function generateAllCodes() {
        $.get('/generator/all-codes', function (data) {
            swal("提示", data.message, "success");
        }, 'json');
    }

    function generateCode() {
        $.get('/generator/code/' + $('#all-tables').val(), function (data) {
            swal("提示", data.message, "success");
        }, 'json');
    }

    function loadTableData(tableName) {
        $('#table-columns').bootstrapTable('destroy');
        $('#table-columns').bootstrapTable({
            url: '/table/columns/' + tableName,
            dataType: 'json',
            method: 'get',
            singleSelect: true,
            striped: true,
            pagination:true,
            sidePagination: 'server',
            columns: [
                {
                    title: '字段名称',
                    field: 'name',
                    align: 'center'
                },
                {
                    title: '字段类型',
                    field: 'jdbcTypeName',
                    align: 'center'
                },
                {
                    title: 'Java属性名称',
                    field: 'fieldName',
                    align: 'center'
                },
                {
                    title: 'Java属性类型',
                    field: 'javaTypeName',
                    align: 'center'
                },
                {
                    title: '注释',
                    field: 'comment',
                    align: 'center',
                }
            ]
        });
    }

</script>
</html>
