/**
 * Created by Administrator on 2017/5/23.
 */

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
        url: '/alfa-ws/rest/roles/addrole',
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