$(function () {
    loadTable();
    initICheck('green');
    initDatetime();
});

function loadTable() {
    destroyTable('data-list');
    $('#data-list').bootstrapTable({
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
        sortable: true,
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
	title: '用户账号',
	field: 'userAccount',
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
},
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
    let strArray = [];
    strArray.push('<div class="btn-group">');
    strArray.push('<button type="button" class="to-detail btn btn-primary"><i class="fa fa-list"></i>&nbsp;详情</button>');
    strArray.push('<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">');
    strArray.push('<span class="caret"></span>');
    strArray.push('<span class="sr-only">Toggle Dropdown</span>');
    strArray.push('</button>');
    strArray.push('<ul class="dropdown-menu">');
    strArray.push('<li><a href="javascript:void(0)" class="to-edit"><i class="fa fa-edit "></i>&nbsp;修改</a></li>');
    if (row.isActive === 0) {
        strArray.push('<li><a href="javascript:void(0)" class="to-inactive"><i class="fa fa-minus-square-o text-danger"></i><span class="text-danger">&nbsp;冻结</span></a></li>');
    } else {
        strArray.push('<li><a href="javascript:void(0)" class="to-active"><i class="fa fa-check-square-o text-success"></i><span class="text-success">&nbsp;激活</span></a></li>');
    }
    strArray.push('<li><a href="javascript:void(0)" class="to-remove"><i class="fa fa-remove text-danger"></i><span class="text-danger">&nbsp;删除</span></a></li>');
    strArray.push('</ul>');
    strArray.push('</div>');
    return strArray.join('');
}

let fieldTitles = {'id':'编号','userAccount':'用户账号','description':'执行说明','executeClass':'类名称','executeMethod':'方法名称','executeTime-date':'开始执行时间','executeCostTime':'执行耗时(ms)','executeIp':'IP地址'};

window.operateEvents = {
    'click .to-detail': function (e, value, row, index) {
        showRemoteDetailModal('detail-modal', '/sys-log/detail-modal', row, fieldTitles);
    },
    'click .to-edit': function (e, value, row, index) {
        showRemoteEditModal('edit-modal', '/sys-log/edit-modal', 'edit-form', row, validateFields());
    },
    'click .to-inactive': function (e, value, row, index) {
        active('/sys-log/active', row.id, 1, 'data-list', '/sys-log/pager-cond');
    },
    'click .to-active': function (e, value, row, index) {
        active('/sys-log/active', row.id, 0, 'data-list', '/sys-log/pager-cond');
    },
    'click .to-remove': function (e, value, row, index) {
        remove('/sys-log/remove/' + row.id, 'data-list', '/sys-log/pager-cond');
    }
};

function formatDetail(index, row) {
    let detail = '';
    $.each(fieldTitles, function (field, title) {
        let fieldArray = field.split("-");
        let value = row[fieldArray[0]];
        if (value !== undefined) {
            detail += '<div class="col-xs-12 col-sm-4 col-md-2 col-lg-2">'
                + '<span class="row-detail-title">'
                + title
                + ':</span>'
                + '</div><div class="col-xs-12 col-sm-8 col-md-4 col-lg-4">'
                + (value === null ? '-' : fieldArray[1] !== undefined && fieldArray[1] === 'date' ? timestampToDatetime(value) : value)
                + '</div>';
        }
    });
    return detail;
}

function queryParams(params) {
    let query = {
        limit: params.limit,
        offset: params.offset,
        sort: params.sort,
        order: params.order
    };
    $.each($('#search-form').serializeArray(), function(index, field){
        query[field.name] = field.value;
    });
    return query;
}

function validateFields() {
    return {
        
userAccount: {
	validators: {

		stringLength: {
			min: 0,
			max: 100,
			message: '必须小于100个字符'
		}
	}
},
description: {
	validators: {

		stringLength: {
			min: 0,
			max: 500,
			message: '必须小于500个字符'
		}
	}
},
executeClass: {
	validators: {

		stringLength: {
			min: 0,
			max: 200,
			message: '必须小于200个字符'
		}
	}
},
executeMethod: {
	validators: {

		stringLength: {
			min: 0,
			max: 200,
			message: '必须小于200个字符'
		}
	}
},
executeTime: {
	validators: {
	}
},
executeCostTime: {
	validators: {
	}
},
executeIp: {
	validators: {

		stringLength: {
			min: 0,
			max: 100,
			message: '必须小于100个字符'
		}
	}
}
    };
}
