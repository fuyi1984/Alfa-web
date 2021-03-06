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

        //$("#status").combobox('select', '全部');
    } else {
        top.location.href = platform_url + "/pages/home/login.html";
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

        //region

        /*toolbar: ['-', {
         id: 'btnSave',
         text: '添加',
         iconCls: 'icon-add',
         handler: function () {
         /!**
         window.location.href = "/";

         $('#grid').edatagrid('addRow');

         *!/

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

         /!*if (rows.length > 1) {
         $.messager.alert('提示', '请选择一条数据');
         $('#usergrid').datagrid("clearSelections");
         return;
         }

         if (rows[0].userId == guserid) {
         $.messager.alert('提示', '不能删除当前登录用户!');
         $('#usergrid').datagrid("clearSelections");
         return;
         }*!/

         //var parm = {"userId": rows[0].userId};

         for (var i = 0; i < rows.length; i++) {
         if (rows[i].userId == guserid) {
         $.messager.alert('提示', '不能删除当前登录用户!');
         $('#usergrid').datagrid("clearSelections");
         return;
         }
         }

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
         },'-',{
         id:'btnIsCheck',
         text:'审核',
         disabled:false,
         iconCls:'icon-search',
         handler:function(){

         var rows = $('#usergrid').datagrid('getSelections');

         if (!rows || rows.length == 0) {
         $.messager.alert('提示', '请选择需要审核的用户');
         return;
         }

         console.log(rows);
         console.log(rows[0].userId);

         for (var i = 0; i < rows.length; i++) {

         if (rows[i].status == '1') {
         $.messager.alert('提示', '当前用户已审核,不需要二次审核!');
         $('#usergrid').datagrid("clearSelections");
         return;
         }

         }

         var assetList = new Array();

         $.each(rows, function (i, n) {
         assetList.push(n.userId);
         });


         $.messager.confirm('提示', '是否需要审核这些用户?', function (r) {
         if (!r) {
         return;
         }

         $.ajax({
         cache: false,
         datatype: 'json',
         contentType: 'application/json;charset=UTF-8',
         type: "POST",
         url: ws_url + '/rest/user/batchUpdateUserStatus?token=' + gtoken,
         data: JSON.stringify(assetList),
         success: function (msg) {
         if (msg.status == 'success') {
         $.messager.alert('提示', '审核成功！', "info", function () {
         $('#usergrid').datagrid("clearSelections");
         $('#usergrid').datagrid("reload");
         });
         } else {
         $.messager.alert('错误', '审核失败！', "error", function () {
         $('#usergrid').datagrid("clearSelections");
         $('#usergrid').datagrid("reload");
         });
         }
         },
         error: function (xhr) {
         console.log(xhr);
         $('#usergrid').datagrid("clearSelections");
         $.messager.alert('错误', '审核失败！', "error");
         }
         });
         });
         }

         },'-', {
         id: 'btnSearch',
         text: '查询',
         disabled: false,
         iconCls: 'icon-search',
         handler: function () {
         $('#usersearch').window('open');
         }
         }, '-'],
         */
        //endregion

        toolbar: "#tb",

        idField: 'userId',

        frozenColumns: [[
            {field: 'userId', checkbox: true}
        ]],

        columns: [[
            {field: 'username', title: '用户名', width: 80, align: 'center'},
            {field: 'realname', title: '真实姓名', width: 80, align: 'center'},
            {field: 'role_name', title: '角色', width: 80, align: 'center'},
            {
                field: 'status', title: '状态', width: 80, align: 'center',
                formatter: function (value, rec) {
                    switch (value) {
                        case "0":
                            return '<span style="color:red;">未审核</span>';
                        case "1":
                            return '<span style="color:green;">已审核</span>';
                        case "-1":
                            return '<span style="color:orangered;">数据不完整</span>';
                    }
                }
            },
            {field: 'phone', title: ' 联系电话', width: 80, align: 'center'},
            {field: 'address', title: '单位地址', width: 120, align: 'center'},
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
            + '&recordendindex=' + recordendindex + '&username=' + $('#busername').val() + '&realname=' + $("#brealname").val() + '&roleId=' + $("#rolename").combogrid('getValue') + '&status=' + $("#status").combobox('getValue') + '&org=' + $("#org").val() + '&address=' + $('#address').val() + '&startDt=' + $('#startDt').datebox('getValue') + '&endDt=' + $('#endDt').datebox('getValue') + '',
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                console.log(data);
                $('#usergrid').datagrid('clearSelections');
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
    $('#rolelist_update,#rolename,#rolelist').combogrid({
            url: ws_url + '/rest/roles/findAllRole?token=' + gtoken,
            method: 'get',
            idField: 'roleId',
            textField: 'role_name',
            panelWidth: 320,
            panelHeight: 'auto',
            columns: [[
                {field: 'role_name', title: '角色名称', width: 120},
                {field: 'roleDesc', title: ' 角色描述', width: 200}
            ]]
        }
    )

    /*$('#rolelist').combobox({
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
     )*/

}

/**
 * 添加
 */
function doAdd() {
    //$('#rolelist').combogrid({
    //        reload: ws_url + '/rest/roles/findAllRole?token=' + gtoken
    //    }
    //)

    $('#useradd').window('open');
}

/**
 * 修改
 */
function doUpdate() {
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

            //$('#rolelist_update').combogrid({
            //    reload: ws_url + '/rest/roles/findAllRole?token=' + gtoken
            //})

            $('#form2').form('load', {
                userId_update: rows[0].userId,
                realname_update: rows[0].realname,
                //rolelist_update: rows[0].role_name,
                phone_update: rows[0].username,
                address_update: rows[0].address,
                orgname_update: rows[0].orgname
            });

            $('#rolelist_update').combogrid('setValue', rows[0].roleId);

            $('#userupdate').window('open');
        }

    }

    //$('#update').window('open');
}

