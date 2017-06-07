/**
 * Created by Administrator on 2017/6/6.
 */

function configsubmitForm() {

    var params = {
        "configName": $('#configName_add').val(),
        "configKey": $('#configKey_add').val(),
        "configValue": $('#configValue_add').val(),
        "description": $('#description_add').val()
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
        url: ws_url + '/rest/Sysconfig/insertConfig?token=' + gtoken,
        contentType: 'application/json;charset=UTF-8',
        type: 'post',
        datatype: 'json',
        data: JSON.stringify(params),
        cache: false,
        success: function (data) {

            console.log(data.status);
            console.log(data.message);

            $('#form1').form('clear');

            if (data.status == 'success') {
                $.messager.alert('提示', '添加成功！', 'info', function () {
                    //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                    $('#configadd').window('close');
                    $('#configgrid').datagrid("reload");
                });
            } else if (data.status == 'failure') {
                if (data.message == 'Configuration.Exists.Success') {
                    $.messager.alert('提示', '数据已经存在,添加失败！', 'warning', function () {
                        //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                        $('#configadd').window('close');
                        $('#configgrid').datagrid("reload");
                    });
                } else {
                    $.messager.alert('提示', '添加失败！', 'error', function () {
                        //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                        $('#configadd').window('close');
                        $('#configgrid').datagrid("reload");
                    });
                }
            }
        },
        error: function (xhr) {
            console.log(xhr);
        }
    });
}