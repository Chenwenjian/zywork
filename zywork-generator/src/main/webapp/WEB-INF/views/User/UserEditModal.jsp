<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
    <h4 class="modal-title">编辑</h4>
</div>
<div class="modal-body">
    <form id="edit-form" class="form-horizontal row" enctype="multipart/form-data">
        <input type="hidden" name="id"/>
        <div class="form-group">
    <label for="email" class="col-sm-2 control-label">邮箱：</label>
    <div class="col-sm-10">
        <input id="email" name="email" class="form-control" placeholder="请输入邮箱"/>
    </div>
</div>

<div class="form-group">
    <label for="phone" class="col-sm-2 control-label">手机号：</label>
    <div class="col-sm-10">
        <input id="phone" name="phone" class="form-control" placeholder="请输入手机号"/>
    </div>
</div>

<div class="form-group">
    <label for="accountName" class="col-sm-2 control-label">账户名：</label>
    <div class="col-sm-10">
        <input id="accountName" name="accountName" class="form-control" placeholder="请输入账户名"/>
    </div>
</div>

<div class="form-group">
    <label for="password" class="col-sm-2 control-label">密码：</label>
    <div class="col-sm-10">
        <input id="password" name="password" class="form-control" placeholder="请输入密码"/>
    </div>
</div>

<div class="form-group">
    <label for="salt" class="col-sm-2 control-label">加密盐值：</label>
    <div class="col-sm-10">
        <input id="salt" name="salt" class="form-control" placeholder="请输入加密盐值"/>
    </div>
</div>


    </form>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
    <button id="btn-edit" type="button" class="btn btn-primary" onclick="saveOrEdit('btn-edit', 'edit-modal', 'edit-form', '/user/update', 'data-list', '/user/pager-cond')">确认</button>
</div>

<script>
    initDatetime();
</script>
