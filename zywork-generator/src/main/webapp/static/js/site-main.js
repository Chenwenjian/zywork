const contextPath = '';

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

function refreshTable(tableId) {
    $('#' + tableId).bootstrapTable('refresh');
}

function destroyTable(tableId) {
    $('#' + tableId).bootstrapTable('destroy');
}

function formatTableIndex(value, row, index) {
    let options = $('#data-list').bootstrapTable('getOptions');
    return (options.pageNumber - 1) * options.pageSize + index + 1;
}

function formatDate(value, row, index) {
    return timestampToDatetime(value);
}

function showModal(modalId) {
    $('#' + modalId).modal('show');
}

function showAddModal(modalId, formId, validateFields) {
    let modal = $('#' + modalId);
    modal.modal('show');
    validateForm(formId, 'btn-save', validateFields);
    modal.on('hidden.bs.modal', function (e) {
        $('#' + formId)[0].reset();
        resetValidateForm(formId);
    });
}

function showRemoteAddModal(modalId, url, formId, validateFields) {
    let modal = $('#' + modalId);
    modal.on('shown.bs.modal', function (e) {
        validateForm(formId, 'btn-save', validateFields);
    });
    modal.on('hidden.bs.modal', function (e) {
        $('#' + formId)[0].reset();
        resetValidateForm(formId);
    });
    modal.modal({
        remote: url
    });
}

function showDetailModal(modalId, row, rowDetailFieldTitles) {
    let modal = $('#' + modalId);
    $.each(rowDetailFieldTitles, function (field, title) {
        let fieldArray = field.split("-");
        let value = row[fieldArray[0]];
        $('#' + fieldArray[0] + 'Detail').text((value === null ? '-' : fieldArray[1] !== undefined && fieldArray[1] === 'date' ? timestampToDatetime(value) : value));
    });
}

function showRemoteDetailModal(modalId, url, row, rowDetailFieldTitles) {
    let modal = $('#' + modalId);
    modal.on('shown.bs.modal', function (e) {
        $.each(rowDetailFieldTitles, function (field, title) {
            let fieldArray = field.split("-");
            let value = row[fieldArray[0]];
            $('#' + fieldArray[0] + 'Detail').text((value === null ? '-' : fieldArray[1] !== undefined && fieldArray[1] === 'date' ? timestampToDatetime(value) : value));
        });
    });
    modal.on('hidden.bs.modal', function (e) {
        $.each(rowDetailFieldTitles, function (field, title) {
            let fieldArray = field.split("-");
            let value = row[fieldArray[0]];
            $('#' + fieldArray[0] + 'Detail').text("");
        });
    });
    modal.modal({
        remote: url
    });
}

function showEditModal(modalId, formId, row, validateFields) {
    let modal = $('#' + modalId);
    let form = $('#' + formId);
    modal.modal('show');
    form.autofill(row);
    showDatetimeInEditModal(formId, row);
    validateForm(formId, 'edit-save', validateFields);
    modal.on('hidden.bs.modal', function (e) {
        form[0].reset();
        resetValidateForm(formId);
    });
}

function showRemoteEditModal(modalId, url, formId, row, validateFields) {
    let modal = $('#' + modalId);
    modal.on('shown.bs.modal', function (e) {
        $('#' + formId).autofill(row);
        showDatetimeInEditModal(formId, row);
        validateForm(formId, 'edit-save', validateFields);
    });
    modal.on('hidden.bs.modal', function (e) {
        $('#' + formId)[0].reset();
        resetValidateForm(formId);
    });
    modal.modal({
        remote: url
    });
}

function showDatetimeInEditModal(formId, row) {
    $('#' + formId + ' .input-datetime').each(function () {
        let date = timestampToDatetime(row[$(this).attr('name')]);
        $(this).val(date === '-' ? '' : date);
    });
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
        theme: 'bootstrap'
    });
}

function emptySelect2(selectId) {
    $('#' + selectId).select2('destroy').empty();
}

function initDate(dateId) {
    let date;
    if (dateId === undefined) {
        date = $('.form_date');
    } else {
        date = $('#' + dateId);
    }
    date.datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        todayHighlight: true,
        weekStart: 1,
        todayBtn:  true,
        autoclose: true
    });
}

