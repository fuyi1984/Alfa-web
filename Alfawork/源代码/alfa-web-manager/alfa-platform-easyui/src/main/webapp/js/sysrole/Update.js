/**
 * Created by Administrator on 2017/6/6.
 */
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