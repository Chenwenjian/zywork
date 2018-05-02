function processSearchTable() {
    destroyTable('process-data-list');
    $('#process-data-list').bootstrapTable({
        url: contextPath + '/process/pager-cond',
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
	title: '流程名字',
	field: 'name',
	align: 'center',
	sortable: true
},
{
	title: 'ZIP文件路径',
	field: 'filePath',
	align: 'center',
	sortable: true
},
{
	title: '流程描述',
	field: 'description',
	align: 'center',
	sortable: true
},
{
	title: '上传人编号',
	field: 'userId',
	align: 'center',
	sortable: true
},
{
	title: '上传时间',
	field: 'createTime',
	align: 'center',
	sortable: true,
	formatter: formatDate
},
{
	title: '更新时间',
	field: 'updateTime',
	align: 'center',
	sortable: true,
	formatter: formatDate
},
{
	title: '是否部署',
	field: 'isDeploy',
	align: 'center',
	sortable: true
},
{
	title: '部署时间',
	field: 'deployTime',
	align: 'center',
	sortable: true,
	formatter: formatDate
},
{
	title: '是否激活',
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
    $.each($('#process-search-form').serializeArray(), function(index, field){
        query[field.name] = field.value;
    });
    return query;
}

function processSearchModal(modalId, url) {
    let modal = $('#' + modalId);
    modal.on('shown.bs.modal', function (e) {
        processSearchTable();
        initDatetime();
    });
    modal.modal({
        remote: url
    });
}
