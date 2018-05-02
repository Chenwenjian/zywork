function schedulerSearchTable() {
    destroyTable('scheduler-data-list');
    $('#scheduler-data-list').bootstrapTable({
        url: contextPath + '/scheduler/pager-cond',
        dataType: 'json',
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        idField: 'id',
        pagination: true,
        sidePagination: 'server',
        pageNumber: 1,
        pageSize: 10,
        pageList: [10, 20, 30],
        queryParams: queryParams,
        singleSelect: false,
        maintainSelected: true,
        striped: true,
        search: false,
        showColumns: true,
        showRefresh: true,
        showToggle: true,
        icons: {
            refresh: 'glyphicon-refresh icon-refresh',
            toggle: 'glyphicon-list-alt icon-list-alt',
            columns: 'glyphicon-th icon-th',
            detailOpen: 'glyphicon-plus icon-plus',
            detailClose: 'glyphicon-minus icon-minus'
        },
        columns: [
                     {
	field: '_checkbox',
	checkbox: true
},
{
	field: 'id',
	align: 'center',
	visible: false
},
{
	title: '序号',
	field: '_number',
	align: 'center',
	formatter: formatTableIndex
},
{
	title: '作业名称',
	field: 'name',
	align: 'center',
	sortable: true
},
{
	title: '作业完整类名',
	field: 'className',
	align: 'center',
	sortable: true
},
{
	title: 'CRON表达式',
	field: 'cronExpression',
	align: 'center',
	sortable: true
},
{
	title: '作业组名称',
	field: 'groupName',
	align: 'center',
	sortable: true
},
{
	title: '触发器名称',
	field: 'triggerName',
	align: 'center',
	sortable: true
},
{
	title: '触发器组',
	field: 'triggerGroup',
	align: 'center',
	sortable: true
},
{
	title: '作业描述',
	field: 'description',
	align: 'center',
	sortable: true
},
{
	title: '作业状态',
	field: 'jobStatus',
	align: 'center',
	sortable: true
},
{
	title: '创建时间',
	field: 'createTime',
	align: 'center',
	sortable: true,
	formatter: formatDate
},
{
	title: '最近一次更新时间',
	field: 'updateTime',
	align: 'center',
	sortable: true,
	formatter: formatDate
},
{
	title: '是否可用',
	field: 'isActive',
	align: 'center',
	sortable: true
}
                 ]
    });
}

function queryParams(params) {
    let query = {
        limit:params.limit,
        offset:params.offset,
        sortOrder: params.order
    };
    $.each($('#scheduler-search-form').serializeArray(), function(index, field){
        query[field.name] = field.value;
    });
    return query;
}

function schedulerSearchModal(modalId, url) {
    let modal = $('#' + modalId);
    modal.on('shown.bs.modal', function (e) {
        schedulerSearchTable();
        initDatetime();
    });
    modal.modal({
        remote: url
    });
}
