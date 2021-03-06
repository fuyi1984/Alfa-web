/**
 * Created by Administrator on 2017/6/6.
 */
function usersubmitForm(){

    //alert($('#sexlist').combobox('getValue'));
    var username=$('#phone_add').val();
    var phone=$('#phone_add').val();
    var password=$('#password_add').val();
    var repassword=$('#repassword').val();
    //var sex=$('#sexlist').combobox('getValue');
    var role=$('#rolelist').combogrid('getValue');
    var address=$('#address_add').val();
    var realname=$('#realname_add').val();
    var orgname=$('#orgname_add').val();

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
        "realname":realname,
        "orgname":orgname
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
                    $('#useradd').window('close');
                    $('#usergrid').datagrid("reload");
                });
            }else if(data.status=='failure'){
                if(data.message=='USER_EXIST_SUCCESS'){
                    $.messager.alert('提示', '数据已经存在,添加失败！', 'warning', function () {
                        //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                        $('#useradd').window('close');
                        $('#usergrid').datagrid("reload");
                    });
                }else{
                    $.messager.alert('提示', '添加失败！', 'error', function () {
                        //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                        $('#useradd').window('close');
                        $('#usergrid').datagrid("reload");
                    });
                }
            }
        },
        error: function (xhr) {
            console.log(xhr);
        }
    });
}
