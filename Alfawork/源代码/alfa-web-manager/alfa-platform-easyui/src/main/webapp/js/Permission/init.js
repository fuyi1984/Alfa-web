$(function () {

    gtoken = ReadCookie("token");

    if (gtoken != "") {
        setCurrentUser();

        initdatagrid();

        initcombobox();

    } else {
        //window.location.href = platform_url + "/pages/home/login.html";
        top.location.href=platform_url + "/pages/home/login.html";
    }


});


function initdatagrid() {
    $('#Permissiongrid').datagrid({
        title: '权限管理',
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

        /*
        toolbar: ['-', {
            id: 'btnSave',
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {
                $('#menuadd').window('open');
            }
        },'-',{
            id: 'btnUpdate',
            text: '修改',
            iconCls: 'icon-save',
            handler:function(){

                var rows = $('#menugrid').datagrid('getSelections');

                if (!rows || rows.length == 0) {
                    $.messager.alert('提示', '请选择要修改的数据');
                    return;
                }else{
                    if(rows.length>1){
                        $.messager.alert('提示', '请选择一条数据');
                        $('#menugrid').datagrid("clearSelections");
                        return;
                    }else {
                        $('#form2').form('load', {
                            Idedit: rows[0].menuid,
                            CascadeIdedit: rows[0].cascadeid,
                            MenuNameedit: rows[0].menuname,
                            Iconedit: rows[0].icon,
                            Urledit:rows[0].url,
                            ParentIdedit: rows[0].parentid
                        });
                        $('#menuedit').window('open');
                    }
                }
            }
        },'-', {
            id: 'btnDelete',
            text: '删除',
            disabled: false,
            iconCls: 'icon-cut',
            handler: function () {

                var rows = $('#menugrid').datagrid('getSelections');

                if (!rows || rows.length == 0) {
                    $.messager.alert('提示', '请选择要删除的数据');
                    return;
                }

                console.log(rows);
                console.log(rows[0].menuid);

                var assetList = new Array();

                $.each(rows, function (i, n) {
                    assetList.push(n.menuid);
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
                        url: ws_url + '/rest/menu/batchdeletemenu?token=' + gtoken,
                        data: JSON.stringify(assetList),
                        success: function (msg) {
                            if (msg.status == 'success') {
                                $.messager.alert('提示', '删除成功！', "info", function () {
                                    $('#menugrid').datagrid("clearSelections");
                                    $('#menugrid').datagrid("reload");
                                });
                            } else {
                                $.messager.alert('错误', '删除失败！', "error", function () {
                                    $('#menugrid').datagrid("clearSelections");
                                    $('#menugrid').datagrid("reload");
                                });
                            }
                        },
                        error: function (xhr) {
                            console.log(xhr);
                            $('#menugrid').datagrid("clearSelections");
                            $.messager.alert('错误', '删除失败！', "error");
                        }
                    });
                });
            }
        }, '-'],
        */

        //endregion

        toolbar: "#tb",

        idField: 'id',

        frozenColumns: [[
            {field: 'id', checkbox: true}
        ]],

        columns: [[

            {field: 'role_name', title: '角色名', width: 80, align: 'center'},
            {field: 'menuname', title: '菜单名称', width: 80, align: 'center'},
            {field: 'icon', title: '图标', width: 80, align: 'center'},
            {field: 'url', title: 'Url', width: 100, align: 'center'},
            {field: 'createdBy', title: '创建人', width: 80, align: 'center'},
            {
                field: 'createdDt', title: '创建时间', width: 100, align: 'center'
            },
            {field: 'updatedBy', title: '更新人', width: 80, align: 'center'},
            {
                field: 'updatedDt', title: '更新时间', width: 100, align: 'center'
            },
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
            url: ws_url + '/rest/menurole/findlist?token=' + gtoken,
            type: "post",
            data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize=' + pagesize + '&recordstartindex=' + recordstartindex + '&recordendindex=' + recordendindex +'&roleid=' + $("#rolename").combogrid('getValue')+'',
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                console.log(data);
                $('#Permissiongrid').datagrid('clearSelections')
                //$('#form3').form('clear');
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
    $('#rolename').combogrid({
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
}


function doDelete(){
    var rows = $('#Permissiongrid').datagrid('getSelections');

    if (!rows || rows.length == 0) {
        $.messager.alert('提示', '请选择要删除的数据');
        return;
    }

    console.log(rows);
    console.log(rows[0].id);

    var assetList = new Array();

    $.each(rows, function (i, n) {
        assetList.push(n.id);
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
            url: ws_url + '/rest/menurole/batchdelete?token=' + gtoken,
            data: JSON.stringify(assetList),
            success: function (msg) {
                if (msg.status == 'success') {
                    $.messager.alert('提示', '删除成功！', "info", function () {
                        $('#Permissiongrid').datagrid("clearSelections");
                        $('#Permissiongrid').datagrid("reload");
                    });
                } else {
                    $.messager.alert('错误', '删除失败！', "error", function () {
                        $('#Permissiongrid').datagrid("clearSelections");
                        $('#Permissiongrid').datagrid("reload");
                    });
                }
            },
            error: function (xhr) {
                console.log(xhr);
                $('#Permissiongrid').datagrid("clearSelections");
                $.messager.alert('错误', '删除失败！', "error");
            }
        });
    });
}

function doSearch() {
    initdatagrid();
}