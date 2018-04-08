<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>{{zywork.title}}</title>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <%@include file="../master/include-css.jsp"%>
</head>
<body class="gray-bg">
<div class="row wrapper wrapper-content animated fadeInRight">
    <div class="col-sm-12">
        <table id="data-list" data-classes="table table-hover" data-toolbar="#toolbar"></table>

        <div id="toolbar">
            <button class="btn btn-primary" onclick="showModal('add-modal')">
                <i class="fa fa-plus"></i>
                添加
            </button>
            <button class="btn btn-danger">
                <i class="fa fa-remove"></i>
                批量删除
            </button>
        </div>
    </div>

</div>

<div class="modal fade" id="add-modal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加</h4>
            </div>
            <div class="modal-body">
                <form id="add-form" class="form-horizontal row" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="textNo" class="col-sm-2 control-label">公文文号：</label>

                        <div class="col-sm-10">
                            <input id="textNo" name="textNo" class="form-control" placeholder="请输入公文文号"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dept" class="col-sm-2 control-label">制文单位：</label>

                        <div class="col-sm-10">
                            <input id="dept" name="dept" class="form-control" placeholder="请选择制文单位"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="category" class="col-sm-2 control-label">公文分类：</label>

                        <div class="col-sm-10">
                            <input id="category" name="category" class="form-control" placeholder="请选择公文分类"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="type" class="col-sm-2 control-label">公文类型：</label>

                        <div class="col-sm-10">
                            <input id="type" name="type" class="form-control" placeholder="请选择公文类型"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label">公文标题：</label>

                        <div class="col-sm-10">
                            <input id="title" name="title" class="form-control" placeholder="请输入公文标题"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="file" class="col-sm-2 control-label">公文正文：</label>

                        <div class="col-sm-10">
                            <input id="file" name="file" type="file" class="form-control" placeholder="请上传公文正文"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="suggest" class="col-sm-2 control-label">办理意见：</label>

                        <div class="col-sm-10">
                            <textarea id="suggest" name="suggest" class="form-control" rows="3" placeholder="请输入办理意见"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否加急：</label>

                        <div class="col-sm-10">
                            <input type="radio" name="urgent" value="0"/>否
                            <input type="radio" name="urgent" value="1"/>是
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="saveOrEdit('add-modal', 'add-form', 'postUrl', 'data-list', 'tableUrl')">确认</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="edit-modal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">编辑</h4>
            </div>
            <div class="modal-body">
                <form id="edit-form" class="form-horizontal row" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="textNoEdit" class="col-sm-2 control-label">公文文号：</label>

                        <div class="col-sm-10">
                            <input id="textNoEdit" name="textNo" class="form-control" placeholder="请输入公文文号"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="deptEdit" class="col-sm-2 control-label">制文单位：</label>

                        <div class="col-sm-10">
                            <input id="deptEdit" name="dept" class="form-control" placeholder="请选择制文单位"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="categoryEdit" class="col-sm-2 control-label">公文分类：</label>

                        <div class="col-sm-10">
                            <input id="categoryEdit" name="category" class="form-control" placeholder="请选择公文分类"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="typeEdit" class="col-sm-2 control-label">公文类型：</label>

                        <div class="col-sm-10">
                            <input id="typeEdit" name="type" class="form-control" placeholder="请选择公文类型"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="titleEdit" class="col-sm-2 control-label">公文标题：</label>

                        <div class="col-sm-10">
                            <input id="titleEdit" name="title" class="form-control" placeholder="请输入公文标题"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="fileEdit" class="col-sm-2 control-label">公文正文：</label>

                        <div class="col-sm-10">
                            <input id="fileEdit" name="file" type="file" class="form-control" placeholder="请上传公文正文"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="suggestEdit" class="col-sm-2 control-label">办理意见：</label>

                        <div class="col-sm-10">
                            <textarea id="suggestEdit" name="suggest" class="form-control" rows="3" placeholder="请输入办理意见"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="saveOrEdit('add-modal', 'add-form', 'postUrl', 'data-list', 'tableUrl')">确认</button>
            </div>
        </div>
    </div>
</div>

</body>
<%@include file="../master/include-js.jsp"%>
<script src="<%=path%>/static/js/tpl/view.js"></script>
</html>
