/**
 * Created by Administrator on 2017/6/5.
 */


$(function () {

    setCurrentUser();

    $('#add').window('close');
    $('#update').window('close');
    $('#search').window('close');

    initdatagrid();

    initcombobox();


});


function initdatagrid() {
    $('#grid').datagrid({
        title: '用户管理 ',
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
                        userId_update: row.userId,
                        realname_update: row.realname,
                        rolelist_update: row.roleId,
                        phone_update: row.username,
                        address_update: row.address
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
                console.log(rows[0].userId);

                var parm = {"userId": rows[0].userId};

                $.messager.confirm('提示', '是否删除这些数据?', function (r) {
                    if (!r) {
                        return;
                    }

                    $.ajax({
                        cache: false,
                        datatype: 'json',
                        contentType: 'application/json;charset=UTF-8',
                        type: "POST",
                        url: ws_url + '/rest/user/deleteUser?token='+gtoken,
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
            url: ws_url + '/rest/user/findlist?token='+gtoken,
            type: "post",
            data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize='
            + pagesize + '&recordstartindex=' + recordstartindex
            + '&recordendindex=' + recordendindex + '&username=' + $('#username_search').val() + '',
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


function initcombobox() {
    $('.easyui-combobox').combobox({
            url: ws_url + '/rest/roles/findAllRole?token='+gtoken,
            method: 'get',
            valueField: 'roleId',
            textField: 'role_name'
        }
    )

    /*$('#sexlist').combobox({
     url: 'combobox_data.json',
     method:'get',
     valueField:'id',
     textField:'text'
     })*/
}

function submitForm(){

    //alert($('#sexlist').combobox('getValue'));
    var username=$('#phone_add').val();
    var phone=$('#phone_add').val();
    var password=$('#password_add').val();
    var repassword=$('#repassword').val();
    //var sex=$('#sexlist').combobox('getValue');
    var role=$('#rolelist').combobox('getValue');
    var address=$('#address_add').val();
    var realname=$('#realname_add').val();

    if(username==""||phone=="")
    {
        $.messager.alert('提示', '手机号不能为空!');
        return;
    }

    if(password.length<5 && password.length>32){
        $.messager.alert('提示', '密码长度大于5小于32!');
        return;
    }

    if(repassword.length<5 && repassword.length>32){
        $.messager.alert('提示', '确认密码长度大于5小于32!');
        return;
    }

    if(repassword != password){
        $.messager.alert('提示', '密码和确认密码不相等!');
        return;
    }

    if(realname==""){
        $.messager.alert('提示', '真实姓名不能为空!');
        return;
    }

    /*if(sex==""){
     $.messager.alert('提示', '性别不能为空!');
     return;
     }*/

    if(role==""){
        $.messager.alert('提示', '角色不能为空!');
        return;
    }

    var params={
        "username": username,
        "phone": phone,
        "password": password,
        /*"sex":sex,*/
        "roleId":role,
        "address":address,
        "realname":realname
    };

    console.log(params);

    $.ajax({
        url: ws_url+'/rest/user/insertUser?token='+gtoken,
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
                if(data.message=='USER_EXIST_SUCCESS'){
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

    //alert($('#sexlist').combobox('getValue'));
    var userId=$('#userId_update').val();
    var username=$('#phone_update').val();
    var phone=$('#phone_update').val();
    var role=$('#rolelist_update').combobox('getValue');
    var address=$('#address_update').val();
    var realname=$('#realname_update').val();

    var params={
        "userId":userId,
        "username":username,
        "phone":phone,
        "roleId": role,
        "address":address,
        "realname":realname
    }

    console.log(params);

    if(phone=="")
    {
        $.messager.alert('提示', '联系电话不能为空');
        return;
    }

    if(realname=="")
    {
        $.messager.alert('提示', '真实姓名不能为空');
        return;
    }

    if(role=="")
    {
        $.messager.alert('提示', '角色不能为空');
        return;
    }

    $.ajax({
        url: ws_url+'/rest/user/editUser?token'+gtoken,
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