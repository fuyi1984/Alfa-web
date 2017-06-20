/**
 * Created by Administrator on 2017/6/5.
 */

$(function () {

    gtoken = ReadCookie("token");

    if (gtoken != "") {
        setCurrentUser();

        $('#roleadd').window('close');
        $('#roleupdate').window('close');
        $('#rolesearch').window('close');

        initdatagrid();
    } else {
        window.location.href = platform_url + "/pages/home/login.html";
    }
});


function initdatagrid() {
    $('#rolegrid').datagrid({
        title: '角色管理 ',
        singleSelect: false,
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

        toolbar: ['-', {
            id: 'btnSave',
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {
                /**
                 window.location.href = "/";

                 $('#grid').edatagrid('addRow');

                 */

                //this.href = 'alfa-platform-easyui/pages/sysconfig/edit.html';
                $('#roleadd').window('open');
            }
        }, '-', {
            id: 'btnUpdate',
            text: '修改',
            iconCls: 'icon-save',
            handler: function () {

                //var row = $('#rolegrid').datagrid('getSelected');

                var rows = $('#rolegrid').datagrid('getSelections');

                if (!rows || rows.length == 0) {
                    $.messager.alert('提示', '请选择要修改的数据');
                    return;
                }
                else{

                    if(rows.length>1){
                        $.messager.alert('提示', '请选择一条数据');
                        $('#rolegrid').datagrid("clearSelections");
                        return;
                    }else {
                        //window.location.href = "/UserInfo/View/" + row.ID;

                        $('#form2').form('load', {
                            roleId_update: row.roleId,
                            role_name_update: row.role_name,
                            /*statusname_update:row.statusname,*/
                            menuitem_update: row.menuitem,
                            roleDesc_update: row.roleDesc
                        });

                        $('#roleupdate').window('open');
                    }

                }
                //$('#update').window('open');

            }
        }, '-', {
            id: 'btnDelete',
            text: '删除',
            disabled: false,
            iconCls: 'icon-cut',
            handler: function () {

                var rows = $('#rolegrid').datagrid('getSelections');

                if (!rows || rows.length == 0) {
                    $.messager.alert('提示', '请选择要删除的数据');
                    return;
                }

                //console.log(rows);
                //console.log(rows[0].roleId);

                /*if(rows.length>1){
                    $.messager.alert('提示', '请选择一条数据');
                    $('#rolegrid').datagrid("clearSelections");
                    return;
                }*/

                for(var i=0;i<rows.length;i++) {
                    if (rows[i].roleId == groleid) {
                        $.messager.alert('提示', '不能删除当前角色!');
                        $('#rolegrid').datagrid("clearSelections");
                        return;
                    }

                    if (rows[i].roleId == 15 || rows[i].roleId == 9 || rows[i].roleId == 10) {
                        $.messager.alert('提示', '不能删除此角色!');
                        $('#rolegrid').datagrid("clearSelections");
                        return;
                    }
                }

                //var parm = {"roleId": rows[0].roleId};

                var assetList = new Array();

                $.each(rows, function (i, n) {
                    assetList.push(n.roleId);
                });

                $.messager.confirm('提示', '是否删除这些数据?', function (r) {
                    if (!r) {
                        return;
                    }

                    $.ajax({
                        cache: false,
                        datatype: 'json',
                        contentType: 'application/json;charset=UTF-8',
                        type: "POST",
                        url: ws_url + '/rest/roles/batchdeleterole?token=' + gtoken,
                        data: JSON.stringify(assetList),
                        success: function (msg) {
                            if (msg.status == 'success') {
                                $.messager.alert('提示', '删除成功！', "info", function () {
                                    $('#rolegrid').datagrid("clearSelections");
                                    $('#rolegrid').datagrid("reload");
                                });
                            } else {
                                $.messager.alert('错误', '删除失败！', "error", function () {
                                    $('#rolegrid').datagrid("clearSelections");
                                    $('#rolegrid').datagrid("reload");
                                });
                            }
                        },
                        error: function (xhr) {
                            console.log(xhr);
                            $('#rolegrid').datagrid("clearSelections");
                            $.messager.alert('错误', '删除失败！', "error");
                        }
                    });
                });

            }
        }, '-', {
            id: 'btnSearch',
            text: '查询',
            disabled: false,
            iconCls: 'icon-search',
            handler: function () {
                $('#rolesearch').window('open');
            }
        }, '-'],

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
        rownumbers: true

    });

    function ajaxfindlist(param, success, error) {

        console.log(param);

        var pagenum = param.page - 1;
        var pagesize = param.rows;
        var recordstartindex = param.page == 1 ? 0 : param.rows * (param.page - 1);
        var recordendindex = param.rows * param.page;

        $.ajax({
            url: ws_url + '/rest/roles/findlist?token=' + gtoken,
            type: "post",
            data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize=' + pagesize + '&recordstartindex=' + recordstartindex + '&recordendindex=' + recordendindex + '&roleName=' + $('#role_name_search').val() + '',
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                console.log(data);
                $('#rolegrid').datagrid('clearSelections')
                $('#form3').form('clear');
                success(data);
            },
            error: function (xhr) {
                console.log(xhr);
                error(xhr.responseText);
            }
        });
    }
}







