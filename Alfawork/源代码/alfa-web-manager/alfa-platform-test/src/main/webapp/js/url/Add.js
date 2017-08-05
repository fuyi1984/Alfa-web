/**
 * Created by Administrator on 2017/7/16.
 */

function AddValidator(){
    if ($("#url_add").val() == "") {
        $.messager.alert('提示', 'url不能为空');
        return false;
    }

    if ($("#Apiname_add").val() == "") {
        $.messager.alert('提示', 'Api名称不能为空');
        return false;
    }

    return true;
}

function urlsubmitForm(){
    if(AddValidator()){
        var params = {
            "apiAddress": $("#url_add").val(),
            "apiname": $("#Apiname_add").val(),
            "types":types
        }

        console.log(params);

        $.ajax({
            url: ws_url + '/rest/url/inserturl?token=' + gtoken,
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
                        $('#urladd').window('close');
                        $('#urlgrid').datagrid("reload");
                    });
                } else if (data.status == 'failure') {
                    if (data.message == '1') {
                        $.messager.alert('提示', '数据已存在,无法添加！', 'warning', function () {
                            //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                            $('#urladd').window('close');
                            $('#urlgrid').datagrid("reload");
                        });
                    } else {
                        $.messager.alert('提示', '添加失败！', 'error', function () {
                            //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                            $('#urladd').window('close');
                            $('#urlgrid').datagrid("reload");
                        });
                    }
                }
            },
            error: function (xhr) {
                console.log(xhr);
            }
        });
    }
}