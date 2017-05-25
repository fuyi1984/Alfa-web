/**
 * Created by Administrator on 2017/5/23.
 */

function updateform(){
    var params={
        "id":$('#Id_update').val(),
        "configName": $('#configName_update').val(),
        "configKey": $('#configKey_update').val(),
        "configValue": $('#configValue_update').val(),
        "description": $('#description_update').val()
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

            $('#form2').form('clear');

            if(data.status=='success'){
                $.messager.alert('提示', '添加成功！', 'info', function () {
                    //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                    $('#update').window('close');
                    $('#grid').datagrid("clearSelections");
                    $('#grid').datagrid("reload");
                });
            }else if(data.status=='failure'){

                    $.messager.alert('提示', '添加失败！', 'error', function () {
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