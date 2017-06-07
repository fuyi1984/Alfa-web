<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/6
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
    <link href="../../easyui/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="../../easyui/themes/icon.css"/>

    <script src="../../easyui/jquery.min.js" type="text/javascript"></script>
    <script src="../../easyui/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="../../easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            $('#grid').datagrid({
                title: '角色管理 ',
                singleSelect: true,
                iconCls: 'icon-save',
                collapsible: true,
                nowrap: false,
                striped: true,
                loader: ajaxfindlist,
                sortName: 'createdDt',
                sortOrder: 'desc',
                remoteSort: true,
                fitColumns: true,
                fit: true,

                idField: 'roleId',

                frozenColumns: [[
                    {field: 'roleId', checkbox: true}
                ]],

                columns: [[
                    {field: 'role_name', title: '角色名称', width: 80, align: 'center'},
                    /* {field: 'statusname', title: ' 角色状态', width: 80, align: 'center'},*/
                    {field: 'menuitem', title: ' 菜单路径', width: 80, align: 'center'},
                    {field: 'roleDesc', title: ' 角色描述', width: 80, align: 'center'},
                    {field: 'createdBy', title: '创建人', width: 80, align: 'center'},
                    {
                        field: 'createdDt', title: '创建时间', width: 100, align: 'center'
                    },
                    {field: 'updatedBy', title: '更新人', width: 80, align: 'center'},
                    {
                        field: 'updatedDt', title: '更新时间', width: 100, align: 'center',
                    }
                ]],
                pagination: true,
                rownumbers: true,
                toolbar: ['-', {
                    id: 'btnSave',
                    text: '添加',
                    iconCls: 'icon-add',
                    handler: function () {

                    }
                }, '-', {
                    id: 'btnUpdate',
                    text: '修改',
                    iconCls: 'icon-save',
                    handler: function () {


                    }
                }, '-', {
                    id: 'btnDelete',
                    text: '删除',
                    disabled: false,
                    iconCls: 'icon-cut',
                    handler: function () {

                    }
                }, '-', {
                    id: 'btnSearch',
                    text: '查询',
                    disabled: false,
                    iconCls: 'icon-search',
                    handler: function () {

                    }
                }, '-'],

            });

            function ajaxfindlist(param, success, error) {

                console.log(param);

                var pagenum = param.page - 1;
                var pagesize = param.rows;
                var recordstartindex = param.page == 1 ? 0 : param.rows * (param.page - 1);
                var recordendindex = param.rows * param.page;

                $.ajax({
                    url: '/alfa-ws/rest/roles/findlist',
                    type: "post",
                    data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize=' + pagesize + '&recordstartindex=' + recordstartindex + '&recordendindex=' + recordendindex + '&roleName=',
                    contentType: 'application/json;charset=UTF-8',
                    success: function (data) {
                        console.log(data);
                        $('#grid').datagrid('clearSelections')
                        success(data);
                    },
                    error: function (xhr) {
                        console.log(xhr);
                        error(xhr.responseText);
                    }
                });
            }
        })
    </script>
</head>
<body class="easyui-layout">
<div region="center" style="padding: 5px;" border="false">
    <table id="grid">
    </table>
</div>
</body>
</html>
