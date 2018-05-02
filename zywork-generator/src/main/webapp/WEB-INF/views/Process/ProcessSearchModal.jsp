<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
    <h4 class="modal-title">选择</h4>
</div>
<div class="modal-body">
    <table id="process-data-list" data-classes="table table-hover text-nowrap" data-toolbar="#process-toolbar"></table>

    <div id="process-toolbar">
        <button class="btn btn-primary" onclick="">
            <i class="fa fa-key"></i>
            确认
        </button>
        <button class="btn btn-primary" onclick="showSearchForm('process-search-form')">
            <i class="fa fa-eye"></i>
            高级搜索
        </button>
        <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>

        <form id="process-search-form" class="row form-horizontal search-form" style="display: none;">
            <div class="col-sm-6 form-group">
    <label for="nameSearch" class="col-sm-4 control-label">流程名字：</label>
    <div class="col-sm-8">
        <input id="nameSearch" name="name" class="form-control" placeholder="请输入流程名字">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="filePathSearch" class="col-sm-4 control-label">ZIP文件路径：</label>
    <div class="col-sm-8">
        <input id="filePathSearch" name="filePath" class="form-control" placeholder="请输入ZIP文件路径">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="descriptionSearch" class="col-sm-4 control-label">流程描述：</label>
    <div class="col-sm-8">
        <input id="descriptionSearch" name="description" class="form-control" placeholder="请输入流程描述">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="userIdSearch" class="col-sm-4 control-label">上传人编号：</label>
    <div class="col-sm-8">
        <input id="userIdSearch" name="userId" class="form-control" placeholder="请输入上传人编号">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="createTimeStartSearch" class="col-sm-4 control-label">上传时间(开始)：</label>
    <div class="input-group date form_datetime col-sm-8" style="padding-left: 15px; padding-right: 15px;">
        <input id="createTimeStartSearch" name="createTimeStart" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择上传时间(开始)"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="createTimeEndSearch" class="col-sm-4 control-label">上传时间(结束)：</label>
    <div class="input-group date form_datetime col-sm-8" style="padding-left: 15px; padding-right: 15px;">
        <input id="createTimeEndSearch" name="createTimeEnd" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择上传时间(结束)"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="updateTimeStartSearch" class="col-sm-4 control-label">更新时间(开始)：</label>
    <div class="input-group date form_datetime col-sm-8" style="padding-left: 15px; padding-right: 15px;">
        <input id="updateTimeStartSearch" name="updateTimeStart" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择更新时间(开始)"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="updateTimeEndSearch" class="col-sm-4 control-label">更新时间(结束)：</label>
    <div class="input-group date form_datetime col-sm-8" style="padding-left: 15px; padding-right: 15px;">
        <input id="updateTimeEndSearch" name="updateTimeEnd" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择更新时间(结束)"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="isDeploySearch" class="col-sm-4 control-label">是否部署：</label>
    <div class="col-sm-8">
        <input id="isDeploySearch" name="isDeploy" class="form-control" placeholder="请输入是否部署">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="deployTimeStartSearch" class="col-sm-4 control-label">部署时间(开始)：</label>
    <div class="input-group date form_datetime col-sm-8" style="padding-left: 15px; padding-right: 15px;">
        <input id="deployTimeStartSearch" name="deployTimeStart" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择部署时间(开始)"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="deployTimeEndSearch" class="col-sm-4 control-label">部署时间(结束)：</label>
    <div class="input-group date form_datetime col-sm-8" style="padding-left: 15px; padding-right: 15px;">
        <input id="deployTimeEndSearch" name="deployTimeEnd" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择部署时间(结束)"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="isActiveSearch" class="col-sm-4 control-label">是否激活：</label>
    <div class="col-sm-8">
        <input id="isActiveSearch" name="isActive" class="form-control" placeholder="请输入是否激活">
    </div>
</div>



            <div class="col-sm-6 form-group text-right" style="padding-right: 30px;">
                <button type="button" class="btn btn-primary" onclick="doSearch('process-data-list')">
                    <i class="fa fa-search"></i>
                    搜索
                </button>
                <button type="button" class="btn btn-primary" onclick="doSearchAll('process-data-list', 'process-search-form')">
                    <i class="fa fa-search"></i>
                    搜索所有
                </button>
                <button type="button" class="btn btn-primary" onclick="hideSearchForm('process-data-list', 'process-search-form')">
                    <i class="fa fa-eye-slash"></i>
                    取消搜索
                </button>
            </div>
        </form>
    </div>
</div>

<script>
    initDatetime();
</script>
