<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
    <h4 class="modal-title">添加</h4>
</div>
<div class="modal-body">
    <form id="add-form" class="form-horizontal row" enctype="multipart/form-data">
        <div class="form-group">
    <label for="name" class="col-sm-2 control-label">流程名字：</label>
    <div class="col-sm-10">
        <input id="name" name="name" class="form-control" placeholder="请输入流程名字"/>
    </div>
</div>

<div class="form-group">
    <label for="filePath" class="col-sm-2 control-label">ZIP文件路径：</label>
    <div class="col-sm-10">
        <input id="filePath" name="filePath" class="form-control" placeholder="请输入ZIP文件路径"/>
    </div>
</div>

<div class="form-group">
    <label for="description" class="col-sm-2 control-label">流程描述：</label>
    <div class="col-sm-10">
        <input id="description" name="description" class="form-control" placeholder="请输入流程描述"/>
    </div>
</div>

<div class="form-group">
    <label for="userId" class="col-sm-2 control-label">上传人编号：</label>
    <div class="col-sm-10">
        <input id="userId" name="userId" class="form-control" placeholder="请输入上传人编号"/>
    </div>
</div>

<div class="form-group">
    <label for="isDeploy" class="col-sm-2 control-label">是否部署：</label>
    <div class="col-sm-10">
        <input id="isDeploy" name="isDeploy" class="form-control" placeholder="请输入是否部署"/>
    </div>
</div>

<div class="form-group">
    <label for="deployTime" class="col-sm-2 control-label">部署时间：</label>
    <div class="input-group date form_datetime col-sm-10" style="padding-left: 15px; padding-right: 15px;">
        <input id="deployTime" name="deployTime" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择部署时间"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
	    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>


    </form>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
    <button id="btn-save" type="button" class="btn btn-primary" onclick="saveOrEdit('btn-save', 'add-modal', 'add-form', '/process/save', 'data-list', '/process/pager-cond')">确认</button>
</div>

<script>
    initDatetime();
</script>
