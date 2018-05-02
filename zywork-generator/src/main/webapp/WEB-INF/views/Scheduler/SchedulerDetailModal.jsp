<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
    <h4 class="modal-title">详情</h4>
</div>
<div class="modal-body">
    <div class="row">
        <div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">作业编号：</span>
    <span class="col-sm-8" id=idDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">作业名称：</span>
    <span class="col-sm-8" id=nameDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">作业完整类名：</span>
    <span class="col-sm-8" id=classNameDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">CRON表达式：</span>
    <span class="col-sm-8" id=cronExpressionDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">作业组名称：</span>
    <span class="col-sm-8" id=groupNameDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">触发器名称：</span>
    <span class="col-sm-8" id=triggerNameDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">触发器组：</span>
    <span class="col-sm-8" id=triggerGroupDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">作业描述：</span>
    <span class="col-sm-8" id=descriptionDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">作业状态：</span>
    <span class="col-sm-8" id=jobStatusDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">创建时间：</span>
    <span class="col-sm-8" id=createTimeDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">最近一次更新时间：</span>
    <span class="col-sm-8" id=updateTimeDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">是否可用：</span>
    <span class="col-sm-8" id=isActiveDetail></span>
</div>


    </div>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
</div>
