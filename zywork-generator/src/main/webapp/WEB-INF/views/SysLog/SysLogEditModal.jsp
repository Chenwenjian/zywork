<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
    <h4 class="modal-title">编辑</h4>
</div>
<div class="modal-body">
    <form id="edit-form" class="form-horizontal row" enctype="multipart/form-data">
        <input type="hidden" name="id"/>
        <div class="form-group">
    <label for="userId" class="col-sm-2 control-label">用户编号：</label>
    <div class="col-sm-10">
        <input id="userId" name="userId" class="form-control" placeholder="请输入用户编号"/>
    </div>
</div>

<div class="form-group">
    <label for="description" class="col-sm-2 control-label">执行说明：</label>
    <div class="col-sm-10">
        <input id="description" name="description" class="form-control" placeholder="请输入执行说明"/>
    </div>
</div>

<div class="form-group">
    <label for="executeClass" class="col-sm-2 control-label">类名称：</label>
    <div class="col-sm-10">
        <input id="executeClass" name="executeClass" class="form-control" placeholder="请输入类名称"/>
    </div>
</div>

<div class="form-group">
    <label for="executeMethod" class="col-sm-2 control-label">方法名称：</label>
    <div class="col-sm-10">
        <input id="executeMethod" name="executeMethod" class="form-control" placeholder="请输入方法名称"/>
    </div>
</div>

<div class="form-group">
    <label for="executeTime" class="col-sm-2 control-label">开始执行时间：</label>
    <div class="input-group date form_datetime col-sm-10" style="padding-left: 15px; padding-right: 15px;">
        <input id="executeTime" name="executeTime" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择开始执行时间"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
	    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="form-group">
    <label for="executeCostTime" class="col-sm-2 control-label">执行耗时(ms)：</label>
    <div class="col-sm-10">
        <input id="executeCostTime" name="executeCostTime" class="form-control" placeholder="请输入执行耗时(ms)"/>
    </div>
</div>

<div class="form-group">
    <label for="executeIp" class="col-sm-2 control-label">IP地址：</label>
    <div class="col-sm-10">
        <input id="executeIp" name="executeIp" class="form-control" placeholder="请输入IP地址"/>
    </div>
</div>


    </form>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
    <button id="btn-edit" type="button" class="btn btn-primary" onclick="saveOrEdit('btn-edit', 'edit-modal', 'edit-form', '/sys-log/update', 'data-list', '/sys-log/pager-cond')">确认</button>
</div>

<script>
    initDatetime();
</script>
