<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
    <h4 class="modal-title">选择</h4>
</div>
<div class="modal-body">
    <table id="{zywork.moduleName}-data-list" data-classes="table table-hover text-nowrap" data-toolbar="#{zywork.moduleName}-toolbar"></table>

    <div id="{zywork.moduleName}-toolbar">
        <button class="btn btn-primary" onclick="">
            <i class="fa fa-key"></i>
            确认
        </button>
        <button class="btn btn-primary" onclick="showSearchForm('{zywork.moduleName}-search-form')">
            <i class="fa fa-eye"></i>
            高级搜索
        </button>

        <form id="{zywork.moduleName}-search-form" class="row form-horizontal search-form" style="display: none;">
            {zywork.searchFormFields}

            <div class="col-sm-6 form-group text-right" style="padding-right: 30px;">
                <button type="button" class="btn btn-primary" onclick="doSearch('{zywork.moduleName}-data-list')">
                    <i class="fa fa-search"></i>
                    搜索
                </button>
                <button type="button" class="btn btn-primary" onclick="doSearchAll('{zywork.moduleName}-data-list', '{zywork.moduleName}-search-form')">
                    <i class="fa fa-search"></i>
                    搜索所有
                </button>
                <button type="button" class="btn btn-primary" onclick="hideSearchForm('{zywork.moduleName}-data-list', '{zywork.moduleName}-search-form')">
                    <i class="fa fa-eye-slash"></i>
                    取消搜索
                </button>
            </div>
        </form>
    </div>
</div>