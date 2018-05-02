<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
    <h4 class="modal-title">添加</h4>
</div>
<div class="modal-body">
    <form id="add-form" class="form-horizontal row" enctype="multipart/form-data">
        <div class="form-group">
    <label for="name" class="col-sm-2 control-label">作业名称：</label>
    <div class="col-sm-10">
        <input id="name" name="name" class="form-control" placeholder="请输入作业名称"/>
    </div>
</div>

<div class="form-group">
    <label for="className" class="col-sm-2 control-label">作业完整类名：</label>
    <div class="col-sm-10">
        <input id="className" name="className" class="form-control" placeholder="请输入作业完整类名"/>
    </div>
</div>

<div class="form-group">
    <label for="cronExpression" class="col-sm-2 control-label">CRON表达式：</label>
    <div class="col-sm-10">
        <input id="cronExpression" name="cronExpression" class="form-control" placeholder="请输入CRON表达式"/>
    </div>
</div>

<div class="form-group">
    <label for="groupName" class="col-sm-2 control-label">作业组名称：</label>
    <div class="col-sm-10">
        <input id="groupName" name="groupName" class="form-control" placeholder="请输入作业组名称"/>
    </div>
</div>

<div class="form-group">
    <label for="triggerName" class="col-sm-2 control-label">触发器名称：</label>
    <div class="col-sm-10">
        <input id="triggerName" name="triggerName" class="form-control" placeholder="请输入触发器名称"/>
    </div>
</div>

<div class="form-group">
    <label for="triggerGroup" class="col-sm-2 control-label">触发器组：</label>
    <div class="col-sm-10">
        <input id="triggerGroup" name="triggerGroup" class="form-control" placeholder="请输入触发器组"/>
    </div>
</div>

<div class="form-group">
    <label for="description" class="col-sm-2 control-label">作业描述：</label>
    <div class="col-sm-10">
        <input id="description" name="description" class="form-control" placeholder="请输入作业描述"/>
    </div>
</div>

<div class="form-group">
    <label for="jobStatus" class="col-sm-2 control-label">作业状态：</label>
    <div class="col-sm-10">
        <input id="jobStatus" name="jobStatus" class="form-control" placeholder="请输入作业状态"/>
    </div>
</div>


    </form>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
    <button id="btn-save" type="button" class="btn btn-primary" onclick="saveOrEdit('btn-save', 'add-modal', 'add-form', '/scheduler/save', 'data-list', '/scheduler/pager-cond')">确认</button>
</div>

<script>
    initDatetime();
</script>