function initDatetime(datetimeId) {
    let date;
    if (datetimeId === undefined) {
        date = $('.form_datetime');
    } else {
        date = $('#' + dateId);
    }
    date.datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd hh:ii:ss',
        todayHighlight: true,
        weekStart: 1,
        todayBtn:  true,
        autoclose: true
    });
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
    let form = $('#' + formId);
    form.bootstrapValidator('validate');
    if (form.data('bootstrapValidator').isValid()) {
        $.post(contextPath + postUrl,
            form.serialize(),
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
}

function saveOrEditWithFile(modalId, formId, postUrl, tableId, tableUrl) {
    let form = $('#' + formId);
    form.bootstrapValidator('validate');
    if (form.data('bootstrapValidator').isValid()) {
        let formData = new FormData(form[0]);
        $.ajax({
            url: contextPath + postUrl,
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                if (data.code === 200) {
                    swalSuccess(data.message);
                    hideModal(modalId);
                    refreshTable(tableId, contextPath + tableUrl);
                } else {
                    swalError(data.message);
                }
            },
            error: function (data) {
                swalError('提交失败');
            }
        });
    }
}

function active(url, id, status, tableId, tableUrl) {
    $.post(contextPath + url,
        {
            id: id,
            status: status
        },
        function (data) {
            if (data.code === 200) {
                swalSuccess(data.message);
                refreshTable(tableId, contextPath + tableUrl);
            } else {
                swalError(data.message);
            }
        }, 'json'
    );
}

function remove(url, tableId, tableUrl) {
    swal({
        title: '确定删除吗？',
        text: '你将无法恢复删除的数据！',
        type: 'warning',
        showCancelButton: true
    }).then((result) =>  {
        if (result.value) {
            $.get(contextPath + url,
                function (data){
                    if (data.code === 200) {
                        swalSuccess(data.message);
                        refreshTable(tableId, contextPath + tableUrl);
                    } else {
                        swalError(data.message);
                    }
                }, 'json'
            );
        }
    });
}

function batchRemove(url, tableId, tableUrl, tableIdField) {
    let rows = $('#' + tableId).bootstrapTable('getSelections');
    if (rows && rows.length > 0) {
        swal({
            title: '确定删除吗？',
            text: '你将无法恢复删除的数据！',
            type: 'warning',
            showCancelButton: true
        }).then((result) =>  {
            if (result.value) {
                let ids = '';
                $.each(rows, function (index, row) {
                    if (ids === '') {
                        ids += row[tableIdField];
                    } else {
                        ids += ',' + row[tableIdField];
                    }
                });
                $.post(contextPath + url,
                    {
                        ids: ids
                    },
                    function (data) {
                        if (data.code === 200) {
                            swalSuccess(data.message);
                            refreshTable(tableId, contextPath + tableUrl);
                        } else {
                            swalError(data.message);
                        }
                    }, 'json'
                );
            }
        });
    } else {
        swalWarning('请选择需要批量删除的数据');
    }
}

function timestampToDatetime(value) {
    if (value === undefined || value === null || value === '') {
        return '-';
    } else {
        let date = new Date(value);
        let year = date.getFullYear();
        let month = date.getMonth() + 1;
        let day = date.getDate();
        let hour = date.getHours();
        let minute = date.getMinutes();
        let second = date.getSeconds();
        month = month < 10 ? '0' + month : month;
        day = day < 10 ? '0' + day : day;
        hour = hour < 10 ? '0' + hour : hour;
        minute = minute < 10 ? '0' + minute : minute;
        second = second < 10 ? '0' + second : second;
        return year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
    }
}

function showSearchForm(formId) {
    $('#' + formId).fadeIn();
}

function hideSearchForm(tableId, formId) {
    let form = $('#' + formId);
    form.fadeOut();
    form[0].reset();
    refreshTable(tableId);
}

function doSearch(tableId) {
    refreshTable(tableId);
}

function doSearchAll(tableId, formId) {
    $('#' + formId)[0].reset();
    refreshTable(tableId);
}

function isNumInArray(array, num) {
    for (let i = 0, len = array.length; i < len; i++) {
        if (array[i] === num) {
            return true;
        }
    }
    return false;
}

function validateForm(formId, btnId, validateFields) {
    $('#' + formId).bootstrapValidator({
        live: 'enabled',
        // excluded: [':disabled', ':hidden', ':not(:visible)'],
        submitButtons: '#' + btnId,
        message: '请输入或选择合法的值',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: validateFields
    });
}

function resetValidateForm(formId) {
    let formValidator = $('#' + formId).data('bootstrapValidator');
    if (formValidator !== undefined) {
        formValidator.destroy();
    }
}