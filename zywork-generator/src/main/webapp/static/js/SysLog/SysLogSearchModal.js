function sys-logSearchTable() {
    destroyTable('sys-log-data-list');
    $('#sys-log-data-list').bootstrapTable({
        url: contextPath + '/sys-log/pager-cond',
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
	title: '用户编号',
	field: 'userId',
	align: 'center',
	sortable: true
},
{
	title: '执行说明',
	field: 'description',
	align: 'center',
	sortable: true
},
{
	title: '类名称',
	field: 'executeClass',
	align: 'center',
	sortable: true
},
{
	title: '方法名称',
	field: 'executeMethod',
	align: 'center',
	sortable: true
},
{
	title: '开始执行时间',
	field: 'executeTime',
	align: 'center',
	sortable: true,
	formatter: formatDate
},
{
	title: '执行耗时(ms)',
	field: 'executeCostTime',
	align: 'center',
	sortable: true
},
{
	title: 'IP地址',
	field: 'executeIp',
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
    $.each($('#sys-log-search-form').serializeArray(), function(index, field){
        query[field.name] = field.value;
    });
    return query;
}

function sys-logSearchModal(modalId, url) {
    let modal = $('#' + modalId);
    modal.on('shown.bs.modal', function (e) {
        sys-logSearchTable();
        initDatetime();
    });
    modal.modal({
        remote: url
    });
}
