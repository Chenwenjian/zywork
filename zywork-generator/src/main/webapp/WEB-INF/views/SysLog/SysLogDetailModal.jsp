<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
    <h4 class="modal-title">详情</h4>
</div>
<div class="modal-body">
    <div class="row">
        <div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">编号：</span>
    <span class="col-sm-8" id=idDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">用户编号：</span>
    <span class="col-sm-8" id=userIdDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">执行说明：</span>
    <span class="col-sm-8" id=descriptionDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">类名称：</span>
    <span class="col-sm-8" id=executeClassDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">方法名称：</span>
    <span class="col-sm-8" id=executeMethodDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">开始执行时间：</span>
    <span class="col-sm-8" id=executeTimeDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">执行耗时(ms)：</span>
    <span class="col-sm-8" id=executeCostTimeDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">IP地址：</span>
    <span class="col-sm-8" id=executeIpDetail></span>
</div>


    </div>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
</div>
