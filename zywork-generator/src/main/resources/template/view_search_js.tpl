function {beanNameLowerCase}SearchTable() {
    destroyTable('{zywork.moduleName}-data-list');
    $('#{zywork.moduleName}-data-list').bootstrapTable({
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
        icons: {
            refresh: 'glyphicon-refresh icon-refresh',
            toggle: 'glyphicon-list-alt icon-list-alt',
            columns: 'glyphicon-th icon-th',
            detailOpen: 'glyphicon-plus icon-plus',
            detailClose: 'glyphicon-minus icon-minus'
        },
        columns: [
                     {zywork.tableFields}
                 ]
    });
}

function queryParams(params) {
    let query = {
        limit:params.limit,
        offset:params.offset,
        sortOrder: params.order
    };
    $.each($('#{zywork.moduleName}-search-form').serializeArray(), function(index, field){
        query[field.name] = field.value;
    });
    return query;
}

function {beanNameLowerCase}SearchModal(modalId, url) {
    let modal = $('#' + modalId);
    modal.on('shown.bs.modal', function (e) {
        {beanNameLowerCase}SearchTable;
        initDatetime();
    });
    modal.modal({
        remote: url
    });
}