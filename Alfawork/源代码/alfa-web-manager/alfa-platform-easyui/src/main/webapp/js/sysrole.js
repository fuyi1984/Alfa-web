/**
 * Created by Administrator on 2017/6/5.
 */

$(function () {

    setCurrentUser();

    $('#add').window('close');
    $('#update').window('close');
    $('#search').window('close');

    initdatagrid();
});


function initdatagrid() {
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
                /**
                 window.location.href = "/";

                 $('#grid').edatagrid('addRow');

                 */

                //this.href = 'alfa-platform-easyui/pages/sysconfig/edit.html';
                $('#add').window('open');
            }
        }, '-', {
            id: 'btnUpdate',
            text: '修改',
            iconCls: 'icon-save',
            handler: function () {

                var row = $('#grid').datagrid('getSelected');

                console.log(row);

                if (row) {

                    //window.location.href = "/UserInfo/View/" + row.ID;

                    $('#form2').form('load', {
                        roleId_update: row.roleId,
                        role_name_update: row.role_name,
                        /*statusname_update:row.statusname,*/
                        menuitem_update: row.menuitem,
                        roleDesc_update: row.roleDesc
                    });

                    $('#update').window('open');

                }
                else {
                    $.messager.alert('提示', '请选择要修改的数据');
                    return;
                }

                //$('#update').window('open');

            }
        }, '-', {
            id: 'btnDelete',
            text: '删除',
            disabled: false,
            iconCls: 'icon-cut',
            handler: function () {

                var rows = $('#grid').datagrid('getSelections');
                if (!rows || rows.length == 0) {
                    $.messager.alert('提示', '请选择要删除的数据');
                    return;
                }

                console.log(rows);
                console.log(rows[0].roleId);

                var parm = {"roleId": rows[0].roleId};

                $.messager.confirm('提示', '是否删除这些数据?', function (r) {
                    if (!r) {
                        return;
                    }

                    $.ajax({
                        cache: false,
                        datatype: 'json',
                        contentType: 'application/json;charset=UTF-8',
                        type: "POST",
                        url: ws_url + '/rest/roles/deleterole?token='+gtoken,
                        data: JSON.stringify(parm),
                        success: function (msg) {
                            if (msg.status == 'success') {
                                $.messager.alert('提示', '删除成功！', "info", function () {
                                    $('#grid').datagrid("clearSelections");
                                    $('#grid').datagrid("reload");
                                });
                            } else {
                                $.messager.alert('错误', '删除失败！', "error", function () {
                                    $('#grid').datagrid("clearSelections");
                                    $('#grid').datagrid("reload");
                                });
                            }
                        },
                        error: function (xhr) {
                            console.log(xhr);
                            $('#grid').datagrid("clearSelections");
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
                $('#search').window('open');
            }
        }, '-'],

        /*onClickRow:function(rowIndex,rowData){
         if(isChecked(rowData)){
         $("#grid").datagrid("unselectRow", rowIndex);
         }else{
         $("#grid").datagrid("unselectRow", rowIndex);
         }
         }*/

    });

    function ajaxfindlist(param, success, error) {

        console.log(param);

        var pagenum = param.page - 1;
        var pagesize = param.rows;
        var recordstartindex = param.page == 1 ? 0 : param.rows * (param.page - 1);
        var recordendindex = param.rows * param.page;

        $.ajax({
            url: ws_url + '/rest/roles/findlist?token='+gtoken,
            type: "post",
            data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize=' + pagesize + '&recordstartindex=' + recordstartindex + '&recordendindex=' + recordendindex + '&roleName=' + $('#role_name_search').val() + '',
            contentType: 'application/json;charset=UTF-8',
            async:true,
            success: function (data) {
                console.log(data);
                $('#grid').datagrid('clearSelections')
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

function submitForm(){

    var params={
        "role_name": $('#role_name_add').val(),
        "menuitem": $('#menuitem_add').val(),
        "roleDesc": $('#roleDesc_add').val()
    }

    if(params.role_name=="")
    {
        $.messager.alert('提示', '角色名称不能为空');
        return;
    }

    if(params.menuitem=="")
    {
        $.messager.alert('提示', '菜单路径不能为空');
        return;
    }

    console.log(params);

    $.ajax({
        url: ws_url+'/rest/roles/addrole?token='+gtoken,
        contentType: 'application/json;charset=UTF-8',
        type: 'post',
        datatype: 'json',
        data:JSON.stringify(params),
        cache:false,
        success: function (data) {

            console.log(data.status);
            console.log(data.message);

            $('#form1').form('clear');

            if(data.status=='success'){
                $.messager.alert('提示', '添加成功！', 'info', function () {
                    //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                    $('#add').window('close');
                    $('#grid').datagrid("reload");
                });
            }else if(data.status=='failure'){
                if(data.message=='ERROR_ROLES_EXISTS'){
                    $.messager.alert('提示', '数据已经存在,添加失败！', 'warning', function () {
                        //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                        $('#add').window('close');
                        $('#grid').datagrid("reload");
                    });
                }else{
                    $.messager.alert('提示', '添加失败！', 'error', function () {
                        //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                        $('#add').window('close');
                        $('#grid').datagrid("reload");
                    });
                }
            }
        },
        error: function (xhr) {
            console.log(xhr);
        }
    });
}

function searchform() {
    $('#search').window('close');
    initdatagrid();
}

function updateform(){

    var params={
        "roleId":$('#roleId_update').val(),
        "role_name": $('#role_name_update').val(),
        "menuitem": $('#menuitem_update').val(),
        "roleDesc": $('#roleDesc_update').val()
    }

    console.log(params);

    if(params.role_name=="")
    {
        $.messager.alert('提示', '角色名称不能为空');
        return;
    }

    if(params.menuitem=="")
    {
        $.messager.alert('提示', '菜单路径不能为空');
        return;
    }

    $.ajax({
        url: ws_url+'/rest/roles/editrole?token='+gtoken,
        contentType: 'application/json;charset=UTF-8',
        type: 'post',
        datatype: 'json',
        data:JSON.stringify(params),
        cache:false,
        success: function (data) {

            console.log(data.status);
            console.log(data.message);

            $('#form2').form('clear');

            if(data.status=='success'){
                $.messager.alert('提示', '修改成功！', 'info', function () {
                    //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                    $('#update').window('close');
                    $('#grid').datagrid("clearSelections");
                    $('#grid').datagrid("reload");
                });
            }else if(data.status=='failure'){

                $.messager.alert('提示', '修改失败！', 'error', function () {
                    //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                    $('#update').window('close');
                    $('#grid').datagrid("clearSelections");
                    $('#grid').datagrid("reload");
                });

            }
        },
        error: function (xhr) {
            console.log(xhr);
        }
    });
}