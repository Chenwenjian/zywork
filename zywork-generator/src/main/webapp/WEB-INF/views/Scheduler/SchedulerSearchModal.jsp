<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
    <h4 class="modal-title">选择</h4>
</div>
<div class="modal-body">
    <table id="scheduler-data-list" data-classes="table table-hover text-nowrap" data-toolbar="#scheduler-toolbar"></table>

    <div id="scheduler-toolbar">
        <button class="btn btn-primary" onclick="">
            <i class="fa fa-key"></i>
            确认
        </button>
        <button class="btn btn-primary" onclick="showSearchForm('scheduler-search-form')">
            <i class="fa fa-eye"></i>
            高级搜索
        </button>
        <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>

        <form id="scheduler-search-form" class="row form-horizontal search-form" style="display: none;">
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
                <button type="button" class="btn btn-primary" onclick="doSearch('scheduler-data-list')">
                    <i class="fa fa-search"></i>
                    搜索
                </button>
                <button type="button" class="btn btn-primary" onclick="doSearchAll('scheduler-data-list', 'scheduler-search-form')">
                    <i class="fa fa-search"></i>
                    搜索所有
                </button>
                <button type="button" class="btn btn-primary" onclick="hideSearchForm('scheduler-data-list', 'scheduler-search-form')">
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
