/**
 * Created by Administrator on 2017/6/5.
 */


$(function () {

    gtoken = ReadCookie("token");

    if (gtoken != "") {
        setCurrentUser();

        $('#useradd').window('close');
        $('#userupdate').window('close');
        $('#usersearch').window('close');

        initdatagrid();

        initcombobox();
    } else {
        window.location.href = platform_url + "/pages/home/login.html";
    }


});


function initdatagrid() {
    $('#usergrid').datagrid({
        title: '用户管理 ',
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

                $('#rolelist').combobox({
                        reload: ws_url + '/rest/roles/findAllRole?token=' + gtoken
                    }
                )

                $('#useradd').window('open');
            }
        }, '-', {
            id: 'btnUpdate',
            text: '修改',
            iconCls: 'icon-save',
            handler: function () {

                //var row = $('#usergrid').datagrid('getSelected');
                var rows = $('#usergrid').datagrid('getSelections');

                //console.log(rows);
                //console.log(rows[0].userId);

                if (!rows || rows.length == 0) {
                    $.messager.alert('提示', '请选择要修改的数据');
                    return;
                }
                else {

                    if (rows.length > 1) {
                        $.messager.alert('提示', '请选择一条数据');
                        $('#usergrid').datagrid("clearSelections");
                        return;
                    } else {
                        //window.location.href = "/UserInfo/View/" + row.ID;

                        $('#rolelist_update').combobox({
                            reload: ws_url + '/rest/roles/findAllRole?token=' + gtoken
                        })

                        $('#form2').form('load', {
                            userId_update: rows[0].userId,
                            realname_update: rows[0].realname,
                            rolelist_update: rows[0].roleId,
                            phone_update: rows[0].username,
                            address_update: rows[0].address,
                            orgname_update: rows[0].orgname
                        });

                        $('#userupdate').window('open');
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

                var rows = $('#usergrid').datagrid('getSelections');

                if (!rows || rows.length == 0) {
                    $.messager.alert('提示', '请选择要删除的数据');
                    return;
                }

                console.log(rows);
                console.log(rows[0].userId);

                /*if (rows.length > 1) {
                 $.messager.alert('提示', '请选择一条数据');
                 $('#usergrid').datagrid("clearSelections");
                 return;
                 }

                 if (rows[0].userId == guserid) {
                 $.messager.alert('提示', '不能删除当前登录用户!');
                 $('#usergrid').datagrid("clearSelections");
                 return;
                 }*/

                //var parm = {"userId": rows[0].userId};

                var assetList = new Array();

                $.each(rows, function (i, n) {
                    assetList.push(n.userId);
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
                        url: ws_url + '/rest/user/batchdeleteUser?token=' + gtoken,
                        data: JSON.stringify(assetList),
                        success: function (msg) {
                            if (msg.status == 'success') {
                                $.messager.alert('提示', '删除成功！', "info", function () {
                                    $('#usergrid').datagrid("clearSelections");
                                    $('#usergrid').datagrid("reload");
                                });
                            } else {
                                $.messager.alert('错误', '删除失败！', "error", function () {
                                    $('#usergrid').datagrid("clearSelections");
                                    $('#usergrid').datagrid("reload");
                                });
                            }
                        },
                        error: function (xhr) {
                            console.log(xhr);
                            $('#usergrid').datagrid("clearSelections");
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
                $('#usersearch').window('open');
            }
        }, '-'],

        idField: 'userId',

        frozenColumns: [[
            {field: 'userId', checkbox: true}
        ]],

        columns: [[
            {field: 'username', title: '用户名', width: 80, align: 'center'},
            {field: 'realname', title: '真实姓名', width: 80, align: 'center'},
            {field: 'role_name', title: '角色', width: 80, align: 'center'},
            {field: 'phone', title: ' 联系电话', width: 80, align: 'center'},
            {field: 'address', title: '地址', width: 120, align: 'center'},
            {field: 'orgname', title: '单位名称', width: 120, align: 'center'},
            {field: 'loginIp', title: 'IP地址', width: 80, align: 'center'},
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
            url: ws_url + '/rest/user/findlist?token=' + gtoken,
            type: "post",
            data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize='
            + pagesize + '&recordstartindex=' + recordstartindex
            + '&recordendindex=' + recordendindex + '&username=' + $('#username_search').val() + '',
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                console.log(data);
                $('#usergrid').datagrid('clearSelections')
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

function initcombobox() {
    $('#rolelist_update').combobox({
            url: ws_url + '/rest/roles/findAllRole?token=' + gtoken,
            method: 'get',
            valueField: 'roleId',
            textField: 'role_name',
            icons: [{
                iconCls: 'icon-reload',
                handler: function () {
                    $("#rolelist_update").combobox({
                        reload: ws_url + '/rest/roles/findAllRole?token=' + gtoken
                    })
                }
            }]
        }
    )

    $('#rolelist').combobox({
            url: ws_url + '/rest/roles/findAllRole?token=' + gtoken,
            method: 'get',
            valueField: 'roleId',
            textField: 'role_name',
            icons: [{
                iconCls: 'icon-reload',
                handler: function () {
                    $("#rolelist").combobox({
                        reload: ws_url + '/rest/roles/findAllRole?token=' + gtoken
                    })
                }
            }]
        }
    )
}



