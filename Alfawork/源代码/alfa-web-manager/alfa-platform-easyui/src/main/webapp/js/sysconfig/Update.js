/**
 * Created by Administrator on 2017/6/6.
 */
function configupdateform() {
    var params = {
        "id": $.trim($('#Id_update').val()),
        "configName": $.trim($('#configName_update').val()),
        "configKey": $.trim($('#configKey_update').val()),
        "configValue": $.trim($('#configValue_update').val()),
        "description": $.trim($('#description_update').val())
    }

    console.log(params);

    if (params.configName == "") {
        $.messager.alert('提示', '配置项名称不能为空');
        return;
    }

    if (params.configKey == "") {
        $.messager.alert('提示', '配置项代码不能为空');
        return;
    }

    if (params.configValue == "") {
        $.messager.alert('提示', '配置项值不能为空');
        return;
    }

    $.ajax({
        url: ws_url + '/rest/Sysconfig/updateConfig?token=' + gtoken,
        contentType: 'application/json;charset=UTF-8',
        type: 'post',
        datatype: 'json',
        data: JSON.stringify(params),
        cache: false,
        success: function (data) {

            console.log(data.status);
            console.log(data.message);

            $('#form2').form('clear');

            if (data.status == 'success') {
                $.messager.alert('提示', '修改成功！', 'info', function () {
                    //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                    $('#configupdate').window('close');
                    $('#configgrid').datagrid("clearSelections");
                    $('#configgrid').datagrid("reload");
                });
            } else if (data.status == 'failure') {

                $.messager.alert('提示', '修改失败！', 'error', function () {
                    //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                    $('#configupdate').window('close');
                    $('#configgrid').datagrid("clearSelections");
                    $('#configgrid').datagrid("reload");
                });

            }
        },
        error: function (xhr) {
            console.log(xhr);
        }
    });
}
