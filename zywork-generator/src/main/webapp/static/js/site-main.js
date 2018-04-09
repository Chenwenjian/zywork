const contextPath = "";

function loadTable(tableId, url, columns) {
    destroyTable(tableId);
    $('#' + tableId).bootstrapTable({
        url: url,
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
        columns: columns
    });
}

function refreshTable(tableId, url) {
    $('#' + tableId).bootstrapTable('refresh', {
        url: url
    })
}

function destroyTable(tableId) {
    $('#' + tableId).bootstrapTable('destroy');
}

function formatTableIndex(value, row, index) {
    let options = $('#data-list').bootstrapTable('getOptions');
    return (options.pageNumber - 1) * options.pageSize + index + 1;
}

function showModal(modalId) {
    $('#' + modalId).modal('show');
}

function showEditModal(modalId, formId, row) {
    $('#' + modalId).modal('show');
    $('#' + formId).autofill(row);
}

function hideModal(modalId) {
    $('#' + modalId).modal('hide');
}

function fixModalScroll(modalId) {
    let body = $('body');
    $('#' + modalId).on('shown.bs.modal', function (e) {
        body.addClass('modal-open');
        body.css('padding-right','6px');
    })
}

function initICheck(color) {
    $('.iCheck').iCheck({
        checkboxClass: 'icheckbox_square-' + color,
        radioClass: 'iradio_square-' + color,
        increaseArea: '20%'
    });
}

function initSelect2(selectId, jsonData) {
    $('#' + selectId).select2({
        data: jsonData,
        language: 'zh-CN',
        placeholder:'请选择',
        width: '100%',
        theme: "bootstrap"
    });
}

function emptySelect2(selectId) {
    $('#' + selectId).select2('destroy').empty();
}

function swalInfo(message) {
    swal('提示', message, 'info');
}

function swalWarning(message) {
    swal('提示', message, 'warning');
}

function swalSuccess(message) {
    swal('提示', message, 'success');
}

function swalError(message) {
    swal('提示', message, 'error');
}

function saveOrEdit(modalId, formId, postUrl, tableId, tableUrl) {
    $.post(contextPath + postUrl,
        $('#' + formId).serialize(),
        function (data) {
            if (data.code === 200) {
                swalSuccess(data.message);
                hideModal(modalId);
                refreshTable(tableId, contextPath + tableUrl);
            } else {
                swalError(data.message);
            }
        }, 'json'
    );
}

function saveOrEditWithFile(modalId, formId, postUrl, tableId, tableUrl) {
    let formData = new FormData($('#' + formId)[0]);
    $.ajax({
        url: contextPath + postUrl,
        type: 'POST',
        data:formData,
        contentType: false,
        processData: false,
        success:function (data) {
            if (data.code === 200) {
                swalSuccess(data.message);
                hideModal(modalId);
                refreshTable(tableId, contextPath + tableUrl);
            } else {
                swalError(data.message)
            }
        },
        error: function (data) {
            swalError("提交失败")
        }
    });
}

function remove(url, tableId, tableUrl) {
    $.get(contextPath + url,
        function (data){
            if (data.code === 200) {
                swalSuccess(data.message);
                refreshTable(tableId, contextPath + tableUrl);
            } else {
                swalError(data.message);
            }
        }, 'json')
}