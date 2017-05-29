/**
 * Created by Administrator on 2017/5/23.
 */

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
        url: '/alfa-ws/rest/user/editUser',
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