/**
 * 删除
 */
function doDelete() {
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

    for (var i = 0; i < rows.length; i++) {
        if (rows[i].userId == guserid) {
            $.messager.alert('提示', '不能删除当前登录用户!');
            $('#usergrid').datagrid("clearSelections");
            return;
        }
    }

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

/**
 * 审核
 */
function doIsCheck() {
    var rows = $('#usergrid').datagrid('getSelections');

    if (!rows || rows.length == 0) {
        $.messager.alert('提示', '请选择需要审核的用户');
        return;
    }

    console.log(rows);
    console.log(rows[0].userId);

    for (var i = 0; i < rows.length; i++) {

        if (rows[i].status == '1') {
            $.messager.alert('提示', '当前用户已审核,不需要二次审核!');
            $('#usergrid').datagrid("clearSelections");
            return;
        }

    }

    var assetList = new Array();

    $.each(rows, function (i, n) {
        assetList.push(n.userId);
    });


    $.messager.confirm('提示', '是否需要审核这些用户?', function (r) {
        if (!r) {
            return;
        }

        $.ajax({
            cache: false,
            datatype: 'json',
            contentType: 'application/json;charset=UTF-8',
            type: "POST",
            url: ws_url + '/rest/user/batchUpdateUserStatus?token=' + gtoken,
            data: JSON.stringify(assetList),
            success: function (msg) {
                if (msg.status == 'success') {
                    $.messager.alert('提示', '审核成功！', "info", function () {
                        $('#usergrid').datagrid("clearSelections");
                        $('#usergrid').datagrid("reload");
                    });
                } else {
                    $.messager.alert('错误', '审核失败！', "error", function () {
                        $('#usergrid').datagrid("clearSelections");
                        $('#usergrid').datagrid("reload");
                    });
                }
            },
            error: function (xhr) {
                console.log(xhr);
                $('#usergrid').datagrid("clearSelections");
                $.messager.alert('错误', '审核失败！', "error");
            }
        });
    });
}



