$(function () {
    loadTable();
    initICheck('green');
    initDatetime();
});

function loadTable() {
    destroyTable('data-list');
    $('#data-list').bootstrapTable({
        url: contextPath + '{zywork.tableUrl}',
        dataType: 'json',
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        idField: '{zywork.idField}',
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
        detailView: true,
        detailFormatter: formatDetail,
        icons: {
            refresh: 'glyphicon-refresh icon-refresh',
            toggle: 'glyphicon-list-alt icon-list-alt',
            columns: 'glyphicon-th icon-th',
            detailOpen: 'glyphicon-plus icon-plus',
            detailClose: 'glyphicon-minus icon-minus'
        },
        columns: [
                     {zywork.tableFields},
                     {
                         title: '操作',
                         field: '_operation',
                         align: 'center',
                         events: operateEvents,
                         formatter: formatOperators,
                         class: 'operation-column'
                     }
                 ]
    });
}

function formatOperators(value, row, index) {
    return [
        '<div class="btn-group">',
        '<button type="button" class="to-edit btn btn-primary"><i class="fa fa-edit"></i>&nbsp;修改</button>',
        '<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">',
        '<span class="caret"></span>',
        '<span class="sr-only">Toggle Dropdown</span>',
        '</button>',
        '<ul class="dropdown-menu">',
        '<li><a href="javascript:void(0)" class="to-remove"><i class="fa fa-remove"></i>&nbsp;删除</a></li>',
        '</ul>',
        '</div>'
    ].join('')
}

window.operateEvents = {
    'click .to-edit': function (e, value, row, index) {
        showEditModal('edit-modal', 'edit-form', row);
    },
    'click .to-remove': function (e, value, row, index) {
        swal({
            title: "确定删除吗？",
            text: "你将无法恢复删除的数据！",
            type: "warning",
            showCancelButton: true
        }).then((result) =>  {
            if (result.value) {
                remove('{zywork.removeUrl}' + row.{zywork.idField}, 'data-list', '{zywork.tableUrl}');
            }
        })
    }
};

function formatDetail(index, row) {
    let detail = '';
    let idx = 0;
    let titles = [{zywork.rowDetailTitles}];
    let dateIndexArray = [{zywork.dateIndex}];
    $.each(row, function(key, value) {
        if (value !== undefined) {
            detail += '<div class="col-xs-12 col-sm-4 col-md-2 col-lg-2">'
                + '<span class="row-detail-title">'
                + titles[idx]
                + ':</span>'
                + '</div><div class="col-xs-12 col-sm-8 col-md-4 col-lg-4">'
                + (value === null ? '-' : isNumInArray(dateIndexArray, idx) ? timestampToDatetime(value) : value)
                + '</div>';
            idx++;
        }
    });
    return detail;
}

function queryParams(params) {
    let query = {
        limit:params.limit,
        offset:params.offset,
        sortOrder: params.order
    };
    $.each($('#search-form').serializeArray(), function(index, field){
        query[field.name] = field.value;
    });
    return query;
}