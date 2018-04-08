$(function () {
    loadTable();
    initICheck('green');
});

function loadTable() {
    destroyTable('data-list');
    $('#data-list').bootstrapTable({
        url: contextPath + '{zywork.tableUrl}',
        dataType: 'json',
        method: 'get',
        singleSelect: false,
        idField: 'id',
        striped: true,
        pagination: true,
        sidePagination: 'server',
        maintainSelected: true,
        search: true,
        showColumns: true,
        showRefresh: true,
        showToggle: true,
        detailView: true,
        icons: {
            refresh: 'glyphicon-refresh icon-refresh',
            toggle: 'glyphicon-list-alt icon-list-alt',
            columns: 'glyphicon-th icon-th'
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
                remove(row, '{zywork.removeUrl}', 'data-list', '{zywork.tableUrl}');
            }
        })
    }
};