<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
    <h4 class="modal-title">选择</h4>
</div>
<div class="modal-body">
    <table id="sys-log-data-list" data-classes="table table-hover text-nowrap" data-toolbar="#sys-log-toolbar"></table>

    <div id="sys-log-toolbar">
        <button class="btn btn-primary" onclick="">
            <i class="fa fa-key"></i>
            确认
        </button>
        <button class="btn btn-primary" onclick="showSearchForm('sys-log-search-form')">
            <i class="fa fa-eye"></i>
            高级搜索
        </button>
        <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>

        <form id="sys-log-search-form" class="row form-horizontal search-form" style="display: none;">
            <div class="col-sm-6 form-group">
    <label for="userAccountSearch" class="col-sm-4 control-label">用户账号：</label>
    <div class="col-sm-8">
        <input id="userAccountSearch" name="userAccount" class="form-control" placeholder="请输入用户账号">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="descriptionSearch" class="col-sm-4 control-label">执行说明：</label>
    <div class="col-sm-8">
        <input id="descriptionSearch" name="description" class="form-control" placeholder="请输入执行说明">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="executeClassSearch" class="col-sm-4 control-label">类名称：</label>
    <div class="col-sm-8">
        <input id="executeClassSearch" name="executeClass" class="form-control" placeholder="请输入类名称">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="executeMethodSearch" class="col-sm-4 control-label">方法名称：</label>
    <div class="col-sm-8">
        <input id="executeMethodSearch" name="executeMethod" class="form-control" placeholder="请输入方法名称">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="executeTimeStartSearch" class="col-sm-4 control-label">开始执行时间(开始)：</label>
    <div class="input-group date form_datetime col-sm-8" style="padding-left: 15px; padding-right: 15px;">
        <input id="executeTimeStartSearch" name="executeTimeStart" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择开始执行时间(开始)"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="executeTimeEndSearch" class="col-sm-4 control-label">开始执行时间(结束)：</label>
    <div class="input-group date form_datetime col-sm-8" style="padding-left: 15px; padding-right: 15px;">
        <input id="executeTimeEndSearch" name="executeTimeEnd" class="form-control input-datetime" type="text" value="" readonly placeholder="请选择开始执行时间(结束)"/>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="executeCostTimeSearch" class="col-sm-4 control-label">执行耗时(ms)：</label>
    <div class="col-sm-8">
        <input id="executeCostTimeSearch" name="executeCostTime" class="form-control" placeholder="请输入执行耗时(ms)">
    </div>
</div>

<div class="col-sm-6 form-group">
    <label for="executeIpSearch" class="col-sm-4 control-label">IP地址：</label>
    <div class="col-sm-8">
        <input id="executeIpSearch" name="executeIp" class="form-control" placeholder="请输入IP地址">
    </div>
</div>



            <div class="col-sm-6 form-group text-right" style="padding-right: 30px;">
                <button type="button" class="btn btn-primary" onclick="doSearch('sys-log-data-list')">
                    <i class="fa fa-search"></i>
                    搜索
                </button>
                <button type="button" class="btn btn-primary" onclick="doSearchAll('sys-log-data-list', 'sys-log-search-form')">
                    <i class="fa fa-search"></i>
                    搜索所有
                </button>
                <button type="button" class="btn btn-primary" onclick="hideSearchForm('sys-log-data-list', 'sys-log-search-form')">
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
