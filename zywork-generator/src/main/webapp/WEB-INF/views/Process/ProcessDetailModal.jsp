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
    <span class="col-sm-4 row-detail-title">流程名字：</span>
    <span class="col-sm-8" id=nameDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">ZIP文件路径：</span>
    <span class="col-sm-8" id=filePathDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">流程描述：</span>
    <span class="col-sm-8" id=descriptionDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">上传人编号：</span>
    <span class="col-sm-8" id=userIdDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">上传时间：</span>
    <span class="col-sm-8" id=createTimeDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">更新时间：</span>
    <span class="col-sm-8" id=updateTimeDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">是否部署：</span>
    <span class="col-sm-8" id=isDeployDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">部署时间：</span>
    <span class="col-sm-8" id=deployTimeDetail></span>
</div>

<div class="col-sm-6 detail-content">
    <span class="col-sm-4 row-detail-title">是否激活：</span>
    <span class="col-sm-8" id=isActiveDetail></span>
</div>


    </div>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
</div>
