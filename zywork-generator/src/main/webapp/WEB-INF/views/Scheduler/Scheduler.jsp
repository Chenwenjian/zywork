<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>Scheduler</title>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <%@include file="../master/include-css.jsp"%>
</head>
<body class="gray-bg">
<div class="row wrapper wrapper-content animated fadeInRight">
    <div class="col-sm-12">
        <table id="data-list" data-classes="table table-hover text-nowrap" data-toolbar="#toolbar"></table>

        <div id="toolbar">
            <button class="btn btn-primary" onclick="showRemoteAddModal('add-modal', '/scheduler/add-modal', 'add-form', validateFields())">
                <i class="fa fa-plus"></i>
                添加
            </button>
            <div class="btn-group">
              <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fa fa-th-list"></i>
                批量操作&nbsp;<span class="caret"></span>
              </button>
              <ul class="dropdown-menu">
                <li>
                    <a href="javascript:void(0);" onclick="batchActive('/scheduler/batch-active', 0, 'data-list', '/scheduler/pager-cond', 'id')">
                        <i class="fa fa-check-square-o text-success"></i><span class="text-success">&nbsp;批量激活</span>
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="batchActive('/scheduler/batch-active', 1, 'data-list', '/scheduler/pager-cond', 'id')">
                        <i class="fa fa-minus-square-o text-danger"></i><span class="text-danger">&nbsp;批量冻结</span>
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="batchRemove('/scheduler/batch-remove', 'data-list', '/scheduler/pager-cond', 'id')">
                        <i class="fa fa-remove text-danger"></i><span class="text-danger">&nbsp;批量删除</span>
                    </a>
                </li>
              </ul>
            </div>
            <button class="btn btn-primary" onclick="showSearchForm('search-form')">
                <i class="fa fa-eye"></i>
                高级搜索
            </button>

            <form id="search-form" class="row form-horizontal search-form" style="display: none;">
                <div class="col-sm-6 form-group">
    <label for="nameSearch" class="col-sm-4 control-label">作业名称：</label>
    <div class="col-sm-8">
        <input id="nameSearch" name="name" class="form-control" placeholder="请输入作业名称">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="classNameSearch" class="col-sm-4 control-label">作业完整类名：</label>
    <div class="col-sm-8">
        <input id="classNameSearch" name="className" class="form-control" placeholder="请输入作业完整类名">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="cronExpressionSearch" class="col-sm-4 control-label">CRON表达式：</label>
    <div class="col-sm-8">
        <input id="cronExpressionSearch" name="cronExpression" class="form-control" placeholder="请输入CRON表达式">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="groupNameSearch" class="col-sm-4 control-label">作业组名称：</label>
    <div class="col-sm-8">
        <input id="groupNameSearch" name="groupName" class="form-control" placeholder="请输入作业组名称">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="triggerNameSearch" class="col-sm-4 control-label">触发器名称：</label>
    <div class="col-sm-8">
        <input id="triggerNameSearch" name="triggerName" class="form-control" placeholder="请输入触发器名称">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="triggerGroupSearch" class="col-sm-4 control-label">触发器组：</label>
    <div class="col-sm-8">
        <input id="triggerGroupSearch" name="triggerGroup" class="form-control" placeholder="请输入触发器组">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="descriptionSearch" class="col-sm-4 control-label">作业描述：</label>
    <div class="col-sm-8">
        <input id="descriptionSearch" name="description" class="form-control" placeholder="请输入作业描述">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="jobStatusSearch" class="col-sm-4 control-label">作业状态：</label>
    <div class="col-sm-8">
        <input id="jobStatusSearch" name="jobStatus" class="form-control" placeholder="请输入作业状态">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="createTimeStartSearch" class="col-sm-4 control-label">创建时间(开始)：</label>
    <div class="input-group date form_datetime col-sm-8" style="padding-left: 15px; padding-right: 15px;">
        <input id="createTimeStartSearch" name="createTimeStart" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择创建时间(开始)"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="createTimeEndSearch" class="col-sm-4 control-label">创建时间(结束)：</label>
    <div class="input-group date form_datetime col-sm-8" style="padding-left: 15px; padding-right: 15px;">
        <input id="createTimeEndSearch" name="createTimeEnd" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择创建时间(结束)"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="updateTimeStartSearch" class="col-sm-4 control-label">最近一次更新时间(开始)：</label>
    <div class="input-group date form_datetime col-sm-8" style="padding-left: 15px; padding-right: 15px;">
        <input id="updateTimeStartSearch" name="updateTimeStart" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择最近一次更新时间(开始)"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="updateTimeEndSearch" class="col-sm-4 control-label">最近一次更新时间(结束)：</label>
    <div class="input-group date form_datetime col-sm-8" style="padding-left: 15px; padding-right: 15px;">
        <input id="updateTimeEndSearch" name="updateTimeEnd" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择最近一次更新时间(结束)"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="isActiveSearch" class="col-sm-4 control-label">是否可用：</label>
    <div class="col-sm-8">
        <input id="isActiveSearch" name="isActive" class="form-control" placeholder="请输入是否可用">
    </div>
</div>



                <div class="col-sm-6 form-group text-right" style="padding-right: 30px;">
                    <button type="button" class="btn btn-primary" onclick="doSearch('data-list')">
                    <i class="fa fa-search"></i>
                        搜索
                    </button>
                    <button type="button" class="btn btn-primary" onclick="doSearchAll('data-list', 'search-form')">
                        <i class="fa fa-search"></i>
                        搜索所有
                    </button>
                    <button type="button" class="btn btn-primary" onclick="hideSearchForm('data-list', 'search-form')">
                        <i class="fa fa-eye-slash"></i>
                        取消搜索
                    </button>
                </div>
            </form>
        </div>
    </div>

</div>

<div class="modal fade" id="add-modal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
        </div>
    </div>
</div>

<div class="modal fade" id="edit-modal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
        </div>
    </div>
</div>

<div class="modal fade" id="detail-modal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
        </div>
    </div>
</div>

</body>
<%@include file="../master/include-js.jsp"%>
<script src="<%=path%>/static/js/Scheduler/Scheduler.js"></script>
</html>
