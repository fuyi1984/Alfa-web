/**
 * Created by Administrator on 2017/5/23.
 */

function updateForm(){
    var params={
        "id":$('#Idu').val(),
        "configName": $('#configNameu').val(),
        "configKey": $('#configKeyu').val(),
        "configValue": $('#configValueu').val(),
        "description": $('#descriptionu').val()
    }

    console.log(params);

    $.ajax({
        url: '/alfa-ws/rest/Sysconfig/updateConfig',
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
                    $('#update').window('close');
                    $('#grid').datagrid("reload");
                });
            }else if(data.status=='failure'){

                    $.messager.alert('提示', '添加失败！', 'error', function () {
                        //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                        $('#update').window('close');
                        $('#grid').datagrid("reload");
                    });

            }
        },
        error: function (xhr) {
            console.log(xhr);
        }
    });
}